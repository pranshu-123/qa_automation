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
public class SR_03 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(SR_03.class.getName());
	@Test
	public void SH_Reports_3_validatescheduledPageObjects() {
		test = extent.startTest("SH_Reports_3_validatescheduledPageObjects", "Validate Scheduled page elements");
		test.assignCategory("Reports/Scheduled");
		Log.startTestCase("SH_Reports_3_validatescheduledPageObjects");
		TopXReports topXReports = new TopXReports(driver);
		ScheduledReports scheduledReports = new ScheduledReports(driver);
		topXReports.navigateToDifferentReportsTab("scheduled");
		scheduledReports.validateScheduledPageObjects();
		LOGGER.info("Scheduled Page Objects are visible");
		test.log(LogStatus.PASS, "Scheduled Page Objects are visible");
	}

}
