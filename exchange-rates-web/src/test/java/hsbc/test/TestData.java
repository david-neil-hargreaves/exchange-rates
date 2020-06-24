package hsbc.test;

import hsbc.model.Currency;
import hsbc.model.view.CurrentExchangeRates;
import hsbc.model.view.HistoricalExchangeRates;
import java.util.ArrayList;
import java.util.List;

public class TestData {



  public static final Long CURRENCY_ID_EUROS = 1L;
  public static final Long CURRENCY_ID_POUNDS_STERLING = 2L;
  public static final Long CURRENCY_ID_HONG_KONG_DOLLARS = 4L;
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

  public static Long createSubjectCurrencyId() {
    return CURRENCY_ID_EUROS;
  }

  public static String createSubjectCurrencyCode() {
    return CURRENCY_CODE_EUROS;
  }

  public static String createSubjectCurrencyCodeLowerCase() {
    return CURRENCY_CODE_EUROS_LOWER_CASE;
  }

  public static List<Long> createOtherCurrencyIds() {
    List<Long> currencyIds = new ArrayList<>();
    currencyIds.add(CURRENCY_ID_POUNDS_STERLING);
    currencyIds.add(CURRENCY_ID_HONG_KONG_DOLLARS);
    return currencyIds;
  }

  public static List<String> createOtherCurrencyCodes() {
    List<String> currencyCodes = new ArrayList<>();
    currencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    currencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS);
    return currencyCodes;
  }

  public static List<String> createOtherCurrencyCodesLowerCase() {
    List<String> currencyCodes = new ArrayList<>();
    currencyCodes.add(CURRENCY_CODE_POUNDS_STERLING_LOWER_CASE);
    currencyCodes.add(CURRENCY_CODE_HONG_KONG_DOLLARS_LOWER_CASE);
    return currencyCodes;
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

  public static List<Currency> createCurrencies() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createHongKongDollars());
    currencies.add(createPoundsSterling());
    return currencies;
  }

  public static CurrentExchangeRates createCurrentExchangeRates() {
    CurrentExchangeRates currentExchangeRates = new CurrentExchangeRates(null, null);
    return currentExchangeRates;
  }

  public static HistoricalExchangeRates createHistoricalExchangeRates() {
    HistoricalExchangeRates historicalExchangeRates = new HistoricalExchangeRates(null, null, null);
    return historicalExchangeRates;
  }

}
