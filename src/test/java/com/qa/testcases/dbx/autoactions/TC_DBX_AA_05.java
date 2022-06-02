package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_05 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_05.class.getName());

	@Test
	public void TC_DBX_AA_05_deactivateExistingPolicy() {
		test = extent.startTest("TC_DBX_AA_05_deactivateExistingPolicy", "Make active policy inactive");
		test.assignCategory("DBX Auto Action");
		Log.startTestCase("TC_DBX_AA_05_deactivateExistingPolicy");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		dbxAutoAction.navigateToAutoAction();
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectAllPurposeJob();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		dbxAutoAction.selectScopeType("Cluster","except");
		test.log(LogStatus.INFO, "Trigger conditions are removed");
		dbxAutoAction.saveAA();
		dbxAutoAction.deActivateAndValidateJob(policyName);
		logger.info("Policy deactivated successfully");
		test.log(LogStatus.PASS, "Verified Auto Action inactivate process..");

	}
}
