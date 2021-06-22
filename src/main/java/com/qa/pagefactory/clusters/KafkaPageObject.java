package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KafkaPageObject {

  @FindBy(xpath = "//li[@class='active']/ul[@class='sub-menu']/li/a/span[contains(text(),'Kafka')]")
  public WebElement kafkaTab;

  @FindBy(xpath = "//div[@class='component-tabs-secondary']//a[contains(text(),'Broker')]")
  public WebElement brokerTab;

  @FindBy(xpath = "//div[@class='component-tabs-secondary']//a[contains(text(),'Topic')]")
  public WebElement topicTab;

  @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
  public WebElement kafkaClusterDropDown;

  @FindBy(xpath = "//*[@class='select2-results__options']//li")
  public List<WebElement> kafkaClusters;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
      "div[contains(@class, 'col score-improvement-box') and //div[@class='col']]//span")
  public List<WebElement> kafkaClusterKPIs;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
      "div[contains(@class, 'col score-improvement-box') and //div[@class='col-8']]//h2")
  public List<WebElement> kafkaClusterKPIValues;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1")
  public WebElement kafkaHeaderTab;

  @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]")
  public List<WebElement> kafkaMetrics;

  @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='header']/h4")
  public List<WebElement> kafkaMetricsHeader;

  @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]" +
      "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
  public List<WebElement> kafkaMetricsGraph;

  @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='footer']//span")
  public List<WebElement> kafkaMetricsFooter;

  @FindBy(xpath = "//*[@id='app']//div[3]/h2")
  public WebElement metricsTitle;

  @FindBy(xpath = "//div[@class='col-md-2']/input")
  public WebElement topicSearchBox;

  @FindBy(xpath = "//div[@class='filterclass']/input")
  public WebElement brokerSearchBox;

  //@FindBy(xpath = "//table[@class='component-data-tables']/thead/tr/th/span")
  @FindBy(xpath = "//*[@id=\"custom-tbl\"]/div[3]/div/table/thead/tr/th")
  public List<WebElement> topicTableCol;

   @FindBy(xpath = "//tbody/tr")
  public List<WebElement> topicTableRows;

 // @FindBy(xpath = "//table[@class='component-data-tables']//tbody/tr")
  @FindBy(xpath = "//*[@id='custom-tbl']//table/tbody/tr")
  public List<WebElement> topicTableRowData;

  @FindBy(xpath = "//*[contains(@id,'column-')]/span")
  public List<WebElement> brokerCol;

  @FindBy(xpath = "//tbody[@id='undefined-body']/tr")
  public List<WebElement> brokerRows;

  @FindBy(xpath = "//a[contains(@class,'sorting icon-sort')]")
  public List<WebElement> brokerColSortingIcon;

  @FindBy(xpath = "//div[@id='topContainer-undefined']//tr/th/span")
  public List<WebElement> brokerMetricsHeader;

  @FindBy(xpath = "//tbody[@id='kafkaTopicList-body']/tr")
  public List<WebElement> topicMetricRows;

  @FindBy(xpath = "//*[@class='component-dashboard']")
  public WebElement componentDashboard;

  @FindBy(xpath = "//*[@class='container-fluid']/h2")
  public WebElement latestMetricsInfo;

  @FindBy(xpath = "//*[@id='undefined-body']//td[@class='active-td']")
  public List<WebElement> selectedBrokerRowColor;

  @FindBy(xpath = "//*[@id='kafkaTopicList-body']//td[@class='active-td']")
  public List<WebElement> selectedTopicRowColor;


  public KafkaPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
