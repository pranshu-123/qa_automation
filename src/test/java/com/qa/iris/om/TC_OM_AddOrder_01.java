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
public class TC_OM_AddOrder_01 extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_01.class.getName());


	
	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void verifyAddOrderMasterDetailPage() {
		test = extent.createTest("verifyAddOrderMasterDetailPage", "Verify Add Order Master detail page elements");
		test.assignCategory("OrderManagement");
		Log.startTestCase("verifyAddOrderMasterDetailPage");
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
		orderManagementWorkflow.validateOrderPageElements();
		test.log(Status.PASS, "All Page elements validated sucessfully");
	}

}
