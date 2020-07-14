package hsbc.it;

import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LandingScreenIT {

  private final static String URL_LANDING_SCREEN = "http://localhost:4200/";
  private final static String URL_BUY_CURRENT_SCREEN = "http://localhost:4200/buy/current";
  private final static String URL_SELL_CURRENT_SCREEN = "http://localhost:4200/sell/current";
  private final static String URL_BUY_HISTORY_SCREEN = "http://localhost:4200/buy/history";
  private final static String URL_SELL_HISTORY_SCREEN = "http://localhost:4200/sell/history";
  private final static String URL_MENU = "http://localhost:4200/menu";
  private final static String LINK_TEXT_BUY_CURRENT = "Buy (Current)";
  private final static String LINK_TEXT_SELL_CURRENT = "Sell (Current)";
  private final static String LINK_TEXT_BUY_HISTORY = "Buy (History)";
  private final static String LINK_TEXT_SELL_HISTORY = "Sell (History)";
  private final static String LINK_TEXT_MENU = "Menu";
  private final static String ATTRIBUTE_URL = "URL";

  private WebDriver driver;

  @Before
  public void setUp() {
    WebDriverManager.getInstance(FIREFOX).setup();
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get(URL_LANDING_SCREEN);
  }

  @After
  public void tearDown() {
    driver.close();
  }

  @Test
  public void navigateToLandingScreen() {
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_LANDING_SCREEN, actualUrl);
  }

  @Test
  public void navigateToBuyCurrentScreen() {
    WebElement link = driver.findElement(By.linkText(LINK_TEXT_BUY_CURRENT));
    link.click();
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, actualUrl);
  }

  @Test
  public void navigateToSellCurrentScreen() {
    WebElement link = driver.findElement(By.linkText(LINK_TEXT_SELL_CURRENT));
    link.click();
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_SELL_CURRENT_SCREEN, actualUrl);
  }

  @Test
  public void navigateToBuyHistoryScreen() {
    WebElement link = driver.findElement(By.linkText(LINK_TEXT_BUY_HISTORY));
    link.click();
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_BUY_HISTORY_SCREEN, actualUrl);
  }

  @Test
  public void navigateToSellHistoryScreen() {
    WebElement link = driver.findElement(By.linkText(LINK_TEXT_SELL_HISTORY));
    link.click();
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, actualUrl);
  }

  @Test
  public void navigateToMenu() {
    WebElement link = driver.findElement(By.linkText(LINK_TEXT_MENU));
    link.click();
    String actualUrl = driver.getCurrentUrl();
    Assert.assertEquals(ATTRIBUTE_URL, URL_MENU, actualUrl);
  }

}
