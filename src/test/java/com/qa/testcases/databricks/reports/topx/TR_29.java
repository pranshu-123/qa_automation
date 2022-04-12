package com.qa.testcases.databricks.reports.topx;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_29 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_29.class.getName());
	@Test
	public void TopX_Reports_29_loadDiagnostics() {
		test = extent.startTest("TopX_Reports_29_loadDiagnostics", "Load Diagnostics Test");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_29_loadDiagnostics");
		TopXReports topXReports = new TopXReports(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		LOGGER.info("Spark Page opened up");
		sparkDetails.loadDiagnostics();
		LOGGER.info("Loading Diagnostics");
		List<String> values = sparkDetails.fetchDiagnosticHead();
		LOGGER.info("Fetching Diagnostics Values");
		Assert.assertTrue(values.contains("Id"), "Diagnostics headers not displayed");
		Assert.assertTrue(values.contains("Time"), "Diagnostics headers not displayed");
		Assert.assertTrue(values.contains("Message"), "Diagnostics headers not displayed");
		sparkDetails.validateDiagnosticsValue();
		LOGGER.info("Diagnostics Values are displayed");
		sparkDetails.cancelDiagnostics();
		test.log(LogStatus.PASS, "Diagnostics feature is displayed");
	}
}
