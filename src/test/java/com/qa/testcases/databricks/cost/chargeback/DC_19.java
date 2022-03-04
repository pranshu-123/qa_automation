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
public class DC_19 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_19.class.getName());
	
	@Test
	public void TC_Cost_CB_19_VerifyFilterByCluster() {
		test = extent.startTest("TC_Cost_CB_19_VerifyFilterByCluster", "Verify if the filter is working for selected Cluster");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_19_VerifyFilterByCluster");
		String[] expectedGraphValues = {"DBU","Cost","Cluster"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Cluster");
		chargeBackCluster.filterBy("Clusters");
		chargeBackCluster.validatePieChartGraph(expectedGraphValues);
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Cluster");
		test.log(LogStatus.PASS, "Result table was displayed correctly");
		LOGGER.info("Result table was displayed correctly");
	}

}


