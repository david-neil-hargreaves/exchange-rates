package hsbc.model.repository;

import hsbc.model.Period;
import hsbc.model.PeriodType;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Provides basic data access functionality for periods.
 */
@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

  @Query("select p from Period p where p.type = :periodType and p.currentPeriod = true")
  public Optional<Period> findCurrentPeriod(@Param(value = "periodType") PeriodType periodType);

  @Query("select p from Period p where p.type = :periodType"
      + " and p.startDateTime < :startDateTime order by p.startDateTime")
  public List<Period> findPeriodsBeforeStartDateTime(
      @Param(value = "periodType") PeriodType periodType,
      @Param(value = "startDateTime") Date startDateTime);

}
