package com.qa.testcases.databricks.cost.chargeback;

import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;

public class DC_02 extends BaseClass{

	@Test
	public void TC_Cost_CB_02_VerifyChargebackResultGroupByUser() {
		test = extent.startTest("TC_Cost_CB_02_VerifyChargebackResultGroupByUser", "If \"user\" is selected the table should show all the apps run by the users");
		test.assignCategory("Cluster / Job");
		Log.startTestCase("TC_Cost_CB_01_VerifyGroupByFilter");
		String[] expectedValues = {"root","smananghat@unraveldata.com"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.clickOnGroupByDropDown();
		jobs.selectGroupByFilterValue("User");
		chargeBackCluster.validatePieChartGraph();
		chargeBackCluster.validateResultSetIsDisplayedWithValues();
		chargeBackCluster.validateResultSet(expectedValues);

	}

}
