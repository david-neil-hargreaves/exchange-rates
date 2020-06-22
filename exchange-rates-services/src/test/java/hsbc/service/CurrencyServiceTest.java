package hsbc.service;

import static hsbc.service.CurrencyServiceImpl.MESSAGE_INVALID_CONFIGURATION_MULTIPLE_DEFAULT_SUBJECT_CURRENCIES;
import static hsbc.service.CurrencyServiceImpl.MESSAGE_INVALID_CONFIGURATION_NO_DEFAULT_SUBJECT_CURRENCY;
import static hsbc.test.TestData.CURRENCY_CODE_EUROS;
import static hsbc.test.TestData.CURRENCY_CODE_INVALID;
import static hsbc.test.TestData.CURRENCY_ID_EUROS;
import static hsbc.test.TestData.CURRENCY_ID_INVALID;
import static hsbc.test.TestData.createCurrencies;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.getCurrencyCodes;
import static hsbc.test.TestData.getCurrencyIds;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import hsbc.model.Currency;
import hsbc.model.repository.CurrencyRepository;
import hsbc.test.AbstractTest;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.UnsupportedCurrencyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CurrencyServiceTest extends AbstractTest {

  private static final String ATTRIBUTE_CURRENCY = "Currency";
  private static final String ATTRIBUTE_CURRENCIES = "Currencies";

  @Mock
  private CurrencyRepository mockCurrencyRepository;

  @InjectMocks
  private CurrencyService currencyService = new CurrencyServiceImpl();

  @Before
  public void setup() {
    initMocks(this);
  }
  
  @Test
  public void testFindById() throws UnsupportedCurrencyException {
    Currency expectedCurrency = createEuro();
    Optional<Currency> optionalCurrency = Optional.of(expectedCurrency);
    when(mockCurrencyRepository.findById(expectedCurrency.getId())).thenReturn(optionalCurrency);
    Currency actualCurrency = currencyService.findById(expectedCurrency.getId());
    assertEquals(ATTRIBUTE_CURRENCY, expectedCurrency, actualCurrency);
    verify(mockCurrencyRepository, times(1)).findById(expectedCurrency.getId());
  }
  
  @Test
  public void testFindByIdUnsupportedCurrency() throws UnsupportedCurrencyException {
    Long currencyId = CURRENCY_ID_INVALID;
    Optional<Currency> optionalCurrency = Optional.empty();
    when(mockCurrencyRepository.findById(currencyId)).thenReturn(optionalCurrency);
    UnsupportedCurrencyException unsupportedCurrencyException =
        new UnsupportedCurrencyException(currencyId);
    expectedException.expect(unsupportedCurrencyException.getClass());
    expectedException.expectMessage(unsupportedCurrencyException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findById(currencyId);
  }
  
  @Test
  public void testFindByIds() throws UnsupportedCurrencyException {
    List<Currency> expectedCurrencies = createCurrencies();
    for (Currency currency : expectedCurrencies) {
      Optional<Currency> optionalCurrency = Optional.of(currency);
      when(mockCurrencyRepository.findById(currency.getId())).thenReturn(optionalCurrency);
    }
    List<Currency> actualCurrencies = currencyService.findByIds(getCurrencyIds());
    assertEquals(ATTRIBUTE_CURRENCIES, expectedCurrencies, actualCurrencies);
    for (Currency currency : expectedCurrencies) {
      verify(mockCurrencyRepository, times(1)).findById(currency.getId());
    }
  }
  
  @Test
  public void testFindByIdsUnsupportedCurrencyCode() throws UnsupportedCurrencyException {
    List<Currency> currencies = createCurrencies();
    for (Currency currency : currencies) {
      if (currency.getId().equals(CURRENCY_ID_EUROS)) {
        Optional<Currency> optionalCurrency = Optional.empty();
        when(mockCurrencyRepository.findById(currency.getId())).thenReturn(optionalCurrency);
      } else {
        Optional<Currency> optionalCurrency = Optional.of(currency);
        when(mockCurrencyRepository.findById(currency.getId())).thenReturn(optionalCurrency);
      }
    }
    UnsupportedCurrencyException unsupportedCurrencyException =
        new UnsupportedCurrencyException(CURRENCY_ID_EUROS);
    expectedException.expect(unsupportedCurrencyException.getClass());
    expectedException.expectMessage(unsupportedCurrencyException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findByIds(getCurrencyIds());
  }

  @Test
  public void testFindByCode() throws UnsupportedCurrencyException {
    Currency expectedCurrency = createEuro();
    Optional<Currency> optionalCurrency = Optional.of(expectedCurrency);
    when(mockCurrencyRepository.findByCode(expectedCurrency.getCode())).thenReturn(optionalCurrency);
    Currency actualCurrency = currencyService.findByCode(expectedCurrency.getCode());
    assertEquals(ATTRIBUTE_CURRENCY, expectedCurrency, actualCurrency);
    verify(mockCurrencyRepository, times(1)).findByCode(expectedCurrency.getCode());
  }

  @Test
  public void testFindByCodeUnsupportedCurrency() throws UnsupportedCurrencyException {
    String currencyCode = CURRENCY_CODE_INVALID;
    Optional<Currency> optionalCurrency = Optional.empty();
    when(mockCurrencyRepository.findByCode(currencyCode)).thenReturn(optionalCurrency);
    UnsupportedCurrencyException unsupportedCurrencyException =
        new UnsupportedCurrencyException(currencyCode);
    expectedException.expect(unsupportedCurrencyException.getClass());
    expectedException.expectMessage(unsupportedCurrencyException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findByCode(currencyCode);
  }

  @Test
  public void testFindByCodes() throws UnsupportedCurrencyException {
    List<Currency> expectedCurrencies = createCurrencies();
    for (Currency currency : expectedCurrencies) {
      Optional<Currency> optionalCurrency = Optional.of(currency);
      when(mockCurrencyRepository.findByCode(currency.getCode())).thenReturn(optionalCurrency);
    }
    List<Currency> actualCurrencies = currencyService.findByCodes(getCurrencyCodes());
    assertEquals(ATTRIBUTE_CURRENCIES, expectedCurrencies, actualCurrencies);
    for (Currency currency : expectedCurrencies) {
      verify(mockCurrencyRepository, times(1)).findByCode(currency.getCode());
    }
  }

  @Test
  public void testFindByCodesUnsupportedCurrencyCode() throws UnsupportedCurrencyException {
    List<Currency> currencies = createCurrencies();
    for (Currency currency : currencies) {
      if (currency.getCode().equals(CURRENCY_CODE_EUROS)) {
        Optional<Currency> optionalCurrency = Optional.empty();
        when(mockCurrencyRepository.findByCode(currency.getCode())).thenReturn(optionalCurrency);
      } else {
        Optional<Currency> optionalCurrency = Optional.of(currency);
        when(mockCurrencyRepository.findByCode(currency.getCode())).thenReturn(optionalCurrency);
      }
    }
    UnsupportedCurrencyException unsupportedCurrencyException =
        new UnsupportedCurrencyException(CURRENCY_CODE_EUROS);
    expectedException.expect(unsupportedCurrencyException.getClass());
    expectedException.expectMessage(unsupportedCurrencyException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findByCodes(getCurrencyCodes());
  }
  
  @Test
  public void testFindDefaultSubjectCurrency() throws ServiceException {
    List<Currency> currencies = new ArrayList<>();
    Currency expectedCurrency = createEuro();
    currencies.add(expectedCurrency);
    when(mockCurrencyRepository.findByDefaultSubjectCurrencyTrue()).thenReturn(currencies);
    Currency actualCurrency = currencyService.findDefaultSubjectCurrency();
    assertEquals(ATTRIBUTE_CURRENCY, expectedCurrency, actualCurrency);
    verify(mockCurrencyRepository, times(1)).findByDefaultSubjectCurrencyTrue();
  }
  
  @Test
  public void testFindDefaultSubjectCurrencyNoneSetUp() throws ServiceException {
    List<Currency> currencies = new ArrayList<>();
    when(mockCurrencyRepository.findByDefaultSubjectCurrencyTrue()).thenReturn(currencies);
    ServiceException serviceException =
        new ServiceException(MESSAGE_INVALID_CONFIGURATION_NO_DEFAULT_SUBJECT_CURRENCY);
    expectedException.expect(serviceException.getClass());
    expectedException.expectMessage(serviceException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findDefaultSubjectCurrency();
  }
  
  @Test
  public void testFindDefaultSubjectCurrencyMultipleSetUp() throws ServiceException {
    List<Currency> currencies = createCurrencies();
    when(mockCurrencyRepository.findByDefaultSubjectCurrencyTrue()).thenReturn(currencies);
    String message = String.format(MESSAGE_INVALID_CONFIGURATION_MULTIPLE_DEFAULT_SUBJECT_CURRENCIES, currencies.size());
    ServiceException serviceException =
        new ServiceException(message);
    expectedException.expect(serviceException.getClass());
    expectedException.expectMessage(serviceException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findDefaultSubjectCurrency();
  }
  
  @Test
  public void testFindDefaultOtherCurrencies() {
    List<Currency> expectedCurrencies = createCurrencies();
    when(mockCurrencyRepository.findByDefaultOtherCurrencyTrue()).thenReturn(expectedCurrencies);
    List<Currency> actualCurrencies = currencyService.findDefaultOtherCurrencies();
    assertEquals(ATTRIBUTE_CURRENCIES, expectedCurrencies, actualCurrencies);
    verify(mockCurrencyRepository, times(1)).findByDefaultOtherCurrencyTrue();
  }
  
  @Test
  public void testFindAll() {
    List<Currency> expectedCurrencies = createCurrencies();
    when(mockCurrencyRepository.findAll()).thenReturn(expectedCurrencies);
    List<Currency> actualCurrencies = currencyService.findAll();
    assertEquals(ATTRIBUTE_CURRENCIES, expectedCurrencies, actualCurrencies);
    verify(mockCurrencyRepository, times(1)).findAll();
  }

}
