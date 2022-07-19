package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_19 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_19.class.getName());
	@Test
	public void TopX_Reports_19_SortByWorkspace() {
		test = extent.startTest("TopX_Reports_19_SortByWorkspace", "Sort Workspaces as per Name");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_19_SortByWorkspace");
		TopXReports topXReports = new TopXReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.validateSortingByWorkspace();
		LOGGER.info("Sorting successful as per workspace name");
		test.log(LogStatus.PASS, "Sorting successful as per workspace name");
	}
}