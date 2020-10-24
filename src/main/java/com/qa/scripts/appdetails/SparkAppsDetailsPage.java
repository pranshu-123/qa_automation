package com.qa.scripts.appdetails;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.ActionPerformer;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparkAppsDetailsPage {

  private WaitExecuter waitExecuter;
  private WebDriver driver;

  Logger logger = LoggerFactory.getLogger(SparkAppsDetailsPage.class);

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public SparkAppsDetailsPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    this.driver = driver;
  }

  /**
   * Method to validate the tasks attempt tab in Resources and stages tab.
   */
  public void validateTaskAttemptTab(SparkAppsDetailsPageObject sparkAppPageObj) {
    List<WebElement> footerNameList = sparkAppPageObj.taskAttFooterName;
    Assert.assertFalse(footerNameList.isEmpty(),
        "SUCCESS/FAILED Attempts not displayed");
    List<WebElement> footerValList = sparkAppPageObj.taskAttFooterVal;
    Assert.assertFalse(footerValList.isEmpty(), "SUCCESS/FAILED Attempts values " +
        "not displayed");
    String pValStr = sparkAppPageObj.resourcesPieChartInternalVal.getText();
    String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
    int pieChartInternalVal = Integer.parseInt(Arrays.asList(pValStr.split(regex)).get(0));
    logger.info("The value displayed inside the Pie Chart is " +
        pieChartInternalVal);
    int totalTaskCnt = 0;
    for (int f = 0; f < footerNameList.size(); f++) {
      String footerName = footerNameList.get(f).getText();
      String footerValStr = footerValList.get(f).getText();
      int footerVal = Integer.parseInt(footerValStr.replaceAll("[^\\dA-Za-z ]",
          "").trim());
      totalTaskCnt += footerVal;
      logger.info("FooterName = " + footerName + " Value = " + footerVal);
    }
    logger.info("Total Task Attempts = " + totalTaskCnt + " pie chart val = " +
        pieChartInternalVal);
  }


  /**
   * Method to verify the summary tabs in the right pane of the App Details page
   */
  public void verifyAppSummaryTabs(SparkAppsDetailsPageObject sparkAppPageObj, String verifyTabName, ExtentTest test) {
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
                //Store it in recommendation array
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
              throw new AssertionError("Caught exception while clicking the collapsable" +
                  " icon for insights.\n" + ex.getMessage());
            }
            test.log(LogStatus.PASS, "Analysis tab is populated");
            break;
          case "Resources":
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            //appsTabList.get(i).click();
            waitExecuter.sleep(3000);
            String[] expectedGraphTitle = {"Task Attempts", "Containers", "Vcores", "Memory", "Metrics"};
            List<WebElement> graphTitleList = sparkAppPageObj.resourcesGraphTitle;
            verifyAssertFalse(graphTitleList.isEmpty(), sparkAppPageObj, "No title displayed");
            List<WebElement> allGraphsList = sparkAppPageObj.resourcesAllGraphs;
            verifyAssertFalse(allGraphsList.isEmpty(), sparkAppPageObj, "No graphs displayed");
            for (int t = 0; t < graphTitleList.size(); t++) {
              String graphTitle = graphTitleList.get(t).getText();
              logger.info("Graph title is " + graphTitle);
              Assert.assertTrue(Arrays.asList(expectedGraphTitle).contains(graphTitle), "The expected" +
                  " Graph title doesnot match with the titles in the UI");
              Assert.assertTrue(allGraphsList.get(t).isDisplayed(), "All Graphs are not displayed");
              switch (graphTitle) {
                case "Task Attempts":
                  logger.info("Validating the Graph " + graphTitle);
                  validateTaskAttemptTab(sparkAppPageObj);
                  //Assert.assertSame(totalTaskCnt, pieChartInternalVal, "The Values are not same");
                  break;
                case "Containers":
                case "Metrics":
                  logger.info("Validating the Graph " + graphTitle);
                  WebElement metricDropDown = sparkAppPageObj.resourcesMetricsDropDown;
                  MouseActions.clickOnElement(driver, metricDropDown);
                  List<WebElement> dropDownList = sparkAppPageObj.resourcesMetricsDropDownData;
                  Assert.assertFalse(dropDownList.isEmpty(), "No contents listed in the dropdown");
                  String[] expectetContents = {"availableMemory", "vmRss", "systemCpuLoad",
                      "processCpuLoad", "gcLoad", "maxHeap", "usedHeap"};
                  for (int d = 0; d < dropDownList.size(); d++) {
                    String metric = dropDownList.get(d).getText();
                    logger.info("The metric is " + metric);
                    Assert.assertTrue(Arrays.asList(expectetContents).contains(metric), "The expected" +
                        " metric is not listed in the drop down box");
                    //click on the dropdown list element and validate the graph
                    MouseActions.clickOnElement(driver, dropDownList.get(d));
                    List<WebElement> resourcesMetricsPlotGraphList = sparkAppPageObj.resourcesMetricsPlotGraph;
                    List<WebElement> metricLegendList = sparkAppPageObj.resourcesMetricsPlotGraphLegend;
                    Assert.assertEquals(resourcesMetricsPlotGraphList.size(), metricLegendList.size(),
                        "The number of executors in the legend do not match to the ones plotted in the graph");
                    MouseActions.clickOnElement(driver, metricDropDown);
                  }

                case "Vcores":
                case "Memory":
                  logger.info("Validating the Graph " + graphTitle);
                  break;

              }
              Assert.assertTrue(allGraphsList.get(0).isDisplayed(), "No graph is displayed for "
                  + graphTitle);
            }
            break;
          case "Errors":
            String[] expectedErrorCategory = {"driver", "executor-1", "executor-2", "rm-diagnostics"};
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            //appsTabList.get(i).click();
            waitExecuter.sleep(3000);
            List<WebElement> errorTypeList = sparkAppPageObj.errorCategories;
            Assert.assertFalse(errorTypeList.isEmpty(), "Errors tab is not populated");
            for (int e = 0; e < errorTypeList.size(); e++) {
              String errorType = errorTypeList.get(e).getText();
              logger.info("Error Type is " + errorType);
              Assert.assertTrue(Arrays.asList(expectedErrorCategory).contains(errorType),
                  "The UI error types displayed does not match with the Expected error types ");
            }
            List<WebElement> errorCollapsableList = sparkAppPageObj.errorCollapse;
            Assert.assertFalse(errorCollapsableList.isEmpty(), "No collapsable icon present");
            List<WebElement> errorContentList = sparkAppPageObj.errorContents;
            Assert.assertFalse(errorContentList.isEmpty(), "No error contents in the error tab");

            //TODO check for specific error string in the content list.
            boolean foundErrorMsg = false;
            String msg = "";
            for (int c = 0; c < errorContentList.size(); c++) {
              String expectedErrorString = "Exception";
              String actualErrorString = errorContentList.get(c).getText();
              if (actualErrorString.contains(expectedErrorString)) {
                foundErrorMsg = true;
                msg = "Found the expected error message in the Errors Tab " +
                    "Expected = " + expectedErrorString + "Actual Error Message = " + actualErrorString;
                break;
              }
            }
            Assert.assertTrue(foundErrorMsg, "Expected Error message not present in the Error tab");
            logger.info(msg);
            for (int c = 0; c < errorCollapsableList.size(); c++) {
              MouseActions.clickOnElement(driver, errorCollapsableList.get(c));
            }
            test.log(LogStatus.PASS, "Errors tab is populated");
            break;
          case "Configuratio...":
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            waitExecuter.sleep(10000);
            String[] expectedKeyWords = {"METADATA", "MEMORY", "LIMIT", "RESOURCES", "CPU", "NET",
                "YARN", "DEPLOY"};
            List<WebElement> keyWordsList = sparkAppPageObj.configKeywords;
            Assert.assertFalse(keyWordsList.isEmpty(), "Keywords not found");
            String beforeResetProp = sparkAppPageObj.configPropNum.getText();
            int propNum = Integer.parseInt(beforeResetProp.split("\\s+")[0]);
            logger.info("Number of properties displayed by default are  " + propNum);

            //Verify if property key value is present:
            List<WebElement> propKeyList = sparkAppPageObj.configPropKey;
            List<WebElement> propValueList = sparkAppPageObj.configPropValue;
            logger.info("PropKey size = " + propKeyList.size() + " propVal size = " +
                propValueList.size());
            Assert.assertFalse((propKeyList.isEmpty() && propValueList.isEmpty()), "Key/Value is empty");
            Assert.assertEquals(propKeyList.size(), propValueList.size(), "One of the key/Value " +
                "is missing");

            //Verify the keywords are present and should be clickable
            for (int k = 0; k < keyWordsList.size(); k++) {
              String keyword = keyWordsList.get(k).getText();
              logger.info("Keyword Type is " + keyword);
              Assert.assertTrue(Arrays.asList(expectedKeyWords).contains(keyword),
                  "Keywords displayed on the UI doesnot match with the expected keywords");
              MouseActions.clickOnElement(driver, keyWordsList.get(k));
              //keyWordsList.get(k).click();
              waitExecuter.sleep(2000);
            }
            //Check RESET buttons sets default props
            MouseActions.clickOnElement(driver, sparkAppPageObj.resetButton);
            //  sparkAppPageObj.resetButton.click();
            waitExecuter.sleep(3000);
            String afterResetProp = sparkAppPageObj.configPropNum.getText();
            logger.info("No. of Properties displayed by default " + beforeResetProp + "\n " +
                "No. of Properties displayed after RESET " + afterResetProp);
            Assert.assertEquals(afterResetProp, beforeResetProp, "The properties have not been reset " +
                "to default");
            break;
          case "Logs":
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            // appsTabList.get(i).click();
            waitExecuter.sleep(10000);
            List<WebElement> executorNameList = sparkAppPageObj.logExecutorNames;
            Assert.assertFalse(executorNameList.isEmpty(), "No executors listed in the Logs Tab");
            List<WebElement> logCollapsableList = sparkAppPageObj.logElementCollapsable;
            Assert.assertFalse(logCollapsableList.isEmpty(), "No executors listed in the Logs Tab");
            try {
              int cnt = 0;
              for (int c = 0; c < logCollapsableList.size(); c++) {
                if (cnt != 0)
                  MouseActions.clickOnElement(driver, logCollapsableList.get(c));
                //  logCollapsableList.get(c).click();
                waitExecuter.sleep(5000);
                WebElement contents = sparkAppPageObj.logExecutorContents;
                Assert.assertTrue(contents.isDisplayed(), "No logs found");
                WebElement showFullLogs = sparkAppPageObj.showFullLogLink;
                logCollapsableList.get(c).click();
                cnt += 1;
              }
            } catch (NoSuchElementException ex) {
              throw new AssertionError("Caught exception while clicking the collapsable icon for insights.\n" +
                  ex.getMessage());
            }

            break;
          case "Tags":
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            //         appsTabList.get(i).click();
            waitExecuter.sleep(3000);
            List<WebElement> tagTableHeader = sparkAppPageObj.tagTableHeader;
            Assert.assertFalse(tagTableHeader.isEmpty(), "Tags header is not populated");
            List<WebElement> tagTableRows = sparkAppPageObj.tagTableRows;
            Assert.assertFalse(tagTableRows.isEmpty(), "Tage row  populated");
            for (int rows = 1; rows <= tagTableRows.size(); rows++) {
              for (int col = 1; col <= tagTableHeader.size(); col++) {
                String data = driver.findElement(By.xpath("//*[@id='appTagsTable']/tbody/tr[" + rows + "]" +
                    "/td[" + col + "]")).getText();
                logger.info("The data is " + data);
                Assert.assertNotSame("", data);
              }
            }
            test.log(LogStatus.PASS, "Tags tab is populated");
            break;
          case "Program":
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            waitExecuter.sleep(3000);
            List<WebElement> programDataList = sparkAppPageObj.programTabData;
            verifyAssertFalse(programDataList.isEmpty(), sparkAppPageObj, "The Programs data is not populated");
            break;
          case "Timings":
            Actions action = new Actions(driver);
            MouseActions.clickOnElement(driver, appsTabList.get(i));
            waitExecuter.sleep(3000);
            List<WebElement> subTabList = sparkAppPageObj.timingsSubTabs;
            Assert.assertFalse(subTabList.isEmpty(), "No sub tabs available");
            String[] expectedSubTabList = {"Task Time", "App Time"};
            String[] expectedTTLegendNames = {"Input Stages", "Output Stages", "Processing Stages"};
            String[] expectedATLegendNames = {"Queue Time", "Driver Time", "Job Time"};
            for (int t = 0; t < subTabList.size(); t++) {
              String subTabName = subTabList.get(t).getText();
              logger.info("The Timings subTab is " + subTabName);
              System.out.println("The Timings subTab is " + subTabName);
              Assert.assertTrue(Arrays.asList(expectedSubTabList).contains(subTabName),
                  "Tab list displayed in the UI doesnot match with the expected list of tabs");
              MouseActions.clickOnElement(driver, subTabList.get(t));
              waitExecuter.sleep(1000);
              String titleName = sparkAppPageObj.timingsTabTitle.getText();
              logger.info("The Timings subTabs Title is " + titleName);
              Assert.assertTrue(sparkAppPageObj.pieChart.isDisplayed(), "Piechart for Task Attempts" +
                  " is not displayed");
              if (subTabName.equals("Task Time")) {
                List<WebElement> legendNameList = sparkAppPageObj.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), sparkAppPageObj, "Empty legend list");
                for (int l = 0; l < legendNameList.size(); l++) {
                  String legendName = legendNameList.get(l).getText();
                  logger.info("The Legends for subTab " + subTabName + " is " + legendName);
                  System.out.println("The Legends for subTab " + subTabName + " is " + legendName);

                  Assert.assertTrue(Arrays.asList(expectedTTLegendNames).contains(legendName),
                      "The legends displayed in the UI for Task Time doesnot match to the expected list of legends");
                  waitExecuter.sleep(1000);
                  if (legendName.equals("Processing Stages")) {
                    WebElement ele = sparkAppPageObj.processingStage;
                    verifyTimingStages(action, ele, legendName);
                  } else if (legendName.equals("Input Stages")) {
                    WebElement ele = sparkAppPageObj.inputStage;
                    verifyTimingStages(action, ele, legendName);
                  } else {
                    WebElement ele = sparkAppPageObj.outputStage;
                    verifyTimingStages(action, ele, legendName);
                  }
                }
              } else {
                List<WebElement> legendNameList = sparkAppPageObj.legendNames;
                verifyAssertFalse(legendNameList.isEmpty(), sparkAppPageObj, "Empty legend list for APP TIME");
                for (int l = 0; l < legendNameList.size(); l++) {
                  String legendName = legendNameList.get(l).getText();
                  Assert.assertTrue(Arrays.asList(expectedATLegendNames).contains(legendName),
                      "The legends displayed in the UI for App Time doesnot match to the expected list of legends");
                  waitExecuter.sleep(2000);
                  if (legendName.equals("Driver Time")) {
                    WebElement ele = driver.findElement(By.xpath("//*[@class='highcharts-series-group']//*[name()='path' " +
                        "and  contains(@class, 'highcharts-drilldown-point')]"));
                    MouseActions.clickOnElement(driver, ele);
                    String[] expectedDriverLegends = {"FileCommit Time", "File Setup Time", "Others"};
                    List<WebElement> driverLegendNameList = sparkAppPageObj.legendNames;
                    verifyAssertFalse(driverLegendNameList.isEmpty(), sparkAppPageObj, "Empty legend list for DRIVER TIME");
                    for (int d = 0; d < driverLegendNameList.size(); d++) {
                      String driverLegend = driverLegendNameList.get(d).getText();
                      Assert.assertTrue(Arrays.asList(expectedDriverLegends).contains(driverLegend),
                          "The legends displayed in the UI for App Time does not match to the expected list of legends");
                    }

                  }
                }
              }
            }
        }
        break;
      }
    }
  }

  public void verifyTimingStages(Actions action, WebElement ele, String legendName) {
    try {
      MouseActions.clickOnElement(driver, ele);
      waitExecuter.sleep(1000);
      WebElement buttn = driver.findElement(By.xpath("//*[@class='highcharts-button-box']"));
      action.moveToElement(buttn).click().build().perform();
      waitExecuter.sleep(1000);
    } catch (NoSuchElementException e) {
      System.out.println("The element is not present for stage " + legendName);
    }
  }

  /**
   * Method to verify if the component tabs like navigation | Gantt chart | jobs is present
   * and contains data if job count is > 0
   * if validateCompData = true validate each component tab data
   * if validateExecutorTab = true validate jobs execution tabs data.
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
                  String data = driver.findElement(By.xpath("//*[@id='appNavigation-body']/" +
                      "tr[" + rows + "]/td[" + col + "]/span")).getText();
                  logger.info("The data is " + data);
                  Assert.assertNotSame("", data);
                }
              }
            }
          }
          validateStageAndStageData(navigationRows, navigationRowList, sparkPageObj, validateExecutorTab, validateStageTab);
          break;
        case 1:
          //The component is Gantt Chart ,click it and then verify the no. rows in the table
          Assert.assertEquals(tabName, "Gantt Chart", "Gantt Chart tab not present");
          MouseActions.clickOnElement(driver, componentList.get(j));
          //  componentList.get(j).click();
          List<WebElement> ganttChartTableRows = sparkPageObj.ganttChartTable;
          logger.info("No. of rows in Gantt Chart tables are " + ganttChartTableRows.size());
          if (validateCompData) {
            List<WebElement> headerList = sparkPageObj.ganttChartHeaders;
            Assert.assertFalse(headerList.isEmpty(), "No headers for Gantt Chart table for application");
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
   * Method to validate the stage table header and the data.
   * if validateExecutorTab = true, validate each jobs execution tabs data
   */
  public void validateStageAndStageData(int navigationRows, List<WebElement> navigationRowList,
                                        SparkAppsDetailsPageObject sparkPageObj, Boolean validateExecutorTab,
                                        Boolean validateStageTabs) {
    if (navigationRows > 0) {
      String[] expectedHeader = {"Stage ID", "Start Time", "Duration", "Tasks", "Shuffle Read",
          "Shuffle Write", "Input", "Output"};
      //click the jobId to sort it .
      driver.findElement(By.xpath("(//thead[@id='appNavigation-head'])/tr/th[1]")).click();
      for (int rows = 0; rows < navigationRows; rows++) {
        navigationRowList.get(rows).click();
        waitExecuter.sleep(2000);
        if (validateExecutorTab) {
          Assert.assertTrue(sparkPageObj.DAGData.isDisplayed(), "Dag data is not displayed ");
          logger.info("Dags data is displayed ");
          List<WebElement> rddBlockList = sparkPageObj.rddBlockList;
          Assert.assertFalse(rddBlockList.isEmpty(), "No DAGs data");
          logger.info("No. of RDD blocks in the flow chart is " + rddBlockList.size());
          for (int i = 0; i < rddBlockList.size(); i++) {
            Assert.assertTrue(rddBlockList.get(i).isDisplayed(), "FlowChart doesnot have the RDD blocks displayed");
          }
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
    //click the stageId to sort it
    driver.findElement(By.xpath("//*[@id='sparkStageNavigation-head']/tr/th[1]")).click();
    List<WebElement> stageRowList = sparkPageObj.stageRows;
    Assert.assertFalse(stageRowList.isEmpty(), "No stages displayed for JobId");
    for (int i = 0; i < stageRowList.size(); i++) {
      stageRowList.get(i).click();
      waitExecuter.sleep(1000);
      List<WebElement> stageTabsList = sparkPageObj.stagesTab;
      Assert.assertFalse(stageTabsList.isEmpty(), "No tabs displayed for selected stage");
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
              Assert.assertFalse(programDataList.isEmpty(), "Program data not found");
              WebElement sourceText = sparkPageObj.programSourceLinkText;
              String sourceStr = sourceText.getText();
              System.out.println("The Source string is " + sourceStr);
              int lineNo = Integer.parseInt(sourceStr.split(":")[2].trim());
              logger.info("LineNo to verify in the Programs tab is " + lineNo);
              System.out.println("LineNo to verify in the Programs tab is " + lineNo);
              MouseActions.clickOnElement(driver, sparkPageObj.programSourceLink);
              List<WebElement> programList = sparkPageObj.programTabData;
              verifyAssertFalse(programList.isEmpty(), sparkPageObj, sparkPageObj.programDataNotFound.getText());
              WebElement highlightedLine = driver.findElement(By.xpath("//*[@id=\"app-query\"]" +
                  "/div[contains(@data-range,'" + lineNo + "')]"));
              Assert.assertTrue(highlightedLine.isDisplayed(), "The line no from the stage source doesnot " +
                  "point to the same line in the source program file");

            } catch (NoSuchElementException ex) {
              throw new AssertionError("Programs tab got exception " + ex.getMessage());
            }
            break;
          case "Timeline":
            logger.info("Validating the stage tab Timeline");
            MouseActions.clickOnElement(driver, stageTabsList.get(i));
            waitExecuter.sleep(1000);
            String[] expectedHeaderList = {"ShuffleMap Input (KB)", "Shuffle Map Time(sec)", "ShuffleMap Output (KB)"
                , "Disk Bytes Spilled (KB)", "Memory Bytes Spilled (KB)", "Records Read (count)"};
            String[] expectedSubTabs = {"Timeline", "Selected Tass"};
            List<WebElement> headerlist = sparkPageObj.stagesTimelineHeader;
            Assert.assertFalse(headerlist.isEmpty(), "No header displayed");
            List<WebElement> barGraphList = sparkPageObj.timelineBarGraph;
            Assert.assertFalse(barGraphList.isEmpty(), "No bar graphs displayed");
            for (int h = 0; h < headerlist.size(); h++) {
              String headerName = headerlist.get(h).getText();
              logger.info("The header name is " + headerName);
              Assert.assertTrue(Arrays.asList(expectedHeaderList).contains(headerName),
                  "Header names displayed on the UI does not match with the expected headerList");
              Assert.assertTrue(barGraphList.get(h).isDisplayed(), "The bar graph for " +
                  "" + headerName + " is not displayed");
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
            Assert.assertFalse(stageTimingHeaderList.isEmpty(), "The headers in the timmings " +
                "tab not displayed");
            String[] expectedTimingHeaders = {"Stage Time Distribution", "IO Metrics",
                "Time Metrics"};
            List<WebElement> legendList = sparkPageObj.legendNames;
            Assert.assertFalse(legendList.isEmpty(), "No legends displayed");
            List<WebElement> graphList = sparkPageObj.timingGraphList;
            Assert.assertFalse(graphList.isEmpty(), "No graphs displayed");

            for (int s = 0; s < stageTimingHeaderList.size(); s++) {
              String stageTimingHeader = stageTimingHeaderList.get(s).getText();
              logger.info("The stageTimingHeader is " + stageTimingHeader);
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
  public void verifyRightPaneKpis(SparkAppsDetailsPageObject sparkPageObj) {
    List<WebElement> kpiList = sparkPageObj.rightPaneKpis;
    validateLeftPaneKpis(kpiList);
    List<WebElement> appKpis = sparkPageObj.rightPaneAppKpis;
    List<WebElement> appKpiVal = sparkPageObj.rightPaneAppKpiVal;
    Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
    Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
    for (int i = 0; i < appKpis.size(); i++) {
      Assert.assertNotSame("", appKpis.get(i).getText(), "Kpi text is empty");
      Assert.assertNotSame("", appKpiVal.get(i).getText(), "Kpi Value is empty");
      logger.info("Kpi Name = " + appKpis.get(i).getText() + " Value = " + appKpiVal.get(i).getText());
    }
  }

  /**
   * Verify that Left pane must be opened and should have KPIs listed
   * (start, end and duration are listed and should not be empty)
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
    appPageObj.getTypeFromTable.click();
    waitExecuter.sleep(5000);
    waitExecuter.waitUntilPageFullyLoaded();
    String headerAppId = sparkPageObj.getHeaderAppId.getText().trim();
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
    logger.info("Navigate to jobs tab from header");
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
    waitExecuter.sleep(1000);
    topPanelObj.jobs.click();
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    //Select cluster
    logger.info("Select Cluster: " + clusterId);
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
                                          SparkAppsDetailsPageObject sparkPageObj, ExtentTest test,
                                          Boolean verifyAppComp) {
    applicationsPageObject.expandStatus.click();
    int failedAppCnt = clickOnlyLink("Failed");
    logger.info("Failed App Cnt is " + failedAppCnt);
    if (failedAppCnt > 0) {
      verifyAppId(sparkPageObj, applicationsPageObject);
      List<WebElement> kpiList = sparkPageObj.leftPaneKPIList;
      validateLeftPaneKpis(kpiList);
      test.log(LogStatus.PASS, "All the Kpis (start, end and duration are listed )are" +
          " displayed and are not empty: ");

      /**There should be attempts tab under which attempts  for "failed"
       *    and "success" must be displayed in the form of bar graph for failed attempts.
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
        //Close apps details page
        logger.info("Caught exception " + e.getMessage());
        sparkPageObj.closeAppsPageTab.click();
      }

    } else {
      logger.info("There are no failed apps to navigate to");
      List<WebElement> kpiList = sparkPageObj.leftPaneKPIList;
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

    int appCount = appsDetailsPage.clickOnlyLink("Spark");
    //Clicking on the Spark app must go to apps detail page
    if (appCount > 0) {
      String headerAppId = appsDetailsPage.verifyAppId(sparkAppPageObj, applicationsPageObject);
      test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
      appsDetailsPage.verifyAppSummaryTabs(sparkAppPageObj, tabName, test);
    } else {
      logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
          "of 90 days");
    }
    //Close apps details page
    sparkAppPageObj.closeAppsPageTab.click();
  }

  /**
   * Method to validate the Load Diagnostic Action
   */
  public void verifyLoadDiagnosticAction(SparkAppsDetailsPageObject sparkAppPageObj) {
    WebElement actionMenu = sparkAppPageObj.loadAction;
    MouseActions.clickOnElement(driver, actionMenu);
    List<WebElement> elementList = sparkAppPageObj.loadActionList;
    verifyAssertFalse(elementList.isEmpty(), sparkAppPageObj, "No elements listed in the Action Menu Expected " +
        "(Load Diagnostic | Load Logs)");
    for (int i = 0; i < elementList.size(); i++) {
      if (elementList.get(i).getText().equals("Load Diagnostics")) {
        MouseActions.clickOnElement(driver, elementList.get(i));
        waitExecuter.sleep(1000);
        verifyAssertTrue(sparkAppPageObj.loadDiagnosticWin.isDisplayed(), sparkAppPageObj,
            "The load diagnostic window is not displayed");
        String expectedStr = "APPLICATION DIAGNOSTICS";
        String winHeader = sparkAppPageObj.loadDiagnosticWinHeader.getText();
        logger.info("The LoadDignostics window header is " + winHeader);
        System.out.println("The LoadDignostics window header is " + winHeader);
        verifyAssertTrue(winHeader.contains(expectedStr), sparkAppPageObj, "Expected Header String is not " +
            "displayed\n Expected = " + expectedStr + " Actual = " + winHeader);
        MouseActions.clickOnElement(driver, sparkAppPageObj.loadDiagnosticWinClose);
        MouseActions.clickOnElement(driver, actionMenu);
      }
    }
  }

  /**
   * Method to validate the Load Logs Action
   */
  public void verifyLoadLogsAction(SparkAppsDetailsPageObject sparkAppPageObj) {
    WebElement actionMenu = sparkAppPageObj.loadAction;
    MouseActions.clickOnElement(driver, actionMenu);
    List<WebElement> elementList = sparkAppPageObj.loadActionList;
    verifyAssertFalse(elementList.isEmpty(), sparkAppPageObj, "No elements listed in the Action Menu Expected " +
        "(Load Diagnostic | Load Logs)");
    for (int i = 0; i < elementList.size(); i++) {
      if (elementList.get(i).getText().equals("Load Logs")) {
        MouseActions.clickOnElement(driver, elementList.get(i));
        waitExecuter.sleep(1000);
        verifyAssertTrue(sparkAppPageObj.loadDiagnosticWin.isDisplayed(), sparkAppPageObj,
            "The load diagnostic window is not displayed");
        String expectedStr = "APPLICATION DIAGNOSTICS";
        String winHeader = sparkAppPageObj.loadDiagnosticWinHeader.getText();
        logger.info("The LoadDignostics window header is " + winHeader);
        System.out.println("The LoadDignostics window header is " + winHeader);
        verifyAssertTrue(winHeader.contains(expectedStr), sparkAppPageObj, "Expected Header String is not " +
            "displayed\n Expected = " + expectedStr + " Actual = " + winHeader);
        MouseActions.clickOnElement(driver, sparkAppPageObj.loadDiagnosticWinClose);
        MouseActions.clickOnElement(driver, actionMenu);
      }
    }
  }

  public void verifyAssertFalse(Boolean condition, SparkAppsDetailsPageObject sparkAppPageObj, String msg) {
    try {
      Assert.assertFalse(condition, msg);
    } catch (Throwable e) {
      //Close apps details page
      sparkAppPageObj.closeAppsPageTab.click();
      throw new AssertionError(msg + e.getMessage());
    }
  }

  public void verifyAssertTrue(Boolean condition, SparkAppsDetailsPageObject sparkAppPageObj, String msg) {
    try {
      Assert.assertTrue(condition, msg);
    } catch (Throwable e) {
      //Close apps details page
      sparkAppPageObj.closeAppsPageTab.click();
      throw new AssertionError(msg + e.getMessage());
    }
  }

}
