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
public class SR_12 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_12.class.getName());
	@Test
	public void SH_Reports_12_FilterAllReport() {
		test = extent.startTest("SH_Reports_12_FilterAllReport", "Filter Report as All type");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_12_FilterAllReport");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		scheduledReports.filterReport("All");
		Assert.assertTrue(scheduledReports.fetchReportType().contains("Top X"));
		LOGGER.info("Scheduled reports are displayed as per selected filter");
		test.log(LogStatus.PASS, "Scheduled reports are displayed as per selected filter");
	}
}

