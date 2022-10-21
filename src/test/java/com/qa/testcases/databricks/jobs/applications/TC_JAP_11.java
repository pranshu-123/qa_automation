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
public class TC_JAP_11 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_11.class);

    @Test()
    public void verifyErrorsTab() throws InterruptedException {
        test = extent.startTest("TC_JAP_11.verifyErrorsTab",
                "Verify Spark Properties and values must be listed in the UI, " +
                        "1. Spark Properties and values must be listed here\n" +
                        "2. Search page must be present here and user should be able to search any property and values\n" +
                        "3. These key words must be present as templates and users should be able to select them and list related\n" +
                        "    properties (metadata, memory, limit, resources, cpu, net, yarn, deploy)\n" +
                        "4. RESET should reset all the searches to default");
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
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Errors", logger, true);
            test.log(LogStatus.PASS, "Verified the Errors tab successfully");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}

