package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ankur Jaiswal
 * This class contains the methods which perform action on elements using
 * javascript.
 */
public class JavaScriptExecuter {

  /**
   * Click on any webelement
   * @param driver - WebDriver instance
   * @param element - WebElement on which we need to click
   */
  public static void clickOnElement(WebDriver driver, WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();", element);
  }

  public static void scrollOnElement(WebDriver driver, WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].scrollIntoView()", element);
  }

  public static String getTextFromElement(WebDriver driver, WebElement element){
    JavascriptExecutor js = (JavascriptExecutor)driver;
    String text = (String) js.executeScript("return arguments[0].text;", element);
    return text;
  }

  public static void addAttribute(WebDriver driver, WebElement element, String attr, String attrVal) {
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].setAttribute('arguments[1]', 'arguments[2]')", element, attr, attrVal);
  }

  public static void scrollViewWithYAxis(WebDriver driver, int scroll) {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    javascriptExecutor.executeScript("window.scrollBy(0," + scroll +")");
  }

}
