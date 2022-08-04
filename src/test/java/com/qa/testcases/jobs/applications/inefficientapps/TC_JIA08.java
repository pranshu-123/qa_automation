package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.InefficientApps
@Marker.GCPInefficientApps
@Marker.All
public class TC_JIA08 extends BaseClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(TC_JIA08.class);

    /**
     * Verify application count in result and list
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppCount(String clusterId) {
        test = extent.startTest("TC_JIA08.verifyAppCount" + clusterId, "Verify application count in " +
                "result and list");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);

        // Add the job count of each application
        test.log(LogStatus.INFO, "Add the job count of each application");
        LOGGER.info("Add the job count of each application");
        int sumOfAllAppCounts = inefficientApps.addApplicationTypeCount();
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
        test.log(LogStatus.PASS, "Verified application count in result and list ");
    }

}
