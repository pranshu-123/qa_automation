package com.qa.testcases.dbx.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.DbxAutoAction
public class TC_DBX_AA_32 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_32.class.getName());

	@Test
	public void TC_DBX_AA_32_verifyAATrigerringEvent() {
		test = extent.startTest("TC_DBX_AA_32_verifyAATrigerringEvent", "Verify that UI to be able to specify which AA triggered an event for a given application.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_32_verifyAATrigerringEvent");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		AutoActions aa = new AutoActions(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(0, 10);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		aa.clickOnSaveBtn();
		aa.getTriggeredAAs();
		 test.log(LogStatus.PASS, "Validate all triggered AAs in application badge.");
	}
}
