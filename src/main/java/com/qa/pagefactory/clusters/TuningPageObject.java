package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TuningPageObject {

    @FindBy(xpath="")
    public WebElement e;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "//span[contains(text(),'Run')]/parent::a")
    public WebElement runButton;

    @FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
    public WebElement modalRunButton;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement closeNewReport;

    @FindBy(xpath = "//span[contains(text(),'Schedule')]/parent::a")
    public WebElement scheduleButton;

    @FindBy(xpath = "(//form[@name='taskrunnerForm']/div/div[3]/b[contains(text(), 'Schedule Name')]/parent::div/p/input)[1]")
    public WebElement scheduleName;

    @FindBy(xpath = "(//span[contains(text(),'Schedule')])[2]/parent::a")
    public WebElement modalScheduleButton;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[1]")
    public WebElement scheduleSuccessMsg;

    @FindBy(xpath = "//div[@class='task-runner-ht']/section/span[2]/a")
    public WebElement scheduleSuccessMsgClickHere;

    @FindBy(xpath = "//select[contains(@class,'schedule-days')]")
    public WebElement scheduleToRun;

    public TuningPageObject(WebDriver driver){PageFactory.initElements(driver,this);}
}
