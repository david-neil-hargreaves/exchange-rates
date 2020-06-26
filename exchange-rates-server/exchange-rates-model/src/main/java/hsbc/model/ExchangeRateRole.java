package hsbc.model;

/**
 * Represents an exchange rate role - buying or selling.
 */

public enum ExchangeRateRole {

  BUYING("buying", "Buying"), SELLING("selling", "Selling");

  private String description;
  private String descriptionInitialCapitalLetter;

  private ExchangeRateRole(String description, String descriptionInitialCapitalLetter) {
    this.description = description;
    this.descriptionInitialCapitalLetter = descriptionInitialCapitalLetter;
  }

  /**
   * Returns the exchange rate role description.
   * 
   * @return The exchange rate role description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the exchange rate role description with initial capital letter.
   * 
   * @return The exchange rate role description with initial capital letter.
   */
  public String getDescriptionInitialCapitalLetter() {
    return descriptionInitialCapitalLetter;
  }
}
