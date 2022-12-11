package com.qa.iris.login;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.relevantcodes.extentreports.LogStatus;

@Marker.Login
public class LoginTests extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(LoginTests.class.getName());

	//Sample Test Case
	
	@Test
	public void loginToOrderManagement() {
		test = extent.startTest("loginToOrderManagement", "Validate Successful Login");
		test.assignCategory("Login");
		Log.startTestCase("loginToOrderManagement");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		test.log(LogStatus.PASS, "User successfully logged in to Order Management Page");
	}

}
