package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class TC_JAP_16 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_16.class);

    @Test()
    public void TC_JAP_16_verifySQLTab() {
        test = extent.startTest("TC_JAP_15.verifySQLTab",
                "Verify Program tab must be loaded on the UI and data should be available");
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
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "SQL", logger, false);
            test.log(LogStatus.PASS, "Verified the Program tab successfully");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}