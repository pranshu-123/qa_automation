package com.qa.constants;

public class PageConstants {
    public static String YARN_RESOURCE_HEADER = "Yarn Resource Usage";

    public static class Clusters {
        public static String CHARGEBACK_IMPALA_HEADING = "Chargeback Impala";
        public static String CHARGEBACK_YARN_HEADING = "Chargeback Yarn";
    }

    public static class AppTypes {
        public static String HIVE = "hive";
    }

    public static class JobsStatusType {
        public static String[] STATUSTYPE = {"Killed", "Failed", "Running", "Success", "Pending", "Unknown", "Waiting"};
        public static String RUNNING = "running";
    }

    public static class TuningScheduleRun {
        public static String[] SCHEDULE_RUN = {"Daily","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday","Saturday","Every 2 Weeks","Every Month"};
        public static String[] SCHEDULE_CLUSTERID = {"tnode28-HDP315-TLS-Kerb-Ranger", "tnode3-CDH633-TLS-Kerb-Sentry",
                "tnode40-CDH5162"};
    }
    public static class MRHiveKPIs {
        public static String[] KPIS = {"duration", "data i/o", "start time", "end time"};
    }

    public static class MigrationAndServices{
        public static String[] Google_Dataproc = {"Dataproc 2.0.0-Preview", "Dataproc 1.5.13",
                "Dataproc 1.5.12", "Dataproc 1.5.11"};
        public static String[] Amazon_EMR = {"EMR 6.1.0", "EMR 6.0.0", "EMR 5.31.0", "EMR 5.30.1"};
        public static String[] Azure_HDI = {"HDInsight 4.0", "HDInsight 3.6"};
    }

    public static class AppQueue {
        public static String LLAP = "llap";
    }
}
