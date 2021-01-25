package com.qa.enums.migration;

public enum MigrationCloudMappingModalTable {
    VM_TYPE("VM Type", 0),
    CORES("Cores", 1),
    MEMORY("Memory", 2),
    DISK("Disk", 3),
    COST("Cost", 4),
    CUSTOM_COST("Custom Cost", 5),
    CHECKBOX("", 6);

    private String value;
    private int index;

    MigrationCloudMappingModalTable(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public Integer getIndex() {
        return index;
    }
}
