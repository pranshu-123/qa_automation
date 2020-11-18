package com.qa.utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 * This class contains all methods related to mouse actions
 */
public class MouseActions {
    private static LoggingUtils logger = new LoggingUtils(MouseActions.class);
    /**
     * Click on the element, First it will use selenium to click if
     * we get click intercepted then click using javascript executer
     * @param driver - WebDriver instance
     * @param element - WebElement which is to be clicked
     */
    public static void clickOnElement(WebDriver driver, WebElement element) {
        logger.info("Click on the element: " + element.getText(), null);
        WaitExecuter executer = new WaitExecuter(driver);
        try {
            executer.waitUntilElementPresent(element);
            element.click();
        } catch (ElementClickInterceptedException clickInterceptedException) {
            JavaScriptExecuter.clickOnElement(driver, element);
        }
        executer.sleep(2000);
    }
}
