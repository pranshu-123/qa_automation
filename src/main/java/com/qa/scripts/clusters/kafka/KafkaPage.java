package com.qa.scripts.clusters.kafka;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class KafkaPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;

  Logger logger = Logger.getLogger(KafkaPage.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public KafkaPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    this.driver = driver;
  }

  /***
   * Method to Navigate to Kafka tab and then to broker tab
   */
  public void navigateToBrokerTab(KafkaPageObject kafkaPageObject) {
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    verifyClusterDropDown(kafkaPageObject);
    MouseActions.clickOnElement(driver, kafkaPageObject.brokerTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
  }

  /***
   * Method to verify kafka cluster name in tab
   */
  public void verifyClusterName(KafkaPageObject kafkaPageObject) {
    String expectedClusterName = verifyClusterDropDown(kafkaPageObject);
    String clusterName = kafkaPageObject.kafkaHeaderTab.getText();
    logger.info("The cluster name in the tab is " + clusterName + " Expected Cluster name is " + expectedClusterName);
    Assert.assertTrue(clusterName.contains(expectedClusterName), " Clustername does not match\n" +
        " Expected: [" + expectedClusterName + "] Actual: [" + clusterName + "]");
  }

  /***
   * Method to verify the kafka cluster dropdown
   */
  public String verifyClusterDropDown(KafkaPageObject kafkaPageObject) {
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaClusterDropDown);
    waitExecuter.sleep(2000);
    List<WebElement> kafkaClusterList = kafkaPageObject.kafkaClusters;
    Assert.assertFalse(kafkaClusterList.isEmpty(), "The drop down list for kafka cluster is empty");
    String clustername = kafkaClusterList.get(0).getText();
    MouseActions.clickOnElement(driver, kafkaClusterList.get(0));
    return clustername;
  }

  /***
   * Method to verify the kpis of a connected kafka cluster
   */
  public void verifyKafkaClusterKPIs(KafkaPageObject kafkaPageObject, String expectedKPIName) {
    List<WebElement> kpiList = kafkaPageObject.kafkaClusterKPIs;
    List<WebElement> kpiValueList = kafkaPageObject.kafkaClusterKPIValues;
    Assert.assertFalse(kpiList.isEmpty() || kpiValueList.isEmpty(), "Kafka cluster kpi list is empty");
    String[] expectedKPIList = {"# Under Replicated Partitions", "Offline Partitions", "Controller", "Bytes In per Sec",
        "Bytes Out per Sec", "Messages In per Sec", "Total Fetch Requests per Sec"};
    for (int i = 0; i < kpiList.size(); i++) {
      String kpiName;
      if (!expectedKPIName.isEmpty())
        kpiName = expectedKPIName;
      else
        kpiName = kpiList.get(i).getText();
      String kpiValue = kpiValueList.get(i).getText();
      logger.info("KPI Name: " + kpiName + "\n KPI Value: " + kpiValue);
      Assert.assertTrue(Arrays.asList(expectedKPIList).contains(kpiName), "The kpi: [" + kpiName + "] " +
          "is not displayed in the UI");
      Assert.assertFalse(kpiValue.isEmpty(), "No values for kpi " + kpiName + "displayed");
    }
  }

  /***
   * Method to verify the Kafka Metrics KPI Graphs of a connected kafka cluster
   */
  public void verifyKafkaKPIGraphs(KafkaPageObject kafkaPageObject, String expectedMetricsName) {
    String[] metricsNameList = {"Bytes In per Second", "Bytes Out per Second", "Messages In per Second", "Total Fetch Requests per Second",
        "# Under Replicated Partitions", "# Active Controller Trend", "Request Handler Idle Ratio Average per Minute", "# Partition Count",
        "# Leader Partition Count", "# Offline Partition Count", "Fetch Total Time, 99th Percentile", "Produce Total Time, 99th Percentile",
        "# Fetch Requests per Sec", "# Produce Requests per Sec", "Produce Purgatory Size"};
    List<WebElement> metricsKpiList = kafkaPageObject.kafkaMetrics;
    List<WebElement> metricsKpiHeaderList = kafkaPageObject.kafkaMetricsHeader;
    List<WebElement> metricsKpiFooterList = kafkaPageObject.kafkaMetricsFooter;
    List<WebElement> metricsKpiGraphList = kafkaPageObject.kafkaMetricsGraph;
    Assert.assertFalse(metricsKpiList.isEmpty(), "Metrics for kafka cluster is empty");
    for (int i = 0; i < metricsKpiList.size(); i++) {
      String metricsName = metricsKpiHeaderList.get(i).getText();
      logger.info("Metrics Name: " + metricsName);
      if (metricsName.equals(expectedMetricsName)) {
        Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
        logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
        Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
        logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
        Assert.assertTrue(metricsKpiFooterList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
        logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
      }
    }
  }

  /***
   * Method to verify the info msg for kafka metrics.
   */
  public void verifyMetricsInfoMsg(KafkaPageObject kafkaPageObject) {
    String infoMsg = kafkaPageObject.metricsTitle.getText();
    logger.info("The metrics info msg is: " + infoMsg);
    Assert.assertTrue(kafkaPageObject.metricsTitle.isDisplayed(), "The Metrics info msg is not displayed on the UI");
  }

  /***
   * Method to verify topic records.
   */
  public void verifyTopicRecords(KafkaPageObject kafkaPageObject) {
    List<WebElement> colList = kafkaPageObject.topicTableCol;
    List<WebElement> rowList = kafkaPageObject.topicTableRowData;
    List<WebElement> rowDataList = kafkaPageObject.topicTableRowData;
    Assert.assertFalse(colList.isEmpty(), "No columns for topic table");
    Assert.assertFalse(rowList.isEmpty(), "No data found for the topic tables ");
    for (int row = 1; row <= rowList.size(); row++) {
      for (int col = 1; col <= colList.size(); col++) {
        String colName = colList.get(col).getText();
        logger.info("The colName is: " + colName);
        WebElement rowData = driver.findElement(By.xpath("//tbody/tr[" + row + "]/td[" + col + "]"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /***
   * Method to verify broker metrics.
   */
  public void verifyBrokerMetrics(KafkaPageObject kafkaPageObject) {
    List<WebElement> brokerColList = kafkaPageObject.brokerCol;
    Assert.assertFalse(brokerColList.isEmpty(), " No columns displayed for broker metrics");
    List<WebElement> brokerRowList = kafkaPageObject.brokerRows;
    Assert.assertFalse(brokerRowList.isEmpty(), "No data displayed for broker metrics table");
    for (int col = 0; col < brokerColList.size(); col++) {
      String colName = brokerColList.get(col).getText();
      logger.info("The Broker colName is: " + colName);
      for (int row = 1; row <= brokerRowList.size(); row++) {
        WebElement rowData = driver.findElement(By.xpath("//tbody[@id='undefined-body']/tr/td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /***
   * Method to verify selected broker record color.
   */
  public void verifySelectedBrokerRecordColor(KafkaPageObject kafkaPageObject) {
    List<WebElement> brokerColList = kafkaPageObject.brokerCol;
    List<WebElement> brokerRowList = kafkaPageObject.brokerRows;
    Assert.assertFalse(brokerRowList.isEmpty(), "No data displayed for broker metrics table");
    for (int row = 0; row < brokerRowList.size(); row++) {
      MouseActions.clickOnElement(driver, brokerRowList.get(row));
      waitExecuter.sleep(2000);
      List<WebElement> activeRowsList = kafkaPageObject.selectedRowColor;
      Assert.assertFalse(activeRowsList.isEmpty(), "Color of the selected rows have not changed");
      Assert.assertEquals(activeRowsList.size(), brokerColList.size() + 1, "Color for all the columns of a selected" +
          " row has not changed ");
    }
  }
}
