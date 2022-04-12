package com.qa.testcases.databricks.reports.topx;

import java.util.LinkedHashMap;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
public class TR_23 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_23.class.getName());
	@Test
	public void TopX_Reports_23_ValidateHighestDiskIOSparkPage() {
		test = extent.startTest("TopX_Reports_23_ValidateHighestDiskIOSparkPage", "Validate Highest Disk I/O Spark Page");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_23_ValidateHighestDiskIOSparkPage");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		String clusterName ="Delta-cluster";
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.selectLast90Days();
		topXReports.createNewReportForCluster("28", clusterName);
		LOGGER.info("Top X Report created successfully");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		LinkedHashMap<String, String> appValues = topXReports.populateSparkDetailValues();
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		sparkDetails.validateSummaryTabValues();
		LOGGER.info("All tabs are present in Spark details page.");
		sparkDetails.validateSparkDetailsPage(appValues);
		LOGGER.info("Correct details are captured in Spark details page.");
		test.log(LogStatus.PASS, "Correct details are captured in Spark details page.");
	}
}
