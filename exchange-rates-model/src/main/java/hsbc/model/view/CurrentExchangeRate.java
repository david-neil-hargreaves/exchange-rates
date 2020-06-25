package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a current exchange rate for a subject currency (implied) to the other currency.
 */
@Data
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class CurrentExchangeRate implements Comparable<CurrentExchangeRate> {

  private Currency otherCurrency;
  private Optional<BigDecimal> rate;

  /**
   * Compares two CurrentExchangeRates. These are sorted by the other currency.
   * 
   * @param other The other CurrentExchangeRate for use in the comparison.
   * @return
   *         <li>-1 if this CurrentExchangeRate should appear before the other when sorting.
   *         <li>1 if this CurrentExchangeRate should appear after the other when sorting.
   *         <li>0 if the order of the CurrentExchangeRates when sorting is indeterminate.
   */
  @Override
  public int compareTo(CurrentExchangeRate other) {
    return this.getOtherCurrency().compareTo(other.getOtherCurrency());
  }

}
