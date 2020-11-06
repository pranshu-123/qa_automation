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
 * @author Ankur Jaiswal
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
   * This method read the image pixel by pixel and check whether any pixel contains the
   * color which user expecting or not. If that is present then it returns true else false.
   * Please note even if expected color is matching at least 90% then it will return true
   * @param screenshot Screenshot image
   * @param rgb - RGB expected value which is expecting user
   * @return boolean value if color present
   */
  public static boolean isContainColor(File screenshot, String rgb) {
    String[] expectedRGB = rgb.split(",");
    Color expectedColor = new Color(Integer.parseInt(expectedRGB[0]),
      Integer.parseInt(expectedRGB[1]), Integer.parseInt(expectedRGB[2]));

    BufferedImage image = null;
    try {
      image = ImageIO.read(screenshot);
    } catch (IOException e) {
      e.printStackTrace();
    }
    int width = image.getWidth();
    int height = image.getHeight();
    for (int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        Color c = new Color(image.getRGB(j, i));
        float colorDiff = ColorHelper.findPercentDifference(c,expectedColor);
        if (colorDiff < 10) {
          return true;
        }
      }
    }
    return false;
  }

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
