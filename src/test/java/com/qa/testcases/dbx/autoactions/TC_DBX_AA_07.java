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
public class TC_DBX_AA_07 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_07.class.getName());

	@Test
	public void TC_DBX_AA_07_validateNameAs500Chars() {
		test = extent.startTest("TC_DBX_AA_07_validateNameAs500Chars", "Verify user can set a name which is 500 characters long and save the auto action.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_07_validateNameAs500Chars");
		AutoActions aa = new AutoActions(driver);
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		String policyName = "abcpolicyname0123456789validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500Chars" +
				"validateNameAs500Charsabpolicyname0123456789validateNameAs500CharsvalidateNameAs500Chars" +
				"validateNameAs500CharsvalidateNameAs500Charsabpolicyname0123456789validateNameAs500Chars" +
				"validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500Charsabpolicyname0123456789" +
				"validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500" +
				"Charsabpolicyname0123456789validateNameAs500CharsvalidateNameAs5";
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		waitExecuter.waitUntilPageFullyLoaded();
		test.log(LogStatus.INFO, "Fill new auto action policy details");
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		logger.info("Filled New Auto Action Policy details and clicked on save button");
		Assert.assertFalse(aa.validateAutoActionAdded(policyName), "Newly added Policy name 500 char : " +
				policyName + " able to found.");
		test.log(LogStatus.PASS, "New Auto action policy not added with policy name " +
				"as 500 characters long.");

	}
}
