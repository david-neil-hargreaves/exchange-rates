package hsbc.it;

import static hsbc.it.TestData.ATTRIBUTE_URL;
import static hsbc.it.TestData.URL_MENU;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuIT extends AbstractTestIT {

  @Test
  public void navigateToMenu() {
    driver.get(URL_MENU);
    Assert.assertEquals(ATTRIBUTE_URL, URL_MENU, driver.getCurrentUrl());
    WebElement subjectCurrencySelect = getWebElementById("subjectCurrency");
    WebElement subjectCurrencySelectDiv = subjectCurrencySelect.findElement(By.xpath("parent::node()"));
    //WebElement deleteButton = getWebElementById("deleteComparisonCurrency0");
    //WebElement deleteButtonDiv = deleteButton.findElement(By.xpath("parent::node()"));
    WebElement deleteButton = getWebElementByTagName("button");
    WebElement deleteButtonDiv = deleteButton.findElement(By.xpath("parent::node()"));
    String expectedComparisonCurrency = "Comparison currency: GBP";
    String actualComparisonCurrency = deleteButtonDiv.getText();
    actualComparisonCurrency = actualComparisonCurrency.substring(0, expectedComparisonCurrency.length());
    Assert.assertEquals("Comparison Currency", expectedComparisonCurrency, actualComparisonCurrency);
  }

}
