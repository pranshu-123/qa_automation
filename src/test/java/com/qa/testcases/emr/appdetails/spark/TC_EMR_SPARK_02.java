package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.appDetailsPage.EmrSparkAppsDetailsPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EMRSpark
public class TC_EMR_SPARK_02 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_EMR_SPARK_02.class);

    @Test()
    public void validateJobSummaryRunning() {
        test = extent.startTest("TC_EMR_SPARK_PART1.validateJobSummary",
                "Verify All the Running jobs are listed on the Application Summary page");
        test.assignCategory("Apps Details-Spark");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Success");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.WARNING, "Running jobs displays 'No Application Present' msg. " +
                    ".Check manually if data was expected.");
        }

    }

    @Test()
    public void validateJobSummarySuccess() {
        test = extent.startTest("TC_EMR_SPARK_PART2.validateJobSummary",
                "Verify All the Success jobs are listed on the Application Summary page");
        test.assignCategory("Apps Details-Spark");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Success");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "SQL", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Application present ");
            loggingUtils.error("No Application present in the Runs page", test);
        }

    }

    @Test()
    public void validateJobSummaryFailed() {
        test = extent.startTest("TC_EMR_SPARK_PART3.validateJobSummary",
                "Verify All the Failed jobs are listed on the Application Summary page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Failed");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            waitExecuter.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.WARNING, "Failed jobs displays 'No Application Present' msg. " +
                    ".Check manually if data was expected.");
        }

    }

    @Test()
    public void validateJobSummaryKilled() {
        test = extent.startTest("TC_EMR_SPARK_PART4.validateJobSummary",
                "Verify All the Killed jobs are listed on the Application Summary page");
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Killed");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        wait.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            wait.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            wait.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            wait.sleep(3000);
        } else {
            test.log(LogStatus.WARNING, "Killed jobs displays 'No Application Present' msg. " +
                    ".Check manually if data was expected.");
        }
}

        @Test
        public void validateJobSummaryUnknown(){
            test = extent.startTest("TC_EMR_SPARK_PART5.validateJobSummaryUnknown",
                    "Verify All the Unknown jobs are listed on the Application Summary page");
            EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
            EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
            EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
            EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
            DatePicker datePicker = new DatePicker(driver);
            EMRAllApps allApps = new EMRAllApps(driver);
            WaitExecuter wait = new WaitExecuter(driver);

            // Navigate to Jobs tab from header
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                    applicationsPageObject);
            wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
            sparkAppPageObj.statusHeader.click();

            wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
            appsDetailsPage.clickOnlyLink("Unknown");
            int appCount = appsDetailsPage.clickOnlyLink("Spark");
            wait.sleep(2000);
            int totalCount = Integer
                    .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
            wait.sleep(2000);

            if (appCount > 0) {
                String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
                test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
                //Close apps details page
                wait.waitUntilPageFullyLoaded();
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
                appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
                wait.sleep(2000);
                test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
                //Close apps details page
                MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
                wait.sleep(3000);
            } else {
                test.log(LogStatus.WARNING, "Unknown jobs displays 'No Application Present' msg. " +
                        ".Check manually if data was expected.");
            }

    }

    @Test
    public void validateJobSummaryPending(){
        test = extent.startTest("TC_EMR_SPARK_PART6.validateJobSummaryPending",
                "Verify All the Pending jobs are listed on the Application Summary page");
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Pending");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        wait.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            wait.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            wait.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            wait.sleep(3000);
        } else {
            test.log(LogStatus.WARNING, "Pending jobs displays 'No Application Present' msg. " +
                    ".Check manually if data was expected.");
        }

    }

    @Test
    public void validateJobSummaryWaiting(){
        test = extent.startTest("TC_EMR_SPARK_PART7.validateJobSummaryWaiting",
                "Verify All the Waiting jobs are listed on the Application Summary page");
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        sparkAppPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Waiting");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);
        int totalCount = Integer
                .parseInt(sparkAppPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        wait.sleep(2000);

        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj);
            test.log(LogStatus.PASS, "Application Spark name is displayed in the Header: " + headerAppId);
            //Close apps details page
            wait.waitUntilPageFullyLoaded();
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Analysis", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Resources", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Errors", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Logs", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Tags", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Configuration", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Program", test);
            appsDetailsPage.verifyAllDataTabs(sparkAppPageObj, "Timings", test);
            wait.sleep(2000);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            wait.sleep(3000);
        } else {
            test.log(LogStatus.WARNING, "Waiting jobs displays 'No Application Present' msg. " +
                    ".Check manually if data was expected.");
        }

    }
}
