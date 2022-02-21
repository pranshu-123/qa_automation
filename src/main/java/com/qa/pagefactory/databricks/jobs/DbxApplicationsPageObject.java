package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxApplicationsPageObject {

    @FindBy(xpath = "	")
    public WebElement clusterIdsearchfield;

    @FindBy(xpath = "	")
    public List<WebElement> clusterIdsList;
    
    @FindBy(css = "tbody#RunsListAll-body")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "//div[contains(@class,'check-items-container')]//input//following-sibling::span")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]//div[2]/div[2]//div/p/label/span[2]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getclusterListDropdown;

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
