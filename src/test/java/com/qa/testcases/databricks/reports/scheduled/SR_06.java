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
@Marker.EmrReportsScheduled
public class SR_06 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_06.class.getName());
	@Test
	public void SH_Reports_6_DeleteScheduledReport() {
		test = extent.startTest("SH_Reports_6_DeleteScheduledReport", "Delete the scheduled report");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_6_DeleteScheduledReport");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		scheduledReports.deleteScheduledReport();
		LOGGER.info("Scheduled report deleted successfully.");
		test.log(LogStatus.PASS, "Scheduled report deleted successfully.");
	}
}

