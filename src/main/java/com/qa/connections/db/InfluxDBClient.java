package com.qa.connections.db;

import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import com.qa.constants.InfluxMetricsConstants;
import com.qa.io.InfluxConfigReader;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ankur Jaiswal
 * This class contains utility to insert data into influxDB
 */
public class InfluxDBClient {
    private static InfluxDB influxDB;
    private static InfluxDBClient influxDBClient;

    private InfluxDBClient() {
        InfluxConfigReader configReader = new InfluxConfigReader();
        try {
            Map<String, Object> config = configReader.readYamlFile(FileConstants.getInfluxConfigYaml());
            influxDB = InfluxDBFactory.connect(((Map)config.get("influx")).get("url").toString());
            influxDB.setDatabase(((Map)config.get("influx")).get("db").toString());
            influxDB.enableBatch(BatchOptions.DEFAULTS);
            influxDB.ping();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the connection of influxDB
     * @return influxDBConnection Client
     */
    public static InfluxDBClient getConnection() {
        if (influxDBClient == null) {
            influxDBClient = new InfluxDBClient();
        }
        return influxDBClient;
    }

    /**
     * Write data to influx db as tag and value
     * @param data - Data to be written in influx
     */
    public void writeDataToInflux(Map<String, Object> data) {
        influxDB.write(Point.measurement("test_execution1")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(InfluxMetricsConstants.UNRAVEL_BUILD, data.get(ConfigConstants.UnravelConfig.UNRAVEL_BUILD).toString())
                .tag(InfluxMetricsConstants.UNRAVEL_VERSION, data.get(ConfigConstants.UnravelConfig.UNRAVEL_VERSION).toString())
                .tag(InfluxMetricsConstants.URL, data.get(InfluxMetricsConstants.URL).toString())
                .tag(InfluxMetricsConstants.BATCH_ID, data.get(InfluxMetricsConstants.BATCH_ID).toString())
                .addField(InfluxMetricsConstants.METHOD_NAME, data.get(InfluxMetricsConstants.METHOD_NAME).toString())
                .tag(InfluxMetricsConstants.HOST, data.get(InfluxMetricsConstants.HOST).toString())
                .tag(InfluxMetricsConstants.STATUS, data.get(InfluxMetricsConstants.STATUS).toString())
                .addField(InfluxMetricsConstants.DURATION, data.get(InfluxMetricsConstants.DURATION).toString())
                .tag(InfluxMetricsConstants.MARKERS, data.get(InfluxMetricsConstants.MARKERS).toString())
                .tag(InfluxMetricsConstants.PILLAR, data.get(InfluxMetricsConstants.PILLAR).toString())
                .build());
    }

    /**
     * Close Connection
     */
    public void closeInfluxConnection() {
        influxDB.close();
    }
}
