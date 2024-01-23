package com.qa.iris.om.packingslip;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.base.MainAccelerator;
import com.qa.constants.Categories;
import com.qa.iris.om.purchaseorder.PO_AddLineItem;
import com.qa.utils.DateUtils;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;
import com.qa.workflows.PackingSlipWorkflow;

public class PS_VerifyPackingSlipDefaultDate extends MainAccelerator{

	private static final Logger LOGGER = Logger.getLogger(PO_AddLineItem.class.getName());


	@Test(groups = Categories.PACKING_SLIP)
	public void TC_PS_09_verifyDefaultPackingSlipDate() {
		test = extent.createTest("TC_PS_09_verifyDefaultPackingSlipDate", "Verify Packing Slip Default Date");
		test.assignCategory("PackingSlip");
		Log.startTestCase("TC_PS_09_verifyDefaultPackingSlipDate");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
		PackingSlipWorkflow packingSlipWorkflow = new PackingSlipWorkflow(driver);
		loginWorkflow.loginToOM();
		LOGGER.info("Navigated to NFM Dev Page");
		homepageWorkflow.selectAddOrder();
		homepageWorkflow.navigateToAddOrderPage();
		LOGGER.info("Navigated to Order Management page");
		packingSlipWorkflow.selectPackingSlip();
		String date = packingSlipWorkflow.getPackingSlipDefaultDate();
		String currentDate = DateUtils.getCurrentDate();
		Assert.assertTrue(currentDate.equals(date));
		test.log(Status.PASS, "Packing Date is set to default current date");
	}

}
