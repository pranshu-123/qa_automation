package com.qa.constants;

import com.qa.annotations.Marker;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ankur Jaiswal
 * This class contains all constants related with markers
 */
public class MarkerConstants {
    public static final String SMOKE = "smoke";
    public static final String TOPX = "topx";
    public static final String REGRESSION = "regression";
    public static final String ALL = "all";
    public static final String ALERTS = "alerts";
    public static final String CLUSTER_OVERVIEW = "cluster_overview";
    public static final String IMPALA_RESOURCES = "impala_resources";
    public static final String IMPALA_CHARGEBACK = "impala_chargeback";
    public static final String ALL_APPS = "all_apps";
    public static final String QUEUE_ANALYSIS = "queue_analysis";
    public static final String APP_DETAILS_IMPALA = "app_details_impala";
    public static final String APP_DETAILS_SPARK = "app_details_spark";
    public static final String APP_DETAILS_TEZ = "app_details_tez";
    public static final String APP_DETAILS_TEZLLAP = "app_details_tezllap";
    public static final String APP_DETAILS_MR = "app_details_mr";
    public static final String INEFFICIENT_APPS = "inefficient_apps";
    public static final String YARN_CHARGEBACK = "yarn_chargeback";
    public static final String YARN_RESOURCES = "yarn_resources";
    public static final String CLUSTER_JOBS = "cluster_jobs";
    public static final String CLUSTER_USER_REPORTS = "cluster_user_report";
    public static final String CLUSTER_WORKLOAD = "cluster_workload";
    public static final String CLUSTER_HBASE = "cluster_hbase";
    public static final String MANAGE = "manage";
    public static final String REPORT_ARCHIEVE = "report_archieve";
    public static final String REPORTS_SCHEDULED = "reports_scheduled";
    public static final String APP_DETAILS_HIVE = "app_details_hive";
    public static final String DATA_FORECASTING = "data_forecasting";
    public static final String DATA_SMALLFILES = "data_smallfiles";
    public static final String DATA_FILEREPORTS = "data_filereports";
    public static final String CLUSTER_TUNING = "cluster_tuning";
    public static final String KAFKA_EXTERNAL = "kafka_external";
    public static final String CLUSTER_ELK = "cluster_elk";
    public static final String MIGRATION_SERVICESANDVERSIONS = "migration_services";
    public static final String MIGRATION_CLUSTER_DISCOVERY = "cluster_discovery";
    public static final String ONLY = "only";
    public static final String WORKLOAD_FIT = "workload_fit";
    public static final String CLOUD_MAPPING_PER_HOST = "cloud_mapping_per_host";
    public static final String JOBS_WORKFLOW = "jobs_workflow";

    public static final String JOBS_SESSIONS = "jobs_sessions";


    public static final String DATA_OVERVIEW = "data_overview";

    public static final String DATA_TABLES = "data_tables";
    public static final String IMPALA_INSIGHTS = "impala_insights";
    public static final String DBX_RUNS = "dbx_runs";
    public static final String DBX_JOBS = "dbx_jobs";
    public static final String DBX_COST_TRENDS = "dbx_cost_trends";
    public static final String DBX_COST_BUDGET = "dbx_cost_budget";
    public static final String DBX_REPORTS_TOPX = "dbx_reports_topX";
    public static final String DBX_REPORTS_ARCHIVED = "dbx_reports_archived";
    public static final String DBX_REPORTS_SCHEDULED = "dbx_reports_scheduled";
    public static final String DBX_COST_CHARGEBACK = "dbx_cost_chargeback";
    public static final String DBX_APP_DETAILS = "dbx_app_details";

    public static final String DBX_INSIGHTS_OVERVIEW = "dbx_insights_overview";
    public static final String DBX_MANAGE = "dbx_manage";
    public static final String DBX_COMPUTE = "dbx_compute";
    public static final String DBX_AUTOACTION = "dbx_autoaction";
    public static final String DBX_APITOKEN = "DbxApiToken";
    public static final String EMR_CLUSTER_INSIGHTS = "emrclusterinsights";
    public static final String EMR_CLUSTER_JOBTRENDS = "emrclusterjobtrends";
    public static final String EMR_INEFFICIENT_APPS = "emr_Inefficient_apps";
    public static final String EMR_CLUSTER_CHARGEBACK = "emrClusterChargeback";
    public static final String EMR_COST_CHARGEBACK = "emr_cost_chargeback";

    // EMR Markers
    public static final String EMR_SPARK = "Emr_Spark";

    public static final String EMR_HIVE = "Emr_hive";

    public static final String EMR_TEZ = "Emr_tez";

    public static final String EMR_MAPREDUCE = "Emr_mapreduce";

    public static final String EMR_ALL_APPLICATION = "emr_all_apps";

    public static final String EMR_DATA_OVERVIEW = "emr_data_overview";

