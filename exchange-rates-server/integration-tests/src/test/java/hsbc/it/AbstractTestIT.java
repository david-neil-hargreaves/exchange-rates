package hsbc.it;

import static hsbc.it.TestData.ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE;
import static hsbc.it.TestData.ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.ATTRIBUTE_COMPARISON_CURRENCY;
import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_CODE;
import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.ATTRIBUTE_EXCHANGE_RATE;
import static hsbc.it.TestData.ATTRIBUTE_HEADING;
import static hsbc.it.TestData.ATTRIBUTE_PERIOD;
import static hsbc.it.TestData.ATTRIBUTE_SUBJECT_CURRENCY;
import static hsbc.it.TestData.ATTRIBUTE_URL;
import static hsbc.it.TestData.COLUMN_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.COLUMN_EXCHANGE_RATE;
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.CUSTOM_COMPARISON_CURRENCY_CODES;
import static hsbc.it.TestData.CUSTOM_COMPARISON_CURRENCY_CODES_SELECTED_ORDER;
import static hsbc.it.TestData.CUSTOM_COMPARISON_CURRENCY_DESCRIPTIONS;
import static hsbc.it.TestData.CUSTOM_EXCHANGE_RATES_BUY_CURRENT;
import static hsbc.it.TestData.CUSTOM_EXCHANGE_RATES_BUY_HISTORY;
import static hsbc.it.TestData.CUSTOM_EXCHANGE_RATES_SELL_CURRENT;
import static hsbc.it.TestData.CUSTOM_EXCHANGE_RATES_SELL_HISTORY;
import static hsbc.it.TestData.CUSTOM_HEADING_TEXT_BUY;
import static hsbc.it.TestData.CUSTOM_HEADING_TEXT_SELL;
import static hsbc.it.TestData.CUSTOM_SUBJECT_CURRENCY_CODE;
import static hsbc.it.TestData.DEFAULT_COMPARISON_CURRENCY_CODES;
import static hsbc.it.TestData.DEFAULT_COMPARISON_CURRENCY_DESCRIPTIONS;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_BUY_CURRENT;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_BUY_HISTORY;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_SELL_CURRENT;
import static hsbc.it.TestData.DEFAULT_EXCHANGE_RATES_SELL_HISTORY;
import static hsbc.it.TestData.DEFAULT_HEADING_TEXT_BUY;
import static hsbc.it.TestData.DEFAULT_HEADING_TEXT_SELL;
import static hsbc.it.TestData.DEFAULT_SUBJECT_CURRENCY_CODE;
import static hsbc.it.TestData.ELEMENT_ID_PREFIX_COMPARISON_CURRENCY;
import static hsbc.it.TestData.ELEMENT_ID_SUBJECT_CURRENCY;
import static hsbc.it.TestData.PARENT_ELEMENT_SELECTOR;
import static hsbc.it.TestData.PERIODS;
import static hsbc.it.TestData.PREFIX_COMPARISON_CURRENCY;
import static hsbc.it.TestData.TAG_HEADING;
import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_BUY_HISTORY_SCREEN;
import static hsbc.it.TestData.URL_MENU;
import static hsbc.it.TestData.URL_SELL_CURRENT_SCREEN;
import static hsbc.it.TestData.URL_SELL_HISTORY_SCREEN;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static org.junit.Assert.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

abstract public class AbstractTestIT {

  protected WebDriver webDriver;
  private Wait<WebDriver> wait;

