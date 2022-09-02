package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WorkloadFitPageObject {

    @FindBy(xpath = "//section[contains(@class,'component-message-banner scrollbar-s')]//div")
    public List<WebElement> noJobSelectedErrorMessage;

    @FindBy(xpath = "(//div[contains(@class,'pie-legend')])[1]//label/span[text()='All']")
    public List<WebElement> AllTagOfJobTypes;

    @FindBy(xpath = "(//div[contains(@class,'pie-legend')])[3]//label/span[text()='All']")
    public List<WebElement> AllTagOfUserTypes;

    @FindBy(xpath = "(//div[contains(@class,'pie-legend')])[5]//label/span[text()='All']")
    public List<WebElement> AllTagOfQueuesTypes;

    @FindBy(xpath = "(//div[contains(@class,'pie-legend')])[7]//label/span[text()='All']")
    public List<WebElement> AllTagOfTagsTypes;

    @FindBy(xpath = "//span[@class='checkmark legend-checkbox-all']")
    public WebElement selectAllJobTypes;

    @FindBy(xpath = "(//span[@class='checkmark legend-checkbox-all'])[2]")
    public WebElement selectAllUserTypes;

    @FindBy(xpath = "(//span[@class='checkmark legend-checkbox-all'])[3]")
    public WebElement selectAllQueueTypes;

    @FindBy(xpath = "(//span[@class='checkmark legend-checkbox-all'])[4]")
    public WebElement selectAllTagTypes;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
    public WebElement failedErrorMessage;

    @FindBy(xpath = "//button[text()[normalize-space()='Get HeatMap View']]")
    public WebElement generateHeatMap;

    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    public WebElement editPreferences;

    @FindBy(xpath = "//a[contains(text(),'Add')]")
    public WebElement addPreferences;

    @FindBy(xpath = "//div[@class='vm-preference']//table[contains(@class,'component-data-tables')]//tbody/tr//label[contains(concat(' ', normalize-space(@class), ' '), ' active ')]")
    public List<WebElement> activeVMPreference;

    @FindBy(xpath = "//div[@class='vm-preference']//table[contains(@class,'component-data-tables')]//tbody/tr//label[contains(concat(' ', normalize-space(@class), ' '), ' nonactive ')]")
    public WebElement nonActiveVMPreference;

    @FindBy(xpath = "//div[@class='vm-preference']//table[contains(@class,'component-data-tables')]//tbody/tr//label[contains(concat(' ', normalize-space(@class), ' '), ' nonactive ')]/input")
    public WebElement selectFirstVMPreference;

    @FindBy(xpath = "//div[@class='vm-preference']//input[@type='search']")
    public WebElement vmPreferenceSearchBox;

    @FindBy(xpath = "//span[contains(@class,'cta-primary')]//span[text()='Set']")
    public WebElement clickSetButton;

    @FindBy(xpath = "//div[text()='Job Type']//parent::div//div[@track-by='name']//div/span")
    public List<WebElement> getJobTypeNames;

    @FindBy(xpath = "//div[text()='Users']//parent::div//div[@track-by='name']//div/span")
    public List<WebElement> getUserTypeNames;

    @FindBy(xpath = "//div[text()='Queues']//parent::div//div[@track-by='name']//div/span")
    public List<WebElement> getQueuesTypeNames;

    @FindBy(xpath = "//div[text()='Tags']//parent::div//div[@track-by='name']//div/span")
    public List<WebElement> getTagsTypeNames;

    @FindBy(xpath = "(//span[@class='select2-selection__arrow'])[2]")
    public WebElement heatMapDropdown;

    @FindBy(xpath = "//div[contains(@class,'heat-section')]//div[contains(@class,'highcharts-container')]")
    public WebElement heatMapHighchart;

    @FindBy(xpath = "((//div[contains(@class,'pie-legend')])[1]//div[@track-by='name']/label/span[@class='checkmark'])[1]")
    public WebElement selectJobFirstCheckBox;

    @FindBy(xpath = "((//div[contains(@class,'pie-legend')])[3]//div[@track-by='name']/label/span[@class='checkmark'])[1]")
    public WebElement selectUserFirstCheckBox;

    @FindBy(xpath = "((//div[contains(@class,'pie-legend')])[5]//div[@track-by='name']/label/span[@class='checkmark'])[1]")
    public WebElement selectQueueFirstCheckBox;

    @FindBy(xpath = "((//div[contains(@class,'pie-legend')])[7]//div[@track-by='name']/label/span[@class='checkmark'])[1]")
    public WebElement selectTagFirstCheckBox;

    @FindBy(xpath = "//div[contains(@class,'scrollable-tab-container')]//li/a")
    public List<WebElement> mapToClusterTabs;

    @FindBy (xpath = "//label[text()='Object Storage Size Required:']/following::input")
    public WebElement objectStorageSize;

    @FindBy (xpath = "//label[text()='Local Attached Storage Size Required for all Clusters:']/following::input")
    public WebElement localStorageSize;

    @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Run')]")
    public WebElement runButton;

    @FindBy(css = "i.glyphicon-caret")
    public WebElement dateRangePointer;

    public WorkloadFitPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
