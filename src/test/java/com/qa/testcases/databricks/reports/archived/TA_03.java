package com.qa.testcases.databricks.reports.archived;

import java.util.Properties;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.io.ConfigReader;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
public class TA_03 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_03.class.getName());
	@Test
	public void AR_Reports_03_ValidateCopyUrlFunctionality() {
		test = extent.startTest("AR_Reports_03_ValidateCopyUrlFunctionality", "Validate Copy URL functionality from Archived page");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_03_ValidateCopyUrlFunctionality");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		Properties prop = ConfigReader.readBaseConfig();
		String url = prop.getProperty("url");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		 topXReports.createNewReportWithTags("3","Name","ML team");
		LOGGER.info("New Custom Report with tags created");
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		String copiedUrl = archivedReports.copyUrlAndNavigate();
		Assert.assertTrue(copiedUrl.contains(url+"#/reports/topx?report_id="),"URL not copied or is incorrect");		
		LOGGER.info("Url copied successfully and is navigable");
		test.log(LogStatus.PASS, "Url copied successfully and is navigable");
	}
}
