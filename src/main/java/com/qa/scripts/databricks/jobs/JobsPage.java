package com.qa.scripts.databricks.jobs;

import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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
        String statusTable = jobsPage.status.getText().trim().toLowerCase();
        logger.info("Application Id is " + statusTable);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnStatus);
        jobsPage.clickOnStatus.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = jobsPage.appStatus.getText().trim().toLowerCase();
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
    public String validateLeftPaneKpis(List<WebElement> kpiList) {
        try {
            Assert.assertFalse(kpiList.isEmpty(), "The kpi list is empty");
            for (WebElement webElement : kpiList) {
                logger.info("The leftPane kpi is " + webElement.getText());
                String kpis = webElement.getText();
                Assert.assertNotSame("", kpis, "The kpis is empty");
                String[] kpisOut = kpis.split(":");
                String kpiName = kpisOut[0];
                String kpiVal = kpisOut[0];
                logger.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[0]);
                Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
                Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            MouseActions.clickOnElement(driver, jobsPageObject.closeAppsPageTab);
        }
        return String.valueOf(0);
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

    public void validateTableSorting(String sortBy) {
        Set<String> initialSet = new HashSet<String>();
        Set<String> resultantSet = new HashSet<String>();
        TreeSet<String> sort = null;

        switch (sortBy) {
            case "Last Run Status":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastRunStatusList
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortLastRunStatus.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastRunStatusList
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Job ID":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastJobID
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortJobID.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastJobID
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Job Name":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastJobName
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortJobName.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastJobName
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Cluster Name":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastClusterName
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortClusterName.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastClusterName
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Workspace":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastWorkspace
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortWorkspace.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastWorkspace
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "User":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastUser
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortUser.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastUser
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Start Time":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastStartTime
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortStartTime.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastStartTime
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Run Count":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastRunCount
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortRunCount.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastRunCount
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Duration":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastDuration
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortDuration.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastDuration
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Read":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastRead
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortRead.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastRead
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Write":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastWrite
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortWrite.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastWrite
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
            case "Cost":
                //Fetching data before applying sorting
                initialSet = jobsPageObject.lastCost
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                jobsPageObject.sortCost.click();

                //Fetching data after applying sorting
                resultantSet = jobsPageObject.lastCost
                        .stream().map(el -> el.getText())
                        .collect(Collectors.toSet());
                break;
        }

        sort = new TreeSet<String>(initialSet);
        Assert.assertNotEquals(resultantSet, sort);
    }

    /**
     * Method to validate the tasks attempt Map tab in Resources and stages tab.
     */
    public void validateTaskAttemptMapTab(DbxJobsPageObject jobsPage) {
        List<WebElement> footerNameList = jobsPage.taskAttFooterName;
        List<WebElement> footerValList = jobsPage.taskAttFooterVal;
        String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
        int totalTaskCnt = 0;
        for (int f = 0; f < footerNameList.size(); f++) {
            String footerName = footerNameList.get(f).getText();
            String footerValStr = footerValList.get(f).getText();
            int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]",
                    "").trim());
            totalTaskCnt += footerVal;
            logger.info("FooterName = " + footerName + " Value = " + footerVal);
        }
        logger.info("Total Task Attempts = " + totalTaskCnt + "");
    }

    /**
     * Method to validate the tasks attempt Reduce tab in Resources and stages tab.
     */
    public void validateTaskAttemptReduceTab(DbxJobsPageObject jobsPage, ExtentTest test) {
        try {
            List<WebElement> footerNameList = jobsPage.taskAttReduceFooterName;
            boolean isPresent = jobsPage.taskAttReduceFooterName.size() > 0;
            if (isPresent) {
                List<WebElement> footerValList = jobsPage.taskAttReduceFooterVal;
                Assert.assertFalse(footerValList.isEmpty(), "SUCCESS Attempts values " +
                        "not displayed");
                String pValStr = jobsPage.resourcesReducePieChartInternalVal.getText();
                String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
                int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
                logger.info("The value displayed inside the Pie Chart is " +
                        pieChartInternalVal);
                int totalTaskCnt = 0;
                for (int f = 0; f < footerNameList.size(); f++) {
                    String footerName = footerNameList.get(f).getText();
                    String footerValStr = footerValList.get(f).getText();
                    int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]", "").trim());
                    totalTaskCnt += footerVal;
                    logger.info("FooterName = " + footerName + " Value = " + footerVal);
                }
                logger.info("Total Task Attempts = " + totalTaskCnt + " pie chart val = " +
                        pieChartInternalVal);

            } else {
                test.log(LogStatus.WARNING, "Reduce tab is empty for application, " +
                        "There is no information available.");
            }
        } catch (NoSuchElementException ex) {
            waitExecuter.waitUntilElementPresent(jobsPage.closeAppsPageTab);
            waitExecuter.sleep(2000);
            MouseActions.clickOnElement(driver, jobsPage.closeAppsPageTab);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            throw new AssertionError(
                    "Caught exception while clicking on the resource tab.\n" + ex.getMessage());
        }
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateAttemptTab(DbxJobsPageObject jobsPage) {
        List<WebElement> footerNameList = jobsPage.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(), "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = jobsPage.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " + "not displayed");
        String pValStr = jobsPage.resourcesPieChartInternalVal.getText();
        String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
        int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
        logger.info("The value displayed inside the Pie Chart is " + pieChartInternalVal);
        int totalTaskCnt = 0;
        for (int f = 0; f < footerNameList.size(); f++) {
            String footerName = footerNameList.get(f).getText();
            String footerValStr = footerValList.get(f).getText();
            int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]", "").trim());
            totalTaskCnt += footerVal;
            logger.info("FooterName = " + footerName + " Value = " + footerVal);
        }
        logger.info("Total Task Attempts = " + totalTaskCnt + " pie chart val = " + pieChartInternalVal);
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateResourcesTab(DbxJobsPageObject jobsPage, ExtentTest test) {
        String[] expectedGraphTitle = {"Task Attempt (MAP)", "Task Attempt (REDUCE)", "Attempts", "Task Attempts"};
        boolean isGraphTitle = jobsPage.taskAttReduceFooterName.size() > 0;
        if (isGraphTitle) {
            waitExecuter.waitUntilPageFullyLoaded();
            List<WebElement> graphTitleList = jobsPage.resourcesGraphTitle;
            verifyAssertFalse(graphTitleList.isEmpty(), jobsPage, "No title displayed");
            List<WebElement> allGraphsList = jobsPage.resourcesAllGraphs;
            verifyAssertFalse(allGraphsList.isEmpty(), jobsPage, "No graphs displayed");
            for (int t = 0; t < graphTitleList.size(); t++) {
                String graphTitle = graphTitleList.get(t).getText();
                logger.info("Graph title is " + graphTitle);
                verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), jobsPage,
                        " The expected" + " Graph title doesnot match with the titles in the UI");
                String appDuration = jobsPage.rightPaneAppKpiVal.get(0).getText();
                logger.info("AppDuration is = " + appDuration);
                verifyAssertTrue(allGraphsList.get(t).isDisplayed(), jobsPage, " All Graphs are not displayed," +
                        " its an expected behaviour if app duration is < 90secs.\n The app Duration is " + appDuration);
                switch (graphTitle) {
                    case "Task Attempts":
                        logger.info("Validating the Graph " + graphTitle);
                        validateTaskAttemptTab(jobsPage);
                        break;
                }
                verifyAssertTrue(allGraphsList.get(0).isDisplayed(), jobsPage,
                        " No graph is displayed for " + graphTitle);
            }
        } else {
            test.log(LogStatus.WARNING, "Resources tab is no information available., " +
                    "Check manually if Resources tab were expected");
        }
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateTaskAttemptTab(DbxJobsPageObject jobsPage) {
        List<WebElement> footerNameList = jobsPage.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(), "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = jobsPage.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " + "not displayed");
        String pValStr = jobsPage.resourcesPieChartInternalVal.getText();
        String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
        int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
        logger.info("The value displayed inside the Pie Chart is " + pieChartInternalVal);
        int totalTaskCnt = 0;
        for (int f = 0; f < footerNameList.size(); f++) {
            String footerName = footerNameList.get(f).getText();
            String footerValStr = footerValList.get(f).getText();
            int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]", "").trim());
            totalTaskCnt += footerVal;
            logger.info("FooterName = " + footerName + " Value = " + footerVal);
        }
        logger.info("Total Task Attempts = " + totalTaskCnt + " pie chart val = " + pieChartInternalVal);
    }


    public void verifyAssertTrue(Boolean condition, DbxJobsPageObject jobsPage, String msg) {
        try {
            Assert.assertTrue(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin) {
                MouseActions.clickOnElement(driver, jobsPage.loadWinClose);
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, jobsPage.closeAppsPageTab);
            } else
                MouseActions.clickOnElement(driver, jobsPage.closeAppsPageTab);
            throw new AssertionError(msg);
        }
    }

    public String validateTagsTab(DbxJobsPageObject jobsPage, ExtentTest test) {
        List<WebElement> tagTableHeader = jobsPage.tagTableHeader;
        boolean isTagTable = jobsPage.taskAttReduceFooterName.size() > 0;
        if (isTagTable) {
            verifyAssertFalse(tagTableHeader.isEmpty(), jobsPage, " Tags header is not populated");
            List<WebElement> tagKeyList = jobsPage.tagKey;
            List<WebElement> tagValueList = jobsPage.tagValue;
            verifyAssertFalse((tagKeyList.isEmpty() || tagValueList.isEmpty()), jobsPage,
                    "The tags key value pair are empty");
            String tagValue = "";
            for (int k = 0; k < tagKeyList.size(); k++) {
                String key = tagKeyList.get(k).getText();
                String value = tagValueList.get(k).getText();
                logger.info("The key value pair is " + "Key = " + key + " | Value = " + value);
                verifyAssertTrue(key.length() > 0, jobsPage, "Key is not present");
                verifyAssertTrue(value.length() > 0, jobsPage, "Key is not present");
                if (key.equals("JobType"))
                    tagValue = value;
            }
            return tagValue;
        } else {
            test.log(LogStatus.WARNING, "Tags tab is 'No Tags found'., " +
                    "Check manually if Tags tab were expected");
        }
        return null;
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
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        try {
                            validateAnalysisTab(jobsPage, test);
                            test.log(LogStatus.PASS, "Analysis tab is populated");
                        } catch (Exception ex) {
                            test.log(LogStatus.WARNING, "Analysis tab is empty for application <app_id>, " +
                                    "Check manually if recommendations/Insights were expected");
                        }
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateResourcesTab(jobsPage,test);
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateErrorsTab(jobsPage,test);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(jobsPage,test);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(DbxJobsPageObject jobsPage,ExtentTest test) {
        String[] expectedErrorCategory = {"driver", "executor-", "rm-diagnostics"};
        boolean isErrorCategory = jobsPage.taskAttReduceFooterName.size() > 0;
        if (isErrorCategory) {
            List<WebElement> errorTypeList = jobsPage.errorCategories;
            verifyAssertFalse(errorTypeList.isEmpty(), jobsPage, " Errors tab is not populated");
            for (int e = 0; e < errorTypeList.size(); e++) {
                String errorType = errorTypeList.get(e).getText();
                String newErrorType = "";
                logger.info("Error Type is " + errorType);
                if (errorType.contains("executor-"))
                    newErrorType = "executor-";
                else
                    newErrorType = errorType;
                logger.info("New Error Type is " + errorType);
                verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(newErrorType), jobsPage,
                        " The UI error types displayed does not match with the Expected error types ");
            }
            List<WebElement> errorCollapsableList = jobsPage.errorCollapse;
            verifyAssertFalse(errorCollapsableList.isEmpty(), jobsPage, " No collapsable icon present");
            List<WebElement> errorContentList = jobsPage.errorContents;
            verifyAssertFalse(errorContentList.isEmpty(), jobsPage, " No error contents in the error tab");
            // TODO check for specific error string in the content list.
            boolean foundErrorMsg = false;
            String msg = "";
            for (int c = 0; c < errorCollapsableList.size(); c++) {
                MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
            }
        } else {
            test.log(LogStatus.WARNING, "Error tab is No Errors Found., " +
                    "Check manually if Error tab were expected");
        }
    }

    /**
     * Method to validate Jobs Application Summary Analysis tab.
     */
    public void validateAnalysisTab(DbxJobsPageObject jobsPage, ExtentTest test) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = jobsPage.insightsType;
        boolean isPresent = jobsPage.insightsType.size() > 0;
        if (isPresent) {
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
            List<WebElement> collapsableList = jobsPage.analysisCollapse;
            try {
                for (int c = 0; c < collapsableList.size(); c++) {
                    collapsableList.get(c).click();
                }
            } catch (Exception ex) {
                throw new AssertionError(
                        "Caught exception while clicking the collapsable" + " icon for insights.\n" + ex.getMessage());
            }
        } else {
            test.log(LogStatus.WARNING, "Analysis tab is empty for application <app_id>, " +
                    "Check manually if recommendations/Insights were expected");
        }
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

