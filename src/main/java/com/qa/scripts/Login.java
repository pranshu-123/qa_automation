package com.qa.scripts;

import com.qa.constants.ConfigConstants;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.LoginPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * @author Ankur Jaiswal
 * This class contains all action methods related with login class
 */
public class Login {

  LoginPageObject loginObj;
  WebDriver driver;
  WaitExecuter executer;
  HomePageObject homePageObject;

  /**
   * Constructor initialize the page components
   * @param driver - Instance of WebDriver
   */
  public Login(WebDriver driver) {
    this.driver = driver;
    loginObj = new LoginPageObject(driver);
    homePageObject = new HomePageObject(driver);
    executer = new WaitExecuter(driver);
  }

  /**
   * Login to the application
   */
  public void loginToApp() {
    driver.navigate().refresh();
    Properties prop = ConfigReader.readBaseConfig();
    String user = prop.getProperty(ConfigConstants.UnravelConfig.USERNAME);
    loginObj.loginUserName.sendKeys(user);
    String pwd = prop.getProperty(ConfigConstants.UnravelConfig.PASSWORD);
    loginObj.loginPassword.sendKeys(pwd);
    executer.sleep(2000);
    JavaScriptExecuter.clickOnElement(driver,loginObj.signInButton);

    // Remove below code once login is working fine
    try {
      executer.waitUntilElementPresent(homePageObject.unravelLogo);
    } catch (TimeoutException timeoutException) {
      driver.navigate().refresh();
      loginToApp();
    }
  }

  /**
   * Logout from the application.
   */
  public void logout() {
    executer.waitUntilElementPresent(loginObj.logoutButton);
    JavaScriptExecuter.clickOnElement(driver,loginObj.logoutButton);

    //after logout fix this should be removed
    driver.navigate().refresh();

  }
}
