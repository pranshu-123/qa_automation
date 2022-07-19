package com.qa.testcases.databricks.reports.scheduled;

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
@Marker.EmrReportsScheduled
public class SR_13 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_13.class.getName());
	@Test
	public void SH_Reports_13_ScheduleReportFromTopX() {
		test = extent.startTest("SH_Reports_1_ScheduleEmptyReport", "Schedule report with empty name");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_1_ScheduleEmptyReport");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectSchedule();
		scheduledReports.createNewReportWithDefaultValues("3");
		scheduledReports.selectScheduledTime("Monday");
		scheduledReports.scheduleReport();
		String errorMessage = scheduledReports.fetchErrorMessage();
		Assert.assertTrue(errorMessage.contains("Schedule Name is required"));
		LOGGER.info("Correct Error Messages are displayed");
		test.log(LogStatus.PASS, "Correct error messages are displayed");
	}
}


