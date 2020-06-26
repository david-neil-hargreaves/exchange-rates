
package hsbc.model;

import static hsbc.test.TestData.createCurrencies;
import static hsbc.test.TestData.createCurrenciesSorted;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class CurrencyTest {

  private static final String ATTRIBUTE_SORTED_CURRENCIES = "Sorted currencies";

  @Test
  public void testCompareTo() {
    List<Currency> currencies = createCurrencies();
    List<Currency> expectedSortedCurrencies = createCurrenciesSorted();
    assertNotEquals(ATTRIBUTE_SORTED_CURRENCIES, expectedSortedCurrencies, currencies);
    Collections.sort(currencies);
    assertEquals(ATTRIBUTE_SORTED_CURRENCIES, expectedSortedCurrencies, currencies);
  }

}
