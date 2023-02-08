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
public class TC_OM_AddOrder_06 extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_06.class.getName());


	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void TC_OM_AddOrder_06_createNewBuildOrder() {
		test = extent.createTest("TC_OM_AddOrder_06_createNewBuildOrder", "Create New Build Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_OM_AddOrder_06_createNewBuildOrder");
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
		orderManagementWorkflow.saveOrder();
		orderManagementWorkflow.createOrder();
		test.log(Status.PASS, "New Build Order Created successfully");
	}

}
