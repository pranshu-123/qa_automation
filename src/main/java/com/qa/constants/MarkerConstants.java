package com.qa.constants;

import com.qa.annotations.Marker;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MarkerConstants {
  public static final Map<String, Class<? extends Annotation>> MARKER_MAPPING = initMap();
  public static final String SMOKE = "smoke";
  public static final String TOPX = "topx";
  public static final String REGRESSION = "regression";
  public static final String CLUSTER_OVERVIEW = "cluster_overview";

  private static Map<String, Class<? extends Annotation>> initMap() {
    Map<String, Class<? extends Annotation>> map = new LinkedHashMap<>();
    map.put(SMOKE, Marker.Smoke.class);
    map.put(CLUSTER_OVERVIEW, Marker.ClusterOverview.class);
    map.put(TOPX, Marker.TopX.class);
    map.put(REGRESSION, Marker.Regression.class);
    return Collections.unmodifiableMap(map);
  }
}
