package com.qa.pagefactory.databricks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class DbxSubTopPanelModulePageObject {

    @FindBy(xpath = "//span[text()[normalize-space()='Jobs']]")
    public WebElement jobs;

    @FindBy(xpath = "//span[text()='Jobs']")
    public WebElement jobsTabs;

    @FindBy(xpath = "(//a[@class='menu'])[2]")
    public WebElement gear;

    @FindBy(xpath = "//span[normalize-space()='Daemons']")
    public WebElement daemeons;

    @FindBy(xpath = "//*[@id='RunsListAll-body']/tr/td[1]")
    public WebElement Status;

    @FindBy(xpath = "//tbody[1]/tr/td[13]/span[1]")
    public WebElement costValue;

    @FindBy(xpath = "//tbody[1]/tr/td[13]/div[1]")
    public WebElement costDbu;

    @FindBy(xpath = "//div[@class='col-12']//div[1]")
    public WebElement appCostDbu;

    @FindBy(xpath = "//tr/td[1]")
    public WebElement statusFinishedTab;

    @FindBy(xpath = "//a[@href='#/manage/stats']")
    public WebElement stats;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "//h3[normalize-space()='No Data Available']")
    public List<WebElement> noDataPresent;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[1]")
    public List<WebElement> listStaus;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[4]/span[1]")
    public List<WebElement> listIds;

    @FindBy(xpath = "//a[@class='sorting icon-sort']")
    public List<WebElement> iconSort;

    @FindBy(xpath = "//*[@id=\"runs-filter-panel\"]//div/p/label/div/span[1]")
    public List<WebElement> getStatusTypes;

    @FindBy(xpath = "//table[@id='RunsListAll']/thead[1]/tr[1]/th/span[1]")
    public List<WebElement> getColumnsTypes;

    @FindBy(xpath = "//thead[1]/tr[1]/th/span[1]")
    public List<WebElement> getColumnsTypesFinishedTab;

    @FindBy(xpath = "//a[text()[normalize-space()='Only']]")
    public WebElement clickTab;

    @FindBy(css = "a.about")
    public WebElement aboutInfo;

    @FindBy(xpath = "//p[@class='results-title']//b[1]")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeIcon;

    @FindBy(xpath = "(//div[contains(@class,'duration-section')])[2]/input[1]")
    public WebElement eventsSliderInputLeft;

    @FindBy(xpath = "(//div[contains(@class,'duration-section')])[2]/input[2]")
    public WebElement eventsSliderInputRight;

    @FindBy(xpath = "//div[@class='vue-slider-process']")
    public WebElement eventsSlider;

    @FindBy(xpath = "//*[@id='app']//*[@class='right']/span")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy(css = "button.close")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[3]/a[2]")
    public WebElement getIdeFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[5]")
    public WebElement getRunIdFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[10]")
    public WebElement getWorkspaceFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[11]")
    public WebElement getClusterNameFromTable;

    @FindBy(xpath = "//tbody/tr/td[11]/span[1]")
    public WebElement getClusterName;

    @FindBy(xpath = "//tbody/tr[1]/td[3]/span[1]")
    public WebElement getNameFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[2]")
    public WebElement getUsernameFromTable;

    @FindBy(xpath = "//tr/td[2]/span[1]")
    public WebElement getUsernameFromFinishedTable;

    @FindBy(xpath = "//tbody[1]/tr/td[12]")
    public WebElement getClusterTypeFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[12]/span[1]")
    public WebElement getClusterTypeFromFinishedTable;

    @FindBy(xpath = "/er/tbody[1]/tr/td[10]/span[1]")
    public WebElement getWorkspaceAllFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[10]/span[1]")
    public WebElement getWorkspaceFromFinishedTable;

    @FindBy(xpath = "//li[@class='select2-results__option select2-results__message']")
    public WebElement getNoDataTable;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[3]//input")
    public WebElement workspaceSearchBox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[3]//input")
    public WebElement clusterTypeSearchBox;

    @FindBy(xpath = "//div[@class='status-title']//following-sibling::span[contains(@class,'badge') and not(contains(@class,'module-status'))]")
    public List<WebElement> getBadgeTitle;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;

    @FindBy(id = "apps-global-search-filter")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement clusterSearchBox;

    @FindBy(xpath = "//*[@id='statusContainer']/span[1]")
    public WebElement appStatus;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]//div[1]/div/div/h3")
    public WebElement appCost;

    @FindBy(xpath = "//tr/td[3]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//tr/td[3]/a[2]")
    public WebElement finishedAppId;

    @FindBy(xpath = "//button[text()[normalize-space()='Spark']]")
    public WebElement clickOnSpark;

    @FindBy(xpath = "//tbody[1]/tr/td[3]/a[1]")
    public WebElement getAppname;

    @FindBy(xpath = "//span[text()='Runs']")
    public WebElement runsTab;

    @FindBy(id = "running")
    public WebElement runningTab;

    @FindBy(id = "finished")
    public WebElement finishedTab;

    @FindBy(xpath = "//h3[text()[normalize-space()='No Data Available']]")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'User')]")
    public WebElement userExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Workspace')]")
    public WebElement workspaceExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Workspace')]")
    public WebElement clusterTypeExpandableHeader;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement clusterDropdown;

    @FindBy(xpath = "//div[@class='modal-body scrollbar-s']//div[2]")
    public WebElement versionInfo;

    @FindBy(css = "button.close")
    public WebElement closeAboutWindow;

    @FindBy(xpath = "(//ul[@class='sub-menu']//span[text()='Chargeback'])")
    public WebElement chargeback;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxSubTopPanelModulePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
