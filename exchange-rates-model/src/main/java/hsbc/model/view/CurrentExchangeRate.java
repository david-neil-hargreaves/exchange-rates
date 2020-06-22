package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentExchangeRate {
  private Currency otherCurrency;
  private Optional<BigDecimal> rate;
}
