package hsbc.model.selector;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Deprecated
@Data
@AllArgsConstructor
public class ExchangeRatesSelector {

  @NotNull(message = "Buying currency code cannot be null.")
  @Size(min = 3, max = 3, message = "Buying currency code must be three characters.")
  private String buyingCurrencyCode;

  @NotNull(message = "At least one selling currency code must be input.")
  @NotEmpty(message = "At least one selling currency code must be input.")
  private List<@Size(min = 3, max = 3,
      message = "Selling currency code must be three characters.") String> sellingCurrencyCodes;

}
