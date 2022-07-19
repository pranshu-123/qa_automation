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
@Marker.EmrReportsArchived
public class TA_09 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_09.class.getName());
	@Test
	public void AR_Reports_09_SortArchivedReportByName() {
		test = extent.startTest("AR_Reports_09_SortArchivedReportByName", "Sort archived report as per name");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_09_SortArchivedReportByName");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		archivedReports.validateSorting("Name");
		LOGGER.info("Archived Reports are sorted as per date");
		test.log(LogStatus.PASS, "Archived Reports are sorted as per date");
	}

}
