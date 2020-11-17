package com.qa.scripts;

import com.qa.constants.ConfigConstants;
import com.qa.enums.UserAction;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.LoginPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.RetryExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author Ankur Jaiswal
 * This class contains all action methods related with login class
 */
public class Login {

  LoginPageObject loginObj;
  WebDriver driver;
  WaitExecuter executer;
  HomePageObject homePageObject;
  private UserActions userActions;
  /**
   * Constructor initialize the page components
   * @param driver - Instance of WebDriver
   */
  public Login(WebDriver driver) {
    this.driver = driver;
    loginObj = new LoginPageObject(driver);
    homePageObject = new HomePageObject(driver);
    executer = new WaitExecuter(driver);
    userActions = new UserActions(driver);
  }

  /**
   * Login to the application
   */
  public void loginToApp() {
    Properties prop = ConfigReader.readBaseConfig();
    String user = prop.getProperty(ConfigConstants.UnravelConfig.USERNAME);
    String pwd = prop.getProperty(ConfigConstants.UnravelConfig.PASSWORD);
    RetryExecuter<Object> retryExecuter = new RetryExecuter<>();
    Supplier<Object> method = () -> doLogin(user, pwd);
    retryExecuter.run(method);
  }

  public Boolean doLogin(String user, String pwd) {
    userActions.performActionWithPolling(loginObj.loginUserName, UserAction.SEND_KEYS, user);
    userActions.performActionWithPolling(loginObj.loginPassword, UserAction.SEND_KEYS, pwd);
    executer.sleep(2000);
    userActions.performActionWithPolling(loginObj.signInButton, UserAction.CLICK);
    return true;
  }
  /**
   * Logout from the application.
   */
  public void logout() {
    RetryExecuter<Object> retryExecuter = new RetryExecuter<>();
    Supplier<Object> method = () -> doLogout();
    retryExecuter.run(method);
  }

  public Boolean doLogout() {
    userActions.performActionWithPolling(loginObj.logoutButton, UserAction.CLICK);
    return true;
  }
}
