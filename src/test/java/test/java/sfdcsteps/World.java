package test.java.sfdcsteps;

import io.cucumber.core.api.Scenario;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class World {
    public WebDriver driver;
    private static Map<Integer, String> testCaseResultMap;
    public String caseId;
    public String originalWindow;
    public String currentCancellationStatus;
    public String contact;
    public Boolean allBulkStepsHavePassed = true;
    public String orderNumber;
    public String scenarioName;
    public List<String> temporaryPhoneNumbersToBeDeleted = new ArrayList<>();
    public List<String> phoneNumbersToBeDeleted = new ArrayList<>();
    public String contactId;
    public String accountName;
    public Connection mysqlConnection;


    public static Map<Integer, String> getTestCaseResultMap() {
        return testCaseResultMap;
    }

    public static void setTestCaseResultMap(Map<Integer, String> testCaseResultMap) {
        World.testCaseResultMap = testCaseResultMap;
    }

}
