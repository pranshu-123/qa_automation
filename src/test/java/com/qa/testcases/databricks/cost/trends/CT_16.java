package com.qa.testcases.databricks.cost.trends;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostTrends;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostTrends
public class CT_16 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_16.class.getName());
	
	@Test
	public void TC_Cost_Trends_16_VerifyOptimizeFunctionalityForSingleUser() {
		test = extent.startTest("TC_Cost_Trends_16_VerifyOptimizeFunctionalityForSingleUser", "Validate Optimize for single User");
		test.assignCategory("Cost/Trends");
		Log.startTestCase("TC_Cost_Trends_16_VerifyOptimizeFunctionalityForSingleUser");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		CostTrends costTrends = new CostTrends(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		costTrends.filterBy("Users");
		costTrends.filterSingleValue("root");
		chargeBackCluster.selectOptimize();
		waitExecuter.sleep(2000);
		String url = driver.getCurrentUrl();
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("compute/dbclusters"));
		test.log(LogStatus.PASS, "Compute page succesfully opened up");
		LOGGER.info("Compute page succesfully opened up");
	}

}
