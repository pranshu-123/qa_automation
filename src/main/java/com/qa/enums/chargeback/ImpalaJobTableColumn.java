package com.qa.enums.chargeback;

/**
 * @author Ankur Jaiswal
 * Column Name displayed on impala tables on impala chargeback page
 */
public enum ImpalaJobTableColumn {
    TYPE("type", 0),
    STATE("State", 1),
    USER("User", 2),
    REAL_USER("Real User", 3),
    APP_NAME_ID("App Name / ID", 4),
    START_TIME("Start Time", 5),
    FINISHED_TIME("Finished Time", 6),
    QUEUE("Queue", 7),
    MEMORY_MB_SECONDS("Memory MB Seconds", 8),
    TOTAL_PROCESSING_TIME_SECONDS("Total Processing Time Seconds", 9);

    public String value;
    public Integer index;

    ImpalaJobTableColumn(String value, Integer index) {
        this.value = value;
        this.index = index;
    }
}
