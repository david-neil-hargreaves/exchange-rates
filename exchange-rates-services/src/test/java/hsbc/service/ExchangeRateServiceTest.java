package hsbc.service;

import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createExchangeRates;
import static hsbc.test.TestData.createPeriods;
import static hsbc.test.TestData.createSellingCurrencies;
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
import hsbc.test.AbstractTest;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
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

}
