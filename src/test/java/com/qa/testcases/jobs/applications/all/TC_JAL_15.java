package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */

@Marker.AllApps
@Marker.GCPAllApps
@Marker.All
public class TC_JAL_15 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_15.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyQueueFilter(String clusterId) {
        test = extent.startTest("TC_JAL_15.VerifyQueueFilter",
                "Verify application is listed only of selected queue name");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);

        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Click on user searchbox and get all usernames.
        test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
        LOGGER.info("Click on user searchbox and get all usernames.");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.queueNameSearchBox);

        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.queueExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.queueNameSearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        List<WebElement> queueNameList = applicationsPageObject.getNamesFromDropDown;
        String queuenameSelected = queueNameList.get(0).getText();
        waitExecuter.waitUntilElementClickable(queueNameList.get(0));
        userActions.performActionWithPolling(queueNameList.get(0), UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        LOGGER.info("Selected username from dropdown " + queuenameSelected);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        if (totalCount > 0) {
            String usernameFromTable = applicationsPageObject.getQueueNameTable.getText();
            LOGGER.info("Username displayed in table " + usernameFromTable);
            LOGGER.info("queuenameSelected displayed in table " + queuenameSelected);
            Assert.assertEquals(usernameFromTable, queuenameSelected,
                    "The application in table contains username other than that of " + usernameFromTable);
            test.log(LogStatus.PASS, "The application in table contains username: " + usernameFromTable);
        } else
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display" +
                            " 'No Data Available' for it");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueNameSearchBox);
        userActions.performActionWithPolling(applicationsPageObject.queueNameSearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueNameSearchBox);
        waitExecuter.waitUntilElementClickable(queueNameList.get(0));
        userActions.performActionWithPolling(queueNameList.get(0), UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueNameSearchBox);

        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.resetButton);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

}
