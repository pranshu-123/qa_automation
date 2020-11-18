package com.qa.enums;

public enum AllAppsEnum {
    TYPE {
        @Override
        public int getIndex() {
            return 0;
        }
    },
    STATUS {
        @Override
        public int getIndex() {
            return 1;
        }
    },
    USER {
        @Override
        public int getIndex() {
            return 2;
        }
    },
    APP_NAME_ID {
        @Override
        public int getIndex() {
            return 3;
        }
    },
    CLUSTER_ID {
        @Override
        public int getIndex() {
            return 4;
        }
    },
    START_TIME {
        @Override
        public int getIndex() {
            return 5;
        }
    },
    DURATION {
        @Override
        public int getIndex() {
            return 6;
        }
    },
    QUEUE {
        @Override
        public int getIndex() {
            return 7;
        }
    },
    READ {
        @Override
        public int getIndex() {
            return 8;
        }
    },
    WRITE {
        @Override
        public int getIndex() {
            return 9;
        }
    },
    INSIGHTS {
        @Override
        public int getIndex() {
            return 10;
        }
    };
    public abstract int getIndex();
}