package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_30 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_30.class.getName());

	@Test
	public void TC_Cost_CB_30_ValidateDatePicker() {
		test = extent.startTest("TC_Cost_CB_30_ValidateDatePicker", "Validate Date Filter");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_30_ValidateDatePicker");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		  String[] expectedDateOptions = {"Last 7 Days", "Last 14 Days", "Last 30 Days","Custom Range"};

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

