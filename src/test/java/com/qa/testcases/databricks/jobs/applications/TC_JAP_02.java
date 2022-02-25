package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.DbxAppDetails
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
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Running");

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
            summaryPage.verifyAllDataTabs(summaryPageObject, "Metrics", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
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
                "Verify Job summary must be defined Running");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Success");

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
            summaryPage.verifyAllDataTabs(summaryPageObject, "Metrics", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
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
                "Verify Job summary must be defined Running");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Failed");

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
            summaryPage.verifyAllDataTabs(summaryPageObject, "Metrics", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
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
                "Verify Job summary must be defined Running");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = dballApps.clickOnlyLink("Killed");

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
            summaryPage.verifyAllDataTabs(summaryPageObject, "Metrics", test);
            summaryPage.verifyAllDataTabs(summaryPageObject, "Configuration", test);
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

