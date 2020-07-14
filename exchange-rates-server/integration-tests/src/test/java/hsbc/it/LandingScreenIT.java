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
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LandingScreenIT extends AbstractTestIT {

  @Test
  public void navigateToLandingScreen() {
    driver.get(URL_LANDING_SCREEN);
    Assert.assertEquals(ATTRIBUTE_URL, URL_LANDING_SCREEN, driver.getCurrentUrl());
  }

  @Test
  public void navigateToBuyCurrentScreen() {
    driver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_BUY_CURRENT);
    link.click();
    Assert.assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, driver.getCurrentUrl());
  }

  @Test
  public void navigateToSellCurrentScreen() {
    driver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_SELL_CURRENT);
    link.click();
    Assert.assertEquals(ATTRIBUTE_URL, URL_SELL_CURRENT_SCREEN, driver.getCurrentUrl());
  }

  @Test
  public void navigateToBuyHistoryScreen() {
    driver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_BUY_HISTORY);
    link.click();
    Assert.assertEquals(ATTRIBUTE_URL, URL_BUY_HISTORY_SCREEN, driver.getCurrentUrl());
  }

  @Test
  public void navigateToSellHistoryScreen() {
    driver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_SELL_HISTORY);
    link.click();
    Assert.assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, driver.getCurrentUrl());
  }

  @Test
  public void navigateToMenu() {
    driver.get(URL_LANDING_SCREEN);
    WebElement link = getLink(LINK_TEXT_MENU);
    link.click();
    Assert.assertEquals(ATTRIBUTE_URL, URL_MENU, driver.getCurrentUrl());
  }

}
