package com.qa.base;

import com.qa.connections.db.InfluxDBClient;
import com.qa.constants.ConfigConstants;
import com.qa.constants.InfluxMetricsConstants;
import com.qa.io.ConfigReader;
import com.qa.utils.DateUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Ankur Jaiswal
 * Implementation of custom listener for testng.
 */
public class CustomListener extends BaseClass implements ITestListener {

    private static final Long EXECUTION_ID = System.currentTimeMillis();
    private InfluxDBClient influxDBClient = InfluxDBClient.getConnection();

    /**
     * Add skip test cases to html report
     * @param result - Result Object of test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.startTest(result.getMethod().getMethodName());
        test.log(LogStatus.SKIP, result.getTestName() + " skipped because " +
                result.getThrowable().getMessage());
        extent.endTest(test);
        extent.flush();
        try {
            influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
                    "SKIP"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
                    "PASS"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            if (result.getThrowable() instanceof AssertionError) {
                influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
                        "FAIL"));
            } else {
                influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
                        "FATAL"));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {
    }

    /**
     * Get the data to be pushed on influxDB
     * @param result - ITestResult
     * @param status - Execution Status
     * @return - Map of k,v pair
     * @throws UnknownHostException
     */
    private Map<String, Object> getDataToPushForInflux(ITestResult result,
                                                       String status) throws UnknownHostException {
        Map<String, Object> data = new HashMap<>();
        Properties prop = ConfigReader.readBaseConfig();
        data.put(InfluxMetricsConstants.METHOD_NAME, result.getMethod().getMethodName());
        data.put(InfluxMetricsConstants.STATUS, status);
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_BUILD, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_BUILD));
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_VERSION, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_VERSION));
        data.put(InfluxMetricsConstants.BATCH_ID, DateUtils.convertMilliSecToISO(EXECUTION_ID));
        data.put(InfluxMetricsConstants.URL, prop.getProperty(ConfigConstants.UnravelConfig.URL));
        data.put(InfluxMetricsConstants.HOST, InetAddress.getLocalHost().getHostName());
        data.put(InfluxMetricsConstants.DURATION, result.getEndMillis() - result.getStartMillis());
        data.put(InfluxMetricsConstants.MARKERS, System.getProperty(ConfigConstants.SystemConfig.MARKERS));
        return data;
    }
}
