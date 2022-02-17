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
public class DC_09 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_09.class.getName());
	
	@Test
	public void TC_Cost_CB_09_VerifyChargebackGraphGroupByCluster() {
		test = extent.startTest("TC_Cost_CB_09_VerifyChargebackGraphGroupByCluster", "\"Cluster\" Graph validation");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_09_VerifyChargebackGraphGroupByCluster");
		String[] expectedGraphValues = {"DBU","Cost","Cluster"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		LOGGER.info("Navigated to Chareback page");
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Cluster");
		chargeBackCluster.validatePieChartGraph(expectedGraphValues);
		chargeBackCluster.validateGeneratedPieChartValues();
		test.log(LogStatus.PASS, "Graph populated as  per Group By filter");
		LOGGER.info("Graph populated as  per Group By filter");
		
	}
}