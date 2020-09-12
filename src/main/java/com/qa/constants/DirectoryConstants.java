package com.qa.constants;

import java.io.File;

public class DirectoryConstants {

  public static final String SRC = "src";
  public static final String MAIN = "main";
  public static final String JAVA = "java";
  public static final String COM = "com";
  public static final String QA = "qa";
  public static final String CONFIG = "config";
  public static final String RESULT = "result";
  public static final String EXTENT = "extent";
  public static final String SCREENSHOTS = "screenshots";

  public static String getUserDir() {
    return System.getProperty("user.dir");
  }

  public static String getConfigDir() {
    return getUserDir() + File.separator + SRC + File.separator + MAIN + File.separator +
        JAVA + File.separator + COM + File.separator + QA + File.separator +
        CONFIG + File.separator;
  }

  public static String getExtentResultDir() {
    return getUserDir() + File.separator + RESULT + File.separator + EXTENT + File.separator;
  }

  public static String getScreenshotDir() {
    return getUserDir() + File.separator + SCREENSHOTS + File.separator;
  }
}
