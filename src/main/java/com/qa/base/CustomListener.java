package com.qa.base;

import com.qa.connections.db.InfluxDBClient;
import com.qa.constants.ConfigConstants;
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
//    private Long START;
//    private Long END;
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
            influxDBClient.writeDataToInflux(dataToPush(result,
                    "SKIP"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
//        START = System.currentTimeMillis();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            influxDBClient.writeDataToInflux(dataToPush(result,
                    "PASS"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            if (result.getThrowable() instanceof AssertionError) {
                influxDBClient.writeDataToInflux(dataToPush(result,
                        "FAIL"));
            } else {
                influxDBClient.writeDataToInflux(dataToPush(result,
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

    private Map<String, Object> dataToPush(ITestResult result,
                                   String status) throws UnknownHostException {
        Map<String, Object> data = new HashMap<>();
        Properties prop = ConfigReader.readBaseConfig();
        data.put("test_case_id", result.getMethod().getMethodName());
        data.put("method_name", result.getMethod().getMethodName());
        data.put("status", status);
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_BUILD, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_BUILD));
        data.put(ConfigConstants.UnravelConfig.UNRAVEL_VERSION, prop.getProperty(ConfigConstants.UnravelConfig.UNRAVEL_VERSION));
        data.put("batch_id", DateUtils.convertMilliSecToISO(EXECUTION_ID));
        data.put("url", prop.getProperty(ConfigConstants.UnravelConfig.URL));
        data.put("host", InetAddress.getLocalHost().getHostName());
        data.put("duration", result.getEndMillis() - result.getStartMillis());
        data.put("markers", System.getProperty(ConfigConstants.SystemConfig.MARKERS));
        return data;
    }
}
