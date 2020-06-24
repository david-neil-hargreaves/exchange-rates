
package hsbc.util.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InvalidConfigurationExceptionTest {

  private static final String ATTRIBUTE_EXCEPTION_MESSAGE = "Exception message";
  private static final String ATTRIBUTE_EXCEPTION_CAUSE = "Exception cause";
  private static final String SERVICE_EXCEPTION_MESSAGE = "Service exception";

  @Test
  public void testMessage() {
    InvalidConfigurationException invalidConfigurationException =
        new InvalidConfigurationException(SERVICE_EXCEPTION_MESSAGE);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, SERVICE_EXCEPTION_MESSAGE,
        invalidConfigurationException.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, null, invalidConfigurationException.getCause());
  }

}
