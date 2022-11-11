package com.qa.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitExecuter {
  private WebDriver driver;
  private WebDriverWait wait;
  private final Integer MAX_TIME = 60;

  public WaitExecuter(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver,Duration.ofSeconds(60));
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

  public void waitUntilTextNotToBeInWebElement(WebElement element, String textValue) {
      int timer = 0;
      final int pollInterval = 500;
      while (timer < MAX_TIME*1000) {
          if (element.getText().contains(textValue)) {
             sleep(500);
             timer += pollInterval;
          } else {
              return;
          }
      }
      throw new TimeoutException("Maximum time exceeded.");
  }

    public void waitForSeconds(int strWaitTime) throws InterruptedException {
        Thread.sleep(strWaitTime*1000);
        Log.info("Waited for <<" + strWaitTime + ">> seconds");
    }

  public void waitUntilNumberOfWindowsToBe(int size) {
    wait.until(ExpectedConditions.numberOfWindowsToBe(size));
  }
}

