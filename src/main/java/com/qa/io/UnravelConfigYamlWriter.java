package com.qa.io;

import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import com.qa.utils.LoggingUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Ankur Jaiswal
 * writer class for unravel yaml config
 */
public class UnravelConfigYamlWriter implements YamlWriter {
    private final static LoggingUtils LOGGER = new LoggingUtils(UnravelConfigYamlWriter.class);

    /**
     * Updating cluster details in unravel
     * yaml configuration file
     */
    public void updateClusterDetails() {
        try {
            LOGGER.info("Reading unravel yaml config file", null);
            UnravelConfigYamlReader reader = new UnravelConfigYamlReader();
            Map<String, Object> configDetails = reader.readYamlFile(FileConstants.getUnravelConfigYaml());
            Map<String, Object> unravelNode =
                    (Map<String,Object>) configDetails.get(ConfigConstants.UnravelYamlConfig.UNRAVEL);
            if (System.getProperty(ConfigConstants.UnravelYamlConfig.CLUSTERS) != null
                    && !System.getProperty(ConfigConstants.UnravelYamlConfig.CLUSTERS).trim().equals("")) {
                unravelNode.put(ConfigConstants.UnravelYamlConfig.CLUSTERS,
                        System.getProperty(ConfigConstants.UnravelYamlConfig.CLUSTERS));
            }
            if (System.getProperty(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS) != null
                    && !System.getProperty(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS).trim().equals("")) {
                unravelNode.put(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS, System.getProperty(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS));
            }
            if (System.getProperty(ConfigConstants.UnravelYamlConfig.HBASE_CLUSTERS) != null
                    && !System.getProperty(ConfigConstants.UnravelYamlConfig.HBASE_CLUSTERS).trim().equals("")) {
                unravelNode.put(ConfigConstants.UnravelYamlConfig.HBASE_CLUSTERS,
                        System.getProperty(ConfigConstants.UnravelYamlConfig.HBASE_CLUSTERS));
            }
            if (System.getProperty(ConfigConstants.UnravelYamlConfig.MAPREDUCE_CLUSTERS) != null &&
                    !System.getProperty(ConfigConstants.UnravelYamlConfig.MAPREDUCE_CLUSTERS).trim().contains("")) {
                unravelNode.put(ConfigConstants.UnravelYamlConfig.MAPREDUCE_CLUSTERS,
                        System.getProperty(ConfigConstants.UnravelYamlConfig.MAPREDUCE_CLUSTERS));
            }
            LOGGER.info("Updated cluster details in unravel yaml config file", null);
            writeYamlFile(FileConstants.getUnravelConfigYaml(), configDetails);
        } catch (FileNotFoundException fileNotFoundEx) {
            LOGGER.info("Exception Raised while updating cluster details", null);
            fileNotFoundEx.printStackTrace();
        }
    }

    @Override
    public void writeYamlFile(String yamlFile, Map<String, Object> data) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(yamlFile));
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        yaml.dump(data, writer);
    }
}
