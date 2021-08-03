package com.qa.enums.chargeback;

public enum GroupByOptions {
    USER("user"),
    REAL_USER("Real User"),
    QUEUE("Queue"),
    DEPT("dept"),
    PROJECT("project"),
    DBS("DB"),
    INPUT_TABLES("inputTables"),
    PRIORITY("priority"),
    REALUSER("RealUser"),
    TEAM("team");

    public String value;
    GroupByOptions(String value) {
        this.value = value;
    }
}
