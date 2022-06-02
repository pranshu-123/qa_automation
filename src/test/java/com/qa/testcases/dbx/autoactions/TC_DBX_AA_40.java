package com.qa.testcases.dbx.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.DbxAutoAction
public class TC_DBX_AA_40 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_40.class.getName());

	@Test
	public void TC_DBX_AA_40_validateAutoActionJobHistory() {
		test = extent.startTest("TC_DBX_AA_40_validateAutoActionJobHistory", "Validate Auto Action Job Run History");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_40_validateAutoActionJobHistory");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectAllPurposeJob();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		dbxAutoAction.selectScopeType("Cluster","except");
		test.log(LogStatus.INFO, "Trigger conditions are removed");
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		Assert.assertTrue(dbxAutoAction.validateHistoryPageForAutoAction(policyName),"History Page does not show up accurate data");
		dbxAutoAction.close();
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "Auto Action History Page shows correct details.");

	}
}
