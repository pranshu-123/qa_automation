package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Marker {
  @interface Smoke {}
  @interface Regression {}
  @interface All {}
  @interface Alerts {}
  @interface ClusterOverview {}
  @interface TopX {}
  @interface ImpalaResources {}
  @interface ImpalaChargeback {}
  @interface AllApps {}
  @interface QueueAnalysis {}
  @interface AppDetailsImpala {}
  @interface AppDetailsSpark {}
  @interface AppDetailsTez {}
  @interface ReportArchive {}
  @interface InefficientApps {}
  @interface YarnChargeback {}
  @interface YarnResources {}
  @interface ClusterJobs {}
  @interface ClusterUserReports {}
  @interface ClusterWorkload {}
  @interface UserReports {}
  @interface Manage {}
  @interface AppDetailsHive {}
  @interface DataForecasting {}
  @interface Tuning {}
}