package com.qa.scripts.clusters.kafka;

import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;

public class KafkaPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;
  private DatePicker datePicker;
  String xAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-xaxis-labels')]/*[name()='text']";
  String yAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-yaxis-labels')]/*[name()='text']";

  Logger logger = Logger.getLogger(KafkaPage.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public KafkaPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    datePicker = new DatePicker(driver);
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
   * Method to Navigate to Kafka tab and then to Topic tab
   */
  public void navigateToTopicTab(KafkaPageObject kafkaPageObject) {
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    verifyClusterDropDown(kafkaPageObject);
    MouseActions.clickOnElement(driver, kafkaPageObject.topicTab);
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
    waitExecuter.waitUntilElementClickable(kafkaPageObject.kafkaClusterDropDown);
    waitExecuter.sleep(2000);
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaClusterDropDown);
    waitExecuter.sleep(2000);
    List<WebElement> kafkaClusterList = kafkaPageObject.kafkaClusters;
    Assert.assertFalse(kafkaClusterList.isEmpty(), "The drop down list for kafka cluster is empty");
    String clustername = kafkaClusterList.get(0).getText();
    MouseActions.clickOnElement(driver, kafkaClusterList.get(0));

    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast7Days();
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilPageFullyLoaded();

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
      //Check if data has only special charaters
      boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
      Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "Expected: AlphaNumeric value for " + kpiName + " Actual: " + kpiValue);
    }
  }

  /***
   * Method to verify the Kafka Metrics KPI Graphs of a connected kafka cluster
   */
  public void verifyKafkaKPIGraphs(KafkaPageObject kafkaPageObject, String expectedMetricsName, String graphId) {
    List<WebElement> metricsKpiList = kafkaPageObject.kafkaMetrics;
    List<WebElement> metricsKpiHeaderList = kafkaPageObject.kafkaMetricsHeader;
    List<WebElement> metricsKpiFooterList = kafkaPageObject.kafkaMetricsFooter;
    List<WebElement> metricsKpiGraphList = kafkaPageObject.kafkaMetricsGraph;
    Assert.assertFalse(metricsKpiList.isEmpty(), "Metrics for kafka cluster is empty");
    String xAxisPath = "//*[@id='" + graphId + "']" + xAxis;
    String yAxisPath = "//*[@id='" + graphId + "']" + yAxis;
    for (int i = 0; i < metricsKpiList.size(); i++) {
      String metricsName = metricsKpiHeaderList.get(i).getText();
      logger.info("Metrics Name: " + metricsName + " Expected Name: " + expectedMetricsName);
      if (metricsName.equals(expectedMetricsName)) {
        Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
        logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
        Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
        logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
        Assert.assertTrue(metricsKpiFooterList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
        logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
       // verifyAxis(xAxisPath, "X-Axis");
        verifyAxis(yAxisPath, "Y-Axis");
      }
    }
  }

  public void verifyAxis(String axisPath, String axisName) {
    List<WebElement> axisPathList = driver.findElements(By.xpath(axisPath));
    Assert.assertFalse(axisPathList.isEmpty(), "No points plotted on the " + axisName);
    HashSet<String> axisValSet = new HashSet<>();
    ArrayList<String> axisValArr = new ArrayList<>();
    for (int i = 0; i < axisPathList.size(); i++) {
      logger.info("axis value = "+ axisPathList.get(i).getText());
      axisValArr.add(axisPathList.get(i).getText());
      axisValSet.add(axisPathList.get(i).getText());
    }
    if (axisValSet.size() != axisValArr.size()){
      if (!axisValArr.contains("12:00")) {
        logger.info("Expected " + axisName + " : " + axisValSet + "\n Actual " + axisName + " : " + axisValArr);
        Assert.assertEquals(axisValSet.size(), axisValArr.size(), "Duplicate values present in the " + axisName + "\n" +
            "Expected : " + axisValSet + " Actual : " + axisValArr);
      }
    }
    logger.info("Expected " + axisName + " : " + axisValSet + "\n Actual " + axisName + " : " + axisValArr);
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
    Assert.assertFalse(colList.isEmpty(), "No columns for topic table");
    Assert.assertFalse(rowList.isEmpty(), "No data found for the topic tables ");
    for (int row = 0; row < rowList.size(); row++) {
      for (int col = 0; col < colList.size(); col++) {
        String colName = colList.get(col).getText();
        logger.info("The colName is: " + colName);
       // WebElement rowData = driver.findElement(By.xpath("//tbody/tr[" + (row + 1) + "]/td[" + (col + 1) + "]"));
        WebElement rowData = driver.findElement(By.xpath("//*[@id='custom-tbl']//table/tbody/tr[" + (row + 1) + "]" +
            "/td[" + (col + 1) + "]"));
        if ((col + 1) != 2 ) {
          Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
          //Check if data has only special charaters
          boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
          Assert.assertFalse(onlySpecialChars, "Expected value for column " + colName + " But got: " + rowData.getText());
        }
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
        WebElement rowData = driver.findElement(By.xpath("//tbody[@id='undefined-body']/tr[" + row + "]/td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        //Check if data has only special charaters
        boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(onlySpecialChars, "Expected value for column " + colName + " But got: " + rowData.getText());
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
      List<WebElement> activeRowsList = kafkaPageObject.selectedBrokerRowColor;
      Assert.assertFalse(activeRowsList.isEmpty(), "Color of the selected rows have not changed");
      Assert.assertEquals(activeRowsList.size(), brokerColList.size() + 1, "Color for all the columns of a selected" +
          " row has not changed ");
    }
  }

  /***
   * Method to verify selected Topic record color.
   */
  public void verifySelectedTopicRecordColor(KafkaPageObject kafkaPageObject) {
    List<WebElement> topicColList = kafkaPageObject.brokerCol;
    List<WebElement> topicRowList = kafkaPageObject.topicMetricRows;
    Assert.assertFalse(topicRowList.isEmpty(), "No data displayed for broker metrics table");
    for (int row = 0; row < topicRowList.size(); row++) {
      MouseActions.clickOnElement(driver, topicRowList.get(row));
      waitExecuter.sleep(2000);
      List<WebElement> activeRowsList = kafkaPageObject.selectedTopicRowColor;
      Assert.assertFalse(activeRowsList.isEmpty(), "Color of the selected rows have not changed");
      Assert.assertEquals(activeRowsList.size(), topicColList.size() + 1, "Color for all the columns of a selected" +
          " row has not changed ");
    }
  }

  public void verifyTopicMetrics(KafkaPageObject kafkaPageObject) {
    List<WebElement> topicColList = kafkaPageObject.brokerCol;
    Assert.assertFalse(topicColList.isEmpty(), " No columns displayed for topic metrics");
    List<WebElement> topicRowList = kafkaPageObject.topicMetricRows;
    Assert.assertFalse(topicRowList.isEmpty(), "No data displayed for topic metrics table");
    for (int col = 0; col < topicColList.size(); col++) {
      String colName = topicColList.get(col).getText();
      logger.info("The Topic colName is: " + colName);
      for (int row = 1; row <= topicRowList.size(); row++) {
        WebElement rowData = driver.findElement(By.xpath("//tbody[@id='kafkaTopicList-body']/tr[" + row + "]/td[" + (col + 1) + "]/span"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + colName);
        //Check if data has only special charaters
        boolean onlySpecialChars = rowData.getText().matches("[^a-zA-Z0-9]+");
        Assert.assertFalse(onlySpecialChars, "Expected value for column " + colName + " But got: " + rowData.getText());
        logger.info("Row data for column: " + colName + "\n " + rowData.getText());
      }
    }
  }

  /**
   * Method to verify column sorting options for Broker Metric table
   */
  public void verifyBrokerMetricsColSort(KafkaPageObject kafkaPageObject) {
    List<WebElement> brokerColList = kafkaPageObject.brokerCol;
    Assert.assertFalse(brokerColList.isEmpty(), " No columns displayed for broker metrics");
    List<WebElement> brokerRowList = kafkaPageObject.brokerRows;
    String[] colStr = {"Broker", "Bytes In Per Sec", "Bytes Out Per Sec"};
    //String[] intStr = {"Messages In Per Sec", "Total Fetch Requests Per Sec", "Active Controller Count"};
    Assert.assertFalse(brokerRowList.isEmpty(), "No data displayed for broker metrics table");
    for (int col = 0; col < brokerColList.size(); col++) {
      ArrayList<String> strArr = new ArrayList<>();
      ArrayList<Integer> intArr = new ArrayList<>();
      ArrayList<String> expectedStrArr = new ArrayList<>();
      ArrayList<Integer> expectedIntArr = new ArrayList<>();
      String colName = brokerColList.get(col).getText();
      logger.info("The Broker colName is: " + colName);
      if (Arrays.asList(colStr).contains(colName)) {
        expectedStrArr = sortBrokerStrCol(brokerRowList, col);
        Collections.sort(expectedStrArr);
      } else {
        expectedIntArr = sortBrokerIntCol(brokerRowList, col);
        Collections.sort(expectedIntArr);
      }
      MouseActions.clickOnElement(driver, kafkaPageObject.brokerColSortingIcon.get(col));
      waitExecuter.waitUntilPageFullyLoaded();
      if (Arrays.asList(colStr).contains(colName)) {
        strArr = sortBrokerStrCol(brokerRowList, col);
        logger.info("Actual sorted value is: " + strArr + "\n Expected sorted value is " + expectedStrArr);
        Assert.assertEquals(strArr, expectedStrArr);
      } else {
        intArr = sortBrokerIntCol(brokerRowList, col);
        logger.info("Actual sorted value is: " + intArr + "\n Expected sorted value is " + expectedIntArr);
        Assert.assertEquals(intArr, expectedIntArr);
      }
      strArr.clear();
      intArr.clear();
      expectedStrArr.clear();
      expectedIntArr.clear();
    }
  }

  public ArrayList<Integer> sortBrokerIntCol(List<WebElement> brokerRowList, int col) {
    ArrayList<Integer> intArr = new ArrayList<>();
    for (int row = 0; row < brokerRowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath("//tbody[@id='undefined-body']/" +
          "tr[" + (row + 1) + "]/td[" + (col + 1) + "]/span"));
      int rowVal = Integer.parseInt(rowData.getText());
      intArr.add(rowVal);
    }
    return intArr;
  }

  public ArrayList<String> sortBrokerStrCol(List<WebElement> brokerRowList, int col) {
    ArrayList<String> strArr = new ArrayList<>();
    for (int row = 0; row < brokerRowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath("//tbody[@id='undefined-body']/" +
          "tr[" + (row + 1) + "]/td[" + (col + 1) + "]/span"));
      strArr.add(rowData.getText());
    }
    return strArr;
  }

  public void verifyTopicRecordPerBroker(KafkaPageObject kafkaPageObject) {
    List<WebElement> brokerColList = kafkaPageObject.brokerCol;
    Assert.assertFalse(brokerColList.isEmpty(), " No columns displayed for broker metrics");
    List<WebElement> brokerRowList = kafkaPageObject.brokerRows;
    Assert.assertFalse(brokerRowList.isEmpty(), "No data displayed for broker metrics table");
    for (int row = 1; row <= brokerRowList.size(); row++) {
      WebElement rowData = driver.findElement(By.xpath("//tbody[@id='undefined-body']/tr[" + row + "]/td[" + 1 + "]/span"));
      MouseActions.clickOnElement(driver, rowData);
      waitExecuter.waitUntilPageFullyLoaded();
      verifyTopicRecords(kafkaPageObject);
    }
  }
}
