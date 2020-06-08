

package hsbc.service;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.PeriodRepository;
import hsbc.util.exception.ServiceException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl implements PeriodService {

  private static final Logger LOGGER = LogManager.getLogger(PeriodServiceImpl.class);

  public static final String MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED = "Current %s not configured.";

  @Autowired
  private PeriodRepository periodRepository;

  @Override
  public List<Period> getLatestHistoricalPeriods(PeriodType type, int numberOfPeriods)
      throws ServiceException {
    LOGGER.traceEntry();
    Optional<Period> optionalCurrentPeriod = periodRepository.findCurrentPeriod(type);
    if (!(optionalCurrentPeriod.isPresent())) {
      String message = String.format(MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED, type.getDescription());
      throw new ServiceException(message);
    }
    Period currentPeriod = optionalCurrentPeriod.get();
    List<Period> periodsBeforeCurrentPeriod =
        periodRepository.findPeriodsBeforeStartDateTime(type, currentPeriod.getStartDateTime());
    int startIndex = Math.max((periodsBeforeCurrentPeriod.size() - numberOfPeriods), 0);
    return LOGGER.traceExit(
        periodsBeforeCurrentPeriod.subList(startIndex, periodsBeforeCurrentPeriod.size()));
  }

}
