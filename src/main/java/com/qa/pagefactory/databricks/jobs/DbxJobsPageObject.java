package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxJobsPageObject {

    @FindBy(xpath = "//*[@id='statusContainer']/span[1]")
    public WebElement appStatus;

    @FindBy(xpath = "(//div[@class='right text-white']//span)[2]")
    public WebElement appJobId;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[2]")
    public List<WebElement> tagValue;

    @FindBy(xpath = "//*[@class=\"error-code\"]/p")
    public List<WebElement> errorContents;

    @FindBy(xpath = "//span [@class='icon-caret']")
    public List<WebElement> errorCollapse;

    @FindBy(xpath = "//*[@class=\"error-id\"]")
    public List<WebElement> errorCategories;

    @FindBy(xpath = "(//h2[@class='text-white']//span)[3]")
    public WebElement appName;

    @FindBy(xpath = "//*[@id=\"app\"]//div[2]/div[1]//div[1]/div[2]/h4/span/b")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy(xpath = "(//div[@id='scrollableMenu'])/ul/li/a")
    public List<WebElement> appJobsTabs;

    @FindBy(xpath = "//*[@id='appTagsTable']/thead//th")
    public List<WebElement> tagTableHeader;

    @FindBy(xpath = "//*[@id='io_metrics']//tbody/tr/td[1]")
    public List<WebElement> tagKey;

    @FindBy(xpath = "//table[@class='component-data-tables']/tbody/tr[\" + row + \"]/td[\" + 2 + \"]")
    public WebElement getDataTable;

    @FindBy(xpath = "//*[@id=\"taskattempts\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesPieChartInternalVal;

    @FindBy(xpath = "//*[@id=\"taskattemptmap\"]//*[name()='svg']/*[name()='text' and @class='highcharts-subtitle']/*")
    public WebElement resourcesReducePieChartInternalVal;

    @FindBy(xpath = "//span[@class='start']")
    public WebElement appStartTime;

    @FindBy(css = "h2[class='text-white'] button[class='close']")
    public WebElement loadWinClose;

    @FindBy(xpath = "//div[@class='close']")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[1]")
    public List<WebElement> lastRunStatusList;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[2]")
    public List<WebElement> lastJobID;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[3]")
    public List<WebElement> lastJobName;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[4]")
    public List<WebElement> lastClusterName;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[5]")
    public List<WebElement> lastWorkspace;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[6]")
    public List<WebElement> lastUser;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[7]")
    public List<WebElement> lastStartTime;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[8]")
    public List<WebElement> lastRunCount;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[9]")
    public List<WebElement> lastDuration;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[10]")
    public List<WebElement> lastRead;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[11]")
    public List<WebElement> lastWrite;

    @FindBy(xpath = "//*[@id=\"pipelinesList-body\"]/tr/td[12]")
    public List<WebElement> lastCost;

    @FindBy(xpath = "//div[@class='right text-white']//span[1]")
    public WebElement appUserName;

    @FindBy(xpath = "//*[@id='taskattemptreduce']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttReduceFooterName;

    @FindBy(xpath = "//*[@id='taskattemptreduce']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttReduceFooterVal;

    @FindBy(xpath = "//*[@id='SummaryDetails']/div/div[2]/div[1]/div/h3")
    public WebElement appDuration;

    @FindBy(xpath = "//*[@id='taskattemptmap']//*[div and @class='footer']/div/*/span[1]")
    public List<WebElement> taskAttFooterName;

    @FindBy(xpath = "//*[@id='taskattemptmap']//*[div and @class='footer']/div/*/span[2]")
    public List<WebElement> taskAttFooterVal;

    @FindBy(xpath = "//div[@class=\"component-dashboard\"]//*[@class=\"header\"]/h4")
    public List<WebElement> resourcesGraphTitle;

    @FindBy(xpath = "//*[@class=\"highcharts-series-group\"]")
    public List<WebElement> resourcesAllGraphs;

    @FindBy(xpath = "(//div[@class='right text-white']//span)[3]")
    public WebElement appWorkSpace;

    @FindBy(xpath = "(//h2[@class='text-white']//span)[3]")
    public WebElement appJobName;

    @FindBy(xpath = "(//div[@class='right text-white']//span)[2]")
    public WebElement appclusterName;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[9]")
    public WebElement getRead;

    @FindBy(xpath = "(//h2[@class='text-white']//span)[3]")
    public WebElement appRunCount;

    @FindBy(xpath = "(//div[@class='right text-white']//span)[2]")
    public WebElement appClusterName;

    @FindBy(xpath = "//tbody/tr/td[1]/span[1]")
    public WebElement status;

    @FindBy(xpath = "//tbody/tr/td[2]/span[1]")
    public WebElement jobId;

    @FindBy(xpath = "//tbody/tr/td[3]/span[1]")
    public WebElement jobName;

    @FindBy(xpath = "//tbody/tr/td[4]/span[1]")
    public WebElement clusterName;

    @FindBy(xpath = "//tbody/tr/td[7]")
    public WebElement startTime;

    @FindBy(xpath = "//tbody/tr/td[6]/span[1]")
    public WebElement userName;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[9]/div[2]/span[1]")
    public WebElement duration;

    @FindBy(xpath = "//tbody/tr/td[8]/span[1]")
    public WebElement runCount;

    @FindBy(xpath = "//tbody/tr/td[5]/span[1]")
    public WebElement workSpace;

    @FindBy(css = "tbody#RunsListAll-body")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//tbody/tr/td[1]")
    public WebElement clickOnStatus;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[2]/div/div[2]/div[3]/div/div/section/div[1]/span[1]")
    public List<WebElement> analysisCollapse;

    @FindBy(xpath = "//*[@class=\"status-title\"]//*[@class=\"badge fatal\"]")
    public List<WebElement> insightsType;

    @FindBy(xpath = "//p[normalize-space()='No Recommendations and Insights found.']")
    public List<WebElement> noDatainsightsAppid;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[2]/span[1]")
    public WebElement clickOnJobId;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[3]/span[1]")
    public WebElement clickOnName;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[3]/span[1]")
    public WebElement totalCostValue;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[3]/span[1]")
    public WebElement totalCostDBUValue;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr/td[3]/span[1]")
    public WebElement totalCost;

    @FindBy(xpath = "//span[contains(text(),'Last Run Status')]")
    public WebElement sortLastRunStatus;

    @FindBy(xpath = "//span[contains(text(),'Job ID')]")
    public WebElement sortJobID;

    @FindBy(xpath = "//span[contains(text(),'Job Name')]")
    public WebElement sortJobName;

    @FindBy(xpath = "//span[contains(text(),'Cluster Name')]")
    public WebElement sortClusterName;

    @FindBy(xpath = "//span[text()[normalize-space()='Workspace']]")
    public WebElement sortWorkspace;

    @FindBy(xpath = "//span[text()[normalize-space()='User']]")
    public WebElement sortUser;

    @FindBy(xpath = "//span[contains(text(),'Start Time')]")
    public WebElement sortStartTime;

    @FindBy(xpath = "//span[contains(text(),'Run Count')]")
    public WebElement sortRunCount;

    @FindBy(xpath = "//span[contains(text(),'Duration')]")
    public WebElement sortDuration;

    @FindBy(xpath = "//span[text()[normalize-space()='Read']]")
    public WebElement sortRead;

    @FindBy(xpath = "//span[text()[normalize-space()='Write']]")
    public WebElement sortWrite;

    @FindBy(xpath = "//table[@id='pipelinesList']/thead[1]/tr[1]/th[12]/span[1]")
    public WebElement sortCost;

    @FindBy(xpath = "(//div[@class='component-tabs-primary'])/ul/li/a")
    public List<WebElement> appSummaryTabs;

    @FindBy(className = "close")
    public WebElement closeIcon;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "//div[contains(@class,'check-items-container')]//input//following-sibling::span")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]//div[2]/div[2]//div/p/label/span[2]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getclusterListDropdown;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[1]")
    public WebElement clusterSearchBox;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
    public WebElement select1stCluster;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public WebElement removeCluster;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[6]")
    public List<WebElement> getApplicationClusterId;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxJobsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
