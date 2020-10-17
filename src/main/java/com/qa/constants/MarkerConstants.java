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
  public static final Map<String, Class<? extends Annotation>> MARKER_MAPPING = initMap();
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
  public static final String INEFFICIENT_APPS = "ineffient_apps";
  public static final String YARN_CHARGEBACK = "yarn_chargeback";
  public static final String YARN_RESOURCES = "yarn_resources";
  public static final String CLUSTER_JOBS = "cluster_jobs";
  public static final String CLUSTER_USER_REPORTS = "cluster_user_report";
  public static final String CLUSTER_WORKLOAD = "cluster_workload";
  public static final String USER_REPORTS = "user_reports";
  public static final String MANAGE = "manage";

  /**
   * This method will generate the reference of Marker Interface with
   * constant value which will be used on run time.
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
    map.put(INEFFICIENT_APPS, Marker.InefficientApps.class);
    map.put(YARN_CHARGEBACK, Marker.YarnChargeback.class);
    map.put(YARN_RESOURCES, Marker.YarnResources.class);
    map.put(CLUSTER_JOBS, Marker.ClusterJobs.class);
    map.put(CLUSTER_USER_REPORTS, Marker.ClusterUserReports.class);
    map.put(CLUSTER_WORKLOAD, Marker.ClusterWorkload.class);
    map.put(USER_REPORTS, Marker.UserReports.class);
    map.put(MANAGE, Marker.Manage.class);
    return Collections.unmodifiableMap(map);
  }
}
