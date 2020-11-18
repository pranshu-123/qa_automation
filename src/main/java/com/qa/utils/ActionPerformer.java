package com.qa.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;

public class ActionPerformer {
  private Actions actions;
  private WebDriver driver;

  public ActionPerformer(WebDriver driver) {
    this.driver = driver;
    actions = new Actions(driver);
  }

  public void moveToTheElementByOffset(WebElement element,
    int xOffset, int yOffset) {
    actions.moveToElement(element, xOffset, yOffset).build().perform();
  }

  //bk
  public void moveToTheElementByOffsetAndClick(WebElement element,
                                       int xOffset, int yOffset) {
    actions.moveToElement(element, xOffset, yOffset).click().perform();
  }

  public static void hoverToPosition(WebDriver driver, int xOffset, int yOffset) {
    Actions actions = new Actions(driver);
    actions.moveByOffset(xOffset, yOffset).perform();
  }

  public static void resetMouseOffset(WebDriver driver) {
    Actions actions = new Actions(driver);
    actions.moveByOffset(0, 0).perform();
  }

  public static void hoverToElementOnPosition(WebDriver driver, WebElement element, int xOffset, int yOffset) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element,xOffset, yOffset).build().perform();
  }

  public static void pressKeyBoardButton(WebDriver driver, Keys key) {
    Actions actions = new Actions(driver);
    actions.sendKeys(key);
  }

  /**
   * Move the mouse to specific element
   * @param element - Web Element
   */
  public void moveToTheElement(WebElement element) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element).perform();
  }
}