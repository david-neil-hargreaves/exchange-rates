package hsbc.service;

import static hsbc.util.exception.InvalidConfigurationException.MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.PeriodRepository;
import hsbc.util.exception.InvalidConfigurationException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl implements PeriodService {

  private static final Logger LOGGER = LogManager.getLogger(PeriodServiceImpl.class);

  @Autowired
  private PeriodRepository periodRepository;

  @Override
  public List<Period> getLatestHistoricalPeriods(PeriodType periodType, int numberOfPeriods)
      throws InvalidConfigurationException {
    LOGGER.traceEntry();
    Optional<Period> optionalCurrentPeriod = periodRepository.findCurrentPeriod(periodType);
    if (!(optionalCurrentPeriod.isPresent())) {
      String message =
          String.format(MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED, periodType.getDescription());
      throw new InvalidConfigurationException(message);
    }
    Period currentPeriod = optionalCurrentPeriod.get();
    List<Period> periodsBeforeCurrentPeriod = periodRepository
        .findPeriodsBeforeStartDateTime(periodType, currentPeriod.getStartDateTime());
    int startIndex = Math.max((periodsBeforeCurrentPeriod.size() - numberOfPeriods), 0);
    return LOGGER.traceExit(
        periodsBeforeCurrentPeriod.subList(startIndex, periodsBeforeCurrentPeriod.size()));
  }

}