    public static final String EMR_REPORTS_TOPX = "emr_reports_topX";

    public static final String EMR_MANAGE = "emr_manage";

    public static final String EMR_REPORTS_ARCHIVED = "emr_reports_archived";

    public static final String EMR_REPORTS_SCHEDULED = "emr_reports_scheduled";

    public static final String DBX_DATA_OVERVIEW = "dbx_data_overview";
    public static final String DBX_DATA_TABLES = "dbx_data_tables";

    // GCP Markers

    public static final String GCP_CLUSTER_OVERVIEW = "gcp_cluster_overview";

    public static final String GCP_ALL_APPS = "gcp_all_apps";

    public static final String GCP_APP_DETAILS_SPARK = "gcp_app_details_spark";

    public static final String GCP_APP_DETAILS_TEZ = "gcp_app_details_tez";

    public static final String GCP_APP_DETAILS_MR = "gcp_app_details_mr";

    public static final String GCP_INEFFICIENT_APPS = "gcp_inefficient_apps";

    public static final String GCP_YARN_CHARGEBACK = "gcp_yarn_chargeback";

    public static final String GCP_YARN_RESOURCES = "gcp_yarn_resources";


    public static final String GCP_CLUSTER_WORKLOAD = "gcp_cluster_workload";

    public static final String GCP_MANAGE = "gcp_manage";

    public static final String GCP_APP_DETAILS_HIVE = "gcp_app_details_hive";

    public static final String GCP_CLUSTER_JOBS = "gcp_cluster_jobs";

    public static final String GCP_AUTO_ACTIONS = "gcp_auto_actions";

    public static final String GCP_DATA_OVERVIEW = "gcp_data_overview";

    public static final String GCP_DATA_TABLES = "gcp_data_tables";
    public static final Map<String, Class<? extends Annotation>> MARKER_MAPPING = initMap();

