package hsbc.service.validation;

import hsbc.model.Currency;
import hsbc.model.ExchangeRateRole;
import hsbc.model.Period;
import hsbc.util.exception.ValidationException;
import java.util.List;

/**
 * Provides validation for service methods for exchange rates.
 *
 */
public interface ExchangeRateServiceValidator {

  /**
   * Validates the subject currency and other currencies.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   * @param exchangeRateRoleSubjectCurrency The exchange rate role for the subject currency.
   * @param exchangeRateRoleOtherCurrencies The exchange rate role for the other currencies.
   * @throws ValidationException if the input parameters are invalid.
   */
  public void validate(Currency subjectCurrency, List<Currency> otherCurrencies,
      ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleOtherCurrencies) throws ValidationException;

  /**
   * Validates the subject currency, other currencies and periods.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   * @param periods The periods.
   * @param exchangeRateRoleSubjectCurrency The exchange rate role for the subject currency.
   * @param exchangeRateRoleOtherCurrencies The exchange rate role for the other currencies.
   * @throws ValidationException if the input parameters are invalid.
   */
  public void validate(Currency subjectCurrency, List<Currency> otherCurrencies,
      List<Period> periods, ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleOtherCurrencies) throws ValidationException;

}
