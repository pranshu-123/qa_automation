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
            dballApps.inJobsSelectClusterAndLast7Days();
            String headerAppId = jobsPage.verifyJobName(jobsPageObject);
            test.log(LogStatus.PASS, "Application Job Name is displayed: " + headerAppId);
            waitExecuter.waitUntilPageFullyLoaded();
            jobsPage.verifyRightPaneKpis();
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            waitExecuter.waitUntilElementClickable(jobsPageObject.closeAppsPageTab);
            MouseActions.clickOnElement(driver, jobsPageObject.closeAppsPageTab);
        } catch (Exception ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}