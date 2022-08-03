package com.qa.testcases.databricks.compute;

import java.util.List;
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
public class TC_CMP_40 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_40.class.getName());

	@Test
	public void TC_Compute_40_verifyInefficientTabJobs() {
		test = extent.startTest("TC_Compute_40_verifyInefficientTabJobs", "All inefficient running cluster must populate data");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_40_verifyInefficientTabJobs");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		String[] expectedValues = {"ContendedDriverEvent","DriverOomeEvent","NodeDownsizingEvent","SlowSQLOperatorEvent"};
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		List<String> jobStatus = compute.returnJobStatustes("inefficient");
		for (String expectedValue : expectedValues) {
			Assert.assertTrue(jobStatus.contains(expectedValue),
					"List does not contain: " + expectedValue);
			test.log(LogStatus.PASS, "List contains option: " + expectedValue);
		}
		LOGGER.info("Finished tab shows all finished job statuses");

	}

}
