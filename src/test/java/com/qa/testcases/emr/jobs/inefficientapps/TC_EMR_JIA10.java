package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.testcases.jobs.applications.inefficientapps.TC_JIA10;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA10 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA10.class);

    /**
     * Verify Application Events filter
     */
    @Test()
    public void verifyAppEventFilter() {
        test = extent.startTest("TC_EMR_JIA10.verifyAppEventFilter" , "Verify Application Events filter");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");


        //Click on Application Type to collapse the default check-box displayed from it.
        inefficientApps.clickOnApplicationType();
        test.log(LogStatus.INFO, "Clicked on Application Type link");

        inefficientApps.clickShowAll();
        test.log(LogStatus.INFO, "Clicked on Show All");
        Assert.assertTrue(inefficientApps.verifyAllAppEventChkBoxIsChecked(), "Application Event " +
                "checkbox is not checked after click on Show All");
        test.log(LogStatus.PASS, "Verified application event filter and clicked on Show All");
    }
}
