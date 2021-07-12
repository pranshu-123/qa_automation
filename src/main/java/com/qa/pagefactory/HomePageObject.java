package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/** @author Ankur Jaiswal
 * All WebElement which is present at application page of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class HomePageObject {

  @FindBy(xpath="//button[@href='#/app/operations/dashboard']")
  public WebElement dashboardTab;

  @FindBy(xpath="//button[@href='#/app/operations/charts/resources']")
  public WebElement usageDetailsTab;

  @FindBy(xpath = "//span[contains(@class,'select2-selection__arrow')]")
  public WebElement clusterDropdownSelect;

  @FindBy(css = "/html/body/div[1]/div/div/div/div[1]/div[1]/div/span/span[1]/span/span[1]")
  public WebElement clusterSpanSelector;

  @FindBy(xpath="//ul[contains(concat(' ', @class, ' '), 'select2-results__options')]/li")
  public List<WebElement> clusterList;

  @FindBy(xpath = "//a[@href='#/clusters/overview']")
  public WebElement unravelLogo;

  @FindBy(xpath = "//a[@href='#/clusters/overview']")
  public List<WebElement> unravelLogoList;

  @FindBy(xpath="//span[@class='kpi-title' and contains(text(),'Nodes')]")
  public WebElement kpiNodes;

  @FindBy(xpath="//span[@class='kpi-title' and contains(text(),'Available Vcores')]")
  public WebElement kpiVcores;

  @FindBy(xpath="//span[@class='kpi-title' and contains(text(),'Available  Memory')]")
  public WebElement kpiMemory;

  //@FindBy(xpath="//span[@class='checkmark']//parent::label//preceding-sibling::span[text()='Active']")
  @FindBy(xpath="//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[1]")
  public  WebElement nodeActiveChkBoxText;

  //@FindBy(xpath="//span[@class='checkmark']//parent::label//preceding-sibling::span[text()='Bad']")
  @FindBy(xpath="//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[2]")
  public  WebElement nodebBadChkBoxText;

  @FindBy(xpath="//span[contains(text(),'Active')]//following-sibling::span")
  public  WebElement activeChkBox;

  @FindBy(xpath = "//span[contains(text(),'Bad')]//following-sibling::span")
  public WebElement badChkBox;

  @FindBy(xpath="//span[text()='Total Available']//following-sibling::span")
  public  WebElement AvailableChkBox;

  @FindBy(xpath = "//span[text()='Total Allocated']//following-sibling::span")
  public WebElement AllocatedChkBox;

  @FindBy(xpath="//span[text()='Success']//following-sibling::span")
  public  WebElement SuccessChkBox;

  @FindBy(xpath = "//span[text()='Failed']//following-sibling::span")
  public WebElement FailedChkBox;

  @FindBy(xpath = "//div[@class='row pointer'][1]")
  public WebElement firstAlertRow;

  @FindBy(xpath="//div[@class='g-two-container']/div[2]/div/h2")
  public WebElement alertsText;

  @FindBy(xpath = "//div[@class='clearfix']/table//td/p")
  public WebElement noRecentAlertsText;

  @FindBy(xpath = "//a[@href='#/alerts/autoaction']")
  public WebElement autoalert;

  @FindBy(xpath = "//a[@href='#/alerts/autoaction/add']")
  public WebElement newautoalert;

  @FindBy(xpath = "//div[@graph-id='NodesTS']//a[@class='menu']")
  public WebElement nodesGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'Nodes')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listnodesGraphDownloadMenu;

  @FindBy(xpath ="//div[@graph-id='VCoreTSOverview']//a[@class='menu']")
  public WebElement vcoresGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'VCores')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listvcoresGraphDownloadMenu;

  @FindBy(xpath ="//div[@graph-id='MemryTSOverview']//a[@class='menu']")
  public WebElement memoryGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'Memory')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listmemoryGraphDownloadMenu;

  @FindBy(xpath ="//div[@graph-id='JobByStatus']//a[@class='menu']")
  public WebElement runningGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'By State')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listrunningGraphDownloadMenu;

  @FindBy(xpath ="//div[@graph-id='JobsFinishedByStatus']//a[@class='menu']")
  public WebElement bystatusGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'By Status')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listbystatusGraphDownloadMenu;

  @FindBy(xpath ="//div[@graph-id='JobsByInefficiency']//a[@class='menu']")
  public WebElement inefficienteventsGraphDownloadMenu;

  @FindBy(xpath = "//h4[contains(text(),'Inefficient Events')]/following-sibling::div[1]/div/ul/li/a")
  public List<WebElement> listinefficienteventsGraphDownloadMenu;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public HomePageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }

}
