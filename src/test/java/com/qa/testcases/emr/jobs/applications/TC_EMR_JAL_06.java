package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


@Marker.EMRAllApps
public class TC_EMR_JAL_06 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_06.class);

    @Test()
    public void verifyApplicationCount() {
        test = extent.startTest("TC_EMR_JAL_06.verifyApplicationCount",
                "Verify both application count list and the showing result are same");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Add the job count of each application
        test.log(LogStatus.INFO, "Add the job count of each application");
        LOGGER.info("Add the job count of each application",test);
        int sumOfAllAppCounts = allApps.addApplicationTypeCount();
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
        LOGGER.info("Assert if total count displayed matches the sum of application counts",test);
        Assert.assertEquals(sumOfAllAppCounts, totalShownAppCount,
                "Total app count displayed does not match the sum of all the job count of each application "
                        + sumOfAllAppCounts);
        test.log(LogStatus.PASS, "Total app count displayed match the sum of all the job count of each application ");
    }

}
