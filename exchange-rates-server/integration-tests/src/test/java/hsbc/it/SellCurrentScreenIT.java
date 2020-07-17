package hsbc.it;

import static hsbc.it.TestData.URL_SELL_CURRENT_SCREEN;
import hsbc.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SellCurrentScreenIT extends AbstractTestIT {

  @Test
  public void navigateToSellCurrentScreen() {
    webDriver.get(URL_SELL_CURRENT_SCREEN);
    verifySellCurrentScreenDefaultCurrencies();
  }

}
