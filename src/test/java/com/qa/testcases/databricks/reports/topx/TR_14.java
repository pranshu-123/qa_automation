package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_14 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_14.class.getName());
	@Test
	public void TopX_Reports_14_ValidateHighestClusterTableSet() {
		test = extent.startTest("TopX_Reports_14_ValidateHighestClusterTableSet", "Validate Highest Cluster Usage table result set");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_14_ValidateHighestClusterTableSet");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.navigateToApplicationFilterTabs("Highest Cluster Usage");
		String[] headers = {"User","Real User","Id","Cluster Usage Duration","CPU","Memory","# Events","# Spark Stages",
				"# Spark Tasks","Cluster"};
		topXReports.validateApplicationHeaders(headers);
		topXReports.validateApplicationDataSetCount();
		LOGGER.info("Data set contains correct headers and associated values");
		test.log(LogStatus.PASS, "Data set contains correct headers and associated values");
		
	}
}
