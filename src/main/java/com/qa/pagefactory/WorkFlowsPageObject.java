package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/** @author Ankur Jaiswal
 * All workflow page related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class WorkFlowsPageObject {

  @FindBy(xpath="//table[contains(@class, 'es-search-results')]")
  public WebElement workflowTable;

  @FindBy(xpath = "//div[contains(@class,'element-info')]//div[contains(@class,'data_name')]")
  public WebElement workflowName;

  @FindBy(xpath = "//div[contains(@uib-tooltip,'Owner')]//div[contains(@class,'info-content')]")
  public WebElement workflowOwner;

  @FindBy(xpath = "//div[contains(@uib-tooltip,'Cluster')]//div[contains(@class,'info-content')]")
  public WebElement workflowCluster;

  @FindBy(xpath = "//div[contains(@uib-tooltip,'Start Time')]//div[contains(@class,'info-content')]")
  public WebElement workflowStartTime;

  @FindBy(xpath = "//div[contains(@uib-tooltip,'End Time')]//div[contains(@class,'info-content')]")
  public WebElement workflowEndTime;

  @FindBy(xpath = "//li[contains(@class,'wf-duration')]/div[2]")
  public WebElement workflowDuration;

  @FindBy(xpath = "//li[contains(@class,'wf-io')]/div[2]")
  public WebElement workflowIO;

  @FindBy(xpath = "//h2//span")
  public WebElement workflowID;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public WorkFlowsPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
