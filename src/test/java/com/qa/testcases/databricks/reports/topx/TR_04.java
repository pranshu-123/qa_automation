package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_04 extends BaseClass
{
	private static final Logger LOGGER = Logger.getLogger(TR_04.class.getName());
	@Test
	public void TopX_Reports_04_VerifyCopyURLFunctionality() {
		test = extent.startTest("TopX_Reports_04_VerifyCopyURLFunctionality", "Validate Copy URL functionality");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_04_VerifyCopyURLFunctionality");
		TopXReports topXReports = new TopXReports(driver);
		Properties prop = ConfigReader.readBaseConfig();
		String url = prop.getProperty("url");
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		topXReports.createNewReportWithDefaultValues("1");
		LOGGER.info("Top X Report created successfully");
		String copiedUrl = topXReports.copyUrlAndNavigate();
		Assert.assertTrue(copiedUrl.contains(url+"#/reports/topx?report_id="),"URL not copied or is incorrect");		
		LOGGER.info("Url copied successfully and is navigable");
		test.log(LogStatus.PASS, "Url copied successfully and is navigable");
	}
}
