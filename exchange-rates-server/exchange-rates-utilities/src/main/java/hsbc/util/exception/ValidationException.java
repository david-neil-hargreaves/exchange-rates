
package hsbc.util.exception;

/**
 * Represents a validation exception.
 */
public class ValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_SUBJECT_CURRENCY_REQUIRED = "%s currency is required.";
  public static final String MESSAGE_COMPARISON_CURRENCIES_REQUIRED = "%s currencies are required.";
  public static final String MESSAGE_DUPLICATE_COMPARISON_CURRENCY = "Duplicate %s currency %s.";
  public static final String MESSAGE_COMPARISON_CURRENCY_CANNOT_BE_SAME_AS_SUBJECT_CURRENCY =
      "%s currency cannot be the same as the %s currency.";
  public static final String MESSAGE_PERIODS_REQUIRED = "Periods are required.";

  /**
   * Constructs a validation exception with the given message.
   * 
   * @param message The message for the validation exception.
   */
  public ValidationException(String message) {
    super(message);
  }

}
