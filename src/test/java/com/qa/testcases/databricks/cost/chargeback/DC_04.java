package com.qa.testcases.databricks.cost.chargeback;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;

public class DC_04 extends BaseClass{

	@Test
	public void TC_Cost_CB_04_VerifyChargebackOptimizeFunctionality() {
		test = extent.startTest("TC_Cost_CB_04_VerifyChargebackOptimizeFunctionality", "Verify \"Optimize\" Chargeback as per User group");
		test.assignCategory("Cluster / Job");
		Log.startTestCase("TC_Cost_CB_04_VerifyChargebackOptimizeFunctionality");
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
		chargeBackCluster.selectOptimize();
		waitExecuter.sleep(2000);
		String url = driver.getCurrentUrl();
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("compute/dbclusters"));
	}
}
