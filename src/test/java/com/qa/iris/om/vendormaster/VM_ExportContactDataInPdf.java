package com.qa.iris.om.vendormaster;

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
import com.qa.workflows.VendorMasterWorkflow;

@Listeners(CustomListener.class)
@Marker.PurchaseOrder
public class VM_ExportContactDataInPdf extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(VM_ExportContactDataInPdf.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_VM_08_exportContactsDataInPdf() {
		test = extent.createTest("TC_VM_08_exportContactsDataInPdf", "Export Contacts Data into Pdf");
		test.assignCategory("VendorMaster");
		Log.startTestCase("TC_VM_08_exportContactsDataInPdf");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		VendorMasterWorkflow vendorMasterWorkflow = new VendorMasterWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Vendor Master page");
		vendorMasterWorkflow.selectVendorMaster();
		vendorMasterWorkflow.createVendor("testmaster");
		vendorMasterWorkflow.createNewVendorMaster();
		LOGGER.info("Vendor created !!!");
		vendorMasterWorkflow.navigateContactsTab();
		vendorMasterWorkflow.generateReports("PDF");
		FileUtils.checkForFileNameInDownloadsFolder("DataGrid");
		test.log(Status.PASS, "File downloaded successfully");
	}

}
