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
 * Represents historical exchange rates for a subject currency to multiple other currencies for a
 * list of periods.
 */
@Data
public class HistoricalExchangeRates {

  private Currency subjectCurrency;

  private List<Currency> otherCurrencies;

  private List<Period> periods;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<OtherCurrencyPeriod, Optional<BigDecimal>> exchangeRates =
      new TreeMap<OtherCurrencyPeriod, Optional<BigDecimal>>();

  /**
   * Constructs a HistoricalExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   * @param periods The periods.
   */
  public HistoricalExchangeRates(Currency subjectCurrency, List<Currency> otherCurrencies,
      List<Period> periods) {
    this.subjectCurrency = subjectCurrency;
    this.otherCurrencies = otherCurrencies;
    this.periods = periods;
  }


  /**
   * Sets the exchange rate from the subject currency to the other currency for the period.
   *
   * @param otherCurrency The other currency.
   * @param period The period.
   * @param rate The exchange rate.
   */
  public void setExchangeRate(Currency otherCurrency, Period period, Optional<BigDecimal> rate) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    exchangeRates.put(otherCurrencyPeriod, rate);
  }

  /**
   * Returns the exchange rate from the subject currency to the other currency for the period.
   * 
   * @param otherCurrency The other currency.
   * @param period The period.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getExchangeRate(Currency otherCurrency, Period period) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    if (exchangeRates.containsKey(otherCurrencyPeriod)) {
      return exchangeRates.get(otherCurrencyPeriod);
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
    for (Currency otherCurrency : otherCurrencies) {
      HistoricalExchangeRatesCurrency historicalExchangeRatesCurrency =
          new HistoricalExchangeRatesCurrency(otherCurrency);
      historicalExchangeRatesCurrencies.add(historicalExchangeRatesCurrency);
      for (Period period : periods) {
        OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
        if (exchangeRates.containsKey(otherCurrencyPeriod)) {
          historicalExchangeRatesCurrency.addRate(exchangeRates.get(otherCurrencyPeriod));
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
  private class OtherCurrencyPeriod implements Comparable<OtherCurrencyPeriod> {

    private Currency otherCurrency;

    private Period period;

    @Override
    public int compareTo(OtherCurrencyPeriod other) {
      if (this.getOtherCurrency().compareTo(other.getOtherCurrency()) != 0) {
        return this.getOtherCurrency().compareTo(other.getOtherCurrency());
      }
      return this.getPeriod().compareTo(other.getPeriod());
    }

  }

}
