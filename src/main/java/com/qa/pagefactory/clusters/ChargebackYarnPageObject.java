package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ChargebackYarnPageObject {
    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Chargeback'])")
    public WebElement clusterChargeBackTab;

    @FindBy(xpath = ".select-chargback-type ~ .select2 .selection .select2-selection__arrow")
    public List<WebElement> chargeBackDropdownsButton;

    @FindBy(css=".select-chargback-type ~ .select2 .selection .select2-selection__arrow")
    public WebElement yarnDropdownOption;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public List<WebElement> selectType;

    @FindBy(xpath = "//label[normalize-space()='Chargeback Type']")
    public WebElement chargeBackPageHeading;

    @FindBy(id = "chargebackvcore")
    public WebElement setChargebackVcorePerHour;

    @FindBy(id = "chargebackmemory")
    public WebElement setChargebackMemoryPerHour;

    @FindBy(css = ".btn-apply")
    public WebElement applyButton;

    @FindBy(xpath = "//span[contains(@class, 'select2-selection__arrow')]")
    public WebElement containerDropdownArrow;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> clustersList;

    @FindBy(xpath="//table[@id='chargeback-table']/tbody/tr")
    public List<WebElement> resultsGroupedByTableRecords;

    @FindBy(xpath="//table[@id='chargeback-table']/thead/tr/th")
    public List<WebElement> resultsGroupedByTableHeaderNames;

    @FindBy(xpath="//table[@id='finishedYarnJobsTable']/tbody/tr")
    public List<WebElement> yarnJobsTableRecords;

    @FindBy(xpath="//table[@id='finishedYarnJobsTable']/thead/tr/th")
    public List<WebElement> yarnJobsTableHeaderNames;

    @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//li")
    public List<WebElement> listOfClusters;

    @FindBy(xpath = "//span[contains(@class, 'select2-selection--multiple')]")
    public WebElement groupBySearchBox;

    @FindBy(xpath = "//span[contains(@class, 'select2-results')]/ul/li")
    public List<WebElement> listOfGroupByOptions;

    @FindBy(xpath="//a[@class='icon-gear']")
    public WebElement iconGear;

    @FindBy(xpath = "//a[@class='icon-gear']/div/ul/li/p/label/input")
    public List<WebElement> listOfIconGearChkBox;

    @FindBy(xpath = "//a[@class='icon-gear']/div/ul/li/p/label/input[@id='Type']")
    public WebElement iconGearTypeChkBox;

    public ChargebackYarnPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
