package hsbc.it;

import static hsbc.it.TestData.CURRENCY_CODE_EUROS;
import static hsbc.it.TestData.CURRENCY_CODE_LIRA;
import static hsbc.it.TestData.CURRENCY_CODE_SERBIAN_DINARS;
import static hsbc.it.TestData.CUSTOM_SUBJECT_CURRENCY_CODE;
import static hsbc.it.TestData.ELEMENT_ID_COMPARISON_CURRENCY;
import static hsbc.it.TestData.ELEMENT_ID_PREFIX_COMPARISON_CURRENCY;
import static hsbc.it.TestData.ELEMENT_ID_SUBJECT_CURRENCY;
import static hsbc.it.TestData.LINK_TEXT_BUY_CURRENT;
import static hsbc.it.TestData.LINK_TEXT_BUY_HISTORY;
import static hsbc.it.TestData.LINK_TEXT_MENU;
import static hsbc.it.TestData.LINK_TEXT_SELL_CURRENT;
import static hsbc.it.TestData.LINK_TEXT_SELL_HISTORY;
import static hsbc.it.TestData.URL_MENU;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MenuIT extends AbstractTestIT {

  @Test
  public void selectCustomCurrenciesDisplayBuyCurrent() {
    driver.get(URL_MENU);
    verifyMenuSelectionDefaultCurrencies();
    changeCurrencySelectionFromDefaultToCustom();
    verifyMenuSelectionCustomCurrencies();
    WebElement link = getLink(LINK_TEXT_BUY_CURRENT);
    link.click();
    verifyBuyCurrentScreenCustomCurrencies();
    link = getLink(LINK_TEXT_MENU);
    link.click();
    verifyMenuSelectionCustomCurrencies();
  }

  @Test
  public void selectCustomCurrenciesDisplaySellCurrent() {
    driver.get(URL_MENU);
    verifyMenuSelectionDefaultCurrencies();
    changeCurrencySelectionFromDefaultToCustom();
    verifyMenuSelectionCustomCurrencies();
    WebElement link = getLink(LINK_TEXT_SELL_CURRENT);
    link.click();
    verifySellCurrentScreenCustomCurrencies();
    link = getLink(LINK_TEXT_MENU);
    link.click();
    verifyMenuSelectionCustomCurrencies();
  }

  @Test
  public void selectCustomCurrenciesDisplayBuyHistory() {
    driver.get(URL_MENU);
    verifyMenuSelectionDefaultCurrencies();
    changeCurrencySelectionFromDefaultToCustom();
    verifyMenuSelectionCustomCurrencies();
    WebElement link = getLink(LINK_TEXT_BUY_HISTORY);
    link.click();
    verifyBuyHistoryScreenCustomCurrencies();
    link = getLink(LINK_TEXT_MENU);
    link.click();
    verifyMenuSelectionCustomCurrencies();
  }

  @Test
  public void selectCustomCurrenciesDisplaySellHistory() {
    driver.get(URL_MENU);
    verifyMenuSelectionDefaultCurrencies();
    changeCurrencySelectionFromDefaultToCustom();
    verifyMenuSelectionCustomCurrencies();
    WebElement link = getLink(LINK_TEXT_SELL_HISTORY);
    link.click();
    verifySellHistoryScreenCustomCurrencies();
    link = getLink(LINK_TEXT_MENU);
    link.click();
    verifyMenuSelectionCustomCurrencies();
  }

  private void changeCurrencySelectionFromDefaultToCustom() {
    Select subjectCurrencySelect = new Select(getWebElementById(ELEMENT_ID_SUBJECT_CURRENCY));
    subjectCurrencySelect.selectByVisibleText(CUSTOM_SUBJECT_CURRENCY_CODE);
    String deleteButtonId = ELEMENT_ID_PREFIX_COMPARISON_CURRENCY + 0;
    WebElement deleteButton = getWebElementById(deleteButtonId);
    deleteButton.click();
    deleteButton = getWebElementById(deleteButtonId);
    deleteButton.click();
    Select comparisonCurrencySelect = new Select(getWebElementById(ELEMENT_ID_COMPARISON_CURRENCY));
    comparisonCurrencySelect.selectByVisibleText(CURRENCY_CODE_EUROS);
    comparisonCurrencySelect = new Select(getWebElementById(ELEMENT_ID_COMPARISON_CURRENCY));
    comparisonCurrencySelect.selectByVisibleText(CURRENCY_CODE_SERBIAN_DINARS);
    comparisonCurrencySelect = new Select(getWebElementById(ELEMENT_ID_COMPARISON_CURRENCY));
    comparisonCurrencySelect.selectByVisibleText(CURRENCY_CODE_LIRA);
  }

}
