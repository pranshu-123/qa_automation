package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxRuns
@Marker.All
public class TC_DR_61 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_61.class);

    @Test()
    public void validateRunningStatus() {
        test = extent.startTest("TC_DR_61.validateRunningStatus",
                "Verify All the Running jobs are listed on the page");
        test.assignCategory("Jobs-Runs/Running");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Running");
        waitExecuter.waitUntilPageFullyLoaded();
        try {

            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());

            if (totalCount > 0) {
                String appStatus = dballApps.verifyStatusInFinishedTab(dbpageObject);
                test.log(LogStatus.PASS, "Application status is displayed in the Header: " + appStatus);
                waitExecuter.waitUntilPageFullyLoaded();
                //Close apps details page
                MouseActions.clickOnElement(driver, dbpageObject.closeAppsPageTab);
                waitExecuter.sleep(3000);

            } else {
                test.log(LogStatus.SKIP, "No Application present ");
                loggingUtils.error("No Application present in the Runs page", test);
            }
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}