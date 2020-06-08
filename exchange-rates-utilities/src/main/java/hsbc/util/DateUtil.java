package hsbc.util;

import java.time.Duration;
import java.util.Date;

/**
 * Various date / time utility methods.
 *
 */
public final class DateUtil {

  private DateUtil() {}

  /**
   * Returns whether or not a date / time is between the given start and end date / times.
   * 
   * @param dateTime Date / time.
   * @param startDateTime Start date / time.
   * @param endDateTime End date / time.
   * @return True if the date / time is between (inclusive) the start and end date / times;
   *         otherwise returns false.
   */
  public static final boolean isBetween(Date dateTime, Date startDateTime, Date endDateTime) {
    return !(dateTime.before(startDateTime)) && !(dateTime.after(endDateTime));
  }

  /**
   * Returns the middle date / time (rounding up to the nearest second) between two date / times.
   * 
   * @param oneDateTime One date / time.
   * @param otherDateTime Other date / time.
   * @return The middle date / time.
   */
  public static final Date getMiddleDateTime(Date oneDateTime, Date otherDateTime) {
    if (oneDateTime.before(otherDateTime)) {
      long secondsBetween =
          Duration.between(oneDateTime.toInstant(), otherDateTime.toInstant()).getSeconds();
      if (secondsBetween % 2 == 1) {
        secondsBetween++;
      }
      return Date.from(oneDateTime.toInstant().plusSeconds(secondsBetween / 2));
    } else {
      long secondsBetween =
          Duration.between(otherDateTime.toInstant(), oneDateTime.toInstant()).getSeconds();
      if (secondsBetween % 2 == 1) {
        secondsBetween++;
      }
      return Date.from(otherDateTime.toInstant().plusSeconds(secondsBetween / 2));
    }
  }

  public static final Date getEarlierDate(Date oneDateTime, Date otherDateTime) {
    return oneDateTime.before(otherDateTime) ? oneDateTime : otherDateTime;
  }

  public static final Date getLaterDate(Date oneDateTime, Date otherDateTime) {
    return oneDateTime.after(otherDateTime) ? oneDateTime : otherDateTime;
  }

}
