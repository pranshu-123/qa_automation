package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

  @FindBy(css = "a.icon-logout")
  public WebElement logoutButton;

  @FindBy(xpath="//span[contains(text(),'Sign in')]/parent::a")
  public WebElement signInButton;

  @FindBy(xpath="/html/body/div[1]/ui-view/login-form/header/div[4]/div/div/a/div")
  public WebElement we_imgName;

  @FindBy(css = ".head-logo")
  public WebElement we_headLogo;

  @FindBy(css=".lg")
  public WebElement we_logo;

  /**
   * @param driver The driver that will be used to look up the elements
   */
  public LoginPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
