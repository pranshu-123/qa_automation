package com.qa.testcases.databricks.reports.topx;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_17 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_17.class.getName());
	@Test
	public void TopX_Reports_17_ValidateApplicationCount() {
		test = extent.startTest("TopX_Reports_17_ValidateApplicationCount", "Validate App count within Application section");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_17_ValidateApplicationCount");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.selectLast90Days();
		topXReports.createNewReportWithDefaultValues("1");
		LOGGER.info("Top X Report created successfully");
		List<String> resultSetValue = topXReports.calculateAppCount();
		String expectedVale = topXReports.returnAppCount();
		Assert.assertTrue(resultSetValue.contains(expectedVale), "App Count calculated from Result Set does not matches with the value populated in Application section");
		test.log(LogStatus.PASS, "App Count are matching.");
		LOGGER.info("App Count are matching.");
	}
	
}
