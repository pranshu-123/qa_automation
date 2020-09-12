package com.qa.scripts;

import org.openqa.selenium.WebDriver;

public class ComponentHelper {

  public static boolean isUrlContain(WebDriver driver, String url) {
    return driver.getCurrentUrl().contains(url);
  }
}
