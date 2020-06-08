package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRateHistory;
import hsbc.model.ExchangeRatePeriodMatchType;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.ExchangeRateHistoryRepository;
import hsbc.model.repository.ExchangeRateRepository;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.util.DateUtil;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private static final Logger LOGGER = LogManager.getLogger(ExchangeRateServiceImpl.class);

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  @Autowired
  private ExchangeRateHistoryRepository exchangeRateHistoryRepository;

  @Autowired
  private CurrencyService currencyService;

  @Autowired
  private PeriodService periodService;

  @Value("${exchange.rates.report.default.period.type}")
  private PeriodType defaultPeriodType;

  @Value("${exchange.rates.report.default.number.historical.periods}")
  private int defaultNumberHistoricalPeriods;

  @Value("${exchange.rate.period.match.type}")
  private ExchangeRatePeriodMatchType exchangeRatePeriodMatchType;

  private int precision = 6;

  private RoundingMode roundingMode = RoundingMode.HALF_EVEN;

  private MathContext mathsContext = new MathContext(precision, roundingMode);

  private int scale = 2;

  @Override
  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods) {
    LOGGER.traceEntry();
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView =
        new ExchangeRatesBuyingCurrencyView(buyingCurrency, sellingCurrencies, periods);
    List<ExchangeRate> currentExchangeRates = exchangeRateRepository
        .findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency, sellingCurrencies);
    for (ExchangeRate currentExchangeRate : currentExchangeRates) {
      exchangeRatesBuyingCurrencyView.setCurrentExchangeRate(
          currentExchangeRate.getSellingCurrency(), Optional.of(currentExchangeRate.getRate()));
    }
    populateExchangeRateHistories(exchangeRatesBuyingCurrencyView, buyingCurrency,
        sellingCurrencies, periods);
    return LOGGER.traceExit(exchangeRatesBuyingCurrencyView);
  }

  @Override
  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(
      String buyingCurrencyCode, List<String> sellingCurrencyCodes, List<Period> periods)
      throws ValidationException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findByCode(buyingCurrencyCode);
    List<Currency> sellingCurrencies = currencyService.findByCodes(sellingCurrencyCodes);
    return LOGGER
        .traceExit(getExchangeRatesBuyingCurrencyView(buyingCurrency, sellingCurrencies, periods));
  }

  @Override
  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(
      String buyingCurrencyCode, List<String> sellingCurrencyCodes)
      throws ServiceException, ValidationException {
    LOGGER.traceEntry();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER.traceExit(
        getExchangeRatesBuyingCurrencyView(buyingCurrencyCode, sellingCurrencyCodes, periods));
  }

  private void populateExchangeRateHistories(
      ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView, Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods) {
    if (periods.size() == 0) {
      return;
    }
    Date fromRangeDateTime = periods.get(0).getStartDateTime();
    Date toRangeDateTime = periods.get(periods.size() - 1).getEndDateTime();
    for (Currency sellingCurrency : sellingCurrencies) {
      populateExchangeRateHistories(exchangeRatesBuyingCurrencyView, buyingCurrency,
          sellingCurrency, periods, fromRangeDateTime, toRangeDateTime);
    }
  }

  private void populateExchangeRateHistories(
      ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView, Currency buyingCurrency,
      Currency sellingCurrency, List<Period> periods, Date fromRangeDateTime,
      Date toRangeDateTime) {
    List<ExchangeRateHistory> exchangeRateHistories =
        exchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, sellingCurrency,
            fromRangeDateTime, toRangeDateTime);
    Optional<ExchangeRate> optionalCurrentExchangeRate = exchangeRateRepository
        .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    for (Period period : periods) {
      Optional<BigDecimal> rateForPeriod =
          getRateForPeriod(period, exchangeRateHistories, optionalCurrentExchangeRate);
      exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(sellingCurrency, period,
          rateForPeriod);
    }
  }

  private Optional<BigDecimal> getRateForPeriod(Period period,
      List<ExchangeRateHistory> exchangeRateHistories,
      Optional<ExchangeRate> optionalCurrentExchangeRate) {
    if (exchangeRatePeriodMatchType == ExchangeRatePeriodMatchType.AVERAGE) {
      return getRateForPeriodAverage(period, exchangeRateHistories, optionalCurrentExchangeRate);
    } else {
      return getRateForPeriodDate(period, exchangeRateHistories, optionalCurrentExchangeRate);
    }
  }

  private Optional<BigDecimal> getRateForPeriodAverage(Period period,
      List<ExchangeRateHistory> exchangeRateHistories,
      Optional<ExchangeRate> optionalCurrentExchangeRate) {
    long totalSecondsForAllRates = 0;
    BigDecimal totalRateTimesSeconds = new BigDecimal(0);
    for (ExchangeRateHistory exchangeRateHistory : exchangeRateHistories) {
      Date startDateTime =
          DateUtil.getLaterDate(period.getStartDateTime(), exchangeRateHistory.getStartDateTime());
      Date endDateTime =
          DateUtil.getEarlierDate(period.getEndDateTime(), exchangeRateHistory.getEndDateTime());
      long secondsRateUsed =
          Duration.between(startDateTime.toInstant(), endDateTime.toInstant()).getSeconds();
      if (secondsRateUsed > 0) {
        totalSecondsForAllRates += secondsRateUsed;
        BigDecimal rateTimesSeconds =
            exchangeRateHistory.getRate().multiply(new BigDecimal(secondsRateUsed));
        totalRateTimesSeconds = totalRateTimesSeconds.add(rateTimesSeconds);
      }
    }
    if (optionalCurrentExchangeRate.isPresent()) {
      ExchangeRate currentExchangeRate = optionalCurrentExchangeRate.get();
      Date startDateTime =
          DateUtil.getLaterDate(period.getStartDateTime(), currentExchangeRate.getStartDateTime());
      Date endDateTime = period.getEndDateTime();
      long secondsRateUsed =
          Duration.between(startDateTime.toInstant(), endDateTime.toInstant()).getSeconds();
      if (secondsRateUsed > 0) {
        totalSecondsForAllRates += secondsRateUsed;
        BigDecimal rateTimesSeconds =
            currentExchangeRate.getRate().multiply(new BigDecimal(secondsRateUsed));
        totalRateTimesSeconds = totalRateTimesSeconds.add(rateTimesSeconds);
      }
    }
    Optional<BigDecimal> optionalRate;
    if (totalSecondsForAllRates == 0) {
      optionalRate = Optional.empty();
    } else {
      BigDecimal averageRate =
          totalRateTimesSeconds.divide(new BigDecimal(totalSecondsForAllRates), mathsContext)
              .setScale(scale, roundingMode);
      optionalRate = Optional.of(averageRate);
    }
    return optionalRate;
  }


  private Optional<BigDecimal> getRateForPeriodDate(Period period,
      List<ExchangeRateHistory> exchangeRateHistories,
      Optional<ExchangeRate> optionalCurrentExchangeRate) {
    Date periodDate = null;
    if (exchangeRatePeriodMatchType == ExchangeRatePeriodMatchType.START) {
      periodDate = period.getStartDateTime();
    } else if (exchangeRatePeriodMatchType == ExchangeRatePeriodMatchType.MIDDLE) {
      periodDate = period.getMiddleDateTime();
    } else {
      // ExchangeRatePeriodMatchType.END
      periodDate = period.getEndDateTime();
    }
    Optional<BigDecimal> optionalRate = Optional.empty();
    for (ExchangeRateHistory exchangeRateHistory : exchangeRateHistories) {
      if (DateUtil.isBetween(periodDate, exchangeRateHistory.getStartDateTime(),
          exchangeRateHistory.getEndDateTime())) {
        optionalRate = Optional.of(exchangeRateHistory.getRate());
        break;
      }
    }
    if ((optionalRate.equals(Optional.empty())) && ((optionalCurrentExchangeRate.isPresent()))) {
      ExchangeRate currentExchangeRate = optionalCurrentExchangeRate.get();
      if (!(periodDate.before(currentExchangeRate.getStartDateTime()))) {
        optionalRate = Optional.of(currentExchangeRate.getRate());
      }
    }
    return optionalRate;
  }

  void setDefaultPeriodType(PeriodType defaultPeriodType) {
    this.defaultPeriodType = defaultPeriodType;
  }

  void setDefaultNumberHistoricalPeriods(int defaultNumberHistoricalPeriods) {
    this.defaultNumberHistoricalPeriods = defaultNumberHistoricalPeriods;
  }

  void setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType exchangeRatePeriodMatchType) {
    this.exchangeRatePeriodMatchType = exchangeRatePeriodMatchType;
  }

}
