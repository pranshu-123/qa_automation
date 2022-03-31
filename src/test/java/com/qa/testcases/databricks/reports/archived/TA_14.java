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
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
public class TA_14 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_14.class.getName());
	@Test
	public void AR_Reports_14_Copyurl() {
		test = extent.startTest("AR_Reports_14_Copyurl", "Copy Url of any report from archived list");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_14_Copyurl");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver); 
		Properties prop = ConfigReader.readBaseConfig();
		String url = prop.getProperty("url");
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		String copiedUrl = archivedReports.copyUrlAndNavigate();
		waitExecuter.sleep(4000);
		Assert.assertTrue(copiedUrl.contains(url+"#/reports/topx?report_id="),"URL not copied or is incorrect");		
		LOGGER.info("Url copied successfully and is navigable");
		test.log(LogStatus.PASS, "Url copied successfully and is navigable");
	}

}
