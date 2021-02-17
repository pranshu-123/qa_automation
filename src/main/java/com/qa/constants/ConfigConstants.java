package com.qa.constants;

/**
 * @author Ankur Jaiswal
 * This class contains constants related with configuration files
 */
public class ConfigConstants {

    /**
     * This class contains constants related with unravel configuration files
     */
    public static class UnravelConfig {
        public static final String URL = "url";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String BROWSER = "browser";
    }

    /**
     * This class contains constants related with system configuration
     */
    public static class SystemConfig {
        public static final String URL = "url";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String IS_MULTI_CLUSTER = "isMultiCluster";
        public static final String BROWSER = "browser";
        public static final String HEADLESS = "headless";
        public static final String MARKERS = "markers";
    }

    /**
     * This class contains constants related with reports
     */
    public static class ReportConfig {
        public static final String SELENIUM_VERSION = "SeleniumVersion";
    }

    /**
     * This class contains constants related with unravel yaml config
     */
    public static class UnravelYamlConfig {
        public static final String UNRAVEL = "unravel";
        public static final String CLUSTERS = "clusters";
        public static final String IMPALA_CLUSTERS = "impala_clusters";
        public static final String AWS = "aws";
        public static final String BUCKET_NAME = "bucket_name";
        public static final String HBASE_CLUSTERS = "hbase_clusters";
    }
}