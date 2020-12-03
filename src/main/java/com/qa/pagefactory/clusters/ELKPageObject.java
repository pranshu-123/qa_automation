package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ELKPageObject {
  @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text()," +
      "'Elasticsearch')]")
  public WebElement ESTab;

  @FindBy(xpath = "//a[contains(text(),'Indices')]")
  public WebElement indicesTab;

  @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text()," +
      "'Logstash')]")
  public WebElement logstashTab;

  @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text()," +
      "'Kibana')]")
  public WebElement kibanaTab;

  @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
  public WebElement clusterDropDown;

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

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
      "div[contains(@class, 'col score-improvement-box') and //div[@class='col']]//span")
  public List<WebElement> ESKpiNames;

  @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//div[contains(@class," +
      "'col score-improvement-box') and //div[@class='col']]//h2")
  public List<WebElement> ESKpiValues;

  @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='footer']/label")
  public List<WebElement> ESMetricsFooter;

  @FindBy(xpath = "//div[@class='col-md-6']//div[contains(@class,'dashboard-module')]//div[@class='header']/h4")
  public List<WebElement> IndiceMetricsHeader;

  @FindBy(xpath = "//div[@class='col-md-6']//div[contains(@class,'dashboard-module')]//div[@class='footer']")
  public List<WebElement> IndiceMetricsFooter;

  public ELKPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
