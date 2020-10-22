package com.qa.testcases.jobs.applications.all;

import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
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
        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Click on user searchbox and get all usernames.
        test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
        LOGGER.info("Click on user searchbox and get all usernames.");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.queueNameSearchBox);
        applicationsPageObject.queueNameSearchBox.click();
        waitExecuter.sleep(1000);
        List<WebElement> queueNameList = applicationsPageObject.getNamesFromDropDown;
        waitExecuter.sleep(2000);
        String queuenameSelected = queueNameList.get(0).getText();
        queueNameList.get(0).click();
        waitExecuter.sleep(2000);
        LOGGER.info("Selected username from dropdown " + queuenameSelected);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.jobsPageHeader);
        waitExecuter.sleep(2000);
        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(2000);
        if (totalCount > 0) {
            String usernameFromTable = applicationsPageObject.getQueueNameTable.getText();
            LOGGER.info("Username displayed in table " + usernameFromTable);
            Assert.assertEquals(usernameFromTable, queuenameSelected,
                    "The application in table contains username other than that of " + usernameFromTable);
            test.log(LogStatus.PASS, "The application in table contains username: " + usernameFromTable);
        } else
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display" +
                            " 'No Data Available' for it");
        waitExecuter.sleep(2000);
        applicationsPageObject.queueNameSearchBox.click();
        waitExecuter.sleep(2000);
        queueNameList.get(0).click();
        waitExecuter.sleep(2000);
    }

}