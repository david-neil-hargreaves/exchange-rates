package hsbc.model;

import static hsbc.test.TestData.INVALID_PERIOD_TYPE;
import static hsbc.test.TestData.MESSAGE_INVALID_ENUM_VALUE;
import static hsbc.test.TestData.PERIOD_TYPE_DESCRIPTION;
import static hsbc.test.TestData.VALID_PERIOD_TYPE;
import static org.junit.Assert.assertEquals;

import hsbc.test.AbstractTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class PeriodTypeTest extends AbstractTest {

  private static final String ATTRIBUTE_DESCRIPTION = "Description";

  @Test
  public void testGetDescription() {
    PeriodType periodType = PeriodType.valueOf(VALID_PERIOD_TYPE);
    assertEquals(ATTRIBUTE_DESCRIPTION, PERIOD_TYPE_DESCRIPTION, periodType.getDescription());
  }

  @Test
  public void testInvalidPeriodType() {
    String message =
        String.format(MESSAGE_INVALID_ENUM_VALUE, PeriodType.class.getName(), INVALID_PERIOD_TYPE);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(message);
    expectedException.expect(illegalArgumentException.getClass());
    expectedException.expectMessage(illegalArgumentException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    PeriodType.valueOf(INVALID_PERIOD_TYPE);
  }

}
