package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
@Marker.DbxRuns
@Marker.All
public class TC_DR_60 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_60.class);

    @Test()
    public void VerifyUserSearchInRunningTab() {
        test = extent.startTest("TC_DR_60.VerifyUserSearchInRunningTab ",
                "Verify application is listed only of selected username");
        test.assignCategory("Jobs-Runs/Running");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to Runs tab from header");
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Running");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            // Click on user searchbox and get all usernames.
            test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
            loggingUtils.info("Click on user searchbox and get all usernames.", test);
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.userSearchBox);

            waitExecuter.waitUntilElementClickable(dbpageObject.userExpandableHeader);
            waitExecuter.waitUntilElementClickable(dbpageObject.userSearchBox);
            userActions.performActionWithPolling(dbpageObject.userSearchBox, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(dbpageObject.userSearchBox);
            List<WebElement> userList = dbpageObject.getNamesFromDropDown;
            waitExecuter.sleep(1000);
            String usernameSelected = userList.get(0).getText();
            waitExecuter.waitUntilElementClickable(userList.get(0));
            userList.get(0).click();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            waitExecuter.sleep(2000);
            loggingUtils.info("Selected username from dropdown " + usernameSelected, test);
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.globalSearchBox);
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            if (totalCount > 0) {
                String usernameFromTable = dbpageObject.getUsernameFromFinishedTable.getAttribute("title");
                loggingUtils.info("Username displayed in table " + usernameFromTable, test);
                Assert.assertTrue(usernameSelected.contains(usernameFromTable),
                        "The application in table contains username other than that of " + usernameFromTable);
                test.log(LogStatus.PASS, "The application in table matches username: " + usernameFromTable);
            } else
                Assert.assertTrue(dbpageObject.whenApplicationPresent.isDisplayed(),
                        "The clusterId has application under it and displays available data for it");
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.resetButton);
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            userActions.performActionWithPolling(dbpageObject.resetButton, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}