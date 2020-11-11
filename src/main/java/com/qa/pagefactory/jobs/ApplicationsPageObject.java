package com.qa.pagefactory.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApplicationsPageObject {

    @FindBy(css = "a#inefficientApps")
    public WebElement inefficientApps;

    @FindBy(css = ".select2-search__field")
    public WebElement clusterIdsearchfield;

    @FindBy(xpath = "//span[@class='select2-results']/ul/li")
    public List<WebElement> clusterIdsList;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']")
    public WebElement tblInefficientApps;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr")
    public List<WebElement> tblInefficientAppsRowsList;

    @FindBy(xpath = "//input[@name='daterangepicker_start' and contains(@class,'active')]")
    public WebElement startDatePicker;

    @FindBy(xpath = "//div[contains(@class,'opensleft')]/div[3]/div/button[1]")
    public WebElement applyBtn;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Jobs']")
    public WebElement jobsPageHeader;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[1]")
    public WebElement clusterSearchBox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
    public WebElement select1stCluster;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public WebElement removeCluster;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getclusterListDropdown;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[5]")
    public List<WebElement> getApplicationClusterId;

    @FindBy(xpath = "//h3[contains(text(),'No Data Available')]")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]")
    public WebElement userSearchBox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getTypeFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr[2]/td)[1]")
    public WebElement getAnotherAppFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[3]")
    public WebElement getUsernameFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[8]")
    public WebElement getQueueNameTable;

    @FindBy(id = "apps-global-search-filter")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;
    // Add to master
    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/span[2]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//div[contains(@class,'check-items-container')]//input//following-sibling::span")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//p[contains(@class,'float-right')]/b")
    public WebElement getTotalAppCount;

    @FindBy(id = "reset")
    public WebElement resetButton;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getUsername;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')]/a)[1]")
    public WebElement applicationType;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')]/a)[2]")
    public WebElement applicationEvent;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])[1]/input")
    public WebElement applicationTypeFirstApp;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])[6]/input")
    public WebElement applicationEventFirstApp;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/input")
    public List<WebElement> getApplicationTypeChkBoxList;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/input")
    public List<WebElement> getApplicationEventChkBoxList;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr")
    public List<WebElement> inefficientAppsTblRowsList;

    @FindBy(xpath = "//h3[contains(@class,'no-data-msg')]")
    public WebElement inefficientAppsTblNoDataMsg;

    @FindBy(xpath = "//a[contains(@class,'icon-sort')]")
    public List<WebElement> inefficientAppsIconSortTbl;

    @FindBy(className = "show-all")
    public WebElement showAll;

    @FindBy(xpath = "//a[contains(text(),'Status')]")
    public WebElement expandStatus;

    @FindBy(xpath = "//a[text()='Status']//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[2]")
    public WebElement getStatusFromTable;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[2]//span[1]")
    public List<WebElement> getStatusTypes;

    @FindBy(xpath = "//a[text()='Status']//following::div[contains(@class,'check-items-container')]//span[2]")
    public List<WebElement> getEachStatusTypeJobCount;

    @FindBy(xpath = "(//div[@id='app-filter-panel']//div[@class='placeholder']//div[contains(@class,'vue-slider-ltr')])[1]")
    public WebElement durationSlider;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[2]/input[1]")
    public WebElement durationSliderInputLeft;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[2]/input[2]")
    public WebElement durationSliderInputRight;

    @FindBy(xpath = "//span[@id = 'allApps-headerspan-6']//following-sibling::a[contains(@class, 'sorting')]")
    public WebElement sortDuration;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[7]")
    public WebElement getDurationFromTable;

    @FindBy(xpath = "(//div[@id='app-filter-panel']//div[@class='placeholder']//div[contains(@class,'vue-slider-ltr')])[2]")
    public WebElement eventsSlider;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[4]/input[1]")
    public WebElement eventsSliderInputLeft;

    @FindBy(xpath = "(//div[contains(@class,'input-section')])[4]/input[2]")
    public WebElement eventsSliderInputRight;

    @FindBy(xpath = "(//span[contains(@class,'badge')])")
    public List<WebElement> getBadgeTitle;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[3]")
    public WebElement queueNameSearchBox;

    @FindBy(xpath = "(//div/h3[contains(@class,'expandable-header')])[7]//following-sibling::div//label")
    public List<WebElement> getTagTypes;

    @FindBy(xpath = "(//div/h3[contains(@class,'expandable-header')])[7]//following-sibling::div//label/span")
    public List<WebElement> tagsCheckboxes;

    @FindBy(xpath = "((//div[contains(@class,'col-md-2')])//div[contains(@class,'filter-section')])[8]//ul[contains(@class,'select2-selection__rendered')]")
    public List<WebElement> tagTypeSearchbox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> select1stOptionInTagsSearchBox;

    @FindBy(xpath = "//a[contains(@content,'Tags')]")
    public WebElement tagsTab;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr")
    public List<WebElement> tagsTable;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr/td[1]")
    public List<WebElement> tagsInTable;

    @FindBy(xpath = "//table[contains(@id,'appTagsTable')]/tbody/tr/td[2]")
    public List<WebElement> descriptionInTable;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[1]")
    public List<WebElement> getTypesColumnFromTable;

    @FindBy(xpath = "//span[(text()='Type')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByType;

    @FindBy(xpath = "//span[(text()='User')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByUser;

    @FindBy(xpath = "//*[contains(@class,'nprogress-busy')]")
    public WebElement loader;

    @FindBy(id = "runningAppList")
    public WebElement runningAppTab;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[2]")
    public List<WebElement> getStatusColumnFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/span[2]")
    public WebElement copyAppName;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]")
    public WebElement getAppNameFromTable;

    @FindBy(xpath = "//section[contains(@class,'icon-success')]")
    public WebElement successBanner;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//span[(text()='Parent App')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByParentApp;

    @FindBy(xpath = "//span[(text()='Status')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortStatus;

    @FindBy(xpath = "//span[(text()='ClusterId')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByClusterId;

    @FindBy(xpath = "//span[(text()='Start Time')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByStartTime;

    @FindBy(xpath = "//span[(text()='Duration')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByDuration;

    @FindBy(xpath = "//span[(text()='Read')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByRead;

    @FindBy(xpath = "//span[(text()='Write')]//following-sibling::a[contains(@class,'sorting')]")
    public WebElement sortByWrite;

    @FindBy(xpath = "//span[@class='globalSearchIcon']")
    public WebElement searchIcon;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ApplicationsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}