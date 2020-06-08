package hsbc.service;

import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createExchangeRateServiceTestData;
import static hsbc.test.TestData.createExchangeRates;
import static hsbc.test.TestData.createExchangeRatesBuyingCurrencyView;
import static hsbc.test.TestData.createPeriods;
import static hsbc.test.TestData.createSellingCurrencies;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRatePeriodMatchType;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.ExchangeRateHistoryRepository;
import hsbc.model.repository.ExchangeRateRepository;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.test.AbstractTest;
import hsbc.test.TestData.ExchangeRateServiceTestData;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ExchangeRateServiceTest extends AbstractTest {

  private static final PeriodType DEFAULT_PERIOD_TYPE = PeriodType.CALENDAR_MONTH;

  private static final int DEFAULT_NUMBER_HISTORICAL_PERIODS = 6;
  private static final int ZERO = 0;

  private static final ExchangeRatePeriodMatchType EXCHANGE_RATE_PERIOD_MATCH_TYPE =
      ExchangeRatePeriodMatchType.END;

  @Mock
  private ExchangeRateRepository mockExchangeRateRepository;

  @Mock
  private ExchangeRateHistoryRepository mockExchangeRateHistoryRepository;

  @Mock
  private CurrencyService mockCurrencyService;

  @Mock
  private PeriodService mockPeriodService;


  @InjectMocks
  private ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();

  @Before
  public void setup() {
    initMocks(this);
    ((ExchangeRateServiceImpl) exchangeRateService).setDefaultPeriodType(DEFAULT_PERIOD_TYPE);
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setDefaultNumberHistoricalPeriods(DEFAULT_NUMBER_HISTORICAL_PERIODS);
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(EXCHANGE_RATE_PERIOD_MATCH_TYPE);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewPeriodMatchTypeEnd() {
    ExchangeRateServiceTestData testData = createExchangeRateServiceTestData();
    ExchangeRatesBuyingCurrencyView expectedExchangeRatesBuyingCurrencyView =
        createExchangeRatesBuyingCurrencyView();
    testGetExchangeRatesBuyingCurrencyView(testData, expectedExchangeRatesBuyingCurrencyView);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewPeriodMatchTypeStart() {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.START);
    ExchangeRateServiceTestData testData = createExchangeRateServiceTestData();
    ExchangeRatesBuyingCurrencyView expectedExchangeRatesBuyingCurrencyView =
        createExchangeRatesBuyingCurrencyView();
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getDecember(),
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getJanuary(), Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getDecember(), Optional.empty());
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getJanuary(), Optional.empty());
    testGetExchangeRatesBuyingCurrencyView(testData, expectedExchangeRatesBuyingCurrencyView);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewPeriodMatchTypeMiddle() {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.MIDDLE);
    ExchangeRateServiceTestData testData = createExchangeRateServiceTestData();
    ExchangeRatesBuyingCurrencyView expectedExchangeRatesBuyingCurrencyView =
        createExchangeRatesBuyingCurrencyView();
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getDecember(),
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getJanuary(), Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getDecember(), Optional.empty());
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getJanuary(), Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS));
    testGetExchangeRatesBuyingCurrencyView(testData, expectedExchangeRatesBuyingCurrencyView);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewPeriodMatchTypeAverage() {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.AVERAGE);
    ExchangeRateServiceTestData testData = createExchangeRateServiceTestData();
    ExchangeRatesBuyingCurrencyView expectedExchangeRatesBuyingCurrencyView =
        createExchangeRatesBuyingCurrencyView();
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getDecember(),
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_DEC));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getPoundsSterling(),
        testData.getJanuary(),
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_JAN));
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getDecember(), Optional.empty());
    expectedExchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(testData.getHongKongDollars(),
        testData.getJanuary(), Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS));
    testGetExchangeRatesBuyingCurrencyView(testData, expectedExchangeRatesBuyingCurrencyView);
  }


  @Test
  public void testGetExchangeRatesBuyingCurrencyViewOverloadedCurrencyCodes()
      throws ValidationException {
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<String> sellingCurrencyCodes =
        sellingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    List<ExchangeRate> exchangeRates = createExchangeRates();
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency,
        sellingCurrencies)).thenReturn(exchangeRates);
    List<Period> periods = createPeriods(PeriodType.CALENDAR_MONTH);
    exchangeRateService.getExchangeRatesBuyingCurrencyView(buyingCurrencyCode, sellingCurrencyCodes,
        periods);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
    verify(mockExchangeRateRepository, times(1))
        .findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency, sellingCurrencies);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewOverloadedNoPeriods()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<String> sellingCurrencyCodes = new ArrayList<>();
    for (Currency sellingCurrency : sellingCurrencies) {
      sellingCurrencyCodes.add(sellingCurrency.getCode());
    }
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    List<ExchangeRate> exchangeRates = createExchangeRates();
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency,
        sellingCurrencies)).thenReturn(exchangeRates);
    List<Period> periods = createPeriods(PeriodType.CALENDAR_MONTH);
    when(mockPeriodService.getLatestHistoricalPeriods(DEFAULT_PERIOD_TYPE,
        DEFAULT_NUMBER_HISTORICAL_PERIODS)).thenReturn(periods);
    exchangeRateService.getExchangeRatesBuyingCurrencyView(buyingCurrencyCode,
        sellingCurrencyCodes);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
    verify(mockExchangeRateRepository, times(1))
        .findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency, sellingCurrencies);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(DEFAULT_PERIOD_TYPE,
        DEFAULT_NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetExchangeRatesBuyingCurrencyViewZeroHistoricalPeriods()
      throws ServiceException, ValidationException {
    ((ExchangeRateServiceImpl) exchangeRateService).setDefaultNumberHistoricalPeriods(ZERO);
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<String> sellingCurrencyCodes = new ArrayList<>();
    for (Currency sellingCurrency : sellingCurrencies) {
      sellingCurrencyCodes.add(sellingCurrency.getCode());
    }
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    List<ExchangeRate> exchangeRates = createExchangeRates();
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency,
        sellingCurrencies)).thenReturn(exchangeRates);
    List<Period> periods = new ArrayList<>();
    when(mockPeriodService.getLatestHistoricalPeriods(DEFAULT_PERIOD_TYPE, ZERO))
        .thenReturn(periods);
    exchangeRateService.getExchangeRatesBuyingCurrencyView(buyingCurrencyCode,
        sellingCurrencyCodes);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
    verify(mockExchangeRateRepository, times(1))
        .findByBuyingCurrencyAndSellingCurrencyIn(buyingCurrency, sellingCurrencies);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(DEFAULT_PERIOD_TYPE, ZERO);
  }

  @SuppressWarnings("deprecation")
  private void testGetExchangeRatesBuyingCurrencyView(ExchangeRateServiceTestData testData,
      ExchangeRatesBuyingCurrencyView expectedExchangeRatesBuyingCurrencyView) {
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrencyIn(
        testData.getBuyingCurrency(), testData.getSellingCurrencies()))
            .thenReturn(testData.getExchangeRates());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(
        testData.getBuyingCurrency(), testData.getHongKongDollars()))
            .thenReturn(testData.getCurrentExchangeRateHongKongDollars());
    List<Period> periods = testData.getPeriods();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(testData.getBuyingCurrency(),
        testData.getPoundsSterling(), periods.get(0).getStartDateTime(),
        periods.get(periods.size() - 1).getEndDateTime()))
            .thenReturn(testData.getExchangeRateHistoriesPoundsSterling());
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(testData.getBuyingCurrency(),
        testData.getHongKongDollars(), periods.get(0).getStartDateTime(),
        periods.get(periods.size() - 1).getEndDateTime()))
            .thenReturn(testData.getExchangeRateHistoriesHongKongDollars());
    ExchangeRatesBuyingCurrencyView actualExchangeRatesBuyingCurrencyView =
        exchangeRateService.getExchangeRatesBuyingCurrencyView(testData.getBuyingCurrency(),
            testData.getSellingCurrencies(), periods);
    assertThat(actualExchangeRatesBuyingCurrencyView)
        .isEqualToComparingFieldByFieldRecursively(expectedExchangeRatesBuyingCurrencyView);
    verify(mockExchangeRateHistoryRepository, times(1)).findExchangeRateHistories(
        testData.getBuyingCurrency(), testData.getPoundsSterling(),
        periods.get(0).getStartDateTime(), periods.get(periods.size() - 1).getEndDateTime());
    verify(mockExchangeRateHistoryRepository, times(1)).findExchangeRateHistories(
        testData.getBuyingCurrency(), testData.getHongKongDollars(),
        periods.get(0).getStartDateTime(), periods.get(periods.size() - 1).getEndDateTime());
    verify(mockExchangeRateRepository, times(1)).findByBuyingCurrencyAndSellingCurrencyIn(
        testData.getBuyingCurrency(), testData.getSellingCurrencies());
  }

}
