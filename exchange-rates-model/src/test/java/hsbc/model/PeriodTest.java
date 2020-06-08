
package hsbc.model;

import static hsbc.test.TestData.DATE_DEC_MIDDLE;
import static hsbc.test.TestData.createDecember;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PeriodTest {

  private static final String ATTRIBUTE_MIDDLE_DATE_TIME = "Middle date / time";

  @Test
  public void testGetMiddleDateTime() {
    Period december = createDecember();
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_DEC_MIDDLE, december.getMiddleDateTime());
  }

}
