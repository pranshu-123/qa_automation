package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_06 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_06.class.getName());

	@Test
	public void TC_DBX_AA_06_activateExistingPolicy() {
		test = extent.startTest("TC_DBX_AA_06_activateExistingPolicy", "Make inactive policy active");
		test.assignCategory("DBX Auto Action");
		Log.startTestCase("TC_DBX_AA_06_activateExistingPolicy");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		dbxAutoAction.navigateToAutoAction();
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		dbxAutoAction.selectRefinedScope("Queue");
		dbxAutoAction.selectScopeType("Queue","only");
		test.log(LogStatus.INFO, "Trigger conditions are removed");
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		String name = dbxAutoAction.fetchPolicyName();
		waitExecuter.sleep(1000);
		dbxAutoAction.deActivateAndValidateJob(name);
		dbxAutoAction.activateAndValidateJob(name);
		logger.info("Policy activated successfully");
		test.log(LogStatus.PASS, "Verified Auto Action activate process..");
	}
}
