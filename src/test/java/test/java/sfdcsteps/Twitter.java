package test.java.sfdcsteps;

import automationFramework.Driver;
import automationFramework.PageActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import pageobjets.LoginPage;
import pageobjets.TwitterHomePage;

import java.io.IOException;

public class Twitter extends PageActions {
    private final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Twitter.class.getName());
    private World world;
    public String REASSIGN_TO_DEPLOYMENT_CONFIRMATION_MESSAGE = "Thank you. Your Case has been escalated and reassigned to the Deployment team.";
    public String REASSIGN_TO_BILLING_CONFIRMATION_MESSAGE = "Case Reassigned To Billing";
    Driver driverObj;
    LoginPage loginpage;

    public Twitter(World world) throws IOException {
        super(world.driver);
        this.world = world;
        driverObj = new Driver(world);
        driverObj.startWebDriver();
        loginpage = new LoginPage(world.driver);
    }

    @Given("^user navigates to the Twitter URL$")
    public void twitterIsLoaded() throws Throwable {
        String url = "www.twitter.com";
        Assert.assertTrue("Twitter page has not opened up!", loginpage.navigateToTwitterURL(url).contains("Twitter"));
    }


    @And("user logs in into twitter")
    public void userLogsIntoTwitter() throws Throwable {
        String url = "www.twitter.com";
        loginpage.loginIntoTwitter("SeleniumUser3", "Selenium@58");
    }

    @And("user validates mandatory fields for login of twitter")
    public void userVerifiesMandatoryFieldsOfLoginPage() throws Throwable {
       Assert.assertTrue("Username and Password being mandatory is not verified!" , loginpage.verifyMandatoryFields("SeleniumUser3", "Selenium@58"));
    }

    @And("user uploads profile photo")
    public void userUploadsPhoto() throws Throwable {
        TwitterHomePage twitterHomePage = new TwitterHomePage(world.driver);
        twitterHomePage.uploadPhoto();
    }

    @And("user updates profile bio")
    public void userUpdatesBio() throws Throwable {
        TwitterHomePage twitterHomePage = new TwitterHomePage(world.driver);
        twitterHomePage.updateBio();
    }

    @And("user gets times of india tweets of past 2 hours")
    public void userGetsTwits() throws Throwable {
        TwitterHomePage twitterHomePage = new TwitterHomePage(world.driver);
        twitterHomePage.getTwitsOfPast2Hours();
    }


}
