package hsbc.util.exception;

public class InvalidConfigurationException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an invalid configuration exception with the given message.
   * 
   * @param message the message for the invalid configuration exception.
   */
  public InvalidConfigurationException(String message) {
    super(message);
  }

}
