package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.Period;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.HistoricalExchangeRates;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.List;

public interface ExchangeRateService {

  public CurrentExchangeRates getCurrentBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies);

  public CurrentExchangeRates getCurrentBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException, ServiceException;

  public CurrentExchangeRates getCurrentBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException, ServiceException;

  public CurrentExchangeRates getCurrentBuyingExchangeRates() throws ServiceException;

  public CurrentExchangeRates getCurrentSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies);

  public CurrentExchangeRates getCurrentSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException, ServiceException;

  public CurrentExchangeRates getCurrentSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException, ServiceException;

  public CurrentExchangeRates getCurrentSellingExchangeRates() throws ServiceException;

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods);

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds, List<Period> periods) throws ValidationException;

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes, List<Period> periods) throws ValidationException;

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(Long buyingCurrencyId,
      List<Long> sellingCurrencyIds) throws ValidationException, ServiceException;

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates(String buyingCurrencyCode,
      List<String> sellingCurrencyCodes) throws ValidationException, ServiceException;

  public HistoricalExchangeRates getHistoricalBuyingExchangeRates() throws ServiceException;

  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Currency sellingCurrency,
      List<Currency> buyingCurrencies, List<Period> periods);

  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds, List<Period> periods) throws ValidationException;

  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes, List<Period> periods) throws ValidationException;

  public HistoricalExchangeRates getHistoricalSellingExchangeRates(Long sellingCurrencyId,
      List<Long> buyingCurrencyIds) throws ValidationException, ServiceException;

  public HistoricalExchangeRates getHistoricalSellingExchangeRates(String sellingCurrencyCode,
      List<String> buyingCurrencyCodes) throws ValidationException, ServiceException;

  public HistoricalExchangeRates getHistoricalSellingExchangeRates() throws ServiceException;

}
