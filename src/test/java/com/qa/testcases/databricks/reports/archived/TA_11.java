package com.qa.testcases.databricks.reports.archived;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.ArchivedReports;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsArchived
@Marker.EmrReportsArchived
public class TA_11 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TA_11.class.getName());
	@Test
	public void AR_Reports_11_DownloadArchivedReport() {
		test = extent.startTest("AR_Reports_11_DownloadArchivedReport", "Download archived report");
		test.assignCategory("Reports/Archived");
		Log.startTestCase("AR_Reports_11_DownloadArchivedReport");
		TopXReports topXReports = new TopXReports(driver);
		ArchivedReports archivedReports = new ArchivedReports(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver); 
		topXReports.navigateToDifferentReportsTab("Archived");
		LOGGER.info("Navigated to Reports tab");
		archivedReports.selectReportList();
		LOGGER.info("Report list selected.");
		archivedReports.downloadArchivedReport();
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("topx"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Report into JSON format.");
		LOGGER.info("Successfully downloaded Report into JSON format.");
	}
}