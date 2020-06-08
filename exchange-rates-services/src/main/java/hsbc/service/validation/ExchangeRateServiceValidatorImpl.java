package hsbc.service.validation;

import static hsbc.util.exception.ValidationException.MESSAGE_BUYING_CURRENCY_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_DUPLICATE_SELLING_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_SELLING_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_SELLING_CURRENCY_CANNOT_BE_SAME_AS_BUYING_CURRENCY;

import hsbc.util.StringUtil;
import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceValidatorImpl implements ExchangeRateServiceValidator {

  @Override
  public void validate(String buyingCurrencyCode, List<String> sellingCurrencyCodes)
      throws ValidationException {
    if (StringUtil.isBlank(buyingCurrencyCode)) {
      throw new ValidationException(MESSAGE_BUYING_CURRENCY_REQUIRED);
    }
    if ((sellingCurrencyCodes == null) || (sellingCurrencyCodes.isEmpty())) {
      throw new ValidationException(MESSAGE_SELLING_CURRENCIES_REQUIRED);
    }
    List<String> uniqueSellingCurrencyCodes = new ArrayList<String>();
    for (String sellingCurrencyCode : sellingCurrencyCodes) {
      if (uniqueSellingCurrencyCodes.contains(sellingCurrencyCode)) {
        String message = String.format(MESSAGE_DUPLICATE_SELLING_CURRENCY, sellingCurrencyCode);
        throw new ValidationException(message);
      }
      uniqueSellingCurrencyCodes.add(sellingCurrencyCode);
    }
    if (sellingCurrencyCodes.contains(buyingCurrencyCode)) {
      throw new ValidationException(MESSAGE_SELLING_CURRENCY_CANNOT_BE_SAME_AS_BUYING_CURRENCY);
    }
  }

}
