package com.qa.testcases.databricks.reports.scheduled;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ScheduledReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsScheduled
public class SR_14 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_14.class.getName());
	@Test
	public void SH_Reports_14_ValidateScheduledToRunOptions() {
		test = extent.startTest("SH_Reports_14_ValidateScheduledToRunOptions", "Validate Scheduled to Run options");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_14_ValidateScheduledToRunOptions");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectSchedule();
		scheduledReports.createNewReportWithDefaultValues("3");
		List<String> actual = scheduledReports.scheduledTimeOptions();
		String[] expectedDateOptions = {"Daily","Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday","Friday","Every 2 Weeks","Every Month"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(actual.contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
	}

}
