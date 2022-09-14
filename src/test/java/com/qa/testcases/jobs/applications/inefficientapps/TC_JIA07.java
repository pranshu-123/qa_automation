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
public class TC_JIA07 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA07.class);

    /**
     * Verify RESET functionality
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyRESETfunctionality(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_JIA07.verifyRESETfunctionality" + clusterId, "Verify RESET functionality");
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

        inefficientApps.clickFirstChkBoxOfAppTypeAndAppEventEach();
        test.log(LogStatus.INFO, "Deselected one application each from Application Type and Application Event");

        // Click on reset button
        test.log(LogStatus.INFO, "Click on reset button");
        LOGGER.info("Click on reset button");
        applicationsPageObject.resetButton.click();
        waitExecuter.waitForSeconds(2);
        test.log(LogStatus.INFO, "Clicked on Reset button");


        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppTypeIsChecked(), "ApplicationType " +
                "checkbox is not checked after Reset functionality fails");
        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppEventIsChecked(), "ApplicationEvent " +
                "checkbox is not checked after Reset functionality fails");
        test.log(LogStatus.PASS, "Verified Reset functionality.");

    }
}
