package com.qa.scripts.clusters.kafka;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
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
        logger.info("Metrics Name: [" + metricsName+"] displayed in the header");
        Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
        logger.info("The graph for Metrics : [" + metricsName+"] is displayed");
        Assert.assertTrue(metricsKpiFooterList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
        logger.info("The footer for Metrics : [" + metricsName+"] is displayed");
      }
    }
  }
}