  @Before
  public void setUp() {
    WebDriverManager.getInstance(FIREFOX).setup();
    webDriver = new FirefoxDriver();
    webDriver.manage().window().maximize();
    wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofMillis(5000))
        .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
  }

  @After
  public void tearDown() {
    webDriver.close();
  }

  protected WebElement getWebElement(final String xpath) {
    return webDriver.findElement(By.xpath(xpath));
    /*
     * WebElement webElement = wait.until(new Function<WebDriver, WebElement>() { public WebElement
     * apply(WebDriver webDriver) { return webDriver.findElement(By.xpath(xpath)); } }); return
     * webElement;
     */
  }

  protected String getWebElementText(final String xpath) {
    return getWebElement(xpath).getText();
  }

  protected WebElement getWebElementByTagName(final String tagName) {
    WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
      public WebElement apply(WebDriver driver) {
        return driver.findElement(By.tagName(tagName));
      }
    });
    return webElement;

  }

  protected String getWebElementTextByTagName(final String tagName) {
    return getWebElementByTagName(tagName).getText();
  }

  protected WebElement getLink(final String linkText) {
    return webDriver.findElement(By.linkText(linkText));
  }

  protected WebElement getWebElementById(final String id) {
    return webDriver.findElement(By.id(id));
  }

  protected void verifyCurrentScreen(String expectedHeading, String[] expectedCurrencyCodes,
      String[] expectedCurrencyDescriptions, String[] expectedExchangeRates) {
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, expectedHeading, heading);
    for (int row = 1; row <= expectedCurrencyCodes.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, expectedCurrencyCodes[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, expectedCurrencyDescriptions[row - 1],
          currencyDescription);
      String exchangeRate =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_EXCHANGE_RATE + "]");
      assertEquals(ATTRIBUTE_EXCHANGE_RATE, expectedExchangeRates[row - 1], exchangeRate);
    }
  }

  protected void verifyBuyCurrentScreen(String expectedHeading, String[] expectedCurrencyCodes,
      String[] expectedCurrencyDescriptions, String[] expectedExchangeRates) {
    assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, webDriver.getCurrentUrl());
    verifyCurrentScreen(expectedHeading, expectedCurrencyCodes, expectedCurrencyDescriptions,
        expectedExchangeRates);
  }

  protected void verifySellCurrentScreen(String expectedHeading, String[] expectedCurrencyCodes,
      String[] expectedCurrencyDescriptions, String[] expectedExchangeRates) {
    assertEquals(ATTRIBUTE_URL, URL_SELL_CURRENT_SCREEN, webDriver.getCurrentUrl());
    verifyCurrentScreen(expectedHeading, expectedCurrencyCodes, expectedCurrencyDescriptions,
        expectedExchangeRates);
  }

  protected void verifyHistoryScreen(String expectedHeading, String[] expectedPeriods,
      String[] expectedCurrencyCodes, String[] expectedCurrencyDescriptions,
      Map<Integer, String[]> expectedExchangeRates) {
    String heading = getWebElementTextByTagName(TAG_HEADING);
    assertEquals(ATTRIBUTE_HEADING, expectedHeading, heading);
    String columnHeadingCurrencyCode =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_CODE + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE, COLUMN_HEADING_CURRENCY_CODE,
        columnHeadingCurrencyCode);
    String columnHeadingCurrencyDescription =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_DESCRIPTION + "]");
    assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION, COLUMN_HEADING_CURRENCY_DESCRIPTION,
        columnHeadingCurrencyDescription);
    for (int periodNumber = 1; periodNumber <= expectedPeriods.length; periodNumber++) {
      String period = getWebElementText("//table/thead/tr/th[" + (periodNumber + 2) + "]");
      assertEquals(ATTRIBUTE_PERIOD, expectedPeriods[periodNumber - 1], period);
    }
    for (int row = 1; row <= expectedCurrencyCodes.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      assertEquals(ATTRIBUTE_CURRENCY_CODE, expectedCurrencyCodes[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, expectedCurrencyDescriptions[row - 1],
          currencyDescription);
      for (int periodNumber = 1; periodNumber <= expectedPeriods.length; periodNumber++) {
        String exchangeRate =
            getWebElementText("//table/tbody/tr[" + row + "]/td[" + (periodNumber + 2) + "]");
        assertEquals(ATTRIBUTE_EXCHANGE_RATE,
            ((String[]) expectedExchangeRates.get(row))[periodNumber - 1], exchangeRate);
      }
    }
  }

  protected void verifyBuyHistoryScreen(String expectedHeading, String[] expectedPeriods,
      String[] expectedCurrencyCodes, String[] expectedCurrencyDescriptions,
      Map<Integer, String[]> expectedExchangeRates) {
    assertEquals(ATTRIBUTE_URL, URL_BUY_HISTORY_SCREEN, webDriver.getCurrentUrl());
    verifyHistoryScreen(expectedHeading, expectedPeriods, expectedCurrencyCodes,
        expectedCurrencyDescriptions, expectedExchangeRates);
  }

  protected void verifySellHistoryScreen(String expectedHeading, String[] expectedPeriods,
      String[] expectedCurrencyCodes, String[] expectedCurrencyDescriptions,
      Map<Integer, String[]> expectedExchangeRates) {
    assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, webDriver.getCurrentUrl());
    verifyHistoryScreen(expectedHeading, expectedPeriods, expectedCurrencyCodes,
        expectedCurrencyDescriptions, expectedExchangeRates);
  }

  protected void verifyBuyCurrentScreenDefaultCurrencies() {
    verifyBuyCurrentScreen(DEFAULT_HEADING_TEXT_BUY, DEFAULT_COMPARISON_CURRENCY_CODES,
        DEFAULT_COMPARISON_CURRENCY_DESCRIPTIONS, DEFAULT_EXCHANGE_RATES_BUY_CURRENT);
  }

  protected void verifySellCurrentScreenDefaultCurrencies() {
    verifySellCurrentScreen(DEFAULT_HEADING_TEXT_SELL, DEFAULT_COMPARISON_CURRENCY_CODES,
        DEFAULT_COMPARISON_CURRENCY_DESCRIPTIONS, DEFAULT_EXCHANGE_RATES_SELL_CURRENT);
  }

  protected void verifyBuyHistoryScreenDefaultCurrencies() {
    verifyBuyHistoryScreen(DEFAULT_HEADING_TEXT_BUY, PERIODS, DEFAULT_COMPARISON_CURRENCY_CODES,
        DEFAULT_COMPARISON_CURRENCY_DESCRIPTIONS, DEFAULT_EXCHANGE_RATES_BUY_HISTORY);
  }

  protected void verifySellHistoryScreenDefaultCurrencies() {
    verifySellHistoryScreen(DEFAULT_HEADING_TEXT_SELL, PERIODS, DEFAULT_COMPARISON_CURRENCY_CODES,
        DEFAULT_COMPARISON_CURRENCY_DESCRIPTIONS, DEFAULT_EXCHANGE_RATES_SELL_HISTORY);
  }

  protected void verifyBuyCurrentScreenCustomCurrencies() {
    verifyBuyCurrentScreen(CUSTOM_HEADING_TEXT_BUY, CUSTOM_COMPARISON_CURRENCY_CODES,
        CUSTOM_COMPARISON_CURRENCY_DESCRIPTIONS, CUSTOM_EXCHANGE_RATES_BUY_CURRENT);
  }

  protected void verifySellCurrentScreenCustomCurrencies() {
    verifySellCurrentScreen(CUSTOM_HEADING_TEXT_SELL, CUSTOM_COMPARISON_CURRENCY_CODES,
        CUSTOM_COMPARISON_CURRENCY_DESCRIPTIONS, CUSTOM_EXCHANGE_RATES_SELL_CURRENT);
  }

  protected void verifyBuyHistoryScreenCustomCurrencies() {
    verifyBuyHistoryScreen(CUSTOM_HEADING_TEXT_BUY, PERIODS, CUSTOM_COMPARISON_CURRENCY_CODES,
        CUSTOM_COMPARISON_CURRENCY_DESCRIPTIONS, CUSTOM_EXCHANGE_RATES_BUY_HISTORY);
  }

  protected void verifySellHistoryScreenCustomCurrencies() {
    verifySellHistoryScreen(CUSTOM_HEADING_TEXT_SELL, PERIODS, CUSTOM_COMPARISON_CURRENCY_CODES,
        CUSTOM_COMPARISON_CURRENCY_DESCRIPTIONS, CUSTOM_EXCHANGE_RATES_SELL_HISTORY);
  }

  protected void verifyMenuSelection(String expectedSubjectCurrencyCode,
      String[] expectedComparisonCurrencyCodes) {
    assertEquals(ATTRIBUTE_URL, URL_MENU, webDriver.getCurrentUrl());
    Select subjectCurrencySelect = new Select(getWebElementById(ELEMENT_ID_SUBJECT_CURRENCY));
    assertEquals(ATTRIBUTE_SUBJECT_CURRENCY, expectedSubjectCurrencyCode,
        subjectCurrencySelect.getFirstSelectedOption().getText());
    int comparisonCurrencyIndex = 0;
    for (String expectedComparisonCurrencyCode : expectedComparisonCurrencyCodes) {
      String deleteButtonId = ELEMENT_ID_PREFIX_COMPARISON_CURRENCY + comparisonCurrencyIndex++;
      WebElement deleteButton = getWebElementById(deleteButtonId);
      WebElement deleteButtonDiv = deleteButton.findElement(By.xpath(PARENT_ELEMENT_SELECTOR));
      String expectedComparisonCurrency =
          PREFIX_COMPARISON_CURRENCY + expectedComparisonCurrencyCode;
      String actualComparisonCurrency = deleteButtonDiv.getText();
      actualComparisonCurrency =
          actualComparisonCurrency.substring(0, expectedComparisonCurrency.length());
      assertEquals(ATTRIBUTE_COMPARISON_CURRENCY, expectedComparisonCurrency,
          actualComparisonCurrency);
    }
  }

  protected void verifyMenuSelectionDefaultCurrencies() {
    verifyMenuSelection(DEFAULT_SUBJECT_CURRENCY_CODE, DEFAULT_COMPARISON_CURRENCY_CODES);
  }

  protected void verifyMenuSelectionCustomCurrencies() {
    verifyMenuSelection(CUSTOM_SUBJECT_CURRENCY_CODE,
        CUSTOM_COMPARISON_CURRENCY_CODES_SELECTED_ORDER);
  }

}
