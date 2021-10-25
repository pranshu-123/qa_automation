package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MrAppsDetailsPageObject {
    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/a[2]")
    public WebElement getAppId;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getSummaryFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getTypeFromTable;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[5]")
    public WebElement getClusterId;

    @FindBy(xpath = "//span[(text()='Insights')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByParentApp;

    @FindBy(xpath = "//span[(text()='Read')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByReadApp;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//span[(text()='Read')]//following-sibling::a[contains(@class,'sorting icon-sort icon-sort-sorted-down')]")
    public WebElement sortApp;

    @FindBy(xpath = "//span[(text()='Write')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByWriteApp;

    @FindBy(xpath = "//span[(text()='Duration')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByDurationApp;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[11]//span[@class='icon-tuning']")
    public List<WebElement> checkInsightsApp;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[9][@class='text-left']")
    public List<WebElement> checkReadApp;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[10][@class='text-left']")
    public List<WebElement> checkWriteApp;

    @FindBy(xpath = "//a[normalize-space()='Analysis']")
    public WebElement analysisTab;

    @FindBy(xpath = "//a[normalize-space()='Resources']")
    public WebElement resourcesTab;

    @FindBy(xpath = "//a[normalize-space()='Configuration']")
    public WebElement configurationTab;

    @FindBy(xpath = "//*[@id='taskattemptmap']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttFooterName;

    @FindBy(xpath = "//*[@id='taskattemptmap']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttFooterVal;

    @FindBy(xpath = "//*[@id=\"taskattempts\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesPieChartInternalVal;

    @FindBy(xpath = "//*[@id=\"taskattemptmap\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesChartInternalVal;

    @FindBy(xpath = "//body[1]//div[1]/*[local-name()='svg'][1]/*[name()='g'][6]/*[name()='g'][1]/*[name()='rect'][1]")
    public WebElement resourcesContainsInternalVal;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='footer']//span")
    public List<WebElement> kafkaMetricsFooter;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> containerMetricsGraph;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]")
    public List<WebElement> containerMetrics;

    @FindBy(xpath = "//*[@id='taskattemptmap']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttReduceFooterName;

    @FindBy(xpath = "//*[@id='taskattemptreduce']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttReduceFooterVal;

    @FindBy(xpath = "//*[@id=\"taskattemptmap\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesReducePieChartInternalVal;

    @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy(xpath = "//span[@class=\"success badge\" and @style=\"margin-top: 16px; margin-left: 10px;\" and text()=\"Success\"]")
    public WebElement appStatus;

    @FindBy(xpath = "//span[@class='badge warning']")
    public WebElement colorCode;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[2]")
    public WebElement Status;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[3]")
    public WebElement getUsername;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement getAppname;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[6]")
    public WebElement getstartTime;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[7]")
    public WebElement getduration;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[9]")
    public WebElement getRead;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[10]")
    public WebElement getWrite;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement getApptooltips;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/div")
    public WebElement getAppid;


    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
    public WebElement getHeaderAppId;

    @FindBy(css = "div.close")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//a[@href='#/']")
    public WebElement homeTab;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> ATlegendNames;

    @FindBy(xpath = "//h2[@class='text-white inline-blk']")
    public WebElement getJobSummary;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> driverLegendNames;


    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
    public WebElement configPropNum;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement SearchProp;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/div[2]/span")
    public List<WebElement> configKeywords;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/span/div/a/span")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@id='appConfiguration']//tbody/tr/td[1]")
    public List<WebElement> configPropKey;

    @FindBy(xpath = "//*[@id='appConfiguration']//tbody/tr/td[2]")
    public List<WebElement> configPropValue;


    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[1]/div[2]/h4/span")
    public List<WebElement> leftPaneKPIList;

    @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "(//div[@id='scrollableMenu'])/ul/li/a")
    public List<WebElement> appSummaryTabs;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
    public List<WebElement> analysisCollapse;

    @FindBy(xpath = "//section[contains(@class,'component-insights-feed')]/div/span[2]")
    public List<WebElement> insightsType;

    @FindBy(xpath = "//h3[contains(text(),'No Data Available')]")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//span[contains(@class,'failed badge')]")
    public WebElement mrSummaryApp;

    @FindBy(className = "nprogress-busy")
    public WebElement loader;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[2]")
    public WebElement getStatusFromTable;

    // Error Tab xpath
    @FindBy(xpath = "//span [@class='icon-caret']")
    public List<WebElement> errorCollapse;

    @FindBy(xpath = "//*[@class=\"error-id\"]")
    public List<WebElement> errorCategories;

    @FindBy(xpath = "//*[@class=\"error-code\"]/p")
    public List<WebElement> errorContents;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[1]")
    public List<WebElement> tagKey;

    @FindBy(xpath = "//table[@class='component-data-tables']/tbody/tr[\" + row + \"]/td[\" + 2 + \"]")
    public WebElement getDataTable;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[2]")
    public List<WebElement> tagValue;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[1]")
    public List<WebElement> metricsKey;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[2]")
    public List<WebElement> metricsValue;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[3]")
    public List<WebElement> metricsDescription;

    @FindBy(xpath = "//body//div//th[7]")
    public WebElement fileColumn;

    @FindBy(xpath = "//table[@class='row-hover component-data-tables margin-bottom']/tbody/tr")
    public List<WebElement> fileTableRows;

    @FindBy(xpath = "//div[@class='col-md-10']//p[1]")
    public WebElement pagination;

    @FindBy(xpath = "//*[name()='svg' and @data-icon='caret-right']")
    public WebElement rightCaretReportCnt;

    @FindBy(xpath = "//*[name()='svg' and @data-icon='backward']")
    public WebElement backwardCaretReportCnt;

    @FindBy(xpath = "//table[@class='component-data-tables']/tbody/tr[1]/td/p")
    public WebElement noDataText;

    @FindBy(xpath = "//*[@id='appTagsTable']//tr/td[2]")
    public List<WebElement> tagDescription;

    @FindBy(xpath = "//*[@id='appTagsTable']/thead//th")
    public List<WebElement> tagTableHeader;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[1]/div[1]/span")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy(xpath = "//span[contains(text(),'Dag Id')]")
    public WebElement singleJobHeader;

    @FindBy(xpath = "//*[@id='app-query']/code/span")
    public List<WebElement> programTabData;

    @FindBy(xpath = "//*[@class='component-data-tables']//td/p")
    public WebElement programDataNotFound;

    @FindBy(xpath = "//*[@class='link-item']")
    public WebElement programSourceLink;

    @FindBy(xpath = "//*[@class='link-item']")
    public WebElement programSourceLinkText;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div[3]/div/div/h3")
    public WebElement startTime;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div[4]/div/div/h3")
    public WebElement EndTime;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div[1]/div[1]/div/h3")
    public WebElement Duration;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div[2]/div[1]/div/h3")
    public WebElement DataIO;

    @FindBy(xpath = "//*[@id=\"highcharts-vjvb3sy-64\"]/svg/rect[1]")
    public WebElement Dags;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[1]")
    public WebElement Owner;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[2]")
    public WebElement Cluster;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[3]")
    public WebElement Queue;

    // Logs Tab xpaths
    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/span")
    public List<WebElement> logElementCollapsable;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/a")
    public List<WebElement> logExecutorNames;

    @FindBy(xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/div/p[1]")
    public WebElement logExecutorContents;

    @FindBy(xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/div/p[2]/a/b")
    public WebElement showFullLogLink;

    // Timings Tab xpath
    @FindBy(xpath = "//*[@id='allApps']")
    public List<WebElement> timingsSubTabs;

    @FindBy(xpath = "//*[@class='highcharts-legend']/*[name()='g']//*[name()='g']//*[name()='tspan']")
    public List<WebElement> legendNames;

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

    @FindBy(xpath = "//*[@class='highcharts-button-box']")
    public WebElement backButton;

    @FindBy(xpath = "//*[@class='stg-link link']")
    public List<WebElement> topStages;

    @FindBy(xpath = "//*[@class='highcharts-series-group']//*[name()='path' and  contains(@class, 'highcharts-drilldown-point')]")
    public WebElement driverDrillDown;

    //Executor Tab xpath
    @FindBy(xpath = "//*[@id=\"dagContainer\"]/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']")
    public List<WebElement> rddBlockList;

    @FindBy(xpath = "//*[@class='consumers']")
    public List<WebElement> rddBlockNumList;

    @FindBy(xpath = "//*[@id=\"highcharts-vjvb3sy-48\"]/svg/rect[1]")
    public WebElement DAGData;

    //Resources Tab xpath
    @FindBy(xpath = "//div[@class=\"component-dashboard\"]//*[@class=\"header\"]/h4")
    public List<WebElement> resourcesGraphTitle;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]")
    public List<WebElement> resourcesAllGraphs;

    @FindBy(xpath = "//div[@id='taskattemptmap']//div[@class='footer']//div//span[contains(text(),'SUCCESS')]")
    public List<WebElement> taskAttMFooterName;

    @FindBy(xpath = "//div[@id='taskattemptmap']//div[@class='footer']//div//span[contains(text(),'SUCCESS')]")
    public List<WebElement> taskAttRFooterName;

    @FindBy(xpath = "//*[@id=\"taskattemptmap\"]/div[3]/div/label/span[2]")
    public List<WebElement> taskAttMFooterVal;

    @FindBy(xpath = "//*[@id=\"taskattemptreduce\"]/div[3]/div/label/span[2]")
    public List<WebElement> taskAttRFooterVal;

    @FindBy(xpath = "//div[@id='highcharts-c3y2yxt-817']//*[local-name()='svg']//*[name()='text'][2]")
    public WebElement resourcesRPieChartInternalVal;

    @FindBy(xpath = "//div[@id='highcharts-c3y2yxt-813']//*[local-name()='svg']//*[name()='text'][2]")
    public WebElement resourcesMPieChartInternalVal;

    @FindBy(xpath = "//*[@class='col-md-12 no-left-gutter no-right-gutter']//*[@role='presentation'][1]")
    public WebElement resourcesMetricsDropDown;

    @FindBy(xpath = "//div[@class='component-dashboard']//div[contains(@class,'col-md-6')]//div[@class='header']/h4")
    public List<WebElement> containerMetricsHeader;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> containerGraph;


    @FindBy(xpath = "//*[@class='select2-results']//ul/li/ul/li")
    public List<WebElement> resourcesMetricsDropDownData;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]//*[name()='path'][2]")
    public List<WebElement> resourcesMetricsPlotGraph;

    @FindBy(xpath = "//*[@class=\"legendclass\"]//span")
    public List<WebElement> resourcesMetricsPlotGraphLegend;

    @FindBy(xpath = "//*[@chart-options='getappResourceUseageOptions']//*[name()='path' and @class='highcharts-tracker-line']")
    public List<WebElement> resourcesMetricsLineGraph;

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

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]//*[name()='g'][1]")
    public List<WebElement> timingGraphList;

    //Load Logs and Diagnostic action
    @FindBy(xpath = "//*[@id='spark-action-container']")
    public WebElement loadAction;

    @FindBy(xpath = "//*[@class='popover-body']/ul/li/h4")
    public List<WebElement> loadActionList;

    @FindBy(xpath = "//*[@class='modal-body']")
    public WebElement loadDiagnosticWin;

    @FindBy(xpath = "(//div[contains(@class,'modal-sec-head')])/h2")
    public WebElement loadDiagnosticWinHeader;

    @FindBy(css = "button.close")
    public WebElement loadDiagnosticWinClose;

    @FindBy(xpath = "//h3[text()='No Errors Found.']")
    public List<WebElement> noErrors;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public MrAppsDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

