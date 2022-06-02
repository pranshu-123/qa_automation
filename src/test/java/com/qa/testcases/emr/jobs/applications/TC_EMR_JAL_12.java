package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EMRAllApps
public class TC_EMR_JAL_12 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_12.class);

    @Test()
    public void VerifyQueueFilter() {
        test = extent.startTest("TC_EMR_JAL_12.VerifyQueueFilter",
                "Verify application is listed only of selected queue name");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        EMRAllApps allApps = new EMRAllApps(driver);
        UserActions userActions = new UserActions(driver);

        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Click on user searchbox and get all usernames.
        test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
        LOGGER.info("Click on user searchbox and get all usernames.", test);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.queueNameSearchBox);


        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.queueExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.queueNameSearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        List<WebElement> queueNameList = applicationsPageObject.getQueueNamesFromDropDown;
        String queuenameSelected = queueNameList.get(0).getText();
        waitExecuter.waitUntilElementClickable(queueNameList.get(0));
        userActions.performActionWithPolling(queueNameList.get(0), UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
        LOGGER.info("Selected username from dropdown " + queuenameSelected, test);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        if (totalCount > 0) {
            String usernameFromTable = applicationsPageObject.getQueueNameTable.getText();
            LOGGER.info("Username displayed in table " + usernameFromTable, test);
            LOGGER.info("queuenameSelected displayed in table " + queuenameSelected, test);
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
