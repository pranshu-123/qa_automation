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
public class TC_JIA10 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA10.class);

    /**
     * Verify Application Events filter
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppEventFilter(String clusterId) {
        test = extent.startTest("TC_JIA10.verifyAppEventFilter" + clusterId, "Verify Application Events filter");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
        test.log(LogStatus.INFO, "ClusterId parameter passed is : " + clusterId);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);

        //Click on Application Type to collapse the default check-box displayed from it.
        inefficientApps.clickOnApplicationType();
        test.log(LogStatus.INFO, "Clicked on Application Type link");

        //Click on Application Event to expand the check-box displayed from it.
        inefficientApps.clickOnApplicationEvent();
        test.log(LogStatus.INFO, "Clicked on Application Event link");

        //Click on every check-box under Application Event to un-check the checkboxes
        inefficientApps.clickAllApplicationEventChkBox();
        test.log(LogStatus.INFO, "Deselected one application each from Application Event");

        inefficientApps.clickShowAll();
        test.log(LogStatus.INFO, "Clicked on Show All");
        Assert.assertTrue(inefficientApps.verifyAllAppEventChkBoxIsChecked(), "Application Event " +
                "checkbox is not checked after click on Show All");
        test.log(LogStatus.PASS, "Verified application event filter and clicked on Show All");
    }
}
