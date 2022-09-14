package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxApplicationsPageObject {

    @FindBy(css = "tbody#RunsListAll-body")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement clusterDropdown;

    @FindBy(xpath = "//tbody/tr/td[11]/span[1]")
    public WebElement getClusterName;

    @FindBy(xpath = "//tbody/tr/td[2]/span[1]")
    public WebElement getUserFromTable;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "//tbody/tr/td[4]/div[1]/div[1]/span[1]")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "//tbody/tr/td[4]/div[1]/div[1]/a[2]")
    public WebElement getWorkspaceNameTable;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@id=\"runs-filter-panel\"]/div[2]/div/p/label/span[1]")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//tbody[1]/tr/td")
    public WebElement selectApplicationPresent;

    @FindBy(xpath = "//*[@id=\"runs-filter-panel\"]/div[2]/div/p/label/div/span[2]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getclusterListDropdown;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getClusterDropdown;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[1]")
    public WebElement clusterSearchBox;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
    public WebElement select1stCluster;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public WebElement removeCluster;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[6]")
    public List<WebElement> getApplicationClusterId;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxApplicationsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
