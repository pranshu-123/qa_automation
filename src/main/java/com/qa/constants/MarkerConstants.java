package com.qa.constants;

import com.qa.annotations.Marker;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pranshu
 * This class contains all constants related with markers
 */
public class MarkerConstants {
    public static final String SMOKE = "smoke";
    public static final String REGRESSION = "regression";
    public static final String ALL = "all";
    public static final String LOGIN = "login";
    public static final String ORDERMANAGEMENT = "OrderManagement";
    
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
        map.put(REGRESSION, Marker.Regression.class);
        map.put(ALL, Marker.All.class);
        map.put(LOGIN, Marker.Login.class);
        map.put(ORDERMANAGEMENT, Marker.OrderManagement.class);
        return Collections.unmodifiableMap(map);
    }
}
