package hsbc.web.controller;

import static hsbc.test.TestData.EXCEPTION_MESSAGE;
import static hsbc.test.TestData.createBuyingCurrencyCode;
import static hsbc.test.TestData.createBuyingCurrencyCodeLowerCase;
import static hsbc.test.TestData.createExchangeRatesBuyingCurrencyView;
import static hsbc.test.TestData.createSellingCurrencyCodes;
import static hsbc.test.TestData.createSellingCurrencyCodesLowerCase;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.service.ExchangeRateService;
import hsbc.test.AbstractTest;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.List;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class ExchangeRatesControllerTest extends AbstractTest {

  private static final String ATTRIBUTE_RESPONSE = "Response";

  @Mock
  private ExchangeRateService mockExchangeRateService;

  @InjectMocks
  private ExchangeRatesController exchangeRatesController = new ExchangeRatesController();

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void testGetExchangeRatesUpperCase() throws ServiceException, ValidationException {
    String inputBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> inputSellingCurrencyCodes = createSellingCurrencyCodes();
    String sanitisedBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> sanitisedSellingCurrencyCodes = createSellingCurrencyCodes();
    testGetExchangeRates(inputBuyingCurrencyCode, inputSellingCurrencyCodes,
        sanitisedBuyingCurrencyCode, sanitisedSellingCurrencyCodes);
  }

  @Test
  public void testGetExchangeRatesLowerCase() throws ServiceException, ValidationException {
    String inputBuyingCurrencyCode = createBuyingCurrencyCodeLowerCase();
    List<String> inputSellingCurrencyCodes = createSellingCurrencyCodesLowerCase();
    String sanitisedBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> sanitisedSellingCurrencyCodes = createSellingCurrencyCodes();
    testGetExchangeRates(inputBuyingCurrencyCode, inputSellingCurrencyCodes,
        sanitisedBuyingCurrencyCode, sanitisedSellingCurrencyCodes);
  }

  @Test
  public void testGetExchangeRatesValidationException()
      throws ServiceException, ValidationException {
    String inputBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> inputSellingCurrencyCodes = createSellingCurrencyCodes();
    String sanitisedBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> sanitisedSellingCurrencyCodes = createSellingCurrencyCodes();
    ValidationException validationException = new ValidationException(EXCEPTION_MESSAGE);
    testGetExchangeRatesException(inputBuyingCurrencyCode, inputSellingCurrencyCodes,
        sanitisedBuyingCurrencyCode, sanitisedSellingCurrencyCodes, validationException);
  }

  @Test
  public void testGetExchangeRatesServiceException() throws ServiceException, ValidationException {
    String inputBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> inputSellingCurrencyCodes = createSellingCurrencyCodes();
    String sanitisedBuyingCurrencyCode = createBuyingCurrencyCode();
    List<String> sanitisedSellingCurrencyCodes = createSellingCurrencyCodes();
    ServiceException serviceException = new ServiceException(EXCEPTION_MESSAGE);
    testGetExchangeRatesException(inputBuyingCurrencyCode, inputSellingCurrencyCodes,
        sanitisedBuyingCurrencyCode, sanitisedSellingCurrencyCodes, serviceException);
  }


  private void testGetExchangeRates(String inputBuyingCurrencyCode,
      List<String> inputSellingCurrencyCodes, String sanitisedBuyingCurrencyCode,
      List<String> sanitisedSellingCurrencyCodes) throws ValidationException, ServiceException {
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView =
        createExchangeRatesBuyingCurrencyView();
    when(mockExchangeRateService.getExchangeRatesBuyingCurrencyView(sanitisedBuyingCurrencyCode,
        sanitisedSellingCurrencyCodes)).thenReturn(exchangeRatesBuyingCurrencyView);
    ResponseEntity<ExchangeRatesBuyingCurrencyView> expectedResponse =
        new ResponseEntity<>(exchangeRatesBuyingCurrencyView, OK);
    ResponseEntity<ExchangeRatesBuyingCurrencyView> actualResponse = exchangeRatesController
        .getExchangeRates(inputBuyingCurrencyCode, inputSellingCurrencyCodes);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getExchangeRatesBuyingCurrencyView(
        sanitisedBuyingCurrencyCode, sanitisedSellingCurrencyCodes);
  }

  private void testGetExchangeRatesException(String inputBuyingCurrencyCode,
      List<String> inputSellingCurrencyCodes, String sanitisedBuyingCurrencyCode,
      List<String> sanitisedSellingCurrencyCodes, Exception expectedExceptionInstance)
      throws ValidationException, ServiceException {
    when(mockExchangeRateService.getExchangeRatesBuyingCurrencyView(sanitisedBuyingCurrencyCode,
        sanitisedSellingCurrencyCodes)).thenThrow(expectedExceptionInstance);
    expectedException.expect(expectedExceptionInstance.getClass());
    expectedException.expectMessage(expectedExceptionInstance.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRatesController.getExchangeRates(inputBuyingCurrencyCode, inputSellingCurrencyCodes);
  }
}
