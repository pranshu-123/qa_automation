package com.qa.testcases.databricks.jobs.runs;

import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DR_40 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_40.class);

    @Test()
    public void verifyApplicationCountFinishedTab() {
        test = extent.startTest("TC_DR_40.verifyApplicationCountFinishedTab",
                "Verify both application count list and the showing result are same");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to Runs tab from header");
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Finished");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            // Navigate to Runs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 30 days");
            dballApps.inJobsSelectClusterAndLast30Days();
            waitExecuter.sleep(2000);
            // Add the job count of each application
            test.log(LogStatus.INFO, "Add the job count of each application");
            loggingUtils.info("Add the job count of each application", test);
            int sumOfAllAppCounts = dballApps.addApplicationTypeCount();
            waitExecuter.sleep(2000);
            test.log(LogStatus.INFO, "Sum of all job counts = " + sumOfAllAppCounts);
            loggingUtils.info("Sum of all job counts = " + sumOfAllAppCounts, test);
            // Get 'Showing total counts' from the left heading
            test.log(LogStatus.INFO, "Get 'Showing total counts' from the left heading ");
            loggingUtils.info("Get 'Showing total counts' from the left heading", test);
            int totalShownAppCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            test.log(LogStatus.INFO, "Total count displayed on left header = " + totalShownAppCount);
            loggingUtils.info("Total count displayed on left header = " + totalShownAppCount, test);
            // Assert if both the count match
            test.log(LogStatus.INFO, "Assert if total count displayed matches the sum of application counts ");
            loggingUtils.info("Assert if total count displayed matches the sum of application counts", test);
            Assert.assertEquals(sumOfAllAppCounts, totalShownAppCount,
                    "Total app count displayed does not match the sum of all the job count of each application "
                            + sumOfAllAppCounts);
            test.log(LogStatus.PASS, "Total app count displayed match the sum of all the job count of each application ");
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No Data picker present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}

