package com.qa.iris.om.purchaseorder;

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
import com.qa.workflows.PurchaseManagementWorkflow;

@Listeners(CustomListener.class)
@Marker.OrderManagement
public class PO_DeleteOrder extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_DeleteOrder.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_PO_04_deleteOrder() {
		test = extent.createTest("TC_PO_04_deleteOrder", "Delete Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_PO_04_deleteOrder");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		PurchaseManagementWorkflow purchaseManagementWorkflow = new PurchaseManagementWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		purchaseManagementWorkflow.selectPurchaseOrder();
		purchaseManagementWorkflow.selectOrder();
		purchaseManagementWorkflow.deleteOrder();
		test.log(Status.PASS, "Order deleted successfully");
	}

}
