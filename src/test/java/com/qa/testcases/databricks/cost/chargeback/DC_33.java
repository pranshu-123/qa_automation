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

@Marker.DbxCostChargeback
public class DC_33 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_33.class.getName());

	@Test
	public void TC_Cost_CB_33_ValidateSortingFeature() {
		test = extent.startTest("TC_Cost_CB_33_ValidateSortingFeature", "Validate Sorting feature");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_33_ValidateSortingFeature");
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
		chargeBackCluster.validateSorting("up");
		chargeBackCluster.validateSorting("down");
	}
}

