package hsbc.service.validation;

import static hsbc.test.TestData.CURRENCY_CODE_EUROS;
import static hsbc.test.TestData.CURRENCY_CODE_HONG_KONG_DOLLARS;
import static hsbc.test.TestData.CURRENCY_CODE_POUNDS_STERLING;
import static hsbc.util.exception.ValidationException.MESSAGE_BUYING_CURRENCY_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_DUPLICATE_SELLING_CURRENCY;
import static hsbc.util.exception.ValidationException.MESSAGE_SELLING_CURRENCIES_REQUIRED;
import static hsbc.util.exception.ValidationException.MESSAGE_SELLING_CURRENCY_CANNOT_BE_SAME_AS_BUYING_CURRENCY;

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
  public void testValidateValidSingleSellingCurrency() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateValidMultipleSellingCurrencies() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    sellingCurrencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS);
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateMissingBuyingCurrency() throws ValidationException {
    final String buyingCurrencyCode = null;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    ValidationException validationException =
        new ValidationException(MESSAGE_BUYING_CURRENCY_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateZeroSellingCurrencies() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    ValidationException validationException =
        new ValidationException(MESSAGE_SELLING_CURRENCIES_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateSellingCurrenciesNull() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = null;
    ValidationException validationException =
        new ValidationException(MESSAGE_SELLING_CURRENCIES_REQUIRED);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateDuplicateSellingCurrency() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    sellingCurrencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS);
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    String message =
        String.format(MESSAGE_DUPLICATE_SELLING_CURRENCY, CURRENCY_CODE_POUNDS_STERLING);
    ValidationException validationException = new ValidationException(message);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

  @Test
  public void testValidateSellingCurrencySameAsBuyingCurrency() throws ValidationException {
    final String buyingCurrencyCode = CURRENCY_CODE_EUROS;
    final List<String> sellingCurrencyCodes = new ArrayList<>();
    sellingCurrencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    sellingCurrencyCodes.add(CURRENCY_CODE_EUROS);
    ValidationException validationException =
        new ValidationException(MESSAGE_SELLING_CURRENCY_CANNOT_BE_SAME_AS_BUYING_CURRENCY);
    expectedException.expect(validationException.getClass());
    expectedException.expectMessage(validationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    exchangeRateServiceValidator.validate(buyingCurrencyCode, sellingCurrencyCodes);
  }

}
