package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.testcases.appdetails.spark.TC_spark_165;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DbxAppDetails
@Marker.All
public class TC_JAP_04 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_04.class);

    @Test()
    public void validateLoadDiagnosticAction() {
        test = extent.startTest("TC_JAP_04.validateLoadDiagnosticAction",
                "Verify 1. Application details page should be opened\n" +
                        "  2. Left pane must be opened and should have KPIs listed (start, end and duration listed and should not be empty)\n" +
                        "  3. If there are failed attempts then, there should be attempts tab under which attempts attempts for \"failed\"\n" +
                        "    and \"success\" must be displayed in the form of bar graph");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        try {
            summaryPage.navigateToJobsTabFromHeader(dballApps, test);

            int appCount = dballApps.clickOnlyLink("Success");
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
            waitExecuter.sleep(2000);


            if (appCount > 0) {
                String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
                test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
                test.log(LogStatus.INFO, "Verify if the user can execute the Load Diagnostics action");
                summaryPage.verifyLoadDiagnosticAction(summaryPageObject);
                test.log(LogStatus.PASS, "Verified that the user can execute the Load Diagnostics action");
                // Close apps details page
                MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
                waitExecuter.waitUntilElementClickable(summaryPageObject.resetButton);
                summaryPageObject.resetButton.click();
                waitExecuter.waitUntilElementClickable(summaryPageObject.resetButton);
            } else {
                test.log(LogStatus.SKIP, "No Spark Application present");
                logger.error("No Job Application present in the last 30 days");
            }
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}
