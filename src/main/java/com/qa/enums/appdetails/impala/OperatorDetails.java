package com.qa.enums.appdetails.impala;

public enum OperatorDetails {
    TYPE("type") {
        @Override
        public int getIndex() {
            return 0;
        }
    },
    OP_ID("Op ID") {
        @Override
        public int getIndex() {
            return 1;
        }
    },
    FRAG_ID("Frag ID") {
        @Override
        public int getIndex() {
            return 2;
        }
    },
    NUM_INSTANCES("No Of Instances") {
        @Override
        public int getIndex() {
            return 3;
        }
    },
    AVG_TIME("Avg Time") {
        @Override
        public int getIndex() {
            return 4;
        }
    },
    MAX_TIME("Max Time") {
        @Override
        public int getIndex() {
            return 5;
        }
    },
    AVG_PEAK_MEMORY("Avg Peak Memory") {
        @Override
        public int getIndex() {
            return 6;
        }
    },
    MAX_PEAK_MEMORY("Max Peak Memory") {
        @Override
        public int getIndex() {
            return 7;
        }
    },
    ROWS_PRODUCED("Rows Produced") {
        @Override
        public int getIndex() {
            return 8;
        }
    };
    private final String value;
    public abstract int getIndex();

    /**
     * Assigning value displayed on UI to each operator details\
     */
    OperatorDetails(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
