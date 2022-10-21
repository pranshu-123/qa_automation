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
public class TC_JAP_15 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_15.class);

    @Test()
    public void TC_JAP_15_verifyProgramsTab() throws InterruptedException {
        test = extent.startTest("TC_JAP_15.verifyProgramsTab",
                "Verify Program tab must be loaded on the UI and data should be available");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        summaryPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilPageFullyLoaded();
        summaryPageObject.sortUp.click();
        try {
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Program", logger, false);
            test.log(LogStatus.PASS, "Verified the Program tab successfully");
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}