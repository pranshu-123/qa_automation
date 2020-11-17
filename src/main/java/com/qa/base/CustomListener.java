package com.qa.base;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author Ankur Jaiswal
 * Implementation of custom listener for testng.
 */
public class CustomListener extends BaseClass implements ITestListener {

    /**
     * Add skip test cases to html report
     * @param result - Result Object of test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.startTest(result.getMethod().getMethodName());
        LogStatus sta = test.getRunStatus();
        test.log(LogStatus.SKIP, result.getTestName() + " skipped because " +
                result.getThrowable().getMessage());
        extent.endTest(test);
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestFailure(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
