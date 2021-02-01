package com.qa.enums.migration;

public enum MigrationCloudMappingHostDetailsTable {
    HOST("Host", 0),
    HOST_ROLES("Host Roles", 1),
    ACTUAL_USAGE("Actual Usage", 2),
    CAPACITY("Capacity", 3),
    RECOMMENDATION("Recommendation", 4),
    TOTAL_COST("Total Cost($/HOUR)", 5);

    private String value;
    private Integer index;

    MigrationCloudMappingHostDetailsTable(String value, Integer index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getIndex() {
        return this.index;
    }
}
