package com.qa.testcases.databricks.compute;

import java.util.LinkedHashMap;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.scripts.databricks.reports.SparkDetails;
import com.qa.scripts.databricks.reports.TopXReports;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCompute
public class TC_CMP_43 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_43.class.getName());

	@Test
	public void TC_Compute_43_verifySparkDetailsForFailedJobs() {
		test = extent.startTest("TC_Compute_43_verifySparkDetailsForFailedJobs", "Verify Spark Details tabs for failed job");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_43_verifySparkDetailsForFailedJobs");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		DatePicker date = new DatePicker(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		TopXReports topXReports = new TopXReports(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		LOGGER.info("Last 90 days selected");
		compute.selectStatus("Failed");
		LinkedHashMap<String, String> appValues = topXReports.populateSparkDetailValues();
		compute.selectSpark();
		LOGGER.info("All tabs are present in Spark details page.");
		sparkDetails.validateUserAndClusterSparkDetails(appValues);
		sparkDetails.validateSummaryTabValues();
		LOGGER.info("Correct details are captured in Spark details page.");
		test.log(LogStatus.PASS, "Correct details are captured in Spark details page.");
	}

}

