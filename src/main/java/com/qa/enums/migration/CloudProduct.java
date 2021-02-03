package com.qa.enums.migration;

/**
 * @author Ankur Jaiswal
 */
public enum CloudProduct {
    GOOGLE_COMPUTE_ENGINE("Google Compute Engine (IaaS)"),
    GOOGLE_DATA_PROC("Google DataProc"),
    AMAZON_EC2("Amazon EC2 (IaaS)"),
    AMAZON_EMR("Amazon EMR"),
    AZURE("Azure (IaaS)"),
    AZURE_HD_INSIGHT("Azure HDInsight");

    private String value;
    CloudProduct(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
