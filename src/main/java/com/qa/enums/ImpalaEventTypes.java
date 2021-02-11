package com.qa.enums;

/**
 * @author Ojasvi Pandey
 * Contains all application events present in inefficient apps details page
 */

public enum ImpalaEventTypes {
    TimeBreakdownEvent("ImpalaTimeBreakdownEvent"),
    SQLNonPartitionedTableEvent("SQLNonPartitionedTableEvent"),
    SqlSlowOperatorEvent("SqlSlowOperatorEvent"),
    SqlUnderestimatedCountOfRowsEvent("SqlUnderestimatedCountOfRowsEvent"),
    ImpalaTablesMissingStatsEvent("ImpalaTablesMissingStatsEvent"),
    ImpalaNonColumnarTablesEvent("ImpalaNonColumnarTablesEvent"),
    SqlTooManyJoinsEvent("SqlTooManyJoinsEvent"),
    SqlNoFilterEvent("SqlNoFilterEvent"),
    ImpalaTimeSkewEvent("ImpalaTimeSkewEvent"),
    SqlTooManyPartitionsEvent("SqlTooManyPartitionsEvent"),
    ImpalaFailureEvent("ImpalaFailureEvent"),
    SqlNonPrunedPartitionsEvent("SqlNonPrunedPartitionsEvent");


    private final String value;

    /**
     * Assigning value displayed on UI to each app type
     */
    ImpalaEventTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
