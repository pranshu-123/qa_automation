package com.qa.listeners;

import com.aventstack.extentreports.Status;
import com.qa.base.MainAccelerator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends MainAccelerator implements ITestListener {

    /**
     * Add skip test cases to html report
     * @param result - Result Object of test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.SKIP, result.getTestName() + " skipped because " +
                result.getThrowable().getMessage());
        extent.flush();
//        try {
////            influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
////                    "SKIP"));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        try {
//            influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
//                    "PASS"));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
//        try {
//            if (result.getThrowable() instanceof AssertionError) {
//                influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
//                        "FAIL"));
//            } else {
//                influxDBClient.writeDataToInflux(getDataToPushForInflux(result,
//                        "FATAL"));
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {
    }
}
