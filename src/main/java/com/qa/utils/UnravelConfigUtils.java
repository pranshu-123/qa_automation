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
        prop.setProperty(ConfigConstants.UnravelConfigConstants.URL, System.getProperty(
            ConfigConstants.SystemConfigConstants.URL));
        prop.setProperty(ConfigConstants.UnravelConfigConstants.USERNAME, System.getProperty(
            ConfigConstants.SystemConfigConstants.USERNAME));
        prop.setProperty(ConfigConstants.UnravelConfigConstants.PASSWORD, System.getProperty(
            ConfigConstants.SystemConfigConstants.PASSWORD));
        prop.setProperty(ConfigConstants.UnravelConfigConstants.BROWSER, System.getProperty(
            ConfigConstants.SystemConfigConstants.BROWSER));
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