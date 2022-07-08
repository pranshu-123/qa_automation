package com.qa.pagefactory.emr.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmrInefficientAppsPageObject {

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]/div[2]/div[1]//div/p/label/span[2]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//label[@class='checkbox']//span")
    public List<WebElement> getApplicationTypeChkBoxList;

    @FindBy(xpath="//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    @FindBy(xpath = "//li[@class='select2-search select2-search--inline']//input[1]")
    public WebElement clusterDropdown;

    @FindBy(css = "input.select2-search__field")
    public WebElement clusterSearchBox;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "//tbody[1]/tr[1]/td[4]/a[2]")
    public WebElement getAppId;

    @FindBy(xpath = "//tbody[1]/tr[1]/td[4]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeIcon;

    @FindBy(xpath = "(//span[@class='instance-id']//span)[3]")
    public WebElement getHeaderAppId;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[1]/div[1]/span")
    public List<WebElement> rightPaneKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/h5")
    public List<WebElement> rightPaneAppKpis;

    @FindBy(xpath = "(//div[@id='SummaryDetails']/div/div)/div//h3")
    public List<WebElement> rightPaneAppKpiVal;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(css = "button.close")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "//p[@class='float-right']//b[1]")
    public WebElement getTotalAppCount;

    @FindBy(css = "tbody#inefficientApps-body")
    public WebElement applicationPresent;


    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "//nav[contains(@class,'pagination')]")
    public WebElement isPaginationPresent;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'path'])[4]")
    public WebElement lastPage;

    @FindBy(xpath = "//nav[@class='pagination']//p")
    public WebElement getTotalNoOfPages;

    @FindBy(xpath = "//nav[@class='pagination']//input")
    public WebElement getPageNumber;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(css = ".select2-search__field")
    public WebElement clusterIdsearchfield;

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]/div[2]/div[2]/div/div/p/label/span[4]")
    public List<WebElement> getApplicationEventChkBoxList;

    public EmrInefficientAppsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

