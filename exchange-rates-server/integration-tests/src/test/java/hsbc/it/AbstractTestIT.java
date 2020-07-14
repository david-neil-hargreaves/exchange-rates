package hsbc.it;

import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.function.Function;
import org.junit.After;
import org.junit.Before;
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
     //driver.close();
  }

  protected WebElement getWebElement(final String xpath) {
    return driver.findElement(By.xpath(xpath));
    /*WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
      public WebElement apply(WebDriver driver) {
        return driver.findElement(By.xpath(xpath));
      }
    });
    return webElement;*/
  }

  protected String getWebElementText(final String xpath) {
    return getWebElement(xpath).getText();
  }

  protected WebElement getWebElementByTagName(final String tagName) {
    return driver.findElement(By.tagName(tagName));
    /*WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
      public WebElement apply(WebDriver driver) {
        return driver.findElement(By.tagName(tagName));
      }
    });
    return webElement;*/
  }

  protected String getWebElementTextByTagName(final String tagName) {
    return getWebElementByTagName(tagName).getText();
  }

}
