package com.qa.iris.om.purchaseorder;

import java.util.logging.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qa.annotations.Marker;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.listeners.CustomListener;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.PurchaseManagementWorkflow;

@Listeners(CustomListener.class)
@Marker.PurchaseOrder
public class PO_ExportAllDataToExcel extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_ExportAllDataToExcel.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_PO_14_exportToExcel() {
		test = extent.createTest("TC_PO_14_exportToExcel", "Create New Order");
		test.assignCategory("PurchaseOrder");
		Log.startTestCase("TC_PO_14_exportToExcel");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		PurchaseManagementWorkflow purchaseManagementWorkflow = new PurchaseManagementWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		purchaseManagementWorkflow.selectPurchaseOrder();
		purchaseManagementWorkflow.generateReports("excel");
		FileUtils.checkForFileNameInDownloadsFolder("DataGrid");
		test.log(Status.PASS, "File downloaded successfully");
	}

}
