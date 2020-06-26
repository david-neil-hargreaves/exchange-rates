
package hsbc.util.exception;

/**
 * Represents a service exception.
 */
public class ServiceException extends Exception {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_FOR_END_USER =
      "The system is currently unavailable.  Please try again later.";

  /**
   * Constructs a service exception with the exception cause.
   * 
   * @param cause The cause of the service exception.
   */
  public ServiceException(Exception cause) {
    super(cause);
  }

  /**
   * Constructs a service exception with the given message.
   * 
   * @param message The message for the service exception.
   */
  public ServiceException(String message) {
    super(message);
  }

  /**
   * Constructs a service exception with the given message and exception cause.
   * 
   * @param message The message for the service exception.
   * @param cause The cause of the service exception.
   */
  public ServiceException(String message, Exception cause) {
    super(message, cause);
  }

}
