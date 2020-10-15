package com.qa.testcases.jobs.applications.all;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * To Test-
 * Applications that are executed between select date range should be listed
 * @Author - Ojasvi Pandey
 */

public class TC_JAL_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateApplicationListingForDateRanges(String clusterId) {
        test = extent.startTest("TC_JAL_03.validateApplicationListingForDateRanges",
                "Verify the apps listed in page for selected date picker filter");
        test.assignCategory("4620 Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        String subStringOfSelectedClusterId = clusterId.substring(0, 19);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(1000);
    }
}
