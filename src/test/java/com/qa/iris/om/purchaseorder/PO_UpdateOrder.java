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
public class PO_UpdateOrder extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_UpdateOrder.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_PO_03_updatePurchaseOrder() {
		test = extent.createTest("TC_PO_03_updatePurchaseOrder", "Update Purchase Order");
		test.assignCategory("OrderManagement");
		Log.startTestCase("TC_PO_03_updatePurchaseOrder");
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
		purchaseManagementWorkflow.addLineItem("5", "2.50");
		purchaseManagementWorkflow.verifyLineAmount(5.00, 2.50);
		purchaseManagementWorkflow.saveOrder();
		purchaseManagementWorkflow.addLineItem("6", "5.50");
		purchaseManagementWorkflow.verifyLineAmount(6.00, 6.50);
		purchaseManagementWorkflow.saveOrder();
		test.log(Status.PASS, "Line Item updated successfully");
	}

}
