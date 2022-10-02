package com.qa.constants;

public class PageConstants {
    public static String YARN_RESOURCE_HEADER = "Resource Type";

    public static class Clusters {
        public static String CHARGEBACK_IMPALA_HEADING = "Chargeback Type";
        public static String CHARGEBACK_YARN_HEADING = "Chargeback Type";
        public static String CHARGEBACK_EMR_HEADING = "Chargeback Type";
    }

    public static class AppTypes {
        public static String HIVE = "hive";
    }

    public static class JobsStatusType {
        public static String[] STATUSTYPE = {"Killed", "Failed", "Running", "Success", "Pending", "Unknown", "Waiting"};
        public static String RUNNING = "running";
    }

    public static class TuningScheduleRun {
        public static String[] SCHEDULE_RUN = {"Daily", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Every 2 Weeks", "Every Month"};
    }

    public static class MRHiveKPIs {
        public static String[] KPIS = {"duration", "data i/o", "start time", "end time"};
    }

    public static class MigrationAndServices {
        public static String[] Google_Dataproc = {"Dataproc 2.0.18", "Dataproc 2.0.17", "Dataproc 2.0.16", "Dataproc 2.0.15"};
        public static String[] Amazon_EMR = {"EMR 6.5.0", "EMR 6.4.0", "EMR 6.3.1", "EMR 6.3.0"};
        public static String[] Azure_HDI = {"HDInsight 4.0", "HDInsight 3.6"};
    }

    /*Workflow name for each application*/
    public static class WorkflowName {
        public static String OOZIE_MR_WF = "Covid_Data_Processing";
        public static String OOZIE_HIVE_WF = "Signup_Subs";
        public static String OOZIE_HIVE2_WF = "oozie_hive2_wf";
        public static String OOZIE_SPARK_WF = "oozie_spark_wf";
        public static String OOZIE_TEZ_WF = "oozie_tez";
        public static String HIVE_TAGGED = "Tagged_Hive_Workflow";
        public static String IMPALA_TAGGED = "Tagged_ImpalaWorkflow";
        public static String SPARK_TAGGED = "Tagged_SparkWorkflow";
        public static String OOZIE_HIVE_SPARK = "oozie_hive_spark";
        public static String TAGGED_HIVE_TEZ = "tagged_hive_tez";

    }

    public static class jobId {
        public static String appIdForDbxjob = "0149c49037160d42:115fbdc200000000";
    }

    public static class AppQueue {
        public static String LLAP = "llap";
    }

    /* Impala event types name */
    public static class EventTypes {
        public static String SqlSlowOperatorEvent = "Slow Operator Analysis";
        public static String ImpalaTimeBreakdownEvent = "Time Breakdown Analysis";
        public static String SQLNonPartitionedTableEvent = "Large non partitioned tables detected";
        public static String SqlUnderestimatedCountOfRowsEvent = "Stale table statistics";
        public static String ImpalaTablesMissingStatsEvent = "Missing Statistics";
        public static String ImpalaNonColumnarTablesEvent = "Inefficient Storage Format";
        public static String SqlTooManyJoinsEvent = "Query contains too many joins";
        public static String SqlNoFilterEvent = "Query is missing filtering conditions";
        public static String ImpalaTimeSkewEvent = "Time skew analysis";
        public static String SqlTooManyPartitionsEvent = "Table has too many partitions";
        public static String ImpalaFailureEvent = "Impala query failure";
        public static String SqlNonPrunedPartitionsEvent = "No partitions were pruned";

    }

    public static class ReportsArchiveNames {
        public static String Tuning = "Tuning";
        public static String TopX = "Top X";

    }

    public static class RunsStatusType {
        public static String[] STATUSTYPE = {"Killed", "Failed", "Running", "Success", "Pending", "Unknown", "Waiting"};
        public static String RUNNING = "running";
    }

    public static class columnsType {
        public static String[] COLUMNSTYPE = {"Status", "User", "Run Name / ID", "Job ID", "Run ID", "Start Time", "Duration", "Read", "Write", "Workspace", "Cluster Name", "Cluster Type", "Cost", "GO TO"};

        public static String RUNNING = "running";
    }

    /*Impala application Id*/
    public static class appId {
        public static String appIdForImpala = "0149c49037160d42:115fbdc200000000";
    }


}
