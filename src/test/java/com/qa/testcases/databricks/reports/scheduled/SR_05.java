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
public class SR_05 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_05.class.getName());
	@Test
	public void SH_Reports_5_EditScheduledReport() {
		test = extent.startTest("SH_Reports_5_EditScheduledReport", "Edit the scheduled report");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_5_EditScheduledReport");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		String editedReportName = scheduledReports.editScheduledReport();
		scheduledReports.updateReport();
		LOGGER.info("Scheduled Report Edited");
		Assert.assertTrue(scheduledReports.fetchReportName().contains(editedReportName),"Report not editable");
		test.log(LogStatus.PASS, "Scheduled report edited successfully.");
	}
}
