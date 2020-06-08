
package hsbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
  private Long id;

  @Column
  private String code;

  @Column
  private String description;

  @Column
  private Integer sortOrderNumber;

  /**
   * Compares two currencies for use when sorting currencies in a logical sort order. Popular
   * currencies should appear before less popular currencies.
   * 
   * @param other the other currency for use in the comparison.
   * @return
   *         <li>-1 if the other currency has a higher sort order number.
   *         <li>1 if the other currency has a lower sort order number.
   *         <li>0 if the other currency has an equal sort order number.
   */
  @Override
  public int compareTo(Currency other) {
    return this.sortOrderNumber.compareTo(other.sortOrderNumber);
  }

}
