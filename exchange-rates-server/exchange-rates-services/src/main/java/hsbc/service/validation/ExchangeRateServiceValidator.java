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
   * Validates the subject currency and comparison currencies.
   * 
   * @param subjectCurrency The subject currency.
   * @param comparisonCurrencies The comparison currencies.
   * @param exchangeRateRoleSubjectCurrency The exchange rate role for the subject currency.
   * @param exchangeRateRoleComparisonCurrencies The exchange rate role for the comparison
   *        currencies.
   * @throws ValidationException if the input parameters are invalid.
   */
  public void validate(Currency subjectCurrency, List<Currency> comparisonCurrencies,
      ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleComparisonCurrencies) throws ValidationException;

  /**
   * Validates the subject currency, comparison currencies and periods.
   * 
   * @param subjectCurrency The subject currency.
   * @param comparisonCurrencies The comparison currencies.
   * @param periods The periods.
   * @param exchangeRateRoleSubjectCurrency The exchange rate role for the subject currency.
   * @param exchangeRateRoleComparisonCurrencies The exchange rate role for the comparison
   *        currencies.
   * @throws ValidationException if the input parameters are invalid.
   */
  public void validate(Currency subjectCurrency, List<Currency> comparisonCurrencies,
      List<Period> periods, ExchangeRateRole exchangeRateRoleSubjectCurrency,
      ExchangeRateRole exchangeRateRoleComparisonCurrencies) throws ValidationException;

}
