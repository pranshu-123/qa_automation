package com.qa.pagefactory.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FileReportsPageObject {
  @FindBy(xpath = "//span[@class='select2-selection__rendered']")
  public WebElement clusterDropDown;

  @FindBy(xpath = "//ul[@class='select2-results__options']/li")
  public List<WebElement> clusterList;

  @FindBy(xpath = "//button[contains(text(),'LARGE')]")
  public WebElement largeFile;

  @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-up']")
  public WebElement sortUp;

  @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-down']")
  public WebElement sortDown;

  @FindBy(xpath = "//span[normalize-space()='Avg File Size']")
  public WebElement sortAvgFileSize;

  @FindBy(xpath = "//button[normalize-space()='LARGE']")
  public WebElement selectedLargeFileOpt;

  @FindBy(xpath = "//*[contains(text(),'MEDIUM')]")
  public WebElement mediumFile;

  @FindBy(xpath = "//button[normalize-space()='MEDIUM']")
  public WebElement selecteMediumFileOpt;

  @FindBy(xpath = "//*[contains(text(),'TINY')]")
  public WebElement tinyFile;

  @FindBy(xpath = "//button[normalize-space()='TINY']")
  public WebElement selecteTinyFileOpt;

  @FindBy(xpath = "//*[contains(text(),'EMPTY')]")
  public WebElement emptyFile;

  @FindBy(xpath = "(//span[contains(@class,'badge pending')]//a[text()='EMPTY'])")
  public WebElement selecteEmptyFileOpt;

  @FindBy(xpath = "//div[@class='col-md-2']/input")
  public WebElement searchField;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th")
  public List<WebElement> tableHeader;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
  public List<WebElement> fileTableRows;

  @FindBy(xpath = "//table/thead/tr")
  public WebElement colData;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody")
  public WebElement rowData;

  @FindBy(xpath = "//h3[normalize-space()='No Data Available']")
  public WebElement noDataText;

  @FindBy(xpath = "//*[@id='file-report']//div[4]/div[2]/nav/p")
  public WebElement pagination;

  @FindBy(xpath = "//*[name()='svg' and @data-icon='caret-right']")
  public WebElement rightCaretReportCnt;

  @FindBy(xpath = "//*[name()='svg' and @data-icon='backward']")
  public WebElement backwardCaretReportCnt;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public FileReportsPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
