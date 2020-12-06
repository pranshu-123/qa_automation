package com.qa.scripts.clusters.elk;

import com.qa.constants.DatePickerConstants;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ELKPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;
  private DatePicker datePicker;
  String xAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-xaxis-labels')]/*[name()='text']/*[name()='tspan']";
  String yAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-yaxis-labels')]/*[name()='text']";

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
    Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_1_HOUR),
        "Last 1 Hour is not present in datepicker filter ");
    test.log(LogStatus.PASS, "Verified 'Last 1 hour' option in date picker filter.");
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
    waitExecuter.waitUntilElementClickable(elkPageObject.clusterDropDown);
    waitExecuter.sleep(2000);
    MouseActions.clickOnElement(driver, elkPageObject.clusterDropDown);
    waitExecuter.sleep(2000);
    List<WebElement> elkClusterList = elkPageObject.ELKClusters;
    Assert.assertFalse(elkClusterList.isEmpty(), "The drop down list for kafka cluster is empty");
    String clustername = elkClusterList.get(0).getText();
    MouseActions.clickOnElement(driver, elkClusterList.get(0));

    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast7Days();
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
      Assert.assertTrue(Arrays.asList(expectedKPIList).contains(kpiName), "The kpi: [" + kpiName + "] " +
          "is not displayed in the UI");
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
        Assert.assertEquals(kpiValue, "Green", "Expected value: Green  Actual value: " + kpiValue);
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
}
