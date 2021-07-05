package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ELKPageObject {
  @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Elasticsearch'])")
  public WebElement ESTab;

  @FindBy(xpath = "//a[contains(text(),'Indices')]")
  public WebElement indicesTab;

  @FindBy(xpath = "//a[contains(text(),'Pipelines')]")
  public WebElement pipelineTab;

  @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Logstash'])")
  public WebElement logstashTab;

  @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Kibana'])")
  public WebElement kibanaTab;

  @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
  public WebElement clusterDropDown;

  @FindBy(xpath = "//a[normalize-space()='Nodes']")
  public WebElement nodesTab;

  @FindBy(xpath = "//a[normalize-space()='Pipelines']")
  public WebElement pipelinesTab;

  @FindBy(xpath = "//*[@class='select2-results__options']//li")
  public List<WebElement> ELKClusters;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1")
  public WebElement ESHeaderTab;

  @FindBy(xpath = "//*[contains(@id,'column-')]/span")
  public List<WebElement> ESCol;

  @FindBy(xpath = "//tbody[@id='elasticSearchNodesTableList-body']/tr")
  public List<WebElement> ESRows;

  @FindBy(xpath = "//tbody[@id='elasticSearchIndexTable-body']/tr")
  public List<WebElement> IndicesRows;

  @FindBy(xpath = "//tbody[@id='elasticSearchIndexTable-body']/tr[1]/td[1]/span")
  public WebElement IndicesName;

  @FindBy(xpath = "//*[@class='search']")
  public WebElement IndicesSearchBox;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
      "div[contains(@class, 'col score-improvement-box') and //div[@class='col']]//span")
  public List<WebElement> ESKpiNames;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//div[contains(@class," +
      "'col score-improvement-box') and //div[@class='col']]//h2")
  public List<WebElement> ESKpiValues;

  @FindBy(xpath = "//div[@id='elastic-index-details']//div[@class='col-md-6']")
  public List<WebElement> IndicesMetric;

  @FindBy(xpath = "//div[@id='elastic-index-details']//div[contains(@class,'dashboard-module')]//div[@class='header']/h4")
  public List<WebElement> IndiceMetricsHeader;

  @FindBy(xpath = "//div[@id='elastic-index-details']//div[contains(@class,'dashboard-module')]//div[@class='footer']/label")
  public List<WebElement> IndiceMetricsFooter;

  @FindBy(xpath = "//div[@id='elastic-index-details']//div[contains(@class,'dashboard-module')]//" +
      "div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
  public List<WebElement> IndiceMetricsGraph;

  //Logstash xpath:
  @FindBy(xpath = "//div[contains(@class,'kpi-parent')]")
  public List<WebElement> logstashKpis;

  @FindBy(xpath = "//div[contains(@class,'kpi-parent')]//div[@class='col']/span")
  public List<WebElement> logstashKpiName;

  @FindBy(xpath = "//div[contains(@class,'kpi-parent')]//div[@class='col']/h2")
  public List<WebElement> logstashKpiValue;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]/div[@class='header']/h4")
  public List<WebElement> logstashGraphHeader;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]/div[@class='footer']//label")
  public List<WebElement> logstashGraphFooter;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]//div[@class='content']//*[name()='g' and " +
      "@class='highcharts-series-group']")
  public List<WebElement> logstashGraph;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-yaxis-labels')]/*[name()='text']")
  public List<WebElement> yAxisPath;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]//*[name()='svg' and contains(@class,'highcharts-root')]" +
      "//*[name()='g' and contains(@class,'highcharts-xaxis-labels')]/*[name()='text']")
  public List<WebElement> xAxisPath;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module container-fluid')]")
  public WebElement logstashKpiBox;

  @FindBy(xpath = "//div[contains(@class,'nodes-table-row')]")
  public WebElement tableRowBox;

  @FindBy(xpath = "//table/thead/tr/th")
  public List<WebElement> nodesCol;

  @FindBy(xpath = "//div[contains(@id,'kibanaGraph')]//div[@class='content']//*[name()='g' and " +
          "@class='highcharts-series-group']")
  public List<WebElement> kibanaGraph;

  @FindBy(xpath = "//div[contains(@id,'kibanaGraph')]/div[@class='header']/h4")
  public List<WebElement> kibanaGraphHeader;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
  public List<WebElement> nodesRows;

  @FindBy(xpath = "//div[contains(@id,'kibanaGraph')]/div[@class='footer']//label")
  public List<WebElement> kibanaGraphFooter;

  @FindBy(xpath = "//a[contains(@class,'icon-sort')]")
  public List<WebElement>  nodesColSortingIcon;

  @FindBy(xpath = "//div[contains(@class,'component-dashboard')]")
  public WebElement overViewBox;

  @FindBy(xpath = "//div[contains(@class,'component-tabs-secondary')]/ul")
  public WebElement nodePipeline;

  //@FindBy(xpath = "//table[@class='component-data-tables']//tbody/tr")
  @FindBy(xpath = "//*[@id='custom-tbl']//table/tbody/tr")
  public List<WebElement> logstashTableRows;

  @FindBy(xpath = "//*[@id='custom-tbl']//table/tbody/tr")
  public List<WebElement> pipelineTableRows;

 // @FindBy(xpath = "//table[@class='component-data-tables']//tbody/tr/td")
  @FindBy(xpath = "//*[@id='custom-tbl']//table/tbody/tr/td")
  public List<WebElement> logstashNodeColData;

  @FindBy(xpath = "//*[@id='custom-tbl']//table/thead//th")
  public List<WebElement> logstashNodeColHeader;

  @FindBy(xpath = "//div[contains(@class,'logstash-graph')]")
  public List<WebElement> logstashMetricsList;

  @FindBy(xpath = "//div[contains(@id,'kibanaGraph')]")
  public List<WebElement> kibanaMetricsList;

  @FindBy(xpath = "//div[contains(@class,'pipeline-node-list')]//table/tbody//tr")
  public List<WebElement> associatedNodeTableRows;

  @FindBy(xpath = "//div[contains(@class,'component-dashboard')]//div[contains(@class,'score-improvement-bo')]")
  public List<WebElement> pipelineKpiList;

  @FindBy(xpath = "//div[contains(@class,'component-dashboard')]//div[contains(@class,'score-improvement-bo')]//" +
      "div[@class='col']/h2")
  public List<WebElement> perPipelineKpiValue;

  @FindBy(xpath = "//div[contains(@class,'component-dashboard')]//div[contains(@class,'score-improvement-bo')]//" +
      "div[@class='col']/span")
  public List<WebElement> perPipelineKpiName;

  public ELKPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
