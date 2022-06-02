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
public class TC_DBX_AA_38 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_38.class.getName());

	@Test
	public void TC_DBX_AA_38_validate2RuleSetWithAndOperator() {
		test = extent.startTest("TC_DBX_AA_38_validate2RuleSetWithAndOperator", "Verify that each function of AA by adding more than 2 RULE SET - AND");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_38_validate2RuleSetWithAndOperator");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		String condition = "AND";
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
		dbxAutoAction.selectTriggerCondition(condition);
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "Two Rule Set validated with AND Operator");
	}
}
