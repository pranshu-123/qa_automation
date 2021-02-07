package com.qa.enums.hbase;

public enum HbaseTablesColumn {
    TABLE_NAME("Table Name", 0),
    TABLE_SIZE("Table Size", 1),
    REGION_COUNT("Region Count", 2),
    READ_REQUEST_COUNT("Read Request Count", 3),
    WRITE_REQUEST_COUNT("Write Request Count", 4),
    HEALTH("Health", 5);

    public String value;
    public Integer index;

    HbaseTablesColumn(String value, Integer index) {
        this.value = value;
        this.index = index;
    }
}
