package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QueueAnalysisPageObject {

    @FindBy(xpath = "//h3/b[contains(text(),'Queue Analysis')]")
    public WebElement queueAnalysisHeading;

    @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Run')]")
    public WebElement runButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(linkText = "Run")
    public WebElement modalRunButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//div[contains(@class,'daterangepicker') and contains(@class, 'open')]//li")
    public WebElement dateRanges;

    @FindBy(xpath = "//span[contains(@class,'text-fatal')]")
    public WebElement messageOnSelectingBeyond30days;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-fatal')]//div[1]")
    public List<WebElement> invalidInputMessage;

    @FindBy(className = "select2-search__field")
    public WebElement queueSearchBox;

    @FindBy(xpath = "//ul[(@class='select2-results__options')]/li")
    public List<WebElement> queueOptions;


    @FindBy(xpath = "//thead//th[contains(text(),'Queue')]//a[contains(@class,'icon-sort')]")
    public WebElement sortByQueueName;

    @FindBy(xpath = "//thead//th[contains(text(),'Jobs Running')]//a[contains(@class,'icon-sort')]")
    public WebElement sortByJobs;

    @FindBy(xpath = "//thead//th[contains(text(),'vCore Allocated')]//a[contains(@class,'icon-sort')]")
    public WebElement sortByVcore;

    @FindBy(xpath = "//thead//th[contains(text(),'Memory Allocated')]//a[contains(@class,'icon-sort')]")
    public WebElement sortByMemory;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
    public WebElement sortDown;

    @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
    public WebElement sortUp;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr[@class='cursor-pointer']/td[1]")
    public List<WebElement> getQueueNameFromTable;

    @FindBy(xpath = "//div[@class='dashboard-row']/div")
    public List<WebElement> queueGraph;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]//td/p")
    public WebElement whenNoQueuePresent;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[1]")
    public WebElement jobsGraph;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[2]")
    public WebElement vcoresGraph;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[3]")
    public WebElement memoryGraph;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g'])[10]/*[local-name() = 'text']")
    public WebElement jobsToolTip;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g'])[20]/*[local-name() = 'text']")
    public WebElement vcoresToolTip;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g'])[30]/*[local-name() = 'text']")
    public WebElement memoryToolTip;

    @FindBy(xpath = "//b[@class='blink']")
    public WebElement loading;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]//div/div/label[contains(text(),'Resource Type')]")
    public WebElement yarnResources;


    @FindBy(xpath = "//table[contains(@class,'component-data-tables row-hover')]/tbody[1]/tr[9]/td[4]/div[1]/span[2]/span[1]")
    public WebElement scheduleButton;

    @FindBy(xpath = "//label[text()='Schedule Name']/following::input")
    public WebElement scheduleName;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@type='email']/following-sibling::span")
    public WebElement addEmail;

    @FindBy(xpath = "//span[@class='cta-primary component-cta']//a[1]")
    public WebElement modalScheduleButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-success')]//div[1]")
    public WebElement scheduleSuccessMsg;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleDays;

    @FindBy(xpath = "//input[@class='display-time']")
    public WebElement displayTime;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='hours']")
    public WebElement hoursDropdown;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='minutes']")
    public WebElement minutesDropdown;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement close;

    @FindBy(xpath = "(//div[@id='breadcrumb']//span)[2][text()='Archives']")
    public WebElement QAHeading;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public WebElement invalidQueueMessage;

    @FindBy(xpath = "//span[contains(@class,'select2-selection--single')]")
    public WebElement clusterDropdown;

    @FindBy(xpath = "//span[contains(@class,'select2-search--dropdown')]/input")
    public WebElement clusterSearchbox;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
    public WebElement select1stClusterOption;

    @FindBy(xpath = "//p[@class='pointer']")
    public List<WebElement> pagination;

    @FindBy(xpath = "//div[contains(@class,'queue-task-runner')]//div[contains(@class,'component-date-picker')]")
    public WebElement dateRange;

    @FindBy(xpath = "//div[@class='ranges']//li[text()='Custom Range']")
    public WebElement customRange;

    @FindBy(xpath = "//div[@class='daterangepicker_input']/input[@name='daterangepicker_start' and contains(@class,'active')]")
    public WebElement startDate;

    @FindBy(xpath = "(//div[@class='daterangepicker_input']/input[@name='daterangepicker_end'])[last()]")
    public WebElement endDate;

    @FindBy(xpath = "//button[contains(@class,'applyBtn')][not(contains(@disabled,'disabled'))][last()]")
    public WebElement applyButton;

    @FindBy(xpath = "//span[@class='text-fatal']")
    public WebElement invalidDateRangeMessage;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables row-hover')]/tbody[1]/tr[9]/td[4]/div[1]/span[1]/span[1]")
    public WebElement addIcon;

    @FindBy(xpath = "((//table/tbody/tr)[5]/td)[4]//span[contains(@class,'icon-expand')]")
    public WebElement latestReportExpandIcon;

    @FindBy(xpath = "((//table/tbody/tr)[5]/td)[3]//span[contains(@class,'success')]")
    public WebElement successBanner;

    @FindBy(xpath = "//span[contains(@class,'component-cta')]//span")
    public WebElement pleaseWaitTimer;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/span")
    public WebElement pleaseWaitBanner;

    @FindBy(xpath="//div[@class='modal-body scrollbar-s']/following-sibling::div[1]")
    public WebElement footerWaitCycle;

    @FindBy(xpath = "//tbody[1]/tr[9]/td[1]/span[text()='Queue Analysis']")
    public WebElement clickOnQAReports;

    @FindBy(xpath ="//tbody/tr[1]/td[3]/span")
    public WebElement isFirstQAReportSuccess;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement select1stQAReport;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> getQueueReportList;

    @FindBy(xpath = "//p[@class='pointer']")
    public WebElement getNumberOfPagesOfReports;

    @FindBy(xpath="//p[@class='pointer']//input")
    public WebElement enterPageNumberToNavigation;

    @FindBy(xpath = "//div[contains(@class,'queue-table')]//table/tbody/tr/td[1]")
    public List<WebElement> getQueueList;

    @FindBy(xpath = "//table//select/following-sibling::span//input")
    public List<WebElement> queueGraphSearchBox;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__arrow')])[1]")
    public WebElement resourceUsagePointer;


    public QueueAnalysisPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
