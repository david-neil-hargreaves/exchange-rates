package hsbc.test;

import hsbc.model.Currency;
import hsbc.model.Period;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import java.util.ArrayList;
import java.util.List;

public class TestData {

  public static final String CURRENCY_CODE_EUROS = "EUR";
  public static final String CURRENCY_CODE_POUNDS_STERLING = "GBP";
  public static final String CURRENCY_CODE_HONG_KONG_DOLLARS = "HKD";

  public static final String CURRENCY_CODE_EUROS_LOWER_CASE = "eur";
  public static final String CURRENCY_CODE_POUNDS_STERLING_LOWER_CASE = "gbp";
  public static final String CURRENCY_CODE_HONG_KONG_DOLLARS_LOWER_CASE = "hkd";

  public static final String EXCEPTION_MESSAGE = "exception message";
  public static final String MESSAGE = "message";
  public static final String ERROR = "error";
  public static final List<String> ERRORS = new ArrayList<>();

  static {
    ERRORS.add(ERROR);
  }

  public static final String CONSTRAINT_VIOLATION_MESSAGE = "constraint violation message";

  public static String createBuyingCurrencyCode() {
    return CURRENCY_CODE_EUROS;
  }

  public static String createBuyingCurrencyCodeLowerCase() {
    return CURRENCY_CODE_EUROS_LOWER_CASE;
  }

  public static List<String> createSellingCurrencyCodes() {
    List<String> currencyCodes = new ArrayList<>();
    currencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    currencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS);
    return currencyCodes;
  }

  public static List<String> createSellingCurrencyCodesLowerCase() {
    List<String> currencyCodes = new ArrayList<>();
    currencyCodes.add(CURRENCY_CODE_POUNDS_STERLING_LOWER_CASE);
    currencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS_LOWER_CASE);
    return currencyCodes;
  }

  public static ExchangeRatesBuyingCurrencyView createExchangeRatesBuyingCurrencyView() {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<Period> periods = new ArrayList<>();
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView =
        new ExchangeRatesBuyingCurrencyView(buyingCurrency, sellingCurrencies, periods);
    return exchangeRatesBuyingCurrencyView;
  }

  public static Currency createEuro() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_EUROS);
    return currency;
  }

  public static Currency createPoundsSterling() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_POUNDS_STERLING);
    return currency;
  }

  public static Currency createHongKongDollars() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_HONG_KONG_DOLLARS);
    return currency;
  }

  public static List<Currency> createSellingCurrencies() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createHongKongDollars());
    currencies.add(createPoundsSterling());
    return currencies;
  }

}
