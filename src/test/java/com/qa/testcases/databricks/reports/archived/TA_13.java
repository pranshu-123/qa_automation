package com.qa.testcases.databricks.reports.archived;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
public class TA_13 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_13.class.getName());
	@Test
	public void AR_Reports_13_ViewArchivedReport() {
		test = extent.startTest("AR_Reports_13_ViewArchivedReport", "View any report from archived list");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_13_ViewArchivedReport");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver); 
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		archivedReports.selectReport();
		LOGGER.info("Sample Report Selected");
		waitExecuter.sleep(1000);
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		List<String> values = new ArrayList<String>(Arrays.asList("Date Range","Top X"));
		topXReports.validateInputParameters(headers, values);
		LOGGER.info("Report consists valid details");
		test.log(LogStatus.PASS, "Report consists valid details");
	}
}
