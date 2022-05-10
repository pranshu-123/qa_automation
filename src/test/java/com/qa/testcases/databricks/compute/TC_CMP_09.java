package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_09 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_09.class.getName());

	@Test
	public void TC_Compute_09_verifyComputeTabs() {
		test = extent.startTest("TC_Compute_09_verifyComputeTabs", "Verify \"All\", \"Finished\", \"Running\", \"Inefficient\" tabs");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_09_verifyComputeTabs");
		Compute compute = new Compute(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.validateTabsPresence();
		test.log(LogStatus.PASS, "All the tabs are listed");
		LOGGER.info("All the tabs are listed");
	}
}
