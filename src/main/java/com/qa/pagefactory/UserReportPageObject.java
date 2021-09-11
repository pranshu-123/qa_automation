package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author sarbashree ray
 * All date schedule date related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class UserReportPageObject {

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[1]/h1")
    public List<WebElement> HeaderElement;

    @FindBy(xpath = "//div[@class=\"col-md-12\"]")
    public WebElement tblScheduledReportsApps;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[3]/div/div[3]/div/table/tbody/tr[1]")
    public List<WebElement> tblScheduledReportsRowsList;

    @FindBy(xpath = "//a[@href='#/clusters/overview']")
    public WebElement unravelLogo;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div[2]/div[2]/div/div/section/div")
    public List<WebElement> confirmationMessageschedule;

    @FindBy(xpath = "//*[@class=\"col-md-12\"]//tbody/tr/td[4]")
    public WebElement getUserNameFromTable;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div/div[2]/div/div[3]/div/div/div/div[3]/div")
    public List<WebElement> userOptions;

    @FindBy(xpath = "//div[@class='row']//a/span[text()='Schedule User Report']")
    public WebElement scheduleuserreportButton;

    @FindBy(xpath = "//div[@id='action-2']//span//span[@class='pointer icon-calendar is-enabled']")
    public WebElement addArchiveduserreport;

    @FindBy(xpath = "//table/tbody/tr/td[1]/span")
    public List<WebElement> reportNames;

    @FindBy(xpath = "//*[@class=\"col-md-12\"]//tbody/tr/td[4]")
    public WebElement scheduleName;

    @FindBy(xpath = "//span[@class=\"glyphicon glyphicon-plus-sign pointer\"]")
    public WebElement addconfiguration;

    @FindBy(xpath = "(//section[contains(@class,'component-message-banner')])[1]/span[contains(text(),'The report has been scheduled successfully.')]")
    public WebElement scheduleSuccessfulMessage;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[4]/div/div[3]/span[1]/a/span")
    public WebElement addbutton;

    @FindBy(xpath = "//option[contains(text(),'Daily')]")
    public WebElement selectdaily;

    @FindBy(xpath = "//span[text()=\"Cancel\"]")
    public WebElement cancelbutton;

    @FindBy(xpath = "//div[@class=\"pointer close right\"]")
    public WebElement closebutton;

    @FindBy(xpath = "//input[contains(@type,'number')]")
    public List<WebElement> topXNumber;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[1]/div[2]/input")
    public List<WebElement> Shedulename;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[1]/div/div[2]/div[2]/div[1]/input")
    public List<WebElement> searchShedulename;

    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[4]")
    public WebElement queuesDropdown;

    @FindBy(xpath = "(//*[@class=\"select2-selection__rendered\"])[4]")
    public WebElement realUser;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li")
    public WebElement dropdownOptions;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select")
    public List<WebElement> rundropdown;

    @FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li")
    public List<WebElement> realUsersDropdown;

    @FindBy(xpath = "//span[@class='select2 select2-container select2-container--default select2-container--focus select2-container--below']//ul[@class='select2-selection__rendered']")
    public WebElement realUsers;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUser;

    @FindBy(xpath = "//li[contains(text(),'Application Type')]")
    public WebElement groupByAppType;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueue;


    @FindBy(xpath = "(//select[@class=\"form-control input-sm schedule-days\"]")
    public WebElement daysdropdown;

    @FindBy(xpath = "(//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[1]/option[12]")
    public WebElement addhour;

    @FindBy(xpath = "(//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[2]/option[12]")
    public WebElement addminutes;

    @FindBy(xpath = "(//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]")
    public List<WebElement> addtime;

    @FindBy(xpath = "//input[@type=\"email\" and @placeholder=\"Email\"]")
    public WebElement emailNotification;

    @FindBy(linkText = "Schedule")
    public WebElement saveschedule;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[1]/div[2]/input")
    public WebElement drop;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[1]/div")
    public WebElement closeWindow;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public UserReportPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

