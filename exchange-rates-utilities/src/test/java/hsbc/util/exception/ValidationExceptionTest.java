
package hsbc.util.exception;

import static hsbc.util.exception.ValidationException.MESSAGE_SUBJECT_CURRENCY_REQUIRED;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidationExceptionTest {

  private static final String ATTRIBUTE_EXCEPTION_MESSAGE = "Exception message";
  private static final String ATTRIBUTE_EXCEPTION_CAUSE = "Exception cause";

  @Test
  public void testMessage() {
    ValidationException exception = new ValidationException(MESSAGE_SUBJECT_CURRENCY_REQUIRED);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, MESSAGE_SUBJECT_CURRENCY_REQUIRED,
        exception.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, null, exception.getCause());
  }

}
