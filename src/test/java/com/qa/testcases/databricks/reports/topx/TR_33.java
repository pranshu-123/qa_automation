package com.qa.testcases.databricks.reports.topx;

import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxReportsTopX
@Marker.EmrReportsTopX
public class TR_33 extends BaseClass

{
	private static final Logger LOGGER = Logger.getLogger(TR_33.class.getName());
	@Test
	public void TopX_Reports_33_validateSparkDetailsForInsightsAndRecommendation() {
		test = extent.startTest("TopX_Reports_33_validateSparkDetailsForInsightsAndRecommendation", "Validate Reports Spark details page for insights and recommendations");
		test.assignCategory("Reports/TopX");
		Log.startTestCase("TopX_Reports_33_validateSparkDetailsForInsightsAndRecommendation");
		TopXReports topXReports = new TopXReports(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		topXReports.navigateToDifferentReportsTab("TopX");
		LOGGER.info("Navigated to Reports tab");
		topXReports.navigateToApplicationFilterTabs("Highest Disk I/O");
		topXReports.openSparkDetailsPage();
		sparkDetails.navigateToSparkPage();
		LOGGER.info("Spark Page opened up");
		sparkDetails.validateInsightsAndRecommendationForSpark();
		LOGGER.info("Spark page displays information on Insights and recommendation");
		test.log(LogStatus.PASS, "Spark page displays information on Insights and recommendation");
	}
}
