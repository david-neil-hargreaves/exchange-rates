package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.Period;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.HistoricalExchangeRates;
import hsbc.util.exception.InvalidConfigurationException;
import hsbc.util.exception.ValidationException;
import java.util.List;

/**
 * Provides service methods for exchange rates.
 *
 */
public interface ExchangeRateService {

  /**
   * Returns a CurrentExchangeRates instance representing current buying exchange rates for the
   * buying currency to the selling currencies.
   * 
   * @param buyingCurrency The buying currency.
   * @param sellingCurrencies The selling currencies.
   * @return A CurrentExchangeRates instance representing current buying exchange rates for the
   *         buying currency to the selling currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies) throws ValidationException;


  /**
   * Returns a CurrentExchangeRates instance representing current buying exchange rates for the
   * buying currency to the selling currencies.
   * 
   * @param buyingCurrencyId The buying currency i.d.
   * @param sellingCurrencyIds The selling currency i.d.s.
   * @return A CurrentExchangeRates instance representing current buying exchange rates for the
   *         buying currency to the selling currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current buying exchange rates for the
   * buying currency to the selling currencies.
   * 
   * @param buyingCurrencyCode The buying currency code.
   * @param sellingCurrencyCodes The selling currency codes.
   * @return A CurrentExchangeRates instance representing current buying exchange rates for the
   *         buying currency to the selling currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current buying exchange rates for the
   * system default subject currency to the system default other currencies.
   * 
   * @return A CurrentExchangeRates instance representing current buying exchange rates for the
   *         buying currency to the selling currencies.
   * @throws ValidationException If a validation exception occurs. InvalidConfigurationException If
   *         a configuration exception occurs.
   */
  public CurrentExchangeRates getCurrentBuyingExchangeRates()
      throws InvalidConfigurationException, ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current selling exchange rates for the
   * selling currency to the buying currencies.
   * 
   * @param sellingCurrency The selling currency.
   * @param buyingCurrencies The buying currencies.
   * @return A CurrentExchangeRates instance representing current selling exchange rates for the
   *         selling currency to the buying currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies) throws ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current selling exchange rates for the
   * selling currency to the buying currencies.
   * 
   * @param sellingCurrencyId The selling currency i.d.
   * @param buyingCurrencyIds The buying currency i.d.s.
   * @return A CurrentExchangeRates instance representing current selling exchange rates for the
   *         selling currency to the buying currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current selling exchange rates for the
   * selling currency to the buying currencies.
   * 
   * @param sellingCurrencyCode The selling currency code.
   * @param buyingCurrencyCodes The buying currency codes.
   * @return A CurrentExchangeRates instance representing current selling exchange rates for the
   *         selling currency to the buying currencies.
   * @throws ValidationException If the input parameters are invalid.
   */
  public CurrentExchangeRates getCurrentSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException;

  /**
   * Returns a CurrentExchangeRates instance representing current selling exchange rates for the
   * system default subject currency to the system default other currencies.
   * 
   * @return A CurrentExchangeRates instance representing current selling exchange rates for the
   *         selling currency to the buying currencies.
   * @throws ValidationException If a validation exception occurs. InvalidConfigurationException If
   *         a configuration exception occurs.
   */
  public CurrentExchangeRates getCurrentSellingExchangeRates()
      throws ValidationException, InvalidConfigurationException;


  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the buying currency to the selling currencies for the periods.
   * 
   * @param buyingCurrency The buying currency.
   * @param sellingCurrencies The selling currencies.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods) throws ValidationException;


  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the buying currency to the selling currencies for the periods.
   * 
   * @param buyingCurrencyId The buying currency i.d.s.
   * @param sellingCurrencyIds The selling currency i.d.s.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds, List<Period> periods) throws ValidationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the buying currency to the selling currencies for the periods.
   * 
   * @param buyingCurrencyCode The buying currency codes.
   * @param sellingCurrencyCodes The selling currency codes.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes, List<Period> periods) throws ValidationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the buying currency to the selling currencies for a default range of periods.
   * 
   * @param buyingCurrencyId The buying currency i.d.s.
   * @param sellingCurrencyIds The selling currency i.d.s.
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException, InvalidConfigurationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the buying currency to the selling currencies for a default range of periods.
   * 
   * @param buyingCurrencyCode The buying currency codes.
   * @param sellingCurrencyCodes The selling currency codes.
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException, InvalidConfigurationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical buying exchange rates for
   * the system default subject currency to the system default other currencies.
   * 
   * @return A HistoricalExchangeRates instance representing historical buying exchange rates for
   *         the buying currency to the selling currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalBuyingExchangeRates()
      throws ValidationException, InvalidConfigurationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the selling currency to the buying currencies for the periods.
   * 
   * @param sellingCurrency The selling currency.
   * @param buyingCurrencies The buying currencies.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies, List<Period> periods) throws ValidationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the selling currency to the buying currencies for the periods.
   * 
   * @param sellingCurrencyId The selling currency i.d.
   * @param buyingCurrencyIds The buying currency i.d.s.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds, List<Period> periods) throws ValidationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the selling currency to the buying currencies for the periods.
   * 
   * @param sellingCurrencyCode The selling currency code.
   * @param buyingCurrencyCode The buying currency codes.
   * @param periods The periods.
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes, List<Period> periods) throws ValidationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the selling currency to the buying currencies for a default range of periods.
   * 
   * @param sellingCurrencyId The selling currency i.d.s.
   * @param buyingCurrencyIds The buying currency i.d.s.
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException, InvalidConfigurationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the selling currency to the buying currencies for a default range of periods.
   * 
   * @param sellingCurrencyCode The selling currency code.
   * @param buyingCurrencyCodes The buying currency codes.
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException, InvalidConfigurationException;

  /**
   * Returns a HistoricalExchangeRates instance representing historical selling exchange rates for
   * the system default subject currency to the system default other currencies.
   * 
   * @return A HistoricalExchangeRates instance representing historical selling exchange rates for
   *         the selling currency to the buying currencies for the periods.
   * @throws ValidationException If the input parameters are invalid. InvalidConfigurationException
   *         If a configuration exception occurs.
   */
  public HistoricalExchangeRates getHistoricalSellingExchangeRates()
      throws ValidationException, InvalidConfigurationException;

}
