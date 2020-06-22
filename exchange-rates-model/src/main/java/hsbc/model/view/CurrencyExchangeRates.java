package hsbc.model.view;

import hsbc.model.Currency;
import hsbc.model.Period;
import java.math.BigDecimal;
import java.util.ArrayList;
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
 * Represents buying and selling exchange rates for a subject currency to multiple other currencies.
 */
@Data
@Deprecated
public class CurrencyExchangeRates {

  private Currency subjectCurrency;

  private List<Currency> otherCurrencies;

  private List<Period> periods;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<Currency, Optional<BigDecimal>> currentBuyingExchangeRates =
      new TreeMap<Currency, Optional<BigDecimal>>();

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<OtherCurrencyPeriod, Optional<BigDecimal>> historicalBuyingExchangeRates =
      new TreeMap<OtherCurrencyPeriod, Optional<BigDecimal>>();

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<Currency, Optional<BigDecimal>> currentSellingExchangeRates =
      new TreeMap<Currency, Optional<BigDecimal>>();

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<OtherCurrencyPeriod, Optional<BigDecimal>> historicalSellingExchangeRates =
      new TreeMap<OtherCurrencyPeriod, Optional<BigDecimal>>();


  /**
   * Constructs a CurrencyExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   * @param periods The periods.
   */
  public CurrencyExchangeRates(Currency subjectCurrency, List<Currency> otherCurrencies,
      List<Period> periods) {
    this.subjectCurrency = subjectCurrency;
    this.otherCurrencies = otherCurrencies;
    this.periods = periods;
  }

  /**
   * Constructs a CurrencyExchangeRates.
   * 
   * @param subjectCurrency The subject currency.
   * @param otherCurrencies The other currencies.
   */
  public CurrencyExchangeRates(Currency subjectCurrency, List<Currency> otherCurrencies) {
    this.subjectCurrency = subjectCurrency;
    this.otherCurrencies = otherCurrencies;
  }

  /**
   * Sets the current buying exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @param rate The exchange rate.
   */
  public void setCurrentBuyingExchangeRate(Currency otherCurrency, Optional<BigDecimal> rate) {
    currentBuyingExchangeRates.put(otherCurrency, rate);
  }

