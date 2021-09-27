package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CloudMappingPerHostPageObject {
  @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Cloud Mapping Per Host'])")
  public WebElement cloudMappingPerHostTab;

  @FindBy(xpath = "//button[contains(@class,'run-btn')]")
  public WebElement runButton;

  @FindBy(xpath = "//*[@id='cloudmapping']/div[1]/div/div[2]/div/div[3]/span[1]")
  public WebElement modalRunButton;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]//span[contains(text(),'Run')]")
  public WebElement newReportRunButton;

  @FindBy(xpath = "//div[@class='table-container']//div[@class='row']/div[@class='col-md-2']//input[@class='search']")
  public WebElement searchField;

  @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Schedule')]")
  public WebElement scheduleButton;

  @FindBy(xpath = "//span[@class='select2-selection__arrow']")
  public List<WebElement> dropDownBtn;

  @FindBy(xpath = "//div[contains(@class,'task_runner')]//select")
  public List<WebElement> cloudProductDropDown;

  @FindBy(xpath = "//*[@id='cloudmapping']/div[1]/div/div[2]/div/div[2]/div/div/form/div/div[2]/div[2]/div/div[2]/span/span[1]/span/span[2]")
  public WebElement regionDropDown;

  @FindBy(xpath = "//ul[@class='select2-results__options']/li")
  public List<WebElement> dropDownValues;

  @FindBy(xpath = "//div/table/tbody/tr/td[7]/label/span")
  public WebElement checkBox;

  @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
  public WebElement bannerMsg;

  @FindBy(xpath = "//div[@class='cust-row']/div[@class='col-md-12 space']")
  public WebElement newReportWin;

  @FindBy(xpath = "//div[@class='close pointer']")
  public WebElement closeNewReportWin;

  @FindBy(xpath = "//div[@class='table-container']//tbody/tr/td[1]")
  public List<WebElement> vmTypeTableRows;

  @FindBy(xpath = "//div[@class='table-container']//tbody/tr/td[5]")
  public List<WebElement> vmCostTableRows;

  @FindBy(xpath = "//div[@class='table-container']//nav/p[@class='pointer']")
  public WebElement paginationCnt;

  @FindBy(xpath ="//div[@class='table-container']//nav/p[@class='pointer']//input")
  public WebElement incrementalPageCnt;

  @FindBy(xpath = "//div[@class='table-container']//a[@class='disabled']//*[name()='svg' and @data-icon='caret-right']")
  public WebElement disabledForwardCaret;

  @FindBy(xpath = "//div[@class='table-container']//*[name()='svg' and @data-icon='caret-right']")
  public WebElement forwardCaret;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[3]//tr/td[1]")
  public List<WebElement> actualUsageKey;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[3]//tr/td[2]")
  public List<WebElement> actualUsageValue;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[4]//tr/td[1]")
  public List<WebElement> capacityKey;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[4]//tr/td[2]")
  public List<WebElement> capacityValue;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[5]//tr/td[1]")
  public List<WebElement> recommendationsKey;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[5]//tr/td[2]")
  public List<WebElement> recommendationsValue;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table/tbody/tr[1]/td[6]")
  public WebElement totalCost;

  @FindBy(xpath = "//*[@id='host-details']/div[2]/div[2]/div/div[1]/div/table")
  public WebElement hostReportTable;

  // EMR web page xpath:

  @FindBy(xpath = "//*[@id='element-a842d544-ca56-4cb2-a1bb-7bb9f83a50cd']//ul")
  public WebElement emrRegionsDropDown;

  @FindBy(xpath = "//*[@id='element-ebfd025d-127e-4c9e-8969-0acb9addbcaa']//ul")
  public WebElement ec2RegionsDropDown;

  @FindBy(xpath = "//*[@id='region-selector']")
  public WebElement hdiRegionSelector;

  @FindBy(xpath = "//ul[contains(@class,'select2-results__option')]/li")
  public List<WebElement> regionsList;

  @FindBy(xpath = "//*[@id='region-selector']/optgroup[contains(@label,'France')]/option[1]")
  public WebElement hdiRegionsList;

  @FindBy(xpath = "//*[@id='element-a842d544-ca56-4cb2-a1bb-7bb9f83a50cd']/table/tbody/tr/td[1]")
  public List<WebElement> emrVMList;

  @FindBy(xpath = "//*[@id='element-ebfd025d-127e-4c9e-8969-0acb9addbcaa']/div[2]/div[2]/table/tbody/tr/td[1]")
  public List<WebElement> ec2VMList;

  @FindBy(xpath = "//*[@id='main']/section[4]/div/div/table/tbody/tr/td[1]")
  public List<WebElement> hdiVMList;

  @FindBy(xpath = "//div[contains(@class,'spinner')]")
  public List<WebElement> loaderElement;

  @FindBy(xpath = "//*[@id='cloudmapping']/div[1]/div/div[2]/div/div[2]/div/div/form/div/div[2]/div[2]/div/div[1]/span/span[1]/span/span[2]")
  public WebElement cloudProductServiceDropdownIcon;

  @FindBy(xpath = "(//div[@class='cust-row'])[5]/div/div/div/table[@class='component-data-tables']/tbody/tr")
  public List<WebElement> tableRows;

  @FindBy(xpath = "(//div[@class='cust-row'])[5]/div/div/div/table[@class='component-data-tables']//thead/tr")
  public WebElement tableHeadings;

  @FindBy(xpath = "//div[@class='table-container']//tbody/tr")
  public List<WebElement> modalTableRows;

  @FindBy(xpath = "//div[@class='table-container']//thead/tr")
  public WebElement modalTableHeadings;

  @FindBy(xpath = "//label[contains(@class,'checkbox active')]")
  public List<WebElement> activeCheckBoxes;

  @FindBy(xpath = "//label[contains(text(),'Storage Type')]/parent::div//span[contains(@class,'select2-selection__rendered')]")
  public WebElement storageTypeDropdown;

  @FindBy(xpath = "((//table[@class='component-data-tables'])[last()]/tbody/tr)")
  public List<WebElement> cloudMappingHostDetailsTableRows;

  @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
  public WebElement confirmationMessageElement;

  @FindBy(xpath = "//span[contains(text(), 'Total Hourly Cost :')]/parent::div/following-sibling::b")
  public WebElement totalHourlyCostValue;

  @FindBy(xpath = "//div[contains(text(), 'Total Local Attached Storage Cost:')]/following-sibling::b")
  public WebElement totalAttachedStorageCostValue;

  @FindBy(xpath = "//button[contains(text(),'COST REDUCTION')]")
  public WebElement costReductionTab;

  @FindBy(xpath = "//button[contains(text(),'LIFT AND SHIFT')]")
  public WebElement liftAndShiftTab;

  @FindBy(xpath = "//label[contains(text(), 'Storage Name')]")
  public WebElement storageNameLabel;

  @FindBy(xpath = "//div[contains(text(),'Cloud Product/Service')]")
  public WebElement cloudProductServicesLabel;

  @FindBy(xpath = "//div[contains(text(),'Instances Summary')]/parent::div//table[contains(@class,'component-data-tables')]/tbody/tr")
  public List<WebElement> instanceSummaryRows;

  @FindBy(xpath = "//div[contains(text(),'Cloud Product/Service')]/b")
  public WebElement cloudProductOrServiceValue;

  @FindBy(xpath = "//div[contains(text(),'Region')]/b")
  public WebElement regionDropDownValue;

  @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr/td[7]//input[contains(@type,'checkbox')]")
  public WebElement nodeTypeSelection;

  @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr/td[7]//label[contains(@class,'nonactive')]")
  public List<WebElement> checkOptionIfNonActive;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public CloudMappingPerHostPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
