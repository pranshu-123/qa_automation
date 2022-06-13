package com.qa.testcases.databricks.reports.topx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_09 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_09.class.getName());
	@Test
	public void TopX_Reports_09_GenerateNewReportWithTags() {
		test = extent.startTest("TopX_Reports_09_GenerateNewReportWithTags", "Create new report with Tags");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_09_GenerateNewReportWithTags");
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		ArrayList<String> values = topXReports.createNewReportWithTags("3");
		LOGGER.info("Top X Report created successfully");
		topXReports.validateInputParameters(headers, values);
		LOGGER.info("Correct Input Parameters are displayed");
		test.log(LogStatus.PASS, "Top X Report created successfully");
	}

}


