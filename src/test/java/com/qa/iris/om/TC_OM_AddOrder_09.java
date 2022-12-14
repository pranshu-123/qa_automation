package com.qa.iris.om;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.OrderManagementWorkflow;
import com.relevantcodes.extentreports.LogStatus;

@Marker.OrderManagement
public class TC_OM_AddOrder_09 extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_09.class.getName());


	@Test
	public void verifyCancelFunctionalForLineItem() {
		test = extent.startTest("verifyCancelFunctionalForLineItem", "Verify Cancel functionality");
		test.assignCategory("OrderManagement");
		Log.startTestCase("verifyCancelFunctionalForLineItem");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		OrderManagementWorkflow orderManagementWorkflow = new OrderManagementWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		orderManagementWorkflow.selectAddOrderMasterDetail();
		orderManagementWorkflow.selectOrder();
		orderManagementWorkflow.addNewLineItem();
		LOGGER.info("New Line Item added up");
		orderManagementWorkflow.cancelLineItem();
		LOGGER.info("Added Line Item cancelled");
		test.log(LogStatus.PASS, "Cancelling line item functionality working successful");
	}

}
