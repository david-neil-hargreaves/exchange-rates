package hsbc.model.view;

import static hsbc.test.TestData.createEuro;
import static hsbc.test.TestData.createEuroPoundsSterling;
import static hsbc.test.TestData.createEuroUsDollars;
import static hsbc.test.TestData.createHongKongDollars;
import static hsbc.test.TestData.createSellingCurrencies;
import static org.junit.Assert.assertEquals;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class CurrentExchangeRatesTest {

  private static final String ATTRIBUTE_SUBJECT_CURRENCY = "Subject currency";
  private static final String ATTRIBUTE_COMPARISON_CURRENCIES = "Comparison currencies";
  private static final String ATTRIBUTE_RATE = "Rate";
  private static final String ATTRIBUTE_CURRENT_EXCHANGE_RATES = "Current exchange rates";

  @Test
  public void test() {
    Currency buyingCurrency = createEuro();
    List<Currency> sellingCurrencies = createSellingCurrencies();
    CurrentExchangeRates currentExchangeRates =
        new CurrentExchangeRates(buyingCurrency, sellingCurrencies);
    ExchangeRate exchangeRateUsDollars = createEuroUsDollars();
    currentExchangeRates.setExchangeRate(exchangeRateUsDollars.getSellingCurrency(),
        Optional.of(exchangeRateUsDollars.getRate()));
    ExchangeRate exchangeRatePoundsSterling = createEuroPoundsSterling();
    currentExchangeRates.setExchangeRate(exchangeRatePoundsSterling.getSellingCurrency(),
        Optional.of(exchangeRatePoundsSterling.getRate()));
    currentExchangeRates.setExchangeRate(exchangeRatePoundsSterling.getSellingCurrency(),
        Optional.of(exchangeRatePoundsSterling.getRate()));
    assertEquals(ATTRIBUTE_SUBJECT_CURRENCY, buyingCurrency,
        currentExchangeRates.getSubjectCurrency());
    assertEquals(ATTRIBUTE_COMPARISON_CURRENCIES, sellingCurrencies,
        currentExchangeRates.getComparisonCurrencies());
    assertEquals(ATTRIBUTE_RATE, Optional.of(exchangeRateUsDollars.getRate()),
        currentExchangeRates.getExchangeRate(exchangeRateUsDollars.getSellingCurrency()));
    assertEquals(ATTRIBUTE_RATE, Optional.of(exchangeRatePoundsSterling.getRate()),
        currentExchangeRates.getExchangeRate(exchangeRatePoundsSterling.getSellingCurrency()));
    Currency hongKongDollars = createHongKongDollars();
    assertEquals(ATTRIBUTE_RATE, Optional.empty(),
        currentExchangeRates.getExchangeRate(hongKongDollars));
    List<CurrentExchangeRate> expectedCurrentExchangeRates = new ArrayList<>();
    expectedCurrentExchangeRates
        .add(new CurrentExchangeRate(exchangeRatePoundsSterling.getSellingCurrency(),
            Optional.of(exchangeRatePoundsSterling.getRate())));
    expectedCurrentExchangeRates.add(new CurrentExchangeRate(
        exchangeRateUsDollars.getSellingCurrency(), Optional.of(exchangeRateUsDollars.getRate())));
    expectedCurrentExchangeRates.add(new CurrentExchangeRate(hongKongDollars, Optional.empty()));
    List<CurrentExchangeRate> actualCurrentExchangeRates =
        currentExchangeRates.getCurrentExchangeRates();
    assertEquals(ATTRIBUTE_CURRENT_EXCHANGE_RATES, expectedCurrentExchangeRates,
        actualCurrentExchangeRates);
  }

}
