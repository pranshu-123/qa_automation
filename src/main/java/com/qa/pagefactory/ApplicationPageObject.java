package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationPageObject {

  @FindBy(xpath="//table[contains(@class, 'es-search-results')]")
  public WebElement applicationTable;

  @FindBy(xpath="//button[contains(text(), 'Running Applications')]")
  public WebElement runningApplicationTab;

  @FindBy(xpath="//button[contains(text(), 'Workflows')]")
  public WebElement workflowsTab;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public ApplicationPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
