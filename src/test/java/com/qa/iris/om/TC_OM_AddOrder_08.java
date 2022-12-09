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
public class TC_OM_AddOrder_08 extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_08.class.getName());


	//@Test
	public void deleteExistingLineItem() {
		test = extent.startTest("deleteExistingLineItem", "Delete existing Line Item");
		test.assignCategory("OrderManagement");
		Log.startTestCase("deleteExistingLineItem");
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
		orderManagementWorkflow.copyLineItem();
		LOGGER.info("New Line Item copied");
		orderManagementWorkflow.deleteLineItem();
		LOGGER.info("New Line Item deleted");
		orderManagementWorkflow.saveOrder();
		orderManagementWorkflow.createOrder();
		test.log(LogStatus.PASS, "Delete existing line item working successful");
	}

}
