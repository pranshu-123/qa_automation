package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_03 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_03.class.getName());

	@Test
	public void TC_DBX_AA_03_editExistingPolicy() {
		test = extent.startTest("TC_DBX_AA_03_editExistingPolicy", "Edit existing policy");
		test.assignCategory("DBX Auto Action");
		Log.startTestCase("TC_DBX_AA_03_editExistingPolicy");
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
		dbxAutoAction.editAndValidateJob(policyName);
		logger.info("Policy editing in progress now..");
		test.log(LogStatus.PASS, "Verified Auto Action edit process..");

	}
}
