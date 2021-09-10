package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author sarbashree ray
 * All date schedule date related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class WorkloadPageObject {

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Workload'])")
    public WebElement workloadTab;

    @FindBy(xpath = "//a[contains(text(),'vCore Hours')]")
    public WebElement workloadDropdownvCoreHours;

    @FindBy(css = "g:nth-child(1) > rect")
    public WebElement memoryTooltip;

    @FindBy(xpath = "//*[@id=\"heat-cont\"]/svg")
    public WebElement workloadHighheatcont;

    @FindBy(xpath= "//div[@class='panel-heading']")
    public  WebElement workloadHeader;

    @FindBy(xpath = "//a[contains(text(),'Memory Hours')]")
    public WebElement workloadDropdownMemoryHours;

    @FindBy(xpath = "//a[contains(text(),'Impala')]")
    public WebElement workloadDropdownImpala;

    @FindBy(xpath = "//a[contains(text(),'Yarn')]")
    public WebElement workloadDropdownYarn;

    @FindBy(css = "div#cluster-workload b:nth-child(3)")
    public WebElement timerangeMessageElement;

    @FindBy(css = "body div b:nth-child(3)")
    public WebElement currentmonthHeader;

    @FindBy(css=".select-workload-type~.select2 .selection .select2-selection__arrow")
    public WebElement workloadDropdownOption;

    @FindBy(css = ".select-view-by~ .select2 .selection .select2-selection__arrow")
    public WebElement selectViewBy;

    @FindBy(css = ".select-aggregate-by ~ .select2 .selection .select2-selection__arrow")
    public WebElement aggregateBy;

    @FindBy(xpath = ".select-group-by ~ .select2 .selection .select2-selection__arrow")
    public WebElement selectGroupBy;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public List<WebElement> selectDropdown;

    @FindBy(xpath="//input[@role='searchbox']")
    public WebElement clusterSearchBox;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public WebElement clusterSearchFirstField;

    @FindBy(xpath = "//*[@class='close pointer']")
    public WebElement loadWinClose;

    @FindBy(xpath = "//*[@id='app']/div/div/div[1]/div[2]")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//*[@id=\"radio-btn-opt\"]/span[1]")
    public WebElement viewByMonth;

    @FindBy(xpath = "(//a[contains(text(),'Day')])[1]")
    public WebElement viewByDay;

    @FindBy(xpath = "//a[normalize-space()='Hour']")
    public WebElement viewByHour;

    @FindBy(xpath = "(//a[contains(text(),'Hour')])[3]")
    public WebElement vcoreHour;

    @FindBy(xpath = "//a[contains(text(),'Hour/Day')]")
    public WebElement viewByHourDay;

    @FindBy(xpath = "//div[@class='pull-right']//span[@class='badge pending']")
    public WebElement viewBySum;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[1]")
    public WebElement HourHighChartContainer;

    @FindBy(xpath = "//*[name()='rect' and contains(@class,'r5 hover_c')]")
    public WebElement viewByAverage;

    @FindBy(xpath = "//*[@id=\"heat-cont\"]")
    public List<WebElement> ViewByMonthCal;

    @FindBy(xpath = "(//*[name()='svg'])[4]//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']")
    public List<WebElement> tspanCal;

    @FindBy(xpath = "(//*[name()='svg'])[4]//*[local-name()='g']")
    public List<WebElement> dateRange;

    @FindBy(xpath = "//*[@id=\"heat-cont\"]/svg/svg/svg/svg/g[28]/rect")
    public WebElement ViewByCal;

    @FindBy(css = "g:nth-child(1) > rect")
    public WebElement customRangeStartDate;

    @FindBy(css = "g:nth-child(1) > rect")
    public WebElement customRangeEndDate;

    @FindBy(xpath = "//*[@id=\"kindlist\"]/table/tbody/tr[1]/td[1]")
    public List<WebElement> workloadTabJobsTable;

    @FindBy(xpath = "//*[@id=\"kindlist\"]/table/tbody/tr[1]")
    public List<WebElement> workloadJobsTableRecords;

    @FindBy(xpath = "//*[@id=\"kindlist\"]/table/tbody/tr/td[1]")
    public List<WebElement> getUsersFromworkloadTable;

    @FindBy(xpath = "//*[@id=\"kindlist\"]/table/thead/tr/th[1]")
    public List<WebElement> workloadJobsTableHeaderNames;

    @FindBy(xpath = "//*[@id=\"kindTable\"]/div[1]")
    public WebElement jobtableHeader;

    @FindBy(css = "body div b:nth-child(3)")
    public WebElement timeRange;

    public WorkloadPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


}
