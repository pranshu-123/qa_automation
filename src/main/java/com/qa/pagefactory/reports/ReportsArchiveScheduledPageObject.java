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



  /**
   * @param driver The driver that will be used to look up the elements
   */
  public ReportsArchiveScheduledPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
