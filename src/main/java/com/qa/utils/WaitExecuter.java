package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitExecuter {
  private WebDriver driver;
  private WebDriverWait wait;

  public WaitExecuter(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver,60);
  }

  public void sleep(int milisecs) {
    try {
      Thread.sleep(milisecs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public void waitUntilElementPresent(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void waitUntilElementClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitUntilUrlContains(String urlFraction) {
    wait.until(ExpectedConditions.urlContains(urlFraction));
  }

  public void waitUntilPageFullyLoaded() {
    wait.until(
      webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
  }

  public void waitUntilTextToBeInWebElement(WebElement element, String textValue) {
    wait.until(ExpectedConditions.textToBePresentInElement(element, textValue));
  }

  public void waitUntilNumberOfWindowsToBe(int size) {
    wait.until(ExpectedConditions.numberOfWindowsToBe(size));
  }
}

