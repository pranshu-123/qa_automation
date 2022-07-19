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
@Marker.EmrReportsScheduled
public class SR_01 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_01.class.getName());
	@Test
	public void SH_Reports_1_ScheduleReportFromTopX() {
		test = extent.startTest("SH_Reports_1_ScheduleReportFromTopX", "Schedule a report from Top X page");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_1_ScheduleReportFromTopX");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		List<String> headers = new ArrayList<String>(Arrays.asList("Name","Report","Next Scheduled Run"));
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectSchedule();
		scheduledReports.createNewReportWithDefaultValues("3");
		String name = scheduledReports.setScheduledReportName("TopX Report");
		scheduledReports.selectScheduledTime("Monday");
		scheduledReports.scheduleReport();
		LOGGER.info("Report Scheduled.");
		List<String> values = new ArrayList<String>(Arrays.asList(name,"Top X"));
		topXReports.navigateToDifferentReportsTab("scheduled");
		scheduledReports.validateScheduledReport(headers, values);
		LOGGER.info("Scheduled Report listed on Scheduled tab");
		test.log(LogStatus.PASS, "Scheduled Report listed on Scheduled tab");
	}
}


