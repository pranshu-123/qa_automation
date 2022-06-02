package com.qa.pagefactory.emr.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmrApplicationsPageObject {

    @FindBy(css = "a#inefficientApps")
    public WebElement inefficientApps;


    @FindBy(xpath = "//span[@class='select2-results']/ul/li")
    public List<WebElement> clusterIdsList;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']")
    public WebElement tblInefficientApps;


    @FindBy(xpath = "//input[@name='daterangepicker_start' and contains(@class,'active')]")
    public WebElement startDatePicker;

    @FindBy(xpath = "//div[contains(@class,'opensleft')]/div[3]/div/button[1]")
    public WebElement applyBtn;

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobsPageHeader;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(css = "input.select2-search__field")
    public WebElement clusterSearchBox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
    public WebElement select1stCluster;

    @FindBy(css = ".select-cluster~.select2 .selection .select2-selection__arrow")
    public WebElement clusterDropdownPage;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public WebElement clusterSearchFirstField;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public WebElement removeCluster;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr")
    public WebElement whenApplicationPresent;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "//tbody[1]/tr/td[3]")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "//tbody[1]/tr/td[9]")
    public List<WebElement> getQueueNamesFromDropDown;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getTypeFromTable;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public WebElement getAppname;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr[2]/td)[1]")
    public WebElement getAnotherAppFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[3]")
    public WebElement getUsernameFromTable;

    @FindBy(xpath = "//tbody[1]/tr/td[9]")
    public WebElement getQueueNameTable;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;
    // Add to master
    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/span[2][not(contains(@class,'show-only'))]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//*[@id=\"runs-filter-panel\"]/div[2]/div/p/label/span[1]")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//span[text()[normalize-space()='Only']]")
    public WebElement verifyApplicationType;

    @FindBy(xpath = "//div[@class='filter-items check-items-container']//div//p[1]//label[1]")
    public List<WebElement> selectApplicationType;

    @FindBy(xpath = "//label[contains(@class,'checkbox')]//following-sibling::span[2]")
    public List<WebElement> selectApplication;

    @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "//span[text()[normalize-space()='RESET']]")
    public WebElement showAll;

    @FindBy(xpath = "//a[contains(text(),'Status')]")
    public WebElement expandStatus;

    @FindBy(xpath = "//a[contains(text(),'Queue')]")
    public WebElement expandQueue;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[2]")
    public WebElement getStatusFromTable;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[2]//span[1]")
    public List<WebElement> getStatusTypes;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[2]")
    public List<WebElement> getEachStatusTypeJobCount;

    @FindBy(xpath = "(//div[@id='app-filter-panel']//div[@class='placeholder']//div[contains(@class,'vue-slider-ltr')])[1]")
    public WebElement durationSlider;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[2]/input[1]")
    public WebElement durationSliderInputLeft;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[2]/input[2]")
    public WebElement durationSliderInputRight;

    @FindBy(xpath = "//span[@id = 'allApps-headerspan-7']//following-sibling::a[contains(@class, 'sorting')]")
    public WebElement sortDuration;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[8]")
    public WebElement getDurationFromTable;

    @FindBy(xpath = "(//div[@id='app-filter-panel']//div[@class='placeholder']//div[contains(@class,'vue-slider-ltr')])[2]")
    public WebElement eventsSlider;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[4]/input[1]")
    public WebElement eventsSliderInputLeft;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[4]/input[2]")
    public WebElement eventsSliderInputRight;

    @FindBy(xpath = "//div[@class='status-title']//following-sibling::span[contains(@class,'badge') and not(contains(@class,'module-status'))]")
    public List<WebElement> getBadgeTitle;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[3]//input")
    public WebElement queueNameSearchBox;

    @FindBy(xpath = "(//div/h3[contains(@class,'expandable-header')])[7]//following-sibling::div//label")
    public List<WebElement> getTagTypes;

    @FindBy(xpath = "(//div/h3[contains(@class,'expandable-header')])[7]//following-sibling::div//label/span")
    public List<WebElement> tagsCheckboxes;

    @FindBy(xpath = "((//div[contains(@class,'col-md-2')])//div[contains(@class,'filter-section')])[9]//ul//input")
    public List<WebElement> tagTypeSearchbox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> select1stOptionInTagsSearchBox;

    @FindBy(xpath = "//a[normalize-space()='Tags']")
    public WebElement tagsTab;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr")
    public List<WebElement> tagsTable;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr/td[1]")
    public List<WebElement> tagsInTable;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr/td[2]")
    public List<WebElement> descriptionInTable;


    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/a[1]")
    public WebElement copyAppName;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]")
    public WebElement getAppNameFromTable;

    @FindBy(xpath = "//section[contains(@class,'icon-success')]")
    public WebElement successBanner;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[12]/a")
    public WebElement sortByParentApp;

    @FindBy(xpath = "//a[@class='icon-gear']")
    public WebElement settings;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'User')]")
    public WebElement userExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Queue')]")
    public WebElement queueExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Tags')]")
    public WebElement tagExpandableHeader;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeIcon;

    @FindBy(xpath = "//tbody/tr/td[3]")
    public WebElement getUserFromTable;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[4]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "//tbody[1]/tr/td[4]/a[2]")
    public WebElement getApplicationIDTable;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public EmrApplicationsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
