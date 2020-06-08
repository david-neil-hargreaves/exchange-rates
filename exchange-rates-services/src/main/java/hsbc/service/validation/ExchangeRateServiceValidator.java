package hsbc.service.validation;

import hsbc.util.exception.ValidationException;
import java.util.List;

// @Deprecated
public interface ExchangeRateServiceValidator {

  public void validate(String buyingCurrencyCode, List<String> sellingCurrencyCodes)
      throws ValidationException;

}
