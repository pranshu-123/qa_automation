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
public class TC_DBX_AA_08 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_08.class.getName());

	@Test
	public void TC_DBX_AA_08_validatePolicyWithEmptyName() {
		test = extent.startTest("validateDescriptionNameAsSpecialChars", "Verify user can save auto action without entering any character in the text box and save the auto action.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("validateDescriptionNameAsSpecialChars");
		AutoActions aa = new AutoActions(driver);
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "policy";
		dbxAutoAction.clearPreFilledName();
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		logger.info("Filled New Auto Action Policy details and clicked on save button");
		Assert.assertFalse(aa.validateAutoActionAdded(policyName), "Newly added Policy name and desc : " +
				policyName + " able to found.");
		test.log(LogStatus.PASS, "New Auto action policy added with policy name and description");

	}
}
