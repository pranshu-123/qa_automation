package com.qa.testcases.databricks.cost.trends;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostTrends;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostTrends
public class CT_03 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_03.class.getName());
	
	@Test
	public void TC_Cost_Trends_03_VerifyChargebackFunctionalityForCostTrends() {
		test = extent.startTest("TC_Cost_Trends_03_VerifyChargebackFunctionalityForCostTrends", "Validate Chargeback functionality for User Trends");
		test.assignCategory("Cost/Trends");
		Log.startTestCase("TC_Cost_Trends_03_VerifyChargebackFunctionalityForCostTrends");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		CostTrends costTrends = new CostTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		chargeBackCluster.filterCost("Users");
		costTrends.selectChargeback();
		waitExecuter.sleep(2000);
		String url = driver.getCurrentUrl();
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("/cost/chargeback"));
		
		test.log(LogStatus.PASS, "Navigated to Cost Chargeback page");
		LOGGER.info("Navigated to Cost Chargeback page");
	}
}