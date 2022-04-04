package com.qa.testcases.databricks.jobs.jobs;

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
            waitExecuter.sleep(2000);

            loggingUtils.info("Navigated to jobs page", test);
            jobsPage.validateTableSorting("Last Run Status");
            loggingUtils.info("Jobs page are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Jobs page are sorted as per Last Run Status");


            jobsPage.validateTableSorting("Job ID");
            loggingUtils.info("Job ID are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Job ID are sorted as per Last Run Status");

            jobsPage.validateTableSorting("Job Name");
            loggingUtils.info("Job Name are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Job Name are sorted as per Last Run Status");


            jobsPage.validateTableSorting("Cluster Name");
            loggingUtils.info("Cluster Name are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Cluster Name are sorted as per Last Run Status");


            jobsPage.validateTableSorting("Workspace");
            loggingUtils.info("Workspace are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Workspace are sorted as per Last Run Status");


            loggingUtils.info("Navigated to jobs page", test);
            jobsPage.validateTableSorting("User");
            loggingUtils.info("User are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "User are sorted as per Last Run Status");

            jobsPage.validateTableSorting("Start Time");
            loggingUtils.info("Start Time are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Start Time are sorted as per Last Run Status");


            jobsPage.validateTableSorting("Duration");
            loggingUtils.info("Duration are sorted as per Last Run Status", test);
            test.log(LogStatus.PASS, "Duration are sorted as per Last Run Status");

        } catch (NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}
