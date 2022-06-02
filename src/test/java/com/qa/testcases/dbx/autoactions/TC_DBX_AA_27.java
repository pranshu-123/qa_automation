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
public class TC_DBX_AA_27 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_27.class.getName());

	@Test
	public void TC_DBX_AA_26_validateSendEmailFunctionalityForMultipleRecipients() {
		test = extent.startTest("TC_DBX_AA_26_validateSendEmailFunctionalityForMultipleRecipients", "Select one auto action (send email) and verify the behaviour when one/more recipient(s) is(are) selected. ");
		test.assignCategory(" Alerts ");
		Log.startTestCase("TC_DBX_AA_26_validateSendEmailFunctionalityForMultipleRecipients");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		String emailId1 = "pagarwal@unraveldata.com";
		String emailId2 = "abc2@abc.com";
		dbxAutoAction.navigateToAutoAction();
		test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
		logger.info("Verified Alerts Tab is clicked.");
		dbxAutoAction.navigateToNewTemplate();
		dbxAutoAction.selectLongRunningJobs();
		test.log(LogStatus.INFO, "clicked on new auto action button");
		waitExecuter.waitUntilPageFullyLoaded();
		String policyName = "Policy_"+driver.getWindowHandle().substring(10, 20);
		dbxAutoAction.enterNewAutoActionPolicyDetails(policyName, "3");
		dbxAutoAction.selectActionType("Email");
		dbxAutoAction.sendEmail(emailId1);
		dbxAutoAction.sendEmail(emailId2);
		dbxAutoAction.saveAA();
		test.log(LogStatus.INFO, "Click on save button");
		dbxAutoAction.validateResultSet(policyName);
		dbxAutoAction.deleteJob(policyName);
		test.log(LogStatus.PASS, "Send Email functionality validated for multiple recipients");
	}
}
