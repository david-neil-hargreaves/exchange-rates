
package hsbc.model;

import static hsbc.test.TestData.createExchangeRates;
import static hsbc.test.TestData.createExchangeRatesSorted;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class ExchangeRateTest {

  private static final String ATTRIBUTE_SORTED_EXCHANGE_RATES = "Sorted exchange rates";

  @Test
  public void testCompareTo() {
    List<ExchangeRate> exchangeRates = createExchangeRates();
    Collections.sort(exchangeRates);
    List<ExchangeRate> expectedExchangeRates = createExchangeRatesSorted();
    assertEquals(ATTRIBUTE_SORTED_EXCHANGE_RATES, expectedExchangeRates, exchangeRates);
  }

}
