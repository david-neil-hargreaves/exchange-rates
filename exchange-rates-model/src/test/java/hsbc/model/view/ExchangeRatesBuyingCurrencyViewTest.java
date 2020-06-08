package hsbc.model.view;

import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY;
import static hsbc.test.TestData.EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER;
import static hsbc.test.TestData.createDecember;
import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createEuroPoundsSterling;
import static hsbc.test.TestData.createEuroUsDollars;
import static hsbc.test.TestData.createHongKongDollars;
import static hsbc.test.TestData.createJanuary;
import static hsbc.test.TestData.createPeriods;
import static hsbc.test.TestData.createPoundsSterling;
import static hsbc.test.TestData.createSellingCurrencies;
import static hsbc.test.TestData.createUsDollars;
import static org.junit.Assert.assertEquals;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import hsbc.model.Period;
import hsbc.model.PeriodType;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class ExchangeRatesBuyingCurrencyViewTest {

  private static final String ATTRIBUTE_BUYING_CURRENCY = "Buying currency";
  private static final String ATTRIBUTE_SELLING_CURRENCIES = "Selling currencies";
  private static final String ATTRIBUTE_PERIODS = "Periods";
  private static final String ATTRIBUTE_RATE = "Rate";

  @Test
  public void test() {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    List<Period> periods = createPeriods(PeriodType.CALENDAR_MONTH);
    ExchangeRatesBuyingCurrencyView exchangeRatesBuyingCurrencyView =
        new ExchangeRatesBuyingCurrencyView(buyingCurrency, sellingCurrencies, periods);
    ExchangeRate exchangeRateUsDollars = createEuroUsDollars();
    exchangeRatesBuyingCurrencyView.setCurrentExchangeRate(
        exchangeRateUsDollars.getSellingCurrency(), Optional.of(exchangeRateUsDollars.getRate()));
    ExchangeRate exchangeRatePoundsSterling = createEuroPoundsSterling();
    exchangeRatesBuyingCurrencyView.setCurrentExchangeRate(
        exchangeRatePoundsSterling.getSellingCurrency(),
        Optional.of(exchangeRatePoundsSterling.getRate()));
    Currency usDollars = createUsDollars();
    Period december = createDecember();
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(usDollars, december,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER));
    Currency poundsSterling = createPoundsSterling();
    Period january = createJanuary();
    exchangeRatesBuyingCurrencyView.setHistoricalExchangeRate(poundsSterling, january,
        Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY));
    assertEquals(ATTRIBUTE_BUYING_CURRENCY, buyingCurrency,
        exchangeRatesBuyingCurrencyView.getBuyingCurrency());
    assertEquals(ATTRIBUTE_SELLING_CURRENCIES, sellingCurrencies,
        exchangeRatesBuyingCurrencyView.getSellingCurrencies());
    assertEquals(ATTRIBUTE_PERIODS, periods, exchangeRatesBuyingCurrencyView.getPeriods());
    assertEquals(ATTRIBUTE_RATE, Optional.of(exchangeRateUsDollars.getRate()),
        exchangeRatesBuyingCurrencyView
            .getCurrentExchangeRate(exchangeRateUsDollars.getSellingCurrency()));
    assertEquals(ATTRIBUTE_RATE, Optional.of(exchangeRatePoundsSterling.getRate()),
        exchangeRatesBuyingCurrencyView
            .getCurrentExchangeRate(exchangeRatePoundsSterling.getSellingCurrency()));
    Currency hongKongDollars = createHongKongDollars();
    assertEquals(ATTRIBUTE_RATE, Optional.empty(),
        exchangeRatesBuyingCurrencyView.getCurrentExchangeRate(hongKongDollars));
    assertEquals(ATTRIBUTE_RATE, Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_US_DOLLARS_DECEMBER),
        exchangeRatesBuyingCurrencyView.getHistoricalExchangeRate(usDollars, december));
    assertEquals(ATTRIBUTE_RATE, Optional.of(EXCHANGE_RATE_BUY_EUROS_SELL_POUNDS_STERLING_JANUARY),
        exchangeRatesBuyingCurrencyView.getHistoricalExchangeRate(poundsSterling, january));
    assertEquals(ATTRIBUTE_RATE, Optional.empty(),
        exchangeRatesBuyingCurrencyView.getHistoricalExchangeRate(usDollars, january));
  }

}
