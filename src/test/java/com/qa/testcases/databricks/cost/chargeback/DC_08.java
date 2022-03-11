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
public class DC_08 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_08	.class.getName());

	//@Test
	public void TC_Cost_CB_08_VerifyChargebackResultGroupByCluster() {
		test = extent.startTest("TC_Cost_CB_08_VerifyChargebackResultGroupByCluster", "If \"Cluster\" is selected the table should show all the apps run by the users");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_08_VerifyChargebackResultGroupByCluster");
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
		chargeBackCluster.validatePieChartGraph(expectedGraphValues);
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Cluster");
		test.log(LogStatus.PASS, "Result populated as  per Group By filter");
		LOGGER.info("Result populated as  per Group By filter");
	}
}
