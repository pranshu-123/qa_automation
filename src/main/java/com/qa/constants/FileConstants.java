package com.qa.constants;

import java.util.UUID;

public class FileConstants {
  public static final String CONFIG_PROPERTIES = "config.properties";
  public static final String EXTENT_REPORT = "extentReport.html";
  public static final String INFLUXDB_CONFIG_YAML = "influxdb.yml";
  public static final String MARKER_PILLAR_MAPPING = "marker_pillar_mapping.properties";

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
   * This method will return absolute path for influx DB yaml config file
   * @return - path of influx DB yaml config file
   */
  public static String getInfluxConfigYaml() {
    return DirectoryConstants.getResourcesDir() + INFLUXDB_CONFIG_YAML;
  }

  /**
   * This method will return absolute path for marker pillar mapping config file
   * @return - path of marker pillar mapping config file
   */
  public static String getMarkerPillarMappingFile() {
    return DirectoryConstants.getResourcesDir() + MARKER_PILLAR_MAPPING;
  }
}
