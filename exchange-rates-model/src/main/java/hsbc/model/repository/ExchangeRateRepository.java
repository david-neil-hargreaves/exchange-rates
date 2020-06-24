
package hsbc.model.repository;

import hsbc.model.Currency;
import hsbc.model.ExchangeRate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Provides basic data access functionality for exchange rates.
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  /*
   * public List<ExchangeRate> findByBuyingCurrencyAndSellingCurrencyIn(
   * 
   * @Param(value = "buyingCurrency") Currency buyingCurrency,
   * 
   * @Param(value = "sellingCurrencies") List<Currency> sellingCurrencies);
   * 
   * public List<ExchangeRate> findBySellingCurrencyAndBuyingCurrencyIn(
   * 
   * @Param(value = "sellingCurrency") Currency sellingCurrency,
   * 
   * @Param(value = "buyingCurrencies") List<Currency> buyingCurrencies);
   */

  public Optional<ExchangeRate> findByBuyingCurrencyAndSellingCurrency(
      @Param(value = "buyingCurrency") Currency buyingCurrency,
      @Param(value = "sellingCurrency") Currency sellingCurrency);

}
