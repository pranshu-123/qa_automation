package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
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
public class TC_JAP_05 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_05.class);

    @Test()
    public void validateAppSummaryPage() {
        test = extent.startTest("TC_JAP_05.validateAppSummaryPage",
                "Verify 1. All the spark apps must be listed on the UI\n" +
                        " 2. left pane must have spark check box and the apps number\n" +
                        " 3. clicking on the UI must go to apps detail page");
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
                summaryPage.verifyAppsComponent(summaryPageObject, false, false,
                        false);
                test.log(LogStatus.PASS, "Verified that the user can execute the Load Diagnostics action");
                // Close apps details page
                test.log(LogStatus.PASS, "The basic components for an application is present");
                //Close apps details page
                MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
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