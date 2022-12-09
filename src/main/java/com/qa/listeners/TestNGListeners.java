package com.qa.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.MainAccelerator;
import com.qa.utils.TestUtils;

public class TestNGListeners extends MainAccelerator implements ITestListener{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult iTestResult) {
		this.sendTestMethodStatus(iTestResult, "Fail");
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
	}

}
