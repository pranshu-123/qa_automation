package com.qa.testcases.databricks.compute;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_37 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_37.class.getName());

	@Test
	public void TC_Compute_37_verifyColumnSettings() {
		test = extent.startTest("TC_Compute_37_verifyColumnSettings", "Column count should change as per the selection/non-selection");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_37_verifyColumnSettings");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.selectSettings();
		compute.verifyColumnHeaders("Status");
		LOGGER.info("Headers are updated as per Settings update");
		test.log(LogStatus.PASS, "Headers are updated as per Settings update");
	}
}
