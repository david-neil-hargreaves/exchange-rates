package hsbc.util;

import static hsbc.test.TestData.DATE_TIME_20200124_000000;
import static hsbc.test.TestData.DATE_TIME_20200224_000000;
import static hsbc.test.TestData.DATE_TIME_20200224_000002;
import static hsbc.test.TestData.DATE_TIME_20200224_000003;
import static hsbc.test.TestData.DATE_TIME_20200229_000000;
import static hsbc.test.TestData.DATE_TIME_20200313_120000;
import static hsbc.test.TestData.DATE_TIME_20200401_000000;
import static hsbc.test.TestData.DATE_TIME_20201128_000000;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateUtilTest {

  private static final String ATTRIBUTE_IS_BETWEEN = "Is between?";
  private static final String ATTRIBUTE_MIDDLE_DATE_TIME = "Middle date / time";
  private static final String ATTRIBUTE_EARLIER_DATE = "Earlier date";
  private static final String ATTRIBUTE_LATER_DATE = "Later date";

  @Test
  public void testIsBetweenTrue() {
    assertEquals(ATTRIBUTE_IS_BETWEEN, true, DateUtil.isBetween(DATE_TIME_20200229_000000,
        DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testIsBetweenEqualsStartDateTime() {
    assertEquals(ATTRIBUTE_IS_BETWEEN, true, DateUtil.isBetween(DATE_TIME_20200224_000000,
        DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testIsBetweenEqualsEndDateTime() {
    assertEquals(ATTRIBUTE_IS_BETWEEN, true, DateUtil.isBetween(DATE_TIME_20200401_000000,
        DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testIsBetweenBeforeStartDateTime() {
    assertEquals(ATTRIBUTE_IS_BETWEEN, false, DateUtil.isBetween(DATE_TIME_20200124_000000,
        DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testIsBetweenAfterEndDateTime() {
    assertEquals(ATTRIBUTE_IS_BETWEEN, false, DateUtil.isBetween(DATE_TIME_20201128_000000,
        DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testGetMiddleDateTime() {
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_TIME_20200313_120000,
        DateUtil.getMiddleDateTime(DATE_TIME_20200224_000000, DATE_TIME_20200401_000000));
  }

  @Test
  public void testGetMiddleDateTimeReverseOrderOtherDateTimes() {
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_TIME_20200313_120000,
        DateUtil.getMiddleDateTime(DATE_TIME_20200401_000000, DATE_TIME_20200224_000000));
  }

  @Test
  public void testGetMiddleDateTimeOtherDateTimesAreEqual() {
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_TIME_20200224_000000,
        DateUtil.getMiddleDateTime(DATE_TIME_20200224_000000, DATE_TIME_20200224_000000));
  }

  @Test
  public void testGetMiddleDateTimeRoundUp() {
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_TIME_20200224_000002,
        DateUtil.getMiddleDateTime(DATE_TIME_20200224_000000, DATE_TIME_20200224_000003));
  }

  @Test
  public void testGetMiddleDateTimeRoundUpReverseOrderOtherDateTimes() {
    assertEquals(ATTRIBUTE_MIDDLE_DATE_TIME, DATE_TIME_20200224_000002,
        DateUtil.getMiddleDateTime(DATE_TIME_20200224_000003, DATE_TIME_20200224_000000));
  }

  @Test
  public void testGetEarlierDate() {
    assertEquals(ATTRIBUTE_EARLIER_DATE, DATE_TIME_20200224_000002,
        DateUtil.getEarlierDate(DATE_TIME_20200224_000002, DATE_TIME_20200224_000003));
  }

  @Test
  public void testGetEarlierDateReverseOrder() {
    assertEquals(ATTRIBUTE_EARLIER_DATE, DATE_TIME_20200224_000002,
        DateUtil.getEarlierDate(DATE_TIME_20200224_000003, DATE_TIME_20200224_000002));
  }

  @Test
  public void testGetLaterDate() {
    assertEquals(ATTRIBUTE_LATER_DATE, DATE_TIME_20200224_000003,
        DateUtil.getLaterDate(DATE_TIME_20200224_000002, DATE_TIME_20200224_000003));
  }

  @Test
  public void testGetLaterDateReverseOrder() {
    assertEquals(ATTRIBUTE_LATER_DATE, DATE_TIME_20200224_000003,
        DateUtil.getLaterDate(DATE_TIME_20200224_000003, DATE_TIME_20200224_000002));
  }

}
