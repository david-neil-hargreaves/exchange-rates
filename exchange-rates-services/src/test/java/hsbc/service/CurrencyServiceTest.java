package hsbc.service;

import static hsbc.test.TestData.CURRENCY_CODE_EUROS;
import static hsbc.test.TestData.CURRENCY_CODE_INVALID;
import static hsbc.test.TestData.createCurrencies;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.getCurrencyCodes;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hsbc.model.Currency;
import hsbc.model.repository.CurrencyRepository;
import hsbc.test.AbstractTest;
import hsbc.util.exception.UnsupportedCurrencyCodeException;
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
  public void testFindByCode() throws UnsupportedCurrencyCodeException {
    Currency currency = createEuro();
    Optional<Currency> optionalCurrency = Optional.of(currency);
    when(mockCurrencyRepository.findByCode(currency.getCode())).thenReturn(optionalCurrency);
    Currency actualCurrency = currencyService.findByCode(currency.getCode());
    assertEquals(ATTRIBUTE_CURRENCY, currency, actualCurrency);
    verify(mockCurrencyRepository, times(1)).findByCode(currency.getCode());
  }

  @Test
  public void testFindByCodeUnsupportedCurrencyCode() throws UnsupportedCurrencyCodeException {
    String currencyCode = CURRENCY_CODE_INVALID;
    Optional<Currency> optionalCurrency = Optional.empty();
    when(mockCurrencyRepository.findByCode(currencyCode)).thenReturn(optionalCurrency);
    UnsupportedCurrencyCodeException unsupportedCurrencyCodeException =
        new UnsupportedCurrencyCodeException(currencyCode);
    expectedException.expect(unsupportedCurrencyCodeException.getClass());
    expectedException.expectMessage(unsupportedCurrencyCodeException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findByCode(currencyCode);
  }

  @Test
  public void testFindByCodes() throws UnsupportedCurrencyCodeException {
    List<Currency> currencies = createCurrencies();
    for (Currency currency : currencies) {
      Optional<Currency> optionalCurrency = Optional.of(currency);
      when(mockCurrencyRepository.findByCode(currency.getCode())).thenReturn(optionalCurrency);
    }
    List<Currency> actualCurrencies = currencyService.findByCodes(getCurrencyCodes());
    assertEquals(ATTRIBUTE_CURRENCIES, currencies, actualCurrencies);
    for (Currency currency : currencies) {
      verify(mockCurrencyRepository, times(1)).findByCode(currency.getCode());
    }
  }

  @Test
  public void testFindByCodesUnsupportedCurrencyCode() throws UnsupportedCurrencyCodeException {
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
    UnsupportedCurrencyCodeException unsupportedCurrencyCodeException =
        new UnsupportedCurrencyCodeException(CURRENCY_CODE_EUROS);
    expectedException.expect(unsupportedCurrencyCodeException.getClass());
    expectedException.expectMessage(unsupportedCurrencyCodeException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    currencyService.findByCodes(getCurrencyCodes());
  }

}
