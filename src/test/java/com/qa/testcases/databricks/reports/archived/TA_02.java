package com.qa.testcases.databricks.reports.archived;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
@Marker.EmrReportsArchived
public class TA_02 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_02.class.getName());
	@Test
	public void AR_Reports_02_ValidateLastArchivedReport() {
		test = extent.startTest("AR_Reports_02_ValidateLastArchivedReport", "Validate last generated report from Archived tab");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_02_ValidateLastArchivedReport");
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		String top = topXReports.createNewReportWithDefaultValues("5");
		LOGGER.info("New Custom Report with tags created");
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectViewReport();
		LOGGER.info("Last Archived Report Selected");
		Assert.assertTrue(archivedReports.returnLatestReportStatus().equals("LATEST SUCCESSFUL TOP X REPORT"), "Latest successful report not shown");
		List<String> values = new ArrayList<String>(Arrays.asList(top,"All Clusters","Date Range","Top X"));

		topXReports.validateInputParameters(headers, values);
		LOGGER.info("Correct Input Parameters are displayed");

		test.log(LogStatus.PASS, "Latest successful report not shown");
	}

}

