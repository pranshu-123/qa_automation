package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.DbxAppDetails
@Marker.All
public class TC_JAP_10 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_10.class);

    @Test()
    public void TC_JAP_10_verifyResourcesTab() throws InterruptedException {
        test = extent.startTest("TC_JAP_10.validateAppSummaryPage",
                "Verify that on a Clicking on Resources tab:\n" +
                        "1. Should contain all the below graphs\n" +
                        "  a. Task attempt- a doughnut graph- this should have all the attempts (failed, success)\n" +
                        "  b. Containers - bar chart- number of containers used for the app\n" +
                        "  c. Vcores - bar chart - should show the number of vcores used by app\n" +
                        "  d. Memory - Bar chart - should show the memory utilised");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        try {
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Resources", logger, false);
            test.log(LogStatus.PASS, "Verified the Resources tab successfully");
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);

        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}
