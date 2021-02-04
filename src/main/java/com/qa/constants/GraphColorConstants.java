package com.qa.constants;

/**
 * @author Ankur Jaiswal
 * This class contains all constants related with graph color
 */
public class GraphColorConstants {

  /**
   * This class contains all constants related with memory graph color
   * present at overview page.
   */
  public static class MemoryGraph {
    public static final String TOTAL_AVAILABLE_COLOR = "250,108,53";
    public static final String TOTAL_ALLOCATED_COLOR = "28,204,235";
    public static final String FIRST_NODE_COLOR = "28,204,235";
  }

  public static class NodesGraph {
    public static final String ACTIVE_COLOR = "135,178,0";
    public static final String BAD_COLOR = "250,108,53";
  }

  public static class RunningGraph {
    public static final String RUNNING_COLOR = "198,242,249";
    public static final String ACCEPTED_COLOR = "202,246,248";
  }

  public static class VCoresGraph {
    public static final String TotalAvailable_COLOR = "250,108,53";
    public static final String TotalAllocated_COLOR = "28,204,235";
  }

  public static class JobsGraph {
    public static final String NEW_COLOR = "44,199,23";
    public static final String RUNNING_COLOR = "187,70,139";
    public static final String ACCEPTED_COLOR = "29,186,175";
  }

  public static class ByStatusGraph {
    public static final String Success_COLOR = "44,199,23";
    public static final String Killed_COLOR = "213,68,81";
    public static final String Failed_COLOR = "249,141,6";
  }
  /**
   * This class contains all constants related with inefficient event graph color
   * present at overview page.
   */
  public static class InefficientEventGraph {
    public static final String MAP_REDUCE_COLOR = "29,186,175";
    public static final String HIVE_COLOR = "135,178,0";
    public static final String SPARK_COLOR = "187,70,139";
  }

  public static class ImpalaQueriesGraph {
    public static final String FIRST_USER_COLOR = "29,186,175";
  }

  public static class YarnResourcesGraph{
    public static final String TOTAL_AVAILABLE_COLOR = "250,108,53";
    public static final String TOTAL_ALLOCATED_COLOR = "28,204,235";
    public static final String YARN_SERVICE_COLOR= "29,186,175";
    public static final String SPARK_COLOR= "187,70,139";
    public static final String TEZ_COLOR= "28,204,235";
    public static final String MAPREDUCE_COLOR= "83,62,120";
  }

}
