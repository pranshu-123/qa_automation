package com.qa.constants;

public class PageConstants {
    public static String YARN_RESOURCE_HEADER = "Yarn Resource Usage";

    public static class Clusters {
        public static String CHARGEBACK_IMPALA_HEADING = "Chargeback Impala";
        public static String CHARGEBACK_YARN_HEADING = "Chargeback Yarn";
    }

    public static class AppTypes {
        public static String HIVE = "hive";
    }

    public static class JobsStatusType {
        public static String[] STATUSTYPE = {"Killed", "Failed", "Running", "Success", "Pending", "Unknown", "Waiting"};
        public static String RUNNING = "running";
    }
}
