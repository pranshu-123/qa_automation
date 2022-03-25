package com.qa.testcases.databricks.reports.archived;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
public class TA_08 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_08.class.getName());
	@Test
	public void AR_Reports_08_SortArchivedReportAsPerDate() {
		test = extent.startTest("AR_Reports_14_Copyurl", "Sort archived report as per date");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_14_Copyurl");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		archivedReports.validateSorting("Created");
		LOGGER.info("Archived Reports are sorted as per date");
		test.log(LogStatus.PASS, "Archived Reports are sorted as per date");
	}

}
