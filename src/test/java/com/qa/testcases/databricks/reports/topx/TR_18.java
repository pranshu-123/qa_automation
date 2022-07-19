package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_18 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_18.class.getName());
	@Test
	public void TopX_Reports_18_ValidateDateFilter() {
		test = extent.startTest("TopX_Reports_18_ValidateDateFilter", "Validate Date Range in New Report generation page");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_18_ValidateDateFilter");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker datePicker = new DatePicker(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		datePicker.clickOnDatePicker();
		String[] expectedDateOptions = {"Current Day","Last 7 Days", "Last 30 Days", "Last 60 Days", "Last 60 Days","Custom Range"};

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
