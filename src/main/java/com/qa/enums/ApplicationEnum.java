package com.qa.enums;

public enum ApplicationEnum {
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
  START_TIME {
    @Override
    public int getIndex() {
      return 4;
    }
  },
  DURATION {
    @Override
    public int getIndex() {
      return 5;
    }
  },
  READ {
    @Override
    public int getIndex() {
      return 6;
    }
  },
  WRITE {
    @Override
    public int getIndex() {
      return 7;
    }
  },
  CLUSTER_ID {
    @Override
    public int getIndex() {
      return 8;
    }
  },
  QUEUE {
    @Override
    public int getIndex() {
      return 9;
    }
  },
  GO_TO {
    @Override
    public int getIndex() {
      return 10;
    }
  };
  public abstract int getIndex();
}