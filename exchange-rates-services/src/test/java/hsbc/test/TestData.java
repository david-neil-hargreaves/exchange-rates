
package hsbc.test;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.ExchangeRateHistory;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.view.ExchangeRatesBuyingCurrencyView;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.Data;

public class TestData {

  public static final String CURRENCY_CODE_EUROS = "EUR";
  public static final String CURRENCY_CODE_POUNDS_STERLING = "GBP";
  public static final String CURRENCY_CODE_US_DOLLARS = "USD";
  public static final String CURRENCY_CODE_HONG_KONG_DOLLARS = "HKD";
  public static final String CURRENCY_CODE_INVALID = "GRO";
  public static final String CURRENCY_DESCRIPTION_EUROS = "Euros";
  public static final String CURRENCY_DESCRIPTION_POUNDS_STERLING = "Pounds sterling";
  public static final String CURRENCY_DESCRIPTION_US_DOLLARS = "US dollars";
  public static final String CURRENCY_DESCRIPTION_HONG_KONG_DOLLARS = "Hong Kong dollars";
  public static final Integer CURRENCY_SORT_ORDER_EUROS = 20;
  public static final Integer CURRENCY_SORT_ORDER_POUNDS_STERLING = 10;
  public static final Integer CURRENCY_SORT_ORDER_US_DOLLARS = 30;
  public static final Integer CURRENCY_SORT_ORDER_HONG_KONG_DOLLARS = 40;
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING =
      new BigDecimal("0.89");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS = new BigDecimal("1.1");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS =
      new BigDecimal("8.50");
  public static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS =
      new BigDecimal("1.12");
  public static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_US_DOLLARS =
      new BigDecimal("1.23");
  public static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_HONG_KONG_DOLLARS =
      new BigDecimal("9.54");

  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200224 =
      new BigDecimal("0.95");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200401 =
      new BigDecimal("0.91");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_20200224 =
      new BigDecimal("1.15");
  public static final BigDecimal EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_20200224 =
      new BigDecimal("1.06");

  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC =
      new BigDecimal("1.30");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC =
      new BigDecimal("1.41");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC =
      new BigDecimal("1.52");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN =
      new BigDecimal("1.63");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_DEC =
      new BigDecimal("1.43");
  public static final BigDecimal EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_AVERAGE_JAN =
      new BigDecimal("1.61");

  public static final Date DATE_20191128_000000 = getDate("2019-11-28T00:00:00Z");
  public static final Date DATE_20191205_235959 = getDate("2019-12-05T23:59:59Z");
  public static final Date DATE_20191208_000000 = getDate("2019-12-08T00:00:00Z");
  public static final Date DATE_20191222_235959 = getDate("2019-12-22T23:59:59Z");
  public static final Date DATE_20191223_000000 = getDate("2019-12-23T00:00:00Z");
  public static final Date DATE_20200105_235959 = getDate("2020-01-05T23:59:59Z");
  public static final Date DATE_20200106_000000 = getDate("2020-01-06T00:00:00Z");
  public static final Date DATE_20200205_235959 = getDate("2020-02-05T23:59:59Z");
  public static final Date DATE_20200224 = getDate("2020-02-24T00:00:00Z");
  public static final Date DATE_20200401 = getDate("2020-04-01T00:00:00Z");
  public static final Date DATE_DEC_START = getDate("2019-12-01T00:00:00Z");
  public static final Date DATE_DEC_MIDDLE = getDate("2019-12-15T12:00:00Z");
  public static final Date DATE_DEC_END = getDate("2019-12-31T23:59:59Z");
  public static final Date DATE_JAN_START = getDate("2020-01-01T00:00:00Z");
  public static final Date DATE_JAN_END = getDate("2020-01-31T23:59:59Z");
  public static final Date DATE_FEB_START = getDate("2020-02-01T00:00:00Z");
  public static final Date DATE_FEB_END = getDate("2020-02-29T23:59:59Z");
  public static final Date DATE_MAR_START = getDate("2020-03-01T00:00:00Z");
  public static final Date DATE_MAR_END = getDate("2020-03-31T23:59:59Z");

  public static final String PERIOD_CODE_DEC = "2019012";
  public static final String PERIOD_CODE_JAN = "2020001";
  public static final String PERIOD_CODE_FEB = "2020002";
  public static final String PERIOD_CODE_MAR = "2020003";

