package com.qa.iris.om.addorder;

import java.util.logging.Logger;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.listeners.CustomListener;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.OrderManagementWorkflow;

@Listeners(CustomListener.class)
@Marker.OrderManagement
public class OM_CreateNewOrder extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(OM_CreateNewOrder.class.getName());


	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void TC_OM_AddOrder_03_createNewOrder() {
		test = extent.createTest("TC_OM_AddOrder_03_createNewOrder", "Create New Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_OM_AddOrder_03_createNewOrder");
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
		orderManagementWorkflow.deleteLineItem();
		orderManagementWorkflow.saveOrder();
		orderManagementWorkflow.createOrder();
		test.log(Status.PASS, "Order Created successfully");
	}

}
