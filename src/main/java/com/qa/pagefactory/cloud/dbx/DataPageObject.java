package com.qa.pagefactory.cloud.dbx;

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

    @FindBy(xpath = "(//label[contains(text(),'Metastore')]/following-sibling::span)[1]")
    public WebElement metastoreParentElement;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> dropdownValues;

    @FindBy(css = "svg > g.highcharts-label > text > tspan")
    public List<WebElement> noDataToDisplayCharts;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]")
    public WebElement dataTable;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> tableHeadings;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr")
    public List<WebElement> tableRows;

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

    @FindBy(xpath = "//section/div[contains(@class,'status-title')]/span[@class='title']")
    public List<WebElement> analysisRecommendationAndInsightsTitle;

    @FindBy(xpath = "//div[contains(text(),'Total Partitions')]")
    public WebElement totalPartitionsLabel;

    @FindBy(xpath = "//div[contains(@class,'dashboard-module')]")
    public List<WebElement> usersAppsAndSizeGraphs;

    @FindBy(css = "body > div.highcharts-tooltip-container > svg > g > text > tspan:nth-child(4)")
    public List<WebElement> graphsTooltips;

    @FindBy(xpath = "//div[contains(@class,'vue-slider-dot-handle')]")
    public List<WebElement> labelTablesSlider;

    @FindBy(xpath = "//div[@class='vue-slider-dot-handle']/following-sibling::div/div/span")
    public List<WebElement> slidersTooltipValues;

    @FindBy(xpath = "//h3[@class='expandable-header']/button")
    public WebElement tableStateSettings;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement modalCloseButton;

    @FindBy(xpath = "//a[contains(text(),'Save Rules')]")
    public WebElement saveRulesButton;

    @FindBy(xpath = "//div[@class='filter-item']//p")
    public List<WebElement> tablesFilterItems;

    @FindBy(xpath = "//td/p[contains(text(),'No Tables available')]")
    public List<WebElement> noTablesAvailableElement;

    public DataPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
