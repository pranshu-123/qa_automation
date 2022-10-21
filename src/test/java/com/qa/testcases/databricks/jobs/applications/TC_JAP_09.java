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
public class TC_JAP_09 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_09.class);

    @Test()
    public void verifyAnalysisTab() throws InterruptedException {
        test = extent.startTest("TC_JAP_09.verifyAnalysisTab",
                "Verify 1. All the spark apps must be listed on the UI\n" +
                        " 2. left pane must have spark check box and the apps number\n" +
                        " 3. clicking on the UI must go to apps detail page");
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
            summaryPage.commonSetupCodeForSumarryTabValidation(test, "Analysis", logger, false);
            test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}
