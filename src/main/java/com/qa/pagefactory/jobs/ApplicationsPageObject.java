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

    @FindBy(css = ".select-cluster~.select2 .selection .select2-selection__arrow")
    public WebElement workspaceDropdown;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public WebElement clusterSearchFirstField;

    @FindBy(xpath="//span[contains(@class, 'select2-search--dropdown')]/input")
    public WebElement workspaceSearchBox;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public WebElement removeCluster;

    @FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
    public WebElement datepickerCalendar;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public List<WebElement> getclusterListDropdown;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[6]")
    public List<WebElement> getApplicationClusterId;

    @FindBy(xpath = "//*[@id=\"allApps-body\"]/tr")
    public WebElement whenApplicationPresent;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]//input")
    public WebElement userSearchBox;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getNamesFromDropDown;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
    public WebElement getTypeFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr[2]/td)[1]")
    public WebElement getAnotherAppFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[3]")
    public WebElement getUsernameFromTable;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[9]")
    public WebElement getQueueNameTable;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearchBox;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getTableData;
    // Add to master
    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/span[2][not(contains(@class,'show-only'))]")
    public List<WebElement> getEachApplicationTypeJobCounts;

    @FindBy(xpath = "//div[contains(@class,'check-items-container')]//input//following-sibling::span")
    public List<WebElement> selectOneApplicationType;

    @FindBy(xpath = "//p[@class='float-right']//b[1]")
    public WebElement getTotalAppCount;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "(//ul[contains(@class,'select2-results')])/li")
    public List<WebElement> getUsername;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')]/a)[1]")
    public WebElement applicationType;

    @FindBy(xpath = "(//h3[contains(@class,'expandable-header')]/a)[2]")
    public WebElement applicationEvent;

    @FindBy(xpath = "//div[@id='app-filter-panel']/div[2]/div[1]//div[1]/p[1]/label[1]/span[4]")
    public WebElement applicationTypeFirstApp;

    @FindBy(xpath = "//label[@title='ExecutorOomeEvent']//span[@class='checkmark']")
    public WebElement applicationEventFirstApp;

    @FindBy(xpath = "//*[@id=\"app-filter-panel\"]/div[2]/div[1]//div/p/label/span[4]")
    public List<WebElement> getApplicationTypeChkBoxList;

    @FindBy(xpath = "(//label[contains(@class,'checkbox')])/input")
    public List<WebElement> getApplicationEventChkBoxList;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr")
    public List<WebElement> inefficientAppsTblRowsList;

    @FindBy(xpath = "//h3[contains(@class,'no-data-msg')]")
    public WebElement inefficientAppsTblNoDataMsg;

    @FindBy(xpath = "//a[contains(@class,'icon-sort')]")
    public List<WebElement> inefficientAppsIconSortTbl;

    @FindBy(xpath = "//span[text()[normalize-space()='Show all']]")
    public WebElement showAll;

    @FindBy(xpath = "//a[contains(text(),'Status')]")
    public WebElement expandStatus;

    @FindBy(xpath = "//a[contains(text(),'Queue')]")
    public WebElement expandQueue;

    @FindBy(xpath = "//a[contains(text(),'User')]")
    public WebElement expandUser;

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

    @FindBy(xpath = "(//a[@class='sorting icon-sort'])[1]")
    public WebElement sortByType;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[3]/a")
    public WebElement sortByUser;

    @FindBy(id = "runningAppList")
    public WebElement runningAppTab;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[2]")
    public List<WebElement> getStatusColumnFromTable;

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

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[2]/a")
    public WebElement sortStatus;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[6]/a")
    public WebElement sortByClusterId;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[7]/a")
    public WebElement sortByStartTime;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[8]/a")
    public WebElement sortByDuration;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[10]/a")
    public WebElement sortByRead;

    @FindBy(xpath = "//*[@id=\"allApps-head\"]/tr/th[11]/a")
    public WebElement sortByWrite;

    @FindBy(xpath = "//a[@class='icon-gear']")
    public WebElement settings;

    @FindBy(xpath = "//ul[@class='dropdown']/li")
    public List<WebElement> settingsList;

    @FindBy(xpath = "//table[@id='allApps']")
    public WebElement isTableExist;

    @FindBy(xpath = "//span[contains(@id,'allApps-headerspan')]")
    public List<WebElement> getTableHeaders;

    @FindBy(xpath = "//nav[contains(@class,'pagination')]")
    public WebElement isPaginationPresent;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'path'])[4]")
    public WebElement lastPage;

    @FindBy(xpath = "//nav[@class='pagination']//input")
    public WebElement getPageNumber;

    @FindBy(xpath = "//nav[@class='pagination']//p")
    public WebElement getTotalNoOfPages;

    @FindBy(xpath = "//tbody[@id = 'runningApps-body']/tr/td[2]")
    public List<WebElement> getStatusColumnOfRunningApps;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[2][not(contains(@class,'show-only'))]")
    public WebElement statusJobCount;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[12]")
    public List<WebElement> getParentAppColumnFromTable;

    @FindBy(xpath = "//table[@id='allApps']/tbody[1]/tr[2]/td[12]/a[1]//span[@class='Hive']")
    public List<WebElement> checkHiveInParentApp;

    @FindBy(xpath = "(//div[contains(@class,'component-section-header')]//h2)[2]")
    public WebElement appSummary;
    
    @FindBy(xpath = "//span[@class='globalSearchIcon']")
    public WebElement searchIcon;

    @FindBy(xpath = "//table[@id='allApps']/tbody")
    public WebElement allAppsTable;

    @FindBy(xpath = "//h4[@class='job-times']/span/b")
    public List<WebElement> getJobTimesHeading;

    @FindBy(xpath = "//h4[@class='job-times']/span")
    public List<WebElement> getJobTimes;


    @FindBy(xpath = "//span[contains(@class,'module-status')]")
    public WebElement hiveChildApp;

    @FindBy(className  = "nprogress-busy")
    public WebElement loader;

    @FindBy(xpath = "//div[contains(@class,'justify-content-start')]//h5")
    public List<WebElement> getAllKPIHeading;

    @FindBy(xpath = "//div[contains(@class,'justify-content-start')]//h3")
    public List<WebElement> getAllKPIHeadingValues;

    @FindBy(xpath = "//div[@class='right']/span")
    public List<WebElement> getOwnerClusterQueueName;

    @FindBy(xpath = "//div[@class='component-tabs-primary']//li/a")
    public List<WebElement> appDetailsTabs;

    @FindBy (xpath ="(//tbody[@id = 'allApps-body']/tr/td[12]//span)[@class='tez']")
    public List<WebElement> checkTezInParentApp;

    @FindBy (xpath ="//tbody/tr/td[12]/a[1]/span[1]/span[1]")
    public List<WebElement> tezInParentApp;

    @FindBy(xpath = "//div[@id = 'scrollableMenu']//li/a")
    public List<WebElement> hiveTezAppDetailsTab;

    @FindBy(xpath = "//div[contains(@class,'component-tabs-secondary')]//li//a")
    public List<WebElement> mrHiveLeftPaneHeaders;

    @FindBy(xpath = "(//div[contains(@class,'component-section-header')]/h2/span)[1]")
    public WebElement hiveTezChildApp;

    @FindBy(xpath = "//div[contains(@class,'component-tabs-secondary')]//span/a")
    public WebElement tezDagsDisplayed;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'User')]")
    public WebElement userExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Queue')]")
    public WebElement queueExpandableHeader;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]//a[contains(text(),'Tags')]")
    public WebElement tagExpandableHeader;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement closeIcon;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[4]/a[2]")
    public WebElement clickOnAppId;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]")
    public WebElement hoverOnAppName;

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/span")
    public WebElement getToolTipValueOfAppName;

    @FindBy(xpath = "(//h3/a[contains(text(),'Application Type')]//following::span)[1]")
    public  WebElement showAllLink;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ApplicationsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
