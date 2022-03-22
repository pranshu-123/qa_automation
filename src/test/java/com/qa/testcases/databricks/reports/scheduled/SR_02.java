package com.qa.testcases.databricks.reports.scheduled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.ScheduledReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsScheduled
public class SR_02 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_02.class.getName());
	@Test
	public void SH_Reports_2_ScheduleReportFromArchivedPage() {
		test = extent.startTest("SH_Reports_2_ScheduleReportFromArchivedPage", "Schedule a report from Archived page.");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_2_ScheduleReportFromArchivedPage");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		List<String> headers = new ArrayList<String>(Arrays.asList("Name","Report","Next Scheduled Run"));
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectScheduledReport();
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
