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
public class TC_API_05 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_API_05.class.getName());

	@Test
	public void TC_API_05_createTokenWithExistingName() {
		test = extent.startTest("TC_API_05_createTokenWithExistingName", "Create API token with existing token name");
		test.assignCategory("Api Token");
		Log.startTestCase("TC_API_05_createTokenWithExistingName");
		DbxApiToken dbxApiToken = new DbxApiToken(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		String token = RandomGenerator.generateRandomName();
		dbxApiToken.navigateToApiToken();
		waitExecuter.sleep(1000);
		dbxApiToken.selectCreateNewApiToken();
		dbxApiToken.createNewToken(token);
		String successMsg = dbxApiToken.retrieveSuccessMessage();
		Assert.assertEquals(successMsg,"API Token created successfully","Token not generated");
		waitExecuter.sleep(1000);
		dbxApiToken.selectCreateNewApiToken();
		dbxApiToken.createNewToken(token);
		successMsg = dbxApiToken.retrieveSuccessMessage();
		Assert.assertEquals(successMsg,"API Token created successfully","Token not generated");
		dbxApiToken.deleteToken(token);
		test.log(LogStatus.PASS, "Api Token with the same name generated");
		LOGGER.info("Api Token with the same name generated");
	}

}
