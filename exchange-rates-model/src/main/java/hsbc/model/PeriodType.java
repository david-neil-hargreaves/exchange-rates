package hsbc.model;

/**
 * Represents a period type.
 */
public enum PeriodType {

  CALENDAR_MONTH("calendar month"), WEEK("week");

  private String description;

  private PeriodType(String description) {
    this.description = description;
  }

  /**
   * Returns the period type description.
   * 
   * @return The period type description.
   */
  public String getDescription() {
    return description;
  }

}
