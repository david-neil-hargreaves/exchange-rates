package hsbc.test;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRateHistory;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData {

  private static final String CURRENCY_CODE_EUROS = "EUR";
  private static final String CURRENCY_CODE_POUNDS_STERLING = "GBP";
  private static final String CURRENCY_CODE_US_DOLLARS = "USD";
  private static final String CURRENCY_CODE_HONG_KONG_DOLLARS = "HKD";
  private static final String CURRENCY_DESCRIPTION_EUROS = "Euros";
  private static final String CURRENCY_DESCRIPTION_POUNDS_STERLING = "Pounds sterling";
  private static final String CURRENCY_DESCRIPTION_US_DOLLARS = "US dollars";
  private static final String CURRENCY_DESCRIPTION_HONG_KONG_DOLLARS = "Hong Kong dollars";
  private static final Integer CURRENCY_SORT_ORDER_EUROS = 20;
  private static final Integer CURRENCY_SORT_ORDER_POUNDS_STERLING = 10;
  private static final Integer CURRENCY_SORT_ORDER_US_DOLLARS = 30;
  private static final Integer CURRENCY_SORT_ORDER_HONG_KONG_DOLLARS = 40;
  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING =
      new BigDecimal("0.89");
  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS = new BigDecimal("1.1");
  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS =
      new BigDecimal("8.5");
  private static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS =
      new BigDecimal("1.12");
  private static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_US_DOLLARS =
      new BigDecimal("1.23");
  private static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_HONG_KONG_DOLLARS =
      new BigDecimal("9.54");

  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200224 =
      new BigDecimal("0.95");
  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200401 =
      new BigDecimal("0.91");
  private static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_20200224 =
      new BigDecimal("1.15");
  private static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_20200224 =
      new BigDecimal("1.06");

  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER =
      new BigDecimal("1.14");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY =
      new BigDecimal("0.92");

  private static final Date DATE_20200224 = getDate("2020-02-24T00:00:00Z");
  private static final Date DATE_20200401 = getDate("2020-04-01T00:00:00Z");
  private static final Date DATE_DEC_START = getDate("2019-12-01T00:00:00Z");
  public static final Date DATE_DEC_MIDDLE = getDate("2019-12-16T12:00:00Z");
  private static final Date DATE_DEC_END = getDate("2019-12-31T23:59:59Z");
  private static final Date DATE_JAN_START = getDate("2020-01-01T00:00:00Z");
  private static final Date DATE_JAN_END = getDate("2020-01-31T23:59:59Z");

  public static final String MESSAGE_INVALID_ENUM_VALUE = "No enum constant %s.%s";

  public static final String VALID_PERIOD_TYPE = "CALENDAR_MONTH";
  public static final String PERIOD_TYPE_DESCRIPTION = "calendar month";
  public static final String INVALID_PERIOD_TYPE = "HONAP";

  public static final String VALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE = "START";
  public static final String INVALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE = "KESD";

  private static final String PERIOD_CODE_DEC = "2019012";
  private static final String PERIOD_CODE_JAN = "2020001";

  private static final String PERIOD_DESCRIPTION_DEC = "December 2019";
  private static final String PERIOD_DESCRIPTION_JAN = "January 2020";
  
  public static final String VALID_EXCHANGE_RATE_ROLE = "BUYING";
  public static final String EXCHANGE_RATE_ROLE_DESCRIPTION = "buying";
  public static final String EXCHANGE_RATE_ROLE_DESCRIPTION_INITIAL_CAPITAL_LETTER = "Buying";
 
  public static Currency createEuro() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_EUROS);
    currency.setDescription(CURRENCY_DESCRIPTION_EUROS);
    currency.setSortOrderNumber(CURRENCY_SORT_ORDER_EUROS);
    return currency;
  }

  public static Currency createPoundsSterling() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_POUNDS_STERLING);
    currency.setDescription(CURRENCY_DESCRIPTION_POUNDS_STERLING);
    currency.setSortOrderNumber(CURRENCY_SORT_ORDER_POUNDS_STERLING);
    return currency;
  }

  public static Currency createUsDollars() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_US_DOLLARS);
    currency.setDescription(CURRENCY_DESCRIPTION_US_DOLLARS);
    currency.setSortOrderNumber(CURRENCY_SORT_ORDER_US_DOLLARS);
    return currency;
  }

  public static Currency createHongKongDollars() {
    Currency currency = new Currency();
    currency.setCode(CURRENCY_CODE_HONG_KONG_DOLLARS);
    currency.setDescription(CURRENCY_DESCRIPTION_HONG_KONG_DOLLARS);
    currency.setSortOrderNumber(CURRENCY_SORT_ORDER_HONG_KONG_DOLLARS);
    return currency;
  }

  public static ExchangeRate createEuroPoundsSterling() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createEuro());
    exchangeRate.setSellingCurrency(createPoundsSterling());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING);
    return exchangeRate;
  }

  public static ExchangeRate createEuroUsDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createEuro());
    exchangeRate.setSellingCurrency(createUsDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS);
    return exchangeRate;
  }

  private static ExchangeRate createEuroHongKongDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createEuro());
    exchangeRate.setSellingCurrency(createHongKongDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS);
    return exchangeRate;
  }

  private static ExchangeRate createPoundsSterlingEuro() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createEuro());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS);
    return exchangeRate;
  }

  private static ExchangeRate createPoundsSterlingUsDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createUsDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_US_DOLLARS);
    return exchangeRate;
  }

  private static ExchangeRate createPoundsSterlingHongKongDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createHongKongDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_HONG_KONG_DOLLARS);
    return exchangeRate;
  }

  private static ExchangeRateHistory createEuroPoundsSterling20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200224);
    return exchangeRateHistory;
  }

  private static ExchangeRateHistory createEuroPoundsSterling20200401() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200401);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200401);
    return exchangeRateHistory;
  }

  private static ExchangeRateHistory createEuroUsDollars20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createUsDollars());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_20200224);
    return exchangeRateHistory;
  }

  private static ExchangeRateHistory createPoundsSterlingEuro20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createPoundsSterling());
    exchangeRateHistory.setSellingCurrency(createEuro());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_20200224);
    return exchangeRateHistory;
  }

  public static List<Currency> createCurrencies() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createHongKongDollars());
    currencies.add(createUsDollars());
    currencies.add(createPoundsSterling());
    currencies.add(createEuro());
    return currencies;
  }

  public static List<Currency> createCurrenciesSorted() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createPoundsSterling());
    currencies.add(createEuro());
    currencies.add(createUsDollars());
    currencies.add(createHongKongDollars());
    return currencies;
  }

  public static List<Currency> createSellingCurrencies() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createHongKongDollars());
    currencies.add(createUsDollars());
    currencies.add(createPoundsSterling());
    return currencies;
  }

  public static List<ExchangeRate> createExchangeRates() {
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    exchangeRates.add(createEuroHongKongDollars());
    exchangeRates.add(createPoundsSterlingHongKongDollars());
    exchangeRates.add(createEuroPoundsSterling());
    exchangeRates.add(createPoundsSterlingUsDollars());
    exchangeRates.add(createEuroUsDollars());
    exchangeRates.add(createPoundsSterlingEuro());
    return exchangeRates;
  }

  public static List<ExchangeRate> createExchangeRatesSorted() {
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    exchangeRates.add(createPoundsSterlingEuro());
    exchangeRates.add(createPoundsSterlingUsDollars());
    exchangeRates.add(createPoundsSterlingHongKongDollars());
    exchangeRates.add(createEuroPoundsSterling());
    exchangeRates.add(createEuroUsDollars());
    exchangeRates.add(createEuroHongKongDollars());
    return exchangeRates;
  }

  public static List<ExchangeRateHistory> createExchangeRateHistories() {
    List<ExchangeRateHistory> exchangeRateHistories = new ArrayList<>();
    exchangeRateHistories.add(createEuroPoundsSterling20200401());
    exchangeRateHistories.add(createEuroUsDollars20200224());
    exchangeRateHistories.add(createEuroPoundsSterling20200224());
    exchangeRateHistories.add(createPoundsSterlingEuro20200224());
    return exchangeRateHistories;
  }

  public static List<ExchangeRateHistory> createExchangeRateHistoriesSorted() {
    List<ExchangeRateHistory> exchangeRateHistories = new ArrayList<>();
    exchangeRateHistories.add(createPoundsSterlingEuro20200224());
    exchangeRateHistories.add(createEuroPoundsSterling20200224());
    exchangeRateHistories.add(createEuroPoundsSterling20200401());
    exchangeRateHistories.add(createEuroUsDollars20200224());
    return exchangeRateHistories;
  }

  public static Period createDecember() {
    Period period = new Period();
    period.setType(PeriodType.CALENDAR_MONTH);
    period.setCode(PERIOD_CODE_DEC);
    period.setDescription(PERIOD_DESCRIPTION_DEC);
    period.setStartDateTime(DATE_DEC_START);
    period.setEndDateTime(DATE_DEC_END);
    period.setCurrentPeriod(false);
    return period;
  }

  public static Period createJanuary() {
    Period period = new Period();
    period.setType(PeriodType.CALENDAR_MONTH);
    period.setCode(PERIOD_CODE_JAN);
    period.setDescription(PERIOD_DESCRIPTION_JAN);
    period.setStartDateTime(DATE_JAN_START);
    period.setEndDateTime(DATE_JAN_END);
    period.setCurrentPeriod(false);
    return period;
  }

  public static List<Period> createPeriods(PeriodType periodType) {
    List<Period> periods = new ArrayList<>();
    periods.add(createDecember());
    periods.add(createJanuary());
    return periods;
  }

  private static Date getDate(String dateString) {
    OffsetDateTime offsetDateTime =
        OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
    Instant instant = offsetDateTime.toInstant();
    return Date.from(instant);
  }


}
