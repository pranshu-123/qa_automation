package com.qa.pagefactory.clusters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 * All WebElement which is related with clusters chargeback impala
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class ChargebackImpalaPageObject {

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Chargeback')]")
    public WebElement clusterChargeBackTab;

    @FindBy(xpath = "//b[contains(@class,'dropbtn')]")
    public WebElement chargeBackDropdownOptionsButton;

    @FindBy(xpath = "//a[contains(text(),'Impala')]")
    public WebElement chargeBackDropdownImpalaOption;

    @FindBy(tagName = "h1")
    public WebElement chargeBackPageHeading;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> clustersList;

    @FindBy(xpath = "//table[@id='chargeback-table']/tbody/tr")
    public List<WebElement> resultsGroupedByTableRecords;

    @FindBy(xpath = "//table[@id='finishedImpalaJobsTable']")
    public WebElement impalaJobsTable;

    @FindBy(xpath = "//table[@id='finishedImpalaJobsTable']/tbody/tr")
    public List<WebElement> impalaJobsTableRecords;

    @FindBy(xpath = "//table[@id='finishedImpalaJobsTable']/tbody/tr/td[3]/span[1]")
    public List<WebElement> getUsersFromFinishedImpalaJobsTable;

    @FindBy(xpath = "//span[contains(@class, 'select2-selection__arrow')]")
    public WebElement containerDropdownArrow;

    @FindBy(xpath = "//span[contains(@class,'select2-container')]/span/span/input[contains(@class, 'select2-search__field')]")
    public WebElement clusterDropdownSearchbox;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//li")
    public List<WebElement> listOfClusters;

    @FindBy(xpath = "//span[contains(@class, 'select2-selection--multiple')]")
    public WebElement groupBySearchBox;

    @FindBy(xpath = "//span[contains(@class, 'select2-results')]/ul/li")
    public List<WebElement> listOfGroupByOptions;

    @FindBy(xpath = "((//div[contains(@class,'dashboard-sec')])[last()]//div[contains(@class,'pie-legend')])[2]//div/span")
    public List<WebElement> userNamesOfMemoryCheckbox;

    @FindBy(xpath = "((//div[contains(@class,'dashboard-sec')])[last()]//div[contains(@class,'pie-legend')])[2]//label/span")
    public List<WebElement> userNameRGBColorOfMemoryCheckbox;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[3]")
    public WebElement parentElementOfMemoryPieChart;

    @FindBy(xpath = "(.//*[local-name() = 'g'])[last()]/*[local-name() = 'text']/*[local-name() = 'tspan']")
    public List<WebElement> childElementsOfMemoryPieChart;

    @FindBy(xpath = "//*[@id='chargeback-table']/tbody/tr/td[4]/div/span[1]")
    public List<WebElement> getMemoryDataFromTable;

    @FindBy(xpath = "//div[@class='col-md-4'][3]//h4/span")
    public WebElement getMemoryDataFromHeader;

    @FindBy(id = "chargebackvcore")
    public WebElement setChargebackVcorePerHour;

    @FindBy(id = "chargebackmemory")
    public WebElement setChargebackMemoryPerHour;

    @FindBy(xpath = "//div/button[@class='applybtn']")
    public WebElement applyButton;

    @FindBy(xpath = "//li[contains(text(),'Real User')]")
    public WebElement realUser;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td[1]//span[1]")
    public List<WebElement> getUsersFromChargebackTable;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td[3]//span[1]")
    public List<WebElement> getUsersCPUHoursFromChargebackTable;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td[3]/div/div[@class='costClass']")
    public List<WebElement> getUsersCPUHoursCostFromChargebackTable;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td[4]//span[1]")
    public List<WebElement> getUsersMemoryHoursFromChargebackTable;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td[4]/div/div[@class='costClass']")
    public List<WebElement> getUsersMemoryHoursCostFromChargebackTable;

    @FindBy(xpath = "(//div[contains(@class,'dashboard-module')])[2]//h4/span")
    public WebElement CPUHoursFromGraphHeader;

    @FindBy(xpath = "//div[@class='col-md-4'][3]//h4/span")
    public WebElement MemoryHoursFromGraphHeader;

    @FindBy(xpath = "//div[@class='col-md-4'][2]//h4/span")
    public WebElement cpuGraphHeader;

    @FindBy(xpath = "//div[@class='col-md-4'][1]//h4/span")
    public WebElement donutchart;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[3]/div/div/div/div[2]/div[1]/div[1]/h2")
    public WebElement impalajobs;

    @FindBy(xpath = "//*[@id="app"]/div/div[1]/div/div/div[3]/div/div/div/div[2]/div[1]/div[1]/h2")
    public WebElement Chargebacktable;

    @FindBy(xpath = "//div[@class='col-md-4'][1]//h4/span")
    public WebElement JobsFromGraphHeader;

    @FindBy(css = "div#highcharts-bpp6qdh-101.highcharts-container svg.highcharts-root g.highcharts-label.highcharts-no-data text tspan")
    public WebElement jobsNoDataAvailableText;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectgroup;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectQueue;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectdept;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectproject;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectprojectgroup;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectrealUser;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectdbs;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectinput;

    @FindBy(xpath= "//table[@id='chargeback-table']/tbody/tr[1]/td[1]//span")
    public WebElement select1Row1ColumnFromGroupByTable;

    @FindBy(xpath= "//div[@class='chargebackdrill']")
    public List<WebElement> listChargeBackDrillFromGroupByFilters;

    @FindBy(xpath="//table[@id='chargeback-table']//tbody/tr/td[2]/div/span[1]")
    public List<WebElement> getJobsCountFromChargebackTable;

    @FindBy(xpath = "//button[@class='btn-csv']")
    public WebElement downloadCSV;

    public ChargebackImpalaPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
