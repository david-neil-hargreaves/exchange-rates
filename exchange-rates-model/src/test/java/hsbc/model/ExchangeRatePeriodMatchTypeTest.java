package hsbc.model;

import static hsbc.test.TestData.INVALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE;
import static hsbc.test.TestData.MESSAGE_INVALID_ENUM_VALUE;
import static hsbc.test.TestData.VALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE;

import hsbc.test.AbstractTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class ExchangeRatePeriodMatchTypeTest extends AbstractTest {

  @Test
  public void testValidExchangeRatePeriodMatchType() {
    ExchangeRatePeriodMatchType.valueOf(VALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE);
  }

  @Test
  public void testInvalidExchangeRatePeriodMatchType() {
    String message = String.format(MESSAGE_INVALID_ENUM_VALUE,
        ExchangeRatePeriodMatchType.class.getName(), INVALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(message);
    expectedException.expect(illegalArgumentException.getClass());
    expectedException.expectMessage(illegalArgumentException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    ExchangeRatePeriodMatchType.valueOf(INVALID_EXCHANGE_RATE_PERIOD_MATCH_TYPE);
  }
}
