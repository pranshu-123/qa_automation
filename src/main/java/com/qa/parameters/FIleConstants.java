package com.qa.parameters;

public class FIleConstants {
  public static final String CONFIG_PROPERTIES = "config.properties";
  public static String DOWNLOAD_CSV_FOLDER = "";

  public static String getConfigFile() {
    return DirectoryConstants.getConfigDir() + CONFIG_PROPERTIES;
  }

  public static void setDownloadFolderUUID(String folderUUID){
    DOWNLOAD_CSV_FOLDER=folderUUID;
  }
  public static String getDownloadCsvFolder(){
    return DOWNLOAD_CSV_FOLDER;
  }

}
