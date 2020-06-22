package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRateHistory;
import hsbc.model.ExchangeRatePeriodMatchType;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.ExchangeRateHistoryRepository;
import hsbc.model.repository.ExchangeRateRepository;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.model.view.HistoricalExchangeRates;
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

  @Override
  public CurrentExchangeRates getCurrentBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies) {
    LOGGER.traceEntry();
    CurrentExchangeRates currenctExchangeRates =
        new CurrentExchangeRates(buyingCurrency, sellingCurrencies);
    for (Currency sellingCurrency : sellingCurrencies) {
      Optional<ExchangeRate> currentExchangeRate = exchangeRateRepository
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
      Optional<BigDecimal> rate;
      if (currentExchangeRate.isPresent()) {
        rate = Optional.of(currentExchangeRate.get().getRate());
      } else {
        rate = Optional.empty();
      }
      currenctExchangeRates.setExchangeRate(sellingCurrency, rate);
    }
    return LOGGER.traceExit(currenctExchangeRates);
  }

  @Override
  public CurrentExchangeRates getCurrentBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findByCode(buyingCurrencyCode);
    List<Currency> sellingCurrencies = currencyService.findByCodes(sellingCurrencyCodes);
    return LOGGER.traceExit(getCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies));
  }

  @Override
  public CurrentExchangeRates getCurrentBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findById(buyingCurrencyId);
    List<Currency> sellingCurrencies = currencyService.findByIds(sellingCurrencyIds);
    return LOGGER.traceExit(getCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies));
  }

  @Override
  public CurrentExchangeRates getCurrentBuyingExchangeRates() throws ServiceException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findDefaultSubjectCurrency();
    List<Currency> sellingCurrencies = currencyService.findDefaultOtherCurrencies();
    if (sellingCurrencies.size() == 0) {
      sellingCurrencies = currencyService.findAll();
    }
    return LOGGER.traceExit(getCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies));
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods) {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        new HistoricalExchangeRates(buyingCurrency, sellingCurrencies, periods);
    populateBuyingExchangeRateHistories(historicalExchangeRates, buyingCurrency, sellingCurrencies,
        periods);
    return LOGGER.traceExit(historicalExchangeRates);
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes, List<Period> periods) throws ValidationException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findByCode(buyingCurrencyCode);
    List<Currency> sellingCurrencies = currencyService.findByCodes(sellingCurrencyCodes);
    return LOGGER
        .traceExit(getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER.traceExit(
        getHistoricalBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds, List<Period> periods) throws ValidationException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findById(buyingCurrencyId);
    List<Currency> sellingCurrencies = currencyService.findByIds(sellingCurrencyIds);
    return LOGGER
        .traceExit(getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER
        .traceExit(getHistoricalBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates() throws ServiceException {
    LOGGER.traceEntry();
    Currency buyingCurrency = currencyService.findDefaultSubjectCurrency();
    List<Currency> sellingCurrencies = currencyService.findDefaultOtherCurrencies();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER
        .traceExit(getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods));
  }

  @Override
  public CurrentExchangeRates getCurrentSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies) {
    LOGGER.traceEntry();
    CurrentExchangeRates currentExchangeRates =
        new CurrentExchangeRates(sellingCurrency, buyingCurrencies);
    for (Currency buyingCurrency : buyingCurrencies) {
      Optional<ExchangeRate> currentExchangeRate = exchangeRateRepository
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
      Optional<BigDecimal> rate;
      if (currentExchangeRate.isPresent()) {
        rate = Optional.of(currentExchangeRate.get().getRate());
      } else {
        rate = Optional.empty();
      }
      currentExchangeRates.setExchangeRate(buyingCurrency, rate);
    }
    return LOGGER.traceExit(currentExchangeRates);
  }

  @Override
  public CurrentExchangeRates getCurrentSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findByCode(sellingCurrencyCode);
    List<Currency> buyingCurrencies = currencyService.findByCodes(buyingCurrencyCodes);
    return LOGGER.traceExit(getCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies));
  }

  @Override
  public CurrentExchangeRates getCurrentSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findById(sellingCurrencyId);
    List<Currency> buyingCurrencies = currencyService.findByIds(buyingCurrencyIds);
    return LOGGER.traceExit(getCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies));
  }

  @Override
  public CurrentExchangeRates getCurrentSellingExchangeRates() throws ServiceException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findDefaultSubjectCurrency();
    List<Currency> buyingCurrencies = currencyService.findDefaultOtherCurrencies();
    return LOGGER.traceExit(getCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies));
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies, List<Period> periods) {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        new HistoricalExchangeRates(sellingCurrency, buyingCurrencies, periods);
    populateSellingExchangeRateHistories(historicalExchangeRates, sellingCurrency, buyingCurrencies,
        periods);
    return LOGGER.traceExit(historicalExchangeRates);
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes, List<Period> periods) throws ValidationException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findByCode(sellingCurrencyCode);
    List<Currency> buyingCurrencies = currencyService.findByCodes(buyingCurrencyCodes);
    return LOGGER
        .traceExit(getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER.traceExit(
        getHistoricalSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds, List<Period> periods) throws ValidationException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findById(sellingCurrencyId);
    List<Currency> buyingCurrencies = currencyService.findByIds(buyingCurrencyIds);
    return LOGGER
        .traceExit(getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException, ServiceException {
    LOGGER.traceEntry();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER.traceExit(
        getHistoricalSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds, periods));
  }

  @Override
  public HistoricalExchangeRates getHistoricalSellingExchangeRates() throws ServiceException {
    LOGGER.traceEntry();
    Currency sellingCurrency = currencyService.findDefaultSubjectCurrency();
    List<Currency> buyingCurrencies = currencyService.findDefaultOtherCurrencies();
    List<Period> periods =
        periodService.getLatestHistoricalPeriods(defaultPeriodType, defaultNumberHistoricalPeriods);
    return LOGGER
        .traceExit(getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods));
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

  private void populateBuyingExchangeRateHistories(HistoricalExchangeRates historicalExchangeRates,
      Currency buyingCurrency, List<Currency> sellingCurrencies, List<Period> periods) {
    if (periods.size() == 0) {
      return;
    }
    Date fromRangeDateTime = periods.get(0).getStartDateTime();
    Date toRangeDateTime = periods.get(periods.size() - 1).getEndDateTime();
    for (Currency sellingCurrency : sellingCurrencies) {
      populateBuyingExchangeRateHistories(historicalExchangeRates, buyingCurrency, sellingCurrency,
          periods, fromRangeDateTime, toRangeDateTime);
    }
  }

  private void populateBuyingExchangeRateHistories(HistoricalExchangeRates historicalExchangeRates,
      Currency buyingCurrency, Currency sellingCurrency, List<Period> periods,
      Date fromRangeDateTime, Date toRangeDateTime) {
    List<ExchangeRateHistory> exchangeRateHistories =
        exchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, sellingCurrency,
            fromRangeDateTime, toRangeDateTime);
    Optional<ExchangeRate> optionalCurrentExchangeRate = exchangeRateRepository
        .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    for (Period period : periods) {
      Optional<BigDecimal> rateForPeriod =
          getRateForPeriod(period, exchangeRateHistories, optionalCurrentExchangeRate);
      historicalExchangeRates.setExchangeRate(sellingCurrency, period, rateForPeriod);
    }
  }

  private void populateSellingExchangeRateHistories(HistoricalExchangeRates historicalExchangeRates,
      Currency sellingCurrency, List<Currency> buyingCurrencies, List<Period> periods) {
    if (periods.size() == 0) {
      return;
    }
    Date fromRangeDateTime = periods.get(0).getStartDateTime();
    Date toRangeDateTime = periods.get(periods.size() - 1).getEndDateTime();
    for (Currency buyingCurrency : buyingCurrencies) {
      populateSellingExchangeRateHistories(historicalExchangeRates, sellingCurrency, buyingCurrency,
          periods, fromRangeDateTime, toRangeDateTime);
    }
  }

  private void populateSellingExchangeRateHistories(HistoricalExchangeRates historicalExchangeRates,
      Currency sellingCurrency, Currency buyingCurrency, List<Period> periods,
      Date fromRangeDateTime, Date toRangeDateTime) {
    List<ExchangeRateHistory> exchangeRateHistories =
        exchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, sellingCurrency,
            fromRangeDateTime, toRangeDateTime);
    Optional<ExchangeRate> optionalCurrentExchangeRate = exchangeRateRepository
        .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    for (Period period : periods) {
      Optional<BigDecimal> rateForPeriod =
          getRateForPeriod(period, exchangeRateHistories, optionalCurrentExchangeRate);
      historicalExchangeRates.setExchangeRate(buyingCurrency, period, rateForPeriod);
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
