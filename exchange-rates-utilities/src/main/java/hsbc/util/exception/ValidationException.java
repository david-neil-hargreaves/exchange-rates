
package hsbc.util.exception;

/**
 * Represents a validation exception.
 */
public class ValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_BUYING_CURRENCY_REQUIRED = "Buying currency code is required.";
  public static final String MESSAGE_SELLING_CURRENCIES_REQUIRED =
      "Selling currency codes are required.";
  public static final String MESSAGE_DUPLICATE_SELLING_CURRENCY =
      "Duplicate selling currency code %s entered.";
  public static final String MESSAGE_SELLING_CURRENCY_CANNOT_BE_SAME_AS_BUYING_CURRENCY =
      "Selling currency code cannot be the same as the buying currency code.";

  /**
   * Constructs a validation exception with the given message.
   * 
   * @param message the message for the validation exception.
   */
  public ValidationException(String message) {
    super(message);
  }

}
