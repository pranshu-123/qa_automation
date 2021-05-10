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

  @FindBy(xpath = "//span[contains(text(),'LARGE')]")
  public WebElement largeFile;

  @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
  public WebElement sortUp;

  @FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
  public WebElement sortDown;

  @FindBy(xpath = "//span[normalize-space()='Avg File Size']")
  public WebElement sortAvgFileSize;


  @FindBy(xpath = "//span[contains(text(),'LARGE') and contains(@class, 'badge pending')]")
  public WebElement selectedLargeFileOpt;

  @FindBy(xpath = "//span[contains(text(),'MEDIUM')]")
  public WebElement mediumFile;

  @FindBy(xpath = "//span[contains(text(),'MEDIUM') and contains(@class, 'badge pending')]")
  public WebElement selecteMediumFileOpt;

  @FindBy(xpath = "//span[contains(text(),'TINY')]")
  public WebElement tinyFile;

  @FindBy(xpath = "//span[contains(text(),'TINY') and contains(@class, 'badge pending')]")
  public WebElement selecteTinyFileOpt;

  @FindBy(xpath = "//span[contains(text(),'EMPTY')]")
  public WebElement emptyFile;

  @FindBy(xpath = "//span[contains(text(),'EMPTY') and contains(@class, 'badge pending')]")
  public WebElement selecteEmptyFileOpt;

  @FindBy(xpath = "//div[@class='col-md-2']/input")
  public WebElement searchField;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th")
  public List<WebElement> tableHeader;

  @FindBy(xpath = "//tbody/tr/td[2]")
  public WebElement fileColumn;

  @FindBy(xpath = "//tbody/tr/td[4]")
  public WebElement totalFileSizeColumn;

  @FindBy(xpath = "//tbody/tr/td[5]")
  public WebElement minFileSizeColumn;

  @FindBy(xpath = "//tbody/tr/td[1]")
  public WebElement pathColumn;

  @FindBy(xpath = "//tbody/tr/td[3]")
  public WebElement avgFileSizeColumn;

  @FindBy(xpath = "//tbody/tr/td[6]")
  public WebElement maxFileSizeColumn;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
  public List<WebElement> fileTableRows;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr[" + 1 + "]/td[" + 6 + "]")
  public WebElement maxFileSizeRowData;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr[" + 1 + "]/td[" + 5 + "]")
  public WebElement minFileSizeRowData;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr[" + 1 + "]/td[" + 4 + "]")
  public WebElement totalFileSizeRowData;

  @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr[" + 1 + "]/td[" + 3 + "]")
  public WebElement avgFileSizeRowData;

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
