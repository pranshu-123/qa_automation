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

    @FindBy(xpath = "//span[contains(text(),'Run New')]/parent::a")
    public WebElement runNowButton;

    @FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
    public WebElement modalRunButton;

    @FindBy(xpath = "//b[contains(text(),'Forecasting')]//parent::p/following-sibling::p/input")
    public WebElement numOfDaysForForecasting;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-success icon-success')]")
    public WebElement headerMessageElement;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[1]/div[2]/div/div[4]/div/div[1]/div/div[1]")
    public WebElement verifyAbsoluteSize;

    @FindBy(xpath = "//h2[@class='text-white']/div")
    public WebElement modalCancelButton;

    @FindBy(xpath = "//h4[normalize-space()='Advanced Options']")
    public WebElement advancedOptions;

    @FindBy(xpath = "//span[@class='select2-results']/ul/li")
    public List<WebElement> clusterIdsList;

    @FindBy(xpath="//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
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

    @FindBy(xpath = "//*[@id=\"select2-h9li-container\"]")
    public WebElement clusterIdsearchfield;

    @FindBy(css = "//div[@class='close pointer']")
    public WebElement closeAppsPageTab;

    @FindBy(xpath = "(//input[contains(@type,'text')])[4]")
    public WebElement minimumSmallFile;

    @FindBy(xpath = "(//input[contains(@type,'text')])[5]")
    public WebElement directoriestoShow;

    @FindBy(xpath = "(//input[contains(@type,'text')])[5]")
    public WebElement onminParentDirectory;

    @FindBy(xpath = "(//input[contains(@type,'text')])[5]")
    public WebElement maxParentDirectory;

    @FindBy(xpath = "//div[@class='panel-body']/div[2]/p/b")
    public WebElement previousReportData;

    @FindBy(xpath = "//div[contains(@class,'close pointer')]")
    public WebElement closebutton;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SmallfilesPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
