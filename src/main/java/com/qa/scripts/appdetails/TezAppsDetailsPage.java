package com.qa.scripts.appdetails;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
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

public class TezAppsDetailsPage {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TezAppsDetailsPage.class.getName());
    private static Boolean isDignosticWin = false;
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private TezAppsDetailsPageObject tezApps;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public TezAppsDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateTaskAttemptTab(TezAppsDetailsPageObject tezApps) {
        List<WebElement> footerNameList = tezApps.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(),
                "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = tezApps.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " +
                "not displayed");
        String pValStr = tezApps.resourcesPieChartInternalVal.getText();
        String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
        int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
        LOGGER.info("The value displayed inside the Pie Chart is " +
                pieChartInternalVal);
        int totalTaskCnt = 0;
        for (int f = 0; f < footerNameList.size(); f++) {
            String footerName = footerNameList.get(f).getText();
            String footerValStr = footerValList.get(f).getText();
            int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]",
                    "").trim());
            totalTaskCnt += footerVal;
            LOGGER.info("FooterName = " + footerName + " Value = " + footerVal);
        }
        LOGGER.info("Total Task Attempts = " + totalTaskCnt + " pie chart val = " +
                pieChartInternalVal);
    }

    /**
     * Method to validate AppSummary Analysis tab.
     */
    public void validateAnalysisTab(TezAppsDetailsPageObject tezApps) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = tezApps.insightsType;
        verifyAssertFalse(insightType.isEmpty(), tezApps, "No Insights generated");
        for (int j = 0; j < insightType.size(); j++) {
            String insights = insightType.get(j).getText();
            LOGGER.info("Insight generated are " + insights);
            if (insights.equals("EFFICIENCY")) {
                // Store it in efficiency array
                efficiency.add(insights);
            } else {
                //Store it in recommendation array
                recommendation.add(insights);
            }
        }
        verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), tezApps, "No insights generated");
        List<WebElement> collapsableList = tezApps.analysisCollapse;
        try {
            for (int c = 0; c < collapsableList.size(); c++) {
                collapsableList.get(c).click();
            }
        } catch (Exception ex) {
            throw new AssertionError("Caught exception while clicking the collapsable" +
                    " icon for insights.\n" + ex.getMessage());
        }
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateResourcesTab(TezAppsDetailsPageObject tezApps) {
        String[] expectedGraphTitle = {"Task Attempts", "Containers", "Vcores", "Memory", "Metrics"};
        List<WebElement> graphTitleList = tezApps.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), tezApps, "No title displayed");
        List<WebElement> allGraphsList = tezApps.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), tezApps, "No graphs displayed");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);
            verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), tezApps, " The expected" +
                    " Graph title doesnot match with the titles in the UI");
            verifyAssertTrue(allGraphsList.get(t).isDisplayed(), tezApps, " All Graphs are not displayed");
            switch (graphTitle) {
                case "Task Attempts":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptTab(tezApps);
                    //Assert.assertSame(totalTaskCnt, pieChartInternalVal, "The Values are not same");
                    break;
                case "Containers":
                case "Metrics":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    WebElement metricDropDown = tezApps.resourcesMetricsDropDown;
                    MouseActions.clickOnElement(driver, metricDropDown);
                    List<WebElement> dropDownList = tezApps.resourcesMetricsDropDownData;
                    waitExecuter.sleep(2000);
                    verifyAssertFalse(dropDownList.isEmpty(), tezApps, " No contents listed in the dropdown");
                    String[] expectetContents = {"availableMemory", "vmRss", "systemCpuLoad",
                            "processCpuLoad", "gcLoad", "maxHeap", "usedHeap"};
                    for (int d = 0; d < dropDownList.size(); d++) {
                        String metric = dropDownList.get(d).getText();
                        LOGGER.info("The metric is " + metric);
                        verifyAssertTrue(Arrays.asList(expectetContents).contains(metric), tezApps, " The expected" +
                                " metric is not listed in the drop down box");
                        //click on the dropdown list element and validate the graph
                        MouseActions.clickOnElement(driver, dropDownList.get(d));
                        List<WebElement> resourcesMetricsPlotGraphList = tezApps.resourcesMetricsPlotGraph;
                        List<WebElement> metricLegendList = tezApps.resourcesMetricsPlotGraphLegend;
                        Assert.assertEquals(resourcesMetricsPlotGraphList.size(), metricLegendList.size(),
                                "The number of executors in the legend do not match to the ones plotted in the graph");
                        MouseActions.clickOnElement(driver, metricDropDown);
                    }
                case "Vcores":
                case "Memory":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    break;

            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), tezApps, " No graph is displayed for "
                    + graphTitle);
        }
    }

    /**
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(TezAppsDetailsPageObject tezApps) {
        String[] expectedErrorCategory = {"driver", "executor-1", "executor-2", "rm-diagnostics"};
        List<WebElement> errorTypeList = tezApps.errorCategories;
        verifyAssertFalse(errorTypeList.isEmpty(), tezApps, " Errors tab is not populated");
        for (int e = 0; e < errorTypeList.size(); e++) {
            String errorType = errorTypeList.get(e).getText();
            LOGGER.info("Error Type is " + errorType);
            verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(errorType), tezApps,
                    " The UI error types displayed does not match with the Expected error types ");
        }
        List<WebElement> errorCollapsableList = tezApps.errorCollapse;
        verifyAssertFalse(errorCollapsableList.isEmpty(), tezApps, " No collapsable icon present");
        List<WebElement> errorContentList = tezApps.errorContents;
        verifyAssertFalse(errorContentList.isEmpty(), tezApps, " No error contents in the error tab");
        //TODO check for specific error string in the content list.
        boolean foundErrorMsg = false;
        String msg = "";
        for (int c = 0; c < errorCollapsableList.size(); c++) {
            MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
        }
    }

    public void validateConfigurationTab(TezAppsDetailsPageObject tezApps) {
        String[] expectedKeyWords = {"METADATA", "MEMORY", "LIMIT", "RESOURCES", "CPU", "NET",
                "YARN", "DEPLOY"};
        List<WebElement> keyWordsList = tezApps.configKeywords;
        verifyAssertFalse(keyWordsList.isEmpty(), tezApps, " Keywords not found");
        String beforeResetProp = tezApps.configPropNum.getText();
        int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
        LOGGER.info("Number of properties displayed by default are  " + propNum);

        //Verify if property key value is present:
        List<WebElement> propKeyList = tezApps.configPropKey;
        List<WebElement> propValueList = tezApps.configPropValue;
        LOGGER.info("PropKey size = " + propKeyList.size() + " propVal size = " +
                propValueList.size());
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), tezApps, " Key/Value is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " +
                "is missing");

        //Verify the keywords are present and should be clickable
        for (int k = 0; k < keyWordsList.size(); k++) {
            String keyword = keyWordsList.get(k).getText();
            LOGGER.info("Keyword Type is " + keyword);
            verifyAssertTrue(Arrays.asList(expectedKeyWords).contains(keyword), tezApps,
                    " Keywords displayed on the UI doesnot match with the expected keywords");
            MouseActions.clickOnElement(driver, keyWordsList.get(k));
            waitExecuter.sleep(2000);
        }
        //Check RESET buttons sets default props
        MouseActions.clickOnElement(driver, tezApps.resetButton);
        waitExecuter.sleep(3000);
        String afterResetProp = tezApps.configPropNum.getText();
        LOGGER.info("No. of Properties displayed by default " + beforeResetProp + "\n " +
                "No. of Properties displayed after RESET " + afterResetProp);
        Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " +
                "to default");
    }

    public void validateLogsTab(TezAppsDetailsPageObject tezApps) {
        List<WebElement> executorNameList = tezApps.logExecutorNames;
        verifyAssertFalse(executorNameList.isEmpty(), tezApps, " No executors listed in the Logs Tab");
        List<WebElement> logCollapsableList = tezApps.logElementCollapsable;
        verifyAssertFalse(logCollapsableList.isEmpty(), tezApps, " No executors listed in the Logs Tab");
        try {
            int cnt = 0;
            for (int c = 0; c < logCollapsableList.size(); c++) {
                if (cnt != 0)
                    MouseActions.clickOnElement(driver, logCollapsableList.get(c));
                waitExecuter.sleep(5000);
                WebElement contents = tezApps.logExecutorContents;
                verifyAssertTrue(contents.isDisplayed(), tezApps, " No logs found");
                WebElement showFullLogs = tezApps.showFullLogLink;
                logCollapsableList.get(c).click();
                cnt += 1;
            }
        } catch (NoSuchElementException ex) {
            throw new AssertionError("Caught exception while clicking the collapsable icon for insights.\n" +
                    ex.getMessage());
        }
    }

    public String validateTagsTab(TezAppsDetailsPageObject tezApps) {
        List<WebElement> tagTableHeader = tezApps.tagTableHeader;
        verifyAssertFalse(tagTableHeader.isEmpty(), tezApps, " Tags header is not populated");
        List<WebElement> tagKeyList = tezApps.tagKey;
        List<WebElement> tagValueList = tezApps.tagValue;
        verifyAssertFalse((tagKeyList.isEmpty() || tagValueList.isEmpty()), tezApps, "The tags key value pair are empty");
        String tagValue = "";
        for (int k = 0; k < tagKeyList.size(); k++) {
            String key = tagKeyList.get(k).getText();
            String value = tagValueList.get(k).getText();
            LOGGER.info("The key value pair is " + "Key = " + key + " | Value = " + value);
            verifyAssertTrue(key.length() > 0, tezApps, "Key is not present");
            verifyAssertTrue(value.length() > 0, tezApps, "Key is not present");
            if (key.equals("JobType"))
                tagValue = value;
        }
        return tagValue;
    }

    public void validateTimingTab(TezAppsDetailsPageObject tezApps) {
        Actions action = new Actions(driver);
        List<WebElement> subTabList = tezApps.timingsSubTabs;
        Assert.assertFalse(subTabList.isEmpty(), "No sub tabs available");
        String[] expectedSubTabList = {"Task Time", "App Time"};
        String[] expectedTTLegendNames = {"Input Stages", "Output Stages", "Processing Stages"};
        String[] expectedATLegendNames = {"Queue Time", "Driver Time", "Job Time"};
        for (int t = 0; t < subTabList.size(); t++) {
            String subTabName = subTabList.get(t).getText();
            LOGGER.info("The Timings subTab is " + subTabName);
            verifyAssertTrue(Arrays.asList(expectedSubTabList).contains(subTabName), tezApps,
                    " Tab list displayed in the UI doesnot match with the expected list of tabs");
            MouseActions.clickOnElement(driver, subTabList.get(t));
            waitExecuter.sleep(1000);
            String titleName = tezApps.timingsTabTitle.getText();
            LOGGER.info("The Timings subTabs Title is " + titleName);
            verifyAssertTrue(tezApps.pieChart.isDisplayed(), tezApps, " Piechart for Task Attempts" +
                    " is not displayed");
            if (subTabName.equals("Task Time")) {
                List<WebElement> legendNameList = tezApps.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), tezApps, "Empty legend list");
                for (int l = 0; l < legendNameList.size(); l++) {
                    String legendName = legendNameList.get(l).getText();
                    LOGGER.info("The Legends for subTab " + subTabName + " is " + legendName);
                    verifyAssertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName), tezApps,
                            " The legends displayed in the UI for Task Time doesnot match to the expected list of legends");
                    waitExecuter.sleep(1000);
                    if (legendName.equals("Processing Stages")) {
                        WebElement ele = tezApps.processingStage;
                        verifyTimingStages(action, ele, tezApps, legendName);
                    } else if (legendName.equals("Input Stages")) {
                        WebElement ele = tezApps.inputStage;
                        verifyTimingStages(action, ele, tezApps, legendName);
                    } else {
                        WebElement ele = tezApps.outputStage;
                        verifyTimingStages(action, ele, tezApps, legendName);
                    }
                }
            } else {
                List<WebElement> legendNameList = tezApps.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), tezApps, "Empty legend list for APP TIME");
                for (int l = 0; l < legendNameList.size(); l++) {
                    waitExecuter.sleep(1000);
                    String legendName = legendNameList.get(l).getText();
                    verifyAssertTrue(Arrays.asList(expectedATLegendNames).contains(legendName), tezApps,
                            " The legends displayed in the UI for App Time doesnot match to the expected list of legends");
                    //waitExecuter.sleep(2000);
                    if (legendName.equals("Driver Time")) {
                        WebElement ele = tezApps.driverDrillDown;
                        MouseActions.clickOnElement(driver, ele);
                        String[] expectedDriverLegends = {"FileCommit Time", "File Setup Time", "Others"};
                        List<WebElement> driverLegendNameList = tezApps.legendNames;
                        verifyAssertFalse(driverLegendNameList.isEmpty(), tezApps, "Empty legend list for DRIVER TIME");
                        for (int d = 0; d < driverLegendNameList.size(); d++) {
                            String driverLegend = driverLegendNameList.get(d).getText();
                            verifyAssertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend), tezApps,
                                    " The legends displayed in the UI for App Time does not match to the expected list of legends");
                        }
                        driverLegendNameList.clear();
                    }
                }
            }
        }
    }

    /**
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAppSummaryTabs(TezAppsDetailsPageObject tezApps, String verifyTabName, ExtentTest test) {
        List<WebElement> appsTabList = tezApps.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), tezApps, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));

        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            LOGGER.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        validateAnalysisTab(tezApps);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateResourcesTab(tezApps);
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateErrorsTab(tezApps);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Configuratio...":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateConfigurationTab(tezApps);
                        break;
                    case "Logs":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateLogsTab(tezApps);
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(tezApps);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                    //  break;
                    case "Program":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        List<WebElement> programDataList = tezApps.programTabData;
                        verifyAssertFalse(programDataList.isEmpty(), tezApps, tezApps.programDataNotFound.getText());
                        break;
                    case "Timings":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateTimingTab(tezApps);
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to verify the Timings tabs stages ,legends, graphs and top stages
     */
    public void verifyTimingStages(Actions action, WebElement ele, TezAppsDetailsPageObject tezApps, String legendName) {
        try {
            MouseActions.clickOnElement(driver, ele);
            waitExecuter.sleep(1000);
            List<WebElement> stageLegends = tezApps.legendNames;
            verifyAssertFalse(stageLegends.isEmpty(), tezApps, "The list is empty for " + legendName);
            List<WebElement> graphList = tezApps.timingGraphList;
            verifyAssertFalse(graphList.isEmpty(), tezApps, "No graphs displayed");
            for (int g = 0; g < graphList.size(); g++)
                verifyAssertTrue(graphList.get(g).isDisplayed(), tezApps, "The graph is not displayed");
            List<WebElement> topStagesList = tezApps.topStages;
            verifyAssertFalse(topStagesList.isEmpty(), tezApps, "Top stages are not displayed");
            WebElement backButton = tezApps.backButton;
            action.moveToElement(backButton).click().build().perform();
            waitExecuter.sleep(1000);
        } catch (NoSuchElementException | JavascriptException ex) {
            System.out.println("The element is not present for stage " + legendName);
        }
    }

    public void verifyStreamingAppsComponent(TezAppsDetailsPageObject tezApps, DatePicker datePicker) {
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        WebElement lineChart = tezApps.streamingLineChart;
        WebElement waveChart = tezApps.streamingWaveChart;
        verifyAssertTrue(lineChart.isDisplayed(), tezApps, "Line chart is not displayed");
        verifyAssertTrue(waveChart.isDisplayed(), tezApps, "Wave chart is not displayed");
        String completedBatchNum = tezApps.completedBatchTitle.getText().split(":")[1];
        int completedBatches = Integer.parseInt(completedBatchNum.replaceAll("[^\\dA-Za-z ]", "").trim());
        verifyAssertTrue(completedBatches > 0, tezApps, "There are no completed batches count displayed in the title");

        //Verify tables have data in it
        List<WebElement> tableContentList = tezApps.streamTableRows;
        verifyAssertFalse(tableContentList.isEmpty(), tezApps, "The spark streaming table is empty.");
    }

    /**
     * Method to verify if the component tabs like navigation | Gantt chart | jobs is present
     * and contains data if job count is > 0
     * if validateCompData = true validate each component tab data
     * if validateExecutorTab = true validate jobs execution tabs data.
     */
    public void verifyAppsComponent(TezAppsDetailsPageObject tezApps, Boolean validateCompData,
                                    Boolean validateExecutorTab, Boolean validateStageTab) {
        List<WebElement> componentList = tezApps.component_element;
        LOGGER.info("ComponentList is " + componentList.size());
        int navigationRows = 0;
        String tabName = "";
        for (int j = 0; j < componentList.size(); j++) {
            if (j != 3)
                tabName = componentList.get(j).getText();
            switch (j) {
                case 0:
                    Assert.assertEquals(tabName, "Navigation", "Navigation tab not present");
                    List<WebElement> navigationRowList = tezApps.navigationTableRows;
                    navigationRows = navigationRowList.size();
                    LOGGER.info("Navigation Rows are " + navigationRows);
                    if (validateCompData) {
                        List<WebElement> headerList = tezApps.navigationHeaders;
                        Assert.assertFalse(headerList.isEmpty(), "No headers for Navigation table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            LOGGER.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (navigationRows > 0) {
                            for (int rows = 1; rows <= navigationRows; rows++) {
                                for (int col = 1; col <= headerList.size(); col++) {
                                    String data = driver.findElement(By.xpath("//*[@id='appNavigation-body']/" +
                                            "tr[" + rows + "]/td[" + col + "]/span")).getText();
                                    LOGGER.info("The data is " + data);
                                    Assert.assertNotSame("", data);
                                }
                            }
                        }
                    }
                    validateStageAndStageData(navigationRows, navigationRowList, tezApps, validateExecutorTab, validateStageTab);
                    break;
                case 1:
                    //The component is Gantt Chart ,click it and then verify the no. rows in the table
                    Assert.assertEquals(tabName, "Gantt Chart", "Gantt Chart tab not present");
                    MouseActions.clickOnElement(driver, componentList.get(j));
                    List<WebElement> ganttChartTableRows = tezApps.ganttChartTable;
                    LOGGER.info("No. of rows in Gantt Chart tables are " + ganttChartTableRows.size());
                    if (validateCompData) {
                        List<WebElement> headerList = tezApps.ganttChartHeaders;
                        verifyAssertFalse(headerList.isEmpty(), tezApps, " No headers for Gantt Chart table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            LOGGER.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (ganttChartTableRows.size() > 0) {
                            for (int rows = 0; rows < ganttChartTableRows.size(); rows++) {
                                String jobId = tezApps.ganttChartJobId.get(rows).getText();
                                String startTime = tezApps.ganttChartStartTime.get(rows).getText();
                                String duration = tezApps.ganttChartDuration.get(rows).getText();
                                LOGGER.info("Duration = " + duration + " JobId = " + jobId + " starttime = " + startTime);
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
                    LOGGER.info("JobCount is " + jobCnt);
                    break;
            }
        }
    }

    /**
     * Method to validate the stage table header and the data.
     * if validateExecutorTab = true, validate each jobs execution tabs data
     */
    public void validateStageAndStageData(int navigationRows, List<WebElement> navigationRowList,
                                          TezAppsDetailsPageObject tezApps, Boolean validateExecutorTab,
                                          Boolean validateStageTabs) {
        if (navigationRows > 0) {
            String[] expectedHeader = {"Stage ID", "Start Time", "Duration", "Tasks", "Shuffle Read",
                    "Shuffle Write", "Input", "Output"};
            //click the jobId to sort it .
            MouseActions.clickOnElement(driver, tezApps.singleJobHeader);
            for (int rows = 0; rows < navigationRows; rows++) {
                navigationRowList.get(rows).click();
                waitExecuter.sleep(2000);
                if (validateExecutorTab) {
                    verifyAssertTrue(tezApps.DAGData.isDisplayed(), tezApps, " Dag data is not displayed ");
                    LOGGER.info("Dags data is displayed ");
                    List<WebElement> rddBlockList = tezApps.rddBlockList;
                    verifyAssertFalse(rddBlockList.isEmpty(), tezApps, "No DAGs data");
                    LOGGER.info("No. of RDD blocks in the flow chart is " + rddBlockList.size());
                    for (int i = 0; i < rddBlockList.size(); i++) {
                        verifyAssertTrue(rddBlockList.get(i).isDisplayed(), tezApps, "FlowChart doesnot have the RDD blocks displayed");
                    }
                    List<WebElement> rddNumberList = tezApps.rddBlockNumList;
                    verifyAssertFalse(rddNumberList.isEmpty(), tezApps, "Rdd block numbers are present");
                }
                List<WebElement> stageHeaderList = tezApps.stageHeaders;
                for (int i = 0; i < stageHeaderList.size(); i++) {
                    Assert.assertNotSame("", stageHeaderList.get(i).getText());
                    Assert.assertEquals(expectedHeader[i], stageHeaderList.get(i).getText(),
                            "expected stage header do not match to the one in the UI");
                }
                if (validateStageTabs)
                    validateStagesTabs(tezApps);
            }
        }
    }

    /**
     * Method to validate the tabs for each job stage.
     */
    public void validateStagesTabs(TezAppsDetailsPageObject tezApps) {
        //click the stageId to sort it
        driver.findElement(By.xpath("//*[@id='sparkStageNavigation-head']/tr/th[1]")).click();
        List<WebElement> stageRowList = tezApps.stageRows;
        verifyAssertFalse(stageRowList.isEmpty(), tezApps, " No stages displayed for JobId");
        for (int i = 0; i < stageRowList.size(); i++) {
            stageRowList.get(i).click();
            waitExecuter.sleep(1000);
            List<WebElement> stageTabsList = tezApps.stagesTab;
            verifyAssertFalse(stageTabsList.isEmpty(), tezApps, " No tabs displayed for selected stage");
            for (int t = 0; t < stageTabsList.size(); t++) {
                String tabName = stageTabsList.get(t).getText();
                switch (tabName) {
                    case "Taskattempt":
                        LOGGER.info("Validating the stage tab Taskattempt");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        validateTaskAttemptTab(tezApps);
                        break;
                    case "Program":
                        LOGGER.info("Validating the stage tab Program");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        try {
                            List<WebElement> programDataList = tezApps.programTabData;
                            verifyAssertFalse(programDataList.isEmpty(), tezApps, " Program data not found");
                            WebElement sourceText = tezApps.programSourceLinkText;
                            String sourceStr = sourceText.getText();
                            int lineNo = Integer.parseInt(sourceStr.split(":")[2].trim());
                            LOGGER.info("LineNo to verify in the Programs tab is " + lineNo);
                            MouseActions.clickOnElement(driver, tezApps.programSourceLink);
                            List<WebElement> programList = tezApps.programTabData;
                            verifyAssertFalse(programList.isEmpty(), tezApps, tezApps.programDataNotFound.getText());
                            WebElement highlightedLine = driver.findElement(By.xpath("//*[@id=\"app-query\"]" +
                                    "/div[contains(@data-range,'" + lineNo + "')]"));
                            verifyAssertTrue(highlightedLine.isDisplayed(), tezApps, " The line no from the stage source doesnot " +
                                    "point to the same line in the source program file");
                        } catch (NoSuchElementException ex) {
                            throw new AssertionError("Programs tab got exception " + ex.getMessage());
                        }
                        break;
                    case "Timeline":
                        LOGGER.info("Validating the stage tab Timeline");
                        MouseActions.clickOnElement(driver, stageTabsList.get(i));
                        waitExecuter.sleep(1000);
                        String[] expectedHeaderList = {"ShuffleMap Input (KB)", "Shuffle Map Time(sec)", "ShuffleMap Output (KB)"
                                , "Disk Bytes Spilled (KB)", "Memory Bytes Spilled (KB)", "Records Read (count)"};
                        String[] expectedSubTabs = {"Timeline", "Selected Tass"};
                        List<WebElement> headerlist = tezApps.stagesTimelineHeader;
                        verifyAssertFalse(headerlist.isEmpty(), tezApps, " No header displayed");
                        List<WebElement> barGraphList = tezApps.timelineBarGraph;
                        verifyAssertFalse(barGraphList.isEmpty(), tezApps, " No bar graphs displayed");
                        for (int h = 0; h < headerlist.size(); h++) {
                            String headerName = headerlist.get(h).getText();
                            LOGGER.info("The header name is " + headerName);
                            verifyAssertTrue(Arrays.asList(expectedHeaderList).contains(headerName), tezApps,
                                    "Header names displayed on the UI does not match with the expected headerList");
                            verifyAssertTrue(barGraphList.get(h).isDisplayed(), tezApps, " The bar graph for " +
                                    "" + headerName + " is not displayed");
                        }
                        List<WebElement> subTabList = tezApps.stagesTimelineSubTab;
                        Assert.assertFalse(subTabList.isEmpty(), "No sub tab displayed for Timeline tab");
                        for (int s = 0; s < subTabList.size(); s++) {
                            String subTask = subTabList.get(s).getText();
                            LOGGER.info("The subTask is " + subTask);
                            Assert.assertTrue(Arrays.asList(expectedSubTabs).contains(subTask),
                                    "Subtask names displayed on the UI does not match with the expected list");
                        }
                        break;
                    case "Timings":
                        LOGGER.info("Validating the stage tab Timings");
                        MouseActions.clickOnElement(driver, stageTabsList.get(i));
                        waitExecuter.sleep(1000);
                        List<WebElement> stageTimingHeaderList = tezApps.stageTimingHeaders;
                        Assert.assertFalse(stageTimingHeaderList.isEmpty(), "The headers in the timmings " +
                                "tab not displayed");
                        String[] expectedTimingHeaders = {"Stage Time Distribution", "IO Metrics",
                                "Time Metrics"};
                        List<WebElement> legendList = tezApps.legendNames;
                        Assert.assertFalse(legendList.isEmpty(), "No legends displayed");
                        List<WebElement> graphList = tezApps.timingGraphList;
                        Assert.assertFalse(graphList.isEmpty(), "No graphs displayed");

                        for (int s = 0; s < stageTimingHeaderList.size(); s++) {
                            String stageTimingHeader = stageTimingHeaderList.get(s).getText();
                            LOGGER.info("The stageTimingHeader is " + stageTimingHeader);
                            Assert.assertTrue(Arrays.asList(expectedTimingHeaders).contains(stageTimingHeader),
                                    "Stage Timing header names displayed on the UI does not match with " +
                                            "the expected list");
                        }
                        break;
                }
            }
        }
    }

    /**
     * Method to verify the following:
     * 1.All the KPIs should be listed and the data must be populated.
     * (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis(TezAppsDetailsPageObject tezApps) {
        List<WebElement> kpiList = tezApps.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = tezApps.rightPaneAppKpis;
        List<WebElement> appKpiVal = tezApps.rightPaneAppKpiVal;
        Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
        Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
        String appDuration = "0";
        for (int i = 0; i < appKpis.size(); i++) {
            Assert.assertNotSame("", appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotSame("", appKpiVal.get(i).getText(), "Kpi Value is empty");
            appDuration = (appKpiVal.get(0).getText().trim());
            LOGGER.info("Kpi Name = " + appKpis.get(i).getText() + " Value = " + appKpiVal.get(i).getText());
        }
        LOGGER.info("The application duration is " + appDuration);
        return appDuration;
    }

    /**
     * Verify that Left pane must be opened and should have KPIs listed
     * (start, end and duration are listed and should not be empty)
     */
    public void validateLeftPaneKpis(List<WebElement> kpiList) {
        Assert.assertFalse(kpiList.isEmpty(), "The kpi list is empty");
        for (WebElement webElement : kpiList) {
            LOGGER.info("The leftPane kpi is " + webElement.getText());
            String kpis = webElement.getText();
            Assert.assertNotSame("", kpis, "The kpis is empty");
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            LOGGER.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppId(TezAppsDetailsPageObject tezApps, ApplicationsPageObject appPageObj) {
        String appId = tezApps.getAppId.getText().trim();
        LOGGER.info("Spark application Id is " + appId);
        appPageObj.getTypeFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = tezApps.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Tez Application Id is not displayed in the Header");
        return headerAppId;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyStatus(TezAppsDetailsPageObject tezApps) {
        String Status = tezApps.Bystatus.getText();
        LOGGER.info("Spark application Id is " + Status);
        Assert.assertNotSame("", Status, "Tez Application Id is not displayed in the Header");
        return Status;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifyusername(TezAppsDetailsPageObject tezApps) {
        String typeValue = tezApps.getUsername.getText();
        LOGGER.info("Tez Status is " + typeValue);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typeValue, "Tez User name is not displayed in the Table");
        return typeValue;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appname .
     */
    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appname .
     */
    public String verifyAppname(TezAppsDetailsPageObject tezApps) {
        WebElement Appname =tezApps.getAppname;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appname).build().perform();
        WebElement AppnametoolTip = driver.findElement(By.xpath("//*[@id=\"allApps-body\"]/tr[1]/td[4]"));
        waitExecuter.sleep(3000);
        String AppnameText = AppnametoolTip.getText().trim();
        System.out.println(AppnameText);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appname, "Tez App name is not displayed in the Table");
        return AppnameText;
    }
    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appid .
     */
    public String verifyappId(TezAppsDetailsPageObject tezApps) {
        WebElement Appid =tezApps.getAppid;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppnametoolTip = driver.findElement(By.xpath("//*[@id=\"allApps-body\"]/tr[1]/td[4]/div"));
        waitExecuter.sleep(3000);
        String AppIdText = AppnametoolTip.getText().trim();
        LOGGER.info("Tez Status is " + Appid);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appid, "Tez App id name is not displayed in the Table");
        return AppIdText;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appid .
     */
    public String verifyclusterId(TezAppsDetailsPageObject tezApps) {
        WebElement Appid =tezApps.getClusterId;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppnametoolTip = tezApps.getClusterId;
        waitExecuter.sleep(3000);
        String AppIdText = AppnametoolTip.getText().trim();
        LOGGER.info("Tez Status is " + Appid);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appid, "Tez App id name is not displayed in the Table");
        return AppIdText;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifystarttime(TezAppsDetailsPageObject tezApps) {
        String typetarttime = tezApps.getstartTime.getText();
        LOGGER.info("Tez Status is " + typetarttime);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typetarttime, "Tez User name is not displayed in the Table");
        return typetarttime;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifyduration(TezAppsDetailsPageObject tezApps) {
        String typetarttime = tezApps.getduration.getText();
        LOGGER.info("Tez Status is " + typetarttime);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typetarttime, "Tez User name is not displayed in the Table");
        return typetarttime;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifyRead(TezAppsDetailsPageObject tezApps) {
        String ReadIO = tezApps.getRead.getText();
        LOGGER.info("Tez Status is " + ReadIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", ReadIO, "Tez User name is not displayed in the Table");
        return ReadIO;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifyWrite(TezAppsDetailsPageObject tezApps) {
        String WriteIO = tezApps.getWrite.getText();
        LOGGER.info("Tez Status is " + WriteIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", WriteIO, "Tez User name is not displayed in the Table");
        return WriteIO;
    }
    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyClusterId(TezAppsDetailsPageObject tezApps, ApplicationsPageObject appPageObj) {
        String appId = tezApps.getClusterId.getText();
        LOGGER.info("Tez application Id is " + appId);
        appPageObj.getTypeFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = tezApps.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Spark Application Id is not displayed in the Header");
        return headerAppId;
    }

    /**
     * Common steps to navigate to the Jobs page from header.
     * Clicks on jobs tab
     * Selects a specific cluster
     * Selects 90 days time interval
     * Deselsects all the selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(TopPanelComponentPageObject topPanelObj, AllApps allApps,
                                            DatePicker datePicker, ApplicationsPageObject appPageObj, String clusterId) {
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
        waitExecuter.sleep(1000);
        topPanelObj.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);

        //Select cluster
        LOGGER.info("Select Cluster: " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[2]"))).click().build().perform();
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]",
                "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

    /**
     * Method to navigate to the failed apps details page.
     */
    public void navigateToFailedAppsAppPage(ApplicationsPageObject applicationsPageObject,
                                            TezAppsDetailsPageObject tezApps, ExtentTest test,
                                            Boolean verifyAppComp) {
        applicationsPageObject.expandStatus.click();
        int failedAppCnt = clickOnlyLink("Failed");
        LOGGER.info("Failed App Cnt is " + failedAppCnt);
        if (failedAppCnt > 0) {
            verifyAppId(tezApps, applicationsPageObject);
            List<WebElement> kpiList = tezApps.leftPaneKPIList;
            validateLeftPaneKpis(kpiList);
            test.log(LogStatus.PASS, "All the Kpis (start, end and duration are listed )are" +
                    " displayed and are not empty: ");

            /**There should be attempts tab under which attempts  for "failed"
             *    and "success" must be displayed in the form of bar graph for failed attempts.
             */
            String attempt = "";
            try {
                attempt = tezApps.ifAttemptPresent.getText();
                List<WebElement> numAttemptsList = tezApps.numAttempts;
                Assert.assertFalse(numAttemptsList.isEmpty(), "There are no attempts for failed apps");
                LOGGER.info("Number of attempts for application is " + numAttemptsList.size());
                if (verifyAppComp) {
                    for (int a = 0; a < numAttemptsList.size(); a++) {
                        numAttemptsList.get(a).click();
                        verifyAppsComponent(tezApps, false, false, false);
                    }
                }
            } catch (Throwable e) {
                //Close apps details page
                LOGGER.info("Caught exception " + e.getMessage());
                tezApps.closeAppsPageTab.click();
            }

        } else {
            LOGGER.info("There are no failed apps to navigate to");
            List<WebElement> kpiList = tezApps.leftPaneKPIList;
            validateLeftPaneKpis(kpiList);
            test.log(LogStatus.PASS, "All the Kpis (start, end and duration are listed )are" +
                    " displayed and are not empty: ");
        }
    }

    /***
     * Common actions listed in one method that does the following:
     * Navigate to Jobs tab from header
     * Verify that the left pane has spark app
     * Get Job count of selected App click on it and go to apps details page
     * Verify specific summary tabs.
     * */
    public void commonSetupCodeForSumarryTabValidation(ExtentTest test, String clusterId, String tabName, Logger logger) {
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        logger.info("Initialize all class objects");
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkAppPageObj = new SparkAppsDetailsPageObject(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has spark check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        appsDetailsPage.clickOnlyLink("Tez");
        applicationsPageObject.expandStatus.click();
        int appCount = appsDetailsPage.clickOnlyLink("Success");
        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj, applicationsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            appsDetailsPage.verifyAppSummaryTabs(sparkAppPageObj, tabName, test);
            //Close apps details page
            MouseActions.clickOnElement(driver, sparkAppPageObj.closeAppsPageTab);
        } else {
            test.log(LogStatus.SKIP, "No Spark Application present");
            logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }

    /**
     * Method to validate the Load Diagnostic Action
     */
    public void verifyLoadDiagnosticAction(TezAppsDetailsPageObject tezApps) {
        WebElement actionMenu = tezApps.loadAction;
        MouseActions.clickOnElement(driver, actionMenu);
        List<WebElement> elementList = tezApps.loadActionList;
        verifyAssertFalse(elementList.isEmpty(), tezApps, "No elements listed in the Action Menu Expected " +
                "(Load Diagnostic | Load Logs)");
        isDignosticWin = true;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().equals("Load Diagnostics")) {
                MouseActions.clickOnElement(driver, elementList.get(i));
                waitExecuter.sleep(1000);
                verifyAssertTrue(tezApps.loadDiagnosticWin.isDisplayed(), tezApps,
                        "The load diagnostic window is not displayed");
                String expectedStr = "APPLICATION DIAGNOSTICS";
                String winHeader = tezApps.loadDiagnosticWinHeader.getText();
                LOGGER.info("The LoadDignostics window header is " + winHeader);
                verifyAssertTrue(winHeader.contains(expectedStr), tezApps, " Expected Header String is not " +
                        "displayed\n Expected = " + expectedStr + " Actual = " + winHeader);
                MouseActions.clickOnElement(driver, tezApps.loadDiagnosticWinClose);
                MouseActions.clickOnElement(driver, actionMenu);
            }
        }
        isDignosticWin = false;
    }

    public void verifyAssertFalse(Boolean condition, TezAppsDetailsPageObject tezApps, String msg) {
        String appDuration = "0";
        try {
            appDuration = verifyRightPaneKpis(tezApps);
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            //Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, tezApps.loadDiagnosticWinClose);
            else
                MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    public void verifyAssertTrue(Boolean condition, TezAppsDetailsPageObject tezApps, String msg) {
        try {
            Assert.assertTrue(condition, msg);
        } catch (Throwable e) {
            //Close apps details page
            if (isDignosticWin) {
                MouseActions.clickOnElement(driver, tezApps.loadDiagnosticWinClose);
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            } else
                MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }


}