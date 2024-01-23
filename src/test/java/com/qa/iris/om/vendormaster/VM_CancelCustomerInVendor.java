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
public class VM_CancelCustomerInVendor extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(VM_CancelCustomerInVendor.class.getName());


	@Test(groups = Categories.PURCHASE_ORDER)
	public void TC_VM_10_cancelAddedContacts() {
		test = extent.createTest("TC_VM_10_cancelAddedContacts", "Cancel Contacts");
		test.assignCategory("VendorMaster");
		Log.startTestCase("TC_VM_10_cancelAddedContacts");
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
		vendorMasterWorkflow.addCustomer("Pranshu", "Agarwal");
		vendorMasterWorkflow.cancelCustomerAddition();
		test.log(Status.PASS, "Customer addition cancelled.");
	}

}
