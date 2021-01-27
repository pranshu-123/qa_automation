package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TezLlapAppsDetailsPageObject {

    @FindBy(xpath = "//span[@class='select2-results']/ul/li")
    public List<WebElement> clickQueueBox;

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]/div[2]/div/span/span[1]/span/ul")
    public WebElement filterInput;

    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[4]")
    public WebElement queuesDropdown;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[3]")
    public WebElement queueSearchBox;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[3]")
    public WebElement getUsernameFromTable;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;


    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[8]")
    public WebElement getQueueNameTable;

    @FindBy(xpath = "//span[contains(@class, 'select2-results')]/ul/li")
    public List<WebElement> listOfGroupByOptions;

    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public List<WebElement> filterElements;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li")
    public List<WebElement> dropdownOptions;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[3]")
    public WebElement readUsersDropdown;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[8]")
    public List<WebElement> getApplicationQueue;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[2]//span[1]")
    public List<WebElement> getStatusTypes;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[2]")
    public WebElement getStatusFromTable;

    @FindBy(xpath = "//h3[contains(text(),'No Data Available')]")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//span[(text()='Queue')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByType;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[8]")
    public List<WebElement> getTypesColumnFromTable;


    @FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
    public WebElement globalSearchBox;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection select2-selection--multiple')])[3]")
    public WebElement queueBySearchBox;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/div")
    public WebElement getAppId;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[5]")
    public WebElement getClusterId;

    @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy(xpath = "//span[@class=\"success badge\" and @style=\"margin-top: 16px; margin-left: 10px;\" and text()=\"Success\"]")
    public WebElement appStatus;

    @FindBy(xpath = "//p[normalize-space()='No Configruation Data Available']")
    public WebElement NoConfigruationDataAvailable;

    @FindBy(xpath = "//a[normalize-space()='Configuration']")
    public WebElement configtab;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[2]")
    public WebElement status;

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

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/div")
    public WebElement getAppid;


    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
    public WebElement getHeaderAppId;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//a[@href='#/clusters/overview']")
    public WebElement homeTab;

    @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "(//div[@class='component-tabs-primary'])/ul/li/a")
    public List<WebElement> appSummaryTabs;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
    public List<WebElement> analysisCollapse;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[2]")
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

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[1]")
    public WebElement Owner;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[2]")
    public WebElement Cluster;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[1]/span[3]")
    public WebElement Queue;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[2]")
    public WebElement Status;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
    public List<WebElement> ganttChartHeaders;

    @FindBy(xpath = "//tbody")
    public List<WebElement> DagtableRows;

    @FindBy(xpath = "//table[@class='component-data-tables']//thead//tr")
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

    @FindBy(css = "//*[@class='close pointer']")
    public WebElement loadDiagnosticWinClose;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public TezLlapAppsDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
