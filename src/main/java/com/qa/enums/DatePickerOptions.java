package com.qa.enums;

public enum DatePickerOptions {
    CURRENT_DAY("Current Day"),
    LAST_7_DAYS("Last 7 Days"),
    LAST_30_DAYS("Last 30 Days"),
    LAST_60_DAYS("Last 60 Days"),
    LAST_90_DAYS("Last 90 Days"),
    CUSTOM_RANGE("Custom Range");

    public String value;
    DatePickerOptions(String value) {
        this.value = value;
    }
}
