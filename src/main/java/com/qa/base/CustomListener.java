package com.qa.base;

import com.qa.connections.db.InfluxDBClient;
import static com.qa.utils.processing.InfluxDBUtils.getDataToPushForInflux;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.net.UnknownHostException;
/**
 * @author Ankur Jaiswal
 * Implementation of custom listener for testng.
 */
public class CustomListener extends BaseClass implements ITestListener {
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
}
