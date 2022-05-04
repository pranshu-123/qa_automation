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
public class TC_JAP_14 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_14.class);

    @Test()
    public void TC_JAP_14_verifyTagsTab() {
        test = extent.startTest("TC_JAP_14.verifyTagsTab",
                "Verify that the Tags tab contains tags in form of key value pair");
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
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Tags", logger, false);
            test.log(LogStatus.PASS, "Verified the Tags tab successfully");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}

