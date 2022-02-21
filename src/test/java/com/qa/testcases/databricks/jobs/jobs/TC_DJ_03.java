package com.qa.testcases.databricks.jobs.jobs;

import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.testcases.databricks.jobs.runs.TC_DR_01;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TC_DJ_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_DR_01.class.getName());

    @Test()
    public void validateRunStatus() {
        test = extent.startTest("TC_DR_05.validateKilledStatus",
                "Verify All the Killed jobs are listed on the page");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab();

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        dballApps.inJobsSelectClusterAndLast7Days();
        waitExecuter.sleep(2000);


        int appCount = dballApps.clickOnlyLink("Killed");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = dballApps.verifyStatus(dbpageObject);
            test.log(LogStatus.PASS, "Application Id is displayed in the Header: " + headerAppId);
            waitExecuter.waitUntilPageFullyLoaded();
            //Close apps details page
            MouseActions.clickOnElement(driver, dbpageObject.closeAppsPageTab);
            waitExecuter.sleep(3000);

        }
    }
}

