package hsbc.service;

import static hsbc.test.TestData.createCurrentPeriod;
import static hsbc.test.TestData.createPeriods;
import static hsbc.util.exception.InvalidConfigurationException.MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import hsbc.model.repository.PeriodRepository;
import hsbc.test.AbstractTest;
import hsbc.util.exception.InvalidConfigurationException;
import hsbc.util.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PeriodServiceTest extends AbstractTest {

  private static final String ATTRIBUTE_PERIODS = "Periods";

  @Mock
  private PeriodRepository mockPeriodRepository;

  @InjectMocks
  private PeriodService periodService = new PeriodServiceImpl();

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void testGetLatestHistoricalPeriodsRequestedNumberEqualsPeriodsAvailable()
      throws ServiceException {
    PeriodType periodType = PeriodType.CALENDAR_MONTH;
    List<Period> expectedPeriods = createPeriods(periodType);
    int numberOfPeriods = 2;
    testGetLatestHistoricalPeriods(numberOfPeriods, expectedPeriods);
  }

  @Test
  public void testGetLatestHistoricalPeriodsRequestedNumberMoreThanPeriodsAvailable()
      throws ServiceException {
    PeriodType periodType = PeriodType.CALENDAR_MONTH;
    List<Period> expectedPeriods = createPeriods(periodType);
    int numberOfPeriods = 3;
    testGetLatestHistoricalPeriods(numberOfPeriods, expectedPeriods);
  }

  @Test
  public void testGetLatestHistoricalPeriodsRequestedNumberLessThanPeriodsAvailable()
      throws ServiceException {
    PeriodType periodType = PeriodType.CALENDAR_MONTH;
    List<Period> periods = createPeriods(periodType);
    int numberOfPeriods = 1;
    List<Period> expectedPeriods = new ArrayList<>();
    expectedPeriods.add(periods.get(1));
    testGetLatestHistoricalPeriods(numberOfPeriods, expectedPeriods);
  }

  @Test
  public void testGetLatestHistoricalPeriodsRequestedNumberZero() throws ServiceException {
    int numberOfPeriods = 0;
    List<Period> expectedPeriods = new ArrayList<>();
    testGetLatestHistoricalPeriods(numberOfPeriods, expectedPeriods);
  }

  @Test
  public void testGetLatestHistoricalPeriodsCurrentPeriodNotConfigured() throws ServiceException {
    PeriodType periodType = PeriodType.CALENDAR_MONTH;
    when(mockPeriodRepository.findCurrentPeriod(periodType)).thenReturn(Optional.empty());
    String message =
        String.format(MESSAGE_CURRENT_PERIOD_NOT_CONFIGURED, periodType.getDescription());
    InvalidConfigurationException invalidConfigurationException =
        new InvalidConfigurationException(message);
    expectedException.expect(invalidConfigurationException.getClass());
    expectedException.expectMessage(invalidConfigurationException.getMessage());
    expectedException.expectCause(IsEqual.equalTo(null));
    int numberOfPeriods = 2;
    periodService.getLatestHistoricalPeriods(periodType, numberOfPeriods);
  }

  private void testGetLatestHistoricalPeriods(int numberOfPeriods, List<Period> expectedPeriods)
      throws ServiceException {
    PeriodType periodType = PeriodType.CALENDAR_MONTH;
    Period currentPeriod = createCurrentPeriod(periodType);
    when(mockPeriodRepository.findCurrentPeriod(periodType)).thenReturn(Optional.of(currentPeriod));
    List<Period> periods = createPeriods(periodType);
    when(mockPeriodRepository.findPeriodsBeforeStartDateTime(periodType,
        currentPeriod.getStartDateTime())).thenReturn(periods);
    List<Period> actualPeriods =
        periodService.getLatestHistoricalPeriods(periodType, numberOfPeriods);
    assertEquals(ATTRIBUTE_PERIODS, expectedPeriods, actualPeriods);
    verify(mockPeriodRepository, times(1)).findCurrentPeriod(periodType);
    verify(mockPeriodRepository, times(1)).findPeriodsBeforeStartDateTime(periodType,
        currentPeriod.getStartDateTime());
  }

}
