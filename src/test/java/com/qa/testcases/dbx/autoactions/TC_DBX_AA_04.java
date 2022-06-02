package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_04 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_04.class.getName());

	@Test
	public void TC_DBX_AA_04_deleteExistingPolicy() {
		test = extent.startTest("TC_DBX_AA_04_deleteExistingPolicy", "Delete existing policy");
		test.assignCategory("DBX Auto Action");
		Log.startTestCase("TC_DBX_AA_04_deleteExistingPolicy");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		dbxAutoAction.navigateToAutoAction();
		String name = dbxAutoAction.fetchPolicyName();
		dbxAutoAction.deleteJob(name);
		logger.info("Policy deleted successfully");
		test.log(LogStatus.PASS, "Verified Auto Action delete process..");

	}
}
