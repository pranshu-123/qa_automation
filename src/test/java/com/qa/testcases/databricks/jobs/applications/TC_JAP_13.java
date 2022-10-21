package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.DbxAppDetails
@Marker.All
public class TC_JAP_13 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_13.class);

    @Test()
    public void TC_JAP_13_verifyLogsTab() throws InterruptedException {
        test = extent.startTest("TC_JAP_13.verifyLogsTab",
                "Verify 1. Logs tab must contain One Driver and executor logs listed\n" +
                        " 2. They must be collapsible tabs\n" +
                        " 3. clicking on each must open logs window and should be able to scroll the logs");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        summaryPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilPageFullyLoaded();
        summaryPageObject.sortUp.click();
        try {
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Logs", logger, false);
            test.log(LogStatus.PASS, "Verified the Logs tab successfully");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}

