package com.qa.io;

import com.qa.constants.FileConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Ankur Jaiswal
 */
public class ConfigReader {

    /**
     * Read yaml file and return the properties
     * @param configFile - File to read
     * @return - Properties
     */
    public static Properties readConfig(String configFile) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(configFile);
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Read the config.properties file
     * @return Properties
     */
    public static Properties readBaseConfig() {
        return readConfig(FileConstants.getConfigFile());
    }

}
