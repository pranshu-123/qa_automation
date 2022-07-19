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
public class TA_04 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_04.class.getName());
	@Test
	public void AR_Reports_04_ValidateArchivedPageElements() {
		test = extent.startTest("AR_Reports_01_ValidateArchivedPageElements", "Create new report from Archived page");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_01_ValidateArchivedPageElements");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectCreateReports();
		LOGGER.info("New Report button selected from archived page");
		archivedReports.createNewReportWithDefaultValues("5");
		LOGGER.info("New Report generated from archived page");
		Assert.assertTrue(archivedReports.returnReportStatus().equals("SUCCESS"), "Report not generated from archived page");
		test.log(LogStatus.PASS, "Report generated from archived page");
	}
}
