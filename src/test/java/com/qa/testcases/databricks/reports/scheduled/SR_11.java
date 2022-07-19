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
public class SR_11 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_11.class.getName());
	@Test
	public void SH_Reports_11_FilterTopXReport() {
		test = extent.startTest("SH_Reports_11_FilterTopXReport", "Filter Report as per Top X type");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_11_FilterTopXReport");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		scheduledReports.filterReport("Top X");
		Assert.assertTrue(scheduledReports.fetchReportType().contains("Top X"));
		LOGGER.info("Scheduled reports are displayed as per selected filter");
		test.log(LogStatus.PASS, "Scheduled reports are displayed as per selected filter");
	}
}

