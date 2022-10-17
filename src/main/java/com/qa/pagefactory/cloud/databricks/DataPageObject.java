package com.qa.pagefactory.cloud.databricks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 * All WebElement which is related with Databricks Data page
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class DataPageObject {

    @FindBy(xpath = "//a[contains(@href,'data/overview')]/span[contains(text(),\"Data\")]")
    public WebElement dataTab;

    @FindBy(xpath = "//a[contains(@href,'data/tables')]/span[contains(text(),\"Tables\")]")
    public WebElement dataTablesTab;

    @FindBy(xpath = "(//label[contains(text(),'Workspace')]/following-sibling::span)[1]//span[contains(@class,'select2-selection__arrow')]")
    public WebElement workspaceDropdown;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    public String filterByValues = "//li[contains(text(),'%s')]";

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[2]")
    public WebElement select1stCluster;

    @FindBy(xpath = "(//label[contains(text(),'Metastore')]/following-sibling::span)[1]")
    public WebElement metastoreParentElement;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public List<WebElement> selectType;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> dropdownValues;

    @FindBy(css = "svg > g.highcharts-label > text > tspan")
    public List<WebElement> noDataToDisplayCharts;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]")
    public List<WebElement> dataTable;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> tableHeadings;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//tbody[1]/tr/td[13]/a[1]/span[1]/span[1]")
    public List<WebElement> parentRows;

    @FindBy(xpath = "//input[@placeholder=\"Search\"]")
    public WebElement searchBoxForTableData;

    @FindBy(xpath = "//input[@class=\"search-tbl-btn search pointer\"]")
    public WebElement searchBoxButton;

    @FindBy(xpath = "(//div[@class='filter-section'])[1]//a[contains(text(),'Table state')]")
    public WebElement tableStateFilterLabel;

    @FindBy(xpath = "(//div[@class='filter-section'])[1]/div//label")
    public List<WebElement> tableStateOptionsLabel;

    @FindBy(xpath = "(//div[@class='filter-section'])[2]//a[contains(text(),'Table Events')]")
    public WebElement tableEventsFilterLabel;

    @FindBy(xpath = "(//div[@class='filter-section'])[2]/div//label")
    public List<WebElement> tableEventsOptionsLabel;

    @FindBy(xpath = "//p[contains(text(),'Total tables')]")
    public WebElement totalTables;

    @FindBy(xpath = "//div[contains(text(), 'Download CSV')]/parent::a")
    public WebElement downloadCSVButton;

    @FindBy(tagName = "h2")
    public WebElement tableDetailsPageHeading;

    @FindBy(xpath = "//a[contains(text(),'Tables')]")
    public WebElement backToTablesLinkOnTableDetails;

    @FindBy(xpath = "//td[15]/a[1]/span[1]")
    public WebElement moreInfo;

    @FindBy(xpath = "//div[contains(@class,'kpi-box')]//div[@class='row']//div/h3")
    public List<WebElement> usersAppsSizePartitionsValues;

    @FindBy(xpath = "//div[contains(@class,'kpi-box row')]//div/div")
    public List<WebElement> generalKPIsStorageKPIsValues;

    @FindBy(xpath = "//a[contains(text(),'Analysis')]")
    public WebElement analysisTabOnTableDetails;

    @FindBy(xpath = "//a[contains(text(),'Applications')]")
    public WebElement applicationTabOnTableDetails;

    @FindBy(xpath = "//a[contains(text(),'Partition Detail')]")
    public WebElement partitionDetailTabOnTableDetails;

    @FindBy(xpath = "//a[@href='#/data/tables']")
    public WebElement closeTab;

    @FindBy(xpath = "//section/div[contains(@class,'status-title')]/span[@class='title']")
    public List<WebElement> analysisRecommendationAndInsightsTitle;

    @FindBy(xpath = "//div[contains(text(),'Total Partitions')]")
    public WebElement totalPartitionsLabel;

    @FindBy(xpath = "//div[contains(@class,'dashboard-module')]")
    public List<WebElement> displayedGraphs;

    @FindBy(css = "body > div.highcharts-tooltip-container >svg>g")
    public List<WebElement> graphsTooltips;

    @FindBy(xpath = "//div[contains(@class,'vue-slider-dot-handle')]")
    public List<WebElement> labelTablesSlider;

    @FindBy(xpath = "//div[@class='vue-slider-dot-handle']/following-sibling::div/div/span")
    public List<WebElement> slidersTooltipValues;

    @FindBy(xpath = "//h3[@class='expandable-header']/button")
    public WebElement tableStateSettings;

    @FindBy(xpath = "//a[contains(@class,'icon-gear')]")
    public WebElement settingsIcon;

    @FindBy(css = "button.close")
    public WebElement modalCloseButton;

    @FindBy(xpath = "//a[contains(text(),'Save Rules')]")
    public WebElement saveRulesButton;

    @FindBy(xpath = "//div[@class='filter-item']//p")
    public List<WebElement> tablesFilterItems;

    @FindBy(xpath = "//td/p[contains(text(),'No Tables available')]")
    public List<WebElement> noTablesAvailableElement;

    @FindBy(xpath = "//p[contains(text(),'No table KPIs available.')]")
    public List<WebElement> noTableKPIsElements;

    @FindBy(xpath = "//div[@class='row no-gutters container-fluid']")
    public List<WebElement> tableKPIsElements;

    @FindBy(xpath = "//p[contains(text(),'No partition KPIs available.')]")
    public List<WebElement> noPartitionKPIsElements;

    @FindBy(xpath = "//h2[contains(text(),'Tables KPIs')]/following-sibling::div[1]//" +
            "div[contains(@class,'kpi-parent')]/div[contains(@class,'title-val')]")
    public List<WebElement> tablesKPIsLastDayTitles;

    @FindBy(xpath = "//h2[contains(text(),'Partitions KPIs')]/following-sibling::div[1]//" +
        "div[contains(@class,'kpi-parent')]/div[contains(@class,'title-val')]")
    public List<WebElement> partitionsKPIsLastDayTitles;

    @FindBy(xpath = "//h2[contains(text(),'Tables KPIs')]/following-sibling::div[1]" +
            "//div[contains(@class,'kpi-parent')]/div/div/h2")
    public List<WebElement> tablesKPIsLastDayValues;

    @FindBy(xpath = "//h2[contains(text(),'Partitions KPIs')]/following-sibling::div[1]" +
        "//div[contains(@class,'kpi-parent')]/div/div/h2")
    public List<WebElement> partitionsKPIsLastDayValues;

    @FindBy(xpath = "//div[@class='footer']")
    public WebElement tableStateGraphFooter;

    @FindBy(css = "svg > g > g.highcharts-series.highcharts-series-0.highcharts-pie-series.highcharts-tracker")
    public WebElement tableStatePieChart;

    @FindBy(css = "svg > text.highcharts-subtitle > tspan")
    public WebElement pieChartValue;

    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> columnCheckboxes;

    @FindBy(xpath = "//tbody[1]/tr/td[4]/div[1]/div[1]/a[2]")
    public WebElement applicationId;

    @FindBy(xpath = "//h2/span[1]")
    public WebElement applicationDetailsHeading;

    @FindBy(xpath = "(//div[@class='right text-white']//span)[2]")
    public WebElement applicationClusterId;

    @FindBy(xpath = "//div[@class='close']")
    public WebElement closeApplicationsDetailsButton;

    @FindBy(xpath = "//a[@class='home-link']")
    public WebElement homeTab;

    @FindBy(xpath = "(//h2/following-sibling::div)[1]")
    public WebElement ownerClusterQueueDetailsApplicationPage;

    @FindBy(css = "h4.job-times")
    public WebElement startEndDurationDetailsApplicationPage;

    @FindBy(css = "div#statusContainer>span")
    public WebElement statusValueApplicationPage;

    @FindBy(xpath = "//div[@id=\"SummaryDetails\"]//h3")
    public List<WebElement> instanceSummaryValues;

    @FindBy(css = "div.highcharts-container")
    public List<WebElement> applicationDetailsPageCharts;

    @FindBy(xpath = "//span[contains(@class,'icon-caret')]")
    public WebElement dropdownIconOnApplicationDetails;

    @FindBy(xpath = "//div[contains(@class,'component-dropdown')]/ul/li")
    public List<WebElement> leftGraphOptions;

    @FindBy(xpath = "//span/a[contains(text(),'Navigation')]")
    public WebElement navigationTabApplicationDetailsPage;

    @FindBy(css = "div.gantt-parent")
    public WebElement ganttChart;

    @FindBy(xpath = "//a[contains(text(),'Analysis')]")
    public WebElement analysisTabApplicationDetails;

    @FindBy(xpath = "//a[contains(text(),'Resources')]")
    public WebElement resourcesTabApplicationDetails;

    @FindBy(xpath = "//a[contains(text(),'Daggraph')]")
    public WebElement dagGraphTabApplicationDetails;

    @FindBy(xpath = "//a[contains(text(),'Errors')]")
    public WebElement errorsTabApplicationDetails;

    @FindBy(xpath = "//a[contains(text(),'Tags')]")
    public WebElement tagsTabApplicationDetails;

    @FindBy(xpath = "//td/p[contains(text(),'No Recommendations and Insights found.')]")
    public List<WebElement> noRecommendationAndInsightsElements;

    @FindBy(xpath = "//h3[contains(text(),'No Errors Found.')]")
    public List<WebElement> noErrorsFoundElements;

    @FindBy(xpath = "//h3[contains(text(),'No Tags found.')]")
    public List<WebElement> noTagsFoundElements;

    public DataPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
