
package com.qa.enums;

public enum WorkflowEnums {
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
  WORKFLOW_NAME {
    @Override
    public int getIndex() {
      return 3;
    }
  },
  TUNING_SUGGESTIONS {
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
  READ {
    @Override
    public int getIndex() {
      return 7;
    }
  },
  WRITE {
    @Override
    public int getIndex() {
      return 8;
    }
  },
  WORKFLOW_TRENDS {
    @Override
    public int getIndex() {
      return 9;
    }
  },
  CLUSTER_ID {
    @Override
    public int getIndex() {
      return 10;
    }
  };

  public abstract int getIndex();
}
