package hsbc.service;

import hsbc.model.Currency;
import hsbc.model.Period;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import hsbc.util.exception.ServiceException;
import hsbc.util.exception.ValidationException;
import java.util.List;

public interface ExchangeRateService {

  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(Currency buyingCurrency,
      List<Currency> sellingCurrencies, List<Period> periods);

  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(
      String buyingCurrencyCode, List<String> sellingCurrencyCodes, List<Period> periods)
      throws ValidationException;

  public ExchangeRatesBuyingCurrencyView getExchangeRatesBuyingCurrencyView(
      String buyingCurrencyCode, List<String> sellingCurrencyCodes)
      throws ValidationException, ServiceException;

}
