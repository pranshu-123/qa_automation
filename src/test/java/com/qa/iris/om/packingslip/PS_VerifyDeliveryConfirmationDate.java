package com.qa.iris.om.packingslip;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.iris.om.purchaseorder.PO_AddLineItem;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.PackingSlipWorkflow;

public class PS_VerifyDeliveryConfirmationDate extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_AddLineItem.class.getName());


	@Test(groups = Categories.PACKING_SLIP)
	public void TC_PS_12_verifyDeliveryConfirmationDate() {
		test = extent.createTest("TC_PS_12_verifyDeliveryConfirmationDate", "Verify Delivery Confirmation Date");
		test.assignCategory("PackingSlip");
		Log.startTestCase("TC_PS_12_verifyDeliveryConfirmationDate");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		PackingSlipWorkflow packingSlipWorkflow = new PackingSlipWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		packingSlipWorkflow.selectPackingSlip();
		packingSlipWorkflow.selectOrder();
		packingSlipWorkflow.getDeliveryConfirmationDefaultDate();
		test.log(Status.PASS, "Delivery Confirmation Date is set to current date");
	}

}
