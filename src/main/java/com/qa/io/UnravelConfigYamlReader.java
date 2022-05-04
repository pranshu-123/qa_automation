package com.qa.io;

import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 * Read the unravel yaml config, this class contains methods which
 * read the various components like clusters etc. of the unravel config.
 */
public class UnravelConfigYamlReader implements YamlReader {
    private Yaml yaml;
    private Map<String, Object> config;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public UnravelConfigYamlReader() {
        yaml = new Yaml();
    }

    /**
     * Read the yaml config and return the map of k,v pair
     * of the config
     * @param file - Absolute path of the config
     * @return Map of whole structure of config
     */
    @Override
    public Map<String, Object> readYamlFile(String file) {
        try {
            InputStream inputStream = new FileInputStream(new File(file));
            config = yaml.load(inputStream);
        } catch (FileNotFoundException exception) {
            logger.error("Config file not found: " + exception.getMessage());
        }
        return config;
    }

    /**
     * Return the list of clusters from yaml file
     * @param isImpala - isImpala is true then return the impala clusters
     * @return Iterator<Object[]> Iterator of clusters array
     */
    public Iterator<Object[]> getClusterList(Boolean isImpala) {
       Map<String, Object> unravelConfig = readYamlFile(FileConstants.getUnravelConfigYaml());
        Map<String, Object> unravelNode =
            (Map<String,Object>)unravelConfig.get(ConfigConstants.UnravelYamlConfig.UNRAVEL);
        String clusters = "";
        if (isImpala) {
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS);
        } else {
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.CLUSTERS);
        }
        return Arrays.stream(clusters.split(",")).map(cluster -> new Object[]{cluster.trim()})
            .collect(Collectors.toList()).iterator();
    }

    /**
     * Return the list of clusters from yaml file
     * @param appName - appName then return the appName clusters
     * @return Iterator<Object[]> Iterator of clusters array
     */
    public Iterator<Object[]> getClusterList(String appName) {
        Map<String, Object> unravelConfig = readYamlFile(FileConstants.getUnravelConfigYaml());
        Map<String, Object> unravelNode =
                (Map<String,Object>)unravelConfig.get(ConfigConstants.UnravelYamlConfig.UNRAVEL);
        String clusters = "";
        if (appName.contains("impala")) {
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.IMPALA_CLUSTERS);
        } else if (appName.contains("hbase")){
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.HBASE_CLUSTERS);
        }else if (appName.contains("mapreduce")){
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.MAPREDUCE_CLUSTERS);
        }else if (appName.contains("overview")){
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.WORKSPACE_CLUSTERS);
        } else {
            clusters = (String) unravelNode.get(ConfigConstants.UnravelYamlConfig.CLUSTERS);
        }
        return Arrays.stream(clusters.split(",")).map(cluster -> new Object[]{cluster.trim()})
                .collect(Collectors.toList()).iterator();
    }

    /**
     * Return AWS details from unravel_config.yml in k,v pair
     */
    public Map<String,Object> getAWSDetails() {
        Map<String, Object> unravelConfig = readYamlFile(FileConstants.getUnravelConfigYaml());
        Map<String, Object> awsDetails =
            (Map<String,Object>)unravelConfig.get(ConfigConstants.UnravelYamlConfig.AWS);
        return awsDetails;
    }
}
