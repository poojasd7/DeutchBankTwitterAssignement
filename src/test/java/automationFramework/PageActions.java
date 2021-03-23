package automationFramework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class PageActions {

    private final Logger LOGGER = LogManager.getLogger(PageActions.class.getName());
    public Integer EXPLICIT_WAIT_20_SEC = 20;
    public Integer EXPLICIT_WAIT_60_SEC = 60;
    public Integer EXPLICIT_WAIT_120_SEC = 120;
    public Integer EXPLICIT_WAIT_180_SEC = 180;
    public StringBuffer VERIFICATION_ERRORS = new StringBuffer();

    private WebDriver driver;

    public PageActions(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 140);
        wait.pollingEvery(Duration.ofSeconds(1));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean verifyWebElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_180_SEC);
        wait.pollingEvery(Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException | ErrorHandler.UnknownServerException | TimeoutException e) {
            driver.navigate().refresh();
            VERIFICATION_ERRORS.append("Element: " + element + " is not present on page \n -Caugth exception: "
                    + e.getMessage() + "\n\n");
            return false;
        }
    }

    public void executeJavaScriptScroll(WebElement element) throws InterruptedException {
        waitUntilClickable(element);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void waitForElementToAppear(WebElement element) {
        await().ignoreExceptions().atMost(180, SECONDS).pollInterval(3, SECONDS)
                .until(() -> verifyWebElementPresent(element));

    }

    public void waitForElementToAppearBy(By by) {
        await().ignoreExceptions().atMost(110, SECONDS).pollInterval(1, SECONDS).pollDelay(10, SECONDS)
                .until(() -> verifyWebElementByVisible(by, 1));
    }

    public Boolean verifyWebElementByVisible(By by, int explicitWait) {
        WebDriverWait wait = new WebDriverWait(driver, explicitWait);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (NoSuchElementException | NoSuchFrameException | NoSuchWindowException | ErrorHandler.UnknownServerException | TimeoutException e) {
            VERIFICATION_ERRORS.append("By: ").append(by).append(" is not visible on page \n -Caugth exception: ").append(e.getMessage()).append("\n\n");
            return false;
        }
    }

    public void clickWebElement(WebElement element) throws InterruptedException {
        try {
            element.click();
        }
        catch (Exception e) {
            waitForElementToAppear(element);
            waitUntilClickable(element);
            executeJavaScriptScroll(element);
        }
    }

    public boolean verifyUnclickability(WebElement element) throws InterruptedException {
        if(element.getAttribute("aria-disabled").toString().equalsIgnoreCase("true"))
            return true;
        else return false;
    }

    public void fillTextByCharacter(WebElement element, String stringToEnter) throws InterruptedException {
        LOGGER.info("Filling following text in the field: " + stringToEnter);
        clickWebElement(element);
        element.clear();
        for (Character ch : stringToEnter.toCharArray()) {
            element.sendKeys(ch.toString());
        }
    }

    public String navigateToURL(String url) {
        LOGGER.info("Navigate to url: " + url);
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.get(url);
        return driver.getTitle();
    }
}