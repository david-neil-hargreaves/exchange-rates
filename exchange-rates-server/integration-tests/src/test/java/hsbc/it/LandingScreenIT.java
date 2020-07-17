package hsbc.it;

import static hsbc.it.TestData.ATTRIBUTE_URL;
import static hsbc.it.TestData.LINK_TEXT_BUY_CURRENT;
import static hsbc.it.TestData.LINK_TEXT_BUY_HISTORY;
import static hsbc.it.TestData.LINK_TEXT_MENU;
import static hsbc.it.TestData.LINK_TEXT_SELL_CURRENT;
import static hsbc.it.TestData.LINK_TEXT_SELL_HISTORY;
import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_BUY_HISTORY_SCREEN;
import static hsbc.it.TestData.URL_LANDING_SCREEN;
import static hsbc.it.TestData.URL_MENU;
import static hsbc.it.TestData.URL_SELL_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_SELL_HISTORY_SCREEN;
import static org.junit.Assert.assertEquals;
import hsbc.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class LandingScreenIT extends AbstractTestIT {

  @Test
  public void navigateToLandingScreen() {
    webDriver.get(URL_LANDING_SCREEN);
    assertEquals(ATTRIBUTE_URL, URL_LANDING_SCREEN, webDriver.getCurrentUrl());
  }

  @Test
  public void navigateToBuyCurrentScreen() {
    webDriver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_BUY_CURRENT);
    link.click();
    assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, webDriver.getCurrentUrl());
  }

  @Test
  public void navigateToSellCurrentScreen() {
    webDriver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_SELL_CURRENT);
    link.click();
    assertEquals(ATTRIBUTE_URL, URL_SELL_CURRENT_SCREEN, webDriver.getCurrentUrl());
  }

  @Test
  public void navigateToBuyHistoryScreen() {
    webDriver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_BUY_HISTORY);
    link.click();
    assertEquals(ATTRIBUTE_URL, URL_BUY_HISTORY_SCREEN, webDriver.getCurrentUrl());
  }

  @Test
  public void navigateToSellHistoryScreen() {
    webDriver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_SELL_HISTORY);
    link.click();
    assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, webDriver.getCurrentUrl());
  }

  @Test
  public void navigateToMenu() {
    webDriver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_MENU);
    link.click();
    assertEquals(ATTRIBUTE_URL, URL_MENU, webDriver.getCurrentUrl());
  }

}
