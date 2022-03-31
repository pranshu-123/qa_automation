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
public class CT_10 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_10.class.getName());
	
	@Test
	public void TC_Cost_Trends_10_VerifyOptimizeForCostClusterTrends() {
		test = extent.startTest("TC_Cost_Trends_10_VerifyOptimizeForCostClusterTrends", "Validate Optimize functionality for cluster Trends");
		test.assignCategory("Cost/Trends");
		Log.startTestCase("TC_Cost_Trends_10_VerifyOptimizeForCostClusterTrends");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		CostTrends costTrends = new CostTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		costTrends.filterBy("Cluster");
		waitExecuter.sleep(2000);
		costTrends.selectOptimize("Cluster");
		waitExecuter.sleep(4000);
		String url = driver.getCurrentUrl();
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("compute/dbclusters"));
		
		test.log(LogStatus.PASS, "Navigated to Cost Chargeback page");
		LOGGER.info("Navigated to Cost Chargeback page");
	}

}
