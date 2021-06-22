package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_40_PART1 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_40_PART1.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyFilterByUserQueue(String clusterId) {
        test = extent.startTest("TC_HIVE_40_PART1.VerifyFilterByUserQueue",
                "Verify that User is able to see the apps sorted based on Users and Queue.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        UserActions userAction = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userAction.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (hiveAppCount > 0) {
            // Get 1st username from table for hive apps
            String filterByUsername = applicationsPageObject.getUsernameFromTable.getText().trim();
            String upTo10CharUserName = StringUtils.left(filterByUsername, 10);
            LOGGER.info("User name should be filtered by- " + upTo10CharUserName);
            // Get 1st queuename from table for hive apps
            String filterByQueue = applicationsPageObject.getQueueNameTable.getText().trim();
            String upTo10CharQueueName = StringUtils.left(filterByQueue, 10);
            LOGGER.info("Queue name should be filtered by- " + upTo10CharQueueName);
            waitExecuter.sleep(2000);
            // Click on user searchbox and get all usernames.
            test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
            LOGGER.info("Click on user searchbox and get all usernames.");
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.userExpandableHeader);
            userAction.performActionWithPolling(applicationsPageObject.userExpandableHeader, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.userSearchBox);
            userAction.performActionWithPolling(applicationsPageObject.userSearchBox, UserAction.CLICK);
            waitExecuter.sleep(2000);
            applicationsPageObject.userSearchBox.sendKeys(upTo10CharUserName);
            List<WebElement> userList = applicationsPageObject.getNamesFromDropDown;
            String usernameSelected = null;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getText().equals(filterByUsername)) {
                    usernameSelected = userList.get(i).getText();
                    LOGGER.info("Selected username from dropdown " + usernameSelected);
                    waitExecuter.waitUntilElementClickable(userList.get(i));
                    userAction.performActionWithPolling(userList.get(i), UserAction.CLICK);
                    waitExecuter.waitUntilElementClickable(applicationsPageObject.userExpandableHeader);
                    waitExecuter.sleep(2000);
                    break;
                }
            }
            if (!upTo10CharQueueName.trim().isEmpty() || !upTo10CharQueueName.trim().equals("-")) {
                waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
                userAction.performActionWithPolling(applicationsPageObject.queueExpandableHeader, UserAction.CLICK);
                waitExecuter.waitUntilElementClickable(applicationsPageObject.queueNameSearchBox);
                userAction.performActionWithPolling(applicationsPageObject.queueNameSearchBox, UserAction.CLICK);
                waitExecuter.sleep(1000);
                applicationsPageObject.queueNameSearchBox.sendKeys(upTo10CharQueueName);
                waitExecuter.sleep(1000);
                List<WebElement> queueList = applicationsPageObject.getNamesFromDropDown;
                String queuenameSelected = null;
                if (!upTo10CharQueueName.isEmpty() || !upTo10CharQueueName.equals("-"))
                    for (int i = 0; i < queueList.size(); i++) {
                        if (queueList.get(i).getText().equals(upTo10CharQueueName)) {
                            queuenameSelected = queueList.get(i).getText();
                            LOGGER.info("Selected username from dropdown " + queuenameSelected);
                            waitExecuter.waitUntilElementClickable(queueList.get(i));
                            userAction.performActionWithPolling(queueList.get(i), UserAction.CLICK);
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.queueExpandableHeader);
                            waitExecuter.sleep(2000);
                            break;
                        }
                    }
            }
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            if (totalCount > 0) {
                String usernameFromTable = applicationsPageObject.getUsernameFromTable.getText().trim();
                LOGGER.info("Username displayed in table " + usernameFromTable);
                Assert.assertEquals(usernameFromTable, usernameSelected,
                        "The application in table contains username other than that of " + usernameFromTable);
                test.log(LogStatus.PASS, "The application in table contains username i.e. selected in filter");
            } else {
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it");
                test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
                // Reset username filter to default
                test.log(LogStatus.INFO, "Reset username filter");
                allApps.reset();
                throw new SkipException("The clusterId does not have any application under it");
            }
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.userSearchBox);
            userAction.performActionWithPolling(applicationsPageObject.userSearchBox, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(userList.get(0));
            userAction.performActionWithPolling(userList.get(0), UserAction.CLICK);
            // Reset username filter to default
            test.log(LogStatus.INFO, "Reset username filter");
            allApps.reset();
        }
    }
}
