package hsbc.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents an exchange rate between two currencies.
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"BUYING_CURRENCY_ID", "SELLING_CURRENCY_ID"})})
@Data
public class ExchangeRate implements Comparable<ExchangeRate> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BUYING_CURRENCY_ID")
  @NotNull
  private Currency buyingCurrency;

  @ManyToOne
  @JoinColumn(name = "SELLING_CURRENCY_ID")
  @NotNull
  private Currency sellingCurrency;

  @Column(name = "START_DATE_TIME")
  @NotNull
  private Date startDateTime;

  @Column
  @NotNull
  private BigDecimal rate;

  /**
   * Compares two exchange rates. These are sorted firstly by buying currency and secondly by
   * selling currency.
   * 
   * @param other the other exchange rate for use in the comparison.
   * @return
   *         <li>-1 if this exchange rate should appear before the other when sorting.
   *         <li>1 if this exchange rate should appear after the other when sorting.
   *         <li>0 if the order of the exchange rates when sorting is indeterminate.
   */
  @Override
  public int compareTo(ExchangeRate other) {
    if (this.getBuyingCurrency().compareTo(other.getBuyingCurrency()) != 0) {
      return this.getBuyingCurrency().compareTo(other.getBuyingCurrency());
    }
    return this.getSellingCurrency().compareTo(other.getSellingCurrency());
  }
}
