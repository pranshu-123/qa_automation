package com.qa.enums.migration;

/**
 * @author Ankur Jaiswal
 * Cloud Storage types displayed at migration cloud mapping per host page
 */
public enum CloudStorageType {

    OBJECT_STORAGE("Object storage"),
    LOCAL_ATTACHED_STORAGE("Local attached storage");

    private final String value;
    CloudStorageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
