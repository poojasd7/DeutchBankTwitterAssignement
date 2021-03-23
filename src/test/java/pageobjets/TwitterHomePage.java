package pageobjets;

import automationFramework.BuildConstructor;
import automationFramework.PageActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.utils.Log;
import org.openqa.selenium.*;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class TwitterHomePage extends BuildConstructor {

    private final Logger LOGGER = LogManager.getLogger(TwitterHomePage.class.getName());
    private WebDriver driver;
    Log logObj = new Log();

    @FindBy(xpath = "//span[text()='Log in']/../..")
    public WebElement btnLogin;

    @FindBy(xpath = "//span[text()='Phone, email, or username']/../../following-sibling::div//input")
    public WebElement txtUsername;

    @FindBy(xpath = "//span[text()='Password']/../../following-sibling::div//input")
    public WebElement txtPassword;


    public TwitterHomePage(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void uploadPhoto() throws InterruptedException {
        String currentDir = System.getProperty("user.dir");
        String filename = currentDir + "\\src\\test\\resources\\data\\photo.png";
        waitForElementToAppear(constructdivspan("Profile"));
        clickWebElement(constructdivspan("Profile"));
        clickWebElement(constructspanspan("Edit profile"));
        clickWebElement(constructdivWithAriaLabelValue("Add avatar photo"));
        constructdivWithAriaLabelValue("Add avatar photo").sendKeys(filename);
        clickWebElement(constructspanspan("Apply"));
        clickWebElement(constructspanspan("Save"));
        clickWebElement(constructdivWithAriaLabelValue("Close"));
    }

    public void updateBio() throws InterruptedException {
        clickWebElement(constructdivspan("Profile"));
        clickWebElement(constructspanspan("Edit profile"));
        fillTextByCharacter(constructDivSpanWithTextFollowedByDivTextArea("Bio"), "Selenium Automation user");
        clickWebElement(constructspanspan("Save"));
    }

    public void getTwitsOfPast2Hours() throws InterruptedException {
        navigateToURL("https://twitter.com/home");
        fillTextByCharacter(constructDivSpanWithTextFollowedByDivTextArea("Search Twitter"), "TOI Pune");
        clickWebElement(constructlispan("TOI Pune"));
        List<WebElement> tweets = driver.findElements(By.xpath("((//time[contains(text(),'2h')] |  //time[contains(text(),'1h')] | //time[contains(text(),'m')]  | //time[contains(text(),'s')])/ancestor::div[@class='css-1dbjc4n r-1iusvr4 r-16y2uox r-1777fci r-kzbkwu']/div[2]//div[1][@lang='en']//span[@class='css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0'])"));
        LOGGER.info("Tweets are:");
        for(WebElement tweet: tweets) {
            String tweetText = tweet.getText();
            if(tweetText.length()>50) {
                for(int i=0; i<tweetText.length(); ){
                    LOGGER.info(i+1+"/3"+":"+tweetText.substring(i,tweetText.length()/3));
                    i = tweetText.length()/3;
                }
            }
            else
            LOGGER.info(tweetText);
        }
    }

}