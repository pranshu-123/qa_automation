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
public class TC_DBX_AA_29 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_29.class.getName());

	@Test
	public void TC_DBX_AA_29_HttpPostActionWithValidtUrl() {
		test = extent.startTest("TC_DBX_AA_29_HttpPostActionWithValidtUrl", "P1-Validate behavior - select the HTTP Post action and save AA with a valid URL.");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_29_HttpPostActionWithValidtUrl");
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
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		String inputAction = "Http Post";
		String httpPostUrl = "http://addhttpposturl.com";
		aa.enterHttpPostUrl(inputAction,httpPostUrl);
		test.log(LogStatus.INFO,"Clicked action HttpPostUrl");
		aa.clickOnSaveBtn();
		test.log(LogStatus.INFO,"Clicked on save button");
		Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
				policyName + " not found.");
		test.log(LogStatus.PASS, "Validated the select HTTP Post action and save AA by adding valide URL");
	}
}
