package com.qa.iris.om;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.base.MainAccelerator;
import com.qa.utils.Log;
import com.qa.workflows.NFMHomepageWorkflow;
import com.qa.workflows.NFMLoginWorkflow;

public class TC_OM_AddOrder_01 extends MainAccelerator{
	
	private static final Logger LOGGER = Logger.getLogger(TC_OM_AddOrder_01.class.getName());

	
	@Test
	public void verifyAddOrderMasterDetailPage() {
		test = extent.startTest("verifyAddOrderMasterDetailPage", "Validate Successful Login");
		test.assignCategory("OrderManagement");
		Log.startTestCase("verifyAddOrderMasterDetailPage");
		NFMLoginWorkflow loginWorkflow = new NFMLoginWorkflow(driver);
		NFMHomepageWorkflow homepageWorkflow = new NFMHomepageWorkflow(driver);
	}

}
