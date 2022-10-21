package com.qa.testcases.databricks.jobs.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.scripts.databricks.jobs.JobsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
@Marker.DbxJobs
@Marker.All
public class TC_DJ_14 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DJ_14.class);

    @Test()
    public void validateSortTable() {
        test = extent.startTest("TC_DJ_14.validateSortTable",
                "Validate that the user is able to sort based on Last Run Status,job ID,Job Name,Cluster Name,Workspace");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxJobsPageObject jobsPageObject = new DbxJobsPageObject(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        JobsPage jobsPage = new JobsPage(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab("Jobs");
        waitExecuter.waitUntilElementPresent(dbpageObject.jobsTabs);
        try {
            // Navigate to Jobs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            dballApps.inJobsSelectClusterAndLast7Days();
            waitExecuter.waitForSeconds(5);

            loggingUtils.info("Navigated to jobs page", test);
            jobsPage.validateJobSorting("Last Run Status");
            loggingUtils.info("Jobs are sorted and listed as per sorted criteria",test);
            test.log(LogStatus.PASS, "Jobs are sorted and listed as per sorted criteria");


        } catch (Exception ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}
