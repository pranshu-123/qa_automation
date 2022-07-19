package com.qa.testcases.databricks.reports.topx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class TR_28 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_28.class.getName());
	@Test
	public void TopX_Reports_28_GenerateNewReportForACluster() {
		test = extent.startTest("TopX_Reports_07_GenerateNewReportForACluster", "Create new Report with Custom Date");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_07_GenerateNewReportForACluster");
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.setCurrentAndPastDate(-10);
		date.clickOnCustomDateApplyBtn();
		String[] result = topXReports.createNewReportForCluster("8");
		LOGGER.info("Top X Report created successfully");
		List<String> values = new ArrayList<String>(Arrays.asList(result));
		
		topXReports.validateInputParameters(headers, values);
		LOGGER.info("Correct Input Parameters are displayed");
		test.log(LogStatus.PASS, "Top X Report created successfully");
	}

}
