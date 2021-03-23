package pageobjets;

import automationFramework.BuildConstructor;
import automationFramework.PageActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.utils.Log;

import java.io.IOException;

public class LoginPage extends BuildConstructor {

    private final Logger LOGGER = LogManager.getLogger(LoginPage.class.getName());
    private WebDriver driver;
    Log logObj = new Log();

    @FindBy(xpath = "//span[text()='Log in']/../../..")
    public WebElement btnLogin;

    @FindBy(xpath = "//span[text()='Phone, email, or username']/../../following-sibling::div//input")
    public WebElement txtUsername;

    @FindBy(xpath = "//span[text()='Password']/../../following-sibling::div//input")
    public WebElement txtPassword;


    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String navigateToTwitterURL(String url) throws InterruptedException {
        LOGGER.info("Navigating to Twitter URL");
        logObj.startTestCase();
        String title = navigateToURL(url);
        LOGGER.info("The Twitter Title is: " + title);
        return title;
    }

    public void loginIntoTwitter(String username, String password) throws InterruptedException {
        LOGGER.info("Navigating To Twitter Home");
        fillTextByCharacter(txtUsername, username);
        fillTextByCharacter(txtPassword, password);
        clickWebElement(btnLogin);
    }

    public boolean verifyMandatoryFields(String username, String password) throws InterruptedException {
        LOGGER.info("Verifying Mandatory fields for login!");
        boolean result1 = false, result2 = false, result3= false;
        clickWebElement(btnLogin);
        fillTextByCharacter(txtUsername, "");
        fillTextByCharacter(txtPassword, password);
        try {
            clickWebElement(btnLogin);
            result1 = constructdivdiv("The username and password you entered did not match our records. Please double-check and try again.").isDisplayed();

        } catch (Exception e) {
            result1 = verifyUnclickability(btnLogin);
        }
        fillTextByCharacter(txtUsername, username);
        fillTextByCharacter(txtPassword, "");
        clickWebElement(btnLogin);
        waitForElementToAppear(constructdivdiv("The username and password you entered did not match our records. Please double-check and try again."));
        result2 = constructdivdiv("The username and password you entered did not match our records. Please double-check and try again.").isDisplayed();
        fillTextByCharacter(txtUsername, "");
        fillTextByCharacter(txtPassword, "");

        try {
            clickWebElement(btnLogin);
            result3 = constructdivdiv("The username and password you entered did not match our records. Please double-check and try again.").isDisplayed();

        } catch (Exception e) {
            result3 = verifyUnclickability(btnLogin);
        }
        return result1 && result2 && result3;
    }
}
