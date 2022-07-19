package com.qa.testcases.databricks.reports.archived;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
@Marker.EmrReportsArchived
public class TA_10 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_10.class.getName());
	@Test
	public void AR_Reports_10_ValidateDateRange() {
		test = extent.startTest("AR_Reports_07_SearchedArchivedReport", "Validate Date Range in Archived page");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_07_SearchedArchivedReport");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker datePicker = new DatePicker(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		datePicker.clickOnDatePicker();

        String[] expectedDateOptions = {"Last 1 Hour","Last 2 Hours", "Last 6 Hours","Last 12 Hours","Today",
                "Yesterday", "Last 7 Days", "Last 30 Days", "This Month", "Last Month", "Custom Range"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
        datePicker.clickOnDatePicker();
        LOGGER.info("Date picker contains all the required date range");
		test.log(LogStatus.PASS, "Date picker contains all the required date range.");
	}

}
