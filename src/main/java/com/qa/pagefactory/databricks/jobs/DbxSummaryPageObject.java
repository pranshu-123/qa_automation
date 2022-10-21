package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxSummaryPageObject {
    @FindBy(className = "close")
    public WebElement closeIcon;

    @FindBy(xpath = "//button[text()[normalize-space()='Spark']]")
    public WebElement sparkIcon;

    @FindBy(xpath = "(//tbody[@id='appNavigation-body'])/tr")
    public List<WebElement> navigationTableRows;

    @FindBy(xpath = "(//thead[@id='appNavigation-head'])/tr/th")
    public List<WebElement> navigationHeaders;

    @FindBy(xpath = "//*[@class='link-item']")
    public WebElement programSourceLink;

    @FindBy(xpath = "//*[@class='link-item']")
    public WebElement programSourceLinkText;

    @FindBy(xpath = "//th[7]//a[1]")
    public WebElement sortByDurationApp;

    @FindBy(xpath = "//p[@class='results-title']//b[1]")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//*[@class=\"highcharts-title\"]/*[name()='tspan']")
    public List<WebElement> stageTimingHeaders;

    @FindBy(xpath = "//*[contains(@class,'component-data-tables')]//td/p")
    public WebElement programDataNotFound;

    //Load Logs and Diagnostic action
    @FindBy(xpath = "//*[@id='spark-action-container']")
    public WebElement loadAction;

    @FindBy(xpath = "//*[@class='popover-body']/ul/li/h4")
    public List<WebElement> loadActionList;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[4]/a[2]")
    public WebElement getAppId;

    @FindBy(xpath = "//span[@class='text-ellipsis app-id']/following-sibling::span[1]")
    public WebElement getHeaderAppId;

    @FindBy(xpath = "//span[@class='text-ellipsis app-id']/following-sibling::span[1]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[1]/div[2]/h4/span")
    public List<WebElement> leftPaneKPIList;

    @FindBy(xpath = "//*[@id='app-prev-header']/b")
    public WebElement ifAttemptPresent;

    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[2]/div[1]/div/div[2]/div/ul/li")
    public List<WebElement> component_element;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])")
    public List<WebElement> ganttChartTable;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
    public List<WebElement> ganttChartHeaders;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//div/p")
    public List<WebElement> ganttChartDuration;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[1]")
    public List<WebElement> ganttChartJobId;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[2]")
    public List<WebElement> ganttChartStartTime;

    @FindBy(xpath = "//*[@id=\"spark_exec_plan\"]/*[name()='svg']")
    public WebElement DAGData;

    @FindBy(xpath = "(//thead[@id='appNavigation-head'])/tr/th[1]")
    public WebElement singleJobHeader;

    @FindBy(xpath = "//*[@id=\"dagContainer\"]/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']")
    public List<WebElement> rddBlockList;

    @FindBy(xpath = "//*[@class='consumers']")
    public List<WebElement> rddBlockNumList;

    @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy(xpath = "//div[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0" +
            " highcharts-tracker']/span")
    public List<WebElement> numAttempts;

    @FindBy(xpath = "//*[@id='sparkStageNavigation-body']//tr")
    public List<WebElement> stageRows;

    @FindBy(xpath = "//div [@class='component-tabs-primary']/ul/li")
    public List<WebElement> stagesTab;

    @FindBy(xpath = "//div [@class='dashboard-container']//h4")
    public List<WebElement> stagesTimelineHeader;

    @FindBy(xpath = "//*[@class='histogram']/*[name()='g'][3]")
    public List<WebElement> timelineBarGraph;

    @FindBy(xpath = "//*[@id=\"timeline_tab\"]/li/span/a")
    public List<WebElement> stagesTimelineSubTab;

    @FindBy(xpath = "//*[@id=\"app\"]//div[2]/div[1]//div[1]/div[2]/h4/span/b")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div/div[1]/div/h3")
    public List<WebElement> rightPaneAppKpiVal;


    @FindBy(xpath = "(//div[@id='scrollableMenu'])/ul/li/a")
    public List<WebElement> appSummaryTabs;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@class='modal-body scrollbar-s']")
    public WebElement loadDiagnosticWin;

    @FindBy(xpath = "(//div[contains(@class,'modal-sec-head')])/h2")
    public WebElement loadDiagnosticWinHeader;

    @FindBy(css = "h2[class='text-white'] button[class='close']")
    public WebElement loadWinClose;

    @FindBy(xpath = "//span[text()[normalize-space()='Spark:']]")
    public WebElement getAppSparkIcon;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
    public WebElement configPropNum;

    @FindBy(xpath = "//*[@id='app-query']/code/span")
    public List<WebElement> programTabData;

    @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/p")
    public List<WebElement> configPropKey;

    @FindBy(xpath = "//div[contains(@class,'cta-secondary')]/a/span[text()='RESET']")
    public WebElement resetButtonAppDetails;

    @FindBy(xpath = "//*[@id='appTagsTable']//tr/td[1]")
    public List<WebElement> tagKey;

    @FindBy(xpath = "//*[@id='appTagsTable']//tr/td[2]")
    public List<WebElement> tagValue;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class, 'highcharts-drilldown-point')]")
    public WebElement driverDrillDown;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
            "'highcharts-drilldown-point') and @fill='#DDDF00' or @fill='#dddf00' or @fill='rgb(221,223,0)']")
    public WebElement processingStage;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
            "'highcharts-drilldown-point') and @fill='#7cb5ec' or @fill='#7CB5EC' or @fill='rgb(124,181,236)']")
    public WebElement inputStage;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class," +
            "'highcharts-drilldown-point') and @fill='rbg(92,184,92)']")
    public WebElement outputStage;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> legendNames;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> ATlegendNames;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> driverLegendNames;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='g'][1]")
    public List<WebElement> timingGraphList;

    @FindBy(xpath = "//*[@id='app']//section/div/a/span")
    public List<WebElement> logExecutorNames;

    @FindBy(xpath = "//div[@class=\"component-dashboard\"]//*[@class=\"header\"]/h4")
    public List<WebElement> resourcesGraphTitle;

    @FindBy(xpath = "//div[@class='scroll-contant']//li")
    public List<WebElement> timingsSubTabs;

    @FindBy(xpath = "//*[@class=\"error-id\"]")
    public List<WebElement> errorCategories;

    @FindBy(xpath = "//*[@class=\"highcharts-container \"]/*[name()='svg']/*[name()='text']/*")
    public WebElement timingsTabTitle;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]//*[name()='g'][1]")
    public WebElement pieChart;

    @FindBy(xpath = "//*[contains(@class,'highcharts-drillup-button')]")
    public WebElement backButton;

    @FindBy(xpath = "//*[@class=\"error-code\"]/p")
    public List<WebElement> errorContents;

    @FindBy(xpath = "//*[@class='stg-link link']")
    public List<WebElement> topStages;

    @FindBy(xpath = "//*[@id='app']//section/div/a")
    public List<WebElement> logElementCollapsable;

    @FindBy(xpath = "//div[@class='modal-body scrollbar-s']//p")
    public List<WebElement> logScrollable;

    @FindBy(xpath = "//*[@id='app']//section//p[1]")
    public WebElement logExecutorContents;

    @FindBy(xpath = "//*[@id='app']//section//p[2]/a/b")
    public WebElement showFullLogLink;

    @FindBy(xpath = "//*[@id='appTagsTable']/thead//th")
    public List<WebElement> tagTableHeader;


    @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/span")
    public List<WebElement> configPropValue;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/div[2]/span")
    public List<WebElement> configKeywords;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
    public List<WebElement> analysisCollapse;

    @FindBy(xpath = "//*[@class=\"status-title\"]//*[@class=\"badge fatal\"]")
    public List<WebElement> insightsType;

    // Error Tab xpath
    @FindBy(xpath = "//span [@class='icon-caret']")
    public List<WebElement> errorCollapse;

    @FindBy(xpath = "//*[@id=\"taskattempts\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesPieChartInternalVal;

    @FindBy(xpath = "//*[@class='col-md-12 no-left-gutter no-right-gutter']//*[@role='presentation'][1]")
    public WebElement resourcesMetricsDropDown;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]")
    public List<WebElement> vcoreMetrics;


    @FindBy(xpath = "//*[@class='select2-results']//ul/li/ul/li")
    public List<WebElement> resourcesMetricsDropDownData;

    @FindBy(xpath = "//span[@id='select2-qvbs-container']/following-sibling::span[1]")
    public WebElement resourcesHotsMetricsDropDown;

    @FindBy(xpath = "//*[@class='select2-results']//ul/li/ul/li")
    public List<WebElement> resourcesHostMetricsDropDownData;


    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]")
    public List<WebElement> memoryMetrics;

    @FindBy(xpath = "//*[@chart-options='getappResourceUseageOptions']//*[name()='path' and @class='highcharts-tracker-line']")
    public List<WebElement> resourcesMetricsLineGraph;

    @FindBy(xpath = "//*[@class=\"legendclass\"]//span")
    public List<WebElement> resourcesMetricsPlotGraphLegend;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]")
    public List<WebElement> resourcesAllGraphs;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> containerMetricsGraph;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]//div[@class='header']/h4")
    public List<WebElement> vcoreMetricsHeader;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]//div[@class='header']/h4")
    public List<WebElement> memoryMetricsHeader;


    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]")
    public List<WebElement> containerMetrics;

    @FindBy(xpath = "//*[@chart-options='getappResourceUseageOptions']//*[name()='path' and @class='highcharts-tracker-line']")
    public List<WebElement> resourcesHostMetricsLineGraph;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]//div[@class='header']/h4")
    public List<WebElement> containerMetricsHeader;

    @FindBy(xpath = "//*[@class=\"legendclass\"]//span")
    public List<WebElement> resourcesHostMetricsPlotGraphLegend;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> vcoreMetricsGraph;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> memoryMetricsGraph;

    @FindBy(xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttFooterName;

    @FindBy(xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttFooterVal;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxSummaryPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
