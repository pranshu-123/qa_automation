package com.qa.constants;

import java.util.UUID;

public class FileConstants {
  public static final String CONFIG_PROPERTIES = "config.properties";
  public static final String EXTENT_REPORT = "extentReport.html";

  public static String getConfigFile() {
    return DirectoryConstants.getConfigDir() + CONFIG_PROPERTIES;
  }
  public static String getExtentReportFile() {
    return DirectoryConstants.getExtentResultDir() + EXTENT_REPORT;
  }

  public static String getScreenshotFileName() {
    final String uuid = UUID.randomUUID().toString().replace("-", "");
    return "ss-" + uuid + ".png";
  }
}
