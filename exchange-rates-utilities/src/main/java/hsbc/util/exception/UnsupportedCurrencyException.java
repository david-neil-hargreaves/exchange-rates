
package hsbc.util.exception;

/**
 * Represents an unsupported currency code.
 */
public class UnsupportedCurrencyException extends ValidationException {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_UNKNOWN_CURRENCY_CODE = "Currency code %s not supported.";

  public static final String MESSAGE_UNKNOWN_CURRENCY_ID = "Unknown currency with i.d. %s.";

  /**
   * Constructs a unsupported currency exception with the given currency code.
   * 
   * @param currencyCode The currency code.
   */
  public UnsupportedCurrencyException(String currencyCode) {
    super(String.format(MESSAGE_UNKNOWN_CURRENCY_CODE, currencyCode));
  }

  /**
   * Constructs a unsupported currency exception with the given currency i.d.
   * 
   * @param currencyId The currency i.d.
   */
  public UnsupportedCurrencyException(Long currencyId) {
    super(String.format(MESSAGE_UNKNOWN_CURRENCY_ID, currencyId));
  }

}
