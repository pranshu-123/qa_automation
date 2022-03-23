package com.qa.scripts.databricks.jobs;

import com.qa.enums.UserAction;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JobsPage {

    private static final Boolean isDignosticWin = false;
    private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private final LoggingUtils loggingUtils = new LoggingUtils(com.qa.scripts.databricks.jobs.DbAllApps.class);
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final DbxApplicationsPageObject applicationsPageObject;
    private final DbxJobsPageObject jobsPageObject;
    private final Actions action;
    private final UserActions userActions;
    private final DbxSubTopPanelModulePageObject dbSubTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    private final List<String> durationTooltipValues;


    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public JobsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new DbxApplicationsPageObject(driver);
        action = new Actions(driver);
        userActions = new UserActions(driver);
        dbSubTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        jobsPageObject = new DbxJobsPageObject(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
        durationTooltipValues = new ArrayList<String>();
    }

    public List<String> getDurationTooltipValues() {
        return durationTooltipValues;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify Status App details Page .
     */
    public String verifyStatus(DbxJobsPageObject jobsPage) {
        String statusTable = jobsPage.status.getText();
        logger.info("Application Id is " + statusTable);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnStatus);
        jobsPage.clickOnStatus.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = jobsPage.appStatus.getText().trim();
        Assert.assertEquals(statusTable, status, "Runs Status is not displayed in the Header");
        return status;
    }


    /**
     * Method to click on jobs Id , navigate to the details page.
     * and verify jobs Id details Page .
     */
    public String verifyJobId(DbxJobsPageObject jobsPage) {
        String jobsIdTable = jobsPage.jobId.getText();
        logger.info("Application Id is " + jobsIdTable);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnJobId);
        jobsPage.clickOnJobId.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsIdAppPage = jobsPage.appJobId.getText().trim();
        Assert.assertNotSame(jobsIdTable, jobsIdAppPage, "Runs Id is not displayed in the Header");
        return jobsIdAppPage;
    }


    /**
     * Method to verify the following:
     * 1.All the KPIs should be listed and the data must be populated.
     * (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis() {
        List<WebElement> kpiList = jobsPageObject.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = jobsPageObject.rightPaneAppKpis;
        List<WebElement> appKpiVal = jobsPageObject.rightPaneAppKpiVal;
        Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
        Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
        String appDuration = "0";
        for (int i = 0; i < appKpis.size(); i++) {
            Assert.assertNotNull(appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotNull(appKpiVal.get(i).getText(), "Kpi Value is empty");
            appDuration = (appKpiVal.get(0).getText().trim());
            logger.info("Kpi Name = " + appKpis.get(i).getText() + " Value = " + appKpiVal.get(i).getText());
        }
        logger.info("The application duration is " + appDuration);
        return appDuration;
    }

    /**
     * Verify that Left pane must be opened and should have KPIs listed (start, end
     * and duration are listed and should not be empty)
     */
    public void validateLeftPaneKpis(List<WebElement> kpiList) {
        Assert.assertFalse(kpiList.isEmpty(), "The kpi list is empty");
        for (WebElement webElement : kpiList) {
            logger.info("The leftPane kpi is " + webElement.getText());
            String kpis = webElement.getText();
            Assert.assertNotSame("", kpis, "The kpis is empty");
            String[] kpisOut = kpis.split(":");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            logger.info("Kpi name = " + kpisOut[1] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }

    /**
     * Common steps to navigate to the Jobs page from header. Clicks on jobs tab
     * Selects a specific cluster Selects 30 days time interval
     */
    public void navigateToJobsTabFromHeader(DbAllApps dballApps, DatePicker datePicker) {
        dballApps.navigateToJobsTab("Jobs");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(3000);
    }

    public void verifySummaryTabValidation(ExtentTest test, String tabName, Logger logger) {
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        logger.info("Initialize all class objects");
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DbxJobsPageObject jobsPageObject = new DbxJobsPageObject(driver);
        JobsPage jobsPage = new JobsPage(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DatePicker datePicker = new DatePicker(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        jobsPage.navigateToJobsTabFromHeader(dballApps, datePicker);

        waitExecuter.sleep(2000);
        String headerAppId = jobsPage.verifyJobId(jobsPageObject);
        test.log(LogStatus.PASS, "Jobs Application Id is displayed in the Header: " + headerAppId);
        jobsPage.verifyAppSummaryTabs(jobsPageObject, tabName, test);
        // Close apps details page

        waitExecuter.waitUntilElementClickable(jobsPageObject.closeAppsPageTab);
        MouseActions.clickOnElement(driver, jobsPageObject.closeAppsPageTab);

    }

    /**
     * Method to verify the Spark summary tabs in the right pane of the App Details page
     */
    public String verifyAppSummaryTabs(DbxJobsPageObject jobsPage, String verifyTabName,
                                       ExtentTest test) {
        List<WebElement> appsTabList = jobsPage.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), jobsPage, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));
        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            logger.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        validateAnalysisTab(jobsPage);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to validate Jobs Application Summary Analysis tab.
     */
    public void validateAnalysisTab(DbxJobsPageObject jobsPage) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = jobsPage.insightsType;
        verifyAssertFalse(insightType.isEmpty(), jobsPage, "No Insights generated");
        for (int j = 0; j < insightType.size(); j++) {
            String insights = insightType.get(j).getText();
            logger.info("Insight generated are " + insights);
            if (insights.equals("EFFICIENCY")) {
                // Store it in efficiency array
                efficiency.add(insights);
            } else {
                // Store it in recommendation array
                recommendation.add(insights);
            }
        }
        verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), jobsPage, "No insights generated");
        List<WebElement> collapsableList = jobsPage.analysisCollapse;
        try {
            for (int c = 0; c < collapsableList.size(); c++) {
                collapsableList.get(c).click();
            }
        } catch (Exception ex) {
            throw new AssertionError(
                    "Caught exception while clicking the collapsable" + " icon for insights.\n" + ex.getMessage());
        }
    }


    /**
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAllDataTabs(DbxJobsPageObject jobsPage, String verifyTabName, ExtentTest test) {
        List<WebElement> appsTabList = jobsPage.appJobsTabs;
        verifyAssertFalse(appsTabList.isEmpty(), jobsPage, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));

        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            logger.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Resources tab is populated");
                        break;
                    case "Daggraph":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Logs tab is populated");
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        break;
                }
                break;
            }
        }
        return "";
    }

    public void verifyAssertFalse(Boolean condition, DbxJobsPageObject jobsPageObject, String msg) {
        String appDuration = "0";
        try {
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, jobsPageObject.loadWinClose);
            else
                MouseActions.clickOnElement(driver, jobsPageObject.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }


    /**
     * Method to click on jobs Name , navigate to the details page.
     * and verify jobs Id details Page .
     */
    public String verifyJobName(DbxJobsPageObject jobsPage) {
        String jobsName = jobsPage.jobName.getText();
        logger.info("Application Id is " + jobsName);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsNameAppPage = jobsPage.appJobName.getText().trim();
        Assert.assertNotSame(jobsName, jobsNameAppPage, "Runs Id is not displayed in the Header");
        return jobsNameAppPage;
    }

    /**
     * Method to click on Cluster Name , navigate to the details page.
     * and verify Cluster Name details Page .
     */
    public String verifyClusterName(DbxJobsPageObject jobsPage) {
        String clusterName = jobsPage.clusterName.getText();
        logger.info("Application Cluster Name is " + clusterName);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String clusterNameAppPage = jobsPage.appclusterName.getText().trim();
        Assert.assertNotSame(clusterName, clusterNameAppPage, "Cluster Name is not displayed in the Header");
        return clusterNameAppPage;
    }


    /**
     * Method to click on Work Space , navigate to the details page.
     * and verify Work Space details Page .
     */
    public String verifyWorkSpace(DbxJobsPageObject jobsPage) {
        String workSpace = jobsPage.workSpace.getText();
        logger.info("Application WorkSpace is " + workSpace);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String workSpaceAppPage = jobsPage.appWorkSpace.getText().trim();
        Assert.assertNotSame(workSpace, workSpaceAppPage, "WorkSpace is not displayed in the Header");
        return workSpaceAppPage;
    }

    /**
     * Method to click on Work Space , navigate to the details page.
     * and verify Work Space details Page .
     */
    public String verifyUser(DbxJobsPageObject jobsPage) {
        String user = jobsPage.userName.getText();
        logger.info("Application User Name is " + user);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String userNameAppPage = jobsPage.appUserName.getText().trim();
        Assert.assertNotSame(user, userNameAppPage, "User Name is not displayed in the Header");
        return userNameAppPage;
    }

    /**
     * Method to click on Duration , navigate to the details page.
     * and verify Duration details Page .
     */
    public String verifyDuration(DbxJobsPageObject jobsPage) {
        String duration = jobsPage.duration.getText();
        logger.info("Application Duration Name is " + duration);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String durationAppPage = jobsPage.appDuration.getText().trim();
        Assert.assertNotSame(duration, durationAppPage, "duration is not displayed in the Header");
        return durationAppPage;
    }

    /**
     * Get total cost value
     */
    public Double getTotalCostValue() {
        return Double.parseDouble(jobsPageObject.totalCostValue.getText().replace("$", ""));
    }

    /**
     * Get total storage cost value
     */
    public Double getTotalDBUValue() {
        return Double.parseDouble(jobsPageObject.totalCostDBUValue.getText().replace("$", ""));
    }


    public void verifyTotalCost() {
        String totalCost = jobsPageObject.totalCost.getText();
        logger.info("Total cost is " + totalCost);
        boolean onlySpecialChars = totalCost.matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(totalCost.isEmpty() || onlySpecialChars, "No values for total cost displayed " +
                "\n Expected: AlphaNumeric value but Actual: [" + totalCost + "]");
    }

    public void VerifyJobsName(DbxJobsPageObject jobsPage, String tableValue, ExtentTest test) {
        try {
            if (tableValue.equalsIgnoreCase("JobsName")) {
                String jobsName = jobsPage.jobName.getText();
                logger.info("Application Id is " + jobsName);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                waitExecuter.waitUntilPageFullyLoaded();
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String jobsNameAppPage = jobsPage.appJobName.getText().trim();
                Assert.assertNotSame(jobsName, jobsNameAppPage, "RunsId is not displayed in the Header");
                test.log(LogStatus.PASS, "JobsName is displayed in the Header: " + jobsNameAppPage);
            } else if (tableValue.equalsIgnoreCase("ClusterName")) {
                String clusterName = jobsPage.clusterName.getText();
                logger.info("Application Cluster Name is " + clusterName);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilPageFullyLoaded();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String clusterNameAppPage = jobsPage.appclusterName.getText().trim();
                Assert.assertNotSame(clusterName, clusterNameAppPage, "Cluster Name is not displayed in the Header");
                test.log(LogStatus.PASS, "JobName is displayed in the Header: " + clusterNameAppPage);
            } else if (tableValue.equalsIgnoreCase("WorkSpaceName")) {
                String workSpace = jobsPage.workSpace.getText();
                logger.info("Application WorkSpace is " + workSpace);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String workSpaceAppPage = jobsPage.appWorkSpace.getText().trim();
                Assert.assertNotSame(workSpace, workSpaceAppPage, "WorkSpace is not displayed in the Header");
                test.log(LogStatus.PASS, "JobName is displayed in the Header: " + workSpaceAppPage);
            }
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and check if the table data like start time, duration, read and write is displayed or not.
     */
    public void checkAppsJobTableData(String data, String colType) {
        boolean onlySpecialChars = data.matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(data.isEmpty() || onlySpecialChars, colType + " data is not displayed in the table");
        logger.info("Data for " + colType + " is displayed in the table");
    }

    /**
     * Method to click on App details , navigate to the details page.
     * and verify Start Time details Page .
     */
    public String verifyStartTime(DbxJobsPageObject jobsPage) {
        String startTime = jobsPage.startTime.getText();
        logger.info("Application Start Name is " + startTime);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String startTimeAppPage = jobsPage.appStartTime.getText().trim();
        Assert.assertNotSame(startTime, startTimeAppPage, "Start Name is not displayed in the Header");
        return startTimeAppPage;
    }

    /**
     * Method to click on App details , navigate to the details page.
     * and verify Start Time details Page .
     */
    public String verifyRunCount(DbxJobsPageObject jobsPage) {
        String user = jobsPage.runCount.getText();
        logger.info("Application Run Count is " + user);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String clusterNameAppPage = jobsPage.appRunCount.getText().trim();
        Assert.assertNotSame(user, clusterNameAppPage, "Run Count is not displayed in the Header");
        return clusterNameAppPage;
    }
}

