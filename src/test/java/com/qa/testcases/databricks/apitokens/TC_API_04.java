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
public class TC_API_04 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_API_04.class.getName());

	@Test
	public void TC_API_04_createEmptyToken() {
		test = extent.startTest("TC_API_04_createEmptyToken", "Create empty API token");
		test.assignCategory("Api Token");
		Log.startTestCase("TC_API_04_createEmptyToken");
		DbxApiToken dbxApiToken = new DbxApiToken(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		dbxApiToken.navigateToApiToken();
		waitExecuter.sleep(1000);
		dbxApiToken.selectCreateNewApiToken();
		String errorMsg = dbxApiToken.createEmptyToken();
		Assert.assertEquals(errorMsg,"Client Id is required.","Error message not generated");
		test.log(LogStatus.PASS, "No new token generated without client id");
		LOGGER.info("No new token generated without client id.");
	}

}
