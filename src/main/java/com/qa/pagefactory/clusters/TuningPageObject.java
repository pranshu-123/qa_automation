package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TuningPageObject {

    @FindBy(xpath = "")
    public WebElement e;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "//span[@class='cta-primary component-cta']//a[1]")
    public WebElement runButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-info')]//div[1]")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//a[normalize-space()='Run']")
    public WebElement modalRunButton;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement closeNewReport;

    @FindBy(xpath = "//span[contains(text(),'Schedule')]/parent::a")
    public WebElement scheduleButton;

    @FindBy(xpath = "//label[text()='Schedule Name']/following::input")
    public WebElement scheduleName;

    //@FindBy(xpath = "(//span[contains(text(),'Schedule')])[2]/parent::a")
    @FindBy(xpath = "//span[@class='cta-primary component-cta']//a[1]")
    public WebElement modalScheduleButton;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[1]")
    public WebElement scheduleSuccessMsg;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[2]/a")
    public WebElement scheduleSuccessMsgClickHere;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleToRun;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@type='email']/following-sibling::span")
    public WebElement addEmail;

    @FindBy(xpath = "//div[@id='breadcrumb']/span/span")
    public WebElement archivesText;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement close;

    public TuningPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
