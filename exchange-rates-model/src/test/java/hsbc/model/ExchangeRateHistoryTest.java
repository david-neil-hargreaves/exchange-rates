
package hsbc.model;

import static hsbc.test.TestData.createExchangeRateHistories;
import static hsbc.test.TestData.createExchangeRateHistoriesSorted;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class ExchangeRateHistoryTest {

  private static final String ATTRIBUTE_SORTED_EXCHANGE_RATE_HISTORIES =
      "Sorted exchange rate histories";

  @Test
  public void testCompareTo() {
    List<ExchangeRateHistory> exchangeRateHistories = createExchangeRateHistories();
    Collections.sort(exchangeRateHistories);
    List<ExchangeRateHistory> expectedExchangeRateHistories = createExchangeRateHistoriesSorted();
    assertEquals(ATTRIBUTE_SORTED_EXCHANGE_RATE_HISTORIES, expectedExchangeRateHistories,
        exchangeRateHistories);
  }

}
