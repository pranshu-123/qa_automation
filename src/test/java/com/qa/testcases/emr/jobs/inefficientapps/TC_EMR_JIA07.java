package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.testcases.jobs.applications.inefficientapps.TC_JIA07;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA07 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA07.class);

    /**
     * Verify RESET functionality
     */
    @Test()
    public void verifyRESETfunctionality() {
        test = extent.startTest("TC_EMR_JIA07.verifyRESETfunctionality" , "Verify RESET functionality");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Select cluster
        inefficientApps.clickOnApplicationEvent();
        test.log(LogStatus.INFO, "Clicked on Application Event link");

        inefficientApps.clickFirstChkBoxOfAppTypeAndAppEventEach();
        test.log(LogStatus.INFO, "Deselected one application each from Application Type and Application Event");

        // Click on reset button
        test.log(LogStatus.INFO, "Click on reset button");
        LOGGER.info("Click on reset button",test);
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Clicked on Reset button");

        inefficientApps.clickOnApplicationEvent();
        test.log(LogStatus.INFO, "Clicked on Application Event link");

        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppTypeIsChecked(), "ApplicationType " +
                "checkbox is not checked after Reset functionality fails");
        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppEventIsChecked(), "ApplicationEvent " +
                "checkbox is not checked after Reset functionality fails");
        test.log(LogStatus.PASS, "Verified Reset functionality.");

    }
}
