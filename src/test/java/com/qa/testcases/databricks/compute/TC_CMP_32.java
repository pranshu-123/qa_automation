package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_32 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_32.class.getName());

	@Test
	public void TC_Compute_32_verifyGlobalSearchFunctionality() {
		test = extent.startTest("TC_Compute_32_verifyGlobalSearchFunctionality", "Verify global search functionality");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_32_verifyGlobalSearchFunctionality");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		String appId = "app";
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.performGlobalSearch(appId);
		compute.validateGlobalSearchResult();
		LOGGER.info("Global Search working as expected");
		test.log(LogStatus.PASS, "Global Search working as expected");

	}
}

