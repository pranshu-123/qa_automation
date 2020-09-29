package com.qa.utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MouseActions {

    public static void clickOnElement(WebDriver driver, WebElement element) {
        WaitExecuter executer = new WaitExecuter(driver);
        try {
            executer.waitUntilElementPresent(element);
            element.click();
        } catch (Exception clickInterceptedException) {
            JavaScriptExecuter.clickOnElement(driver, element);
        }
        executer.sleep(2000);
    }
}
