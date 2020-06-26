package hsbc.service.validation;

import static hsbc.util.exception.ValidationException.MESSAGE_COMPARISON_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_COMPARISON_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_DUPLICATE_COMPARISON_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_PERIODS_REQUIRED;
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
  public void validate(Currency subjectCurrency, List<Currency> comparisonCurrencies,
      ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleComparisonCurrencies) throws ValidationException {
    if (subjectCurrency == null) {
      String message = String.format(MESSAGE_SUBJECT_CURRENCY_REQUIRED,
          exchangeRateRoleSubjectCurrency.getDescriptionInitialCapitalLetter());
      throw new ValidationException(message);
    }
    if ((comparisonCurrencies == null) || (comparisonCurrencies.isEmpty())) {
      String message = String.format(MESSAGE_COMPARISON_CURRENCIES_REQUIRED,
          exchangeRateRoleComparisonCurrencies.getDescriptionInitialCapitalLetter());
      throw new ValidationException(message);
    }
    List<Currency> uniqueComparisonCurrencies = new ArrayList<>();
    for (Currency comparisonCurrency : comparisonCurrencies) {
      if (uniqueComparisonCurrencies.contains(comparisonCurrency)) {
        String message = String.format(MESSAGE_DUPLICATE_COMPARISON_CURRENCY,
            exchangeRateRoleComparisonCurrencies.getDescription(), comparisonCurrency.getCode());
        throw new ValidationException(message);
      }
      uniqueComparisonCurrencies.add(comparisonCurrency);
    }
    if (comparisonCurrencies.contains(subjectCurrency)) {
      String message = String.format(MESSAGE_COMPARISON_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY,
          exchangeRateRoleComparisonCurrencies.getDescriptionInitialCapitalLetter(),
          exchangeRateRoleSubjectCurrency.getDescription());
      throw new ValidationException(message);
    }
  }

  @Override
  public void validate(Currency subjectCurrency, List<Currency> comparisonCurrencies,
      List<Period> periods, ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleComparisonCurrencies) throws ValidationException {
    validate(subjectCurrency, comparisonCurrencies, exchangeRateRoleSubjectCurrency,
        exchangeRateRoleComparisonCurrencies);
    if ((periods == null) || (periods.isEmpty())) {
      throw new ValidationException(MESSAGE_PERIODS_REQUIRED);
    }
  }

}
