package com.qa.testcases.databricks.jobs.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.scripts.databricks.jobs.JobsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
@Marker.DbxJobs
@Marker.All
public class TC_DJ_15 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DJ_15.class);

    @Test()
    public void validateJobsKPI() {
        test = extent.startTest("TC_DJ_15.validateJobsKPI",
                "Verify App should have KPI defined");
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


            String headerAppId = jobsPage.verifyRunCount(jobsPageObject);
            test.log(LogStatus.PASS, "Application RunCount is displayed: " + headerAppId);
            waitExecuter.waitUntilPageFullyLoaded();
            jobsPage.verifyAllDataTabs(jobsPageObject, "Analysis", test);
            jobsPage.verifyAllDataTabs(jobsPageObject, "Resources", test);
            jobsPage.verifyAllDataTabs(jobsPageObject, "Daggraph", test);
            jobsPage.verifyAllDataTabs(jobsPageObject, "Errors", test);
            jobsPage.verifyAllDataTabs(jobsPageObject, "Tags", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            waitExecuter.sleep(3000);
            //Close apps details page
            MouseActions.clickOnElement(driver, jobsPageObject.closeIcon);
            waitExecuter.sleep(3000);

        } catch (NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}


