package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AllApps
@Marker.GCPAllApps
@Marker.All
public class TC_JAL_09 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_09.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyApplicationCount(String clusterId) {
        test = extent.startTest("TC_JAL_09.verifyApplicationCount",
                "Verify both application count list and the showing result are same");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Add the job count of each application
        test.log(LogStatus.INFO, "Add the job count of each application");
        LOGGER.info("Add the job count of each application");
        int sumOfAllAppCounts = allApps.addApplicationTypeCount();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Sum of all job counts = " + sumOfAllAppCounts);
        LOGGER.info("Sum of all job counts = " + sumOfAllAppCounts);
        // Get 'Showing total counts' from the left heading
        test.log(LogStatus.INFO, "Get 'Showing total counts' from the left heading ");
        LOGGER.info("Get 'Showing total counts' from the left heading");
        int totalShownAppCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(1000);
        test.log(LogStatus.INFO, "Total count displayed on left header = " + totalShownAppCount);
        LOGGER.info("Total count displayed on left header = " + totalShownAppCount);
        // Assert if both the count match
        test.log(LogStatus.INFO, "Assert if total count displayed matches the sum of application counts ");
        LOGGER.info("Assert if total count displayed matches the sum of application counts ");
        Assert.assertEquals(sumOfAllAppCounts, totalShownAppCount,
                "Total app count displayed does not match the sum of all the job count of each application "
                        + sumOfAllAppCounts);
        test.log(LogStatus.PASS, "Total app count displayed match the sum of all the job count of each application ");
    }

}
