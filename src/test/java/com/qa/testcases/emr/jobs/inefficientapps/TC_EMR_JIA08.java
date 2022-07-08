package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.testcases.jobs.applications.inefficientapps.TC_JIA08;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA08 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA08.class);

    /**
     * Verify application count in result and list
     */
    @Test()
    public void verifyAppCount() {
        test = extent.startTest("TC_EMR_JIA08.verifyAppCount", "Verify application count in " +
                "result and list");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);


        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Add the job count of each application
        test.log(LogStatus.INFO, "Add the job count of each application");
        LOGGER.info("Add the job count of each application",test);
        int sumOfAllAppCounts = inefficientApps.addApplicationTypeCount();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Sum of all job counts = " + sumOfAllAppCounts);
        LOGGER.info("Sum of all job counts = " + sumOfAllAppCounts,test);

        // Get 'Showing total counts' from the left heading
        test.log(LogStatus.INFO, "Get 'Showing total counts' from the left heading ");
        LOGGER.info("Get 'Showing total counts' from the left heading",test);
        int totalShownAppCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(1000);
        test.log(LogStatus.INFO, "Total count displayed on left header = " + totalShownAppCount);
        LOGGER.info("Total count displayed on left header = " + totalShownAppCount,test);

        // Assert if both the count match
        test.log(LogStatus.INFO, "Assert if total count displayed matches the sum of application counts ");
        LOGGER.info("Assert if total count displayed matches the sum of application counts ",test);
        Assert.assertEquals(sumOfAllAppCounts, totalShownAppCount,
                "Total app count displayed does not match the sum of all the job count of each application "
                        + sumOfAllAppCounts);
        test.log(LogStatus.PASS, "Verified application count in result and list ");
    }

}
