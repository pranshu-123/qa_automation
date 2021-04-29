package com.qa.enums.chargeback;

public enum GroupByOptions {
    USER("user"),
    REAL_USER("Real User"),
    QUEUE("queue"),
    DEPT("dept"),
    PROJECT("project"),
    DBS("DB"),
    INPUT_TABLES("input_tables"),
    PRIORITY("priority"),
    REALUSER("RealUser"),
    TEAM("team");

    public String value;
    GroupByOptions(String value) {
        this.value = value;
    }
}
