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
  private Long id;

  @Column(name = "PERIOD_TYPE")
  @Enumerated(EnumType.STRING)
  private PeriodType type;

  @Column
  private String code;

  @Column
  private String description;

  @Column(name = "START_DATE_TIME")
  private Date startDateTime;

  @Column(name = "END_DATE_TIME")
  private Date endDateTime;

  @Column(name = "CURRENT_PERIOD")
  private Boolean currentPeriod;

  public Date getMiddleDateTime() {
    return DateUtil.getMiddleDateTime(startDateTime, endDateTime);
  }

  public int compareTo(Period other) {
    return this.getStartDateTime().compareTo(other.getStartDateTime());
  }

}
