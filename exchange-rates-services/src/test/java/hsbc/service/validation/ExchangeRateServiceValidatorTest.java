package hsbc.service.validation;

import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createOtherCurrencies;
import static hsbc.test.TestData.createPeriods;
import static hsbc.util.exception.ValidationException.MESSAGE_DUPLICATE_OTHER_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_OTHER_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_OTHER_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY;
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
    List<Currency> otherCurrencies = createOtherCurrencies();
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateSubjectCurrencyNull() throws ValidationException {
    Currency subjectCurrency = null;
    List<Currency> otherCurrencies = createOtherCurrencies();
    String message = String.format(MESSAGE_SUBJECT_CURRENCY_REQUIRED,
        ExchangeRateRole.BUYING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateOtherCurrenciesNull() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = null;
    String message = String.format(MESSAGE_OTHER_CURRENCIES_REQUIRED,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateOtherCurrenciesEmpty() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = new ArrayList<>();
    String message = String.format(MESSAGE_OTHER_CURRENCIES_REQUIRED,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateDuplicateOtherCurrency() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = createOtherCurrencies();
    otherCurrencies.add(otherCurrencies.get(0));
    String message = String.format(MESSAGE_DUPLICATE_OTHER_CURRENCY,
        ExchangeRateRole.SELLING.getDescription(), otherCurrencies.get(0).getCode());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateOtherCurrencySameAsSubjectCurrency() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = createOtherCurrencies();
    otherCurrencies.add(subjectCurrency);
    String message = String.format(MESSAGE_OTHER_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY,
        ExchangeRateRole.SELLING.getDescriptionInitialCapitalLetter(),
        ExchangeRateRole.BUYING.getDescription());
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, ExchangeRateRole.BUYING,
        ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriods() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = createOtherCurrencies();
    List<Period> periods = createPeriods();
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriodsNull() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = createOtherCurrencies();
    List<Period> periods = null;
    ValidationException validationException = new ValidationException(MESSAGE_PERIODS_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

  @Test
  public void testValidateWithPeriodsEmpty() throws ValidationException {
    Currency subjectCurrency = createEuro();
    List<Currency> otherCurrencies = createOtherCurrencies();
    List<Period> periods = new ArrayList<>();
    ValidationException validationException = new ValidationException(MESSAGE_PERIODS_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(subjectCurrency, otherCurrencies, periods,
        ExchangeRateRole.BUYING, ExchangeRateRole.SELLING);
  }

}
