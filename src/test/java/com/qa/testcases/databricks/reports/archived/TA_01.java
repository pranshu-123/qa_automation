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
public class TA_01 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_01.class.getName());
	@Test
	public void AR_Reports_01_ValidateArchivedPageElements() {
		test = extent.startTest("AR_Reports_01_ValidateArchivedPageElements", "Validate Archived tab page elements");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_01_ValidateArchivedPageElements");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.validateArchivedPageObjects();
		LOGGER.info("All the page objects are listed");
		test.log(LogStatus.PASS, "Top X Report created successfully");
	}

}

