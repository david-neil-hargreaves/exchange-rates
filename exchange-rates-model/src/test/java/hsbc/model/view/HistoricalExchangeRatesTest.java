package hsbc.model.view;

import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER;
import static hsbc.test.TestData.createDecember;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createHongKongDollars;
import static hsbc.test.TestData.createJanuary;
import static hsbc.test.TestData.createPeriods;
import static hsbc.test.TestData.createPoundsSterling;
import static hsbc.test.TestData.createSellingCurrencies;
import static hsbc.test.TestData.createUsDollars;
import static org.junit.Assert.assertEquals;

import hsbc.model.Currency;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class HistoricalExchangeRatesTest {

  private static final String ATTRIBUTE_SUBJECT_CURRENCY = "Subject currency";
  private static final String ATTRIBUTE_OTHER_CURRENCIES = "Other currencies";
  private static final String ATTRIBUTE_PERIODS = "Periods";
  private static final String ATTRIBUTE_RATE = "Rate";
  private static final String ATTRIBUTE_HISTORICAL_EXCHANGE_RATES = "Historical exchange rates";

  @Test
  public void test() {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<Period> periods = createPeriods(PeriodType.CALENDAR_MONTH);
    HistoricalExchangeRates historicalExchangeRates =
        new HistoricalExchangeRates(buyingCurrency, sellingCurrencies, periods);
    Currency usDollars = createUsDollars();
    Period december = createDecember();
    historicalExchangeRates.setExchangeRate(usDollars, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER));
    Currency poundsSterling = createPoundsSterling();
    Period january = createJanuary();
    historicalExchangeRates.setExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY));
    assertEquals(ATTRIBUTE_SUBJECT_CURRENCY, buyingCurrency,
        historicalExchangeRates.getSubjectCurrency());
    assertEquals(ATTRIBUTE_OTHER_CURRENCIES, sellingCurrencies,
        historicalExchangeRates.getOtherCurrencies());
    assertEquals(ATTRIBUTE_PERIODS, periods, historicalExchangeRates.getPeriods());
    assertEquals(ATTRIBUTE_RATE, Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER),
        historicalExchangeRates.getExchangeRate(usDollars, december));
    assertEquals(ATTRIBUTE_RATE, Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY),
        historicalExchangeRates.getExchangeRate(poundsSterling, january));
    assertEquals(ATTRIBUTE_RATE, Optional.empty(),
        historicalExchangeRates.getExchangeRate(usDollars, january));
    Currency hongKongDollars = createHongKongDollars();
    List<HistoricalExchangeRatesCurrency> expectedHistoricalExchangeRates = new ArrayList<>();
    HistoricalExchangeRatesCurrency poundsSterlingExchangeRates = new HistoricalExchangeRatesCurrency(poundsSterling);
    expectedHistoricalExchangeRates.add(poundsSterlingExchangeRates);
    poundsSterlingExchangeRates.addRate(Optional.empty());
    poundsSterlingExchangeRates
        .addRate(Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY));
    HistoricalExchangeRatesCurrency usDollarsExchangeRates = new HistoricalExchangeRatesCurrency(usDollars);
    expectedHistoricalExchangeRates.add(usDollarsExchangeRates);
    usDollarsExchangeRates.addRate(Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER));
    usDollarsExchangeRates.addRate(Optional.empty());
    HistoricalExchangeRatesCurrency hongKongDollarsExchangeRates =
        new HistoricalExchangeRatesCurrency(hongKongDollars);
    expectedHistoricalExchangeRates.add(hongKongDollarsExchangeRates);
    hongKongDollarsExchangeRates.addRate(Optional.empty());
    hongKongDollarsExchangeRates.addRate(Optional.empty());
    List<HistoricalExchangeRatesCurrency> actualHistoricalExchangeRates =
        historicalExchangeRates.getHistoricalExchangeRates();
    assertEquals(ATTRIBUTE_HISTORICAL_EXCHANGE_RATES, expectedHistoricalExchangeRates,
        actualHistoricalExchangeRates);
  }

}
