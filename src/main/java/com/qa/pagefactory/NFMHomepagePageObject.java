package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NFMHomepagePageObject {

  @FindBy(xpath="//td[@class='tdborderdashboard']/a[contains(@href,'ADD')]")
  public WebElement addOrderMasterDetails;

  @FindBy(xpath="//a[contains(text(),'Logout')]")
  public WebElement logout;
  
  /**
   * @param driver The driver that will be used to look up the elements
   */
  public NFMHomepagePageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
