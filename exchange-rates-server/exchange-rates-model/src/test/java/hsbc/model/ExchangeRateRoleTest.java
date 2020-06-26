package hsbc.model;

import static hsbc.test.TestData.EXCHANGE_RATE_ROLE_DESCRIPTION;
import static hsbc.test.TestData.EXCHANGE_RATE_ROLE_DESCRIPTION_INITIAL_CAPITAL_LETTER;
import static hsbc.test.TestData.VALID_EXCHANGE_RATE_ROLE;
import static org.junit.Assert.assertEquals;

import hsbc.test.AbstractTest;
import org.junit.Test;

public class ExchangeRateRoleTest extends AbstractTest {

  private static final String ATTRIBUTE_DESCRIPTION = "Description";
  private static final String ATTRIBUTE_DESCRIPTION_INITIAL_CAPITAL_LETTER =
      "Description inital capital letter";

  @Test
  public void testGetDescription() {
    ExchangeRateRole exchangeRateRole = ExchangeRateRole.valueOf(VALID_EXCHANGE_RATE_ROLE);
    assertEquals(ATTRIBUTE_DESCRIPTION, EXCHANGE_RATE_ROLE_DESCRIPTION,
        exchangeRateRole.getDescription());
  }

  @Test
  public void testGetDescriptionInitialCapitalLetter() {
    ExchangeRateRole exchangeRateRole = ExchangeRateRole.valueOf(VALID_EXCHANGE_RATE_ROLE);
    assertEquals(ATTRIBUTE_DESCRIPTION_INITIAL_CAPITAL_LETTER,
        EXCHANGE_RATE_ROLE_DESCRIPTION_INITIAL_CAPITAL_LETTER,
        exchangeRateRole.getDescriptionInitialCapitalLetter());
  }


}
