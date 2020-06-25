package hsbc.service;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.util.exception.InvalidConfigurationException;
import java.util.List;

/**
 * Provides service methods for periods.
 *
 */
public interface PeriodService {

  /**
   * Returns the latest historical periods immediately prior to the current period for the period
   * type.
   * 
   * @param periodType The period type.
   * @param numberOfPeriods The number of periods.
   * @return The latest historical periods immediately prior to the current period for the period
   *         type.
   * @throws InvalidConfigurationException If a configuration exception occurs.
   */
  public List<Period> getLatestHistoricalPeriods(PeriodType periodType, int numberOfPeriods)
      throws InvalidConfigurationException;

}
