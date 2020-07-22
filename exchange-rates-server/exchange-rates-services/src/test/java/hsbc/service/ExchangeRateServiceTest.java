package hsbc.service;

import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_AVERAGE_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_HIGH_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_LOW_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_START_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_HIGH_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_HIGH_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_LOW_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_LOW_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_SERBIAN_DINARS;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_HONG_KONG_DOLLARS_SELL_EUROS_LOW_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_END_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_HIGH_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_HIGH_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_LOW_DEC;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_LOW_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_REST_JAN;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_SERBIAN_DINARS_SELL_EUROS;
import static hsbc.test.TestData.createComparisonCurrencies;
import static hsbc.test.TestData.createDecember;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createEuroHongKongDollars;
import static hsbc.test.TestData.createEuroHongKongDollarsHistory;
import static hsbc.test.TestData.createEuroPoundsSterling;
import static hsbc.test.TestData.createEuroPoundsSterlingHistory;
import static hsbc.test.TestData.createEuroSerbianDinars;
import static hsbc.test.TestData.createEuroUsDollars;
import static hsbc.test.TestData.createHongKongDollars;
import static hsbc.test.TestData.createHongKongDollarsEuro;
import static hsbc.test.TestData.createHongKongDollarsEuroHistory;
import static hsbc.test.TestData.createJanuary;
import static hsbc.test.TestData.createLira;
import static hsbc.test.TestData.createPeriods;
import static hsbc.test.TestData.createPoundsSterling;
import static hsbc.test.TestData.createPoundsSterlingEuro;
import static hsbc.test.TestData.createPoundsSterlingEuroHistory;
import static hsbc.test.TestData.createSerbianDinars;
import static hsbc.test.TestData.createSerbianDinarsEuro;
import static hsbc.test.TestData.createUsDollars;
import static hsbc.test.TestData.createUsDollarsEuro;
import static hsbc.util.exception.InvalidConfigurationException.MESSAGE_INVALID_CONFIGURATION_ONLY_ONE_CURRENCY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRateHistory;
import hsbc.model.ExchangeRatePeriodMatchType;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.ExchangeRateHistoryRepository;
import hsbc.model.repository.ExchangeRateRepository;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.HistoricalExchangeRates;
import hsbc.service.validation.ExchangeRateServiceValidator;
import hsbc.test.AbstractTest;
import hsbc.util.exception.InvalidConfigurationException;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ExchangeRateServiceTest extends AbstractTest {

  private static final PeriodType PERIOD_TYPE = PeriodType.CALENDAR_MONTH;
  private static final int NUMBER_HISTORICAL_PERIODS = 6;
  private static final ExchangeRatePeriodMatchType EXCHANGE_RATE_PERIOD_MATCH_TYPE =
      ExchangeRatePeriodMatchType.END;
  private static final int PRECISION = 6;
  private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
  private static final int SCALE = 6;

  private static final String ATTRIBUTE_CURRENT_EXCHANGE_RATES = "Current exchange rates";
  private static final String ATTRIBUTE_HISTORICAL_EXCHANGE_RATES = "Historical exchange rates";

  @Mock
  private ExchangeRateRepository mockExchangeRateRepository;

  @Mock
  private ExchangeRateHistoryRepository mockExchangeRateHistoryRepository;

  @Mock
  private CurrencyService mockCurrencyService;

  @Mock
  private PeriodService mockPeriodService;

  @Mock
  private ExchangeRateServiceValidator mockExchangeRateServiceValidator;

  @InjectMocks
  private ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();

  @Before
  public void setUp() {
    initMocks(this);
    ((ExchangeRateServiceImpl) exchangeRateService).setDefaultPeriodType(PERIOD_TYPE);
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setDefaultNumberHistoricalPeriods(NUMBER_HISTORICAL_PERIODS);
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(EXCHANGE_RATE_PERIOD_MATCH_TYPE);
    ((ExchangeRateServiceImpl) exchangeRateService).setPrecision(PRECISION);
    ((ExchangeRateServiceImpl) exchangeRateService).setRoundingMode(ROUNDING_MODE);
    ((ExchangeRateServiceImpl) exchangeRateService).setScale(SCALE);
    ((ExchangeRateServiceImpl) exchangeRateService).setUp();
  }

  @Test
  public void testGetCurrentBuyingExchangeRates() throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies);
    verifyResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesCurrencyIds()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    Long buyingCurrencyId = buyingCurrency.getId();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Long> sellingCurrencyIds =
        sellingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    when(mockCurrencyService.findById(buyingCurrencyId)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByIds(sellingCurrencyIds)).thenReturn(sellingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    verifyResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findById(buyingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(sellingCurrencyIds);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesCurrencyCodes()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<String> sellingCurrencyCodes =
        sellingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    verifyResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesNoParameters()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(buyingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(sellingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates();
    verifyResultGetCurrentBuyingExchangeRates(buyingCurrency, sellingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findDefaultSubjectCurrency();
    verify(mockCurrencyService, times(1)).findDefaultComparisonCurrencies();
  }

  @Test
  public void testGetCurrentSellingExchangeRates() throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies);
    verifyResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesCurrencyIds()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    Long sellingCurrencyId = sellingCurrency.getId();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Long> buyingCurrencyIds =
        buyingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    when(mockCurrencyService.findById(sellingCurrencyId)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByIds(buyingCurrencyIds)).thenReturn(buyingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    verifyResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findById(sellingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(buyingCurrencyIds);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesCurrencyCodes()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    String sellingCurrencyCode = sellingCurrency.getCode();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<String> buyingCurrencyCodes =
        buyingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    when(mockCurrencyService.findByCode(sellingCurrencyCode)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByCodes(buyingCurrencyCodes)).thenReturn(buyingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates = exchangeRateService
        .getCurrentSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    verifyResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(sellingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(buyingCurrencyCodes);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesNoParameters()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(sellingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(buyingCurrencies);
    CurrentExchangeRates expectedCurrentExchangeRates =
        getExpectedResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies);
    CurrentExchangeRates actualCurrentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates();
    verifyResultGetCurrentSellingExchangeRates(sellingCurrency, buyingCurrencies,
        expectedCurrentExchangeRates, actualCurrentExchangeRates);
    verify(mockCurrencyService, times(1)).findDefaultSubjectCurrency();
    verify(mockCurrencyService, times(1)).findDefaultComparisonCurrencies();
  }

  @Test
  public void testGetCurrentSellingExchangeRatesNoParametersNoDefaultComparisonCurrencies()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = new ArrayList<>();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(sellingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(buyingCurrencies);
    InvalidConfigurationException invalidConfigurationException =
        new InvalidConfigurationException(MESSAGE_INVALID_CONFIGURATION_ONLY_ONE_CURRENCY);
    expectedException.expect(invalidConfigurationException.getClass());
    expectedException.expectMessage(invalidConfigurationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateService.getCurrentSellingExchangeRates();
  }

  @Test
  public void testGetCurrentSellingExchangeRatesComparisonCurrenciesIncludesSubjectCurrency()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = new ArrayList<>();
    buyingCurrencies.add(sellingCurrency);
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(sellingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(buyingCurrencies);
    InvalidConfigurationException invalidConfigurationException =
        new InvalidConfigurationException(MESSAGE_INVALID_CONFIGURATION_ONLY_ONE_CURRENCY);
    expectedException.expect(invalidConfigurationException.getClass());
    expectedException.expectMessage(invalidConfigurationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateService.getCurrentSellingExchangeRates();
  }

  @Test
  public void testGetHistoricalBuyingExchangeRates() throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesZeroPeriods()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = new ArrayList<>();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        new HistoricalExchangeRates(buyingCurrency, sellingCurrencies, periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates,
        actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyIds()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    Long buyingCurrencyId = buyingCurrency.getId();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Long> sellingCurrencyIds =
        sellingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findById(buyingCurrencyId)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByIds(sellingCurrencyIds)).thenReturn(sellingCurrencies);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findById(buyingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(sellingCurrencyIds);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyCodes()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<String> sellingCurrencyCodes =
        sellingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyIdsNoPeriods()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    Long buyingCurrencyId = buyingCurrency.getId();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Long> sellingCurrencyIds =
        sellingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findById(buyingCurrencyId)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByIds(sellingCurrencyIds)).thenReturn(sellingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates =
        exchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findById(buyingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(sellingCurrencyIds);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyCodesNoPeriods()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    String buyingCurrencyCode = buyingCurrency.getCode();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<String> sellingCurrencyCodes =
        sellingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findByCode(buyingCurrencyCode)).thenReturn(buyingCurrency);
    when(mockCurrencyService.findByCodes(sellingCurrencyCodes)).thenReturn(sellingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(buyingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(sellingCurrencyCodes);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesNoParameters()
      throws ValidationException, ServiceException {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(buyingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(sellingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates =
        exchangeRateService.getHistoricalBuyingExchangeRates();
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findDefaultSubjectCurrency();
    verify(mockCurrencyService, times(1)).findDefaultComparisonCurrencies();
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesExchangeRatePeriodMatchTypeStart()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.START);
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    Currency hongKongDollars = createHongKongDollars();
    Currency poundsSterling = createPoundsSterling();
    Currency serbianDinars = createSerbianDinars();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_START_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, january, Optional.empty());
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesExchangeRatePeriodMatchTypeMiddle()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.MIDDLE);
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesExchangeRatePeriodMatchTypeAverage()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.AVERAGE);
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Currency hongKongDollars = createHongKongDollars();
    Currency serbianDinars = createSerbianDinars();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_AVERAGE_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_SERBIAN_DINARS));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesExchangeRatePeriodMatchTypeHigh()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.HIGH);
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Currency hongKongDollars = createHongKongDollars();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_HIGH_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_HIGH_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_HIGH_JAN));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesExchangeRatePeriodMatchTypeLow()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.LOW);
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Currency hongKongDollars = createHongKongDollars();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_LOW_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_LOW_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS_LOW_JAN));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods);
    verifyResultGetHistoricalBuyingExchangeRates(buyingCurrency, sellingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalSellingExchangeRates() throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesZeroPeriods()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Period> periods = new ArrayList<>();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        new HistoricalExchangeRates(sellingCurrency, buyingCurrencies, periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods);
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates,
        actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyIds()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    Long sellingCurrencyId = sellingCurrency.getId();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Long> buyingCurrencyIds =
        buyingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findById(sellingCurrencyId)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByIds(buyingCurrencyIds)).thenReturn(buyingCurrencies);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds, periods);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findById(sellingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(buyingCurrencyIds);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyCodes()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    String sellingCurrencyCode = sellingCurrency.getCode();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<String> buyingCurrencyCodes =
        buyingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findByCode(sellingCurrencyCode)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByCodes(buyingCurrencyCodes)).thenReturn(buyingCurrencies);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes, periods);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(sellingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(buyingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyIdsNoPeriods()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    Long sellingCurrencyId = sellingCurrency.getId();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Long> buyingCurrencyIds =
        buyingCurrencies.stream().map(Currency::getId).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findById(sellingCurrencyId)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByIds(buyingCurrencyIds)).thenReturn(buyingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates =
        exchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findById(sellingCurrencyId);
    verify(mockCurrencyService, times(1)).findByIds(buyingCurrencyIds);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyCodesNoPeriods()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    String sellingCurrencyCode = sellingCurrency.getCode();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<String> buyingCurrencyCodes =
        buyingCurrencies.stream().map(Currency::getCode).collect(Collectors.toList());
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findByCode(sellingCurrencyCode)).thenReturn(sellingCurrency);
    when(mockCurrencyService.findByCodes(buyingCurrencyCodes)).thenReturn(buyingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findByCode(sellingCurrencyCode);
    verify(mockCurrencyService, times(1)).findByCodes(buyingCurrencyCodes);
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesNoParameters()
      throws ValidationException, ServiceException {
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(sellingCurrency);
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(buyingCurrencies);
    when(mockPeriodService.getLatestHistoricalPeriods(PERIOD_TYPE, NUMBER_HISTORICAL_PERIODS))
        .thenReturn(periods);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    HistoricalExchangeRates actualHistoricalExchangeRates =
        exchangeRateService.getHistoricalSellingExchangeRates();
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
    verify(mockCurrencyService, times(1)).findDefaultSubjectCurrency();
    verify(mockCurrencyService, times(1)).findDefaultComparisonCurrencies();
    verify(mockPeriodService, times(1)).getLatestHistoricalPeriods(PERIOD_TYPE,
        NUMBER_HISTORICAL_PERIODS);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesExchangeRatePeriodMatchTypeHigh()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.HIGH);
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_HIGH_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_HIGH_JAN));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesExchangeRatePeriodMatchTypeLow()
      throws ValidationException, ServiceException {
    ((ExchangeRateServiceImpl) exchangeRateService)
        .setExchangeRatePeriodMatchType(ExchangeRatePeriodMatchType.LOW);
    Currency sellingCurrency = createEuro();
    List<Currency> buyingCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        getExpectedResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies,
            periods);
    Currency poundsSterling = createPoundsSterling();
    Currency hongKongDollars = createHongKongDollars();
    Period december = createDecember();
    Period january = createJanuary();
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_LOW_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_LOW_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_HONG_KONG_DOLLARS_SELL_EUROS_LOW_JAN));
    HistoricalExchangeRates actualHistoricalExchangeRates = exchangeRateService
        .getHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods);
    verifyResultGetHistoricalSellingExchangeRates(sellingCurrency, buyingCurrencies, periods,
        expectedHistoricalExchangeRates, actualHistoricalExchangeRates);
  }

  private CurrentExchangeRates getExpectedResultGetCurrentBuyingExchangeRates(
      Currency buyingCurrency, List<Currency> sellingCurrencies)
      throws ValidationException, ServiceException {
    Currency hongKongDollars = createHongKongDollars();
    Currency usDollars = createUsDollars();
    Currency poundsSterling = createPoundsSterling();
    Currency serbianDinars = createSerbianDinars();
    Optional<ExchangeRate> hongKongDollarsExchangeRate = Optional.of(createEuroHongKongDollars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        hongKongDollars)).thenReturn(hongKongDollarsExchangeRate);
    Optional<ExchangeRate> usDollarsExchangeRate = Optional.of(createEuroUsDollars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        usDollars)).thenReturn(usDollarsExchangeRate);
    Optional<ExchangeRate> poundsSterlingExchangeRate = Optional.of(createEuroPoundsSterling());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        poundsSterling)).thenReturn(poundsSterlingExchangeRate);
    Optional<ExchangeRate> serbianDinarsExchangeRate = Optional.of(createEuroSerbianDinars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        serbianDinars)).thenReturn(serbianDinarsExchangeRate);
    CurrentExchangeRates expectedCurrentExchangeRates =
        new CurrentExchangeRates(buyingCurrency, sellingCurrencies);
    expectedCurrentExchangeRates.setExchangeRate(hongKongDollars,
        Optional.of(hongKongDollarsExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(usDollars,
        Optional.of(usDollarsExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(poundsSterling,
        Optional.of(poundsSterlingExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(serbianDinars,
        Optional.of(serbianDinarsExchangeRate.get().getRate()));
    Currency lira = createLira();
    expectedCurrentExchangeRates.setExchangeRate(lira, Optional.empty());
    return expectedCurrentExchangeRates;
  }

  private void verifyResultGetCurrentBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies, CurrentExchangeRates expectedCurrentExchangeRates,
      CurrentExchangeRates actualCurrentExchangeRates) {
    assertEquals(ATTRIBUTE_CURRENT_EXCHANGE_RATES, expectedCurrentExchangeRates.toString(),
        actualCurrentExchangeRates.toString());
    assertEquals(ATTRIBUTE_CURRENT_EXCHANGE_RATES, expectedCurrentExchangeRates,
        actualCurrentExchangeRates);
    for (Currency sellingCurrency : sellingCurrencies) {
      verify(mockExchangeRateRepository, times(1))
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    }
  }

  private CurrentExchangeRates getExpectedResultGetCurrentSellingExchangeRates(
      Currency sellingCurrency, List<Currency> buyingCurrencies)
      throws ValidationException, ServiceException {
    Currency hongKongDollars = createHongKongDollars();
    Currency usDollars = createUsDollars();
    Currency poundsSterling = createPoundsSterling();
    Currency serbianDinars = createSerbianDinars();
    Optional<ExchangeRate> hongKongDollarsExchangeRate = Optional.of(createHongKongDollarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(hongKongDollars,
        sellingCurrency)).thenReturn(hongKongDollarsExchangeRate);
    Optional<ExchangeRate> usDollarsExchangeRate = Optional.of(createUsDollarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(usDollars,
        sellingCurrency)).thenReturn(usDollarsExchangeRate);
    Optional<ExchangeRate> poundsSterlingExchangeRate = Optional.of(createPoundsSterlingEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(poundsSterling,
        sellingCurrency)).thenReturn(poundsSterlingExchangeRate);
    Optional<ExchangeRate> serbianDinarsExchangeRate = Optional.of(createSerbianDinarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(serbianDinars,
        sellingCurrency)).thenReturn(serbianDinarsExchangeRate);
    CurrentExchangeRates expectedCurrentExchangeRates =
        new CurrentExchangeRates(sellingCurrency, buyingCurrencies);
    expectedCurrentExchangeRates.setExchangeRate(hongKongDollars,
        Optional.of(hongKongDollarsExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(usDollars,
        Optional.of(usDollarsExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(poundsSterling,
        Optional.of(poundsSterlingExchangeRate.get().getRate()));
    expectedCurrentExchangeRates.setExchangeRate(serbianDinars,
        Optional.of(serbianDinarsExchangeRate.get().getRate()));
    Currency lira = createLira();
    expectedCurrentExchangeRates.setExchangeRate(lira, Optional.empty());
    return expectedCurrentExchangeRates;
  }

  private void verifyResultGetCurrentSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies, CurrentExchangeRates expectedCurrentExchangeRates,
      CurrentExchangeRates actualCurrentExchangeRates) {
    assertEquals(ATTRIBUTE_CURRENT_EXCHANGE_RATES, expectedCurrentExchangeRates,
        actualCurrentExchangeRates);
    for (Currency buyingCurrency : buyingCurrencies) {
      verify(mockExchangeRateRepository, times(1))
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    }
  }

  private HistoricalExchangeRates getExpectedResultGetHistoricalBuyingExchangeRates(
      Currency buyingCurrency, List<Currency> sellingCurrencies, List<Period> periods)
      throws ValidationException, ServiceException {
    Currency hongKongDollars = createHongKongDollars();
    Currency usDollars = createUsDollars();
    Currency poundsSterling = createPoundsSterling();
    Currency serbianDinars = createSerbianDinars();
    Period december = createDecember();
    Period january = createJanuary();
    Date fromRangeDateTime = december.getStartDateTime();
    Date toRangeDateTime = january.getEndDateTime();
    List<ExchangeRateHistory> exchangeRateHistoriesHongKongDollars =
        createEuroHongKongDollarsHistory();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency,
        hongKongDollars, fromRangeDateTime, toRangeDateTime))
            .thenReturn(exchangeRateHistoriesHongKongDollars);
    List<ExchangeRateHistory> exchangeRateHistoriesUsDollars = new ArrayList<>();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, usDollars,
        fromRangeDateTime, toRangeDateTime)).thenReturn(exchangeRateHistoriesUsDollars);
    List<ExchangeRateHistory> exchangeRateHistoriesPoundsSterling =
        createEuroPoundsSterlingHistory();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, poundsSterling,
        fromRangeDateTime, toRangeDateTime)).thenReturn(exchangeRateHistoriesPoundsSterling);
    List<ExchangeRateHistory> exchangeRateHistoriesSerbianDinars = new ArrayList<>();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(buyingCurrency, serbianDinars,
        fromRangeDateTime, toRangeDateTime)).thenReturn(exchangeRateHistoriesSerbianDinars);
    Optional<ExchangeRate> hongKongDollarsExchangeRate = Optional.of(createEuroHongKongDollars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        hongKongDollars)).thenReturn(hongKongDollarsExchangeRate);
    Optional<ExchangeRate> usDollarsExchangeRate = Optional.of(createEuroUsDollars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        usDollars)).thenReturn(usDollarsExchangeRate);
    Optional<ExchangeRate> poundsSterlingExchangeRate = Optional.of(createEuroPoundsSterling());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        poundsSterling)).thenReturn(poundsSterlingExchangeRate);
    Optional<ExchangeRate> serbianDinarsExchangeRate = Optional.of(createEuroSerbianDinars());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(buyingCurrency,
        serbianDinars)).thenReturn(serbianDinarsExchangeRate);
    HistoricalExchangeRates expectedHistoricalExchangeRates =
        new HistoricalExchangeRates(buyingCurrency, sellingCurrencies, periods);
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(hongKongDollarsExchangeRate.get().getRate()));
    expectedHistoricalExchangeRates.setExchangeRate(usDollars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(usDollars, january, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN));
    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_SERBIAN_DINARS));
    Currency lira = createLira();
    expectedHistoricalExchangeRates.setExchangeRate(lira, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(lira, january, Optional.empty());
    return expectedHistoricalExchangeRates;
  }

  private void verifyResultGetHistoricalBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods,
      HistoricalExchangeRates expectedHistoricalExchangeRates,
      HistoricalExchangeRates actualHistoricalExchangeRates) {
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates.toString(),
        actualHistoricalExchangeRates.toString());
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates,
        actualHistoricalExchangeRates);

    Date fromRangeDateTime = periods.get(0).getStartDateTime();
    Date toRangeDateTime = periods.get(periods.size() - 1).getEndDateTime();
    for (Currency sellingCurrency : sellingCurrencies) {
      verify(mockExchangeRateHistoryRepository, times(1)).findExchangeRateHistories(buyingCurrency,
          sellingCurrency, fromRangeDateTime, toRangeDateTime);
      verify(mockExchangeRateRepository, times(1))
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    }
  }

  private HistoricalExchangeRates getExpectedResultGetHistoricalSellingExchangeRates(
      Currency sellingCurrency, List<Currency> buyingCurrencies, List<Period> periods)
      throws ValidationException, ServiceException {
    Currency hongKongDollars = createHongKongDollars();
    Currency usDollars = createUsDollars();
    Currency poundsSterling = createPoundsSterling();
    Currency serbianDinars = createSerbianDinars();
    Period december = createDecember();
    Period january = createJanuary();
    Date fromRangeDateTime = december.getStartDateTime();
    Date toRangeDateTime = january.getEndDateTime();
    List<ExchangeRateHistory> exchangeRateHistoriesHongKongDollars =
        createHongKongDollarsEuroHistory();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(hongKongDollars,
        sellingCurrency, fromRangeDateTime, toRangeDateTime))
            .thenReturn(exchangeRateHistoriesHongKongDollars);
    List<ExchangeRateHistory> exchangeRateHistoriesUsDollars = new ArrayList<>();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(usDollars, sellingCurrency,
        fromRangeDateTime, toRangeDateTime)).thenReturn(exchangeRateHistoriesUsDollars);
    List<ExchangeRateHistory> exchangeRateHistoriesPoundsSterling =
        createPoundsSterlingEuroHistory();
    when(mockExchangeRateHistoryRepository.findExchangeRateHistories(poundsSterling,
        sellingCurrency, fromRangeDateTime, toRangeDateTime))
            .thenReturn(exchangeRateHistoriesPoundsSterling);
    Optional<ExchangeRate> hongKongDollarsExchangeRate = Optional.of(createHongKongDollarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(hongKongDollars,
        sellingCurrency)).thenReturn(hongKongDollarsExchangeRate);
    Optional<ExchangeRate> usDollarsExchangeRate = Optional.of(createUsDollarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(usDollars,
        sellingCurrency)).thenReturn(usDollarsExchangeRate);

    Optional<ExchangeRate> poundsSterlingExchangeRate = Optional.of(createPoundsSterlingEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(poundsSterling,
        sellingCurrency)).thenReturn(poundsSterlingExchangeRate);

    Optional<ExchangeRate> serbianDinarsExchangeRate = Optional.of(createSerbianDinarsEuro());
    when(mockExchangeRateRepository.findByBuyingCurrencyAndSellingCurrency(serbianDinars,
        sellingCurrency)).thenReturn(serbianDinarsExchangeRate);

    HistoricalExchangeRates expectedHistoricalExchangeRates =
        new HistoricalExchangeRates(sellingCurrency, buyingCurrencies, periods);
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(hongKongDollars, january,
        Optional.of(hongKongDollarsExchangeRate.get().getRate()));
    expectedHistoricalExchangeRates.setExchangeRate(usDollars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(usDollars, january, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_END_DEC));
    expectedHistoricalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_REST_JAN));

    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(serbianDinars, january,
        Optional.of(EXCHANGE_RATE_BUY_SERBIAN_DINARS_SELL_EUROS));

    Currency lira = createLira();
    expectedHistoricalExchangeRates.setExchangeRate(lira, december, Optional.empty());
    expectedHistoricalExchangeRates.setExchangeRate(lira, january, Optional.empty());
    return expectedHistoricalExchangeRates;
  }

  private void verifyResultGetHistoricalSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies, List<Period> periods,
      HistoricalExchangeRates expectedHistoricalExchangeRates,
      HistoricalExchangeRates actualHistoricalExchangeRates) {
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates,
        actualHistoricalExchangeRates);
    Date fromRangeDateTime = periods.get(0).getStartDateTime();
    Date toRangeDateTime = periods.get(periods.size() - 1).getEndDateTime();
    for (Currency buyingCurrency : buyingCurrencies) {
      verify(mockExchangeRateHistoryRepository, times(1)).findExchangeRateHistories(buyingCurrency,
          sellingCurrency, fromRangeDateTime, toRangeDateTime);
      verify(mockExchangeRateRepository, times(1))
          .findByBuyingCurrencyAndSellingCurrency(buyingCurrency, sellingCurrency);
    }
  }

}
