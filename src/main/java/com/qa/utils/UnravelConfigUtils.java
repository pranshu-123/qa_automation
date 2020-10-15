package com.qa.utils;

import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import com.qa.io.ConfigReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Ankur Jaiswal
 * This is class with contains methods to add, update or delete configs.
 */
public class UnravelConfigUtils {

    /**
     * Update the config based on user input
     */
    public static void updateConfig() {
        Properties prop = ConfigReader.readBaseConfig();
        prop.setProperty(ConfigConstants.UnravelConfig.URL, System.getProperty(
            ConfigConstants.SystemConfig.URL));
        prop.setProperty(ConfigConstants.UnravelConfig.USERNAME, System.getProperty(
            ConfigConstants.SystemConfig.USERNAME));
        prop.setProperty(ConfigConstants.UnravelConfig.PASSWORD, System.getProperty(
            ConfigConstants.SystemConfig.PASSWORD));
        prop.setProperty(ConfigConstants.UnravelConfig.BROWSER, System.getProperty(
            ConfigConstants.SystemConfig.BROWSER));
        try {
            FileOutputStream out = new FileOutputStream(FileConstants.getConfigFile());
            prop.store(out, null);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}