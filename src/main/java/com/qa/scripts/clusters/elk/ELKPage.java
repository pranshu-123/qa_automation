package com.qa.scripts.clusters.elk;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.constants.DatePickerConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class ELKPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;
  private DatePicker datePicker;
  public UserActions userActions;
  String xAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-xaxis-labels')]/*[name()='text']/*[name()='tspan']";
  String yAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-yaxis-labels')]/*[name()='text']";
  //String tablePath = "//div[contains(@class,'nodes-table-row')]/div[1]//table[@class='component-data-tables']//tbody";
  String tablePath = "//*[@id='custom-tbl']//table/tbody";

  Logger logger = Logger.getLogger(ELKPage.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public ELKPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    datePicker = new DatePicker(driver);
    this.driver = driver;
  }

  /***
   * Method to verify Elastic Search date range
   */
  public void verifyDateRange(List<String> calendarRanges, ExtentTest test) {
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_2_HOUR),
        "Last 2 Hour is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 2 hour' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_6_HOUR),
        "Last 6 Hour is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 6 hour' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_12_HOUR),
        "Last 12 Hour is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 12 hour' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.TODAY),
        "Today is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Today' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_1_HOUR),
            "Last 1 Hour is not present in datepicker filter ");
    test.log(LogStatus.PASS, "Verified 'Last 1 hour' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.YESTERDAY),
        "Yesterday is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Yesterday' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_7_DAYS),
        "Last 7 Day is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 7 days' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_30_DAYS),
        "Last 30 Days is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 30 days' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_90_DAYS),
        "Last 90 days is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last 90 days' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_MONTH),
        "Last Month is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Last Month' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.THIS_MONTH),
        "This Month is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'This Month' option in date picker filter.");
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.CUSTOM_RANGE),
        "Custom Range is not present in datepicker filter");
    test.log(LogStatus.PASS, "Verified 'Custom Range' option in date picker filter.");
  }

  /***
   * Method to verify Elastic Search cluster name in tab
   */
  public void verifyClusterName(ELKPageObject elkPageObject) {
    String expectedClusterName = verifyClusterDropDown(elkPageObject);
    String clusterName = elkPageObject.ESHeaderTab.getText();
    logger.info("The cluster name in the tab is " + clusterName + " Expected Cluster name is " + expectedClusterName);
    Assert.assertTrue(clusterName.contains(expectedClusterName), " Clustername does not match\n" +
        " Expected: [" + expectedClusterName + "] Actual: [" + clusterName + "]");
  }

  /***
   * Method to verify Elastic Search cluster drop down
   */
  public String verifyClusterDropDown(ELKPageObject elkPageObject) {
    waitExecuter.sleep(4000);
    MouseActions.clickOnElement(driver, elkPageObject.clusterDropDown);
    waitExecuter.sleep(2000);
    List<WebElement> elkClusterList = elkPageObject.ELKClusters;
    Assert.assertFalse(elkClusterList.isEmpty(), "The drop down list for kafka cluster is empty");
    String clustername = elkClusterList.get(0).getText();
    MouseActions.clickOnElement(driver, elkClusterList.get(0));
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilPageFullyLoaded();

    return clustername;
  }

  /***
   * Method to verify the kpis of Elastic Search Cluster
   */
  public void verifyESClusterKPIs(ELKPageObject elkPageObject) {
    List<WebElement> kpiList = elkPageObject.ESKpiNames;
    List<WebElement> kpiValueList = elkPageObject.ESKpiValues;
    Assert.assertFalse(kpiList.isEmpty() || kpiValueList.isEmpty(), "Elastic Search cluster kpi list is empty");
    String[] expectedKPIList = {"Status", "Nodes", "Indices", "Total Shards", "Unassigned Shards", "Documents",
        "Successful Shards", "Active Shards", "Initializing Shards", "Relocating Shards"};
    for (int i = 0; i < kpiList.size(); i++) {
      String kpiName = kpiList.get(i).getText();
      String kpiValue = kpiValueList.get(i).getText();
      logger.info("KPI Name: " + kpiName + "\n KPI Value: " + kpiValue);
//      Assert.assertTrue(Arrays.asList(expectedKPIList).contains(kpiName), "The kpi: [" + kpiName + "] " +
//          "is not displayed in the UI");
      boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
      Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "No values for kpi " + kpiName +
          "displayed \n Expected: AlphaNumeric value Actual: [" + kpiValue + "]");
    }
  }

  /***
   * Method to verify the kpis of Elastic Search Cluster
   */
  public void verifyESKPIColors(ELKPageObject elkPageObject) {
    List<WebElement> kpiList = elkPageObject.ESKpiNames;
    List<WebElement> kpiValueList = elkPageObject.ESKpiValues;
    Assert.assertFalse(kpiList.isEmpty() || kpiValueList.isEmpty(), "Elastic Search cluster kpi list is empty");
    for (int i = 0; i < kpiList.size(); i++) {
      String kpiName = kpiList.get(i).getText();
      if (kpiName.equals("Status")) {
        String kpiValue = kpiValueList.get(i).getText();
        logger.info("KPI Name: " + kpiName + "\n KPI Value: " + kpiValue);
        boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "No values for kpi " + kpiName +
            "displayed \n Expected: AlphaNumeric value Actual: [" + kpiValue + "]");
       // Assert.assertEquals(kpiValue, "Green", "Expected value: Green  Actual value: " + kpiValue);
      }
    }
  }

  /***
   * Method to verify Elastic Search node table data
   */
  public void verifyESNodeTable(ELKPageObject elkPageObject) {
    List<WebElement> ESColList = elkPageObject.ESCol;
    Assert.assertFalse(ESColList.isEmpty(), " No columns displayed for ES Nodes metrics");
    List<WebElement> ESRowList = elkPageObject.ESRows;
    Assert.assertFalse(ESRowList.isEmpty(), "No data displayed for ES Nodes metrics table");
    for (int row = 0; row < ESRowList.size(); row++) {
      for (int col = 0; col < ESColList.size(); col++) {
        String colName = ESColList.get(col).getText();
        logger.info("The ES colName is: " + colName);
        WebElement rowData = driver.findElement(By.xpath("//tbody[@id='elasticSearchNodesTableList-body']" +
            "/tr[" + (row + 1) + "]/td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        //Check if data has only special charaters
        boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(onlySpecialChars, "Expected some alpha numeric value for column " + colName +
            " But got: " + rowData.getText());
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /***
   * Method to navigate to Indices Tab
   */
  public void navigateToIndicesTab(ELKPageObject elkPageObject) {
    MouseActions.clickOnElement(driver, elkPageObject.ESTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    verifyClusterDropDown(elkPageObject);
    MouseActions.clickOnElement(driver, elkPageObject.indicesTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
  }

  public void navigateToPipilineTab(ELKPageObject elkPageObject) {
    MouseActions.clickOnElement(driver, elkPageObject.logstashTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    MouseActions.clickOnElement(driver, elkPageObject.pipelineTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilPageFullyLoaded();
  }

  /***
   * Method to verify Elastic Search Indices Table Data
   */
  public void verifyESIndicesTableData(ELKPageObject elkPageObject) {
    List<WebElement> colList = elkPageObject.ESCol;
    List<WebElement> rowList = elkPageObject.IndicesRows;
    Assert.assertFalse(colList.isEmpty(), "No columns for topic table");
    Assert.assertFalse(rowList.isEmpty(), "No data found for the topic tables ");
    for (int row = 0; row < rowList.size(); row++) {
      for (int col = 0; col < colList.size() - 1; col++) {
        String colName = colList.get(col).getText();
        logger.info("The colName is: " + colName);
        WebElement rowData = driver.findElement(By.xpath("//tbody[@id='elasticSearchIndexTable-body']/tr/" +
            "td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        //Check if data has only special charaters
        boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(onlySpecialChars, "Expected value for column " + colName + " But got: " + rowData.getText());
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /***
   * Method to verify ES indices specific metrics graph.
   */
  public void verifyPerIndicesMetricsGraph(ELKPageObject elkPageObject) {
    List<WebElement> indicesList = elkPageObject.IndicesRows;
    Assert.assertFalse(indicesList.isEmpty(), "No indices listed in the Indices table");
    for (int i = 0; i < indicesList.size(); i++) {
      MouseActions.clickOnElement(driver, indicesList.get(i));
      waitExecuter.waitUntilPageFullyLoaded();
      waitExecuter.sleep(2000);
      List<WebElement> metricsKpiList = elkPageObject.IndicesMetric;
      List<WebElement> metricsKpiHeaderList = elkPageObject.IndiceMetricsHeader;
      List<WebElement> metricsKpiFooterList = elkPageObject.IndiceMetricsFooter;
      List<WebElement> metricsKpiGraphList = elkPageObject.IndiceMetricsGraph;
      Assert.assertFalse(metricsKpiList.isEmpty(), "Metrics for cluster is empty");
      verifyGraph(metricsKpiList, metricsKpiHeaderList, metricsKpiFooterList, metricsKpiGraphList);
    }
  }

  public void verifyIndicesSearchOption(ELKPageObject elkPageObject) {
    String searchString = elkPageObject.IndicesName.getText();
    logger.info("The search string is " + searchString);
    elkPageObject.IndicesSearchBox.sendKeys(searchString);
    waitExecuter.sleep(1000);
    List<WebElement> indicesRowList = elkPageObject.IndicesRows;
    Assert.assertFalse(indicesRowList.isEmpty(), "There are no rows matching to string " + searchString);
  }

  /***
   * Method to verify ES cluster node metrics graph.
   */
  public void verifyNodeGraphs(KafkaPageObject kafkaPageObject) {
    List<WebElement> metricsKpiList = kafkaPageObject.kafkaMetrics;
    List<WebElement> metricsKpiHeaderList = kafkaPageObject.kafkaMetricsHeader;
    List<WebElement> metricsKpiFooterList = kafkaPageObject.kafkaMetricsFooter;
    List<WebElement> metricsKpiGraphList = kafkaPageObject.kafkaMetricsGraph;
    Assert.assertFalse(metricsKpiList.isEmpty(), "Metrics for cluster is empty");
    verifyGraph(metricsKpiList, metricsKpiHeaderList, metricsKpiFooterList, metricsKpiGraphList);
  }

  /***
   * Generic graph validation method for ELK.
   */
  public void verifyGraph(List<WebElement> metricsKpiList, List<WebElement> metricsKpiHeaderList,
                          List<WebElement> metricsKpiFooterList, List<WebElement> metricsKpiGraphList) {
    KafkaPage kafkaPage = new KafkaPage(driver);
    for (int i = 0; i < metricsKpiList.size(); i++) {
      String graphId = "elasticsearchGraph" + i;
      String xAxisPath = "//*[@id='" + graphId + "']" + xAxis;
      String yAxisPath = "//*[@id='" + graphId + "']" + yAxis;
      String metricsName = metricsKpiHeaderList.get(i).getText();
      Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
      logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
      Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
      logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
      Assert.assertTrue(metricsKpiFooterList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
      logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
      kafkaPage.verifyAxis(xAxisPath, "X-Axis");
      kafkaPage.verifyAxis(yAxisPath, "Y-Axis");
    }
  }

  /***
   * Method to verify ES node specific metrics graph.
   */
  public void verifyNodeSpecificGraphs(ELKPageObject elkPageObject, KafkaPageObject kafkaPageObject) {
    List<WebElement> nodeList = elkPageObject.ESRows;
    Assert.assertFalse(nodeList.isEmpty(), "No nodes listed for Elastic Search Cluster");
    for (int i = 0; i < nodeList.size(); i++) {
      MouseActions.clickOnElement(driver, nodeList.get(i));
      waitExecuter.waitUntilPageFullyLoaded();
      waitExecuter.sleep(2000);
      verifyNodeGraphs(kafkaPageObject);
    }
  }

  /***
   * Method to verify logstash pipeline and verify all elements exists.
   */
  public void verifyLogstashPipeline(ELKPageObject elkPageObject) {
    WebElement kpiBox = elkPageObject.logstashKpiBox;
    WebElement tableRow = elkPageObject.tableRowBox;
    WebElement overview = elkPageObject.overViewBox;
    WebElement nodePipelineList = elkPageObject.nodePipeline;
    Assert.assertTrue(kpiBox.isDisplayed() && tableRow.isDisplayed() && overview.isDisplayed() &&
        nodePipelineList.isDisplayed(), " All elements not displayed in the UI");
  }

  /* Get all InefficientApp application Table row count */
  public int getTotalCountOfNodesTblRow(ELKPageObject elkPageObject) {
    int countInefficientAppsTblRow = elkPageObject.nodesRows.size();
    if (countInefficientAppsTblRow > 0) {
      return countInefficientAppsTblRow;
    }
    return countInefficientAppsTblRow;
  }

  /**
   * Method to verify column sorting options for Broker Metric table
   */
  public void clickOnNodesTblColSort(ELKPageObject elkPageObject) {
    int countOfHeaderToSort = elkPageObject.nodesColSortingIcon.size();
    int countInefficientAppsTblRowBeforeClick = getTotalCountOfNodesTblRow(elkPageObject);
    if (countOfHeaderToSort > 0) {
      for (int i = 0; i < countOfHeaderToSort; i++) {
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, elkPageObject.nodesColSortingIcon.get(i));
        waitExecuter.sleep(3000);
        int countInefficientAppsTblRow = elkPageObject.nodesRows.size();
        Assert.assertEquals(countInefficientAppsTblRow, countInefficientAppsTblRowBeforeClick,
                "InefficientAppTableRow count mismatch before and after sort");
      }
    }
  }


  /***
   * Method to verify logstash KPis and its value.
   */
  public void verifyLogstashKPIs(ELKPageObject elkPageObject, String expectedKpi) {
    List<WebElement> kpiList = elkPageObject.logstashKpis;
    List<WebElement> kpiNames = elkPageObject.logstashKpiName;
    List<WebElement> kpiValues = elkPageObject.logstashKpiValue;
    List<WebElement> nodeList = elkPageObject.logstashTableRows;
    Assert.assertFalse(kpiList.isEmpty(), "No Kpis listed for logstash");
    Assert.assertFalse(nodeList.isEmpty(), "No nodes are displayed ");
    for (int i = 0; i < kpiList.size(); i++) {
      String kpiName = kpiNames.get(i).getText().trim();
      if (kpiName.equals(expectedKpi)) {
        String kpiValue = kpiValues.get(i).getText().trim();
        boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "No values for kpi " + kpiName +
            "displayed \n Expected: AlphaNumeric value Actual: [" + kpiValue + "]");
        if (kpiName.equals("Events Received") || kpiName.equals("Events Emitted")) {
          int colId;
          if (kpiName.equals("Events Received"))
            colId = 3;
          else
            colId = 4;
          logger.info("SP: KpiName = "+ kpiName + " KpiValue = "+ kpiValue);
          String regex = "[a-zA-Z]";
          int expectedEventData = getAvgEventData(elkPageObject, colId);
          int actualEventData = Integer.parseInt(kpiValue.split(regex)[0]);
          logger.info("Expected event data: " + expectedEventData + " Actual Event data: " + actualEventData);
          Assert.assertEquals(expectedEventData, actualEventData, "The average number of events flowing into " +
              "all nodes over the selected time range is not equal\n Expected: " + expectedEventData + " Actual: " + actualEventData);
        } else if (kpiName.equals("Memory")) {
          float expectedMemoryData = getSumOfMemory(elkPageObject, 2);
          String regex = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
          String onlyMemoryVal = kpiValue.split("/")[0].trim().split(regex)[0];
          float actualMemoryData = 0L;
          if (onlyMemoryVal.contains("."))
            actualMemoryData += Float.parseFloat(onlyMemoryVal);
          else
            actualMemoryData += Integer.parseInt(onlyMemoryVal);
          logger.info("Expected memory: " + expectedMemoryData + " Actual memory: " + actualMemoryData);
          float mem_diff = Math.abs(expectedMemoryData - actualMemoryData);
          logger.info("Difference of memory is "+ mem_diff);
          //TODO verify the behaviour
//          Assert.assertTrue(mem_diff > 50.0, "The sum of the JVM Heap Used Column of the " +
//              "nodes table is not equal to the Memory KPI value \n Expected: " + expectedMemoryData +
//                  " Actual: " + actualMemoryData);
//          Assert.assertEquals(expectedMemoryData, actualMemoryData, " The sum of the JVM Heap Used Column of the " +
//              "nodes table is not equal to the Memory KPI value \n Expected: " + expectedMemoryData +
//              " Actual: " + actualMemoryData);
        }
      }
    }
  }

  /***
   * Method to validate Kibana metrics graph .
   */
  public void verifyKibanaGraph(ELKPageObject elkPageObject) {
    List<WebElement> metricsList = elkPageObject.kibanaMetricsList;
    List<WebElement> headerList = elkPageObject.kibanaGraphHeader;
    List<WebElement> footerList = elkPageObject.kibanaGraphFooter;
    List<WebElement> graphList = elkPageObject.kibanaGraph;

    for (int i = 0; i < metricsList.size(); i++) {
      String metricsName = headerList.get(i).getText();
      Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
      logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
      Assert.assertTrue(graphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
      logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
      Assert.assertTrue(footerList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
      logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
    }
  }

  /***
   * Method to validate logstash metrics JVM heap used, events received, events emited and events filtered
   */
  public void verifyLogstashNodesTableData(ELKPageObject elkPageObject) {
    List<WebElement> rowList = elkPageObject.logstashTableRows;
    List<WebElement> colList = elkPageObject.logstashNodeColHeader;
    Assert.assertFalse(rowList.isEmpty(), "No data in the nodes table");
    for (int row = 0; row < rowList.size(); row++) {
      for (int col = 0; col < colList.size() - 1; col++) {
        String colName = colList.get(col).getText();
        WebElement rowData = driver.findElement(By.xpath(tablePath +
            "/tr[" + (row + 1) + "]/td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        //Check if data has only special charaters
        boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(onlySpecialChars, "Expected some alpha numeric value for column " + colName +
            " But got: " + rowData.getText());
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /***
   * Method to validate logstash metrics graph .
   */
  public void verifyLogstashGraph(ELKPageObject elkPageObject) {
    List<WebElement> metricsList = elkPageObject.logstashMetricsList;
    List<WebElement> headerList = elkPageObject.logstashGraphHeader;
    List<WebElement> footerList = elkPageObject.logstashGraphFooter;
    List<WebElement> graphList = elkPageObject.logstashGraph;

    for (int i = 0; i < metricsList.size(); i++) {
      String metricsName = headerList.get(i).getText();
      Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
      logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
      Assert.assertTrue(graphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
      logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
      Assert.assertTrue(footerList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
      logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
    }
  }

  public void verifyPerNodeMetricsGraph(ELKPageObject elkPageObject) {
    List<WebElement> rowList = elkPageObject.logstashTableRows;
    for (int row = 0; row < rowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath(tablePath + "/tr[" + (row + 1) + "]/td[" + 1 + "]/span"));
      MouseActions.clickOnElement(driver, rowData);
      verifyLogstashGraph(elkPageObject);
    }
  }

  /***
   * Method to validate that all the pipelines are listed  under pipeline tab.
   */
  public void verifyPipelineTab(ELKPageObject elkPageObject) {
    List<WebElement> rowList = elkPageObject.pipelineTableRows;
    List<WebElement> kpiList = elkPageObject.logstashKpis;
    List<WebElement> kpiNames = elkPageObject.logstashKpiName;
    List<WebElement> kpiValues = elkPageObject.logstashKpiValue;
    int expectedPipelineCnt = 0;
    int actualPipilineCnt = rowList.size();
    for (int i = 0; i < kpiList.size(); i++) {
      if (kpiNames.get(i).getText().equals("Pipelines")) {
        expectedPipelineCnt = Integer.parseInt(kpiValues.get(i).getText().trim());
      }
    }
    logger.info("The expected pipeline count: " + expectedPipelineCnt + " Actual pipeline count: " + actualPipilineCnt);
    Assert.assertEquals(expectedPipelineCnt, actualPipilineCnt, "The pipelines are missing \n Expected pipelineCnt: "
        + expectedPipelineCnt + " Actual pipelineCnt: " + actualPipilineCnt);
  }

  public void verifyPipelineTable(ELKPageObject elkPageObject) {
    verifyLogstashNodesTableData(elkPageObject);
  }

  public void verifyAssociatedPipelineNodeList(ELKPageObject elkPageObject) {
    List<WebElement> rowList = elkPageObject.pipelineTableRows;
    for (int row = 1; row < rowList.size(); row++) {
      WebElement nodes = driver.findElement(By.xpath(tablePath + "/tr[" + (row) + "]/td[" + 5 + "]/span"));
      int asssociatedNodeCnt = Integer.parseInt(nodes.getText().trim());
      String pipelineName = driver.findElement(By.xpath(tablePath + "/tr[" + (row) + "]/td[" + 1 + "]/span")).getText();
      logger.info("Pipeline " + pipelineName + " has " + asssociatedNodeCnt + " node associated with it");
      Assert.assertTrue(asssociatedNodeCnt > 0, "No nodes associated with pipeline " + pipelineName);
      MouseActions.clickOnElement(driver, nodes);
      waitExecuter.sleep(1000);
      List<WebElement> nodeRowList = elkPageObject.associatedNodeTableRows;
      Assert.assertFalse(nodeRowList.isEmpty(), "No rows displayed for the associated nodes in the table");
      logger.info("No. associated nodes: " + asssociatedNodeCnt + " Entry in node table: " + nodeRowList.size());
      Assert.assertEquals(asssociatedNodeCnt, nodeRowList.size(), " The number of nodes in the pipeline table is" +
          " not equal to the nodes displayed in the associated nodes tables.");
    }
  }

  public void verifyPipelineSpecificKPIs(ELKPageObject elkPageObject) {
    List<WebElement> rowList = elkPageObject.pipelineTableRows;

    for (int row = 0; row < rowList.size(); row++) {
      WebElement pipelineName = driver.findElement(By.xpath(tablePath + "/tr[" + (row + 1) + "]/td[" + 1 + "]/span"));
      MouseActions.clickOnElement(driver, pipelineName);
      waitExecuter.sleep(2000);
      List<WebElement> kpiList = elkPageObject.pipelineKpiList;
      Assert.assertFalse(kpiList.isEmpty(), "No kpis listed for pipleline " + pipelineName);
      List<WebElement> kpiNames = elkPageObject.perPipelineKpiName;
      List<WebElement> kpiValues = elkPageObject.perPipelineKpiValue;
      for (int i = 0; i < kpiList.size(); i++) {
        String kpiValue = kpiValues.get(i).getText().trim();
        String kpiName = kpiNames.get(i).getText().trim();
        boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
        logger.info("Pipeline " + pipelineName.getText() + " has the following kpis \n" +
            "Kpi Name: [" + kpiName + "] Kpi Vale: [" + kpiValue + "]");
        Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "No values for kpi " + kpiName +
            "displayed \n Expected: AlphaNumeric value Actual: [" + kpiValue + "]");
      }
    }
  }

  public void verifyPipelineMetricGraphs(ELKPageObject elkPageObject) {
    verifyLogstashGraph(elkPageObject);
  }

  /***
   * Method to get average number of events flowing out of all nodes .
   */
  public int getAvgEventData(ELKPageObject elkPageObject, int colId) {
    List<WebElement> rowList = elkPageObject.logstashTableRows;
    int numNodes = rowList.size();
    int eventsCnt = 0;
    double eventsCnt_d = 0.0;
    for (int row = 0; row < rowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath(tablePath + "/tr[" + (row + 1) + "]/td[" + colId + "]/span"));
      //Check if data has only special charaters
      boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
      Assert.assertFalse(onlySpecialChars, "Expected some alpha numeric value  But got: " + rowData.getText());
      String raw_str = rowData.getText().replaceAll("[a-zA-Z]", "");
      logger.info("SP: raw_str "+ raw_str);
      if (raw_str.contains(".")) {
        eventsCnt_d += Double.parseDouble(raw_str.trim());
        logger.info("SP: eventsCnt_d "+ eventsCnt_d );
      }
      else {
        eventsCnt += Integer.parseInt(raw_str.trim());
        logger.info("SP: eventsCnt "+ eventsCnt );
      }
    }
    logger.info("Sum of eventdata= " + (eventsCnt + eventsCnt_d) + " Number of nodes = " + numNodes);
    return ((int)(eventsCnt + eventsCnt_d) )/ numNodes;
  }

  /**
   * Method to get the sum of the JVM Heap Used Column from nodes table.
   */
  public float getSumOfMemory(ELKPageObject elkPageObject, int colId) {
    float memorySum = 0L;
    List<WebElement> rowList = elkPageObject.logstashTableRows;
    for (int row = 0; row < rowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath(tablePath + "/tr[" + (row + 1) + "]/td[" + colId + "]/span"));
      //Check if data has only special charaters
      logger.info("SP:rowData = "+ rowData.getText().trim());
      boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
      Assert.assertFalse(onlySpecialChars, "Expected some alpha numeric value  But got: " + rowData.getText());
      String onlyMemValue = rowData.getText().trim().split("\\s")[0].trim();
      logger.info("Memory used = " + onlyMemValue);
      if (onlyMemValue.contains("."))
        memorySum += Float.parseFloat(onlyMemValue);
      else
        memorySum += Integer.parseInt(onlyMemValue);
    }
    logger.info("Sum of memory used = " + memorySum);
    return memorySum;
  }

  public void verifyKibanaMetricGraphs(ELKPageObject elkPageObject) {
    verifyKibanaGraph(elkPageObject);
  }

  public void verifyKibanaKPIs(ELKPageObject elkPageObject){
    verifyESClusterKPIs(elkPageObject);
    verifyESKPIColors(elkPageObject);
  }
}
