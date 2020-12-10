package com.qa.enums.appdetails.impala;

/**
 * @author Ankur Jaiswal
 * List of fragment details displayed for impala app details
 */
public enum ImpalaFragmentDetails {
    ID("ID") {
        @Override
        public int getIndex() {
          return 0;
        }
    },
    NUM_INSTANCES("No Of Instances") {
        @Override
        public int getIndex() {
            return 1;
        }
    },
    AVG_TIME("Avg Time") {
        @Override
        public int getIndex() {
            return 2;
        }
    },
    MAX_TIME("Max Time") {
        @Override
        public int getIndex() {
            return 3;
        }
    },
    AVG_PEAK_MEMORY("Avg Peak Memory") {
        @Override
        public int getIndex() {
            return 4;
        }
    },
    MAX_PEAK_MEMORY("Max Peak Memory") {
        @Override
        public int getIndex() {
            return 5;
        }
    },
    ROWS_PRODUCED("Rows Produced") {
        @Override
        public int getIndex() {
            return 6;
        }
    };
    private final String value;
    public abstract int getIndex();
    /**
     * Assigning value displayed on UI to each fragment details\
     */
    ImpalaFragmentDetails(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
