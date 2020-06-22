package hsbc.web.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import hsbc.model.view.CurrencyExchangeRates;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.model.view.HistoricalExchangeRates;
import hsbc.service.ExchangeRateService;
import hsbc.util.StringUtil;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides exchange rates Web pages.
 */
@Api(tags = "Exchange Rates")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/exchange-rates", produces = APPLICATION_JSON_VALUE)
@Validated
public class ExchangeRatesController {

  private static final Logger LOGGER = LogManager.getLogger(ExchangeRatesController.class);

  @Autowired
  private ExchangeRateService exchangeRateService;

  /**
   * Displays exchange rates from the given buying currency to the given selling currencies.
   * 
   * @param buyingCurrencyCode The buying currency code.
   * @param sellingCurrencyCodes The selling currency codes.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  //@GetMapping(value = "/{buyingCurrencyCode}")
  public ResponseEntity<ExchangeRatesBuyingCurrencyView> getExchangeRates(
      @PathVariable(name = "buyingCurrencyCode") @NotBlank @Size(min = 3, max = 3,
          message = "Buying currency code must be 3 characters.") String buyingCurrencyCode,
      @RequestParam List<@NotBlank @Size(min = 3, max = 3,
          message = "Selling currency code must be 3 characters.") String> sellingCurrencyCodes)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry(buyingCurrencyCode);
    buyingCurrencyCode = StringUtil.sanitiseToUpperCase(buyingCurrencyCode);
    sellingCurrencyCodes = StringUtil.sanitiseStringListToUpperCase(sellingCurrencyCodes);
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView = exchangeRateService
        .getExchangeRatesBuyingCurrencyView(buyingCurrencyCode, sellingCurrencyCodes);
    return LOGGER.traceExit(new ResponseEntity<>(exchangeRatesBuyingCurrencyView, OK));
  }

  /**
   * Returns current buying exchange rates from the given buying currency to the given selling
   * currencies.
   * 
   * @param buyingCurrencyId The buying currency i.d.
   * @param sellingCurrencyIds The selling currency i.d.s.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/buy/current/{buyingCurrencyId}")
  public ResponseEntity<CurrentExchangeRates> getCurrentBuyingExchangeRates(
      @PathVariable(name = "buyingCurrencyId") Long buyingCurrencyId,
      @RequestParam List<Long> sellingCurrencyIds)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  /**
   * Returns current buying exchange rates from the given buying currency to the given selling
   * currencies.
   * 
   * @param buyingCurrencyCode The buying currency code.
   * @param sellingCurrencyCodes The selling currency codes.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/buy/current/code/{buyingCurrencyCode}")
  public ResponseEntity<CurrentExchangeRates> getCurrentBuyingExchangeRates(
      @PathVariable(name = "buyingCurrencyCode") @NotBlank @Size(min = 3, max = 3,
          message = "Buying currency code must be 3 characters.") String buyingCurrencyCode,
      @RequestParam List<@NotBlank @Size(min = 3, max = 3,
          message = "Selling currency code must be 3 characters.") String> sellingCurrencyCodes)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry(buyingCurrencyCode);
    buyingCurrencyCode = StringUtil.sanitiseToUpperCase(buyingCurrencyCode);
    sellingCurrencyCodes = StringUtil.sanitiseStringListToUpperCase(sellingCurrencyCodes);
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  @GetMapping(value = "/buy/current")
  public ResponseEntity<CurrentExchangeRates> getCurrentBuyingExchangeRates()
      throws ServiceException, ValidationException, ConstraintViolationException {
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentBuyingExchangeRates();
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  /**
   * Returns historical buying exchange rates from the given buying currency to the given selling
   * currencies.
   * 
   * @param buyingCurrencyId The buying currency i.d.
   * @param sellingCurrencyIds The selling currency i.d.s.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/buy/history/{buyingCurrencyId}")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalBuyingExchangeRates(
      @PathVariable(name = "buyingCurrencyId") Long buyingCurrencyId,
      @RequestParam List<Long> sellingCurrencyIds)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyId, sellingCurrencyIds);
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }
  
  /**
   * Returns historical buying exchange rates from the given buying currency to the given selling
   * currencies.
   * 
   * @param buyingCurrencyCode The buying currency code.
   * @param sellingCurrencyCodes The selling currency codes.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/buy/history/code/{buyingCurrencyCode}")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalBuyingExchangeRates(
      @PathVariable(name = "buyingCurrencyCode") @NotBlank @Size(min = 3, max = 3,
          message = "Buying currency code must be 3 characters.") String buyingCurrencyCode,
      @RequestParam List<@NotBlank @Size(min = 3, max = 3,
          message = "Selling currency code must be 3 characters.") String> sellingCurrencyCodes)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry(buyingCurrencyCode);
    buyingCurrencyCode = StringUtil.sanitiseToUpperCase(buyingCurrencyCode);
    sellingCurrencyCodes = StringUtil.sanitiseStringListToUpperCase(sellingCurrencyCodes);
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalBuyingExchangeRates(buyingCurrencyCode, sellingCurrencyCodes);
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }
  
  @GetMapping(value = "/buy/history")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalBuyingExchangeRates()
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalBuyingExchangeRates();
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }
  
  /**
   * Returns current selling exchange rates to the given selling currency from the given buying
   * currencies.
   * 
   * @param sellingCurrencyId The selling currency i.d.
   * @param buyingCurrencyIds The buying currency i.d.s.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/sell/current/{sellingCurrencyId}")
  public ResponseEntity<CurrentExchangeRates> getCurrentSellingExchangeRates(
      @PathVariable(name = "sellingCurrencyId") Long sellingCurrencyId,
      @RequestParam List<Long> buyingCurrencyIds)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  /**
   * Returns current selling exchange rates to the given selling currency from the given buying
   * currencies.
   * 
   * @param sellingCurrencyCode The selling currency code.
   * @param buyingCurrencyCodes The buying currency codes.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/sell/current/code/{sellingCurrencyCode}")
  public ResponseEntity<CurrentExchangeRates> getCurrentSellingExchangeRates(
      @PathVariable(name = "sellingCurrencyCode") @NotBlank @Size(min = 3, max = 3,
          message = "Selling currency code must be 3 characters.") String sellingCurrencyCode,
      @RequestParam List<@NotBlank @Size(min = 3, max = 3,
          message = "Buying currency code must be 3 characters.") String> buyingCurrencyCodes)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry(sellingCurrencyCode);
    sellingCurrencyCode = StringUtil.sanitiseToUpperCase(sellingCurrencyCode);
    buyingCurrencyCodes = StringUtil.sanitiseStringListToUpperCase(buyingCurrencyCodes);
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  @GetMapping(value = "/sell/current")
  public ResponseEntity<CurrentExchangeRates> getCurrentSellingExchangeRates()
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    CurrentExchangeRates currentExchangeRates =
        exchangeRateService.getCurrentSellingExchangeRates();
    LOGGER.debug(currentExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(currentExchangeRates, OK));
  }
  
  /**
   * Returns historical selling exchange rates to the given selling currency from the given buying
   * currencies.
   * 
   * @param sellingCurrencyId The selling currency i.d.
   * @param buyingCurrencyIds The buying currency i.d.s.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/sell/history/{sellingCurrencyId}")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalSellingExchangeRates(
      @PathVariable(name = "sellingCurrencyId") Long sellingCurrencyId,
      @RequestParam List<Long> buyingCurrencyIds)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyId, buyingCurrencyIds);
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }
  
  /**
   * Returns historical selling exchange rates to the given selling currency from the given buying
   * currencies.
   * 
   * @param sellingCurrencyCode The selling currency code.
   * @param buyingCurrencyCodes The buying currency codes.
   * @return exchange rates.
   * @throws ServiceException service exception.
   * @throws ValidationException validation exception.
   */
  @GetMapping(value = "/sell/history/code/{sellingCurrencyCode}")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalSellingExchangeRates(
      @PathVariable(name = "sellingCurrencyCode") @NotBlank @Size(min = 3, max = 3,
          message = "Selling currency code must be 3 characters.") String sellingCurrencyCode,
      @RequestParam List<@NotBlank @Size(min = 3, max = 3,
          message = "Buying currency code must be 3 characters.") String> buyingCurrencyCodes)
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry(sellingCurrencyCode);
    sellingCurrencyCode = StringUtil.sanitiseToUpperCase(sellingCurrencyCode);
    buyingCurrencyCodes = StringUtil.sanitiseStringListToUpperCase(buyingCurrencyCodes);
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalSellingExchangeRates(sellingCurrencyCode, buyingCurrencyCodes);
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }
  
  @GetMapping(value = "/sell/history")
  public ResponseEntity<HistoricalExchangeRates> getHistoricalSellingExchangeRates()
      throws ServiceException, ValidationException, ConstraintViolationException {
    LOGGER.traceEntry();
    HistoricalExchangeRates historicalExchangeRates =
        exchangeRateService.getHistoricalSellingExchangeRates();
    LOGGER.debug(historicalExchangeRates);
    return LOGGER.traceExit(new ResponseEntity<>(historicalExchangeRates, OK));
  }

}
