package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DbxAppDetails
@Marker.All
public class TC_JAP_02 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_JAP_02.class);

    @Test()
    public void validateJobSummaryRunning() {
        test = extent.startTest("TC_JAP_02_PART1.validateJobSummary",
                "Verify Job summary must be defined Running");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        dballApps.navigateToJobsTab("Runs");

        int appCount = dballApps.clickOnlyLink("Running");
        waitExecuter.sleep(2000);
        int totalCount = Integer
                .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            summaryPage.verifyAllDataTabs(summaryPageObject, "Analysis", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Resources", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Errors", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Logs", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Tags", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Program", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "SQL", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page", test);
        }

    }

    @Test()
    public void validateJobSummarySuccess() {
        test = extent.startTest("TC_JAP_02_PART2.validateJobSummary",
                "Verify All the Success jobs are listed on the Application Summary page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Success");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            summaryPage.verifyAllDataTabs(summaryPageObject, "Analysis", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Resources", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Errors", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Logs", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Tags", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Program", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "SQL", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page", test);
        }

    }

    @Test()
    public void validateJobSummaryFailed() {
        test = extent.startTest("TC_JAP_02_PART3.validateJobSummary",
                "Verify All the Failed jobs are listed on the Application Summary page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Failed");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            summaryPage.verifyAllDataTabs(summaryPageObject, "Analysis", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Resources", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Errors", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Logs", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Tags", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Program", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "SQL", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page", test);
        }

    }

    @Test()
    public void validateJobSummaryKilled() {
        test = extent.startTest("TC_JAP_02_PART4.validateJobSummary",
                "Verify All the Killed jobs are listed on the Application Summary page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Killed");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            summaryPage.verifyAllDataTabs(summaryPageObject, "Analysis", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Resources", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Errors", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Logs", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Tags", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Program", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "SQL", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, summaryPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page", test);
        }
    }
}

