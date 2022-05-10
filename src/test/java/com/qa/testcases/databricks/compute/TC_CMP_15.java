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
public class TC_CMP_15 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_15.class.getName());

	@Test
	public void TC_Compute_14_verifyCustomDateFunctionality() {
		test = extent.startTest("TC_Compute_14_verifyCustomDateFunctionality", "Verify Custom Date functionality");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_14_verifyCustomDateFunctionality");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		datePicker.clickOnDatePicker();
		String[] expectedDateOptions = {"Last 1 Hour","Last 2 Hours","Last 6 Hours","Last 12 Hours",
				"Today","Yesterday","Last 7 Days", "Last 90 Days", "Last 30 Days",
				"This Month","Last Month","Custom Range"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
        datePicker.clickOnDatePicker();
        LOGGER.info("Cost Chargeback: Verify all the date pickers.");
        test.log(LogStatus.PASS, "Verified all the date pickers on Cost Chargeback.");

		
	}
}
