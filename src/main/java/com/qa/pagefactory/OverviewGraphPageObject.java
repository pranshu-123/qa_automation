package com.qa.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

/** @author Ankur Jaiswal
 * All WebElement which is related with graphs
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class OverviewGraphPageObject {

  @FindBy(xpath = "//*[@id=\"MemryTSOverview\"]/span/div/div")
  public WebElement memoryGraph;

  @FindBy(xpath = "//span[@id=\"MemryTSOverview\"]/following-sibling::div//span[contains(text(),'Total Available')]/following-sibling::input")
  public WebElement memoryTotalAvailableCheckbox;

  @FindBy(xpath = "//span[@id=\"MemryTSOverview\"]/following-sibling::div//span[contains(text(),'Total Allocated')]/following-sibling::input")
  public WebElement memoryTotalAllocatedCheckbox;

  //@FindBy(xpath="//*[@id=\"highcharts-lsgdzb1-0\"]")
  @FindBy(xpath="//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[2]")
  public WebElement nodeGraph;

  //@FindBy(xpath="//span[@class='checkmark']//parent::label//preceding-sibling::span[text()='Active']")
  @FindBy(xpath="//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[1]")
  public  WebElement nodeActiveChkBoxText;

  //@FindBy(xpath="//span[@class='checkmark']//parent::label//preceding-sibling::span[text()='Bad']")
  @FindBy(xpath="//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[2]")
  public  WebElement nodebBadChkBoxText;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div[2]")
  public WebElement vcoreGraph;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div[3]/label[1]")
  public WebElement VcoreTotalAvailableCheckbox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div[3]/label[2]")
  public WebElement VcoreTotalAllocatedCheckbox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[3]/div[1]/div/div/div/div/div/div[2]")
  public WebElement JobsGraph;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[3]/div[1]/div/div/div/div/div/div[3]/div/label[3]")
  public WebElement JobsNewCheckbox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[3]/div[1]/div/div/div/div/div/div[3]/div/label[2]")
  public WebElement JobsRunningCheckbox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[3]/div[1]/div/div/div/div/div/div[3]/div/label[1]")
  public WebElement JobsAcceptedCheckbox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[2]/div/div[2]/div/div")
  public WebElement statusGraph;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[2]/div/div[3]/label[1]")
  public WebElement SuccessChkBox;

  @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[2]/div/div[3]/label[2]")
  public WebElement FailedChkBox;

  @FindBy(xpath="//*[@id='app']/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div")
  public WebElement runningGraph;

  @FindBy(xpath="//*[@id='app']/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div/div[3]/label[1]")
  public  WebElement runningChkBoxText;

  @FindBy(xpath="//*[@id='app']/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div/div[3]/label[2]")
  public  WebElement acceptedChkBoxText;

  @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'By Type')]")
  public WebElement inefficientEventsGraphHeading;

  @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'Inefficient Events')]/parent::div/following-sibling::div[1]/div")
  public WebElement inefficientEventsGraph;

  public By graphGContents = By.cssSelector("svg > g.highcharts-series-group");

  @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'Inefficient events')]/parent::div/following-sibling::div[2]/label[1]")
  public WebElement inefficientGraphMRCheckbox;

  @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'Inefficient events')]/parent::div/following-sibling::div[2]/label[2]")
  public WebElement inefficientGraphHiveCheckbox;

  @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'Inefficient events')]/parent::div/following-sibling::div[2]/label[3]")
  public WebElement inefficientGraphSparkCheckbox;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public OverviewGraphPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }

}
