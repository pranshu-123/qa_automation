package com.qa.iris.om;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.OrderManagementWorkflow;
import com.relevantcodes.extentreports.LogStatus;

@Marker.OrderManagement
public class TC_OM_AddOrder_02 extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_02.class.getName());

	
	@Test(groups = Categories.ORDER_MANAGEMENT)
	public void verifyAddOrderAddressDetailPage() {
		test = extent.startTest("verifyAddOrderAddressDetailPage", "Verify Add Order Address detail page elements");
		test.assignCategory("OrderManagement");
		Log.startTestCase("verifyAddOrderAddressDetailPage");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		OrderManagementWorkflow orderManagementWorkflow = new OrderManagementWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
	//	orderManagementWorkflow.selectAddOrderMasterDetail();
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		orderManagementWorkflow.selectOrder();
		orderManagementWorkflow.selectAddressTab();
		orderManagementWorkflow.validateAddressPageElements();
		test.log(LogStatus.PASS, "All Page elements validated sucessfully");
	}

}
