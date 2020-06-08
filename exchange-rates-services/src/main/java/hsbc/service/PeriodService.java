package hsbc.service;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.util.exception.ServiceException;
import java.util.List;

public interface PeriodService {

  public List<Period> getLatestHistoricalPeriods(PeriodType type, int numberOfPeriods)
      throws ServiceException;

}
