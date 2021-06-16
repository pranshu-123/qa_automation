package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/** @author Ankur Jaiswal
 * All WebElement which is present at login page of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class LoginPageObject {

  @FindBy(name="username")
  public WebElement loginUserName;

  @FindBy(name="password")
  public WebElement loginPassword;

  @FindBy(name = "username")
  public List<WebElement> loginButtonList;

  @FindBy(css = "a.icon-logout")
  public WebElement logoutButton;

  @FindBy(css = "a.icon-logout")
  public List<WebElement> logoutButtonList;

  @FindBy(xpath="//button[normalize-space()='Sign in']")
  public WebElement signInButton;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public LoginPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
