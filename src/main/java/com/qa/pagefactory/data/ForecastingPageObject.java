package com.qa.pagefactory.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ForecastingPageObject {

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "(//span[contains(text(),'Run')])")
    public WebElement runButton;

    @FindBy(xpath = "//span[contains(text(),'Run New')]/parent::a")
    public WebElement runNowButton;

    @FindBy(xpath = "//span[text()='Schedule']")
    public WebElement scheduleButton;

    @FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
    public WebElement modalRunButton;

    @FindBy(xpath = "(//span[contains(text(),'Schedule')])[2]/parent::a")
    public WebElement modalScheduleButton;

    @FindBy(xpath = "//b[contains(text(),'Forecasting')]//parent::p/following-sibling::p/input")
    public WebElement numOfDaysForForecasting;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//span[contains(@class,'text-fatal')]")
    public WebElement ForecastingErrorMessageElement;

    @FindBy(xpath = "//h2[@class='text-white']/div")
    public WebElement modalCancelButton;

    @FindBy(xpath = "//div[@class='panel-body']/div[2]/p/b")
    public WebElement previousReportData;

    @FindBy(xpath = "//div[@class='component-date-picker']/i[1]")
    public WebElement historyDateRangeDropDown;

    @FindBy(xpath = "(//form[@name='taskrunnerForm']/div/div[3]/b[contains(text(), 'Schedule Name')]/parent::div/p/input)[1]")
    public WebElement scheduleName;

    @FindBy(xpath = "//input[@type='email']/following-sibling::span")
    public WebElement addEmail;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@class='display-time']")
    public WebElement displayTime;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='hours']")
    public WebElement hoursDropdown;

    @FindBy(xpath = "//input[@class='display-time']/following::select[@class='minutes']")
    public WebElement minutesDropdown;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[1]")
    public WebElement scheduleSuccessMsg;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement verifyColorCode;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleDays;

    @FindBy(xpath = "//div[@class='ranges']/ul/li")
    public List<WebElement> listDateRange;

    @FindBy(xpath = "//span/parent::a")
    public WebElement modalAfterRunButton;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ForecastingPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
