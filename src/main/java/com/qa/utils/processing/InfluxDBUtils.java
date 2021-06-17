package com.qa.utils.processing;

import com.qa.annotations.Marker;
import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import com.qa.constants.InfluxMetricsConstants;
import com.qa.constants.MarkerConstants;
import com.qa.io.ConfigReader;
import com.qa.utils.DateUtils;
import org.testng.ITestResult;


import java.lang.annotation.Annotation;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class InfluxDBUtils {

    private static final Long EXECUTION_ID = System.currentTimeMillis();

    /**
     * Get the data to be pushed on influxDB
     * @param result - ITestResult
     * @param status - Execution Status
     * @return - Map of k,v pair
     * @throws UnknownHostException
     */
    public static Map<String, Object> getDataToPushForInflux(ITestResult result,
                                                       String status) throws UnknownHostException {
        Annotation[] ann = result.getMethod().getRealClass().getAnnotations();
        Map<String, Object> data = new HashMap<>();
        Properties prop = ConfigReader.readBaseConfig();
        String markers = System.getProperty(ConfigConstants.SystemConfig.MARKERS);
        Properties markerPillarMapping = ConfigReader.readConfig(FileConstants.getMarkerPillarMappingFile());
        data.put(InfluxMetricsConstants.METHOD_NAME, result.getMethod().getMethodName());
        data.put(InfluxMetricsConstants.STATUS, status);
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_BUILD, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_BUILD));
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_VERSION, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_VERSION));
        data.put(InfluxMetricsConstants.BATCH_ID, DateUtils.convertMilliSecToISO(EXECUTION_ID));
        data.put(InfluxMetricsConstants.URL, prop.getProperty(ConfigConstants.UnravelConfig.URL));
        data.put(InfluxMetricsConstants.HOST, InetAddress.getLocalHost().getHostName());
        data.put(InfluxMetricsConstants.DURATION, result.getEndMillis() - result.getStartMillis());
        String marker = getClassSpecificMarkers(ann);
        data.put(InfluxMetricsConstants.MARKERS, marker);
        if (markerPillarMapping.containsKey(marker)) {
            if (markerPillarMapping.getProperty(marker) != null && !markerPillarMapping
                .getProperty(marker).trim().equals("")) {
                data.put(InfluxMetricsConstants.PILLAR, markerPillarMapping.getProperty(marker));
            } else {
                data.put(InfluxMetricsConstants.PILLAR, "");
            }
        } else {
            data.put(InfluxMetricsConstants.PILLAR, "");
        }
        return data;
    }

    /**
     * Get class specific markers
     */
    private static String getClassSpecificMarkers(Annotation[] markers) {
        for (Annotation marker : markers) {
            if ((!(marker instanceof Marker.All) &&
                !(marker instanceof Marker.Regression) &&
                !(marker instanceof Marker.Smoke))) {
                Set<Map.Entry<String, Class<? extends Annotation>>> markerMapping =
                    MarkerConstants.MARKER_MAPPING.entrySet();
                for (Map.Entry mapping : markerMapping) {
                    if (mapping.getValue().toString().contains(marker.annotationType().getName())) {
                        return mapping.getKey().toString();
                    }
                }
            }
        }
        return "Unknown Marker";
    }
}
