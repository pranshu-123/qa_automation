package com.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.base.MainAccelerator;

public class TestNGListeners extends MainAccelerator implements ITestListener{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

		tearDown();
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult iTestResult) {
		if(iTestResult.getMethod().getMethodName()!="main") {
			this.sendTestMethodStatus(iTestResult, "Fail");
		}
		test.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " FAILED", ExtentColor.RED));
		//take screen shot
		//		try {
		//		TestUtils.takeScreenshotAtEndOfTest();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}

	public void onTestSkipped(ITestResult iTestResult) {
		this.sendTestMethodStatus(iTestResult, "Skip");		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult iTestResult) {
		this.sendTestMethodStatus(iTestResult, "Pass");
		test.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName() + " PASSED", ExtentColor.GREEN));;
	}

}
