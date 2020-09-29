package com.qa.io;

import com.qa.annotations.Marker;
import com.qa.constants.FileConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
  public static Properties readBaseConfig() {
    FileInputStream fis = null;
    Properties prop = new Properties();
    try {
      fis = new FileInputStream(
        FileConstants.getConfigFile());
      prop.load(fis);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }
}
