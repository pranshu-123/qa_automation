package com.qa.pagefactory.clusters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ImpalaPageObject {

    @FindBy(css = "g.highcharts-label.highcharts-tooltip.highcharts-color-0 > text")
    public WebElement memoryTooltip;

    @FindBy(css = "svg > g.highcharts-label.highcharts-tooltip.highcharts-color-undefined > text")
    public WebElement queryTooltip;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[1]")
    public WebElement memoryHighChartContainer;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__arrow')])[2]")
    public WebElement groupByDropdownButton;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueueList;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUserList;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[2]")
    public WebElement queryHighChartContainer;

    public By graphGContents = By.cssSelector("svg > g.highcharts-series-group");

    @FindBy(xpath = "//div[contains(@class,'dashboard-module')]//div/h4[text()='Memory Consumption']//following::div[contains(@class,'footer')][1]//label/span[1]")
    public List<WebElement> memoryGraphToggleCheckboxList;

    @FindBy(xpath = "//div[contains(@class,'dashboard-module')]//div/h4[text()='Memory Consumption']//following::div[contains(@class,'footer')][2]//label/span[1]")
    public List<WebElement> queryGraphToggleCheckboxList;

    @FindBy(xpath = "(//div[contains(@class,'dashboard-module')]//div[contains(@id,'highcharts')])[1]")
    public WebElement parentMemoryConsumptionGraph;

    @FindBy(xpath = "(//*[@id=\"app\"]/div/div/div/div[3]/div[1]/div[1]/div/div/div/div/div[3]/div/label[6]/span[2]")
    public WebElement Nodecheckbox;


    public By checkNoDataOnGraph = By.xpath(
            "(.//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'text']/*[local-name() = 'tspan'])[1]");

    @FindBy(xpath = "(//div[contains(@class,'dashboard-module')]//div[contains(@id,'highcharts')])[2]")
    public WebElement parentQueryGraph;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'text']/*[local-name() = 'tspan'])[6]")
    public WebElement childQueriesGraph;

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Impala')]")
    public WebElement clusterImpalaTab;

    @FindBy(xpath = "//div/table[@id='runningImpalaQueriesTable']/tbody/tr/td//span")
    public WebElement selectimpalatable;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Impala']")
    public WebElement getImpalaPageHeader;

    @FindBy(xpath = "(//div[contains(@class,'daterangepicker_input')]//input[contains(@name,'daterangepicker_start')])[last()]")
    public WebElement customRangeStartDate;

    @FindBy(xpath = "(//div[contains(@class,'daterangepicker_input')]//input[contains(@name,'daterangepicker_end')])[last()]")
    public WebElement customRangeEndDate;

    @FindBy(xpath = "(//div[contains(@class,'range_inputs')]/button[contains(@class,'applyBtn')])[2]")
    public WebElement applyBtnImpalaDatePicker;

    @FindBy(xpath = "(//div[contains(@class,'range_inputs')]/button[contains(@class,'applyBtn')])[last()]")
    public WebElement applyBtn;

    @FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
    public WebElement filterInput;

    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public List<WebElement> filterElements;

    @FindBy(xpath = "//div[@id=\"queryGraph\"]//label")
    public List<WebElement> queriesFooterLabels;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public List<WebElement> filterRemoveElements;

    @FindBy(css = "svg > g.highcharts-axis-labels.highcharts-xaxis-labels")
    public List<WebElement> graphXAxisDateLabels;

    @FindBy(xpath = "(//div[contains(@class,'footer')])[1]//label")
    public List<WebElement> memoryFooterLabels;

    @FindBy(css = "svg > g.highcharts-axis-labels.highcharts-yaxis-labels")
    public List<WebElement> graphYAxisLabels;

    @FindBy(xpath = "//div[contains(@id,'impala-table')]/h2")
    public WebElement impalaQueriesHeader;

    @FindBy(xpath = "//table[@id='runningImpalaQueriesTable']/thead/tr/th")
    public List<WebElement> listImpalaQueriesTableHeaderColumn;

    @FindBy(xpath = "//table[@id='runningImpalaQueriesTable']/tbody/tr")
    public List<WebElement> impalaQueriesTableRecords;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Resources'])")
    public WebElement resourcesTab;

    @FindBy(xpath="(//input[@class='select2-search__field'])[2]")
    public WebElement chargeBackSearchBox;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public WebElement chargeBackSearchFirstField;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__arrow')])[1]")
    public WebElement chargeBackDropdownOptionsButton;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> chargeBacksList;

    @FindBy(css = "a.dropbtn.pointer")
    public WebElement resourceUsagePointer;

    @FindBy(xpath = "//div[contains(@class,'dropdown-content-display')]/a[contains(text(),'Impala')]")
    public WebElement selectImpalaOption;

    public ImpalaPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Get child elements with reference of parent element
    public List<WebElement> getChildElement(WebElement parentElement, By childElement) {
        return parentElement.findElements(childElement);
    }
}
