package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SparkAppsDetailsPageObject {

    @FindBy(xpath = "//*[@id='app-filter-panel']/div[2]/div[2]/div/div[2]/p/label/span[2]")
    public WebElement failedAppCnt;

    @FindBy (xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/div")
    public WebElement getAppId;

    @FindBy (xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
    public WebElement getHeaderAppId;

    @FindBy (xpath = "//*[@id='app']/div/div/div[1]/div[2]")
    public WebElement closeAppsPageTab;

    @FindBy (xpath = "//*[@id='app-prev-header']/b")
    public WebElement ifAttemptPresent;

    @FindBy (xpath = "//div[@class='highcharts-label highcharts-data-label highcharts-data-label-color-0" +
            " highcharts-tracker']/span")
    public List<WebElement> numAttempts;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[1]/div[2]/h4/span")
    public List<WebElement> leftPaneKPIList;

    @FindBy (xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy (xpath = "(//div[@id='scrollableMenu'])/ul/li/a")
    public List<WebElement> appSummaryTabs;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
    public List<WebElement> analysisCollapse;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[2]")
    public List<WebElement> insightsType;

    //span [@class='icon-caret']
    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/section/div/span")
    public List<WebElement> errorCollapse;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/section/div//a/span")
    public List<WebElement> errorCategories;

    @FindBy (xpath = "//*[@id='appTagsTable']/thead//th")
    public List<WebElement> tagTableHeader;

    @FindBy (xpath = "//*[@id='appTagsTable']/tbody/tr")
    public List<WebElement> tagTableRows;

    @FindBy (xpath = "(//div[@id=\"app\"])/div/div/div[2]/div[1]/div/div[2]/div/ul/li")
    public List<WebElement> component_element;

    @FindBy (xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])")
    public List<WebElement> ganttChartTable;

   // (//div[contains(@class, 'gantt-timeline')])//div/p
    @FindBy (xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//div/p")
    public List<WebElement> ganttChartDuration;

    @FindBy (xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[1]")
    public List<WebElement> ganttChartJobId;

    @FindBy (xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[2]")
    public List<WebElement> ganttChartStartTime;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
    public List<WebElement> ganttChartHeaders;

    @FindBy (xpath = "(//tbody[@id='appNavigation-body'])/tr")
    public List<WebElement> navigationTableRows;

    @FindBy (xpath = "(//thead[@id='appNavigation-head'])/tr/th")
    public List<WebElement> navigationHeaders;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[1]/div[1]/span")
    public List<WebElement> rightPaneKpis;

    @FindBy (xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy (xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy (xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy (xpath = "//*[@id='app-query']/code/span")
    public List<WebElement> programTabData;

    // Configuration Tab xpaths

    @FindBy (xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
    public WebElement configPropNum;

    @FindBy (xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/div[2]/span")
    public List<WebElement> configKeywords;

    @FindBy (xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/span/div/a/span")
    public WebElement resetButton;

    @FindBy (xpath = " //*[@id='appConfiguration']//tbody/tr/td/p")
    public List<WebElement> configPropKey;

    @FindBy (xpath = " //*[@id='appConfiguration']//tbody/tr/td/span")
    public List<WebElement> configPropValue;

    // Logs Tab xpaths
    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/span")
    public List<WebElement> logElementCollapsable;

    @FindBy (xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/a")
    public List<WebElement> logExecutorNames;

    @FindBy (xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/div/p[1]")
    public WebElement logExecutorContents;

    @FindBy (xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div[1]/section/div/div/p[2]/a/b")
    public WebElement showFullLogLink;

    // Timings Tab xpath
    @FindBy (xpath = "//*[@id='allApps']")
    public List<WebElement> timingsSubTabs;

    @FindBy (xpath = "//*[@class='highcharts-container ']/*[name()='svg']/*[name()='g'][2]/*[name()='g']" +
            "/*[name()='g']/*[name()='g']/*[name()='text']/*")
    public List<WebElement> legendNames;

    @FindBy (xpath = "//*[@class=\"highcharts-container \"]/*[name()='svg']/*[name()='text']/*")
    public WebElement timingsTabTitle;

    @FindBy (xpath = "//*[@class=\"highcharts-container \"]/*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='circle']")
    public WebElement pieChart;

    //Executor Tab xpath
    @FindBy (xpath = "//*[@id=\"dagContainer\"]/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']")
    public List<WebElement> rddBlockList;

    @FindBy (xpath = "//*[@id=\"spark_exec_plan\"]/*[name()='svg']")
    public WebElement DAGData;

    //Resources Tab xpath
    @FindBy (xpath = "//*[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[3]/div//h4")
    public List<WebElement> resourcesGraphTitle;

    @FindBy (xpath = "//*[@class=\"highcharts-series-group\"]")
    public List<WebElement> resourcesAllGraphs;

    @FindBy (xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttFooterName;

    @FindBy (xpath = "//*[@id='taskattempts']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttFooterVal;

    @FindBy (xpath = "//*[@id=\"taskattempts\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesPieChartInternalVal;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SparkAppsDetailsPageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
