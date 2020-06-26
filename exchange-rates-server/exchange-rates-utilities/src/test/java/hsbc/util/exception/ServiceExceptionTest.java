
package hsbc.util.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ServiceExceptionTest {

  private static final String ATTRIBUTE_EXCEPTION_MESSAGE = "Exception message";
  private static final String ATTRIBUTE_EXCEPTION_CAUSE = "Exception cause";
  private static final String RUNTIME_EXCEPTION_MESSAGE = "Runtime exception";
  private static final String SERVICE_EXCEPTION_MESSAGE = "Service exception";
  private static final String EXCEPTION_MESSAGE_FORMAT = "%s: %s";

  @Test
  public void testCause() {
    RuntimeException runtimeException = new RuntimeException(RUNTIME_EXCEPTION_MESSAGE);
    ServiceException serviceException = new ServiceException(runtimeException);
    String expectedMessage = String.format(EXCEPTION_MESSAGE_FORMAT,
        runtimeException.getClass().getName(), runtimeException.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, expectedMessage, serviceException.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, runtimeException, serviceException.getCause());
  }

  @Test
  public void testMessage() {
    ServiceException serviceException = new ServiceException(SERVICE_EXCEPTION_MESSAGE);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, SERVICE_EXCEPTION_MESSAGE,
        serviceException.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, null, serviceException.getCause());
  }

  @Test
  public void testMessageCause() {
    RuntimeException runtimeException = new RuntimeException(RUNTIME_EXCEPTION_MESSAGE);
    ServiceException serviceException =
        new ServiceException(SERVICE_EXCEPTION_MESSAGE, runtimeException);
    assertEquals(ATTRIBUTE_EXCEPTION_MESSAGE, SERVICE_EXCEPTION_MESSAGE,
        serviceException.getMessage());
    assertEquals(ATTRIBUTE_EXCEPTION_CAUSE, runtimeException, serviceException.getCause());
  }
}
