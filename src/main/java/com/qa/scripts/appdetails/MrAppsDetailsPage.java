package com.qa.scripts.appdetails;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
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
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MrAppsDetailsPage {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TezAppsDetailsPage.class.getName());
    private static Boolean isDignosticWin = false;
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private MrAppsDetailsPageObject mrApps;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public MrAppsDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
    }

    /**
     * Method to validate the tasks attempt tab in Resources and stages tab.
     */
    public void validateTaskAttemptTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> footerNameList = mrApps.taskAttFooterName;
        Assert.assertFalse(footerNameList.isEmpty(),
                "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = mrApps.taskAttFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " +
                "not displayed");
        String pValStr = mrApps.resourcesPieChartInternalVal.getText();
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
    public void validateAnalysisTab(MrAppsDetailsPageObject mrApps) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = mrApps.insightsType;
        verifyAssertFalse(insightType.isEmpty(), mrApps, "No Insights generated");
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
        verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), mrApps, "No insights generated");
        List<WebElement> collapsableList = mrApps.analysisCollapse;
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
    public void validateResourcesTab(MrAppsDetailsPageObject mrApps) {
        String[] expectedGraphTitle = {"Containers", "Vcores", "Memory", "Metrics"};
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), mrApps, "No title displayed");
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), mrApps, "No graphs displayed");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);
            verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), mrApps, " The expected" +
                    " Graph title doesnot match with the titles in the UI");
            verifyAssertTrue(allGraphsList.get(t).isDisplayed(), mrApps, " All Graphs are not displayed");
            switch (graphTitle) {
                case "Task Attempts":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptTab(mrApps);
                    break;
                case "Containers":
                case "Metrics":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    WebElement metricDropDown = mrApps.resourcesMetricsDropDown;
                    MouseActions.clickOnElement(driver, metricDropDown);
                    List<WebElement> dropDownList = mrApps.resourcesMetricsDropDownData;
                    waitExecuter.sleep(2000);
                    verifyAssertFalse(dropDownList.isEmpty(), mrApps, " No contents listed in the dropdown");
                    String[] expectetContents = {"availableMemory", "vmRss", "systemCpuLoad",
                            "processCpuLoad", "gcLoad", "maxHeap", "usedHeap"};
                    for (int d = 0; d < dropDownList.size(); d++) {
                        String metric = dropDownList.get(d).getText();
                        LOGGER.info("The metric is " + metric);
                        verifyAssertTrue(Arrays.asList(expectetContents).contains(metric), mrApps, " The expected" +
                                " metric is not listed in the drop down box");
                        //click on the dropdown list element and validate the graph
                        MouseActions.clickOnElement(driver, dropDownList.get(d));
                        List<WebElement> resourcesMetricsPlotGraphList = mrApps.resourcesMetricsPlotGraph;
                        List<WebElement> metricLegendList = mrApps.resourcesMetricsPlotGraphLegend;
                        Assert.assertEquals(resourcesMetricsPlotGraphList.size(), metricLegendList.size(),
                                "The number of executors in the legend do not match to the ones plotted in the graph");
                        MouseActions.clickOnElement(driver, metricDropDown);
                    }
                case "Vcores":
                case "Memory":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    break;

            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                    + graphTitle);
        }
    }

    /**
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(MrAppsDetailsPageObject mrApps) {
        String[] expectedErrorCategory = {"driver", "executor-1", "executor-2", "rm-diagnostics"};
        List<WebElement> errorTypeList = mrApps.errorCategories;
        verifyAssertFalse(errorTypeList.isEmpty(), mrApps, " Errors tab is not populated");
        for (int e = 0; e < errorTypeList.size(); e++) {
            String errorType = errorTypeList.get(e).getText();
            LOGGER.info("Error Type is " + errorType);
            verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(errorType), mrApps,
                    " The UI error types displayed does not match with the Expected error types ");
        }
        List<WebElement> errorCollapsableList = mrApps.errorCollapse;
        verifyAssertFalse(errorCollapsableList.isEmpty(), mrApps, " No collapsable icon present");
        List<WebElement> errorContentList = mrApps.errorContents;
        verifyAssertFalse(errorContentList.isEmpty(), mrApps, " No error contents in the error tab");
        //TODO check for specific error string in the content list.
        boolean foundErrorMsg = false;
        String msg = "";
        for (int c = 0; c < errorCollapsableList.size(); c++) {
            MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
        }
    }

    /**
     * Method to validate AppSummary Program tab.
     */
    public void validateProgramTab(MrAppsDetailsPageObject mrApps) {
        //TBD data not populated in UI.
    }


    public void validateLogsTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> executorNameList = mrApps.logExecutorNames;
        verifyAssertFalse(executorNameList.isEmpty(), mrApps, " No executors listed in the Logs Tab");
        List<WebElement> logCollapsableList = mrApps.logElementCollapsable;
        verifyAssertFalse(logCollapsableList.isEmpty(), mrApps, " No executors listed in the Logs Tab");
        try {
            int cnt = 0;
            for (int c = 0; c < logCollapsableList.size(); c++) {
                if (cnt != 0)
                    MouseActions.clickOnElement(driver, logCollapsableList.get(c));
                waitExecuter.sleep(5000);
                WebElement contents = mrApps.logExecutorContents;
                verifyAssertTrue(contents.isDisplayed(), mrApps, " No logs found");
                WebElement showFullLogs = mrApps.showFullLogLink;
                logCollapsableList.get(c).click();
                cnt += 1;
            }
        } catch (NoSuchElementException ex) {
            throw new AssertionError("Caught exception while clicking the collapsable icon for insights.\n" +
                    ex.getMessage());
        }
    }

    public String validateTagsTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> tagTableHeader = mrApps.tagTableHeader;
        verifyAssertFalse(tagTableHeader.isEmpty(), mrApps, " Tags header is not populated");
        List<WebElement> tagKeyList = mrApps.tagKey;
        List<WebElement> tagValueList = mrApps.tagValue;
        verifyAssertFalse((tagKeyList.isEmpty() || tagValueList.isEmpty()), mrApps, "The tags key value pair are empty");
        String tagValue = "";
        for (int k = 0; k < tagKeyList.size(); k++) {
            String key = tagKeyList.get(k).getText();
            String value = tagValueList.get(k).getText();
            LOGGER.info("The key value pair is " + "Key = " + key + " | Value = " + value);
            verifyAssertTrue(key.length() > 0, mrApps, "Key is not present");
            verifyAssertTrue(value.length() > 0, mrApps, "Key is not present");
            if (key.equals("JobType"))
                tagValue = value;
        }
        return tagValue;
    }

    public void validateTimingTab(MrAppsDetailsPageObject mrApps) {
        Actions action = new Actions(driver);
        List<WebElement> subTabList = mrApps.timingsSubTabs;
        Assert.assertFalse(subTabList.isEmpty(), "No sub tabs available");
        String[] expectedSubTabList = {"Task Time", "App Time"};
        String[] expectedTTLegendNames = {"Input Stages", "Output Stages", "Processing Stages"};
        String[] expectedATLegendNames = {"Queue Time", "Driver Time", "Job Time"};
        for (int t = 0; t < subTabList.size(); t++) {
            String subTabName = subTabList.get(t).getText();
            LOGGER.info("The Timings subTab is " + subTabName);
            verifyAssertTrue(Arrays.asList(expectedSubTabList).contains(subTabName), mrApps,
                    " Tab list displayed in the UI doesnot match with the expected list of tabs");
            MouseActions.clickOnElement(driver, subTabList.get(t));
            waitExecuter.sleep(1000);
            String titleName = mrApps.timingsTabTitle.getText();
            LOGGER.info("The Timings subTabs Title is " + titleName);
            verifyAssertTrue(mrApps.pieChart.isDisplayed(), mrApps, " Piechart for Task Attempts" +
                    " is not displayed");
            if (subTabName.equals("Task Time")) {
                List<WebElement> legendNameList = mrApps.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), mrApps, "Empty legend list");
                for (int l = 0; l < legendNameList.size(); l++) {
                    String legendName = legendNameList.get(l).getText();
                    LOGGER.info("The Legends for subTab " + subTabName + " is " + legendName);
                    verifyAssertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName), mrApps,
                            " The legends displayed in the UI for Task Time doesnot match to the expected list of legends");
                    waitExecuter.sleep(1000);
                    if (legendName.equals("Processing Stages")) {
                        WebElement ele = mrApps.processingStage;
                        verifyTimingStages(action, ele, mrApps, legendName);
                    } else if (legendName.equals("Input Stages")) {
                        WebElement ele = mrApps.inputStage;
                        verifyTimingStages(action, ele, mrApps, legendName);
                    } else {
                        WebElement ele = mrApps.outputStage;
                        verifyTimingStages(action, ele, mrApps, legendName);
                    }
                }
            } else {
                List<WebElement> legendNameList = mrApps.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), mrApps, "Empty legend list for APP TIME");
                for (int l = 0; l < legendNameList.size(); l++) {
                    waitExecuter.sleep(1000);
                    String legendName = legendNameList.get(l).getText();
                    verifyAssertTrue(Arrays.asList(expectedATLegendNames).contains(legendName), mrApps,
                            " The legends displayed in the UI for App Time doesnot match to the expected list of legends");
                    //waitExecuter.sleep(2000);
                    if (legendName.equals("Driver Time")) {
                        WebElement ele = mrApps.driverDrillDown;
                        MouseActions.clickOnElement(driver, ele);
                        String[] expectedDriverLegends = {"FileCommit Time", "File Setup Time", "Others"};
                        List<WebElement> driverLegendNameList = mrApps.legendNames;
                        verifyAssertFalse(driverLegendNameList.isEmpty(), mrApps, "Empty legend list for DRIVER TIME");
                        for (int d = 0; d < driverLegendNameList.size(); d++) {
                            String driverLegend = driverLegendNameList.get(d).getText();
                            verifyAssertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend), mrApps,
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
    public String verifyAppSummaryTabs(MrAppsDetailsPageObject mrApps, String verifyTabName, ExtentTest test) {
        List<WebElement> appsTabList = mrApps.appSummaryTabs;
        verifyAssertFalse(appsTabList.isEmpty(), mrApps, "No Tabs loaded");
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));

        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            LOGGER.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        validateAnalysisTab(mrApps);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateResourcesTab(mrApps);
                        break;
                    case "Program":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateProgramTab(mrApps);
                        break;
                    case "Diagnostics":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateErrorsTab(mrApps);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(mrApps);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                    //  break;
                    case "Configuration":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateTimingTab(mrApps);
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to verify the Timings tabs stages ,legends, graphs and top stages
     */
    public void verifyTimingStages(Actions action, WebElement ele, MrAppsDetailsPageObject mrApps, String legendName) {
        try {
            MouseActions.clickOnElement(driver, ele);
            waitExecuter.sleep(1000);
            List<WebElement> stageLegends = mrApps.legendNames;
            verifyAssertFalse(stageLegends.isEmpty(), mrApps, "The list is empty for " + legendName);
            List<WebElement> graphList = mrApps.timingGraphList;
            verifyAssertFalse(graphList.isEmpty(), mrApps, "No graphs displayed");
            for (int g = 0; g < graphList.size(); g++)
                verifyAssertTrue(graphList.get(g).isDisplayed(), mrApps, "The graph is not displayed");
            List<WebElement> topStagesList = mrApps.topStages;
            verifyAssertFalse(topStagesList.isEmpty(), mrApps, "Top stages are not displayed");
            WebElement backButton = mrApps.backButton;
            action.moveToElement(backButton).click().build().perform();
            waitExecuter.sleep(1000);
        } catch (NoSuchElementException | JavascriptException ex) {
            System.out.println("The element is not present for stage " + legendName);
        }
    }


    /**
     * Method to validate the header app details page and DAG.
     *
     * @return
     */
    public void validateHeaderTab(MrAppsDetailsPageObject mrApps, ExtentTest test) {
        String jobId = mrApps.startTime.getText();
        test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + jobId);
        String startTime = mrApps.EndTime.getText();
        test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + startTime);
        String endTime = mrApps.EndTime.getText();
        test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + endTime);
        String duration = mrApps.Duration.getText();
        test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + duration);
        String dataIO = mrApps.DataIO.getText();
        test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + dataIO);
        verifyAssertTrue(mrApps.Dags.isDisplayed(), mrApps, " Dag data is not displayed ");
        LOGGER.info("Duration = " + duration + " JobId = " + jobId + " starttime = " + startTime + " EndTime = " + endTime + " DataIO = " + dataIO);
        Assert.assertNotSame("", jobId, "Value for jobId missing");
        Assert.assertNotSame("", startTime, "Value for startTime missing");
        Assert.assertNotSame("", endTime, "Value for duration missing");
        Assert.assertNotSame("", duration, "Value for duration missing");
        Assert.assertNotSame("", dataIO, "Value for duration missing");
    }

    /**
     * Method to validate  top right of the app details page.
     *
     * @return
     */
    public void validateTopRightTab(MrAppsDetailsPageObject mrApps, ExtentTest test) {
        String Owner = mrApps.Owner.getText();
        test.log(LogStatus.PASS, "Owner  is displayed in the Header: " + Owner);
        String Cluster = mrApps.Cluster.getText();
        test.log(LogStatus.PASS, "Cluster  is displayed in the Header: " + Cluster);
        String Queue = mrApps.Queue.getText();
        test.log(LogStatus.PASS, "Queue  is displayed in the Header: " + Queue);
        LOGGER.info("Owner = " + Owner + " Cluster = " + Cluster + " starttime = " + Queue + " Queue");
        Assert.assertNotSame("", Owner, "Value for Owner missing");
        Assert.assertNotSame("", Cluster, "Value for Cluster missing");
        Assert.assertNotSame("", Queue, "Value for Queue missing");

    }


    /**
     * Method to validate the stage table header and the data.
     * if validateExecutorTab = true, validate each jobs execution tabs data
     */
    public void validateStageAndStageData(int navigationRows, List<WebElement> navigationRowList,
                                          MrAppsDetailsPageObject mrApps, Boolean validateExecutorTab,
                                          Boolean validateStageTabs) {
        if (navigationRows > 0) {
            String[] expectedHeader = {"Dag Id", "Start Time", "Duration", "IO", "Events"};
            //click the jobId to sort it .
            MouseActions.clickOnElement(driver, mrApps.singleJobHeader);
            for (int rows = 0; rows < navigationRows; rows++) {
                navigationRowList.get(rows).click();
                waitExecuter.sleep(2000);
                if (validateExecutorTab) {
                    verifyAssertTrue(mrApps.DAGData.isDisplayed(), mrApps, " Dag data is not displayed ");
                    LOGGER.info("Dags data is displayed ");
                    List<WebElement> rddBlockList = mrApps.rddBlockList;
                    verifyAssertFalse(rddBlockList.isEmpty(), mrApps, "No DAGs data");
                    LOGGER.info("No. of RDD blocks in the flow chart is " + rddBlockList.size());
                    for (int i = 0; i < rddBlockList.size(); i++) {
                        verifyAssertTrue(rddBlockList.get(i).isDisplayed(), mrApps, "FlowChart doesnot have the RDD blocks displayed");
                    }
                    List<WebElement> rddNumberList = mrApps.rddBlockNumList;
                    verifyAssertFalse(rddNumberList.isEmpty(), mrApps, "Rdd block numbers are present");
                }
                List<WebElement> stageHeaderList = mrApps.stageHeaders;
                for (int i = 0; i < stageHeaderList.size(); i++) {
                    Assert.assertNotSame("", stageHeaderList.get(i).getText());
                    Assert.assertEquals(expectedHeader[i], stageHeaderList.get(i).getText(),
                            "expected stage header do not match to the one in the UI");
                }
                if (validateStageTabs)
                    validateStagesTabs(mrApps);
            }
        }
    }

    /**
     * Method to validate the tabs for each job stage.
     */
    public void validateStagesTabs(MrAppsDetailsPageObject mrApps) {
        //click the stageId to sort it
        driver.findElement(By.xpath("//*[@id='tezStageNavigation-head']/tr/th[1]")).click();
        List<WebElement> stageRowList = mrApps.stageRows;
        verifyAssertFalse(stageRowList.isEmpty(), mrApps, " No stages displayed for JobId");
        for (int i = 0; i < stageRowList.size(); i++) {
            stageRowList.get(i).click();
            waitExecuter.sleep(1000);
            List<WebElement> stageTabsList = mrApps.stagesTab;
            verifyAssertFalse(stageTabsList.isEmpty(), mrApps, " No tabs displayed for selected stage");
            for (int t = 0; t < stageTabsList.size(); t++) {
                String tabName = stageTabsList.get(t).getText();
                switch (tabName) {
                    case "Taskattempt":
                        LOGGER.info("Validating the stage tab Taskattempt");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        validateTaskAttemptTab(mrApps);
                        break;
                    case "Program":
                        LOGGER.info("Validating the stage tab Program");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        try {
                            List<WebElement> programDataList = mrApps.programTabData;
                            verifyAssertFalse(programDataList.isEmpty(), mrApps, " Program data not found");
                            WebElement sourceText = mrApps.programSourceLinkText;
                            String sourceStr = sourceText.getText();
                            int lineNo = Integer.parseInt(sourceStr.split(":")[2].trim());
                            LOGGER.info("LineNo to verify in the Programs tab is " + lineNo);
                            MouseActions.clickOnElement(driver, mrApps.programSourceLink);
                            List<WebElement> programList = mrApps.programTabData;
                            verifyAssertFalse(programList.isEmpty(), mrApps, mrApps.programDataNotFound.getText());
                            WebElement highlightedLine = driver.findElement(By.xpath("//*[@id=\"app-query\"]" +
                                    "/div[contains(@data-range,'" + lineNo + "')]"));
                            verifyAssertTrue(highlightedLine.isDisplayed(), mrApps, " The line no from the stage source doesnot " +
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
                        List<WebElement> headerlist = mrApps.stagesTimelineHeader;
                        verifyAssertFalse(headerlist.isEmpty(), mrApps, " No header displayed");
                        List<WebElement> barGraphList = mrApps.timelineBarGraph;
                        verifyAssertFalse(barGraphList.isEmpty(), mrApps, " No bar graphs displayed");
                        for (int h = 0; h < headerlist.size(); h++) {
                            String headerName = headerlist.get(h).getText();
                            LOGGER.info("The header name is " + headerName);
                            verifyAssertTrue(Arrays.asList(expectedHeaderList).contains(headerName), mrApps,
                                    "Header names displayed on the UI does not match with the expected headerList");
                            verifyAssertTrue(barGraphList.get(h).isDisplayed(), mrApps, " The bar graph for " +
                                    "" + headerName + " is not displayed");
                        }
                        List<WebElement> subTabList = mrApps.stagesTimelineSubTab;
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
                        List<WebElement> stageTimingHeaderList = mrApps.stageTimingHeaders;
                        Assert.assertFalse(stageTimingHeaderList.isEmpty(), "The headers in the timmings " +
                                "tab not displayed");
                        String[] expectedTimingHeaders = {"Stage Time Distribution", "IO Metrics",
                                "Time Metrics"};
                        List<WebElement> legendList = mrApps.legendNames;
                        Assert.assertFalse(legendList.isEmpty(), "No legends displayed");
                        List<WebElement> graphList = mrApps.timingGraphList;
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
    public String verifyRightPaneKpis(MrAppsDetailsPageObject mrApps) {
        List<WebElement> kpiList = mrApps.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = mrApps.rightPaneAppKpis;
        List<WebElement> appKpiVal = mrApps.rightPaneAppKpiVal;
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
    public String verifyAppId(MrAppsDetailsPageObject mrApps, ApplicationsPageObject appPageObj) {
        String appId = mrApps.getAppId.getText().trim();
        LOGGER.info("Tez application Id is " + appId);
        appPageObj.getTypeFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = mrApps.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Tez Application Id is not displayed in the Header");
        return headerAppId;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppStatus(MrAppsDetailsPageObject mrApps) {
        String Status = mrApps.Status.getText();
        waitExecuter.sleep(3000);
        LOGGER.info("Tez application Id is " + Status);
        Assert.assertNotSame("", Status, "Tez Application Id is not displayed in the Header");
        return Status;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyStatus(MrAppsDetailsPageObject mrApps, ApplicationsPageObject appPageObj) {
        String statusTable = mrApps.Status.getText();
        LOGGER.info("Tez application Id is " + statusTable);
        appPageObj.getTypeFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = mrApps.appStatus.getText();
        Assert.assertNotSame("", status, "Tez Status is not displayed in the Header");
        return status;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Status .
     */
    public String verifyusername(MrAppsDetailsPageObject mrApps) {
        String typeValue = mrApps.getUsername.getText();
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
    public String verifyAppname(MrAppsDetailsPageObject mrApps) {
        WebElement Appname = mrApps.getAppname;
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
    public String verifyappId(MrAppsDetailsPageObject mrApps) {
        WebElement Appid = mrApps.getAppid;
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
     * and verify  clusterId .
     */
    public String verifyclusterId(MrAppsDetailsPageObject mrApps) {
        WebElement Appid = mrApps.getClusterId;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppnametoolTip = mrApps.getClusterId;
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
     * and verify  starttime .
     */
    public String verifystarttime(MrAppsDetailsPageObject mrApps) {
        String typetarttime = mrApps.getstartTime.getText();
        LOGGER.info("Tez Status is " + typetarttime);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typetarttime, "Tez User name is not displayed in the Table");
        return typetarttime;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  duration .
     */
    public String verifyduration(MrAppsDetailsPageObject mrApps) {
        String typetarttime = mrApps.getduration.getText();
        LOGGER.info("Tez Status is " + typetarttime);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typetarttime, "Tez User name is not displayed in the Table");
        return typetarttime;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Read IO .
     */
    public String verifyRead(MrAppsDetailsPageObject mrApps) {
        String ReadIO = mrApps.getRead.getText();
        LOGGER.info("Tez Status is " + ReadIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", ReadIO, "Tez User name is not displayed in the Table");
        return ReadIO;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Write .
     */
    public String verifyWrite(MrAppsDetailsPageObject mrApps) {
        String WriteIO = mrApps.getWrite.getText();
        LOGGER.info("Tez Status is " + WriteIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", WriteIO, "Tez User name is not displayed in the Table");
        return WriteIO;
    }

    /**
     * Common steps to navigate to the Jobs page from header.
     * Clicks on jobs tab
     * Selects a specific cluster
     * Selects 90 days time interval
     * Deselsects all the selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(SubTopPanelModulePageObject topPanelObj, AllApps allApps,
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


    /***
     * Common actions listed in one method that does the following:
     * Navigate to Jobs tab from header
     * Verify that the left pane has Tez app
     * Get Job count of selected App click on it and go to apps details page
     * Verify specific summary tabs.
     * */
    public void commonTabValidation(ExtentTest test, String clusterId, String tabName, Logger logger) {
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has Tez check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Tez check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        mrDetailsPage.clickOnlyLink("Tez");
        applicationsPageObject.expandStatus.click();
        int appCount = mrDetailsPage.clickOnlyLink("Success");
        //Clicking on the Tez app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Tez Application Id is displayed in the Header: " + headerAppId);
            mrDetailsPage.verifyAppSummaryTabs(mrApps, tabName, test);
            //Close apps details page
            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }


    /**
     * Method to validate the Load Diagnostic Action
     */
    public void verifyLoadDiagnosticAction(MrAppsDetailsPageObject mrApps) {
        WebElement actionMenu = mrApps.loadAction;
        MouseActions.clickOnElement(driver, actionMenu);
        List<WebElement> elementList = mrApps.loadActionList;
        verifyAssertFalse(elementList.isEmpty(), mrApps, "No elements listed in the Action Menu Expected " +
                "(Load Diagnostic | Load Logs)");
        isDignosticWin = true;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().equals("Load Diagnostics")) {
                MouseActions.clickOnElement(driver, elementList.get(i));
                waitExecuter.sleep(1000);
                verifyAssertTrue(mrApps.loadDiagnosticWin.isDisplayed(), mrApps,
                        "The load diagnostic window is not displayed");
                String expectedStr = "APPLICATION DIAGNOSTICS";
                String winHeader = mrApps.loadDiagnosticWinHeader.getText();
                LOGGER.info("The LoadDignostics window header is " + winHeader);
                verifyAssertTrue(winHeader.contains(expectedStr), mrApps, " Expected Header String is not " +
                        "displayed\n Expected = " + expectedStr + " Actual = " + winHeader);
                MouseActions.clickOnElement(driver, mrApps.loadDiagnosticWinClose);
                MouseActions.clickOnElement(driver, actionMenu);
            }
        }
        isDignosticWin = false;
    }

    public void verifyAssertFalse(Boolean condition, MrAppsDetailsPageObject mrApps, String msg) {
        String appDuration = "0";
        try {
            appDuration = verifyRightPaneKpis(mrApps);
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            //Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, mrApps.loadDiagnosticWinClose);
            else
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    public void verifyAssertTrue(Boolean condition, MrAppsDetailsPageObject mrApps, String msg) {
        try {
            Assert.assertTrue(condition, msg);
        } catch (Throwable e) {
            //Close apps details page
            if (isDignosticWin) {
                MouseActions.clickOnElement(driver, mrApps.loadDiagnosticWinClose);
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            } else
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

}
