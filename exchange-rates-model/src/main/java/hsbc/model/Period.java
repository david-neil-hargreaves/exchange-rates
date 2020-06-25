package hsbc.model;

import hsbc.util.DateUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * Represents a period.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"PERIOD_TYPE", "CODE"})})
@Data
@NoArgsConstructor
public class Period implements Comparable<Period> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  @NotNull
  private Long id;

  @Column(name = "PERIOD_TYPE")
  @Enumerated(EnumType.STRING)
  @NotNull
  private PeriodType type;

  @Column
  @NotNull
  private String code;

  @Column
  @NotNull
  private String description;

  @Column(name = "START_DATE_TIME")
  @NotNull
  private Date startDateTime;

  @Column(name = "END_DATE_TIME")
  @NotNull
  private Date endDateTime;

  @Column(name = "CURRENT_PERIOD")
  @NotNull
  private Boolean currentPeriod;

  /**
   * Returns the middle date / time for the period.
   * 
   * @return The middle date / time for the period.
   */
  public Date getMiddleDateTime() {
    return DateUtil.getMiddleDateTime(startDateTime, endDateTime);
  }

  /**
   * Compares two periods using the period start date / times.
   * 
   * @param other The other period for use in the comparison.
   * @return
   *         <li>-1 if the other period has a later start date / time.
   *         <li>1 if the other period has an earlier start date / time.
   *         <li>0 if the other period has an equal start date / time.
   */
  public int compareTo(Period other) {
    return this.getStartDateTime().compareTo(other.getStartDateTime());
  }

}
