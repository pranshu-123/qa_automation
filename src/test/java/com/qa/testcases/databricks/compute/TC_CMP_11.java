package com.qa.testcases.databricks.compute;

import java.util.LinkedHashMap;
import java.util.List;
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
public class TC_CMP_11 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CMP_11.class.getName());

	@Test
	public void TC_Compute_11_verifySparkDetails() {
		test = extent.startTest("TC_Compute_11_verifySparkDetails", "Verify SPARK button are operational");
		test.assignCategory("Compute");
		Log.startTestCase("TC_Compute_11_verifySparkDetails");
		Compute compute = new Compute(driver);
		WaitExecuter wait = new WaitExecuter(driver);
		TopXReports topXReports = new TopXReports(driver);
		DatePicker date = new DatePicker(driver);
		SparkDetails sparkDetails = new SparkDetails(driver);
		compute.navigateToCompute();
		wait.sleep(1000);
		date.clickOnDatePicker();
		date.selectLast90Days();
		compute.selectStatus("Success");
		LinkedHashMap<String, String> appValues = topXReports.populateSparkDetailValues();
		compute.selectSpark();
		sparkDetails.validateSummaryTabValues();
		LOGGER.info("All tabs are present in Spark details page.");
		sparkDetails.validateUserAndClusterSparkDetails(appValues);
		LOGGER.info("Correct details are captured in Spark details page.");
		test.log(LogStatus.PASS, "Correct details are captured in Spark details page.");
	}

}
