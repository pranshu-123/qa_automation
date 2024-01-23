package com.qa.iris.om.vendormaster;

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
import com.qa.workflows.VendorMasterWorkflow;

@Listeners(CustomListener.class)
@Marker.PurchaseOrder
public class VM_InactivateVendor extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(VM_InactivateVendor.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_VM_11_inactivateVendor() {
		test = extent.createTest("TC_VM_11_inactivateVendor", "Create New Vendor");
		test.assignCategory("VendorMaster");
		Log.startTestCase("TC_VM_11_inactivateVendor");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		VendorMasterWorkflow vendorMasterWorkflow = new VendorMasterWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Vendor Master page");
		vendorMasterWorkflow.selectVendorMaster();
		vendorMasterWorkflow.createVendor("inactivevendormaster");
		vendorMasterWorkflow.createNewVendorMaster();
		vendorMasterWorkflow.searchVendor("inactivevendormaster");
		vendorMasterWorkflow.activateVendor();
		LOGGER.info("Vendor activated !!!");
		vendorMasterWorkflow.validateActivation();
		test.log(Status.PASS, "Vendor activated successfully");
	}

}
