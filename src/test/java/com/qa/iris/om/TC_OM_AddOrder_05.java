package com.qa.iris.om;

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
public class TC_OM_AddOrder_05 extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_05.class.getName());


	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void TC_OM_AddOrder_05_deleteNewOrder() {
		test = extent.createTest("TC_OM_AddOrder_05_deleteNewOrder", "Delete existing Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_OM_AddOrder_05_deleteNewOrder");
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
		orderManagementWorkflow.deleteOrder();
		test.log(Status.PASS, "Order deleted successfully");
	}

}
