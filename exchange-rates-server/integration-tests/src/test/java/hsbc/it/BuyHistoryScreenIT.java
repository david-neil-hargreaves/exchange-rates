package hsbc.it;

import static hsbc.it.TestData.URL_BUY_HISTORY_SCREEN;

import org.junit.Test;

public class BuyHistoryScreenIT extends AbstractTestIT {

  @Test
  public void navigateToBuyHistoryScreen() throws InterruptedException {
    driver.get(URL_BUY_HISTORY_SCREEN);
    verifyBuyHistoryScreenDefaultCurrencies();
  }

}
