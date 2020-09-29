package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/** @author Ankur Jaiswal
 * All date picker related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class DatePickerPageObject {

  @FindBy(css="div.component-date-picker")
  public WebElement dateRange;

  @FindBy(xpath = "//li[contains(text(),'Last 1 Hour')]")
  public WebElement lastOneHour;

  @FindBy(xpath = "//li[contains(text(),'Last 2 Hours')]")
  public WebElement lastTwoHour;

  @FindBy(xpath = "//li[contains(text(),'Last 6 Hours')]")
  public WebElement lastSixHour;

  @FindBy(xpath = "//li[contains(text(),'Last 12 Hours')]")
  public WebElement last12Hour;

  @FindBy(xpath = "//li[contains(text(),'Today')]")
  public WebElement today;

  @FindBy(xpath = "//li[contains(text(),'Yesterday')]")
  public WebElement yesterday;

  @FindBy(xpath="//li[contains(text(),'Last 7 Days')]")
  public WebElement last7Days;

  @FindBy(xpath = "//li[contains(text(),'Last 30 Days')]")
  public WebElement last30Days;

  @FindBy(xpath = "//li[contains(text(),'Last 90 Days')]")
  public WebElement last90Days;

  @FindBy(xpath = "//li[contains(text(),'This Month')]")
  public WebElement thisMonth;

  @FindBy(xpath = "//li[contains(text(),'Last Month')]")
  public WebElement lastMonth;

  @FindBy(xpath = "//li[contains(text(),'Custom Range')]")
  public WebElement customRange;

  @FindBy(name = "daterangepicker_start")
  public WebElement customRangeStartDate;

  @FindBy(name = "daterangepicker_end")
  public WebElement customRangeEndDate;

  @FindBy(className = "applyBtn")
  public WebElement applyBtn;
  
  @FindBy(xpath = "//div[contains(@class,'component-date-picker')]/span")
  public WebElement calendarDate;

  @FindBy(xpath = "//div[contains(@class,'daterangepicker') and contains(concat(' ', @class, ' '), ' open ')]//li")
  public List<WebElement> dateRangeOptions;

    /**
   * @param driver The driver that will be used to look up the elements
   */
  public DatePickerPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
