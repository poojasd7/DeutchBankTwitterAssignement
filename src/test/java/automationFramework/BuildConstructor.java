package automationFramework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class BuildConstructor extends PageActions {

    private final Logger LOGGER = LogManager.getLogger(PageActions.class.getName());
    public Integer EXPLICIT_WAIT_60_SEC = 60;
    private WebDriver driver;

    public BuildConstructor(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement constructdivspan(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//div/span[contains(text(), '" + differenceInSelector + "')]");
        return driver.findElement(by);
    }

    public WebElement constructdivdiv(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//div/div[contains(text(), '" + differenceInSelector + "')]");
        return driver.findElement(by);
    }

    public WebElement constructdivinput(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//div/input[@placeholder, '" + differenceInSelector + "']");
        return driver.findElement(by);
    }


    public WebElement constructspanspan(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//span/span[contains(text(), '" + differenceInSelector + "')]");
        return driver.findElement(by);
    }

    public WebElement constructlispan(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//li//span[contains(text(), '" + differenceInSelector + "')]");
        return driver.findElement(by);
    }

    public WebElement constructdivWithAriaLabelValue(String differenceInSelector) throws InterruptedException {
        By by = By.xpath("//div[@aria-label='" + differenceInSelector + "']");
        return driver.findElement(by);
    }

    public WebElement constructDivSpanWithTextFollowedByDivTextArea(String fieldText) {
        By by = By.xpath("//div/span[text()='" + fieldText + "']/../../following-sibling::div//textarea");
        return driver.findElement(by);
    }
}
