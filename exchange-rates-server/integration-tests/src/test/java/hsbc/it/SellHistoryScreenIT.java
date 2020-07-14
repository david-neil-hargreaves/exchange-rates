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
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_HEADING_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.CURRENCY_CODES;
import static hsbc.it.TestData.CURRENCY_DESCRIPTIONS;
import static hsbc.it.TestData.EXCHANGE_RATES_SELL_HISTORY;
import static hsbc.it.TestData.PERIODS;
import static hsbc.it.TestData.TAG_HEADING;
import static hsbc.it.TestData.TAG_HEADING_TEXT_SELL;
import static hsbc.it.TestData.URL_SELL_HISTORY_SCREEN;

import org.junit.Assert;
import org.junit.Test;

public class SellHistoryScreenIT extends AbstractTestIT {

  @Test
  public void navigateToSellHistoryScreen() throws InterruptedException {
    driver.get(URL_SELL_HISTORY_SCREEN);
    Assert.assertEquals(ATTRIBUTE_URL, URL_SELL_HISTORY_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    Assert.assertEquals(ATTRIBUTE_HEADING, TAG_HEADING_TEXT_SELL, heading);
    String columnHeadingCurrencyCode =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_CODE + "]");
    Assert.assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_CODE, COLUMN_HEADING_CURRENCY_CODE,
        columnHeadingCurrencyCode);
    String columnHeadingCurrencyDescription =
        getWebElementText("//table/thead/tr/th[" + COLUMN_CURRENCY_DESCRIPTION + "]");
    Assert.assertEquals(ATTRIBUTE_COLUMN_HEADING_CURRENCY_DESCRIPTION,
        COLUMN_HEADING_CURRENCY_DESCRIPTION, columnHeadingCurrencyDescription);
    for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
      String period = getWebElementText("//table/thead/tr/th[" + (periodNumber + 2) + "]");
      Assert.assertEquals(ATTRIBUTE_PERIOD, PERIODS[periodNumber - 1], period);
    }
    for (int row = 1; row <= CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      Assert.assertEquals(ATTRIBUTE_CURRENCY_CODE, CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      Assert.assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      for (int periodNumber = 1; periodNumber <= PERIODS.length; periodNumber++) {
        String exchangeRate =
            getWebElementText("//table/tbody/tr[" + row + "]/td[" + (periodNumber + 2) + "]");
        Assert.assertEquals(ATTRIBUTE_EXCHANGE_RATE,
            ((String[]) EXCHANGE_RATES_SELL_HISTORY.get(row))[periodNumber - 1], exchangeRate);
      }
    }
  }

}
