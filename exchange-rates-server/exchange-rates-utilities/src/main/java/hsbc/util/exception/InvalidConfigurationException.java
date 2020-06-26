package hsbc.util.exception;

/**
 * Represents an invalid configuration exception.
 */
public class InvalidConfigurationException extends ServiceException {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_INVALID_CONFIGURATION_NO_DEFAULT_SUBJECT_CURRENCY =
      "Invalid system configuration - no default subject currency set up.";
  public static final String MESSAGE_INVALID_CONFIGURATION_MULTIPLE_DEFAULT_SUBJECT_CURRENCIES =
      "Invalid system configuration - expecting 1 default subject currency but found %s.";
  public static final String MESSAGE_INVALID_CONFIGURATION_ONLY_ONE_CURRENCY =
      "Invalid system configuration - only one currency set up.";
  public static final String MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED = "Current %s not configured.";

  /**
   * Constructs an invalid configuration exception with the given message.
   * 
   * @param message The message for the invalid configuration exception.
   */
  public InvalidConfigurationException(String message) {
    super(message);
  }

}
