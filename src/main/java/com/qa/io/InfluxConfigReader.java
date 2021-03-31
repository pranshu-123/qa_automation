package com.qa.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class InfluxConfigReader implements YamlReader {
    private Yaml yaml;
    private Map<String, Object> config;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public InfluxConfigReader() {
        yaml = new Yaml();
    }

    @Override
    public Map<String, Object> readYamlFile(String file) throws FileNotFoundException {
        try {
            InputStream inputStream = new FileInputStream(new File(file));
            config = yaml.load(inputStream);
        } catch (FileNotFoundException exception) {
            logger.error("Config file not found: " + exception.getMessage());
        }
        return config;
    }
}
