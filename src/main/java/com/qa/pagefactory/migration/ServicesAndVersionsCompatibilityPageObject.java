package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Birender Kumar All 'Services And Versions Compatibility' tab of Migration related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object create an instance of this class and access
 * the members with that object.
 */

public class ServicesAndVersionsCompatibilityPageObject {

    @FindBy(xpath = "//div[text()='Services and Versions Compatibility completed successfully.']")
    public WebElement closeMsgBanner;

    @FindBy(xpath = "//button[@class='run-btn']")
    public WebElement runBtn;

    @FindBy(xpath = "//span[contains(text(), 'Run New')]//parent::a")
    public WebElement runNewBtn;

    @FindBy(xpath = "//span[@class='select2-selection__arrow']")
    public WebElement cloudProductDropDown;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> cloudProductList;

    @FindBy(xpath = "//span[contains(@class, 'select2-search--dropdown')]/input")
    public WebElement cloudProductSearchBox;

    @FindBy(xpath = "//span[@class='select2-results']//li")
    public WebElement cloudProductSearchFirstField;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/span/a")
    public WebElement runModalBtn;

    @FindBy(xpath = "//section[contains(@class, 'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//span[@class='select2-results']/ul/li[contains(text(), 'No results found')]")
    public WebElement noCloudProductResult;

    @FindBy(xpath = "//h3/b")
    public List<WebElement> latestReportList;

    @FindBy(xpath = "//div[contains(@class,'col-md-12 col-lg-12')]//div")
    public List<WebElement> legendList;

    /*@FindBy(xpath = "(//table[contains(@class, 'component-data-tables')])[2]")*/
    @FindBy(xpath = "(//table[contains(@class, 'component-data-tables row-hover')])[1]")
    public WebElement reportTable;

    @FindBy(xpath = "//tr[@class='plaforms']//th")
    public List<WebElement> platformList;

    @FindBy(xpath = "(//thead/tr)[2]/th")
    public List<WebElement> hdpHeaderList;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> rowsList;

    @FindBy(xpath = "//tbody/tr[1]/td")
    public List<WebElement> colList;

    @FindBy(xpath = "//span[text()='Archived']")
    public WebElement archiveReportSVCHeader;

    @FindBy(xpath = "//div[contains(@class,'popover')]/H3")
    public WebElement deletePopText;

    @FindBy(xpath = "//div[contains(@class,'popover')]/div/span[contains(@class,'pending')]")
    public WebElement deleteOkBtn;

    @FindBy(xpath = "//span[text()='Archives']")
    public WebElement archivesText;

    @FindBy(xpath = "//div[@class='bread-crumb boot-icons']")
    public WebElement archives;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement archiveReportDate;

    @FindBy(xpath = "//tbody/tr[1]/td[1]")
    public WebElement archiveReportName;

    @FindBy(xpath = "//span[normalize-space()='Schedule']")
    public WebElement scheduleBtn;

    @FindBy(xpath = "//*[@class='select2-selection__rendered']")
    public WebElement scheduleCloudDropDown;

    @FindBy(xpath = "//label[text()='Schedule Name']/following::input")
    public WebElement scheduleName;

    @FindBy(xpath = "//select[contains(@class, 'schedule-days')]")
    public WebElement scheduleToRun;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement scheduleNotification;

    @FindBy(xpath = "//div[contains(text(),'Services and Versions Compatibility')]")
    public WebElement scheduleMsg;

    @FindBy(xpath = "//span[normalize-space()='Schedule']")
    public WebElement scheduleRunBtn;

    @FindBy(xpath = "//input[@class = 'display-time']")
    public WebElement scheduleTime;

    @FindBy(xpath = "//select[@class='hours']")
    public WebElement scheduleTimeHours;

    @FindBy(xpath = "//select[@class='minutes']")
    public WebElement scheduleTimeMinutes;

    @FindBy(xpath = "//table[contains(@class, 'component-data-tables')]/tbody")
    public WebElement tableBodyElement;

    @FindBy(xpath = "//tbody/tr/td[contains(@class,'risk-default')]")
    public List<WebElement> missingSrcHeaderElement;

    @FindBy(xpath = "//tbody/tr/td[1][normalize-space()]")
    public List<WebElement> getAllOnPremisesServices;

    @FindBy(xpath = "//span/parent::a")
    public WebElement modalAfterRunButton;

    @FindBy(xpath = "//section/div")
    public WebElement banner;

    @FindBy(xpath = "//tbody/tr/td/p[contains(text(),'No Services And Versions Compatibility')]")
    public List<WebElement> reportPageEmptyVersionCompatibility;

    @FindBy(xpath = "//tbody/tr/td/p[contains(text(),'No Cluster Discover')]")
    public List<WebElement> reportPageEmptyClusterDiscover;

    @FindBy(xpath = "//span[contains(text(),'Services And Versions Compatibility')]")
    public List<WebElement> toolboxCD;

    @FindBy(xpath = "//div[@id='app']//h3[3]")
    public List<WebElement> clusterProductHeader;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ServicesAndVersionsCompatibilityPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