  public static final String PERIOD_DESCRIPTION_DEC = "December 2019";
  public static final String PERIOD_DESCRIPTION_JAN = "January 2020";
  public static final String PERIOD_DESCRIPTION_FEB = "February 2020";
  public static final String PERIOD_DESCRIPTION_MAR = "March 2020";

  public static final String VALID_PERIOD_TYPE = "MONTH";
  public static final String INVALID_PERIOD_TYPE = "HONAP";
  public static final String MESSAGE_INVALID_PERIOD_TYPE = "No enum constant %s.%s";

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

  public static ExchangeRate createEuroHongKongDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createEuro());
    exchangeRate.setSellingCurrency(createHongKongDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS);
    exchangeRate.setStartDateTime(DATE_20200106_000000);
    return exchangeRate;
  }

  public static ExchangeRate createPoundsSterlingEuro() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createEuro());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS);
    return exchangeRate;
  }

  public static ExchangeRate createPoundsSterlingUsDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createUsDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_US_DOLLARS);
    return exchangeRate;
  }

  public static ExchangeRate createPoundsSterlingHongKongDollars() {
    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setBuyingCurrency(createPoundsSterling());
    exchangeRate.setSellingCurrency(createHongKongDollars());
    exchangeRate.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_HONG_KONG_DOLLARS);
    return exchangeRate;
  }

  public static ExchangeRateHistory createEuroPoundsSterling20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200224);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroPoundsSterling20200401() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200401);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200401);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroUsDollars20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createUsDollars());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_20200224);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createPoundsSterlingEuro20200224() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createPoundsSterling());
    exchangeRateHistory.setSellingCurrency(createEuro());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_POUNDS_STERLING_SELL_EUROS_20200224);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroPoundsSterlingStartDec() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20191128_000000);
    exchangeRateHistory.setEndDateTime(DATE_20191205_235959);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_START_DEC);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroPoundsSterlingMiddleDec() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20191208_000000);
    exchangeRateHistory.setEndDateTime(DATE_20191222_235959);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_MIDDLE_DEC);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroPoundsSterlingEndDec() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20191223_000000);
    exchangeRateHistory.setEndDateTime(DATE_20200105_235959);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC);
    return exchangeRateHistory;
  }

  public static ExchangeRateHistory createEuroPoundsSterlingRestJan() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200106_000000);
    exchangeRateHistory.setEndDateTime(DATE_20200205_235959);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN);
    return exchangeRateHistory;
  }


  public static List<ExchangeRateHistory> createEuroPoundsSterlingHistory() {
    List<ExchangeRateHistory> exchangeRateHistories = new ArrayList<>();
    exchangeRateHistories.add(createEuroPoundsSterlingStartDec());
    exchangeRateHistories.add(createEuroPoundsSterlingMiddleDec());
    exchangeRateHistories.add(createEuroPoundsSterlingEndDec());
    exchangeRateHistories.add(createEuroPoundsSterlingRestJan());
    return exchangeRateHistories;
  }

  public static List<Currency> createCurrencies() {
    List<Currency> currencies = new ArrayList<>();
    currencies.add(createPoundsSterling());
    currencies.add(createEuro());
    return currencies;
  }

  public static List<String> getCurrencyCodes() {
    List<String> currencyCodes = new ArrayList<>();
    currencyCodes.add(CURRENCY_CODE_POUNDS_STERLING);
    currencyCodes.add(CURRENCY_CODE_EUROS);
    return currencyCodes;
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
    exchangeRates.add(createEuroPoundsSterling());
    // exchangeRates.add(createEuroUsDollars());
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

  public static ExchangeRateHistory createEuroPoundsSterlingDecStart() {
    ExchangeRateHistory exchangeRateHistory = new ExchangeRateHistory();
    exchangeRateHistory.setBuyingCurrency(createEuro());
    exchangeRateHistory.setSellingCurrency(createPoundsSterling());
    exchangeRateHistory.setStartDateTime(DATE_20200224);
    exchangeRateHistory.setRate(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_20200224);
    return exchangeRateHistory;
  }

  public static ExchangeRatesBuyingCurrencyView createExchangeRatesBuyingCurrencyView() {
    Currency euro = createEuro();
    List<Currency> sellingCurrencies = new ArrayList<>();
    Currency poundsSterling = createPoundsSterling();
    sellingCurrencies.add(poundsSterling);
    Currency hongKongDollars = createHongKongDollars();
    sellingCurrencies.add(hongKongDollars);
    List<Period> periods = createPeriods(PeriodType.CALENDAR_MONTH);
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView =
        new ExchangeRatesBuyingCurrencyView(euro, sellingCurrencies, periods);
    ExchangeRate euroPoundsSterling = createEuroPoundsSterling();
    Optional<BigDecimal> euroPoundsSterlingRate = Optional.of(euroPoundsSterling.getRate());
    exchangeRatesBuyingCurrencyView.setCurrentExchangeRate(poundsSterling, euroPoundsSterlingRate);
    ExchangeRate euroHongKongDollars = createEuroHongKongDollars();
    Optional<BigDecimal> euroHongKongDollarsRate = Optional.of(euroHongKongDollars.getRate());
    exchangeRatesBuyingCurrencyView.setCurrentExchangeRate(hongKongDollars,
        euroHongKongDollarsRate);
    Period december = createDecember();
    Period january = createJanuary();
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(poundsSterling, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_END_DEC));
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_REST_JAN));
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(hongKongDollars, december,
        Optional.empty());
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(hongKongDollars, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_HONG_KONG_DOLLARS));
    return exchangeRatesBuyingCurrencyView;
  }

  public static Period createCurrentPeriod(PeriodType periodType) {
    Period period = new Period();
    period.setType(periodType);
    period.setCode(PERIOD_CODE_FEB);
    period.setDescription(PERIOD_DESCRIPTION_FEB);
    period.setStartDateTime(DATE_FEB_START);
    period.setEndDateTime(DATE_FEB_END);
    period.setCurrentPeriod(true);
    return period;
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

  public static ExchangeRateServiceTestData createExchangeRateServiceTestData() {
    ExchangeRateServiceTestData exchangeRateServiceTestData =
        new TestData().new ExchangeRateServiceTestData();
    Currency buyingCurrency = createEuro();
    exchangeRateServiceTestData.setBuyingCurrency(buyingCurrency);
    Currency poundsSterling = createPoundsSterling();
    exchangeRateServiceTestData.setPoundsSterling(poundsSterling);
    Currency hongKongDollars = createHongKongDollars();
    exchangeRateServiceTestData.setHongKongDollars(hongKongDollars);
    List<ExchangeRate> exchangeRates = createExchangeRates();
    exchangeRateServiceTestData.setExchangeRates(exchangeRates);
    exchangeRateServiceTestData.setDecember(createDecember());
    exchangeRateServiceTestData.setJanuary(createJanuary());
    List<ExchangeRateHistory> exchangeRateHistoriesPoundsSterling =
        createEuroPoundsSterlingHistory();
    exchangeRateServiceTestData
        .setExchangeRateHistoriesPoundsSterling(exchangeRateHistoriesPoundsSterling);
    Optional<ExchangeRate> optionalCurrentExchangeRateHongKongDollars =
        Optional.of(createEuroHongKongDollars());
    exchangeRateServiceTestData
        .setCurrentExchangeRateHongKongDollars(optionalCurrentExchangeRateHongKongDollars);
    List<ExchangeRateHistory> exchangeRateHistoriesHongKongDollars = new ArrayList<>();
    exchangeRateServiceTestData
        .setExchangeRateHistoriesHongKongDollars(exchangeRateHistoriesHongKongDollars);
    return exchangeRateServiceTestData;
  }

  @Data
  public class ExchangeRateServiceTestData {
    private Currency buyingCurrency;
    private Currency poundsSterling;
    private Currency hongKongDollars;
    private List<ExchangeRate> exchangeRates;
    private Period december;
    private Period january;
    private List<ExchangeRateHistory> exchangeRateHistoriesPoundsSterling;
    private Optional<ExchangeRate> currentExchangeRateHongKongDollars;
    private List<ExchangeRateHistory> exchangeRateHistoriesHongKongDollars;

    public List<Currency> getSellingCurrencies() {
      List<Currency> sellingCurrencies = new ArrayList<>();
      sellingCurrencies.add(poundsSterling);
      sellingCurrencies.add(hongKongDollars);
      return sellingCurrencies;
    }

    public List<Period> getPeriods() {
      List<Period> periods = new ArrayList<>();
      periods.add(december);
      periods.add(january);
      return periods;
    }

  }

}
