package hsbc.model.view;

import hsbc.model.Currency;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;

@Data
public class HistoricalExchangeRate {
  private Currency otherCurrency;
  private List<Optional<BigDecimal>> rates = new ArrayList<>();

  HistoricalExchangeRate(Currency otherCurrency) {
    super();
    this.otherCurrency = otherCurrency;
  }

  void addRate(Optional<BigDecimal> rate) {
    rates.add(rate);
  }

}
