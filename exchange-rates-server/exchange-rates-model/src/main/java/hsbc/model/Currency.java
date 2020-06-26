
package hsbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a currency.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"CODE"})})
@Data
@NoArgsConstructor
public class Currency implements Comparable<Currency> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  @NotNull
  private Long id;

  @Column
  @NotNull
  private String code;

  @Column
  @NotNull
  private String description;

  @Column
  @NotNull
  @JsonIgnore
  private Integer sortOrderNumber;

  @Column
  @NotNull
  @JsonIgnore
  private boolean defaultSubjectCurrency;

  @Column
  @NotNull
  @JsonIgnore
  private boolean defaultComparisonCurrency;

  /**
   * Compares two currencies for use when sorting currencies in a logical sort order. Currencies are
   * sorted firstly by sort order number and secondly by code.
   * 
   * @param other The other currency for use in the comparison.
   * @return
   *         <li>-1 if the other currency has a higher sort order number / code.
   *         <li>1 if the other currency has a lower sort order number / code.
   *         <li>0 if the other currency has an equal sort order number / code.
   */
  @Override
  public int compareTo(Currency other) {
    if (this.getSortOrderNumber().compareTo(other.getSortOrderNumber()) != 0) {
      return this.getSortOrderNumber().compareTo(other.getSortOrderNumber());
    }
    return this.getCode().compareTo(other.getCode());
  }

}
