
package hsbc.model.repository;

import hsbc.model.Currency;
import hsbc.model.ExchangeRateHistory;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Provides basic data access functionality for exchange rates.
 */
@Repository
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Long> {

  /**
   * Returns exchange rate histories for the given buying currency, selling currency and range of
   * dates.
   * 
   * <p>Exchange rate histories are returned based on the date range in the following circumstances:
   * <ul>
   * <li>Where the period is wholly within the date range.</li>
   * <li>Where the period starts within the date range but ends after the date range.</li>
   * <li>Where the period starts before the date range but ends within the date range.</li>
   * <li>Where the period starts before the date range and ends after the date range.</li>
   * </ul>
   * 
   * <p>Therefore exchange rate histories are not returned in the following circumstances:
   * <ul>
   * <li>Where the period is wholly before the date range.</li>
   * <li>Where the period is wholly after the date range.</li>
   * </ul>
   * 
   * @param buyingCurrency The buying currency.
   * @param sellingCurrency The selling currency.
   * @param startDateTime Start date / time.
   * @param endDateTime End date / time.
   * @return Exchange rate histories.
   */
  @Query("select e from ExchangeRateHistory e where e.buyingCurrency = :buyingCurrency"
      + " and e.sellingCurrency = :sellingCurrency" + " and  ( "
      + " ( e.startDateTime >= :startDateTime and e.startDateTime <= :endDateTime )" + " or "
      + " ( e.endDateTime >= :startDateTime and e.endDateTime <= :endDateTime )" + " or "
      + " ( e.startDateTime < :startDateTime and e.endDateTime > :endDateTime )" + ")")
  public List<ExchangeRateHistory> findExchangeRateHistories(
      @Param(value = "buyingCurrency") Currency buyingCurrency,
      @Param(value = "sellingCurrency") Currency sellingCurrency,
      @Param(value = "startDateTime") Date startDateTime,
      @Param(value = "endDateTime") Date endDateTime);

}
