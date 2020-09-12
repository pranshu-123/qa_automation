package com.qa.parameters;

import java.io.File;

public class DirectoryConstants {

  public static final String SRC = "src";
  public static final String MAIN = "main";
  public static final String JAVA = "java";
  public static final String COM = "com";
  public static final String QA = "qa";
  public static final String CONFIG = "config";
  

  public static String getUserDir() {
    return System.getProperty("user.dir");
  }

  public static String getConfigDir() {
    return getUserDir() + File.separator + SRC + File.separator + MAIN + File.separator +
        JAVA + File.separator + COM + File.separator + QA + File.separator +
        CONFIG + File.separator;
  }
}
