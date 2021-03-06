package hsbc.web.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import hsbc.model.Currency;
import hsbc.service.CurrencyService;
import hsbc.util.exception.ServiceException;
import io.swagger.annotations.Api;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides currency REST endpoints.
 */
@Api(tags = "Currencies")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/currencies", produces = APPLICATION_JSON_VALUE)
@Validated
public class CurrencyController {

  private static final Logger LOGGER = LogManager.getLogger(CurrencyController.class);

  @Autowired
  private CurrencyService currencyService;

  /**
   * Returns all currencies.
   * 
   * @return All currencies.
   */
  @GetMapping(value = "/all")
  public ResponseEntity<List<Currency>> getCurrencies() {
    LOGGER.traceEntry();
    List<Currency> currencies = currencyService.findAll();
    return LOGGER.traceExit(new ResponseEntity<>(currencies, OK));
  }

  /**
   * Returns the default subject currency.
   * 
   * @return The default subject currency.
   * @throws ServiceException If a service exception occurs.
   */
  @GetMapping(value = "/defaultSubjectCurrency")
  public ResponseEntity<Currency> findDefaultSubjectCurrency() throws ServiceException {
    LOGGER.traceEntry();
    Currency currency = currencyService.findDefaultSubjectCurrency();
    return LOGGER.traceExit(new ResponseEntity<>(currency, OK));
  }

  /**
   * Returns all default comparison currencies.
   * 
   * @return All default comparison currencies.
   */
  @GetMapping(value = "/defaultComparisonCurrencies")
  public ResponseEntity<List<Currency>> findDefaultComparisonCurrencies() {
    LOGGER.traceEntry();
    List<Currency> currencies = currencyService.findDefaultComparisonCurrencies();
    return LOGGER.traceExit(new ResponseEntity<>(currencies, OK));
  }

}
