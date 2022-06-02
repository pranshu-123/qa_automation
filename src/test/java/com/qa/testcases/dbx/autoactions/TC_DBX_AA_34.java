package com.qa.testcases.dbx.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.DbxAutoAction
public class TC_DBX_AA_34 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_34.class.getName());

	@Test
	public void TC_DBX_AA_34_createInactiveAutoActionJob() {
		test = extent.startTest("TC_DBX_AA_34_createInactiveAutoActionJob", "Create inactive AA job ");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_34_createInactiveAutoActionJob");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		test.log(LogStatus.INFO, "Fill new auto action policy details");
		dbxAutoAction.createInactiveJob();
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateInactiveJob(policyName);
		test.log(LogStatus.INFO, "Inactive Job validated");
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "Inactive Job validated");
	}
}
