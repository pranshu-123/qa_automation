package com.qa.testcases.databricks.reports.topx;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_34 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_34.class.getName());
	@Test
	public void TopX_Reports_34_validateResourcesGraph() {
		test = extent.startTest("TopX_Reports_34_validateResourcesGraph", "Validate Reports Spark details Resources tab against all the resources graphs ");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_34_validateResourcesGraph");
		TopXReports topXReports = new TopXReports(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		LOGGER.info("Spark Page opened up");
		sparkDetails.navigateToAppMenu("Resources");
		sparkDetails.validateResourcesGraph();
		LOGGER.info("Spark page displays information on resources graph");
		test.log(LogStatus.PASS, "Spark page displays information on resources graph");
	}
}
