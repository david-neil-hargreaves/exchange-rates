package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents current exchange rates for a subject currency to multiple other currencies.
 */
@Data
public class CurrentExchangeRates {

  private Currency subjectCurrency;

  private List<Currency> otherCurrencies;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<Currency, Optional<BigDecimal>> exchangeRates =
      new TreeMap<Currency, Optional<BigDecimal>>();

  /**
   * Constructs a CurrentExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   */
  public CurrentExchangeRates(Currency subjectCurrency, List<Currency> otherCurrencies) {
    this.subjectCurrency = subjectCurrency;
    this.otherCurrencies = otherCurrencies;
  }

  /**
   * Sets the exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @param rate The exchange rate.
   */
  public void setExchangeRate(Currency otherCurrency, Optional<BigDecimal> rate) {
    exchangeRates.put(otherCurrency, rate);
  }

  /**
   * Returns the exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getExchangeRate(Currency otherCurrency) {
    if (exchangeRates.containsKey(otherCurrency)) {
      return exchangeRates.get(otherCurrency);
    } else {
      return Optional.empty();
    }
  }

  public List<CurrentExchangeRate> getCurrentExchangeRates() {
    List<CurrentExchangeRate> currentExchangeRates = new ArrayList<>();
    for (Currency otherCurrency : otherCurrencies) {
      if (exchangeRates.containsKey(otherCurrency)) {
        currentExchangeRates
            .add(new CurrentExchangeRate(otherCurrency, exchangeRates.get(otherCurrency)));
      } else {
        currentExchangeRates.add(new CurrentExchangeRate(otherCurrency, Optional.empty()));
      }
    }
    return currentExchangeRates;
  }

}
