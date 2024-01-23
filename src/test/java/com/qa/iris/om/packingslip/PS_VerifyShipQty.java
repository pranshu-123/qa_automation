package com.qa.iris.om.packingslip;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.iris.om.purchaseorder.PO_AddLineItem;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.PackingSlipWorkflow;

public class PS_VerifyShipQty extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_AddLineItem.class.getName());


	@Test(groups = Categories.PACKING_SLIP)
	public void TC_PS_17_verifyShipQty() {
		test = extent.createTest("TC_PS_17_verifyShipQty", "Verify Order Ship Qty Field");
		test.assignCategory("PackingSlip");
		Log.startTestCase("TC_PS_17_verifyShipQty");
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
		int initialQty = packingSlipWorkflow.calculateSum();
		packingSlipWorkflow.updateShipQty();
		int finalQty = packingSlipWorkflow.calculateSum();
		Assert.assertTrue(finalQty > initialQty);
		test.log(Status.PASS, "Line Items are disabled once confirmed");
	}

}
