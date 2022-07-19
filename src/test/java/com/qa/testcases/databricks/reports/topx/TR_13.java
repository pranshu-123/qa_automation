package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_13 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_13.class.getName());
	@Test
	public void TopX_Reports_13_ValidateHighestDiskIOTableSet() {
		test = extent.startTest("TopX_Reports_13_ValidateHighestDiskIOTableSet", "Validate Highest Disk I/O table result set");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_13_ValidateHighestDiskIOTableSet");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.selectLast90Days();
		topXReports.createNewReportWithDefaultValues("1");
		LOGGER.info("Top X Report created successfully");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		String[] headers = {"User","Real User","Id","Disk I O","CPU","Memory","# Events","# Spark Stages",
				"# Spark Tasks","Cluster"};
		topXReports.validateApplicationHeaders(headers);
		topXReports.validateApplicationDataSetCount();
		LOGGER.info("Data set contains correct headers and associated values");
		test.log(LogStatus.PASS, "Data set contains correct headers and associated values");
		
	}
}


