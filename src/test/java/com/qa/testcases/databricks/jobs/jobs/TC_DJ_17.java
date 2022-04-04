package com.qa.testcases.databricks.jobs.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.scripts.databricks.jobs.JobsPage;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.DbxJobs
@Marker.All
public class TC_DJ_17 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_DJ_17.class);

    @Test()
    public void verifyResourcesTab() {
        test = extent.startTest("TC_DJ_17.verifyResourcesTab",
                "Verify that on a Clicking on Resources tab:\n" +
                        "1. Task attempt - a doughnut graph- this should have all the attempts (failed, success)\n");
        test.assignCategory("Jobs");
        Log.startTestCase("TC_DJ_17.verifyResourcesTab");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        JobsPage jobsPage = new JobsPage(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab("Jobs");
        waitExecuter.waitUntilElementPresent(dbpageObject.jobsTabs);
        try {
            jobsPage.verifySummaryTabValidation(test, "Resources", logger);
            test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name", test);
            logger.info("Error- " + ex, test);
        }
    }
}
