package com.qa.scripts;

import com.qa.base.BaseClass;
import com.qa.constants.ConfigConstants;
import com.qa.enums.UserAction;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.LoginPageObject;
import com.qa.utils.LoggingUtils;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.RetryExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author Ankur Jaiswal
 * This class contains all action methods related with login class
 */
public class Login {

    private LoginPageObject loginObj;
    private WebDriver driver;
    private WaitExecuter executer;
    private HomePageObject homePageObject;
    private UserActions userActions;
    private static final LoggingUtils LOGGER = new LoggingUtils(Login.class);
    private int MAX_LOGIN = 2;

    /**
     * Constructor initialize the page components
     *
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
     * Login to the application, if user could not log in then it will do retry
     * and if again failed then it will check whether application is already logging in
     * If yes then it will logout and then retry the login again.
     */
    public void loginToApp() {
        if (MAX_LOGIN-- == 0) {
          LOGGER.info("Logout is not working in application. Stopping the execution.", null);
          System.exit(0);
        }
        Properties prop = ConfigReader.readBaseConfig();
        String user = prop.getProperty(ConfigConstants.UnravelConfig.USERNAME);
        String pwd = prop.getProperty(ConfigConstants.UnravelConfig.PASSWORD);
        RetryExecuter<Object> retryExecuter = new RetryExecuter<>();
        try {
            Supplier<Object> method = () -> doLogin(user, pwd);
            retryExecuter.run(method);
        } catch (RuntimeException runtimeException) {
            LOGGER.info("Login to the application failed. \n" + runtimeException.getStackTrace()
                ,null);
            String screenshotImg = ScreenshotHelper.takeScreenshotOfPage(driver);
            LOGGER.info("Screenshot captured: " + screenshotImg, null);
            try {
                if (homePageObject.unravelLogo.isDisplayed()) {
                    LOGGER.info("Login failed because user was already logged in. Doing logout then login"
                      , null);
                    doLogout();
                    loginToApp();
                } else {
                    throw runtimeException;
                }
            } catch (NoSuchElementException noSuchElementException) {
                throw runtimeException;
            }
        }
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
        LOGGER.info("Logout from application", null);
        userActions.performActionWithPolling(loginObj.logoutButton, UserAction.CLICK);
        return true;
    }
}
