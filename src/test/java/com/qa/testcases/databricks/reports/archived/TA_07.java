package com.qa.testcases.databricks.reports.archived;

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
public class TA_07 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_07.class.getName());
	@Test
	public void AR_Reports_07_SearchedArchivedReport() {
		test = extent.startTest("AR_Reports_07_SearchedArchivedReport", "Search archived report");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_07_SearchedArchivedReport");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		String reportName = archivedReports.fetchReportName();
		String searchedReportName = archivedReports.searchReport(reportName);
		Assert.assertEquals(reportName, searchedReportName,"Reports does not match");
		LOGGER.info("Search functionality validated.");
		test.log(LogStatus.PASS, "Search functionality validated.");
	}

}
