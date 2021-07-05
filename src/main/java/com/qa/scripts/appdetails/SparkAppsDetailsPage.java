package com.qa.scripts.appdetails;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.AppDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparkAppsDetailsPage {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private JavascriptExecutor jse;
    private UserActions userActions;
    private AppDetailsPageObject appDetailsPageObject;
    private SparkAppsDetailsPageObject sparkPageObject;

    Logger logger = Logger.getLogger(SparkAppsDetailsPage.class.getName());
    private static Boolean isDignosticWin = false;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public SparkAppsDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        userActions = new UserActions(driver);
        sparkPageObject = new SparkAppsDetailsPageObject(driver);
        appDetailsPageObject = new AppDetailsPageObject(driver);
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateTaskAttemptTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        List<WebElement> footerNameList = sparkAppPageObj.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(), "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = sparkAppPageObj.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " + "not displayed");
        String pValStr = sparkAppPageObj.resourcesPieChartInternalVal.getText();
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
    public void validateAnalysisTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = sparkAppPageObj.insightsType;
        verifyAssertFalse(insightType.isEmpty(), sparkAppPageObj, "No Insights generated");
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
        verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), sparkAppPageObj, "No insights generated");
        List<WebElement> collapsableList = sparkAppPageObj.analysisCollapse;
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
     * Method to validate AppSummary Resource tab.
     */
    public void validateResourcesTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        String[] expectedGraphTitle = { "Task Attempts", "Containers", "Vcores", "Memory", "Metrics" };
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> graphTitleList = sparkAppPageObj.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), sparkAppPageObj, "No title displayed");
        List<WebElement> allGraphsList = sparkAppPageObj.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), sparkAppPageObj, "No graphs displayed");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            logger.info("Graph title is " + graphTitle);
            verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), sparkAppPageObj,
                    " The expected" + " Graph title doesnot match with the titles in the UI");
            verifyAssertTrue(allGraphsList.get(t).isDisplayed(), sparkAppPageObj, " All Graphs are not displayed");
            switch (graphTitle) {
                case "Task Attempts":
                    logger.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptTab(sparkAppPageObj);
                    // Assert.assertSame(totalTaskCnt, pieChartInternalVal, "The Values are not
                    // same");
                    break;
                case "Metrics":
                    logger.info("Validating the Graph " + graphTitle);
                    WebElement metricDropDown = sparkAppPageObj.resourcesMetricsDropDown;
                    MouseActions.clickOnElement(driver, metricDropDown);
                    List<WebElement> dropDownList = sparkAppPageObj.resourcesMetricsDropDownData;
                    waitExecuter.sleep(2000);
                    verifyAssertFalse(dropDownList.isEmpty(), sparkAppPageObj, " No contents listed in the dropdown");
                    String[] expectetContents = { "availableMemory", "vmRss", "systemCpuLoad", "processCpuLoad", "gcLoad",
                            "maxHeap", "usedHeap" };
                    for (int d = 0; d < dropDownList.size(); d++) {
                        String metric = dropDownList.get(d).getText();
                        logger.info("The metric is " + metric);
                        verifyAssertTrue(Arrays.asList(expectetContents).contains(metric), sparkAppPageObj,
                                " The expected" + " metric is not listed in the drop down box");
                        // click on the dropdown list element and validate the graph
                        MouseActions.clickOnElement(driver, dropDownList.get(d));
                        List<WebElement> resourcesMetricsLineGraphList = sparkAppPageObj.resourcesMetricsLineGraph;
                        List<WebElement> metricLegendList = sparkAppPageObj.resourcesMetricsPlotGraphLegend;
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
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), sparkAppPageObj,
                    " No graph is displayed for " + graphTitle);
        }
    }

    /**
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        String[] expectedErrorCategory = { "driver", "executor-", "rm-diagnostics" };
        List<WebElement> errorTypeList = sparkAppPageObj.errorCategories;
        verifyAssertFalse(errorTypeList.isEmpty(), sparkAppPageObj, " Errors tab is not populated");
        for (int e = 0; e < errorTypeList.size(); e++) {
            String errorType = errorTypeList.get(e).getText();
            String newErrorType = "";
            logger.info("Error Type is " + errorType);
            if (errorType.contains("executor-"))
                newErrorType = "executor-";
            else
                newErrorType = errorType;
            logger.info("New Error Type is " + errorType);
            verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(newErrorType), sparkAppPageObj,
                    " The UI error types displayed does not match with the Expected error types ");
        }
        List<WebElement> errorCollapsableList = sparkAppPageObj.errorCollapse;
        verifyAssertFalse(errorCollapsableList.isEmpty(), sparkAppPageObj, " No collapsable icon present");
        List<WebElement> errorContentList = sparkAppPageObj.errorContents;
        verifyAssertFalse(errorContentList.isEmpty(), sparkAppPageObj, " No error contents in the error tab");
        // TODO check for specific error string in the content list.
        boolean foundErrorMsg = false;
        String msg = "";
//    for (int c = 0; c < errorContentList.size(); c++) {
//      String expectedErrorString = "Exception";
//      String actualErrorString = errorContentList.get(c).getText();
//      if (actualErrorString.contains(expectedErrorString)) {
//        foundErrorMsg = true;
//        msg = "Found the expected error message in the Errors Tab " +
//            "Expected = " + expectedErrorString + "Actual Error Message = " + actualErrorString;
//        break;
//      }
//    }
//    verifyAssertTrue(foundErrorMsg, sparkAppPageObj, " Expected Error message not present in the Error tab");
//    logger.info(msg);
        for (int c = 0; c < errorCollapsableList.size(); c++) {
            MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
        }
    }

    public void validateConfigurationTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        String[] expectedKeyWords = { "METADATA", "MEMORY", "DRIVER", "EXECUTOR", "LIMIT", "RESOURCES", "CPU", "NET",
                "YARN", "DEPLOY", "DYNALLOC"};
        List<WebElement> keyWordsList = sparkAppPageObj.configKeywords;
        verifyAssertFalse(keyWordsList.isEmpty(), sparkAppPageObj, " Keywords not found");
        String beforeResetProp = sparkAppPageObj.configPropNum.getText();
        int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
        logger.info("Number of properties displayed by default are  " + propNum);

        // Verify if property key value is present:
        List<WebElement> propKeyList = sparkAppPageObj.configPropKey;
        List<WebElement> propValueList = sparkAppPageObj.configPropValue;
        logger.info("PropKey size = " + propKeyList.size() + " propVal size = " + propValueList.size());
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), sparkAppPageObj, " Key/Value is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " + "is missing");

        // Verify the keywords are present and should be clickable
        for (int k = 0; k < keyWordsList.size(); k++) {
            String keyword = keyWordsList.get(k).getText();
            logger.info("Keyword Type is " + keyword);
            verifyAssertTrue(Arrays.asList(expectedKeyWords).contains(keyword), sparkAppPageObj,
                    " Keywords displayed on the UI: [" + keyword + "] doesnot match with the expected keywords"
                            + Arrays.toString(expectedKeyWords));
            MouseActions.clickOnElement(driver, keyWordsList.get(k));
            waitExecuter.sleep(2000);
        }
        // Check RESET buttons sets default props
        MouseActions.clickOnElement(driver, sparkAppPageObj.resetButtonAppDetails);
        waitExecuter.sleep(3000);
        String afterResetProp = sparkAppPageObj.configPropNum.getText();
        logger.info("No. of Properties displayed by default " + beforeResetProp + "\n "
                + "No. of Properties displayed after RESET " + afterResetProp);
        Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " + "to default");
    }

    public void validateLogsTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        List<WebElement> executorNameList = sparkAppPageObj.logExecutorNames;
        verifyAssertFalse(executorNameList.isEmpty(), sparkAppPageObj, " No executors listed in the Logs Tab");
        List<WebElement> logCollapsableList = sparkAppPageObj.logElementCollapsable;
        verifyAssertFalse(logCollapsableList.isEmpty(), sparkAppPageObj, " No executors listed in the Logs Tab");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            int cnt = 0;
            for (int c = 0; c < logCollapsableList.size(); c++) {
                if (cnt != 0)
                    MouseActions.clickOnElement(driver, logCollapsableList.get(c));
                waitExecuter.sleep(5000);
                WebElement contents = sparkAppPageObj.logExecutorContents;
                verifyAssertTrue(contents.isDisplayed(), sparkAppPageObj, " No logs found");
                MouseActions.clickOnElement(driver, sparkAppPageObj.showFullLogLink);
                waitExecuter.waitUntilNumberOfWindowsToBe(1);
                List<WebElement> logLinesList = sparkAppPageObj.logScrollable;
                int scrollableLines = (logLinesList.size() / 2);
                logger.info("Scrollable line is " + scrollableLines);
                WebElement scrollableElement = driver
                        .findElement(By.xpath("//div[@class='modal-body']//p/br[" + scrollableLines + "]"));
                executor.executeScript("arguments[0].scrollIntoView(true);", scrollableElement);
                MouseActions.clickOnElement(driver, sparkAppPageObj.loadWinClose);
                waitExecuter.waitUntilPageFullyLoaded();
                logCollapsableList.get(c).click();
                cnt += 1;
            }
        } catch (NoSuchElementException ex) {
            throw new AssertionError(
                    "Caught exception while clicking the collapsable icon for insights.\n" + ex.getMessage());
        }
    }

    public String validateTagsTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        List<WebElement> tagTableHeader = sparkAppPageObj.tagTableHeader;
        verifyAssertFalse(tagTableHeader.isEmpty(), sparkAppPageObj, " Tags header is not populated");
        List<WebElement> tagKeyList = sparkAppPageObj.tagKey;
        List<WebElement> tagValueList = sparkAppPageObj.tagValue;
        verifyAssertFalse((tagKeyList.isEmpty() || tagValueList.isEmpty()), sparkAppPageObj,
                "The tags key value pair are empty");
        String tagValue = "";
        for (int k = 0; k < tagKeyList.size(); k++) {
            String key = tagKeyList.get(k).getText();
            String value = tagValueList.get(k).getText();
            logger.info("The key value pair is " + "Key = " + key + " | Value = " + value);
            verifyAssertTrue(key.length() > 0, sparkAppPageObj, "Key is not present");
            verifyAssertTrue(value.length() > 0, sparkAppPageObj, "Key is not present");
            if (key.equals("JobType"))
                tagValue = value;
        }
        return tagValue;
    }

    public void validateTimingTab(SparkAppsDetailsPageObject sparkAppPageObj) {
        Actions action = new Actions(driver);
        List<WebElement> subTabList = sparkAppPageObj.timingsSubTabs;
        Assert.assertFalse(subTabList.isEmpty(), "No sub tabs available");
        String[] expectedSubTabList = { "Task Time", "App Time" };
        String[] expectedTTLegendNames = { "Input Stages", "Output Stages", "Processing Stages" };
        String[] expectedATLegendNames = { "Queue Time", "Driver Time", "Job Time" };
        for (int t = 0; t < subTabList.size(); t++) {
            String subTabName = subTabList.get(t).getText();
            logger.info("The Timings subTab is " + subTabName);
            verifyAssertTrue(Arrays.asList(expectedSubTabList).contains(subTabName), sparkAppPageObj,
                    " Tab list displayed in the UI doesnot match with the expected list of tabs");
            MouseActions.clickOnElement(driver, subTabList.get(t));
            waitExecuter.sleep(1000);
            String titleName = sparkAppPageObj.timingsTabTitle.getText();
            logger.info("The Timings subTabs Title is " + titleName);
            verifyAssertTrue(sparkAppPageObj.pieChart.isDisplayed(), sparkAppPageObj,
                    " Piechart for Task Attempts" + " is not displayed");
            if (subTabName.equals("Task Time")) {
                List<WebElement> legendNameTTList = sparkAppPageObj.legendNames;
                verifyAssertFalse(legendNameTTList.isEmpty(), sparkAppPageObj, "Empty legend list");
                for (int l = 0; l < legendNameTTList.size(); l++) {
                    String legendName = legendNameTTList.get(l).getText();
                    logger.info("The Legends for subTab " + subTabName + " is " + legendName);
                    verifyAssertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName), sparkAppPageObj,
                            " The legends:[" + legendName + "] displayed in the UI for Task Time doesnot match to the"
                                    + " expected list of legends: " + Arrays.toString(expectedTTLegendNames));
                    waitExecuter.sleep(1000);
                    if (legendName.equals("Processing Stages")) {
                        WebElement ele = sparkAppPageObj.processingStage;
                        verifyTimingStages(action, ele, sparkAppPageObj, legendName);
                    } else if (legendName.equals("Input Stages")) {
                        WebElement ele = sparkAppPageObj.inputStage;
                        verifyTimingStages(action, ele, sparkAppPageObj, legendName);
                    } else {
                        WebElement ele = sparkAppPageObj.outputStage;
                        verifyTimingStages(action, ele, sparkAppPageObj, legendName);
                    }
                }
            } else {
                List<WebElement> legendNameATList = sparkAppPageObj.ATlegendNames;
                verifyAssertFalse(legendNameATList.isEmpty(), sparkAppPageObj, "Empty legend list for APP TIME");
                for (int a = 0; a < legendNameATList.size(); a++) {
                    String ATlegendName = legendNameATList.get(a).getText();
                    verifyAssertTrue(Arrays.asList(expectedATLegendNames).contains(ATlegendName), sparkAppPageObj,
                            " The legends [" + ATlegendName + "] displayed in the UI for App Time doesnot match to the "
                                    + "expected list of legends: " + Arrays.toString(expectedATLegendNames));
                    if (ATlegendName.equals("Driver Time")) {
                        WebElement ele = sparkAppPageObj.driverDrillDown;
                        MouseActions.clickOnElement(driver, ele);
                        String[] expectedDriverLegends = { "FileCommit Time", "File Setup Time", "Others" };
                        List<WebElement> driverLegendNameList = sparkAppPageObj.driverLegendNames;
                        verifyAssertFalse(driverLegendNameList.isEmpty(), sparkAppPageObj,
                                "Empty legend list for DRIVER TIME");
                        for (int d = 0; d < driverLegendNameList.size(); d++) {
                            String driverLegend = driverLegendNameList.get(d).getText();
                            logger.info("The driverLegend name " + driverLegend);
                            verifyAssertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend),
                                    sparkAppPageObj,
                                    " The legend [" + driverLegend
                                            + "] displayed in the UI for App Time-> Driver Time does not "
                                            + "match to the expected list of legends: "
                                            + Arrays.toString(expectedDriverLegends));
                        }
                        driverLegendNameList.clear();
                        WebElement backButton = sparkAppPageObj.backButton;
                        action.moveToElement(backButton).click().build().perform();
                        waitExecuter.sleep(1000);
                    }
                }
            }
        }
    }

    /**
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAppSummaryTabs(SparkAppsDetailsPageObject sparkAppPageObj, String verifyTabName,
                                       ExtentTest test) {
        List<WebElement> appsTabList = sparkAppPageObj.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), sparkAppPageObj, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));

        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            logger.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                       // MouseActions.clickOnElement(driver, appsTabList.get(i));
                        validateAnalysisTab(sparkAppPageObj);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateResourcesTab(sparkAppPageObj);
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateErrorsTab(sparkAppPageObj);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Configuratio...":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateConfigurationTab(sparkAppPageObj);
                        break;
                    case "Logs":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateLogsTab(sparkAppPageObj);
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(sparkAppPageObj);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                    // break;
                    case "Program":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        List<WebElement> programDataList = sparkAppPageObj.programTabData;
                        verifyAssertFalse(programDataList.isEmpty(), sparkAppPageObj, "Programs tab not populated");
                        break;
                    case "Timings":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateTimingTab(sparkAppPageObj);
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to verify the Timings tabs stages ,legends, graphs and top stages
     */
    public void verifyTimingStages(Actions action, WebElement ele, SparkAppsDetailsPageObject sparkAppPageObj,
                                   String legendName) {
        try {
            MouseActions.clickOnElement(driver, ele);
            waitExecuter.sleep(1000);
            List<WebElement> stageLegends = sparkAppPageObj.legendNames;
            verifyAssertFalse(stageLegends.isEmpty(), sparkAppPageObj, "The list is empty for " + legendName);
            List<WebElement> graphList = sparkAppPageObj.timingGraphList;
            verifyAssertFalse(graphList.isEmpty(), sparkAppPageObj, "No graphs displayed");
            for (int g = 0; g < graphList.size(); g++)
                verifyAssertTrue(graphList.get(g).isDisplayed(), sparkAppPageObj, "The graph is not displayed");
            List<WebElement> topStagesList = sparkAppPageObj.topStages;
            verifyAssertFalse(topStagesList.isEmpty(), sparkAppPageObj, "Top stages are not displayed");
            WebElement backButton = sparkAppPageObj.backButton;
            action.moveToElement(backButton).click().build().perform();
            waitExecuter.sleep(1000);
        } catch (NoSuchElementException | ElementNotInteractableException | JavascriptException ex) {
            logger.info("The element is not present for stage " + legendName);
        }
    }

    public void verifyStreamingAppsComponent(SparkAppsDetailsPageObject sparkPageObj, DatePicker datePicker) {
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        WebElement lineChart = sparkPageObj.streamingLineChart;
        WebElement waveChart = sparkPageObj.streamingWaveChart;
        verifyAssertTrue(lineChart.isDisplayed(), sparkPageObj, "Line chart is not displayed");
        verifyAssertTrue(waveChart.isDisplayed(), sparkPageObj, "Wave chart is not displayed");
        String completedBatchNum = sparkPageObj.completedBatchTitle.getText().split(":")[1];
        int completedBatches = Integer.parseInt(completedBatchNum.replaceAll("[^\\dA-Za-z ]", "").trim());
        verifyAssertTrue(completedBatches > 0, sparkPageObj,
                "There are no completed batches count displayed in the title");

        // Verify tables have data in it
        List<WebElement> tableContentList = sparkPageObj.streamTableRows;
        verifyAssertFalse(tableContentList.isEmpty(), sparkPageObj, "The spark streaming table is empty.");
    }

    /**
     * Method to verify if the component tabs like navigation | Gantt chart | jobs
     * is present and contains data if job count is > 0 if validateCompData = true
     * validate each component tab data if validateExecutorTab = true validate jobs
     * execution tabs data.
     */
    public void verifyAppsComponent(SparkAppsDetailsPageObject sparkPageObj, Boolean validateCompData,
                                    Boolean validateExecutorTab, Boolean validateStageTab) {
        List<WebElement> componentList = sparkPageObj.component_element;
        logger.info("ComponentList is " + componentList.size());
        int navigationRows = 0;
        String tabName = "";
        for (int j = 0; j < componentList.size(); j++) {
            if (j != 3)
                tabName = componentList.get(j).getText();
            switch (j) {
                case 0:
                    Assert.assertEquals(tabName, "Navigation", "Navigation tab not present");
                    List<WebElement> navigationRowList = sparkPageObj.navigationTableRows;
                    navigationRows = navigationRowList.size();
                    logger.info("Navigation Rows are " + navigationRows);
                    if (validateCompData) {
                        List<WebElement> headerList = sparkPageObj.navigationHeaders;
                        Assert.assertFalse(headerList.isEmpty(), "No headers for Navigation table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            logger.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (navigationRows > 0) {
                            for (int rows = 1; rows <= navigationRows; rows++) {
                                for (int col = 1; col <= headerList.size(); col++) {
                                    String data = driver.findElement(By.xpath(
                                            "//*[@id='appNavigation-body']/" + "tr[" + rows + "]/td[" + col + "]/span"))
                                            .getText();
                                    logger.info("The data is " + data);
                                    Assert.assertNotSame("", data);
                                }
                            }
                        }
                    }
                    validateStageAndStageData(navigationRows, navigationRowList, sparkPageObj, validateExecutorTab,
                            validateStageTab);
                    break;
                case 1:
                    // The component is Gantt Chart ,click it and then verify the no. rows in the
                    // table
                    Assert.assertEquals(tabName, "Gantt Chart", "Gantt Chart tab not present");
                    MouseActions.clickOnElement(driver, componentList.get(j));
                    List<WebElement> ganttChartTableRows = sparkPageObj.ganttChartTable;
                    logger.info("No. of rows in Gantt Chart tables are " + ganttChartTableRows.size());
                    if (validateCompData) {
                        List<WebElement> headerList = sparkPageObj.ganttChartHeaders;
                        verifyAssertFalse(headerList.isEmpty(), sparkPageObj,
                                " No headers for Gantt Chart table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            logger.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (ganttChartTableRows.size() > 0) {
                            for (int rows = 0; rows < ganttChartTableRows.size(); rows++) {
                                String jobId = sparkPageObj.ganttChartJobId.get(rows).getText();
                                String startTime = sparkPageObj.ganttChartStartTime.get(rows).getText();
                                String duration = sparkPageObj.ganttChartDuration.get(rows).getText();
                                logger.info("Duration = " + duration + " JobId = " + jobId + " starttime = " + startTime);
                                Assert.assertNotSame("", jobId, "Value for jobId missing");
                                Assert.assertNotSame("", startTime, "Value for startTime missing");
                                Assert.assertNotSame("", duration, "Value for duration missing");
                            }
                        }
                    }
                    break;
                case 2:
                    Assert.assertEquals(tabName, navigationRows + " Jobs", "Jobs text not present");
                    String[] jobCountArr = componentList.get(j).getText().split("\\s+");
                    int jobCnt = Integer.parseInt(jobCountArr[0]);
                    Assert.assertEquals(jobCnt, navigationRows, "JobCnt and navigation rows donot match");
                    logger.info("JobCount is " + jobCnt);
                    break;
            }
        }
    }

    /**
     * Method to validate the stage table header and the data. if
     * validateExecutorTab = true, validate each jobs execution tabs data
     */
    public void validateStageAndStageData(int navigationRows, List<WebElement> navigationRowList,
                                          SparkAppsDetailsPageObject sparkPageObj, Boolean validateExecutorTab, Boolean validateStageTabs) {
        if (navigationRows > 0) {
            String[] expectedHeader = { "Stage ID", "Start Time", "Duration", "Tasks", "Shuffle Read", "Shuffle Write",
                    "Input", "Output" };
            // click the jobId to sort it .
            MouseActions.clickOnElement(driver, sparkPageObj.singleJobHeader);
            for (int rows = 0; rows < navigationRows; rows++) {
                navigationRowList.get(rows).click();
                waitExecuter.sleep(2000);
                if (validateExecutorTab) {
                    verifyAssertTrue(sparkPageObj.DAGData.isDisplayed(), sparkPageObj, " Dag data is not displayed ");
                    logger.info("Dags data is displayed ");
                    List<WebElement> rddBlockList = sparkPageObj.rddBlockList;
                    verifyAssertFalse(rddBlockList.isEmpty(), sparkPageObj, "No DAGs data");
                    logger.info("No. of RDD blocks in the flow chart is " + rddBlockList.size());
                    for (int i = 0; i < rddBlockList.size(); i++) {
                        verifyAssertTrue(rddBlockList.get(i).isDisplayed(), sparkPageObj,
                                "FlowChart doesnot have the RDD blocks displayed");
                    }
                    List<WebElement> rddNumberList = sparkPageObj.rddBlockNumList;
                    verifyAssertFalse(rddNumberList.isEmpty(), sparkPageObj, "Rdd block numbers are present");
                }
                List<WebElement> stageHeaderList = sparkPageObj.stageHeaders;
                for (int i = 0; i < stageHeaderList.size(); i++) {
                    Assert.assertNotSame("", stageHeaderList.get(i).getText());
                    Assert.assertEquals(expectedHeader[i], stageHeaderList.get(i).getText(),
                            "expected stage header do not match to the one in the UI");
                }
                if (validateStageTabs)
                    validateStagesTabs(sparkPageObj);
            }
        }
    }

    /**
     * Method to validate the tabs for each job stage.
     */
    public void validateStagesTabs(SparkAppsDetailsPageObject sparkPageObj) {
        // click the stageId to sort it
        driver.findElement(By.xpath("//*[@id='sparkStageNavigation-head']/tr/th[1]")).click();
        List<WebElement> stageRowList = sparkPageObj.stageRows;
        verifyAssertFalse(stageRowList.isEmpty(), sparkPageObj, " No stages displayed for JobId");
        for (int i = 0; i < stageRowList.size(); i++) {
            stageRowList.get(i).click();
            waitExecuter.sleep(1000);
            List<WebElement> stageTabsList = sparkPageObj.stagesTab;
            verifyAssertFalse(stageTabsList.isEmpty(), sparkPageObj, " No tabs displayed for selected stage");
            for (int t = 0; t < stageTabsList.size(); t++) {
                String tabName = stageTabsList.get(t).getText();
                switch (tabName) {
                    case "Taskattempt":
                        logger.info("Validating the stage tab Taskattempt");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        validateTaskAttemptTab(sparkPageObj);
                        break;
                    case "Program":
                        logger.info("Validating the stage tab Program");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        try {
                            List<WebElement> programDataList = sparkPageObj.programTabData;
                            verifyAssertFalse(programDataList.isEmpty(), sparkPageObj, " Program data not found");
                            WebElement sourceText = sparkPageObj.programSourceLinkText;
                            String sourceStr = sourceText.getText();
                            int lineNo = Integer.parseInt(sourceStr.split(":")[2].trim());
                            logger.info("LineNo to verify in the Programs tab is " + lineNo);
                            MouseActions.clickOnElement(driver, sparkPageObj.programSourceLink);
                            List<WebElement> programList = sparkPageObj.programTabData;
                            logger.info("Program list size- "+programList.size());
                            verifyAssertFalse(programList.isEmpty(), sparkPageObj, "Program tab not verified");
                            //sparkPageObj.programDataNotFound.getText());
                            WebElement highlightedLine = driver.findElement(
                                    By.xpath("//*[@id=\"app-query\"]" + "/div[contains(@data-range,'" + lineNo + "')]"));
                            verifyAssertTrue(highlightedLine.isDisplayed(), sparkPageObj,
                                    " The line no from the stage source doesnot "
                                            + "point to the same line in the source program file");
                        } catch (NoSuchElementException ex) {
                            throw new AssertionError("Programs tab got exception " + ex.getMessage());
                        }
                        break;
                    case "Timeline":
                        logger.info("Validating the stage tab Timeline");
                        MouseActions.clickOnElement(driver, stageTabsList.get(i));
                        waitExecuter.sleep(1000);
                        String[] expectedHeaderList = { "ShuffleMap Input (KB)", "Shuffle Map Time(sec)",
                                "ShuffleMap Output (KB)", "Disk Bytes Spilled (KB)", "Memory Bytes Spilled (KB)",
                                "Records Read (count)" };
                        String[] expectedSubTabs = { "Timeline", "Selected Tass" };
                        List<WebElement> headerlist = sparkPageObj.stagesTimelineHeader;
                        verifyAssertFalse(headerlist.isEmpty(), sparkPageObj, " No header displayed");
                        List<WebElement> barGraphList = sparkPageObj.timelineBarGraph;
                        logger.info("Bar graph list size- "+barGraphList.size());
                        verifyAssertFalse(barGraphList.isEmpty(), sparkPageObj, " No bar graphs displayed");
                        for (int h = 0; h < headerlist.size(); h++) {
                            String headerName = headerlist.get(h).getText();
                            logger.info("The header name is " + headerName);
                            verifyAssertTrue(Arrays.asList(expectedHeaderList).contains(headerName), sparkPageObj,
                                    "Header names displayed on the UI does not match with the expected headerList");
                            verifyAssertTrue(barGraphList.get(h).isDisplayed(), sparkPageObj,
                                    " The bar graph for " + "" + headerName + " is not displayed");
                        }
                        List<WebElement> subTabList = sparkPageObj.stagesTimelineSubTab;
                        Assert.assertFalse(subTabList.isEmpty(), "No sub tab displayed for Timeline tab");
                        for (int s = 0; s < subTabList.size(); s++) {
                            String subTask = subTabList.get(s).getText();
                            logger.info("The subTask is " + subTask);
                            Assert.assertTrue(Arrays.asList(expectedSubTabs).contains(subTask),
                                    "Subtask names displayed on the UI does not match with the expected list");
                        }
                        break;
                    case "Timings":
                        logger.info("Validating the stage tab Timings");
                        MouseActions.clickOnElement(driver, stageTabsList.get(i));
                        waitExecuter.sleep(1000);
                        List<WebElement> stageTimingHeaderList = sparkPageObj.stageTimingHeaders;
                        Assert.assertFalse(stageTimingHeaderList.isEmpty(),
                                "The headers in the timmings " + "tab not displayed");
                        String[] expectedTimingHeaders = { "Stage Time Distribution", "IO Metrics", "Time Metrics" };
                        List<WebElement> legendList = sparkPageObj.legendNames;
                        Assert.assertFalse(legendList.isEmpty(), "No legends displayed");
                        List<WebElement> graphList = sparkPageObj.timingGraphList;
                        Assert.assertFalse(graphList.isEmpty(), "No graphs displayed");

                        for (int s = 0; s < stageTimingHeaderList.size(); s++) {
                            String stageTimingHeader = stageTimingHeaderList.get(s).getText();
                            logger.info("The stageTimingHeader is " + stageTimingHeader);
                            Assert.assertTrue(Arrays.asList(expectedTimingHeaders).contains(stageTimingHeader),
                                    "Stage Timing header names displayed on the UI does not match with "
                                            + "the expected list");
                        }
                        break;
                }
            }
        }
    }

    /**
     * Method to verify the following: 1.All the KPIs should be listed and the data
     * must be populated. (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis(SparkAppsDetailsPageObject sparkPageObj) {
        List<WebElement> kpiList = sparkPageObj.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = sparkPageObj.rightPaneAppKpis;
        List<WebElement> appKpiVal = sparkPageObj.rightPaneAppKpiVal;
        Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
        Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
        String appDuration = "0";
        for (int i = 0; i < appKpis.size(); i++) {
            Assert.assertNotSame("", appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotSame("", appKpiVal.get(i).getText(), "Kpi Value is empty");
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
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            logger.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppId(SparkAppsDetailsPageObject sparkPageObj, ApplicationsPageObject appPageObj) {
        String appId = sparkPageObj.getAppId.getText().trim();
        logger.info("Spark application Id is " + appId);
        waitExecuter.waitUntilPageFullyLoaded();
     //   waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);
       // appPageObj.getTypeFromTable.click();
        sparkPageObj.getAppId.click();
        waitExecuter.waitUntilElementClickable(sparkPageObject.closeAppsPageTab);
        //waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = sparkPageObj.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Spark Application Id is not displayed in the Header");
        return headerAppId;
    }

    /**
     * Common steps to navigate to the Jobs page from header. Clicks on jobs tab
     * Selects a specific cluster Selects 90 days time interval Deselsects all the
     * selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(SubTopPanelModulePageObject topPanelObj, AllApps allApps,
                                            DatePicker datePicker, ApplicationsPageObject appPageObj, String clusterId) {
        logger.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
        userActions.performActionWithPolling(topPanelObj.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);
        userActions.performActionWithPolling(sparkPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);

        // Select cluster
        logger.info("Select Cluster: " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);
        datePicker.selectLast90Days();
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(3000);
    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left
     * pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver
                .findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we)
                .moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                        + "/span[contains(text(),'" + types + "')]/following-sibling::span[2]")))
                .click().build().perform();
        waitExecuter.waitUntilElementClickable(sparkPageObject.resetButton);
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                + "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        waitExecuter.sleep(3000);
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

    /**
     * Method to navigate to the failed apps details page.
     */
    public void navigateToFailedAppsAppPage(ApplicationsPageObject applicationsPageObject,
                                            SparkAppsDetailsPageObject sparkPageObj, ExtentTest test, Boolean verifyAppComp) {
        applicationsPageObject.expandStatus.click();
        int failedAppCnt = clickOnlyLink("Failed");
        logger.info("Failed App Cnt is " + failedAppCnt);
        if (failedAppCnt > 0) {
            verifyAppId(sparkPageObj, applicationsPageObject);
            List<WebElement> kpiList = sparkPageObj.leftPaneKPIList;
            validateLeftPaneKpis(kpiList);
            test.log(LogStatus.PASS,
                    "All the Kpis (start, end and duration are listed )are" + " displayed and are not empty: ");

            /**
             * There should be attempts tab under which attempts for "failed" and "success"
             * must be displayed in the form of bar graph for failed attempts.
             */
            String attempt = "";
            try {
                attempt = sparkPageObj.ifAttemptPresent.getText();
                List<WebElement> numAttemptsList = sparkPageObj.numAttempts;
                Assert.assertFalse(numAttemptsList.isEmpty(), "There are no attempts for failed apps");
                logger.info("Number of attempts for application is " + numAttemptsList.size());
                if (verifyAppComp) {
                    for (int a = 0; a < numAttemptsList.size(); a++) {
                        numAttemptsList.get(a).click();
                        verifyAppsComponent(sparkPageObj, false, false, false);
                    }
                }
            } catch (Throwable e) {
                // Close apps details page
                logger.info("Caught exception " + e.getMessage());
                sparkPageObj.closeAppsPageTab.click();
            }

        } else {
            logger.info("There are no failed apps to navigate to");
            List<WebElement> kpiList = sparkPageObj.leftPaneKPIList;
            validateLeftPaneKpis(kpiList);
            test.log(LogStatus.PASS,
                    "All the Kpis (start, end and duration are listed )are" + " displayed and are not empty: ");
        }
    }

    /***
     * Common actions listed in one method that does the following: Navigate to Jobs
     * tab from header Verify that the left pane has spark app Get Job count of
     * selected App click on it and go to apps details page Verify specific summary
     * tabs.
     */
    public void commonSetupCodeForSumarryTabValidation(ExtentTest test, String clusterId, String tabName, Logger logger,
                                                       Boolean isFailedApp) {
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkAppPageObj = new SparkAppsDetailsPageObject(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Verify that the left pane has spark check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        int totalSparkAppCnt = appsDetailsPage.clickOnlyLink("Spark");
        waitExecuter.sleep(2000);
        if (totalSparkAppCnt > 0) {
            waitExecuter.waitUntilElementClickable(applicationsPageObject.expandStatus);
            applicationsPageObject.expandStatus.click();
            int appCount = 0;
            if (isFailedApp)
                appCount = appsDetailsPage.clickOnlyLink("Failed");
            else
                appCount = appsDetailsPage.clickOnlyLink("Success");
            // Clicking on the Spark app must go to apps detail page
            if (appCount > 0) {
                if (tabName.equals("Analysis")) {
                    userActions.performActionWithPolling(appDetailsPageObject.globalSearchBox, UserAction.SEND_KEYS,
                        "Spark Pi");
                    appDetailsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                }
                String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj, applicationsPageObject);
                test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
                appsDetailsPage.verifyAppSummaryTabs(sparkAppPageObj, tabName, test);
                // Close apps details page

                waitExecuter.waitUntilElementClickable(sparkAppPageObj.closeAppsPageTab);
                MouseActions.clickOnElement(driver, sparkAppPageObj.closeAppsPageTab);
            } else {
                test.log(LogStatus.SKIP, "No Spark Application present");
                logger.info("No Spark Application present in the " + clusterId + " cluster for the time span "
                        + "of 90 days");
            }
        } else {
            test.log(LogStatus.SKIP, "No Spark Application present");
            logger.info(
                    "No Spark Application present in the " + clusterId + " cluster for the time span " + "of 90 days");
        }
    }

    /**
     * Method to validate the Load Diagnostic Action
     */
    public void verifyLoadDiagnosticAction(SparkAppsDetailsPageObject sparkAppPageObj) {
        WebElement actionMenu = sparkAppPageObj.loadAction;
        MouseActions.clickOnElement(driver, actionMenu);
        List<WebElement> elementList = sparkAppPageObj.loadActionList;
        verifyAssertFalse(elementList.isEmpty(), sparkAppPageObj,
                "No elements listed in the Action Menu Expected " + "(Load Diagnostic | Load Logs)");
        isDignosticWin = true;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().equals("Load Diagnostics")) {
                MouseActions.clickOnElement(driver, elementList.get(i));
                waitExecuter.sleep(1000);
                verifyAssertTrue(sparkAppPageObj.loadDiagnosticWin.isDisplayed(), sparkAppPageObj,
                        "The load diagnostic window is not displayed");
                String expectedStr = "APPLICATION DIAGNOSTICS";
                String winHeader = sparkAppPageObj.loadDiagnosticWinHeader.getText();
                logger.info("The LoadDignostics window header is " + winHeader);
                verifyAssertTrue(winHeader.contains(expectedStr), sparkAppPageObj, " Expected Header String is not "
                        + "displayed\n Expected = " + expectedStr + " Actual = " + winHeader);
                MouseActions.clickOnElement(driver, sparkAppPageObj.loadWinClose);
                MouseActions.clickOnElement(driver, actionMenu);
            }
        }
        isDignosticWin = false;
    }

    public void verifyAssertFalse(Boolean condition, SparkAppsDetailsPageObject sparkAppPageObj, String msg) {
        String appDuration = "0";
        try {
            // appDuration = verifyRightPaneKpis(sparkAppPageObj);
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, sparkAppPageObj.loadWinClose);
            else
                MouseActions.clickOnElement(driver, sparkAppPageObj.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    public void verifyAssertTrue(Boolean condition, SparkAppsDetailsPageObject sparkAppPageObj, String msg) {
        try {
            Assert.assertTrue(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin) {
                MouseActions.clickOnElement(driver, sparkAppPageObj.loadWinClose);
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, sparkAppPageObj.closeAppsPageTab);
            } else
                MouseActions.clickOnElement(driver, sparkAppPageObj.closeAppsPageTab);
            throw new AssertionError(msg);
        }
    }

    /* To zoom out of the page by defining percentage of zoom out */
    public void zoomOut(String percentage) {
        jse.executeScript("document.body.style.zoom = " + "'" + percentage + "%';");

    }
}
