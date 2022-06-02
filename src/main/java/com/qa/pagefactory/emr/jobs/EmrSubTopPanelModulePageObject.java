package com.qa.pagefactory.emr.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmrSubTopPanelModulePageObject {
    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobs;

    @FindBy(xpath = "//span[text()='Jobs']")
    public WebElement jobsTabs;

    @FindBy(xpath = "(//a[@class='menu'])[2]")
    public WebElement gear;

    @FindBy(xpath = "//tbody[1]/tr/td[2]")
    public WebElement Status;

    @FindBy(xpath = "//a[@href='#/manage/stats']")
    public WebElement stats;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "//h3[normalize-space()='No Data Available']")
    public List<WebElement> noDataPresent;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@id=\"runs-filter-panel\"]//div/p/label/div/span[1]")
    public List<WebElement> getStatusTypes;

    @FindBy(xpath = "//p[@class='float-right']//b[1]")
    public WebElement getTotalAppCount;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeIcon;

    @FindBy(xpath = "//*[@id='app']//*[@class='right']/span")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy(css = "button.close")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;

    @FindBy(id = "apps-global-search-filter")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement clusterSearchBox;

    @FindBy(xpath = "//*[@id='statusContainer']/span[1]")
    public WebElement appStatus;

    @FindBy(xpath = "//*[@id=\"SummaryDetails\"]//div[2]/div/div/h3")
    public WebElement appCost;

    @FindBy(xpath = "//tbody[1]/tr/td[4]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//h3[text()[normalize-space()='No Data Available']]")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'User')]")
    public WebElement userExpandableHeader;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement clusterDropdown;

    @FindBy(xpath = "(//ul[@class='sub-menu']//span[text()='Chargeback'])")
    public WebElement chargeback;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public EmrSubTopPanelModulePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}

