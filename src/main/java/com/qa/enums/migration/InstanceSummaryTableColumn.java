package com.qa.enums.migration;

public enum InstanceSummaryTableColumn {
    INSTANCE("Instance", 0),
    CORES("Cores", 1),
    MEMORY("Memory", 2),
    HOSTS("Hosts", 3);

    private int index;
    private String name;

    InstanceSummaryTableColumn(String name, int index) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
