package com.qa.testcases.databricks.reports.scheduled;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ScheduledReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsScheduled
public class SR_09 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_09.class.getName());
	@Test
	public void SH_Reports_9_SortReportAsPerNextScheduledRun() {
		test = extent.startTest("SH_Reports_9_SortReportAsPerNextScheduledRun", "Sort the scheduled report as per Next Scheduled Run");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_9_SortReportAsPerNextScheduledRun");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		scheduledReports.validateSorting("Next Scheduled Run");
		LOGGER.info("Scheduled reports are sorted as per Next Scheduled Run");
		test.log(LogStatus.PASS, "Scheduled reports are sorted as per Next Scheduled Run");
	}
}
