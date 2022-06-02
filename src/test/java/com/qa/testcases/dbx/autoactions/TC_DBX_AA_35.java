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
public class TC_DBX_AA_35 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_35.class.getName());

	@Test
	public void TC_DBX_AA_35_createAllPurposeJobWithDurationAsTriggerCondition() {
		test = extent.startTest("TC_DBX_AA_35_createAllPurposeJobWithDurationAsTriggerCondition", "Create AA job for All purpose Databricks Cluster with total duration as trigger condition");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_35_createAllPurposeJobWithDurationAsTriggerCondition");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		String duration = "10";
		String metric = "duration";
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectAllPurposeJob();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		test.log(LogStatus.INFO, "Fill new auto action policy details");
		dbxAutoAction.setTotalDurationValue(metric,duration);
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		test.log(LogStatus.INFO, "All Purpose Job Validated");
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "All Purpose Job with Trigger condition as Time Duration worked as expected");
	}
}
