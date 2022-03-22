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
public class TR_05 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_05.class.getName());
	@Test
	public void TopX_Reports_05_GenerateNewDefaultReport() {
		test = extent.startTest("TopX_Reports_05_GenerateNewDefaultReport", "Create new Report pertaining to All cluster, User, Real User");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_05_GenerateNewDefaultReport");
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		String top = topXReports.createNewReportWithDefaultValues("1");
		LOGGER.info("Top X Report created successfully");
		List<String> values = new ArrayList<String>(Arrays.asList(top,"All Clusters"));
		
		topXReports.validateInputParameters(headers, values);
		LOGGER.info("Correct Input Parameters are displayed");
		test.log(LogStatus.PASS, "Top X Report created successfully");
	}

}
