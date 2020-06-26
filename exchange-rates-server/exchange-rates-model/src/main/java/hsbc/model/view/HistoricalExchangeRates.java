package hsbc.model.view;

import hsbc.model.Currency;
import hsbc.model.Period;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents historical exchange rates for a subject currency to multiple comparison currencies for
 * a list of periods.
 */
@Data
public class HistoricalExchangeRates {

  private Currency subjectCurrency;

  private List<Currency> comparisonCurrencies;

  private List<Period> periods;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<ComparisonCurrencyPeriod, Optional<BigDecimal>> exchangeRates =
      new TreeMap<ComparisonCurrencyPeriod, Optional<BigDecimal>>();

  /**
   * Constructs a HistoricalExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param comparisonCurrencies The comparison currencies.
   * @param periods The periods.
   */
  public HistoricalExchangeRates(Currency subjectCurrency, List<Currency> comparisonCurrencies,
      List<Period> periods) {
    this.subjectCurrency = subjectCurrency;
    this.comparisonCurrencies = comparisonCurrencies;
    this.periods = periods;
  }


  /**
   * Sets the exchange rate from the subject currency to the comparison currency for the period.
   *
   * @param comparisonCurrency The comparison currency.
   * @param period The period.
   * @param rate The exchange rate.
   */
  public void setExchangeRate(Currency comparisonCurrency, Period period,
      Optional<BigDecimal> rate) {
    ComparisonCurrencyPeriod comparisonCurrencyPeriod =
        new ComparisonCurrencyPeriod(comparisonCurrency, period);
    exchangeRates.put(comparisonCurrencyPeriod, rate);
  }

  /**
   * Returns the exchange rate from the subject currency to the comparison currency for the period.
   * 
   * @param comparisonCurrency The comparison currency.
   * @param period The period.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getExchangeRate(Currency comparisonCurrency, Period period) {
    ComparisonCurrencyPeriod comparisonCurrencyPeriod =
        new ComparisonCurrencyPeriod(comparisonCurrency, period);
    if (exchangeRates.containsKey(comparisonCurrencyPeriod)) {
      return exchangeRates.get(comparisonCurrencyPeriod);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Returns the historical exchange rates.
   * 
   * @return The historical exchange rates.
   */
  public List<HistoricalExchangeRatesCurrency> getHistoricalExchangeRates() {
    List<HistoricalExchangeRatesCurrency> historicalExchangeRatesCurrencies = new ArrayList<>();
    for (Currency comparisonCurrency : comparisonCurrencies) {
      HistoricalExchangeRatesCurrency historicalExchangeRatesCurrency =
          new HistoricalExchangeRatesCurrency(comparisonCurrency);
      historicalExchangeRatesCurrencies.add(historicalExchangeRatesCurrency);
      for (Period period : periods) {
        ComparisonCurrencyPeriod comparisonCurrencyPeriod =
            new ComparisonCurrencyPeriod(comparisonCurrency, period);
        if (exchangeRates.containsKey(comparisonCurrencyPeriod)) {
          historicalExchangeRatesCurrency.addRate(exchangeRates.get(comparisonCurrencyPeriod));
        } else {
          historicalExchangeRatesCurrency.addRate(Optional.empty());
        }
      }
    }
    Collections.sort(historicalExchangeRatesCurrencies);
    return historicalExchangeRatesCurrencies;
  }

  @Data
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  private class ComparisonCurrencyPeriod implements Comparable<ComparisonCurrencyPeriod> {

    private Currency comparisonCurrency;

    private Period period;

    @Override
    public int compareTo(ComparisonCurrencyPeriod comparison) {
      if (this.getComparisonCurrency().compareTo(comparison.getComparisonCurrency()) != 0) {
        return this.getComparisonCurrency().compareTo(comparison.getComparisonCurrency());
      }
      return this.getPeriod().compareTo(comparison.getPeriod());
    }

  }

}
