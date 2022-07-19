package com.qa.testcases.databricks.reports.topx;

import java.util.LinkedHashMap;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_22 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_22.class.getName());
	@Test
	public void TopX_Reports_22_ValidateLongestDurationSparkPage() {
		test = extent.startTest("TopX_Reports_22_ValidateLongestDurationSparkPage", "Validate Longest Duration Spark Page");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_22_ValidateLongestDurationSparkPage");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.selectLast90Days();
		topXReports.createNewReportForCluster("8");
		LOGGER.info("Top X Report created successfully");
		topXReports.navigateToApplicationFilterTabs("Longest Duration");
		LinkedHashMap<String, String> appValues = topXReports.populateSparkDetailValues();
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		String jobDuration = sparkDetails.returnJobDuration();
		sparkDetails.validateSummaryTabValues();
		LOGGER.info("All tabs are present in Spark details page.");
		Assert.assertTrue(appValues.containsValue(jobDuration), "Job Run Duration is either incorrect or not captured in spark page");
		LOGGER.info("Job Run Duration is displayed in Spark details page.");
		sparkDetails.validateSparkDetailsPage(appValues);
		LOGGER.info("Correct details are captured in Spark details page.");
		test.log(LogStatus.PASS, "Correct details are captured in Spark details page.");
	}
}
