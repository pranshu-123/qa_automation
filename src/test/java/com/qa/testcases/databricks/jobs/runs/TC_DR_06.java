package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.DbxJobsRuns
@Marker.All
public class TC_DR_06 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_06.class);

    @Test()
    public void validateFailedStatus() {
        test = extent.startTest("TC_DR_06.validateFailedStatus",
                "Verify All the Failed jobs are listed on the page");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToRunsTab();
        try {
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        dballApps.inJobsSelectClusterAndLast7Days();
        waitExecuter.sleep(2000);


        int appCount = dballApps.clickOnlyLink("Failed");

        if (appCount > 0) {
            String headerAppId = dballApps.verifyStatus(dbpageObject);
            test.log(LogStatus.PASS, "Application Id is displayed in the Header: " + headerAppId);
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, dbpageObject.closeAppsPageTab);
            waitExecuter.sleep(3000);

        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page",test);
        }
        } catch (
                NoSuchElementException ex) {
            loggingUtils.info("No app present by this name", test);
            loggingUtils.info("Error- " + ex, test);
        }
    }
}

