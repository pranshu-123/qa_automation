package com.qa.enums.hbase;

public enum HbaseRegionSvrTableColumn {

    REGION_SERVER_NAME("Region Server Name", 0),
    READ_REQUEST_COUNT("Read Request Count", 1),
    WRITE_REQUEST_COUNT("Write Request Count", 2),
    STORE_FILE_SIZE("Store File Size", 3),
    REGION_COUNT("Region Count", 4),
    HEALTH("Health", 5);

    public String value;
    public Integer index;

    HbaseRegionSvrTableColumn(String value, Integer index) {
        this.value = value;
        this.index = index;
    }
}
