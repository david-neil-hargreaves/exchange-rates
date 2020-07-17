package hsbc.it;

import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;
import hsbc.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class BuyCurrentScreenIT extends AbstractTestIT {

  @Test
  public void navigateToBuyCurrentScreen() {
    webDriver.get(URL_BUY_CURRENT_SCREEN);
    verifyBuyCurrentScreenDefaultCurrencies();
  }

}
