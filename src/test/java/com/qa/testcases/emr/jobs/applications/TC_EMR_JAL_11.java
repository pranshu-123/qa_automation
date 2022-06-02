package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
@Marker.EMRAllApps
public class TC_EMR_JAL_11 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_11.class);

    @Test()
    public void VerifyUsernameFilter() {
        test = extent.startTest("TC_EMR_JAL_11.VerifyUsernameFilter",
                "Verify application is listed only of selected username");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);

        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Click on user searchbox and get all usernames.
        test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
        LOGGER.info("Click on user searchbox and get all usernames.",test);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.sleep(2000);

        waitExecuter.waitUntilElementClickable(applicationsPageObject.userExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.userExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.userExpandableHeader);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.userSearchBox);
        userActions.performActionWithPolling(applicationsPageObject.userSearchBox,UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.userSearchBox);
        List<WebElement> userList = applicationsPageObject.getNamesFromDropDown;
        waitExecuter.sleep(1000);
        String usernameSelected = userList.get(0).getText();
        waitExecuter.waitUntilElementClickable(userList.get(0));
        userList.get(0).click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(2000);
        LOGGER.info("Selected username from dropdown " + usernameSelected,test);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        if (totalCount > 0) {
            String usernameFromTable = applicationsPageObject.getUsernameFromTable.getAttribute("title");
            LOGGER.info("Username displayed in table " + usernameFromTable,test);
            Assert.assertTrue(usernameSelected.contains(usernameFromTable),
                    "The application in table contains username other than that of " + usernameFromTable);
            test.log(LogStatus.PASS, "The application in table matches username: " + usernameFromTable);
        } else
            Assert.assertTrue(applicationsPageObject.whenApplicationPresent.isDisplayed(),
                    "The clusterId has application under it and displays available data for it");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.resetButton);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

    }
}


