package com.qa.iris.login;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;

@Marker.Login
public class LoginTests extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(LoginTests.class.getName());

	//Sample Test Case
	
	@Test
	public void loginToOrderManagement() {
		test = extent.createTest("loginToOrderManagement", "Validate Successful Login");
		test.assignCategory("Login");
		Log.startTestCase("loginToOrderManagement");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		test.log(Status.PASS, "User successfully logged in to Order Management Page");
	}

}
