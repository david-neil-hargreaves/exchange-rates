package hsbc.service.validation;

import static hsbc.util.exception.ValidationException.*;
import static hsbc.util.exception.ValidationException.MESSAGE_OTHER_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_OTHER_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_SUBJECT_CURRENCY_REQUIRED;

import hsbc.model.Currency;
import hsbc.model.ExchangeRateRole;
import hsbc.model.Period;
import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceValidatorImpl implements ExchangeRateServiceValidator {

  @Override
  public void validate(Currency subjectCurrency, List<Currency> otherCurrencies,
      ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleOtherCurrencies) throws ValidationException {
    if (subjectCurrency == null) {
      String message = String.format(MESSAGE_SUBJECT_CURRENCY_REQUIRED,
          exchangeRateRoleSubjectCurrency.getDescriptionInitialCapitalLetter());
      throw new ValidationException(message);
    }
    if ((otherCurrencies == null) || (otherCurrencies.isEmpty())) {
      String message = String.format(MESSAGE_OTHER_CURRENCIES_REQUIRED,
          exchangeRateRoleOtherCurrencies.getDescriptionInitialCapitalLetter());
      throw new ValidationException(message);
    }
    List<Currency> uniqueOtherCurrencies = new ArrayList<>();
    for (Currency otherCurrency : otherCurrencies) {
      if (uniqueOtherCurrencies.contains(otherCurrency)) {
        String message = String.format(MESSAGE_DUPLICATE_OTHER_CURRENCY,
            exchangeRateRoleOtherCurrencies.getDescription(), otherCurrency.getCode());
        throw new ValidationException(message);
      }
      uniqueOtherCurrencies.add(otherCurrency);
    }
    if (otherCurrencies.contains(subjectCurrency)) {
      String message = String.format(MESSAGE_OTHER_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY,
          exchangeRateRoleOtherCurrencies.getDescriptionInitialCapitalLetter(),
          exchangeRateRoleSubjectCurrency.getDescription());
      throw new ValidationException(message);
    }
  }

  @Override
  public void validate(Currency subjectCurrency, List<Currency> otherCurrencies,
      List<Period> periods, ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleOtherCurrencies) throws ValidationException {
    validate(subjectCurrency, otherCurrencies, exchangeRateRoleSubjectCurrency,
        exchangeRateRoleOtherCurrencies);
    if ((periods == null) || (periods.isEmpty())) {
      throw new ValidationException(MESSAGE_PERIODS_REQUIRED);
    }
  }

}
