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
public class TC_CMP_20 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_20.class.getName());

	@Test
	public void TC_Compute_20_verifyClusterSearchWithValidParams() {
		test = extent.startTest("TC_Compute_20_verifyClusterSearchWithValidParams", "Verify Cluster Type filter search functionality with valid parameters");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_20_verifyClusterSearchWithValidParams");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		String choice =	compute.filterByClusterName();
		compute.validateFilteredRows(choice.substring(2));
		test.log(LogStatus.PASS, "Filtered by User");
		LOGGER.info("Filtered by user working as expected");
	}
}
