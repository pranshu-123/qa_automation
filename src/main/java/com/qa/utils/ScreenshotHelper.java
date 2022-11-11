package com.qa.utils;

import com.qa.constants.DirectoryConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Pranshu
 * This class take the UI screenshot and all related activity with the screenshot
 */
public class ScreenshotHelper {

  /**
   * This method is used to take screenshot of particular element of web page.
   * @param driver - WebDriver instance
   * @param element - WebElement that needs to be included in screenshot.
   * @param scrollY - Y index value till where user has scrolled the page
   * @return - screenshot file that needs to be stored.
   */
  public static File takeScreenshotOfElement(WebDriver driver, WebElement element, int scrollY) {
    // Get entire page screenshot
    File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    BufferedImage fullImg = null;
    try {
      fullImg = ImageIO.read(screenshot);
      // Get the location of element on the page
      Point point = element.getLocation();
      // Get width and height of the element
      int eleWidth = element.getSize().getWidth();
      int eleHeight = element.getSize().getHeight();
      System.out.println(point.getY());
      // Crop the entire page screenshot to get only element screenshot
      BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY() - scrollY,
        eleWidth, eleHeight);
      ImageIO.write(eleScreenshot, "png", screenshot);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return screenshot;
  }

  /**
   * Save the file to the particular location.
   * @param file - file instance
   * @param location - location of path where file needs to be stored
   */
  public static void saveFileToLocation(File file, String location) {
    File screenshotLocation = new File(location);
    try {
      FileUtils.copyFile(file, screenshotLocation);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  /**
   * Take the screenshot of the page and store it to local machine in png format.
   * @param driver - WebDriver instance
   * @return - Return the path of screenshot
   */
  public static String takeScreenshotOfPage(WebDriver driver) {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File screenshot = new File(DirectoryConstants.getScreenshotDir() + System.currentTimeMillis() + ".png");
    try {
      FileUtils.copyFile(scrFile, screenshot);
    } catch (IOException exception) {
      return null;
    }
    return screenshot.getAbsolutePath();
  }
}
