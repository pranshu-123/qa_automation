package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NFMLoginPageObject {

  @FindBy(xpath="//input[@name ='uName']")
  public WebElement username;
  
  @FindBy(xpath="//input[@name ='pwdBxCls']")
  public WebElement password;
  
  @FindBy(xpath="//input[@name ='Login']")
  public WebElement login;


  /**
   * @param driver The driver that will be used to look up the elements
   */
  public NFMLoginPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
