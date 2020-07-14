package hsbc.it;

import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_CODE;
import static hsbc.it.TestData.ATTRIBUTE_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.ATTRIBUTE_EXCHANGE_RATE;
import static hsbc.it.TestData.ATTRIBUTE_HEADING;
import static hsbc.it.TestData.ATTRIBUTE_URL;
import static hsbc.it.TestData.COLUMN_CURRENCY_CODE;
import static hsbc.it.TestData.COLUMN_CURRENCY_DESCRIPTION;
import static hsbc.it.TestData.COLUMN_EXCHANGE_RATE;
import static hsbc.it.TestData.CURRENCY_CODES;
import static hsbc.it.TestData.CURRENCY_DESCRIPTIONS;
import static hsbc.it.TestData.EXCHANGE_RATES_BUY_CURRENT;
import static hsbc.it.TestData.TAG_HEADING;
import static hsbc.it.TestData.TAG_HEADING_TEXT_BUY;
import static hsbc.it.TestData.URL_BUY_CURRENT_SCREEN;

import org.junit.Assert;
import org.junit.Test;

public class BuyCurrentScreenIT extends AbstractTestIT {

  @Test
  public void navigateToBuyCurrentScreen() {
    driver.get(URL_BUY_CURRENT_SCREEN);
    Assert.assertEquals(ATTRIBUTE_URL, URL_BUY_CURRENT_SCREEN, driver.getCurrentUrl());
    String heading = getWebElementTextByTagName(TAG_HEADING);
    Assert.assertEquals(ATTRIBUTE_HEADING, TAG_HEADING_TEXT_BUY, heading);
    for (int row = 1; row <= CURRENCY_CODES.length; row++) {
      String currencyCode =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_CODE + "]");
      Assert.assertEquals(ATTRIBUTE_CURRENCY_CODE, CURRENCY_CODES[row - 1], currencyCode);
      String currencyDescription = getWebElementText(
          "//table/tbody/tr[" + row + "]/td[" + COLUMN_CURRENCY_DESCRIPTION + "]");
      Assert.assertEquals(ATTRIBUTE_CURRENCY_DESCRIPTION, CURRENCY_DESCRIPTIONS[row - 1],
          currencyDescription);
      String exchangeRate =
          getWebElementText("//table/tbody/tr[" + row + "]/td[" + COLUMN_EXCHANGE_RATE + "]");
      Assert.assertEquals(ATTRIBUTE_EXCHANGE_RATE, EXCHANGE_RATES_BUY_CURRENT[row - 1],
          exchangeRate);
    }
  }

}