  /**
   * Returns the current buying exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getCurrentBuyingExchangeRate(Currency otherCurrency) {
    if (currentBuyingExchangeRates.containsKey(otherCurrency)) {
      return currentBuyingExchangeRates.get(otherCurrency);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Sets the buying exchange rate from the subject currency to the other currency for the period.
   *
   * @param otherCurrency The other currency.
   * @param period The period.
   * @param rate The exchange rate.
   */
  public void setHistoricalBuyingExchangeRate(Currency otherCurrency, Period period,
      Optional<BigDecimal> rate) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    historicalBuyingExchangeRates.put(otherCurrencyPeriod, rate);
  }

  /**
   * Returns the buying exchange rate from the subject currency to the other currency for the
   * period.
   * 
   * @param otherCurrency The other currency.
   * @param period The period.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getHistoricalBuyingExchangeRate(Currency otherCurrency,
      Period period) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    if (historicalBuyingExchangeRates.containsKey(otherCurrencyPeriod)) {
      return historicalBuyingExchangeRates.get(otherCurrencyPeriod);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Sets the current selling exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @param rate The exchange rate.
   */
  public void setCurrentSellingExchangeRate(Currency otherCurrency, Optional<BigDecimal> rate) {
    currentSellingExchangeRates.put(otherCurrency, rate);
  }

  /**
   * Returns the current selling exchange rate from the subject currency to the other currency.
   * 
   * @param otherCurrency The other currency.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getCurrentSellingExchangeRate(Currency otherCurrency) {
    if (currentSellingExchangeRates.containsKey(otherCurrency)) {
      return currentSellingExchangeRates.get(otherCurrency);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Sets the selling exchange rate from the subject currency to the other currency for the period.
   *
   * @param otherCurrency The other currency.
   * @param period The period.
   * @param rate The exchange rate.
   */
  public void setHistoricalSellingExchangeRate(Currency otherCurrency, Period period,
      Optional<BigDecimal> rate) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    historicalSellingExchangeRates.put(otherCurrencyPeriod, rate);
  }

  /**
   * Returns the selling exchange rate from the subject currency to the other currency for the
   * period.
   * 
   * @param otherCurrency The other currency.
   * @param period The period.
   * @return The exchange rate.
   */
  public Optional<BigDecimal> getHistoricalSellingExchangeRate(Currency otherCurrency,
      Period period) {
    OtherCurrencyPeriod otherCurrencyPeriod = new OtherCurrencyPeriod(otherCurrency, period);
    if (historicalSellingExchangeRates.containsKey(otherCurrencyPeriod)) {
      return historicalSellingExchangeRates.get(otherCurrencyPeriod);
    } else {
      return Optional.empty();
    }
  }

  public List<ExchangeRateView> getCurrentBuyingExchangeRates() {
    List<ExchangeRateView> exchangeRateViews = new ArrayList<>();
    for (Currency otherCurrency : currentBuyingExchangeRates.keySet()) {
      exchangeRateViews
          .add(new ExchangeRateView(otherCurrency, currentBuyingExchangeRates.get(otherCurrency)));
    }
    return exchangeRateViews;
  }

  public List<ExchangeRateView> getCurrentSellingExchangeRates() {
    List<ExchangeRateView> exchangeRateViews = new ArrayList<>();
    for (Currency otherCurrency : currentSellingExchangeRates.keySet()) {
      exchangeRateViews
          .add(new ExchangeRateView(otherCurrency, currentSellingExchangeRates.get(otherCurrency)));
    }
    return exchangeRateViews;
  }

  public List<ExchangeRateHistoryView> getHistoricalBuyingExchangeRates() {
    List<ExchangeRateHistoryView> exchangeRateHistoryViews = new ArrayList<>();
    Currency previousCurrency = null;
    ExchangeRateHistoryView exchangeRateHistoryView = null;
    for (OtherCurrencyPeriod otherCurrencyPeriod : historicalBuyingExchangeRates.keySet()) {
      Currency otherCurrency = otherCurrencyPeriod.getOtherCurrency();
      if ((previousCurrency == null) || (!(otherCurrency.equals(previousCurrency)))) {
        exchangeRateHistoryView = new ExchangeRateHistoryView(otherCurrency);
        exchangeRateHistoryViews.add(exchangeRateHistoryView);
        previousCurrency = otherCurrency;
      }
      exchangeRateHistoryView.addRate(historicalBuyingExchangeRates.get(otherCurrencyPeriod));
    }
    return exchangeRateHistoryViews;
  }
  
  public List<ExchangeRateHistoryView> getHistoricalSellingExchangeRates() {
    List<ExchangeRateHistoryView> exchangeRateHistoryViews = new ArrayList<>();
    Currency previousCurrency = null;
    ExchangeRateHistoryView exchangeRateHistoryView = null;
    for (OtherCurrencyPeriod otherCurrencyPeriod : historicalSellingExchangeRates.keySet()) {
      Currency otherCurrency = otherCurrencyPeriod.getOtherCurrency();
      if ((previousCurrency == null) || (!(otherCurrency.equals(previousCurrency)))) {
        exchangeRateHistoryView = new ExchangeRateHistoryView(otherCurrency);
        exchangeRateHistoryViews.add(exchangeRateHistoryView);
        previousCurrency = otherCurrency;
      }
      exchangeRateHistoryView.addRate(historicalSellingExchangeRates.get(otherCurrencyPeriod));
    }
    return exchangeRateHistoryViews;
  }


  @Data
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public class OtherCurrencyPeriod implements Comparable<OtherCurrencyPeriod> {

    private Currency otherCurrency;

    private Period period;

    @Override
    public int compareTo(OtherCurrencyPeriod other) {
      if (this.getOtherCurrency().compareTo(other.otherCurrency) != 0) {
        return this.getOtherCurrency().compareTo(other.otherCurrency);
      }
      return this.getPeriod().compareTo(other.getPeriod());
    }

  }

  @Data
  @AllArgsConstructor
  private class ExchangeRateView {
    private Currency otherCurrency;
    private Optional<BigDecimal> rate;
  }

  @Data
  private class ExchangeRateHistoryView {
    private Currency otherCurrency;
    private List<Optional<BigDecimal>> rates = new ArrayList<>();
    
    ExchangeRateHistoryView(Currency otherCurrency) {
      super();
      this.otherCurrency = otherCurrency;
    }

    void addRate(Optional<BigDecimal> rate) {
      rates.add(rate);
    }

  }

}
