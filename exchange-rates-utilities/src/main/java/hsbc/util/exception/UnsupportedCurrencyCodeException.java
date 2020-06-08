
package hsbc.util.exception;

/**
 * Represents an unsupported currency code.
 */
public class UnsupportedCurrencyCodeException extends ValidationException {

  private static final long serialVersionUID = 1L;

  public static final String MESSAGE_UNKNOWN_CURRENCY_CODE = "Currency code %s not supported.";

  /**
   * Constructs a unsupported currency code exception with the given currency code.
   * 
   * @param currencyCode the currency code.
   */
  public UnsupportedCurrencyCodeException(String currencyCode) {
    super(String.format(MESSAGE_UNKNOWN_CURRENCY_CODE, currencyCode));
  }

}
