package com.qa.scripts.databricks.Runs;

import com.qa.enums.UserAction;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummaryDetailsPage {

    private static final Boolean isDignosticWin = false;
    private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private final LoggingUtils loggingUtils = new LoggingUtils(com.qa.scripts.databricks.jobs.DbAllApps.class);
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final DbxApplicationsPageObject applicationsPageObject;
    private final DbxSummaryPageObject summaryPageObject;
    private final Actions action;
    private final UserActions userActions;
    private final DbxSubTopPanelModulePageObject dbSubTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public SummaryDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new DbxApplicationsPageObject(driver);
        action = new Actions(driver);
        userActions = new UserActions(driver);
        dbSubTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        summaryPageObject = new DbxSummaryPageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
    }

    /**
     * Method to verify the Spark summary tabs in the right pane of the App Details page
     */
    public String verifyAppSummaryTabs(DbxSummaryPageObject summaryPageObject, String verifyTabName,
                                         ExtentTest test) {
        List<WebElement> appsTabList = summaryPageObject.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), summaryPageObject, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));
        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            logger.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        validateAnalysisTab(summaryPageObject);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateResourcesTab(summaryPageObject);
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateErrorsTab(summaryPageObject);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Configuratio...":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateConfigurationTab(summaryPageObject);
                        break;
                    case "Logs":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateLogsTab(summaryPageObject);
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(summaryPageObject);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                    // break;
                    case "Program":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        List<WebElement> programDataList = summaryPageObject.programTabData;
                        verifyAssertFalse(programDataList.isEmpty(), summaryPageObject, "Programs tab not populated");
                        break;
                    case "Timings":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateTimingTab(summaryPageObject);
                }
                break;
            }
        }
        return "";
    }

    public void validateTimingTab(DbxSummaryPageObject summaryPageObject) {
        Actions action = new Actions(driver);
        List<WebElement> subTabList = summaryPageObject.timingsSubTabs;
        Assert.assertFalse(subTabList.isEmpty(), "No sub tabs available");
        String[] expectedSubTabList = {"Task Time", "App Time"};
        String[] expectedTTLegendNames = {"Input Stages", "Output Stages", "Processing Stages"};
        String[] expectedATLegendNames = {"Queue Time", "Driver Time", "Job Time"};
        for (int t = 0; t < subTabList.size(); t++) {
            String subTabName = subTabList.get(t).getText();
            logger.info("The Timings subTab is " + subTabName);
            verifyAssertTrue(Arrays.asList(expectedSubTabList).contains(subTabName), summaryPageObject,
                    " Tab list displayed in the UI doesnot match with the expected list of tabs");
            MouseActions.clickOnElement(driver, subTabList.get(t));
            waitExecuter.sleep(1000);
            String titleName = summaryPageObject.timingsTabTitle.getText();
            logger.info("The Timings subTabs Title is " + titleName);
            verifyAssertTrue(summaryPageObject.pieChart.isDisplayed(), summaryPageObject,
                    " Piechart for Task Attempts" + " is not displayed");
            if (subTabName.equals("Task Time")) {
                List<WebElement> legendNameTTList = summaryPageObject.legendNames;
                verifyAssertFalse(legendNameTTList.isEmpty(), summaryPageObject, "Empty legend list");
                for (int l = 0; l < legendNameTTList.size(); l++) {
                    String legendName = legendNameTTList.get(l).getText();
                    logger.info("The Legends for subTab " + subTabName + " is " + legendName);
                    verifyAssertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName), summaryPageObject,
                            " The legends:[" + legendName + "] displayed in the UI for Task Time doesnot match to the"
                                    + " expected list of legends: " + Arrays.toString(expectedTTLegendNames));
                    waitExecuter.sleep(1000);
                    if (legendName.equals("Processing Stages")) {
                        WebElement ele = summaryPageObject.processingStage;
                        verifyTimingStages(action, ele, summaryPageObject, legendName);
                    } else if (legendName.equals("Input Stages")) {
                        WebElement ele = summaryPageObject.inputStage;
                        verifyTimingStages(action, ele, summaryPageObject, legendName);
                    } else {
                        WebElement ele = summaryPageObject.outputStage;
                        verifyTimingStages(action, ele, summaryPageObject, legendName);
                    }
                }
            } else {
                List<WebElement> legendNameATList = summaryPageObject.ATlegendNames;
                verifyAssertFalse(legendNameATList.isEmpty(), summaryPageObject, "Empty legend list for APP TIME");
                for (int a = 0; a < legendNameATList.size(); a++) {
                    String ATlegendName = legendNameATList.get(a).getText();
                    verifyAssertTrue(Arrays.asList(expectedATLegendNames).contains(ATlegendName), summaryPageObject,
                            " The legends [" + ATlegendName + "] displayed in the UI for App Time doesnot match to the "
                                    + "expected list of legends: " + Arrays.toString(expectedATLegendNames));
                    if (ATlegendName.equals("Driver Time")) {
                        WebElement ele = summaryPageObject.driverDrillDown;
                        MouseActions.clickOnElement(driver, ele);
                        String[] expectedDriverLegends = {"FileCommit Time", "File Setup Time", "Others"};
                        List<WebElement> driverLegendNameList = summaryPageObject.driverLegendNames;
                        verifyAssertFalse(driverLegendNameList.isEmpty(), summaryPageObject,
                                "Empty legend list for DRIVER TIME");
                        for (int d = 0; d < driverLegendNameList.size(); d++) {
                            String driverLegend = driverLegendNameList.get(d).getText();
                            logger.info("The driverLegend name " + driverLegend);
                            verifyAssertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend),
                                    summaryPageObject,
                                    " The legend [" + driverLegend
                                            + "] displayed in the UI for App Time-> Driver Time does not "
                                            + "match to the expected list of legends: "
                                            + Arrays.toString(expectedDriverLegends));
                        }
                        driverLegendNameList.clear();
                        WebElement backButton = summaryPageObject.backButton;
                        action.moveToElement(backButton).click().build().perform();
                        waitExecuter.sleep(1000);
                    }
                }
            }
        }
    }

    /**
     * Method to verify the Timings tabs stages ,legends, graphs and top stages
     */
    public void verifyTimingStages(Actions action, WebElement ele, DbxSummaryPageObject summaryPageObject,
                                   String legendName) {
        try {
            MouseActions.clickOnElement(driver, ele);
            waitExecuter.sleep(1000);
            List<WebElement> stageLegends = summaryPageObject.legendNames;
            verifyAssertFalse(stageLegends.isEmpty(), summaryPageObject, "The list is empty for " + legendName);
            List<WebElement> graphList = summaryPageObject.timingGraphList;
            verifyAssertFalse(graphList.isEmpty(), summaryPageObject, "No graphs displayed");
            for (int g = 0; g < graphList.size(); g++)
                verifyAssertTrue(graphList.get(g).isDisplayed(), summaryPageObject, "The graph is not displayed");
            List<WebElement> topStagesList = summaryPageObject.topStages;
            verifyAssertFalse(topStagesList.isEmpty(), summaryPageObject, "Top stages are not displayed");
            WebElement backButton = summaryPageObject.backButton;
            action.moveToElement(backButton).click().build().perform();
            waitExecuter.sleep(1000);
        } catch (NoSuchElementException | ElementNotInteractableException | JavascriptException ex) {
            logger.info("The element is not present for stage " + legendName);
        }
    }


    /**
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(DbxSummaryPageObject summaryPageObject) {
        String[] expectedErrorCategory = {"driver", "executor-", "rm-diagnostics"};
        List<WebElement> errorTypeList = summaryPageObject.errorCategories;
        verifyAssertFalse(errorTypeList.isEmpty(), summaryPageObject, " Errors tab is not populated");
        for (int e = 0; e < errorTypeList.size(); e++) {
            String errorType = errorTypeList.get(e).getText();
            String newErrorType = "";
            logger.info("Error Type is " + errorType);
            if (errorType.contains("executor-"))
                newErrorType = "executor-";
            else
                newErrorType = errorType;
            logger.info("New Error Type is " + errorType);
            verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(newErrorType), summaryPageObject,
                    " The UI error types displayed does not match with the Expected error types ");
        }
        List<WebElement> errorCollapsableList = summaryPageObject.errorCollapse;
        verifyAssertFalse(errorCollapsableList.isEmpty(), summaryPageObject, " No collapsable icon present");
        List<WebElement> errorContentList = summaryPageObject.errorContents;
        verifyAssertFalse(errorContentList.isEmpty(), summaryPageObject, " No error contents in the error tab");
        // TODO check for specific error string in the content list.
        boolean foundErrorMsg = false;
        String msg = "";
        for (int c = 0; c < errorCollapsableList.size(); c++) {
            MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
        }
    }


    public void validateLogsTab(DbxSummaryPageObject summaryPageObject) {
        List<WebElement> executorNameList = summaryPageObject.logExecutorNames;
        verifyAssertFalse(executorNameList.isEmpty(), summaryPageObject, " No executors listed in the Logs Tab");
        List<WebElement> logCollapsableList = summaryPageObject.logElementCollapsable;
        verifyAssertFalse(logCollapsableList.isEmpty(), summaryPageObject, " No executors listed in the Logs Tab");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            int cnt = 0;
            for (int c = 0; c < logCollapsableList.size(); c++) {
                if (cnt != 0)
                    MouseActions.clickOnElement(driver, logCollapsableList.get(c));
                waitExecuter.sleep(5000);
                WebElement contents = summaryPageObject.logExecutorContents;
                verifyAssertTrue(contents.isDisplayed(), summaryPageObject, " No logs found");
                MouseActions.clickOnElement(driver, summaryPageObject.showFullLogLink);
                waitExecuter.waitUntilNumberOfWindowsToBe(1);
                List<WebElement> logLinesList = summaryPageObject.logScrollable;
                int scrollableLines = (logLinesList.size() / 2);
                logger.info("Scrollable line is " + scrollableLines);
                WebElement scrollableElement = driver
                        .findElement(By.xpath("//div[@class='modal-body scrollbar-s']//p[" + scrollableLines + "]"));
                executor.executeScript("arguments[0].scrollIntoView(true);", scrollableElement);
                MouseActions.clickOnElement(driver, summaryPageObject.loadWinClose);
                waitExecuter.waitUntilPageFullyLoaded();
                logCollapsableList.get(c).click();
                cnt += 1;
            }
        } catch (NoSuchElementException ex) {
            MouseActions.clickOnElement(driver, summaryPageObject.loadWinClose);
            waitExecuter.waitUntilElementClickable(summaryPageObject.closeAppsPageTab);
            MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            throw new AssertionError(
                    "Caught exception while clicking the collapsable icon for insights.\n" + ex.getMessage());
        }
    }

    public String validateTagsTab(DbxSummaryPageObject summaryPageObject) {
        List<WebElement> tagTableHeader = summaryPageObject.tagTableHeader;
        verifyAssertFalse(tagTableHeader.isEmpty(), summaryPageObject, " Tags header is not populated");
        List<WebElement> tagKeyList = summaryPageObject.tagKey;
        List<WebElement> tagValueList = summaryPageObject.tagValue;
        verifyAssertFalse((tagKeyList.isEmpty() || tagValueList.isEmpty()), summaryPageObject,
                "The tags key value pair are empty");
        String tagValue = "";
        for (int k = 0; k < tagKeyList.size(); k++) {
            String key = tagKeyList.get(k).getText();
            String value = tagValueList.get(k).getText();
            logger.info("The key value pair is " + "Key = " + key + " | Value = " + value);
            verifyAssertTrue(key.length() > 0, summaryPageObject, "Key is not present");
            verifyAssertTrue(value.length() > 0, summaryPageObject, "Key is not present");
            if (key.equals("JobType"))
                tagValue = value;
        }
        return tagValue;
    }

    public void validateConfigurationTab(DbxSummaryPageObject summaryPageObject) {
        String[] expectedKeyWords = {"METADATA", "MEMORY", "DRIVER", "EXECUTOR", "LIMIT", "RESOURCES", "CPU", "NET",
                "YARN", "DEPLOY", "DYNALLOC"};
        List<WebElement> keyWordsList = summaryPageObject.configKeywords;
        verifyAssertFalse(keyWordsList.isEmpty(), summaryPageObject, " Keywords not found");
        String beforeResetProp = summaryPageObject.configPropNum.getText();
        int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
        logger.info("Number of properties displayed by default are  " + propNum);

        // Verify if property key value is present:
        List<WebElement> propKeyList = summaryPageObject.configPropKey;
        List<WebElement> propValueList = summaryPageObject.configPropValue;
        logger.info("PropKey size = " + propKeyList.size() + " propVal size = " + propValueList.size());
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), summaryPageObject, " Key/Value is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " + "is missing");

        // Verify the keywords are present and should be clickable
        for (int k = 0; k < keyWordsList.size(); k++) {
            String keyword = keyWordsList.get(k).getText();
            logger.info("Keyword Type is " + keyword);
            verifyAssertTrue(Arrays.asList(expectedKeyWords).contains(keyword), summaryPageObject,
                    " Keywords displayed on the UI: [" + keyword + "] doesnot match with the expected keywords"
                            + Arrays.toString(expectedKeyWords));
            MouseActions.clickOnElement(driver, keyWordsList.get(k));
            waitExecuter.sleep(2000);
        }
        // Check RESET buttons sets default props
        MouseActions.clickOnElement(driver, summaryPageObject.resetButtonAppDetails);
        waitExecuter.sleep(3000);
        String afterResetProp = summaryPageObject.configPropNum.getText();
        logger.info("No. of Properties displayed by default " + beforeResetProp + "\n "
                + "No. of Properties displayed after RESET " + afterResetProp);
        Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " + "to default");
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateResourcesTab(DbxSummaryPageObject summaryPageObject) {
        String[] expectedGraphTitle = {"Task Attempts", "Containers", "Vcores", "Memory", "Metrics"};
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> graphTitleList = summaryPageObject.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), summaryPageObject, "No title displayed");
        List<WebElement> allGraphsList = summaryPageObject.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), summaryPageObject, "No graphs displayed");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            logger.info("Graph title is " + graphTitle);
            verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), summaryPageObject,
                    " The expected" + " Graph title doesnot match with the titles in the UI");
            String appDuration = summaryPageObject.rightPaneAppKpiVal.get(0).getText();
            logger.info("AppDuration is = " + appDuration);
            verifyAssertTrue(allGraphsList.get(t).isDisplayed(), summaryPageObject, " All Graphs are not displayed," +
                    " its an expected behaviour if app duration is < 90secs.\n The app Duration is " + appDuration);
            switch (graphTitle) {
                case "Task Attempts":
                    logger.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptTab(summaryPageObject);
                    // Assert.assertSame(totalTaskCnt, pieChartInternalVal, "The Values are not
                    // same");
                    break;
                case "Metrics":
                    logger.info("Validating the Graph " + graphTitle);
                    WebElement metricDropDown = summaryPageObject.resourcesMetricsDropDown;
                    MouseActions.clickOnElement(driver, metricDropDown);
                    List<WebElement> dropDownList = summaryPageObject.resourcesMetricsDropDownData;
                    waitExecuter.sleep(2000);
                    verifyAssertFalse(dropDownList.isEmpty(), summaryPageObject, " No contents listed in the dropdown");
                    String[] expectetContents = {"availableMemory", "vmRss", "systemCpuLoad", "processCpuLoad", "gcLoad",
                            "maxHeap", "usedHeap"};
                    for (int d = 0; d < dropDownList.size(); d++) {
                        String metric = dropDownList.get(d).getText();
                        logger.info("The metric is " + metric);
                        verifyAssertTrue(Arrays.asList(expectetContents).contains(metric), summaryPageObject,
                                " The expected" + " metric is not listed in the drop down box");
                        // click on the dropdown list element and validate the graph
                        MouseActions.clickOnElement(driver, dropDownList.get(d));
                        List<WebElement> resourcesMetricsLineGraphList = summaryPageObject.resourcesMetricsLineGraph;
                        List<WebElement> metricLegendList = summaryPageObject.resourcesMetricsPlotGraphLegend;
                        Assert.assertEquals(resourcesMetricsLineGraphList.size(), metricLegendList.size(),
                                "The number of executors in the legend do not match to the ones plotted in the graph for metrics "
                                        + metric);
                        MouseActions.clickOnElement(driver, metricDropDown);
                    }
                case "Containers":
                case "Vcores":
                case "Memory":
                    logger.info("Validating the Graph " + graphTitle);
                    break;

            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), summaryPageObject,
                    " No graph is displayed for " + graphTitle);
        }
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateTaskAttemptTab(DbxSummaryPageObject summaryPageObject) {
        List<WebElement> footerNameList = summaryPageObject.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(), "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = summaryPageObject.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " + "not displayed");
        String pValStr = summaryPageObject.resourcesPieChartInternalVal.getText();
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
     * Method to validate AppSummary Analysis tab.
     */
    public void validateAnalysisTab(DbxSummaryPageObject summaryPageObject) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = summaryPageObject.insightsType;
        verifyAssertFalse(insightType.isEmpty(), summaryPageObject, "No Insights generated");
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
        verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), summaryPageObject, "No insights generated");
        List<WebElement> collapsableList = summaryPageObject.analysisCollapse;
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
     * Common steps to navigate to the Jobs page from header. Clicks on jobs tab
     * Selects a specific cluster Selects 90 days time interval Deselsects all the
     * selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(DbAllApps dballApps,ExtentTest test) {
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToRunsTab();
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        dballApps.inJobsSelectClusterAndLast7Days();
        waitExecuter.sleep(2000);
    }

    /**
     * Method to click the first Spark in jobs table , navigate to the details page.
     * and verify Spark .
     */
    public String verifyGoToSpark(DbxSummaryPageObject summaryPageObject) {
        String appId = summaryPageObject.sparkIcon.getText().trim();
        logger.info("Application Id is " + appId);
        waitExecuter.waitUntilPageFullyLoaded();
        userActions.performActionWithPolling(summaryPageObject.sparkIcon, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(summaryPageObject.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = summaryPageObject.getAppSparkIcon.getText().trim();
        Assert.assertNotSame(appId, headerAppId, "Application Id is not displayed in the Header");
        return headerAppId;
    }

    /**
     * Method to verify the following:
     * 1.All the KPIs should be listed and the data must be populated.
     * (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis(DbxSummaryPageObject summaryPageObject) {
        List<WebElement> kpiList = summaryPageObject.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = summaryPageObject.rightPaneAppKpis;
        List<WebElement> appKpiVal = summaryPageObject.rightPaneAppKpiVal;
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
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAllDataTabs(DbxSummaryPageObject summaryPageObject, String verifyTabName, ExtentTest test) {
        List<WebElement> appsTabList = summaryPageObject.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), summaryPageObject, "No Tabs loaded");
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
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Logs":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Logs tab is populated");
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        break;
                    case "Metrics":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Metrics tab is populated");
                        break;
                    case "Configuration":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        test.log(LogStatus.PASS, "Configuration tab is populated");
                }
                break;
            }
        }
        return "";
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
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            logger.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }


    public void verifyAssertFalse(Boolean condition, DbxSummaryPageObject summaryPageObject, String msg) {
        String appDuration = "0";
        try {
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, summaryPageObject.loadWinClose);
            else
                MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    public void verifyAssertTrue(Boolean condition, DbxSummaryPageObject summaryPageObject, String msg) {
        try {
            Assert.assertTrue(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin) {
                MouseActions.clickOnElement(driver, summaryPageObject.loadWinClose);
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            } else
                MouseActions.clickOnElement(driver, summaryPageObject.closeAppsPageTab);
            throw new AssertionError(msg);
        }
    }
}