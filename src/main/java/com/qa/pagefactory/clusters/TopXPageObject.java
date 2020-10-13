package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class TopXPageObject {

    @FindBy(xpath = "//span[contains(text(),'Run New')]/parent::a")
    public WebElement runNowButton;

    @FindBy(xpath = "//span[contains(text(),'Run')]/parent::a")
    public WebElement runButton;

    @FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
    public WebElement modalRunButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "//input[contains(@type,'number')]")
    public WebElement topXNumber;
    
    @FindBy(xpath = "//div[@class='row']//a/span[text()='Schedule']")
    public WebElement scheduleButton;
    
    @FindBy(xpath = "(//a/span[text()='Schedule'])[2]")
    public WebElement scheduleButtonInReport;

    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[2]")
    public WebElement usersDropdown;
    
    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li")
    public List<WebElement> dropdownOptions;
    
    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[3]")
    public WebElement readUsersDropdown;
    
    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[4]")
    public WebElement queuesDropdown;
    
    @FindBy(xpath = "//div/b[text()='Schedule Name']//following::p/input")
    public WebElement scheduleNameTextbox;
    
    @FindBy(xpath ="//p[@class='element-inline']/input")
    public WebElement emailNotification;
    
    @FindBy(xpath = "(//section[contains(@class,'component-message-banner')])[1]/span[contains(text(),'The report has been scheduled successfully.')]")
    public WebElement scheduleSuccessfulMessage;
    
    @FindBy(id = "topx-download")
    public WebElement downloadJsonButton;

    @FindBy(xpath = "//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
    public List<WebElement> clusterList;

    public TopXPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
