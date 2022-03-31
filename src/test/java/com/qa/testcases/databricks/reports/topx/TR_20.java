package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_20 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_20.class.getName());
	@Test
	public void TopX_Reports_20_SortByAppCount() {
		test = extent.startTest("TopX_Reports_20_SortByAppCount", "Sort Workspaces as per App count");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_20_SortByAppCount");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.validateSortingByAppCount();
		LOGGER.info("Sorting successful as per App Count");
		test.log(LogStatus.PASS, "Sorting successful as per App Count");
	}
}
