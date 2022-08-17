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
public class SR_04 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_04.class.getName());
	@Test
	public void SH_Reports_4_ValidateScheduledInfo() {
		test = extent.startTest("SH_Reports_4_ValidateScheduledInfo", "Validate Scheduled Info of the report");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_4_ValidateScheduledInfo");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameters","Value"));
		List<String> values = new ArrayList<String>(Arrays.asList("Cluster","Top X","Date range"));
		topXReports.navigateToDifferentReportsTab("Scheduled");
		LOGGER.info("Navigated to Reports tab");
		scheduledReports.selectScheduledReport();
		scheduledReports.validateScheduledReportInfo(headers, values);
		LOGGER.info("Scheduled Info page shows correct data");
		test.log(LogStatus.PASS, "Scheduled Info page shows correct data");
	}
}
