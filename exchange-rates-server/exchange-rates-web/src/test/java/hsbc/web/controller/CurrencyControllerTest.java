package hsbc.web.controller;

import static hsbc.test.TestData.createCurrencies;
import static hsbc.test.TestData.createEuro;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import hsbc.model.Currency;
import hsbc.service.CurrencyService;
import hsbc.test.AbstractTest;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class CurrencyControllerTest extends AbstractTest {

  private static final String ATTRIBUTE_RESPONSE = "Response";

  @Mock
  private CurrencyService mockCurrencyService;

  @InjectMocks
  private CurrencyController currencyController = new CurrencyController();

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void testGetCurrencies() throws ServiceException, ValidationException {
    List<Currency> currencies = createCurrencies();
    when(mockCurrencyService.findAll()).thenReturn(currencies);
    ResponseEntity<List<Currency>> expectedResponse = new ResponseEntity<>(currencies, OK);
    ResponseEntity<List<Currency>> actualResponse = currencyController.getCurrencies();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockCurrencyService, times(1)).findAll();
  }

  @Test
  public void testFindDefaultSubjectCurrency() throws ServiceException, ValidationException {
    Currency currency = createEuro();
    when(mockCurrencyService.findDefaultSubjectCurrency()).thenReturn(currency);
    ResponseEntity<Currency> expectedResponse = new ResponseEntity<>(currency, OK);
    ResponseEntity<Currency> actualResponse = currencyController.findDefaultSubjectCurrency();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockCurrencyService, times(1)).findDefaultSubjectCurrency();
  }

  @Test
  public void testFindDefaultComparisonCurrencies() throws ServiceException, ValidationException {
    List<Currency> currencies = createCurrencies();
    when(mockCurrencyService.findDefaultComparisonCurrencies()).thenReturn(currencies);
    ResponseEntity<List<Currency>> expectedResponse = new ResponseEntity<>(currencies, OK);
    ResponseEntity<List<Currency>> actualResponse =
        currencyController.findDefaultComparisonCurrencies();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockCurrencyService, times(1)).findDefaultComparisonCurrencies();
  }

}
