package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_47 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_47.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByParentApp(String clusterId) {
        test = extent.startTest("TC_HIVE_47.VerifySortByParentApp", "Verify that on sorting works for Parent App");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
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
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        int appCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());

        if (appCount > 0) {
            // Sort down by Parent App
            test.log(LogStatus.INFO, "Sort up by Parent App");
            LOGGER.info("Sort up by Parent App");
            applicationsPageObject.sortByParentApp.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(applicationsPageObject.sortUp.isDisplayed(), "Sort up is not working");
            // Sort up by Memory Allocated
            test.log(LogStatus.INFO, "Sort down by Parent App");
            LOGGER.info("Sort down by Parent App");
            applicationsPageObject.sortByParentApp.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(applicationsPageObject.sortDown.isDisplayed(), "Sort down is not working");
            test.log(LogStatus.PASS, "Verified sorting on Parent App.");
            // Refresh the page and reload to original state
            test.log(LogStatus.INFO, "Refresh the page and reload to original state");
            LOGGER.info("Refresh the page and reload to original state");
            waitExecuter.sleep(1000);
            driver.navigate().refresh();
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "There are no apps for selected duration and clusterId.");
        }
    }
}