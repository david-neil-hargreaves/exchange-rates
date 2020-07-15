package hsbc.it;

import static hsbc.it.TestData.URL_SELL_HISTORY_SCREEN;

import org.junit.Test;

public class SellHistoryScreenIT extends AbstractTestIT {

  @Test
  public void navigateToSellHistoryScreen() throws InterruptedException {
    driver.get(URL_SELL_HISTORY_SCREEN);
    verifySellHistoryScreenDefaultCurrencies();
  }

}
