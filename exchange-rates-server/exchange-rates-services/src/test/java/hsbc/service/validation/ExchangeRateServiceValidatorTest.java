package hsbc.service.validation;

import static hsbc.test.TestData.createComparisonCurrencies;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createPeriods;
import static hsbc.util.exception.ValidationException.MESSAGE_COMPARISON_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_COMPARISON_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_DUPLICATE_COMPARISON_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_PERIODS_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_SUBJECT_CURRENCY_REQUIRED;

import hsbc.model.Currency;
import hsbc.model.ExchangeRateRole;
import hsbc.model.Period;
import hsbc.test.AbstractTest;
import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class ExchangeRateServiceValidatorTest extends AbstractTest {

  private ExchangeRateServiceValidator exchangeRateServiceValidator =
      new ExchangeRateServiceValidatorImpl();

  @Test
  public void testValidate() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateSubjectCurrencyNull() throws ValidationException {
    String message = String.format(MESSAGE_SUBJECT_CURRENCY_REQUIRED,
        ExchangeRateRole.BUYING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = null;
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateComparisonCurrenciesNull() throws ValidationException {
    String message = String.format(MESSAGE_COMPARISON_CURRENCIES_REQUIRED,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = null;
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateComparisonCurrenciesEmpty() throws ValidationException {
    String message = String.format(MESSAGE_COMPARISON_CURRENCIES_REQUIRED,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = new ArrayList<>();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateDuplicateComparisonCurrency() throws ValidationException {
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    comparisonCurrencies.add(comparisonCurrencies.get(0));
    String message = String.format(MESSAGE_DUPLICATE_COMPARISON_CURRENCY,
        ExchangeRateRole.SELLING.getDescription(), comparisonCurrencies.get(0).getCode());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = createEuro();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateComparisonCurrencySameAsSubjectCurrency() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    comparisonCurrencies.add(subjectCurrency);
    String message = String.format(MESSAGE_COMPARISON_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter(),
        ExchangeRateRole.BUYING.getDescription());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriods() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    List<Period> periods = createPeriods();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriodsNull() throws ValidationException {
    ValidationException validationException = new ValidationException(MESSAGE_PERIODS_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    List<Period> periods = null;
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriodsEmpty() throws ValidationException {
    ValidationException validationException = new ValidationException(MESSAGE_PERIODS_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    Currency subjectCurrency = createEuro();
    List<Currency> comparisonCurrencies = createComparisonCurrencies();
    List<Period> periods = new ArrayList<>();
    exchangeRateServiceValidator.validate(subjectCurrency, comparisonCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

}
