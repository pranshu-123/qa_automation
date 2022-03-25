package com.qa.testcases.databricks.reports.topx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_10 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_10.class.getName());
	@Test
	public void TopX_Reports_10_GenerateReportWithInvalidCount() {
		test = extent.startTest("TopX_Reports_10_GenerateReportWithInvalidCount", "Create new report with invaid Top X count");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_10_GenerateReportWithInvalidCount");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		ArrayList<String> errorMessage = topXReports.createNewReportWithInvalidTopXCount("-1");
		Assert.assertTrue(errorMessage.contains("Please make sure all inputs are valid."));
		Assert.assertTrue(errorMessage.contains("Top X should be a number"));
		LOGGER.info("Correct Error Messages are displayed");
		test.log(LogStatus.PASS, "Correct error messages are displayed");
	}
}
