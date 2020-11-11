package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
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
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(4000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(4000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (hiveAppCount > 0) {
            // Get 1st username from table for hive apps
            String filterByUsername = applicationsPageObject.getUsernameFromTable.getText().trim();
            String filterByQueue = applicationsPageObject.getQueueNameTable.getText().trim();
            test.log(LogStatus.INFO, "Username and Queuename found for hive apps are- " + filterByUsername + " " + filterByQueue);
            LOGGER.info("Select 'Only' hive from app types and get its jobs count");
            String upTo10CharUserName = null;
            String upTo10CharQueueName = null;
            if (!filterByUsername.equals("-")) {
                if (filterByUsername.length() > 10) {
                    upTo10CharUserName = StringUtils.left(filterByUsername, 10);
                    LOGGER.info("User name should be filtered by- " + upTo10CharUserName);
                    test.log(LogStatus.INFO, "Username should be filtered by- " + upTo10CharUserName);

                } else if (filterByUsername.length() <= 10 && filterByUsername.length() > 0) {
                    upTo10CharUserName = filterByUsername;
                    LOGGER.info("User name should be filtered by- " + upTo10CharUserName);
                    test.log(LogStatus.INFO, "Username should be filtered by- " + upTo10CharUserName);
                }
            }
            waitExecuter.sleep(2000);
            if (!filterByQueue.equals("-")) {
                if (filterByQueue.length() > 10) {
                    // Get 1st queuename from table for hive apps
                    upTo10CharQueueName = StringUtils.left(filterByQueue, 10);
                    LOGGER.info("Queue name should be filtered by- " + upTo10CharQueueName);
                    test.log(LogStatus.INFO, "Queue should be filtered by- " + upTo10CharQueueName);

                } else if (filterByQueue.length() <= 10 && filterByQueue.length() > 0) {
                    upTo10CharQueueName = filterByQueue;
                    LOGGER.info("Queue name should be filtered by- " + upTo10CharQueueName);
                    test.log(LogStatus.INFO, "Queue should be filtered by- " + upTo10CharQueueName);
                }
            }
            waitExecuter.sleep(2000);
            // Click on user searchbox and get all usernames.
            test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
            LOGGER.info("Click on user searchbox and get all usernames.");
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
            applicationsPageObject.userSearchBox.click();
            waitExecuter.sleep(2000);
            applicationsPageObject.userSearchBox.sendKeys(upTo10CharUserName);
            List<WebElement> userList = applicationsPageObject.getNamesFromDropDown;
            String usernameSelected = null;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getText().contains(filterByUsername)) {
                    usernameSelected = userList.get(i).getText();
                    LOGGER.info("Selected username from dropdown " + usernameSelected);
                    userList.get(i).click();
                    waitExecuter.sleep(2000);
                    break;
                }
            }
            applicationsPageObject.queueNameSearchBox.click();
            waitExecuter.sleep(1000);
            applicationsPageObject.queueNameSearchBox.sendKeys(upTo10CharQueueName);
            waitExecuter.sleep(1000);
            List<WebElement> queueList = applicationsPageObject.getNamesFromDropDown;
            String queuenameSelected = null;
            for (int i = 0; i < queueList.size(); i++) {
                if (queueList.get(i).getText().contains(upTo10CharQueueName)) {
                    queuenameSelected = queueList.get(i).getText();
                    LOGGER.info("Selected username from dropdown " + queuenameSelected);
                    queueList.get(i).click();
                    waitExecuter.sleep(2000);
                    break;
                }
            }

            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.jobsPageHeader);
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            if (totalCount > 0) {
                String usernameFromTable = applicationsPageObject.getUsernameFromTable.getAttribute("title");
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

            waitExecuter.sleep(2000);
            applicationsPageObject.userSearchBox.click();
            waitExecuter.sleep(2000);
            userList.get(0).click();
            waitExecuter.sleep(2000);
            // Reset username filter to default
            test.log(LogStatus.INFO, "Reset username filter");
            allApps.reset();
        }
    }
}
