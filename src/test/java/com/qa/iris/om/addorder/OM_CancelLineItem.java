package com.qa.iris.om.addorder;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.OrderManagementWorkflow;

@Marker.OrderManagement
public class OM_CancelLineItem extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(OM_CancelLineItem.class.getName());


	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void TC_OM_AddOrder_09_verifyCancelFunctionalForLineItem() {
		test = extent.createTest("TC_OM_AddOrder_09_verifyCancelFunctionalForLineItem", "Verify Cancel functionality");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_OM_AddOrder_09_verifyCancelFunctionalForLineItem");
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
		orderManagementWorkflow.cancelLineItem();
		LOGGER.info("Added Line Item cancelled");
		test.log(Status.PASS, "Cancelling line item functionality working successful");
	}

}
