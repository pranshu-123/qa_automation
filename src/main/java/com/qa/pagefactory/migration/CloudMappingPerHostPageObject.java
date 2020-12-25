package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CloudMappingPerHostPageObject {
  @FindBy(xpath = "//li[contains(@class,'router-link-exact-active')]//span[contains(text(),'Cloud Mapping Per Host')]")
  public WebElement cloudMappingPerHostTab;

  @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Run')]")
  public WebElement runButton;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]//span[contains(text(),'Run')]")
  public WebElement newReportRunButton;

  @FindBy(xpath = "//div[@class='table-container']//div[@class='row']/div[@class='col-md-2']//input[@class='search']")
  public WebElement searchField;

  @FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Schedule')]")
  public WebElement scheduleButton;

  @FindBy(xpath = "//span[@class='select2-selection__arrow']")
  public List<WebElement> dropDownBtn;

  @FindBy(xpath = "//div[@class='col-md-4']//span[contains(@class,'select2-selection__arrow')]")
  public List<WebElement> cloudProductDropDown;

  @FindBy(xpath = "//div[contains(@class,'col-md-4 boot-icons')]//span[contains(@class,'select2-selection__rendered')]")
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

  @FindBy(xpath = "//ul[contains(@class,'lb-dropdown-label js-open')]/li")
  public List<WebElement> regionsList;

  @FindBy(xpath = "//*[@id='region-selector']/optgroup[contains(@label,'France')]/option[1]")
  public WebElement hdiRegionsList;

  @FindBy(xpath = "//*[@id='element-a842d544-ca56-4cb2-a1bb-7bb9f83a50cd']/table/tbody/tr/td[1]")
  public List<WebElement> emrVMList;

  @FindBy(xpath = "//*[@id='element-ebfd025d-127e-4c9e-8969-0acb9addbcaa']/div[2]/div[2]/table/tbody/tr/td[1]")
  public List<WebElement> ec2VMList;

  @FindBy(xpath = "//*[@id='main']/section[4]/div/div/table/tbody/tr/td[1]")
  public List<WebElement> hdiVMList;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public CloudMappingPerHostPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
