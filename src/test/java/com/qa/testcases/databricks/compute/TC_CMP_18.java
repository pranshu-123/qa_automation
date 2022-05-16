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
public class TC_CMP_18 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_18.class.getName());

	@Test
	public void TC_Compute_18_verifyUserSearchWithValidParams() {
		test = extent.startTest("TC_Compute_18_verifyUserSearchWithValidParams", "Verify User search functionality with valid parameters");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_18_verifyUserSearchWithValidParams");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		String choice =	compute.filterByUser();
		compute.validateFilteredRows(choice.substring(2));
		test.log(LogStatus.PASS, "Filtered by User");
		LOGGER.info("Filtered by user working as expected");
	}
}
