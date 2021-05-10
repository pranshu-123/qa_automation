package com.qa.pagefactory.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SmallfilesPageObject {


    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "//span[contains(text(),'Run')]/parent::a")
    public WebElement runButton;

    @FindBy(xpath = "//a[@href='#/clusters/overview']")
    public WebElement homeTab;

    @FindBy(xpath = "//span[contains(text(),'Schedule')]")
    public WebElement SheduleButton;

    @FindBy(xpath = "//input[@class='display-time']")
    public WebElement clickTime;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement closeButton;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='hours']")
    public WebElement hoursDropdown;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='minutes']")
    public WebElement minutesDropdown;

    @FindBy(xpath = "//span[contains(@class,'pointer cta-primary')]//span[contains(text(),'Schedule')]")
    public WebElement runSheduleButton;

    @FindBy(xpath = "//span[contains(text(),'Run New')]/parent::a")
    public WebElement runNowButton;

    @FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
    public WebElement modalRunButton;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleDays;

    @FindBy(xpath = "//input[@class='display-time']")
    public WebElement displayTime;

    @FindBy(xpath = "//span[contains(@class,'select2-selection--single')]")
    public WebElement clusterDropdown;

    @FindBy(xpath = "//span[contains(@class,'select2-search--dropdown')]/input")
    public WebElement clusterSearchbox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[2]")
    public WebElement select2stClusterOption;

    @FindBy(xpath = "//div[contains(text(),'Small file Report completed successfully.')]")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//div[@class='col-md-9']//div[@class='col-md-12']")
    public WebElement verifyReport;

    @FindBy(xpath = "//span[@class='text-fatal']")
    public WebElement confirmationMessageMinFileSizeElement;

    @FindBy(xpath = "//span[normalize-space()='Please, Make sure valid inputs.']")
    public List<WebElement>  errorMessageElement;


    @FindBy(xpath = "//span[contains(text(),'Max File Size is between')]")
    public WebElement confirmationMessageMaxFileSizeElement;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-success icon-success')]")
    public WebElement headerMessageElement;

    @FindBy(xpath = "//div[contains(text(),'Directories containing minimum')]")
    public WebElement verifyAbsoluteSize;

    @FindBy(xpath = "//h4[normalize-space()='Advanced Options']")
    public WebElement advancedOptions;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[1]")
    public List<WebElement> Daily;

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    @FindBy(xpath = "(//input[contains(@type,'text')])[2]")
    public WebElement minFileSize;

    @FindBy(xpath = "(//input[contains(@type,'text')])[3]")
    public WebElement maxiFileSize;

    @FindBy(xpath = "//span[normalize-space()='Path']")
    public WebElement sortPath;

    @FindBy(xpath = "//span[normalize-space()='Files']")
    public WebElement sortFiles;

    @FindBy(xpath = "//span[normalize-space()='Avg File Size']")
    public WebElement sortAvgFileSize;

    @FindBy(xpath = "//span[normalize-space()='Total File Size']")
    public WebElement sortTotalFileSize;

    @FindBy(xpath = "//span[normalize-space()='Min File Size']")
    public WebElement sortMinFileSize;

    @FindBy(xpath = "//span[normalize-space()='Max File Size']")
    public WebElement sortMaxFileSize;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//input[contains(@type,'search')]")
    public WebElement reportSearchBox;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleToRun;

    @FindBy(xpath = "//*[@class=\"col-md-12\"]//tbody/tr/td[1]")
    public List<WebElement> pathName;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> clustersList;

    @FindBy(xpath = "//*[@class=\"col-md-12\"]//tbody/tr/td[2]")
    public List<WebElement> fileName;

    @FindBy(xpath = "//table/tbody/tr/td[3]/span")
    public List<WebElement> avgFileSize;

    @FindBy(xpath = "//table/tbody/tr/td[4]/span")
    public List<WebElement> totalFileSize;

    @FindBy(xpath = "//table/tbody/tr/td[5]/span")
    public List<WebElement> minFileSizeName;

    @FindBy(xpath = "//table/tbody/tr/td[5]/span")
    public List<WebElement> maxFileSize;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr/td[1]")
    public List<WebElement> getPathNameFromTable;

    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
    public List<WebElement> fileTableRows;

    @FindBy(xpath = "//table[@class='component-data-tables']/tbody/tr[1]/td/p")
    public WebElement noDataText;

    @FindBy(xpath = "//*[@id='file-report']//div[4]/div[2]/nav/p")
    public WebElement pagination;

    @FindBy(xpath = "//*[name()='svg' and @data-icon='caret-right']")
    public WebElement rightCaretReportCnt;

    @FindBy(xpath = "//div[@class='col-md-2']/input")
    public WebElement searchField;

    @FindBy(xpath = "//*[name()='svg' and @data-icon='backward']")
    public WebElement backwardCaretReportCnt;


    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th[1]")
    public WebElement fileColumn;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr/td[2]")
    public List<WebElement> getFileNameFromTable;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]//td/p")
    public WebElement whenNoDataDisplay;

    @FindBy(xpath = "//ul[(@class='select2-results__options')]/li")
    public List<WebElement> queueOptions;

    @FindBy(css = "//div[@class='close pointer']")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "(//input[contains(@type,'text')])[4]")
    public WebElement minimumSmallFile;

    @FindBy(xpath = "(//input[contains(@type,'text')])[5]")
    public WebElement directoriestoShow;

    @FindBy(xpath = "//input[@num_files_threshold]")
    public WebElement onminParentDirectory;

    @FindBy(xpath = "(//input[contains(@type,'text')])[7]")
    public WebElement maxParentDirectory;

    @FindBy(xpath = "//div/b[text()='Schedule Name']//following::p/input")
    public WebElement scheduleNameTextbox;

    @FindBy(xpath = "//p[@class='element-inline']/input")
    public WebElement emailNotification;

    @FindBy(xpath = "//div[@class='panel-body']/div[2]/p/b")
    public WebElement previousReportData;

    @FindBy(xpath = "//div[contains(@class,'close pointer')]")
    public WebElement closebutton;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[2]")
    public List<WebElement> Sunday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[3]")
    public List<WebElement> Monday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[4]")
    public List<WebElement> Tuesday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[5]")
    public List<WebElement> Wednesday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[6]")
    public List<WebElement> Thursday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[7]")
    public List<WebElement> Friday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[8]")
    public List<WebElement> Saturday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[9]")
    public List<WebElement> Everytwoweeks;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[10]")
    public List<WebElement> Everymonths;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/input")
    public WebElement timepicker;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]")
    public List<WebElement> timepickerdropdown;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div[2]/div[3]/div[3]/div[3]/span/div[2]/div/select[1]")
    public List<WebElement> hoursRange;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[1]/option[25]")
    public List<WebElement> twentythreehours;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div[2]/div[3]/div[3]/div[3]/span/div[2]/div/select[2]")
    public List<WebElement> minutes;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[2]/option[61]")
    public List<WebElement> Fiftynine;

    @FindBy(xpath = "//div[@id='breadcrumb']/span[2]/span")
    public WebElement archiveReportSVCHeader;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement archiveReportDate;

    @FindBy(xpath = "//table/thead/tr/th[1]/a")
    public WebElement sortingReportNameIcon;

    @FindBy(xpath = "//table/tbody/tr/td[1]/span")
    public List<WebElement> reportNames;

    @FindBy(xpath = "//table/tbody/tr/td[1]")
    public List<WebElement> reportAll;

    @FindBy(xpath = "(//*[@class='pointer icon-delete is-enabled undefined'])")
    public WebElement deleteReportIcon;

    @FindBy(xpath="//div[contains(@class,'popover')]/H3")
    public WebElement deletePopText;

    @FindBy(xpath="//div[contains(@class,'popover')]/div/span[contains(@class,'pending')]")
    public WebElement deleteOkBtn;

    @FindBy(xpath="(//div[@id='breadcrumb']/span/span)[1]")
    public WebElement archives;

    @FindBy(xpath = "(//*[@role='dialog'])")
    public WebElement viewReportDialogWin;

    @FindBy(xpath = "//table/tbody/tr/td[2]/span")
    public List<WebElement> reportCnt;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//*[@class='close pointer']")
    public WebElement closeTab;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement successfulMsgBanner;

    @FindBy(xpath = "(//*[@class='icon-expand is-enabled'])[1]")
    public WebElement viewReportIcon;

    @FindBy(xpath = "//div//span//span[@class='pointer icon-download is-enabled undefined']")
    public WebElement downloadReportIcon;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement archiveReportName;

    @FindBy(xpath = "//span[contains(text(), 'Schedule')]//parent::a")
    public WebElement scheduleBtn;

    @FindBy(xpath = "//table/thead/tr/th[2]/a")
    public WebElement sortingReportCntIcon;

    @FindBy(xpath = "//*[@class='select2-selection__rendered']")
    public WebElement scheduleCloudDropDown;

    @FindBy(xpath = "(//div/b[contains(text(), 'Schedule Name')]//following-sibling::p/input)[1]")
    public WebElement scheduleName;

    @FindBy(xpath = "//select[contains(@class, 'schedule-days')]")
    public WebElement scheduleTRun;

    @FindBy(xpath = "//p[@class='element-inline']/input")
    public WebElement scheduleNotification;

    @FindBy(xpath = "(//section[contains(@class, 'icon-success')]/span)[1]")
    public WebElement scheduleMsg;

    @FindBy(xpath = "//span[contains(@class, 'cta-primary')]/a")
    public WebElement scheduleRunBtn;

    @FindBy(xpath = "//input[@class = 'display-time']")
    public WebElement scheduleTime;

    @FindBy(xpath = "//select[@class='hours']")
    public WebElement scheduleTimeHours;

    @FindBy(xpath = "//select[@class='minutes']")
    public WebElement scheduleTimeMinutes;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SmallfilesPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


}
