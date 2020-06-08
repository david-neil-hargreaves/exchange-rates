package hsbc.web.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
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
  @GetMapping(value = "/{buyingCurrencyCode}")
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

}
