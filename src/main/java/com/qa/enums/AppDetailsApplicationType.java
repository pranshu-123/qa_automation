package com.qa.enums;

/**
 * @author Ankur Jaiswal
 * Contains all application type present in app details page
 */
public enum AppDetailsApplicationType {
    HIVE("Hive"),
    IMPALA("Impala"),
    MAPREDUCE("Map Reduce"),
    SPARK("Spark"),
    TEZ("Tez");

    private final String value;
    /**
     * Assigning value displayed on UI to each app type
     */
    AppDetailsApplicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
