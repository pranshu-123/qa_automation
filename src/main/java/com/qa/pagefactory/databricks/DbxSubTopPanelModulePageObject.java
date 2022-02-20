package com.qa.pagefactory.databricks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class DbxSubTopPanelModulePageObject {

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobs;

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobsTabs;

    @FindBy(xpath = "//*[@id='RunsListAll-body']/tr/td[1]")
    public WebElement Status;

    @FindBy(xpath = "//ul[@class='dropdown-content']//a")
    public WebElement daemeons;

    @FindBy(xpath = "//a[@href='#/manage/stats']")
    public WebElement stats;

    @FindBy(xpath ="//h3[normalize-space()='No Data Available']")
    public List<WebElement> noDataPresent;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "//a[text()[normalize-space()='Only']]")
    public WebElement clickTab;

    @FindBy(css = "a.about")
    public WebElement aboutInfo;

    @FindBy(xpath = "//p[@class='results-title']//b[1]")
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

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[3]/a[2]")
    public WebElement getIdeFromTable;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[3]/span[1]")
    public WebElement getNameeFromTable;


    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;

    @FindBy(id = "apps-global-search-filter")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@id='statusContainer']/span[1]")
    public WebElement appStatus;

    @FindBy(xpath = "//*[@id=\"RunsListAll-body\"]/tr/td[3]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//table[@id='RunsListAll']/tbody[1]/tr/td[3]/a[1]")
    public WebElement getAppname;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

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
