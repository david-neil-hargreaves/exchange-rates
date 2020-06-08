package hsbc.model;

/**
 * This is used when reporting on exchange rates by period to determine which exchange rate (based
 * on its start date / time and end date / time) applies to the period.
 * 
 * <table>
 * <tr>
 * <td>START</td>
 * <td>The exchange rate in use at the start of the period will be used.</td>
 * <tr>
 * <td>END</td>
 * <td>The exchange rate in use at the end of the period will be used.</td>
 * <tr>
 * <td>MIDDLE</td>
 * <td>The exchange rate in use at the middle of the period will be used.</td>
 * <tr>
 * <td>AVERAGE</td>
 * <td>The average exchange rate for the period will be used.</td>
 * </table>
 */
public enum ExchangeRatePeriodMatchType {
  START, END, MIDDLE, AVERAGE
}
