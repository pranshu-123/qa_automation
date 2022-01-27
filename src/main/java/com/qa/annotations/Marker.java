package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Marker {
  @Retention(RetentionPolicy.RUNTIME)
  @interface Only {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface Smoke {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface Regression {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface All {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface Alerts {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterOverview {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface TopX {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ImpalaResources {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ImpalaChargeback {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AllApps {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface QueueAnalysis {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsImpala {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsSpark {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsTez {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsTezLlap {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsMr {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ReportArchive {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface InefficientApps {}
  @Retention(RetentionPolicy.RUNTIME)
  @interface ReportsScheduled{}

  @Retention(RetentionPolicy.RUNTIME)
  @interface YarnChargeback {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface YarnResources {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterJobs {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterUserReports {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterWorkload {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterHBase {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface UserReports {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface Manage {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface AppDetailsHive {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface DataForecasting {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface DataSmallFiles {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface DataFileReports {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface Tuning {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface KafkaExternal {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ClusterELK {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface MigrationServices{}

  @Retention(RetentionPolicy.RUNTIME)
  @interface MigrationClusterDiscovery {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface WorkloadFit {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface CloudMappingPerHost {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface JobsWorkflow {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface ImpalaInsights {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface DbCostChargeback {}

  @Retention(RetentionPolicy.RUNTIME)
  @interface DbJobsRuns {}
}