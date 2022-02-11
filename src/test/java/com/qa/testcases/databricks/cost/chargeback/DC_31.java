package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_31  extends BaseClass{
	

	private static final Logger LOGGER = Logger.getLogger(DC_31.class.getName());

	@Test
	public void TC_Cost_CB_31_VerifyJobRunChargeback() {
		test = extent.startTest("TC_Cost_CB_31_VerifyJobRunChargeback", "Validate JobRun chargeback type");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_31_VerifyJobRunChargeback");
		String[] expectedValues = {"DBU","Cost","JobRuns"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("User");
		chargeBackCluster.validatePieChartGraph(expectedValues);
		chargeBackCluster.validateResultSetIsDisplayedWithValues("User");
		test.log(LogStatus.PASS, "Job Run graph generated succesfully.");
		LOGGER.info("Job Run graph generated succesfully.");
	}
}
