package hsbc.web.controller;

import static org.mockito.MockitoAnnotations.initMocks;
import hsbc.service.ExchangeRateService;
import hsbc.test.AbstractTest;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

  /*@Test
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
  }*/

}
