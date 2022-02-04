package com.qa.testcases.databricks.cost.chargeback;

import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;

public class DC_05 extends BaseClass{

	@Test
	public void TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace() {
		test = extent.startTest("TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace", "If \"Workspace\" is selected the table should show all the apps as per workspace");
		test.assignCategory("Cluster / Job");
		Log.startTestCase("TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace");
		String[] expectedValues = {"AI_Workspace","ML_Workspace","4730_PG"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Workspace");
		chargeBackCluster.validatePieChartGraph();
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Workspace");
		chargeBackCluster.validateResultSet(expectedValues);
	}
}