    /**
     * This method will generate the reference of Marker Interface with
     * constant value which will be used on run time.
     *
     * @return Immutable mapping of markers with constants
     */
    private static Map<String, Class<? extends Annotation>> initMap() {
        Map<String, Class<? extends Annotation>> map = new LinkedHashMap<>();
        map.put(SMOKE, Marker.Smoke.class);
        map.put(CLUSTER_OVERVIEW, Marker.ClusterOverview.class);
        map.put(TOPX, Marker.TopX.class);
        map.put(REGRESSION, Marker.Regression.class);
        map.put(ALL, Marker.All.class);
        map.put(ALERTS, Marker.Alerts.class);
        map.put(IMPALA_RESOURCES, Marker.ImpalaResources.class);
        map.put(IMPALA_CHARGEBACK, Marker.ImpalaChargeback.class);
        map.put(ALL_APPS, Marker.AllApps.class);
        map.put(QUEUE_ANALYSIS, Marker.QueueAnalysis.class);
        map.put(APP_DETAILS_IMPALA, Marker.AppDetailsImpala.class);
        map.put(APP_DETAILS_SPARK, Marker.AppDetailsSpark.class);
        map.put(APP_DETAILS_TEZ, Marker.AppDetailsTez.class);
        map.put(APP_DETAILS_TEZLLAP, Marker.AppDetailsTezLlap.class);
        map.put(APP_DETAILS_MR, Marker.AppDetailsMr.class);
        map.put(INEFFICIENT_APPS, Marker.InefficientApps.class);
        map.put(YARN_CHARGEBACK, Marker.YarnChargeback.class);
        map.put(YARN_RESOURCES, Marker.YarnResources.class);
        map.put(CLUSTER_JOBS, Marker.ClusterJobs.class);
        map.put(CLUSTER_USER_REPORTS, Marker.UserReports.class);
        map.put(CLUSTER_WORKLOAD, Marker.ClusterWorkload.class);
        map.put(CLUSTER_HBASE, Marker.ClusterHBase.class);
        map.put(MANAGE, Marker.Manage.class);
        map.put(REPORT_ARCHIEVE, Marker.ReportArchive.class);
        map.put(REPORTS_SCHEDULED, Marker.ReportsScheduled.class);
        map.put(APP_DETAILS_HIVE, Marker.AppDetailsHive.class);
        map.put(DATA_FORECASTING, Marker.DataForecasting.class);
        map.put(DATA_SMALLFILES, Marker.DataSmallFiles.class);
        map.put(DATA_FILEREPORTS, Marker.DataFileReports.class);
        map.put(CLUSTER_TUNING, Marker.Tuning.class);
        map.put(KAFKA_EXTERNAL, Marker.KafkaExternal.class);
        map.put(CLUSTER_ELK, Marker.ClusterELK.class);
        map.put(MIGRATION_SERVICESANDVERSIONS, Marker.MigrationServices.class);
        map.put(MIGRATION_CLUSTER_DISCOVERY, Marker.MigrationClusterDiscovery.class);
        map.put(ONLY, Marker.Only.class);
        map.put(WORKLOAD_FIT, Marker.WorkloadFit.class);
        map.put(CLOUD_MAPPING_PER_HOST, Marker.CloudMappingPerHost.class);
        map.put(JOBS_WORKFLOW, Marker.JobsWorkflow.class);
        map.put(JOBS_SESSIONS, Marker.JobsSessions.class);
        map.put(DATA_OVERVIEW, Marker.DataOverview.class);
        map.put(DATA_TABLES, Marker.DataTables.class);
        map.put(IMPALA_INSIGHTS, Marker.ImpalaInsights.class);
        map.put(DBX_COST_CHARGEBACK, Marker.DbxCostChargeback.class);
        map.put(DBX_REPORTS_TOPX, Marker.DbxReportsTopX.class);
        map.put(DBX_REPORTS_ARCHIVED, Marker.DbxReportsArchived.class);
        map.put(DBX_REPORTS_SCHEDULED, Marker.DbxReportsScheduled.class);
        map.put(DBX_COST_TRENDS, Marker.DbxCostTrends.class);
        map.put(DBX_COST_BUDGET, Marker.DbxCostBudget.class);
        map.put(DBX_RUNS, Marker.DbxRuns.class);
        map.put(DBX_JOBS, Marker.DbxJobs.class);
        map.put(DBX_APP_DETAILS, Marker.DbxAppDetails.class);
        map.put(DBX_INSIGHTS_OVERVIEW, Marker.DbxInsightsOverview.class);
        map.put(DBX_DATA_OVERVIEW, Marker.DbxDataOverview.class);
        map.put(DBX_DATA_TABLES, Marker.DbxDataTables.class);
        map.put(DBX_MANAGE, Marker.DbxManage.class);
        map.put(DBX_COMPUTE, Marker.DbxCompute.class);
        map.put(DBX_AUTOACTION, Marker.DbxAutoAction.class);
        map.put(DBX_APITOKEN, Marker.DbxApiToken.class);
        map.put(EMR_SPARK, Marker.EMRSpark.class);
        map.put(EMR_HIVE, Marker.EMRHive.class);
        map.put(EMR_TEZ, Marker.EMRTez.class);
        map.put(EMR_MAPREDUCE, Marker.EMRMapReduce.class);
        map.put(EMR_CLUSTER_INSIGHTS, Marker.emrClusterinsights.class);
        map.put(EMR_CLUSTER_JOBTRENDS, Marker.emrClusterjobtrends.class);
        map.put(EMR_INEFFICIENT_APPS, Marker.EmrInefficientApps.class);
        map.put(EMR_CLUSTER_CHARGEBACK, Marker.emrClusterChargeback.class);
        map.put(EMR_MANAGE, Marker.EmrManage.class);
        map.put(EMR_ALL_APPLICATION, Marker.EMRAllApps.class);
        map.put(EMR_DATA_OVERVIEW, Marker.EmrDataOverview.class);
        map.put(EMR_REPORTS_TOPX, Marker.EmrReportsTopX.class);
        map.put(EMR_REPORTS_ARCHIVED, Marker.EmrReportsArchived.class);
        map.put(EMR_REPORTS_SCHEDULED, Marker.EmrReportsScheduled.class);
        map.put(GCP_CLUSTER_OVERVIEW, Marker.GCPClusterOverview.class);
        map.put(GCP_CLUSTER_JOBS, Marker.GCPClusterJobs.class);
        map.put(GCP_YARN_CHARGEBACK, Marker.GCPYarnChargeback.class);
        map.put(GCP_YARN_RESOURCES, Marker.GCPYarnResources.class);
        map.put(GCP_ALL_APPS, Marker.GCPAllApps.class);
        map.put(GCP_INEFFICIENT_APPS, Marker.GCPInefficientApps.class);
        map.put(GCP_MANAGE, Marker.GCPManage.class);
        map.put(GCP_APP_DETAILS_HIVE, Marker.GCPAppDetailsHive.class);
        map.put(GCP_APP_DETAILS_MR, Marker.GCPAppDetailsMr.class);
        map.put(GCP_APP_DETAILS_SPARK, Marker.GCPAppDetailsSpark.class);
        map.put(GCP_APP_DETAILS_TEZ, Marker.GCPAppDetailsTez.class);
        map.put(GCP_AUTO_ACTIONS, Marker.GCPAutoAction.class);
        map.put(GCP_CLUSTER_WORKLOAD, Marker.GCPClusterWorkload.class);
        map.put(GCP_DATA_TABLES, Marker.GCPDataTables.class);
        map.put(GCP_DATA_OVERVIEW, Marker.GCPDataOverview.class);
        map.put(EMR_COST_CHARGEBACK, Marker.EmrCostChargeback.class);
        return Collections.unmodifiableMap(map);
    }
}
