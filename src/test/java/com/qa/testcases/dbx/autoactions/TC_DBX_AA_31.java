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
public class TC_DBX_AA_31 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_31.class.getName());

	@Test
	public void TC_DBX_AA_31_validatePostToSlackActionWithUrlAndToken() {
		test = extent.startTest("TC_DBX_AA_31_validatePostToSlackActionWithUrlAndToken", "Verify Post's behavior in Slack without adding any Slack tokens or webhook URLs.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_31_validatePostToSlackActionWithUrlAndToken");
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
		String inputAction = "Post To Slack";
		String postToSlackUrl = "http://somewebhookurl.com";
		String postToSlackToken = "0123456789";
		aa.enterPostToSlackUrl(inputAction, postToSlackUrl, postToSlackToken);
		test.log(LogStatus.INFO,"Clicked action Post To Slack");
		aa.clickOnSaveBtn();
		test.log(LogStatus.INFO,"Clicked on save button");
		Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
				policyName + " not found.");
		test.log(LogStatus.PASS, "Validated  Post to slack by adding valid Slack token and webhook URL" +
				" on New Auto Action Policy page");
	}
}
