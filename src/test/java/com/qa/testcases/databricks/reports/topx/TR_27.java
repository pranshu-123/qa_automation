package com.qa.testcases.databricks.reports.topx;

import java.util.List;
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
public class TR_27 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_27.class.getName());
	@Test
	public void TopX_Reports_27_ValidateSparkDetailsPage() {
		test = extent.startTest("TopX_Reports_27_ValidateSparkDetailsPage", "Validate Spark Details Page elements");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_27_ValidateSparkDetailsPage");
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.selectRun();
		date.clickOnDatePicker();
		date.selectLast90Days();
		topXReports.createNewReportForCluster("28");
		LOGGER.info("Top X Report created successfully");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		String[] actualSummaryTabsValues = {"Analysis","Resources","Errors","Configuration","Logs","Tags","Program","Timings"};
		List<String> summaryTabs = sparkDetails.fetchSummaryTabsValues();
		for(String summaryDetails : actualSummaryTabsValues) {
			Assert.assertTrue(summaryTabs.contains(summaryDetails), summaryDetails + " tab not listed in Spark Page");
		}
		LOGGER.info("All tabs are present in Spark details page.");
		sparkDetails.validateAppNavigationTab();
		LOGGER.info("App Navigation tab details are displayed correctly.");
		sparkDetails.validateGanttChartTab();
		LOGGER.info("Gantt Chart tab details are displayed correctly");
		sparkDetails.selectJobToPopulateGanttChart();
		sparkDetails.validateGanttChart();
		LOGGER.info("Gantt charts are diaplyed");
		LOGGER.info("All Spark Details page are displayed correctly");
		test.log(LogStatus.PASS, "All Spark Details page are displayed correctly");
	}
}

