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

    @FindBy(xpath = "//span[contains(text(),'Run')]")
    public WebElement modalRunButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//div[contains(@class,'daterangepicker') and contains(@class, 'open')]//li")
    public WebElement dateRanges;

    @FindBy(xpath = "//span[contains(@class,'text-fatal')]")
    public WebElement messageOnSelectingBeyond30days;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/span")
    public WebElement invalidInputMessage;

    @FindBy(className = "select2-search__field")
    public WebElement queueSearchBox;

    @FindBy(xpath = "//ul[(@class='select2-results__options')]/li")
    public List<WebElement> queueOptions;


    @FindBy(xpath = "(//a[contains(@class,'icon-sort')])[1]")
    public WebElement sortByQueueName;

    @FindBy(xpath = "(//a[contains(@class,'icon-sort')])[2]")
    public WebElement sortByJobs;

    @FindBy(xpath = "(//a[contains(@class,'icon-sort')])[3]")
    public WebElement sortByVcore;

    @FindBy(xpath = "(//a[contains(@class,'icon-sort')])[4]")
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

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1")
    public WebElement yarnResources;


    @FindBy(xpath = "//span[contains(text(),'Schedule')]/parent::a")
    public WebElement scheduleButton;

    @FindBy(xpath = "(//form[@name='taskrunnerForm']/div/div[3]/b[contains(text(), 'Schedule Name')]/parent::div/p/input)[1]")
    public WebElement scheduleName;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@type='email']/following-sibling::span")
    public WebElement addEmail;

    @FindBy(xpath = "(//span[contains(text(),'Schedule')])[2]/parent::a")
    public WebElement modalScheduleButton;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[1]")
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

    @FindBy(xpath = "//h3/b[text()='Queue Analysis']")
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

    @FindBy(className = "component-date-picker")
    public WebElement dateRange;

    @FindBy(xpath = "//div[@class='ranges']//li[text()='Custom Range']")
    public WebElement customRange;

    @FindBy(xpath = "(//div[@class='daterangepicker_input']/input[@name='daterangepicker_start'])[last()]")
    public WebElement startDate;

    @FindBy(xpath = "(//div[@class='daterangepicker_input']/input[@name='daterangepicker_end'])[last()]")
    public WebElement endDate;

    @FindBy(xpath = "//button[contains(@class,'applyBtn')][not(contains(@disabled,'disabled'))][last()]")
    public WebElement applyButton;

    @FindBy(xpath = "//span[@class='text-fatal']")
    public WebElement invalidDateRangeMessage;

    @FindBy(xpath = "((//table/tbody/tr)[5]/td)[4]//span[contains(@class,'icon-add')]")
    public WebElement addIcon;

    @FindBy(xpath = "((//table/tbody/tr)[5]/td)[4]//span[contains(@class,'icon-expand')]")
    public WebElement latestReportExpandIcon;

    @FindBy(xpath = "((//table/tbody/tr)[5]/td)[3]//span[contains(@class,'success')]")
    public WebElement successBanner;

    @FindBy(xpath = "//span[contains(@class,'component-cta')]//span")
    public WebElement pleaseWaitTimer;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/span")
    public WebElement pleaseWaitBanner;


    public QueueAnalysisPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
