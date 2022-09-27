package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Marker {
    @Retention(RetentionPolicy.RUNTIME)
    @interface Only {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Smoke {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Regression {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface All {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Alerts {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TopX {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ImpalaResources {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ImpalaChargeback {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AllApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface QueueAnalysis {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsImpala {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsSpark {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsTez {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsTezLlap {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface emrClusterinsights {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrInefficientApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface emrClusterjobtrends {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsMr {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ReportArchive {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface InefficientApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ReportsScheduled {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface YarnChargeback {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface YarnResources {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterJobs {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterUserReports {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterWorkload {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterHBase {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface UserReports {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Manage {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DataForecasting {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DataSmallFiles {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DataFileReports {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrManage {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Tuning {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface KafkaExternal {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClusterELK {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MigrationServices {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MigrationClusterDiscovery {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface WorkloadFit {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface CloudMappingPerHost {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface JobsWorkflow {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface JobsSessions {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ImpalaInsights {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxCostChargeback {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrCostChargeback {
    }
    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxRuns {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxJobs {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxManage {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxAppDetails {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxReportsTopX {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxInsightsOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrReportsTopX {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EMRAllApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxReportsArchived {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrReportsArchived {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxReportsScheduled {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrReportsScheduled {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxCostTrends {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxCostBudget {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxCompute {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxDataOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EmrDataOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxDataTables {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxAutoAction {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EMRSpark {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EMRHive {
    }
    @Retention(RetentionPolicy.RUNTIME)
    @interface EMRMapReduce {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface EMRTez {
    }


    @Retention(RetentionPolicy.RUNTIME)
    @interface DbxApiToken {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface AppDetailsHive {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAppDetailsSpark {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAppDetailsHive {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAppDetailsTez {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAppDetailsMr {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPClusterOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPYarnResources {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPYarnChargeback {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPInefficientApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPManage {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAllApps {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPClusterJobs {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPClusterWorkload {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPDataTables {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPDataOverview {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface GCPAutoAction {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface emrClusterChargeback {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DataTables {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DataOverview {
    }
}

