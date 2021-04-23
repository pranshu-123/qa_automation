package com.qa.scripts.appdetails;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.clusters.ELKPageObject;
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
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class MrAppsDetailsPage {
    private static final Boolean isDignosticWin = false;
    private static final Logger LOGGER = Logger.getLogger(MrAppsDetailsPage.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;


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
    public void validateTaskAttempRtTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> footerNameList = mrApps.taskAttRFooterName;
        Assert.assertFalse(footerNameList.isEmpty(),
                "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = mrApps.taskAttRFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " +
                "not displayed");
        String pValStr = mrApps.resourcesRPieChartInternalVal.getText();
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
     * Method to validate the tasks attempt tab Map tab.
     */
    public void validateTaskAttemptMTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> footerNameList = mrApps.taskAttMFooterName;
        Assert.assertFalse(footerNameList.isEmpty(),
                "SUCCESS/FAILED Attempts not displayed");
        List<WebElement> footerValList = mrApps.taskAttMFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " +
                "not displayed");
        String pValStr = mrApps.resourcesMPieChartInternalVal.getText();
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


    /***
     * Method to validate logstash metrics graph .
     */
    public void verifyResourceGraph(ELKPageObject elkPageObject) {
        List<WebElement> metricsList = elkPageObject.logstashMetricsList;
        List<WebElement> headerList = elkPageObject.logstashGraphHeader;
        List<WebElement> footerList = elkPageObject.logstashGraphFooter;
        List<WebElement> graphList = elkPageObject.logstashGraph;

        for (int i = 0; i < metricsList.size(); i++) {
            String metricsName = headerList.get(i).getText();
            Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
            LOGGER.info("Metrics Name: [" + metricsName + "] displayed in the header");
            Assert.assertTrue(graphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
            LOGGER.info("The graph for Metrics : [" + metricsName + "] is displayed");
            Assert.assertTrue(footerList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
            LOGGER.info("The footer for Metrics : [" + metricsName + "] is displayed");
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
                    case "Resources":
                        LOGGER.info("Validating the stage tab Taskattempt");
                        MouseActions.clickOnElement(driver, stageTabsList.get(t));
                        waitExecuter.sleep(1000);
                        validateTaskAttempRtTab(mrApps);
                        validateTaskAttemptMTab(mrApps);
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
                            assertTrue(Arrays.asList(expectedSubTabs).contains(subTask),
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
                            assertTrue(Arrays.asList(expectedTimingHeaders).contains(stageTimingHeader),
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
            Assert.assertNotNull(appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotNull(appKpiVal.get(i).getText(), "Kpi Value is empty");
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
        for (WebElement webElement : kpiList) {
            LOGGER.info("The leftPane kpi is " + webElement.getText());
            String kpis = webElement.getText();
            Assert.assertNotNull(kpis, "The kpis is empty");
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            LOGGER.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotNull(kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotNull(kpiVal, "The kpi " + kpiVal + " is empty");
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
        Assert.assertEquals(appId, headerAppId, "Tez Application Id is not displayed in the Header");
        return headerAppId;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyJobsSummary(MrAppsDetailsPageObject mrApps) {
        String jobSummary = mrApps.Status.getText().trim();
        LOGGER.info("Map Reduce job Summary is " + jobSummary);
        mrApps.getSummaryFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobSummaryStatus = mrApps.getJobSummary.getText().trim();
        Assert.assertNotSame(jobSummary, jobSummaryStatus, "Map Reduce application page job Summary is not displayed in the Header");
        return jobSummaryStatus;
    }


    /**
     * Method to get the app status.
     */
    public String getAppStatus(MrAppsDetailsPageObject mrApps) {
        String Status = mrApps.Status.getText();
        waitExecuter.sleep(3000);
        LOGGER.info("The application Id is " + Status);
        Assert.assertNotNull(Status, "Application Id is not displayed in the Header");
       // Assert.assertNotSame("", Status, "Application Id is not displayed in the Header");
        return Status;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  clusterId .
     */
    public String verifyclusterId(MrAppsDetailsPageObject mrApps) {
        WebElement Appid = mrApps.getClusterId;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppNameToolTip = mrApps.getClusterId;
        String AppIdText = AppNameToolTip.getText().trim();
        LOGGER.info("Mr app id is " + Appid.getText());
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertEquals(AppIdText, Appid.getText(), "MR Application Id is not displayed in the Header");
        return AppIdText;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and check if the table data like start time, duration, read and write is displayed or not.
     */
    public void checkAppsJobTableData(String data, String colType) {
        boolean onlySpecialChars = data.matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(data.isEmpty() || onlySpecialChars, colType + " data is not displayed in the table");
        LOGGER.info("Data for "+ colType + " is displayed in the table");
    }

    /**
     * Method to validate AppSummary Analysis tab.
     */
    public void validateAnalysisTab(MrAppsDetailsPageObject mrApps) {
        ArrayList<String> efficiency = new ArrayList<>();
        ArrayList<String> recommendation = new ArrayList<>();
        List<WebElement> insightType = mrApps.insightsType;
        waitExecuter.waitUntilPageFullyLoaded();
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
     * Method to validate AppSummary Errors tab.
     */
    public void validateErrorsTab(MrAppsDetailsPageObject mrApps) {
        String[] expectedErrorCategory = {"FATAL/EXCEPTION", "ERROR", "WARNING"};
        List<WebElement> errorTypeList = mrApps.errorCategories;
        waitExecuter.sleep(4000);
        verifyAssertFalse(errorTypeList.isEmpty(), mrApps, " Errors tab is not populated");
        for (int e = 0; e < errorTypeList.size(); e++) {
            String errorType = errorTypeList.get(e).getText();
            String newErrorType = "";
            LOGGER.info("Error Type is " + errorType);
            if (errorType.contains("FATAL / EXCEPTION-"))
                newErrorType = "FATAL / EXCEPTION-";
            else
                newErrorType = errorType;
            LOGGER.info("New Error Type is " + errorType);
            verifyAssertTrue(Arrays.asList(expectedErrorCategory).contains(newErrorType), mrApps,
                    " The UI error types displayed does not match with the Expected error types ");
            waitExecuter.sleep(1000);
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


    public void validateConfigurationTab(MrAppsDetailsPageObject mrApps) {
        String beforeResetProp = mrApps.configPropNum.getText();
        int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
        LOGGER.info("Number of properties displayed by default are  " + propNum);

        //Verify if property key value is present:
        List<WebElement> propKeyList = mrApps.configPropKey;
        List<WebElement> propValueList = mrApps.configPropValue;
        LOGGER.info("PropKey size = " + propKeyList.size() + " propVal size = " +
                propValueList.size());
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), mrApps, " Key/Value is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " +
                "is missing");

        //Check RESET buttons sets default props
        /* MouseActions.clickOnElement(driver, mrApps.resetButton);*/
        waitExecuter.sleep(3000);
        String afterResetProp = mrApps.configPropNum.getText();
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("No. of Properties displayed by default " + beforeResetProp + "\n " +
                "No. of Properties displayed after RESET " + afterResetProp);
        Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " +
                "to default");
    }

    public void validateConfigurationSearchTab(MrAppsDetailsPageObject mrApps) {
        LOGGER.info("Click on queue search box and search for path");
        mrApps.SearchProp.click();
        mrApps.SearchProp.sendKeys("mapreduce.jobhistory.jhist.format");
        String beforeResetProp = mrApps.configPropNum.getText();
        int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
        LOGGER.info("Number of properties displayed by default are  " + propNum);
        //Verify if property key value is present:
        List<WebElement> propKeyList = mrApps.configPropKey;
        List<WebElement> propValueList = mrApps.configPropValue;
        LOGGER.info("PropKey size = " + propKeyList.size() + " propVal size = " +
                propValueList.size());
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), mrApps, " Key/Value is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " +
                "is missing");

        //Check RESET buttons sets default props
        MouseActions.clickOnElement(driver, mrApps.resetButton);
        waitExecuter.sleep(3000);
        String afterResetProp = mrApps.configPropNum.getText();
        LOGGER.info("No. of Properties displayed by default " + beforeResetProp + "\n " +
                "No. of Properties displayed after RESET " + afterResetProp);
        Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " + "to default");
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

    public ArrayList<Integer> getTableCnt(List<WebElement> tableRowList, String fileType, MrAppsDetailsPageObject mrApps) {
        ArrayList<Integer> expectedFileCnt = new ArrayList<>();
        try {
            for (int row = 1; row <= tableRowList.size(); row++) {
                WebElement rowData = mrApps.getDataTable;
                Assert.assertTrue(rowData.isDisplayed(), "No data under column: File " +
                        " for " + fileType + " file type");
                int fileCnt = Integer.parseInt(rowData.getText().trim());
                LOGGER.info("The file count is " + fileCnt);
                expectedFileCnt.add(fileCnt);
            }
        } catch (NoSuchElementException ex) {
            WebElement noData = mrApps.noDataText;
            Assert.assertFalse(noData.isDisplayed(), "Data not present in the table got {'" + noData.getText() + "}' message");
        }
        return expectedFileCnt;
    }


    public void validateMetricsTab(MrAppsDetailsPageObject mrApps) {
        waitExecuter.waitUntilPageFullyLoaded();
        //Verify if property key value is present:
        List<WebElement> propKeyList = mrApps.metricsKey;
        List<WebElement> propValueList = mrApps.metricsValue;
        List<WebElement> propDescriptionList = mrApps.metricsDescription;
        LOGGER.info("PropKey size = " + propKeyList.size() + " propVal size = " +
                propValueList.size() + "PropDescription size = " + propDescriptionList.size());
        waitExecuter.waitUntilPageFullyLoaded();
        verifyAssertFalse((propKeyList.isEmpty() && propValueList.isEmpty() && propDescriptionList.isEmpty()), mrApps, " Key/Value/Description is empty");
        Assert.assertEquals(propKeyList.size(), propValueList.size(), propDescriptionList.size(), "One of the key/Value/Description " +
                "is missing");
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
                List<WebElement> legendNameTTList = mrApps.legendNames;
                verifyAssertFalse(legendNameTTList.isEmpty(), mrApps, "Empty legend list");
                for (int l = 0; l < legendNameTTList.size(); l++) {
                    String legendName = legendNameTTList.get(l).getText();
                    LOGGER.info("The Legends for subTab " + subTabName + " is " + legendName);
                    verifyAssertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName), mrApps,
                            " The legends:[" + legendName + "] displayed in the UI for Task Time doesnot match to the" +
                                    " expected list of legends: " + Arrays.toString(expectedTTLegendNames));
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
                List<WebElement> legendNameATList = mrApps.ATlegendNames;
                verifyAssertFalse(legendNameATList.isEmpty(), mrApps, "Empty legend list for APP TIME");
                for (int a = 0; a < legendNameATList.size(); a++) {
                    String ATlegendName = legendNameATList.get(a).getText();
                    verifyAssertTrue(Arrays.asList(expectedATLegendNames).contains(ATlegendName), mrApps,
                            " The legends [" + ATlegendName + "] displayed in the UI for App Time doesnot match to the " +
                                    "expected list of legends: " + Arrays.toString(expectedATLegendNames));
                    if (ATlegendName.equals("Driver Time")) {
                        WebElement ele = mrApps.driverDrillDown;
                        MouseActions.clickOnElement(driver, ele);
                        String[] expectedDriverLegends = {"FileCommit Time", "File Setup Time", "Others"};
                        List<WebElement> driverLegendNameList = mrApps.driverLegendNames;
                        verifyAssertFalse(driverLegendNameList.isEmpty(), mrApps, "Empty legend list for DRIVER TIME");
                        for (int d = 0; d < driverLegendNameList.size(); d++) {
                            String driverLegend = driverLegendNameList.get(d).getText();
                            LOGGER.info("The driverLegend name " + driverLegend);
                            verifyAssertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend), mrApps,
                                    " The legend [" + driverLegend + "] displayed in the fUI for App Time-> Driver Time does not " +
                                            "match to the expected list of legends: " + Arrays.toString(expectedDriverLegends));
                        }
                        driverLegendNameList.clear();
                        WebElement backButton = mrApps.backButton;
                        action.moveToElement(backButton).click().build().perform();
                        waitExecuter.sleep(1000);
                    }
                }
            }
        }
    }

    /**
     * Method to validate the tasks attempt Map tab in Resources and stages tab.
     */
    public void validateTaskAttemptMapTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> footerNameList = mrApps.taskAttFooterName;
        List<WebElement> footerValList = mrApps.taskAttFooterVal;
        //String pValStr = mrApps.resourcesPieChartInternalVal.getText();
        String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
        /*//int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
        //logger.info("The value displayed inside the Pie Chart is " +
                pieChartInternalVal);*/
        int totalTaskCnt = 0;
        for (int f = 0; f < footerNameList.size(); f++) {
            String footerName = footerNameList.get(f).getText();
            String footerValStr = footerValList.get(f).getText();
            int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]",
                    "").trim());
            totalTaskCnt += footerVal;
            LOGGER.info("FooterName = " + footerName + " Value = " + footerVal);
        }
        LOGGER.info("Total Task Attempts = " + totalTaskCnt + "");
    }

    /**
     * Method to validate the Contains Graph Map tab in Resources and stages tab.
     */
    public void validateContainsGraph(MrAppsDetailsPageObject mrApps, String expectedMetricsName,
                                      ExtentTest test) {
        List<WebElement> metricsKpiList = mrApps.containerMetrics;
        List<WebElement> metricsKpiHeaderList = mrApps.containerMetricsHeader;
        List<WebElement> metricsKpiGraphList = mrApps.containerMetricsGraph;
        Assert.assertFalse(metricsKpiList.isEmpty(), "Contains Graph cluster is empty");
        for (int i = 0; i < metricsKpiList.size(); i++) {
            String metricsName = metricsKpiHeaderList.get(i).getText();
            waitExecuter.sleep(2000);
            LOGGER.info("Metrics Name: " + metricsName + " Expected Name: " + expectedMetricsName);
            if (metricsName.equals(expectedMetricsName)) {
                Assert.assertFalse(metricsName.isEmpty(), " Contains Metrics Name not displayed");
                LOGGER.info("Contains Metrics Name: [" + metricsName + "] displayed in the header");
                test.log(LogStatus.PASS, "Contains Metrics Name displayed in the header" + metricsName);
                Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
                LOGGER.info("The graph for Metrics : [" + metricsName + "] is displayed");
                waitExecuter.sleep(3000);

            }
        }

    }


    /**
     * Method to validate the tasks attempt Reduce tab in Resources and stages tab.
     */
    public void validateTaskAttemptReduceTab(MrAppsDetailsPageObject mrApps) {
        List<WebElement> footerNameList = mrApps.taskAttReduceFooterName;
        Assert.assertFalse(footerNameList.isEmpty(),
                "SUCCESS Attempts not displayed");
        List<WebElement> footerValList = mrApps.taskAttReduceFooterVal;
        Assert.assertFalse(footerValList.isEmpty(), "SUCCESS Attempts values " +
                "not displayed");
        String pValStr = mrApps.resourcesReducePieChartInternalVal.getText();
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

    public void validateResourcesMetricsTab(MrAppsDetailsPageObject mrApps,
                                     ExtentTest test) {
        waitExecuter.waitUntilElementPresent(mrApps.resourcesMetricsDropDown);
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
            List<WebElement> resourcesMetricsLineGraphList = mrApps.resourcesMetricsLineGraph;
            List<WebElement> metricLegendList = mrApps.resourcesMetricsPlotGraphLegend;
            Assert.assertEquals(resourcesMetricsLineGraphList.size(), metricLegendList.size(),
                    "The number of executors in the legend do not match to the ones plotted in the graph for metrics "
                            + metric);
            MouseActions.clickOnElement(driver, metricDropDown);
            waitExecuter.sleep(4000);
        }
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateResourcesTab(MrAppsDetailsPageObject mrApps,String verifyTabName,
                                     ExtentTest test) {
        String[] expectedGraphTitle = {"Metrics", "Memory"};
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), mrApps, "No title displayed");
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), mrApps, "No graphs displayed");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);
            switch (graphTitle) {
                case "Metrics":
                    validateResourcesMetricsTab(mrApps,test);
                    LOGGER.info("Validating the Graph " + graphTitle);
                    break;
                case "Memory":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    break;

            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                    + graphTitle);
        }
    }

    /***
     * Method to verify the MR Metrics KPI Graphs of a connected MR cluster
     */
    public void verifyContainersKPIGraphs(MrAppsDetailsPageObject mrApps, String verifyTabName, String graphId) {
        List<WebElement> ContainersKpiHeaderList = mrApps.containerMetricsHeader;
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        Assert.assertFalse(ContainersKpiHeaderList.isEmpty(), "Metrics for mrapps cluster is empty");
        for (int i = 0; i < graphTitleList.size(); i++) {
            String metricsName = ContainersKpiHeaderList.get(i).getText();
            LOGGER.info("Metrics Name: " + metricsName + " Expected Name: " + verifyTabName);
            if (metricsName.equals(verifyTabName)) {
                Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
                LOGGER.info("Metrics Name: [" + metricsName + "] displayed in the header");
                waitExecuter.waitUntilElementPresent(allGraphsList.get(i));
                Assert.assertTrue(allGraphsList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
                LOGGER.info("The graph for Metrics : [" + metricsName + "] is displayed");

            } else {
                verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                        + metricsName);
            }
        }
    }

    /***
     * Method to verify the Metrics KPI Graphs of a connected MR cluster
     */
    public void verifyMetricsKPIGraphs(MrAppsDetailsPageObject mrApps) {
        String[] expectedGraphTitle = {"Metrics"};
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        verifyAssertFalse(graphTitleList.isEmpty(), mrApps, "No title displayed");
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        verifyAssertFalse(allGraphsList.isEmpty(), mrApps, "No graphs displayed");
        Assert.assertFalse(graphTitleList.isEmpty(), "Metrics for map reduce graphs is empty");
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);
            verifyAssertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), mrApps, " The expected" +
                    " Graph title doesnot match with the titles in the UI");
            verifyAssertTrue(allGraphsList.get(t).isDisplayed(), mrApps, " All Graphs are not displayed");
            switch (graphTitle) {
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
            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                    + graphTitle);
        }
    }

    public void verifyAxis(String axisPath, String axisName) {
        List<WebElement> axisPathList = driver.findElements(By.xpath(axisPath));
        Assert.assertFalse(axisPathList.isEmpty(), "No points plotted on the " + axisName);
        HashSet<String> axisValSet = new HashSet<>();
        ArrayList<String> axisValArr = new ArrayList<>();
        for (int i = 0; i < axisPathList.size(); i++) {
            axisValArr.add(axisPathList.get(i).getText());
            axisValSet.add(axisPathList.get(i).getText());
        }
        LOGGER.info("Expected " + axisName + " : " + axisValSet + "\n Actual " + axisName + " : " + axisValArr);
        Assert.assertEquals(axisValSet.size(), axisValArr.size(), "Duplicate values present in the " + axisName + "\n" +
                "Expected : " + axisValSet + " Actual : " + axisValArr);
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateMapandReduceTab(MrAppsDetailsPageObject mrApps, String verifyTabName, ExtentTest test) {
        String[] expectedGraphTitle = {"Task Attempt (MAP)", "Task Attempt (REDUCE)"};
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);
            switch (graphTitle) {
                case "Task Attempt (MAP)":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptMapTab(mrApps);
                    break;
                case "Task Attempt (REDUCE)":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    validateTaskAttemptReduceTab(mrApps);
                    break;

            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                    + graphTitle);
        }
    }

    /**
     * Method to validate AppSummary Resource tab.
     */
    public void validateContainsTab(MrAppsDetailsPageObject mrApps, String verifyTabName, ExtentTest test) {
        String[] expectedGraphTitle = {"Containers"};
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> graphTitleList = mrApps.resourcesGraphTitle;
        List<WebElement> allGraphsList = mrApps.resourcesAllGraphs;
        for (int t = 0; t < graphTitleList.size(); t++) {
            String graphTitle = graphTitleList.get(t).getText();
            LOGGER.info("Graph title is " + graphTitle);

            switch (graphTitle) {
                case "Containers":
                    LOGGER.info("Validating the Graph " + graphTitle);
                    validateContainsGraph(mrApps, "Containers", test);
                    waitExecuter.sleep(3000);
                    break;
            }
            verifyAssertTrue(allGraphsList.get(0).isDisplayed(), mrApps, " No graph is displayed for "
                    + graphTitle);
        }
    }


    /**
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAppSummaryTabs(MrAppsDetailsPageObject mrApps, String verifyTabName, ExtentTest test) {
        List<WebElement> appsTabList = mrApps.appSummaryTabs;
        /* verifyAssertFalse(appsTabList.isEmpty(), mrApps, "No Tabs loaded");*/
        String tabName = "";
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));

        for (int i = 0; i < appsTabList.size(); i++) {
            tabName = appsTabList.get(i).getText();
            LOGGER.info("Validating tab " + tabName);
            if (tabName.equals(verifyTabName)) {
                switch (verifyTabName) {
                    case "Analysis":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        validateAnalysisTab(mrApps);
                        test.log(LogStatus.PASS, "Analysis tab is populated");
                        break;
                    case "Resources":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateTaskAttemptMapTab(mrApps);
                        break;
                    case "Errors":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(4000);
                        validateErrorsTab(mrApps);
                        test.log(LogStatus.PASS, "Errors tab is populated");
                        break;
                    case "Logs":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateLogsTab(mrApps);
                        break;
                    case "Tags":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        String tagValue = validateTagsTab(mrApps);
                        test.log(LogStatus.PASS, "Tags tab is populated");
                        return tagValue;
                    //  break;
                    case "Metrics":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(3000);
                        validateMetricsTab(mrApps);
                        test.log(LogStatus.PASS, "Metrics tab is populated");
                        break;
                    case "Configuratio...":
                        MouseActions.clickOnElement(driver, appsTabList.get(i));
                        waitExecuter.sleep(10000);
                        validateConfigurationTab(mrApps);
                        break;
                }
                break;
            }
        }
        return "";
    }

    /**
     * Method to verify the summary tabs in the right pane of the App Details page
     */
    public String verifyAllDataTabs(MrAppsDetailsPageObject mrApps, String verifyTabName, ExtentTest test) {
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


    /***
     * Common actions listed in one method that does the following:
     * Navigate to Jobs tab from header
     * Verify that the left pane has Map Reduce app
     * Get Job count of selected App click on it and go to apps details page
     * Verify specific summary tabs.
     * */
    public void commonSetupCodeForSumarryTabValidation(ExtentTest test, String clusterId, String tabName, Logger logger,
                                                       Boolean isFailedApp) {
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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        //Verify that the left pane has Map reduce check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Map reduce check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        int totalMapReduceAppCnt = mrDetailsPage.clickOnlyLink("Map Reduce");
        waitExecuter.sleep(2000);
        if (totalMapReduceAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            int appCount = 0;
            if (isFailedApp)
                appCount = mrDetailsPage.clickOnlyLink("Failed");
            else
                appCount = mrDetailsPage.clickOnlyLink("Success");
            waitExecuter.sleep(2000);
            mrApps.sortByDurationApp.click();
            waitExecuter.waitUntilPageFullyLoaded();
            mrApps.sortUp.click();
            waitExecuter.sleep(2000);
            //Clicking on the Spark app must go to apps detail page
            if (appCount > 0) {
                String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
                test.log(LogStatus.PASS, "Map Reduce Application Id is displayed in the Header: " + headerAppId);
                mrDetailsPage.verifyAppSummaryTabs(mrApps, tabName, test);
                //Close apps details page
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            } else {
                test.log(LogStatus.SKIP, "No Map Reduce Application present");
                logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
        } else {
            test.log(LogStatus.SKIP, "No MapReduce Application present");
            logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }


    /**
     * Method to validate Analysis tab color code.
     */
    public void analysisColorCode(MrAppsDetailsPageObject mrApps, ExtentTest test) {
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
                String efficiencycolorCode = mrApps.colorCode.getAttribute("class");
                String[] arrColor = efficiencycolorCode.split("#");
                assertTrue(arrColor[1].equals("d54451"));
                test.log(LogStatus.PASS, "No Map Reduce Application present" + efficiencycolorCode);
            } else {
                //Store it in recommendation array
                recommendation.add(insights);
                String recommendationcolorCode = mrApps.colorCode.getAttribute("class");
                String[] recColor = recommendationcolorCode.split("#");
                assertTrue(recColor[1].equals("ffb900"));
                test.log(LogStatus.PASS, "No Map Reduce Application present" + recommendationcolorCode);
            }
        }
        //verifyAssertFalse((efficiency.isEmpty() && recommendation.isEmpty()), mrApps, "No insights generated");
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
            assertTrue(condition, msg);
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