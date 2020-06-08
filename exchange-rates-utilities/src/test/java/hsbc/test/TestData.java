package hsbc.test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestData {

  public static final String CURRENCY_CODE_INVALID = "GRO";

  public static final Date DATE_TIME_20200124_000000 = getDate("2020-01-24T00:00:00Z");
  public static final Date DATE_TIME_20200224_000000 = getDate("2020-02-24T00:00:00Z");
  public static final Date DATE_TIME_20200224_000002 = getDate("2020-02-24T00:00:02Z");
  public static final Date DATE_TIME_20200224_000003 = getDate("2020-02-24T00:00:03Z");
  public static final Date DATE_TIME_20200229_000000 = getDate("2020-02-29T00:00:00Z");
  public static final Date DATE_TIME_20200313_120000 = getDate("2020-03-13T12:00:00Z");
  public static final Date DATE_TIME_20200401_000000 = getDate("2020-04-01T00:00:00Z");
  public static final Date DATE_TIME_20201128_000000 = getDate("2020-11-28T00:00:00Z");

  private static Date getDate(String dateString) {
    OffsetDateTime offsetDateTime =
        OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
    Instant instant = offsetDateTime.toInstant();
    return Date.from(instant);
  }

}
