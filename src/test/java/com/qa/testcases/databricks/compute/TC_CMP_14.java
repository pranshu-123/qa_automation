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
public class TC_CMP_14 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_14.class.getName());

	@Test
	public void TC_Compute_14_validateResetFunctionality() {
		test = extent.startTest("TC_Compute_14_validateResetFunctionality", "Verify RESET functionality");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_14_validateResetFunctionality");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.selectStatus("Failed");
		compute.validateJobStatus("Failed");
		LOGGER.info("Only Running jobs are displayed");
		compute.resetStatuses();
		compute.selectStatus("Success");
		compute.validateJobStatus("Success");
		test.log(LogStatus.PASS, "Job status reset functionality working as expected");
		LOGGER.info("Job status reset functionality working as expected");
	}

}
