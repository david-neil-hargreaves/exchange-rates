package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;

/**
 * Represents historical exchange rates for a subject currency (implied) to the comparison currency
 * for a list of periods (implied).
 */
@Data
class HistoricalExchangeRatesCurrency implements Comparable<HistoricalExchangeRatesCurrency> {

  private Currency comparisonCurrency;
  private List<Optional<BigDecimal>> rates = new ArrayList<>();

  HistoricalExchangeRatesCurrency(Currency comparisonCurrency) {
    super();
    this.comparisonCurrency = comparisonCurrency;
  }

  void addRate(Optional<BigDecimal> rate) {
    rates.add(rate);
  }

  /**
   * Compares two HistoricalExchangeRatesCurrency instances. These are sorted by the comparison
   * currency.
   * 
   * @param comparison The comparison HistoricalExchangeRatesCurrency for use in the comparison.
   * @return
   *         <li>-1 if this HistoricalExchangeRatesCurrency should appear before the comparison when
   *         sorting.
   *         <li>1 if this HistoricalExchangeRatesCurrency should appear after the comparison when
   *         sorting.
   *         <li>0 if the order of the HistoricalExchangeRatesCurrency instances when sorting is
   *         indeterminate.
   */
  @Override
  public int compareTo(HistoricalExchangeRatesCurrency comparison) {
    return this.getComparisonCurrency().compareTo(comparison.getComparisonCurrency());
  }

}
