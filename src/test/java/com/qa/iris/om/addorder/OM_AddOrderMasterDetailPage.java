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
public class OM_AddOrderMasterDetailPage extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(OM_AddOrderMasterDetailPage.class.getName());


	
	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void TC_OM_AddOrder_01_verifyAddOrderMasterDetailPage() {
		test = extent.createTest("TC_OM_AddOrder_01_verifyAddOrderMasterDetailPage", "Verify Add Order Master detail page elements");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_OM_AddOrder_01_verifyAddOrderMasterDetailPage");
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
