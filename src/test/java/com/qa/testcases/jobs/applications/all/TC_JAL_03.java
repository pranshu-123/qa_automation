package com.qa.testcases.jobs.applications.all;

import com.qa.base.BaseClass;
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
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();
        String subStringOfSelectedClusterId = clusterId.substring(0, 19);
        LOGGER.info("clusterId subString: " + subStringOfSelectedClusterId);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(1000);
    }
}
