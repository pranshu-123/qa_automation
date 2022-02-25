package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxSummaryPageObject {
    @FindBy(className = "close")
    public WebElement closeIcon;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[14]/button[2]")
    public WebElement sparkIcon;

    @FindBy(xpath = "//*[@id='app']//*[@class='right']/span")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
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

    @FindBy(css = "button.close")
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

    @FindBy(xpath = "//*[@id='app']//section/div/span")
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

    @FindBy(xpath = "//*[@class='select2-results']//ul/li/ul/li")
    public List<WebElement> resourcesMetricsDropDownData;

    @FindBy(xpath = "//*[@chart-options='getappResourceUseageOptions']//*[name()='path' and @class='highcharts-tracker-line']")
    public List<WebElement> resourcesMetricsLineGraph;

    @FindBy(xpath = "//*[@class=\"legendclass\"]//span")
    public List<WebElement> resourcesMetricsPlotGraphLegend;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]")
    public List<WebElement> resourcesAllGraphs;

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
