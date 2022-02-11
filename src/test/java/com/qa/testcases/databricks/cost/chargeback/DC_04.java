package com.qa.testcases.databricks.cost.chargeback;

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
public class DC_04 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_04.class.getName());
	
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

		test.log(LogStatus.PASS, "Navigated to Cluster page");
		LOGGER.info("Navigated to Cluster page");
	}
}
