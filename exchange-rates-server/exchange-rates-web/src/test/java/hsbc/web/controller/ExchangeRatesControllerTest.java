package hsbc.web.controller;

import static hsbc.test.TestData.createComparisonCurrencyCodes;
import static hsbc.test.TestData.createComparisonCurrencyCodesLowerCase;
import static hsbc.test.TestData.createComparisonCurrencyIds;
import static hsbc.test.TestData.createCurrentExchangeRates;
import static hsbc.test.TestData.createHistoricalExchangeRates;
import static hsbc.test.TestData.createSubjectCurrencyCode;
import static hsbc.test.TestData.createSubjectCurrencyCodeLowerCase;
import static hsbc.test.TestData.createSubjectCurrencyId;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.OK;

import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.HistoricalExchangeRates;
import hsbc.service.ExchangeRateService;
import hsbc.test.AbstractTest;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.List;
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
  public void testGetCurrentBuyingExchangeRatesCurrencyIds()
      throws ServiceException, ValidationException {
    Long buyingCurrencyId = createSubjectCurrencyId();
    List<Long> sellingCurrencyIds = createComparisonCurrencyIds();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(
        mockExchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds))
            .thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse =
        exchangeRatesController.getCurrentBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentBuyingExchangeRates(buyingCurrencyId,
        sellingCurrencyIds);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesCurrencyCodes()
      throws ServiceException, ValidationException {
    String buyingCurrencyCode = createSubjectCurrencyCode();
    List<String> sellingCurrencyCodes = createComparisonCurrencyCodes();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes)).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse = exchangeRatesController
        .getCurrentBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesCurrencyCodesLowerCase()
      throws ServiceException, ValidationException {
    String buyingCurrencyCodeLowerCase = createSubjectCurrencyCodeLowerCase();
    String buyingCurrencyCode = createSubjectCurrencyCode();
    List<String> sellingCurrencyCodesLowerCase = createComparisonCurrencyCodesLowerCase();
    List<String> sellingCurrencyCodes = createComparisonCurrencyCodes();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes)).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse = exchangeRatesController
        .getCurrentBuyingExchangeRates(buyingCurrencyCodeLowerCase, sellingCurrencyCodesLowerCase);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes);
  }

  @Test
  public void testGetCurrentBuyingExchangeRatesNoParameters()
      throws ServiceException, ValidationException {
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentBuyingExchangeRates()).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse =
        exchangeRatesController.getCurrentBuyingExchangeRates();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentBuyingExchangeRates();
  }

  @Test
  public void testGetCurrentSellingExchangeRatesCurrencyIds()
      throws ServiceException, ValidationException {
    Long sellingCurrencyId = createSubjectCurrencyId();
    List<Long> buyingCurrencyIds = createComparisonCurrencyIds();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyId,
        buyingCurrencyIds)).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse = exchangeRatesController
        .getCurrentSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentSellingExchangeRates(sellingCurrencyId,
        buyingCurrencyIds);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesCurrencyCodes()
      throws ServiceException, ValidationException {
    String sellingCurrencyCode = createSubjectCurrencyCode();
    List<String> buyingCurrencyCodes = createComparisonCurrencyCodes();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes)).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse = exchangeRatesController
        .getCurrentSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesCurrencyCodesLowerCase()
      throws ServiceException, ValidationException {
    String sellingCurrencyCodeLowerCase = createSubjectCurrencyCodeLowerCase();
    String sellingCurrencyCode = createSubjectCurrencyCode();
    List<String> buyingCurrencyCodesLowerCase = createComparisonCurrencyCodesLowerCase();
    List<String> buyingCurrencyCodes = createComparisonCurrencyCodes();
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes)).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse = exchangeRatesController
        .getCurrentSellingExchangeRates(sellingCurrencyCodeLowerCase, buyingCurrencyCodesLowerCase);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes);
  }

  @Test
  public void testGetCurrentSellingExchangeRatesNoParameters()
      throws ServiceException, ValidationException {
    CurrentExchangeRates currentExchangeRates = createCurrentExchangeRates();
    when(mockExchangeRateService.getCurrentSellingExchangeRates()).thenReturn(currentExchangeRates);
    ResponseEntity<CurrentExchangeRates> expectedResponse =
        new ResponseEntity<>(currentExchangeRates, OK);
    ResponseEntity<CurrentExchangeRates> actualResponse =
        exchangeRatesController.getCurrentSellingExchangeRates();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getCurrentSellingExchangeRates();
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyIds()
      throws ServiceException, ValidationException {
    Long buyingCurrencyId = createSubjectCurrencyId();
    List<Long> sellingCurrencyIds = createComparisonCurrencyIds();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyId,
        sellingCurrencyIds)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse = exchangeRatesController
        .getHistoricalBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalBuyingExchangeRates(buyingCurrencyId,
        sellingCurrencyIds);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyCodes()
      throws ServiceException, ValidationException {
    String buyingCurrencyCode = createSubjectCurrencyCode();
    List<String> sellingCurrencyCodes = createComparisonCurrencyCodes();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse = exchangeRatesController
        .getHistoricalBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyCodesLowerCase()
      throws ServiceException, ValidationException {
    String buyingCurrencyCodeLowerCase = createSubjectCurrencyCodeLowerCase();
    String buyingCurrencyCode = createSubjectCurrencyCode();
    List<String> sellingCurrencyCodesLowerCase = createComparisonCurrencyCodesLowerCase();
    List<String> sellingCurrencyCodes = createComparisonCurrencyCodes();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse =
        exchangeRatesController.getHistoricalBuyingExchangeRates(buyingCurrencyCodeLowerCase,
            sellingCurrencyCodesLowerCase);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalBuyingExchangeRates(buyingCurrencyCode,
        sellingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalBuyingExchangeRatesCurrencyCodesNoParameters()
      throws ServiceException, ValidationException {
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalBuyingExchangeRates())
        .thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse =
        exchangeRatesController.getHistoricalBuyingExchangeRates();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalBuyingExchangeRates();
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyIds()
      throws ServiceException, ValidationException {
    Long sellingCurrencyId = createSubjectCurrencyId();
    List<Long> buyingCurrencyIds = createComparisonCurrencyIds();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyId,
        buyingCurrencyIds)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse = exchangeRatesController
        .getHistoricalSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalSellingExchangeRates(sellingCurrencyId,
        buyingCurrencyIds);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyCodes()
      throws ServiceException, ValidationException {
    String sellingCurrencyCode = createSubjectCurrencyCode();
    List<String> buyingCurrencyCodes = createComparisonCurrencyCodes();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse = exchangeRatesController
        .getHistoricalSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyCodesLowerCase()
      throws ServiceException, ValidationException {
    String sellingCurrencyCodeLowerCase = createSubjectCurrencyCodeLowerCase();
    String sellingCurrencyCode = createSubjectCurrencyCode();
    List<String> buyingCurrencyCodesLowerCase = createComparisonCurrencyCodesLowerCase();
    List<String> buyingCurrencyCodes = createComparisonCurrencyCodes();
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes)).thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse =
        exchangeRatesController.getHistoricalSellingExchangeRates(sellingCurrencyCodeLowerCase,
            buyingCurrencyCodesLowerCase);
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalSellingExchangeRates(sellingCurrencyCode,
        buyingCurrencyCodes);
  }

  @Test
  public void testGetHistoricalSellingExchangeRatesCurrencyCodesNoParameters()
      throws ServiceException, ValidationException {
    HistoricalExchangeRates historicalExchangeRates = createHistoricalExchangeRates();
    when(mockExchangeRateService.getHistoricalSellingExchangeRates())
        .thenReturn(historicalExchangeRates);
    ResponseEntity<HistoricalExchangeRates> expectedResponse =
        new ResponseEntity<>(historicalExchangeRates, OK);
    ResponseEntity<HistoricalExchangeRates> actualResponse =
        exchangeRatesController.getHistoricalSellingExchangeRates();
    assertEquals(ATTRIBUTE_RESPONSE, expectedResponse, actualResponse);
    verify(mockExchangeRateService, times(1)).getHistoricalSellingExchangeRates();
  }

}
