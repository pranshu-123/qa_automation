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
public class TC_DJ_18 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_DJ_18.class);

    @Test()
    public void verifyErrorsTab() {
        test = extent.startTest("TC_DJ_18.verifyErrorsTab",
                "Verify  1. Errors must be listed under this tab\n" +
                        " 2. Tabs must be collapsable\n" +
                        " 3. error related to drivers, executors and rm diagnostics are some kind of error categories");;
        test.assignCategory("Jobs");
        Log.startTestCase("TC_DJ_18.verifyErrorsTab");
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
            jobsPage.verifySummaryTabValidation(test, "Errors", logger);
            test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
        } catch (Exception ex) {
            logger.info("No app present by this name", test);
            logger.error("Error- " + ex, test);
        }
    }
}
