package com.qa.connections.db;

import com.qa.constants.ConfigConstants;
import com.qa.constants.FileConstants;
import com.qa.io.InfluxConfigReader;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InfluxDBClient {
    private static InfluxDB influxDB;
    private static InfluxDBClient influxDBClient;

    private InfluxDBClient() {
        InfluxConfigReader configReader = new InfluxConfigReader();
        try {
            Map<String, Object> config = configReader.readYamlFile(FileConstants.getInfluxConfigYaml());
            influxDB = InfluxDBFactory.connect(((Map)config.get("influx")).get("url").toString(),
                ((Map)config.get("influx")).get("username").toString(),
                ((Map)config.get("influx")).get("password").toString());
            influxDB.setDatabase(((Map)config.get("influx")).get("db").toString());
            influxDB.enableBatch(BatchOptions.DEFAULTS);
            influxDB.ping();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static InfluxDBClient getConnection() {
        if (influxDBClient == null) {
            influxDBClient = new InfluxDBClient();
        }
        return influxDBClient;
    }

    public void writeDataToInflux(Map<String, Object> data) {
        influxDB.write(Point.measurement("test_execution")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("unravel_build", data.get(ConfigConstants.UnravelConfig.UNRAVEL_BUILD).toString())
                .tag("unravel_version", data.get(ConfigConstants.UnravelConfig.UNRAVEL_VERSION).toString())
                .tag("url", data.get("url").toString())
                .tag("batch_id", data.get("batch_id").toString())
                .addField("method_name", data.get("method_name").toString())
                .tag("host", data.get("host").toString())
                .tag("status", data.get("status").toString())
                .addField("duration", data.get("duration").toString())
                .tag("markers", data.get("markers").toString())
                .build());
    }
}
