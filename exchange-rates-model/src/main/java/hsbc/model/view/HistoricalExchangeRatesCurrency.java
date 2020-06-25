package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;

/**
 * Represents historical exchange rates for a subject currency (implied) to the other currency for a
 * list of periods (implied).
 */
@Data
class HistoricalExchangeRatesCurrency
    implements Comparable<HistoricalExchangeRatesCurrency> {

  private Currency otherCurrency;
  private List<Optional<BigDecimal>> rates = new ArrayList<>();

  HistoricalExchangeRatesCurrency(Currency otherCurrency) {
    super();
    this.otherCurrency = otherCurrency;
  }

  void addRate(Optional<BigDecimal> rate) {
    rates.add(rate);
  }

  /**
   * Compares two HistoricalExchangeRatesCurrency instances. These are sorted by the other currency.
   * 
   * @param other The other HistoricalExchangeRatesCurrency for use in the comparison.
   * @return
   *         <li>-1 if this HistoricalExchangeRatesCurrency should appear before the other when
   *         sorting.
   *         <li>1 if this HistoricalExchangeRatesCurrency should appear after the other when
   *         sorting.
   *         <li>0 if the order of the HistoricalExchangeRatesCurrency instances when sorting is
   *         indeterminate.
   */
  @Override
  public int compareTo(HistoricalExchangeRatesCurrency other) {
    return this.getOtherCurrency().compareTo(other.getOtherCurrency());
  }

}
