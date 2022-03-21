package com.qa.testcases.databricks.cost.chargeback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
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
public class DC_32 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_32.class.getName());

	@Test
	public void TC_Cost_CB_32_VerifyTotalJobRunCountGeneratedGraph() {
		test = extent.startTest("TC_Cost_CB_32_VerifyTotalJobRunCountGeneratedGraph", "Add up the JobRun of the result and verify the total JobRun is populated correctly on generated graph ");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_32_VerifyTotalJobRunCountGeneratedGraph");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Workspace");
		waitExecuter.sleep(2000);
		chargeBackCluster.selectChargebackType("JobRun");
		waitExecuter.sleep(2000);
		List<String> resultSetValue = chargeBackCluster.calculateClusterSumFromResultSet();
		String expectedVale = chargeBackCluster.fetchJobRunsValueFromGraph();
		Assert.assertTrue(resultSetValue.contains(expectedVale), "JobRun value calculated from Result Set does not matches with the value populated in DBU Graph");
		test.log(LogStatus.PASS, "JobRun values are matching.");
		LOGGER.info("JobRun values are matching.");
	}

}

