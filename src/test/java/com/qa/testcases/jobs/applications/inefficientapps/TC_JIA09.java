package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class TC_JIA09 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA09.class);

    /**
     * Verify application type filter
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppTypeFilter(String clusterId) {
        test = extent.startTest("TC_JIA09.verifyAppTypeFilter" + clusterId, "Verify application type filter");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
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

        inefficientApps.clickAllApplicationTypeChkBox();
        test.log(LogStatus.INFO, "Deselected one application each from Application Type and Application Event");

        inefficientApps.clickShowAll();
        test.log(LogStatus.INFO, "Clicked on Show All");
        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppTypeIsChecked(), "ApplicationType " +
                "checkbox is not checked after click on Show All");
        test.log(LogStatus.PASS, "Verified application type filter and clicked on Show All");
    }
}
