
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

  public Optional<ExchangeRate> findByBuyingCurrencyAndSellingCurrency(
      @Param(value = "buyingCurrency") Currency buyingCurrency,
      @Param(value = "sellingCurrency") Currency sellingCurrency);

}
