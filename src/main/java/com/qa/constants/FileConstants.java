package com.qa.constants;

import java.util.UUID;

public class FileConstants {
  public static final String CONFIG_PROPERTIES = "config.properties";
  public static final String EXTENT_REPORT = "extentReport.html";
  public static final String UNRAVEL_CONFIG_YAML = "unravel_config.yml";
  public static final String INFLUXDB_CONFIG_YAML = "influxdb.yml";

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

  /**
   * This method will return absolute path for unravel yaml config
   * @return - path of unravel yaml config
   */
  public static String getUnravelConfigYaml() {
    return DirectoryConstants.getResourcesDir() + UNRAVEL_CONFIG_YAML;
  }

  public static String getInfluxConfigYaml() {
    return DirectoryConstants.getResourcesDir() + INFLUXDB_CONFIG_YAML;
  }
}
