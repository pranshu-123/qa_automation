package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SparkAppsDetailsPageObject {

  //Streaming App Component xpath
  @FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-spline-series')]//*[name()='path' and @class='highcharts-graph']")
  public WebElement streamingLineChart;

  @FindBy(xpath = "//*[name()='g']/*[name()='rect' and @class='highcharts-navigator-mask-inside']")
  public WebElement streamingWaveChart;

  @FindBy(xpath = "//*[@id='spark-stream-leftpanel']/h3/b")
  public WebElement completedBatchTitle;

  @FindBy(xpath = "//*[@id='sparkStreamNavigation-head']/tr/th")
  public List<WebElement> streamTableHeader;

  @FindBy(xpath = "//*[@id='sparkStreamNavigation-body']/tr")
  public List<WebElement> streamTableRows;

  @FindBy(xpath = "//*[@id='app-filter-panel']/div[2]/div[2]/div/div[2]/p/label/span[2]")
  public WebElement failedAppCnt;

  @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[4]/a[2]")
  public WebElement getAppId;

  @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
  public WebElement getHeaderAppId;

  @FindBy(xpath = "//div[contains(@class,'close')]")
  public WebElement closeAppsPageTab;

  @FindBy(xpath = "//*[@id='app-prev-header']/b")
  public WebElement ifAttemptPresent;

  @FindBy(xpath = "//div[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0" +
          " highcharts-tracker']/span")
  public List<WebElement> numAttempts;

  @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[1]/div[2]/h4/span")
  public List<WebElement> leftPaneKPIList;

  @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
  public WebElement getTotalAppCount;

  @FindBy(xpath = "(//div[@id='scrollableMenu'])/ul/li/a")
  public List<WebElement> appSummaryTabs;

  @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
  public List<WebElement> analysisCollapse;

  @FindBy(xpath = "//*[@class=\"status-title\"]//*[@class=\"badge fatal\"]")
  public List<WebElement> insightsType;

  // Error Tab xpath
  @FindBy(xpath = "//span [@class='icon-caret']")
  public List<WebElement> errorCollapse;

  @FindBy(xpath = "//*[@class=\"error-id\"]")
  public List<WebElement> errorCategories;

  @FindBy(xpath = "//*[@class=\"error-code\"]/p")
  public List<WebElement> errorContents;

  @FindBy(xpath = "//*[@id='appTagsTable']//tr/td[1]")
  public List<WebElement> tagKey;

  @FindBy(xpath = "//*[@id='appTagsTable']//tr/td[2]")
  public List<WebElement> tagValue;

  @FindBy(xpath = "//*[@id='appTagsTable']/thead//th")
  public List<WebElement> tagTableHeader;

  @FindBy(xpath = "//*[@id='appTagsTable']/tbody/tr")
  public List<WebElement> tagTableRows;

  @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[2]/div[1]/div/div[2]/div/ul/li")
  public List<WebElement> component_element;

  @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])")
  public List<WebElement> ganttChartTable;

  @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//div/p")
  public List<WebElement> ganttChartDuration;

  @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[1]")
  public List<WebElement> ganttChartJobId;

  @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[2]")
  public List<WebElement> ganttChartStartTime;

  @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
  public List<WebElement> ganttChartHeaders;

  @FindBy(xpath = "(//tbody[@id='appNavigation-body'])/tr")
  public List<WebElement> navigationTableRows;

  @FindBy(xpath = "(//thead[@id='appNavigation-head'])/tr/th")
  public List<WebElement> navigationHeaders;

  @FindBy(xpath = "//*[@id='app']//*[@class='right']/span")
  public List<WebElement> rightPaneKpis;

  @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
  public List<WebElement> rightPaneAppKpis;

  @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
  public List<WebElement> rightPaneAppKpiVal;

  @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
  public List<WebElement> stageHeaders;

  @FindBy(xpath = "(//thead[@id='appNavigation-head'])/tr/th[1]")
  public WebElement singleJobHeader;

  @FindBy(xpath = "//*[@id='app-query']/code/span")
  public List<WebElement> programTabData;

  @FindBy(xpath = "//*[contains(@class,'component-data-tables')]//td/p")
  public WebElement programDataNotFound;

  @FindBy(xpath = "//*[@class='link-item']")
  public WebElement programSourceLink;

  @FindBy(xpath = "//*[@class='link-item']")
  public WebElement programSourceLinkText;

  @FindBy(xpath = "//*[@id='app-query']/div[contains(@data-range,'31')]")
  public WebElement programHighlightedLine;

  // Configuration Tab xpaths

  @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
  public WebElement configPropNum;

  @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/div[2]/span")
  public List<WebElement> configKeywords;

  @FindBy(id = "reset")
  public WebElement resetButton;

  @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/p")
  public List<WebElement> configPropKey;

  @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/span")
  public List<WebElement> configPropValue;

  // Logs Tab xpaths
  @FindBy(xpath = "//*[@id='app']//section/div/span")
  public List<WebElement> logElementCollapsable;

  @FindBy(xpath = "//*[@id='app']//section/div/a/span")
  public List<WebElement> logExecutorNames;

  @FindBy(xpath = "//*[@id='app']//section//p[1]")
  public WebElement logExecutorContents;

  @FindBy(xpath = "//*[@id='app']//section//p[2]/a/b")
  public WebElement showFullLogLink;

 // Timings Tab xpath
  @FindBy(xpath = "//*[@id='allApps']")
  public List<WebElement> timingsSubTabs;

  @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
  public List<WebElement> legendNames;

  @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
  public List<WebElement> ATlegendNames;

  @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
  public List<WebElement> driverLegendNames;

  @FindBy(xpath = "//*[@class=\"highcharts-container \"]/*[name()='svg']/*[name()='text']/*")
  public WebElement timingsTabTitle;

  @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]//*[name()='g'][1]")
  public WebElement pieChart;

  @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
          "'highcharts-drilldown-point') and @fill='#DDDF00' or @fill='#dddf00' or @fill='rgb(221,223,0)']")
  public WebElement processingStage;

  @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
          "'highcharts-drilldown-point') and @fill='#7cb5ec' or @fill='#7CB5EC' or @fill='rgb(124,181,236)']")
  public WebElement inputStage;

  @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
          "'highcharts-drilldown-point') and @fill='rbg(92,184,92)']")
  public WebElement outputStage;

  @FindBy(xpath = "//*[contains(@class,'highcharts-drillup-button')]")
  public WebElement backButton;

  @FindBy(xpath ="//*[@class='stg-link link']")
  public List<WebElement> topStages;

  @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class, 'highcharts-drilldown-point')]")
  public WebElement driverDrillDown;

  //Executor Tab xpath
  @FindBy(xpath = "//*[@id=\"dagContainer\"]/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']")
  public List<WebElement> rddBlockList;

  @FindBy(xpath = "//*[@class='consumers']")
  public List<WebElement> rddBlockNumList;

  @FindBy(xpath = "//*[@id=\"spark_exec_plan\"]/*[name()='svg']")
  public WebElement DAGData;

  //Resources Tab xpath
 // @FindBy(xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div//h4")
  @FindBy(xpath = "//div[@class=\"component-dashboard\"]//*[@class=\"header\"]/h4")
  public List<WebElement> resourcesGraphTitle;

  @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]")
  public List<WebElement> resourcesAllGraphs;

  @FindBy(xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[1]")
  public List<WebElement> taskAttFooterName;

  @FindBy(xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[2]")
  public List<WebElement> taskAttFooterVal;

  @FindBy(xpath = "//*[@id=\"taskattempts\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
  public WebElement resourcesPieChartInternalVal;

  @FindBy(xpath = "//*[@class='col-md-12 no-left-gutter no-right-gutter']//*[@role='presentation'][1]")
  public WebElement resourcesMetricsDropDown;

  @FindBy(xpath = "//*[@class='select2-results']//ul/li/ul/li")
  public List<WebElement> resourcesMetricsDropDownData;

  @FindBy(xpath = "//*[@chart-options='getappResourceUseageOptions']//*[name()='path' and @class='highcharts-tracker-line']")
  public List<WebElement> resourcesMetricsLineGraph;

  @FindBy(xpath = "//*[@class=\"legendclass\"]//span")
  public List<WebElement> resourcesMetricsPlotGraphLegend;

  //Detailed stage's Tab xpath
  @FindBy(xpath = "//*[@id='sparkStageNavigation-body']//tr")
  public List<WebElement> stageRows;

  @FindBy(xpath = "//div [@class='component-tabs-primary']/ul/li")
  public List<WebElement> stagesTab;

  @FindBy(xpath = "//div [@class='dashboard-container']//h4")
  public List<WebElement> stagesTimelineHeader;

  @FindBy(xpath = "//*[@id=\"timeline_tab\"]/li/span/a")
  public List<WebElement> stagesTimelineSubTab;

  @FindBy(xpath = "//*[@class='histogram']/*[name()='g'][3]")
  public List<WebElement> timelineBarGraph;

  @FindBy(xpath = "//*[@class=\"highcharts-title\"]/*[name()='tspan']")
  public List<WebElement> stageTimingHeaders;

  @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='g'][1]")
  public List<WebElement> timingGraphList;

  //Load Logs and Diagnostic action
  @FindBy(xpath = "//*[@id='spark-action-container']")
  public WebElement loadAction;

  @FindBy(xpath = "//*[@class='popover-body']/ul/li/h4")
  public List<WebElement> loadActionList;

  @FindBy(xpath = "//*[@class='modal-body scrollbar-s']")
  public WebElement loadDiagnosticWin;

  @FindBy(xpath = "(//div[contains(@class,'modal-sec-head')])/h2")
  public WebElement loadDiagnosticWinHeader;

  @FindBy(xpath = "//button[@class='close']")
  public WebElement loadWinClose;

  @FindBy(xpath ="//div[@class='modal-body']//p/br")
  public List<WebElement> logScrollable;

  @FindBy(xpath = "(//div[contains(@class,'filter-section')]//h3[contains(@class,'expandable-header')])[2]")
  public WebElement statusHeader;

 @FindBy(xpath = "//div[contains(@class,'cta-secondary')]/a/span[text()='RESET']")
 public WebElement resetButtonAppDetails;


  /**
   * @param driver The driver that will be used to look up the elements
   */
  public SparkAppsDetailsPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}