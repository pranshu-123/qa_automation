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
public class TC_OM_AddOrder_05 extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_05.class.getName());


	@Test
	public void deleteNewOrder() {
		test = extent.startTest("deleteNewOrder", "Delete existing Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("deleteNewOrder");
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
		test.log(LogStatus.PASS, "Order deleted successfully");
	}

}
