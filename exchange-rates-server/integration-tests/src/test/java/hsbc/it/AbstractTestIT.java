package hsbc.it;

import static hsbc.it.TestData.ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE;
import static hsbc.it.TestData.ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_CODE;
import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.ATTRIBUTE_EXCHANGE_RATE;
import static hsbc.it.TestData.ATTRIBUTE_HEADING;
import static hsbc.it.TestData.ATTRIBUTE_PERIOD;
import static hsbc.it.TestData.ATTRIBUTE_URL;
import static hsbc.it.TestData.COLUMN_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.COLUMN_EXCHANGE_RATE;
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.DEFAULT_CURRENCY_CODES;
import static hsbc.it.TestData.DEFAULT_CURRENCY_DESCRIPTIONS;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_BUY_CURRENT;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_BUY_HISTORY;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_SELL_CURRENT;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_SELL_HISTORY;
import static hsbc.it.TestData.DEFAULT_TAG_HEADING_TEXT_BUY;
import static hsbc.it.TestData.DEFAULT_TAG_HEADING_TEXT_SELL;
import static hsbc.it.TestData.PERIODS;
import static hsbc.it.TestData.TAG_HEADING;
import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_BUY_HISTORY_SCREEN;
import static hsbc.it.TestData.URL_SELL_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_SELL_HISTORY_SCREEN;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static org.junit.Assert.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

abstract public class AbstractTestIT {

  protected WebDriver driver;
  private Wait<WebDriver> wait;

  @Before
  public void setUp() {
    WebDriverManager.getInstance(FIREFOX).setup();
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(5000))
        .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
  }

  @After
  public void tearDown() {
    driver.close();
  }

  protected WebElement getWebElement(final String xpath) {
    return driver.findElement(By.xpath(xpath));
    /*
     * WebElement webElement = wait.until(new Function<WebDriver, WebElement>() { public WebElement
     * apply(WebDriver driver) { return driver.findElement(By.xpath(xpath)); } }); return
     * webElement;
     */
  }

  protected String getWebElementText(final String xpath) {
    return getWebElement(xpath).getText();
  }

  protected WebElement getWebElementByTagName(final String tagName) {
    return driver.findElement(By.tagName(tagName));
    /*
     * WebElement webElement = wait.until(new Function<WebDriver, WebElement>() { public WebElement
     * apply(WebDriver driver) { return driver.findElement(By.tagName(tagName)); } }); return
     * webElement;
     */
  }

  protected String getWebElementTextByTagName(final String tagName) {
    return getWebElementByTagName(tagName).getText();
  }

  protected WebElement getLink(final String linkText) {
    return driver.findElement(By.linkText(linkText));
  }

  protected WebElement getWebElementById(final String id) {
    return driver.findElement(By.id(id));
  }
  
  protected void verifyBuyCurrentScreenDefaultCurrencies() {
    assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, DEFAULT_TAG_HEADING_TEXT_BUY, heading);
    for (int row = 1; row <= DEFAULT_CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, DEFAULT_CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, DEFAULT_CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      String exchangeRate =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_EXCHANGE_RATE + "]");
      assertEquals(ATTRIBUTE_EXCHANGE_RATE, DEFAULT_EXCHANGE_RATES_BUY_CURRENT[row - 1], exchangeRate);
    }
  }
  
  protected void verifySellCurrentScreenDefaultCurrencies() {
    assertEquals(ATTRIBUTE_URL, URL_SELL_CURRENT_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, DEFAULT_TAG_HEADING_TEXT_SELL, heading);
    for (int row = 1; row <= DEFAULT_CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, DEFAULT_CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, DEFAULT_CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      String exchangeRate =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_EXCHANGE_RATE + "]");
      assertEquals(ATTRIBUTE_EXCHANGE_RATE, DEFAULT_EXCHANGE_RATES_SELL_CURRENT[row - 1],
          exchangeRate);
    }
  }
  
  protected void verifyBuyHistoryScreenDefaultCurrencies() {
    assertEquals(ATTRIBUTE_URL, URL_BUY_HISTORY_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, DEFAULT_TAG_HEADING_TEXT_BUY, heading);
    String columnHeadingCurrencyCode =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_CODE + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE, COLUMN_HEADING_CURRENCY_CODE,
        columnHeadingCurrencyCode);
    String columnHeadingCurrencyDescription =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_DESCRIPTION + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION,
        COLUMN_HEADING_CURRENCY_DESCRIPTION, columnHeadingCurrencyDescription);
    for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
      String period = getWebElementText("//table/thead/tr/th[" + (periodNumber + 2) + "]");
      assertEquals(ATTRIBUTE_PERIOD, PERIODS[periodNumber - 1], period);
    }
    for (int row = 1; row <= DEFAULT_CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, DEFAULT_CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, DEFAULT_CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
        String exchangeRate =
            getWebElementText("//table/tbody/tr[" + row + "]/td[" + (periodNumber + 2) + "]");
        assertEquals(ATTRIBUTE_EXCHANGE_RATE,
            ((String[]) DEFAULT_EXCHANGE_RATES_BUY_HISTORY.get(row))[periodNumber - 1], exchangeRate);
      }
    }
  }
  
  protected void verifySellHistoryScreenDefaultCurrencies() {
    driver.get(URL_SELL_HISTORY_SCREEN);
    assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, DEFAULT_TAG_HEADING_TEXT_SELL, heading);
    String columnHeadingCurrencyCode =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_CODE + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE, COLUMN_HEADING_CURRENCY_CODE,
        columnHeadingCurrencyCode);
    String columnHeadingCurrencyDescription =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_DESCRIPTION + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION,
        COLUMN_HEADING_CURRENCY_DESCRIPTION, columnHeadingCurrencyDescription);
    for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
      String period = getWebElementText("//table/thead/tr/th[" + (periodNumber + 2) + "]");
      assertEquals(ATTRIBUTE_PERIOD, PERIODS[periodNumber - 1], period);
    }
    for (int row = 1; row <= DEFAULT_CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, DEFAULT_CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, DEFAULT_CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
        String exchangeRate =
            getWebElementText("//table/tbody/tr[" + row + "]/td[" + (periodNumber + 2) + "]");
        assertEquals(ATTRIBUTE_EXCHANGE_RATE,
            ((String[]) DEFAULT_EXCHANGE_RATES_SELL_HISTORY.get(row))[periodNumber - 1], exchangeRate);
      }
    }
  }

}
