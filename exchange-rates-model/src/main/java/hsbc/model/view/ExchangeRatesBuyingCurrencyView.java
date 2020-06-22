package hsbc.model.view;

import hsbc.model.Currency;
import hsbc.model.Period;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Represents exchange rates from a buying currency to multiple selling currencies.
 */
@Data
@Deprecated
public class ExchangeRatesBuyingCurrencyView {

  private Currency buyingCurrency;

  private List<Currency> sellingCurrencies;

  private List<Period> periods;

  @Setter(AccessLevel.NONE)
  private Map<Currency, Optional<BigDecimal>> currentExchangeRates =
      new TreeMap<Currency, Optional<BigDecimal>>();

  @Setter(AccessLevel.NONE)
  private Map<SellingCurrencyPeriod, Optional<BigDecimal>> historicalExchangeRates =
      new TreeMap<SellingCurrencyPeriod, Optional<BigDecimal>>();

  /**
   * Constructs a ExchangeRatesBuyingCurrencyView.
   * 
   * @param buyingCurrency The buying currency.
   * @param sellingCurrencies The selling currency.
   * @param periods The periods.
   */
  public ExchangeRatesBuyingCurrencyView(Currency buyingCurrency, List<Currency> sellingCurrencies,
      List<Period> periods) {
    this.buyingCurrency = buyingCurrency;
    this.sellingCurrencies = sellingCurrencies;
    this.periods = periods;
  }

  /**
   * Sets the current exchange rate from the buying currency to the selling currency.
   * 
   * @param sellingCurrency The selling currency.
   * @param rate The exchange rate.
   */
  public void setCurrentExchangeRate(Currency sellingCurrency, Optional<BigDecimal> rate) {
    currentExchangeRates.put(sellingCurrency, rate);
  }

  /**
   * Returns the current exchange rate from the buying currency to the selling currency.
   * 
   * @param sellingCurrency The selling currency.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getCurrentExchangeRate(Currency sellingCurrency) {
    if (currentExchangeRates.containsKey(sellingCurrency)) {
      return currentExchangeRates.get(sellingCurrency);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Sets the exchange rate from the buying currency to the selling currency for the period.
   *
   * @param sellingCurrency The selling currency.
   * @param period The period.
   * @param rate The exchange rate.
   */
  public void setHistoricalExchangeRate(Currency sellingCurrency, Period period,
      Optional<BigDecimal> rate) {
    SellingCurrencyPeriod sellingCurrencyPeriod =
        new SellingCurrencyPeriod(sellingCurrency, period);
    historicalExchangeRates.put(sellingCurrencyPeriod, rate);
  }

  /**
   * Returns the exchange rate from the buying currency to the selling currency for the period.
   * 
   * @param sellingCurrency The selling currency.
   * @param period The period.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getHistoricalExchangeRate(Currency sellingCurrency, Period period) {
    SellingCurrencyPeriod sellingCurrencyPeriod =
        new SellingCurrencyPeriod(sellingCurrency, period);
    if (historicalExchangeRates.containsKey(sellingCurrencyPeriod)) {
      return historicalExchangeRates.get(sellingCurrencyPeriod);
    } else {
      return Optional.empty();
    }
  }

  @Data
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public class SellingCurrencyPeriod implements Comparable<SellingCurrencyPeriod> {

    private Currency sellingCurrency;

    private Period period;

    @Override
    public int compareTo(SellingCurrencyPeriod other) {
      if (this.getSellingCurrency().compareTo(other.sellingCurrency) != 0) {
        return this.getSellingCurrency().compareTo(other.sellingCurrency);
      }
      return this.getPeriod().compareTo(other.getPeriod());
    }

  }

}
