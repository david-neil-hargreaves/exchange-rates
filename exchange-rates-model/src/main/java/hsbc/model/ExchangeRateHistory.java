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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents an historical exchange rate between two currencies, between two date / times.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
    columnNames = {"BUYING_CURRENCY_ID", "SELLING_CURRENCY_ID", "START_DATE_TIME"})})
@Data
public class ExchangeRateHistory implements Comparable<ExchangeRateHistory> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BUYING_CURRENCY_ID")
  private Currency buyingCurrency;

  @ManyToOne
  @JoinColumn(name = "SELLING_CURRENCY_ID")
  private Currency sellingCurrency;

  @Column(name = "START_DATE_TIME")
  private Date startDateTime;

  @Column(name = "END_DATE_TIME")
  private Date endDateTime;

  @Column
  private BigDecimal rate;

  /**
   * Compares two exchange rate histories. These are sorted firstly by buying currency, secondly by
   * selling currency and lastly by start date / time.
   * 
   * @param other the other exchange rate history for use in the comparison.
   * @return
   *         <li>-1 if this exchange rate history should appear before the other when sorting.
   *         <li>1 if this exchange rate history should appear after the other when sorting.
   *         <li>0 if the order of the exchange rate histories when sorting is indeterminate.
   */
  @Override
  public int compareTo(ExchangeRateHistory other) {
    if (this.getBuyingCurrency().compareTo(other.getBuyingCurrency()) != 0) {
      return this.getBuyingCurrency().compareTo(other.getBuyingCurrency());
    }
    if (this.getSellingCurrency().compareTo(other.getSellingCurrency()) != 0) {
      return this.getSellingCurrency().compareTo(other.getSellingCurrency());
    }
    return this.getStartDateTime().compareTo(other.getStartDateTime());
  }
}
