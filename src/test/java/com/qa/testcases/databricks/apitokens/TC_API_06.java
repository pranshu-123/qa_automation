package com.qa.testcases.databricks.apitokens;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.apitoken.DbxApiToken;
import com.qa.utils.Log;
import com.qa.utils.RandomGenerator;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxApiToken
public class TC_API_06 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_API_06.class.getName());

	@Test
	public void TC_API_06_sortApiTokenBasisClinetId() {
		test = extent.startTest("TC_API_06_sortApiTokenBasisClinetId", "Sort Api tokens as per client id");
		test.assignCategory("Api Token");
		Log.startTestCase("TC_API_06_sortApiTokenBasisClinetId");
		DbxApiToken dbxApiToken = new DbxApiToken(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		String token = RandomGenerator.generateRandomName();
		dbxApiToken.navigateToApiToken();
		waitExecuter.sleep(1000);
		dbxApiToken.selectCreateNewApiToken();
		dbxApiToken.createNewToken(token);
		String successMsg = dbxApiToken.retrieveSuccessMessage();
		Assert.assertEquals(successMsg,"API Token created successfully","Token not generated");
		dbxApiToken.validateSorting("Client Id");
		dbxApiToken.deleteToken(token);
		test.log(LogStatus.PASS, "Generate New Api Token test passed.");
		LOGGER.info("Generate New Api Token test passed.");
	}

}
