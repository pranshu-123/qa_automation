package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KafkaPageObject {

  @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Kafka')]")
  public WebElement kafkaTab;

  @FindBy(xpath = "//div[@class='component-tabs-secondary']//a[contains(text(),'Broker')]")
  public WebElement brokerTab;

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

  @FindBy(xpath = "//table[@class='component-data-tables']/thead/tr/th/span")
  public List<WebElement> topicTableCol;

   @FindBy(xpath = "//tbody/tr")
  public List<WebElement> topicTableRows;

  @FindBy(xpath = "//tbody/tr/td")
  public List<WebElement> topicTableRowData;

  @FindBy(xpath = "//*[contains(@id,'column-')]/span")
  public List<WebElement> brokerCol;

  @FindBy(xpath = "//tbody[@id='undefined-body']/tr")
  public List<WebElement> brokerRows;

  @FindBy(xpath = "//*[@class='component-dashboard']")
  public WebElement componentDashboard;

  @FindBy(xpath = "//*[@class=\"container-fluid\"]/h2")
  public WebElement latestMetricsInfo;

  @FindBy(xpath = "//*[@id='undefined-body']//td[@class='active-td']")
  public List<WebElement> selectedRowColor;

  public KafkaPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
