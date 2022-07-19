package com.qa.testcases.databricks.reports.topx;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_11 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_11.class.getName());
	@Test
	public void TopX_Reports_11_GenerateEmptyReport() {
		test = extent.startTest("TopX_Reports_11_GenerateEmptyReport", "Create empty report");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_11_GenerateEmptyReport");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		ArrayList<String> errorMessage = topXReports.createEmptyReport();
		Assert.assertTrue(errorMessage.contains("Please make sure all inputs are valid."));
		Assert.assertTrue(errorMessage.contains("Top X is required."));
		LOGGER.info("Correct Error Messages are displayed");
		test.log(LogStatus.PASS, "Correct error messages are displayed");
	}
}
