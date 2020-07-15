package hsbc.it;

import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;

import org.junit.Test;

public class BuyCurrentScreenIT extends AbstractTestIT {

  @Test
  public void navigateToBuyCurrentScreen() {
    driver.get(URL_BUY_CURRENT_SCREEN);
    verifyBuyCurrentScreenDefaultCurrencies();
  }

}
