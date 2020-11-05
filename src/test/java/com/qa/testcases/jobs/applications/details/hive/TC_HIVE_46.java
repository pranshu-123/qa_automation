package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_46 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_46.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByColumns(String clusterId) {
        test = extent.startTest("TC_HIVE_46.VerifySortByColumns", "Verify that sorting works for Different columns");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
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
        datePicker.selectLastMonth();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Sort Up by Status
        test.log(LogStatus.INFO, "Sort Up by Status");
        LOGGER.info("Sort up by Status");
        applicationsPageObject.sortStatus.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Status
        test.log(LogStatus.INFO, "Sort down by status");
        LOGGER.info("Sort down by status");
        applicationsPageObject.sortStatus.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Status.");
        // Sort Up by User
        test.log(LogStatus.INFO, "Sort Up by user");
        LOGGER.info("Sort up by User");
        applicationsPageObject.sortByUser.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by User
        test.log(LogStatus.INFO, "Sort down by user");
        LOGGER.info("Sort down by user");
        applicationsPageObject.sortByUser.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Status.");
        // Sort Up by ClusterId
        test.log(LogStatus.INFO, "Sort Up by ClusterId");
        LOGGER.info("Sort up by ClusterId");
        applicationsPageObject.sortByClusterId.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by ClusterId
        test.log(LogStatus.INFO, "Sort down by ClusterId");
        LOGGER.info("Sort down by ClusterId");
        applicationsPageObject.sortByClusterId.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on ClusterId.");
        // Sort Up by Start Time
        test.log(LogStatus.INFO, "Sort Up by Start Time");
        LOGGER.info("Sort up by Start Time");
        applicationsPageObject.sortByStartTime.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Start Time
        test.log(LogStatus.INFO, "Sort down by Start Time");
        LOGGER.info("Sort down by Start Time");
        applicationsPageObject.sortByStartTime.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Start Time.");
        // Sort Up by Duration
        test.log(LogStatus.INFO, "Sort Up by Duration");
        LOGGER.info("Sort up by Duration");
        applicationsPageObject.sortByDuration.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Duration
        test.log(LogStatus.INFO, "Sort down by Duration");
        LOGGER.info("Sort down by Duration");
        applicationsPageObject.sortByDuration.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Duration.");
        // Sort Up by Read
        test.log(LogStatus.INFO, "Sort Up by Read");
        LOGGER.info("Sort up by Read");
        applicationsPageObject.sortByRead.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Read
        test.log(LogStatus.INFO, "Sort down by Read");
        LOGGER.info("Sort down by Read");
        applicationsPageObject.sortByRead.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Read.");
        // Sort Up by Write
        test.log(LogStatus.INFO, "Sort Up by Write");
        LOGGER.info("Sort up by Write");
        applicationsPageObject.sortByWrite.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Write
        test.log(LogStatus.INFO, "Sort down by Write");
        LOGGER.info("Sort down by Write");
        applicationsPageObject.sortByWrite.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Write.");
        // Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}
