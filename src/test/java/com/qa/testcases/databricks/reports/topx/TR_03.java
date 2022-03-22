package com.qa.testcases.databricks.reports.topx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class TR_03 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_03.class.getName());
	@Test
	public void TopX_Reports_03_VerifyDownloadFunctionality() {
		test = extent.startTest("TopX_Reports_03_VerifyDownloadFunctionality", "Validate Download Functionality");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_03_VerifyDownloadFunctionality");
		List<String> headers = new ArrayList<String>(Arrays.asList("Parameter","Value"));
		TopXReports topXReports = new TopXReports(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		String top = topXReports.createNewReportWithDefaultValues("1");
		LOGGER.info("Top X Report created successfully");
		List<String> values = new ArrayList<String>(Arrays.asList(top,"All Clusters"));
		
		topXReports.validateInputParameters(headers, values);
		
		topXReports.downloadJSON();
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("TopX"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Report into JSON format.");
		LOGGER.info("Successfully downloaded Report into JSON format.");
	}


}
