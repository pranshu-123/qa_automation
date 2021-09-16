package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class AppDetailsPageObject {

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]/following-sibling::div[contains(@class,'filter-items')]//label")
    public List<WebElement> applicationTypeLabels;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr/td[4]/a[2]")
    public WebElement applicationTypeShowOnly;

    @FindBy(id = "allApps")
    public WebElement allAppsTable;

    @FindBy(xpath = "//div[contains(@class,'close')]")
    public WebElement closeModalbutton;

    @FindBy(xpath = "//h2/span[2]/span/span")
    public WebElement jobUUID;

    @FindBy(xpath = "(//h2/span)[1]")
    public WebElement appTypeAppDetails;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[1]")
    public WebElement jobStartTime;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[2]")
    public WebElement jobEndTime;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[3]")
    public WebElement jobDuration;

    @FindBy(xpath = "//h5[contains(text(),'DATA I/O')]/following-sibling::div")
    public WebElement jobReadWriteData;

    @FindBy(xpath = "//div[@id=\"statusContainer\"]/span[1]")
    public WebElement jobStatus;

    @FindBy(id = "appNavigation")
    public WebElement impalaFragmentTable;

    @FindBy(xpath = "//table[@id='appNavigation']/thead/tr/th")
    public List<WebElement> impalaFragmentTableHeadings;

    @FindBy(xpath = "//table[@id='appNavigation']/tbody/tr")
    public List<WebElement> impalaFragmentRows;

    @FindBy(xpath = "//div[contains(@class,'opnode')]")
    public List<WebElement> impalaQueryPlanComponents;

    @FindBy(xpath = "//a[@content=\"Instance View\"]")
    public WebElement impalaIFragmentnstanceView;

    @FindBy(xpath = "//table[@id='impalaOpInstanceTable']/tbody/tr")
    public List<WebElement> impalaFragmentInstanceViewsRows;

    @FindBy(xpath = "//a[contains(text(),'Operators')]")
    public WebElement operatorsTabImpala;

    @FindBy(xpath = "//a[contains(text(),'Gantt Chart')]")
    public WebElement ganttChartTabImpala;

    @FindBy(xpath = "//div[contains(@class,'gantt-timeline')]/div[1]")
    public List<WebElement> ganttChartIds;

    @FindBy(xpath = "//div[@class='fragment-dag-new']//*[local-name() = 'svg']//*[local-name() = 'g']//*[local-name() = 'text']//*[local-name() = 'tspan' and contains(text(),'$Fragment')]")
    public List<WebElement> getFragmentsFromQueryPlan;

    @FindBy(xpath = "//div[@class='fragment-dag-new']//*[local-name() = 'svg']//*[local-name() = 'g' and contains(@class,'label')]//div/span")
    public List<WebElement> getOperatorsFromQueryPlan;

    @FindBy(xpath = "//table[@id='allApps']/tbody/tr")
    public List<WebElement> getImpalaJobs;

    @FindBy(xpath = "//ul[@class='col-md-12']//a[text() = 'Fragments']")
    public WebElement impalaFragmentTab;

    @FindBy(xpath = "//ul[@class='col-md-12']//a[text() = 'Operators']")
    public WebElement impalaOperatorsTab;

    @FindBy(xpath = "//div[@class='component-tabs-primary']//li/a[normalize-space(text()) = 'Query Plan']")
    public WebElement impalaQueryPlanTab;

    @FindBy(xpath = "//div[@class='component-tabs-secondary']//li//a[normalize-space(text()) = 'Fragment View']")
    public WebElement queryPlanFragmentTab;

    @FindBy(xpath = "//div[@class='component-tabs-secondary']//li//a[normalize-space(text()) = 'Operator View']")
    public WebElement queryPlanOperatorTab;

    @FindBy(xpath = "//table[@id='appNavigation']/tbody/tr")
    public List<WebElement> getFragmentIdRows;

    @FindBy(xpath = "//table[@id='impalaOperatorList']/tbody/tr")
    public List<WebElement> getOperatorTypesRow;

    @FindBy(xpath = "//a[contains(text(),'Status')]")
    public WebElement statusToggleLeftpane;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "(//div[@class='filter-section'])[3]//label")
    public List<WebElement> statusList;

    @FindBy(xpath = "(//table[@id='allApps']/tbody/tr/td)[3]")
    public WebElement firstRow;

    @FindBy(className = "close")
    public List<WebElement> closeIcon;

    @FindBy(xpath = "//table[@id='appTagsTable']/tbody/tr/td[1]")
    public List<WebElement> getTagNames;

    @FindBy(xpath = "//table[@id='appTagsTable']/tbody/tr/td[2]")
    public List<WebElement> getTagDescription;

    @FindBy(xpath = "//div[@class='component-tabs-primary']//li/a[normalize-space(text()) = 'Tags']")
    public WebElement impalaTagsTab;

    @FindBy(xpath = "(//table[@id='allApps']//tbody/tr)[1]/td")
    public List<WebElement> getTableValuesOfImpalaApp;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__arrow')])[1]")
    public WebElement chargebackDropdown;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown')]//li[normalize-space(text())='Impala']")
    public WebElement impalaChargeback;

    @FindBy(xpath = "(//span[@class='select2-selection__arrow'])[2]")
    public WebElement impalaClusterDrodown;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown')]//input")
    public WebElement clusterSearchBox;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public WebElement select1stOption;

    @FindBy(xpath = "(//div[@class='col-md-4'])[1]//h4/span")
    public WebElement impalaChargebackJobCount;

    @FindBy(xpath = "//div[@class='opnode']")
    public List<WebElement> operatorViewOpCount;

    @FindBy(id = "runningAppList")
    public WebElement runningAppsTab;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]//p[normalize-space(text())='No Fragments Data Available']")
    public List<WebElement> noFragments;

    @FindBy(xpath = "//a[@id='inefficientApps']")
    public WebElement inefficientAppsTab;

    @FindBy(xpath = "//a[contains(.,'Application Events')]")
    public WebElement eventToggleLeftPane;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')])[2]/following-sibling::div[contains(@class,'filter-items')]//label")
    public List<WebElement> eventTypeLabels;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')])[2]/following-sibling::div[contains(@class,'filter-items')]//label//span[contains(@class,'show-only')]")
    public List<WebElement> eventTypeShowOnly;

    @FindBy(xpath = "//a[normalize-space()='Analysis']")
    public WebElement impalaAnalysisTab;

    @FindBy(xpath = "//div[@class='status-title']//span[@class='title']")
    public List<WebElement> getEfficiencyTitleString;

    @FindBy(xpath = "//table[@id='inefficientApps']/tbody/tr")
    public List<WebElement> getImpalaInefficientJobs;

    @FindBy(xpath = "(//table[@id='inefficientApps']//tr/td[4]/a[2])[1]")
    public WebElement firstInefficientRow;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//span[@class='globalSearchIcon']")
    public WebElement searchIcon;

    @FindBy(xpath ="(//table[@id='allApps']/tbody//td)[4]/a[2]")
    public List<WebElement> clickFirstApp;

    @FindBy(xpath = "//span[contains(@class,'icon-flipped')]")
    public WebElement flippedButton;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[4]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr/td[4]/a[2]")
    public List<WebElement> clickOnInefficientAppId;

    @FindBy(xpath = "//div[contains(@class,'tippy-content')]/span")
    public WebElement getToolTipValues;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public AppDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
