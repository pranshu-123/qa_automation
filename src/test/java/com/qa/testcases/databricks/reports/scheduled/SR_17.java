package com.qa.testcases.databricks.reports.scheduled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ScheduledReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsScheduled
public class SR_17 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_17.class.getName());
	@Test
	public void SH_Reports_17_ScheduleBiMonthlyReportFromTopX() {
		test = extent.startTest("SH_Reports_17_ScheduleBiMonthlyReportFromTopX", "Schedule a bi-monthly report from Top X page");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_17_ScheduleBiMonthlyReportFromTopX");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		List<String> headers = new ArrayList<String>(Arrays.asList("Name","Report","Next Scheduled Run"));
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectSchedule();
		scheduledReports.createNewReportWithDefaultValues("3");
		String name = scheduledReports.setScheduledReportName("TopX Report");
		scheduledReports.selectScheduledTime("Every 2 Weeks");
		scheduledReports.scheduleReport();
		LOGGER.info("Report Scheduled.");
		List<String> values = new ArrayList<String>(Arrays.asList(name,"Top X"));
		topXReports.navigateToDifferentReportsTab("scheduled");
		scheduledReports.validateScheduledReport(headers, values);
		LOGGER.info("Scheduled Report listed on Scheduled tab");
		test.log(LogStatus.PASS, "Scheduled Report listed on Scheduled tab");
	}
}


