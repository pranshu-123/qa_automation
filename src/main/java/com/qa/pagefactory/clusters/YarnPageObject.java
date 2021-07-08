package com.qa.pagefactory.clusters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Briender Kumar
 */
public class YarnPageObject {

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Resources'])")
    public WebElement clusterResourcesTab;

    @FindBy(xpath = "//label[normalize-space()='Resource Type']")
    public WebElement getResourcesPageHeader;

    @FindBy(css=".select-resource-type ~ .select2 .selection .select2-selection__arrow")
    public WebElement resourceDropdownOption;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public List<WebElement> selectType;

    @FindBy(xpath = "//label[contains(text(), 'Group By')]/following-sibling::span/span/span/span[contains(@class,'select2-selection__arrow')]")
    public WebElement groupByDropdownButton;

    @FindBy(xpath = "//ul[@class='select2-results__options']/li")
    public List<WebElement> getGroupByDropdownElements;

    @FindBy(xpath = "//li[contains(text(),'Application Type')]")
    public WebElement groupByApplicationType;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUser;

    @FindBy(css = "//span[@id='select2-4abp-container']")
    public WebElement chargeBackDropdownOptionsButton;

    @FindBy(css = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
    public WebElement getNamesFromDropDown;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueue;

    @FindBy(xpath = "//li[contains(text(),'dept')]")
    public WebElement groupByDept;

    @FindBy(xpath = "//li[contains(text(),'project')]")
    public WebElement groupByProject;

    @FindBy(xpath = "//li[contains(text(),'realUser')]")
    public WebElement groupByRealUser;

    @FindBy(xpath = "//li[contains(text(),'dbs')]")
    public WebElement groupBydbs;

    @FindBy(xpath = "//li[contains(text(),'inputTables')]")
    public WebElement groupByInputTables;

    @FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
    public WebElement filterInput;

    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public List<WebElement> filterElements;

    @FindBy(xpath = "//ul[contains(@class,'select2-selection__rendered')]/li[@class='select2-selection__choice']")
    public List<WebElement> defaultSelectedFilterElements;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public List<WebElement> filterRemoveElements;

    @FindBy(xpath = "//span[@id='VCoreTS']//*[name()='svg']//*[local-name()='rect' and @class='highcharts-background']")
    public WebElement vcoresSVGGraph;

    @FindBy(xpath = "//div[@id='VCoreTS']//div[@class='footer']//div[@class='container']//label[@class='checkbox alt-error']//span//span[contains(text(),'Total Available')]")
    public WebElement vcoresTotalAvailableChkBox;

    @FindBy(xpath = "//span[@id='MemoryTS']//*[name()='svg']//*[local-name()='rect' and @class='highcharts-background']")
    public WebElement memorySVGGraph;

    @FindBy(xpath = "//div[@id='VCoreTS']//div[@class='footer']//div[@class='container']//label[@class='checkbox alt-error']//span//span[contains(text(),'Total Allocated')]")
    public WebElement vcoresTotalAllocatedChkBox;

    @FindBy(xpath = "//div[@id='MemoryTS']//div[@class='footer']//div[@class='container']//label[@class='checkbox alt-error']//span//span[contains(text(),'Total Available')]")
    public WebElement memoryTotalAvailableChkBox;

    @FindBy(xpath = "//div[@id='MemoryTS']//div[@class='footer']//div[@class='container']//label[@class='checkbox alt-error']//span//span[contains(text(),'Total Allocated')]")
    public WebElement memoryTotalAllocatedChkBox;

    @FindBy(xpath = "//div[@id='VCore-Application']//h4")
    public WebElement vcoreApplicationText;

    @FindBy(xpath = "//div[@id='VCore-Application']//div[3]//label/span[1]")
    public List<WebElement> vcoreApplicationChkBoxList;

    @FindBy(xpath = "//div[@id='Memory-Application']//h4")
    public WebElement memoryApplicationText;

    @FindBy(xpath = "//div[@id='Memory-Application']//div[3]//label/span[1]")
    public List<WebElement> memoryApplicationChkBoxList;

    @FindBy(xpath = "//span[@id='VCoreTS']/div/div")
    public WebElement vCoresGraph;

    public By graphGContents = By.cssSelector("svg > g.highcharts-series-group");

    @FindBy(xpath = "//*[@id='VCore-Application']//*[name()='svg']//*[local-name()='rect' and @class='highcharts-background']")
    public WebElement vCoresAppGraph;

    @FindBy(xpath = "//div[contains(@id,'VCore-Application')]//div[@class='footer']//label")
    public List<WebElement> vcoreGraphAppNames;

    @FindBy(xpath = "//div[contains(@id,'VCore-Application')]//div[@class='footer']//label//span[@class='checkmark']")
    public List<WebElement> vcoreGraphAppsRGBValues;

    @FindBy(xpath = "//div[@id='VCore-Application']//*[name()='svg']//*[local-name()='g']//*[local-name()='g']//*[local-name()='path'][1]")
    public List<WebElement> hashCodeVcoreColors;

    public YarnPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Get child elements with reference of parent element
    public List<WebElement> getChildElement(WebElement parentElement, By childElement) {
        return parentElement.findElements(childElement);
    }

}
