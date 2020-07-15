package hsbc.it;

import static hsbc.it.TestData.URL_SELL_CURRENT_SCREEN;

import org.junit.Test;

public class SellCurrentScreenIT extends AbstractTestIT {

  @Test
  public void navigateToSellCurrentScreen() {
    driver.get(URL_SELL_CURRENT_SCREEN);
    verifySellCurrentScreenDefaultCurrencies();
  }

}
