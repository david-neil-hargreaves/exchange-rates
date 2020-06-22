
package hsbc.util.exception;

import static hsbc.test.TestData.*;
import static hsbc.util.exception.UnsupportedCurrencyException.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnsupportedCurrencyCodeExceptionTest {

  private static final String ATTRIBUTE_EXCEPTION_MESSAGE = "Exception message";
  private static final String ATTRIBUTE_EXCEPTION_CAUSE = "Exception cause";

  @Test
  public void testGetMessageString() {
    UnsupportedCurrencyException exception =
        new UnsupportedCurrencyException(CURRENCY_CODE_INVALID);
    String expectedMessage = String.format(MESSAGE_UNKNOWN_CURRENCY_CODE, CURRENCY_CODE_INVALID);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, expectedMessage, exception.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, null, exception.getCause());
  }

  @Test
  public void testGetMessageLong() {
    UnsupportedCurrencyException exception = new UnsupportedCurrencyException(CURRENCY_ID_INVALID);
    String expectedMessage = String.format(MESSAGE_UNKNOWN_CURRENCY_ID, CURRENCY_ID_INVALID);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, expectedMessage, exception.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, null, exception.getCause());
  }

}
