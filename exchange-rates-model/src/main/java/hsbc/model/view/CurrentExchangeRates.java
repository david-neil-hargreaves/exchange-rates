package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents current exchange rates for a subject currency to multiple comparison currencies.
 */
@Data
public class CurrentExchangeRates {

  private Currency subjectCurrency;

  private List<Currency> comparisonCurrencies;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<Currency, Optional<BigDecimal>> exchangeRates =
      new TreeMap<Currency, Optional<BigDecimal>>();

  /**
   * Constructs a CurrentExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param comparisonCurrencies The comparison currencies.
   */
  public CurrentExchangeRates(Currency subjectCurrency, List<Currency> comparisonCurrencies) {
    this.subjectCurrency = subjectCurrency;
    this.comparisonCurrencies = comparisonCurrencies;
  }

  /**
   * Sets the exchange rate from the subject currency to the comparison currency.
   * 
   * @param comparisonCurrency The comparison currency.
   * @param rate The exchange rate.
   */
  public void setExchangeRate(Currency comparisonCurrency, Optional<BigDecimal> rate) {
    exchangeRates.put(comparisonCurrency, rate);
  }

  /**
   * Returns the exchange rate from the subject currency to the comparison currency.
   * 
   * @param comparisonCurrency The comparison currency.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getExchangeRate(Currency comparisonCurrency) {
    if (exchangeRates.containsKey(comparisonCurrency)) {
      return exchangeRates.get(comparisonCurrency);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Returns the current exchange rates.
   * 
   * @return The current exchange rates.
   */
  public List<CurrentExchangeRate> getCurrentExchangeRates() {
    List<CurrentExchangeRate> currentExchangeRates = new ArrayList<>();
    for (Currency comparisonCurrency : comparisonCurrencies) {
      if (exchangeRates.containsKey(comparisonCurrency)) {
        currentExchangeRates.add(
            new CurrentExchangeRate(comparisonCurrency, exchangeRates.get(comparisonCurrency)));
      } else {
        currentExchangeRates.add(new CurrentExchangeRate(comparisonCurrency, Optional.empty()));
      }
    }
    Collections.sort(currentExchangeRates);
    return currentExchangeRates;
  }

}
