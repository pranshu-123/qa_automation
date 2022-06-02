package com.qa.testcases.dbx.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.DbxAutoAction
public class TC_DBX_AA_12 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_12.class.getName());

	@Test
	public void TC_DBX_AA_12_validateOnlyUserAutoAction() {
		test = extent.startTest("TC_DBX_AA_12_validateOnlyUserAutoAction", "Verify user is able to select only particular users save the auto action.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_12_validateOnlyUserAutoAction");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 18);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		dbxAutoAction.selectRefinedScope("User");
		dbxAutoAction.selectScopeType("User","only");
		test.log(LogStatus.INFO, "Trigger conditions are removed");
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "No Auto Action created without trigger condition");
	}
}
