package com.qa.pagefactory.reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ReportsArchiveScheduledPageObject {

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]")
  public WebElement reportArchiveHeader;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]")
  public WebElement scheduledReportsHeader;

  @FindBy(xpath = "//table/tbody/tr")
  public List<WebElement> tableRows;

  @FindBy(xpath = "//*[@class='clearfix']//td/p")
  public WebElement noDataToDisplay;

  @FindBy(xpath = " //div[contains(@class,'component-cta')]")
  public WebElement goBackButton;

  @FindBy(xpath = "//table/tbody/tr/td[1]/span")
  public List<WebElement> reportNames;

  @FindBy(xpath = "//table/tbody/tr/td[2]/span")
  public List<WebElement> reportCnt;

  @FindBy(xpath = "//*[contains(@class,'status-btn')]")
  public List<WebElement> reportStatus;

  @FindBy(xpath = "(//*[@class='col-md-2']//input)")
  public WebElement reportSearchBox;

  @FindBy(xpath = "//a[contains(@class,'icon-sort')]")
  public List<WebElement> sortingIcon;

  @FindBy(xpath = "//table/thead/tr/th[1]/a")
  public WebElement sortingReportNameIcon;

  @FindBy(xpath = "//table/thead/tr/th[2]/a")
  public WebElement sortingReportCntIcon;

  @FindBy(xpath = "//table/thead/tr/th[3]/a")
  public WebElement sortingStatusIcon;

  @FindBy(xpath = "(//*[@class='icon-add is-enabled'])")
  public List<WebElement> newReportIcon;

  @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
  public WebElement reportCreationNotSup;

  @FindBy(xpath = "//span[contains(@class,'component-cta')]/a/span")
  public WebElement reportCreationRunButton;

  @FindBy(xpath = "(//*[@class='icon-download is-enabled'])[1]")
  public WebElement downloadReportIcon;

  @FindBy(xpath = "(//*[@role='dialog'])")
  public WebElement viewReportDialogWin;

  @FindBy(xpath = "(//*[@class='icon-expand is-enabled'])[1]")
  public WebElement viewReportIcon;

  @FindBy(xpath = "(//*[@class='icon-calendar is-enabled'])")
  public List<WebElement> scheduleReportIcon;

  @FindBy(xpath = "(//*[@class='icon-expand is-enabled'])")
  public List<WebElement> latestReportIcon;

  @FindBy(xpath = "//*[@class='col-md-12']//p/input")
  public List<WebElement> newReportField;

  @FindBy(xpath = "//*[name()='svg' and @data-icon='forward']")
  public WebElement fwdCaretReportCnt;

  @FindBy(xpath = "//*[name()='svg' and @data-icon='caret-right']")
  public WebElement rightCaretReportCnt;

  @FindBy(xpath = "//*[name()='svg' and @data-icon='backward']")
  public WebElement backwardCaretReportCnt;

  @FindBy(xpath = "//div[2]//nav/p[@class='pointer']")
  public WebElement reportCntPerPage;

  @FindBy(xpath = "//*[@class='select2-selection__rendered']")
  public List<WebElement> topXtextFields;

  @FindBy(xpath = "//*[@class='select2-results__options']/li[2]")
  public WebElement topXFieldValue;

  @FindBy(xpath = "//*[@class='tag-checkbox']")
  public List<WebElement> checkBoxSelections;

  @FindBy(xpath = " //table/thead/tr/th[7]/label")
  public WebElement cloudMappingChkBox ;

  @FindBy(xpath = "//*[@class='tag-list']/span")
  public WebElement tagListFields;

  @FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
  public WebElement downloadSuccessfulBanner;

  @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h2")
  public WebElement latestReportHeader;

  @FindBy(xpath = "//*[@class='close pointer']")
  public WebElement closeTab;

  @FindBy(xpath = "//*[@id='topx-param-tbl']/tbody/tr/td[2]")
  public List<WebElement> latestReportTableContent;


  /**
   * @param driver The driver that will be used to look up the elements
   */
  public ReportsArchiveScheduledPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
