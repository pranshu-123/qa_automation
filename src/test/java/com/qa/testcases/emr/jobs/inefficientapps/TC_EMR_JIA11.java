package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.testcases.jobs.applications.inefficientapps.TC_JIA11;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA11 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA11.class);

    /**
     * Verify sorting option
     */
    @Test()
    public void verifyAppEventSortOption() {
        test = extent.startTest("TC_EMR_JIA11.verifyAppEventSortOption" , "Verify sorting option");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");


        //Validate InefficientApps table is populated
        waitExecuter.sleep(2000);
        Assert.assertTrue(inefficientApps.verifyInefficientAppsTblPresent(), "InefficientApps table not found");
        test.log(LogStatus.INFO, "Verified InefficientApps table populated.");

        //Click on InefficientApps table headers to sort it
        waitExecuter.sleep(2000);
        inefficientApps.clickOnInefficientAppsTblColSort();
        test.log(LogStatus.PASS, "Verified InefficientApps table headers sorting option on all applicable tab.");

    }

}
