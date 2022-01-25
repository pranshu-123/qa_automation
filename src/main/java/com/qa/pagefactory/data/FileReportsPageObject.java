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

  @FindBy(xpath = "//button[contains(text(),'large')]")
  public WebElement largeFile;

  @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-up']")
  public WebElement sortUp;

  @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-down']")
  public WebElement sortDown;

  @FindBy(xpath = "//span[normalize-space()='Avg File Size']")
  public WebElement sortAvgFileSize;

  @FindBy(xpath = "//button[text()[normalize-space()='large']]")
  public WebElement selectedLargeFileOpt;

  @FindBy(xpath = "//*[contains(text(),'medium')]")
  public WebElement mediumFile;

  @FindBy(xpath = "//button[text()[normalize-space()='medium']]")
  public WebElement selecteMediumFileOpt;

  @FindBy(xpath = "//*[contains(text(),'tiny')]")
  public WebElement tinyFile;

  @FindBy(xpath = "//button[normalize-space()='tiny']")
  public WebElement selecteTinyFileOpt;

  @FindBy(xpath = "//*[contains(text(),'empty')]")
  public WebElement emptyFile;

  @FindBy(xpath = "//button[normalize-space()='empty']")
  public WebElement selecteEmptyFileOpt;

  @FindBy(xpath = "//label[text()='Cluster']/following::input")
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
