package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TezAppsDetailsPageObject {

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr[1]/td[4]/a[2]")
    public WebElement getAppId;

    @FindBy(xpath = "//table[@id='allApps']/thead//th[10]/a[1]")
    public WebElement sortByReadApp;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//*[@id='allApps-head']/tr/th[8]/a")
    public WebElement sortByDurationApp;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[5]")
    public WebElement getClusterId;

    @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy(xpath = "//*[@id='statusContainer']/span[1]")
    public WebElement appStatus;

    @FindBy(xpath = "//p[normalize-space()='No Configruation Data Available']")
    public WebElement NoConfigruationDataAvailable;

    @FindBy(xpath = "//a[normalize-space()='Configuration']")
    public WebElement Configtab;

    @FindBy(xpath = "//*[@id='allApps-body']/tr/td[2]")
    public WebElement Status;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr/td[9]")
    public WebElement queueJobsPage;

    @FindBy(xpath = "//*[@id='app']//div[1]/div[2]/span[3]")
    public WebElement queueAppPage;


    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/span[1]")
    public WebElement clusterAppPage;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[3]")
    public WebElement getUsername;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getTypeFromTable;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement getAppname;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement getAppnameToolTips;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement AppnameText;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/h2/span[2]/span/span")
    public WebElement Appname;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[6]")
    public WebElement getStartTime;

    @FindBy(xpath = "//div[@class='component-section-header theme-light']//span[2]")
    public WebElement getEndTimeAppPage;

    @FindBy(xpath = "//div[@class='component-section-header theme-light']//span[1]")
    public WebElement getStartTimeAppPage;

    @FindBy(xpath = "//div[@id='SummaryDetails']//div[3]")
    public WebElement getDbname;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[7]")
    public WebElement getduration;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[9]")
    public WebElement getRead;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[10]")
    public WebElement getWrite;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr[1]/td[4]/a[2]")
    public WebElement getAppid;

    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
    public WebElement getHeaderAppId;

    @FindBy(css = "button.close")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//a[@href='#/clusters/overview']")
    public WebElement homeTab;

    @FindBy(xpath = "//span[(text()='Insights')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByParentApp;

    @FindBy(xpath = "//span[(text()='Write')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByWriteApp;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[9][@class='text-left']")
    public List<WebElement> checkReadApp;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[10][@class='text-left']")
    public List<WebElement> checkWriteApp;

    @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "(//div[@class='component-tabs-primary'])/ul/li/a")
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

    @FindBy(xpath = "//a[normalize-space()='Configuration']")
    public WebElement configurationTab;

    @FindBy(xpath = "//a[normalize-space()='Tags']")
    public WebElement tagsTab;

    @FindBy(xpath = "//*[@id='appTagsTable']/tbody/tr")
    public List<WebElement> tagTableRows;

    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[2]/div[1]/div/div[2]/div/ul/li")
    public List<WebElement> component_element;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])")
    public List<WebElement> ganttChartTable;

    // (//div[contains(@class, 'gantt-timeline')])//div/p
    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//div/p")
    public List<WebElement> ganttChartDuration;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[1]")
    public List<WebElement> ganttChartJobId;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[2]")
    public List<WebElement> ganttChartStartTime;

    @FindBy(xpath = "(//span[contains(@class,'start')])")
    public WebElement startTime;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/h2/span[2]/span/span")
    public WebElement jobId;

    @FindBy(xpath = "//*[@id=\"scrollableMenu\"]/ul/li[2]/a")
    public WebElement queryTab;

    @FindBy(xpath = "(//span[contains(@class,'start')])[2]")
    public WebElement EndTime;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div/div[1]/div/h3")
    public WebElement Duration;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]/div/div[2]/div[1]/div/h3")
    public WebElement DataIO;

    @FindBy(xpath = "//*[@id=\"highcharts-vjvb3sy-64\"]/svg/rect[1]")
    public WebElement Dags;

    @FindBy(xpath = "//*[@id='app']/div/div/div[1]/div/span[1]")
    public WebElement Owner;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div/span[2]")
    public WebElement Cluster;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div/span[3]")
    public WebElement Queue;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
    public List<WebElement> ganttChartHeaders;

    @FindBy(xpath = "//tbody")
    public List<WebElement> DagtableRows;

    @FindBy(xpath = "//body//div//th")
    public List<WebElement> navigationHeaders;

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


    // Configuration Tab xpaths

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
    public WebElement configPropNum;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/div[2]/span")
    public List<WebElement> configKeywords;

    @FindBy(xpath = "//div[contains(@class,'cta-secondary')]/a/span[text()='RESET']")
    public WebElement resetButtonAppDetails;

    @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/p")
    public List<WebElement> configPropKey;

    @FindBy(xpath = " //*[@id='appConfiguration']//tbody/tr/td/span")
    public List<WebElement> configPropValue;

    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/span/div/a/span")
    public WebElement resetButton;


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
    @FindBy(xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div//h4")
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

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]//*[name()='path'][2]")
    public List<WebElement> resourcesMetricsPlotGraph;

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

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr[1]/td[4]/span")
    public WebElement appNameToolTip;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public TezAppsDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

