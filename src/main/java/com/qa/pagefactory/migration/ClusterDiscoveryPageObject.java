package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClusterDiscoveryPageObject {

    @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Run')]")
    public WebElement runButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a/i")
    public List<WebElement> confirmationMessageElementClose;

    @FindBy(xpath = "//a[normalize-space()='Run']")
    public WebElement modalRunButton;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner bg-success')]//div[1]")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//div[contains(@class,'daterangepicker') and contains(@class, 'open')]//li")
    public WebElement dateRanges;

    @FindBy(xpath = "//span[contains(@class,'text-fatal')]")
    public WebElement messageOnSelectingBeyond30days;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/span")
    public WebElement invalidInputMessage;

    @FindBy(xpath = "//div[@class='col-md-4']//h3[1]")
    public WebElement getClusterName;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'text']/*[local-name() = 'tspan'])[1]")
    public WebElement getByAppTypePieCount;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'text']/*[local-name() = 'tspan'])[2]")
    public WebElement getByUserPieCount;

    @FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'text']/*[local-name() = 'tspan'])[3]")
    public WebElement getByQueuePieCount;

    @FindBy(xpath = "//h3[contains(@class,'no-data-msg')]")
    public WebElement noDataByAppType;

    @FindBy(xpath = "(//div[contains(@class,'app-details')]//table//p)[2]")
    public WebElement noDataByUser;

    @FindBy(xpath = "(//div[contains(@class,'app-details')]//table//p)[3]")
    public WebElement noDataByQueue;

    @FindBy(xpath = "(//div[contains(@class,'app-details')])[1]//div[@class='highcharts-container ']")
    public WebElement appDataTypePie;

    @FindBy(xpath = "//div[@id='appt']//div[@class='pie-legend']//label/span[1]")
    public List<WebElement> getByAppTypesName;

    @FindBy(xpath = "//div[@id='user']//div[@class='pie-legend']//label/span[1]")
    public List<WebElement> getByUserTypesName;

    @FindBy(xpath = "//div[@id='queue']//div[@class='pie-legend']//label/span[1]")
    public List<WebElement> getByQueueTypesName;

    @FindBy(id = "exporter")
    public WebElement downloadJsonButton;

    @FindBy(xpath = "//div[contains(@class,'component-dropdown')]")
    public WebElement clickOnComponentDropdown;

    @FindBy(id = "cpu")
    public WebElement cpu_memoryHeatMap;

    @FindBy(xpath = "(//*[name()='rect'][@class='highcharts-background'])[4]")
    public WebElement heatMapContainer;

    @FindBy(xpath = "(//div[@id='cpu']//span)[1]")
    public WebElement cpuDropButton;

    @FindBy(xpath = "(//*[name()='rect'][@class='highcharts-background'])[5]")
    public WebElement selectMemoryinDropdown;

    @FindBy(xpath = "//span[@class='text-fatal']")
    public WebElement fatalMessage;

    @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div/span")
    public WebElement failedErrorMessage;



    public ClusterDiscoveryPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
