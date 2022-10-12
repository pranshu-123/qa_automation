package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_31 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_31.class.getName());
	@Test
	public void TopX_Reports_31_downloadHighestDiskIOReport() {
		test = extent.startTest("TopX_Reports_31_downloadHighestDiskIOReport", "Highest Disk I/O Usage report");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_31_downloadHighestDiskIOReport");
		TopXReports topXReports = new TopXReports(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		topXReports.downloadReport();
		wait.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("download.csv"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Report into JSON format.");
		LOGGER.info("Successfully downloaded Report into JSON format.");
	}
}
