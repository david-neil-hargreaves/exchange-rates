
package hsbc.model.repository;

import hsbc.model.Currency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Provides basic data access functionality for currencies.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  public Optional<Currency> findByCode(@Param(value = "code") String code);

  public List<Currency> findByDefaultSubjectCurrencyTrue();

  public List<Currency> findByDefaultComparisonCurrencyTrue();

}
