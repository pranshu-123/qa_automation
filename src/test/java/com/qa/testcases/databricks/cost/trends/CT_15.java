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
public class CT_15 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_15.class.getName());
	
	@Test
	public void TC_Cost_Trends_15_VerifyChargebackFunctionalityForSingleUser() {
		test = extent.startTest("TC_Cost_Trends_15_VerifyChargebackFunctionalityForSingleUser", "Validate Chareback for single User");
		test.assignCategory("Cluster / Job");
		Log.startTestCase("TC_Cost_Trends_15_VerifyChargebackFunctionalityForSingleUser");
		String[] expectedValues = {"root"};
		String[] expectedGraphValues = {"DBU","Cost","Cluster"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		CostTrends costTrends = new CostTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		chargeBackCluster.filterCost("Users");
		costTrends.filterSingleValue("root");
		costTrends.selectChargeback();
		waitExecuter.sleep(2000);
		String url = driver.getCurrentUrl();
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("/cost/chargeback"));
		chargeBackCluster.validatePieChartGraph(expectedGraphValues);
		chargeBackCluster.validateResultSetIsDisplayedWithValues("User");
		chargeBackCluster.validateResultSet(expectedValues);
		
		test.log(LogStatus.PASS, "Navigated to Cost Chargeback page and result set was displayed as per selected user");
		LOGGER.info("Navigated to Cost Chargeback page and result set was displayed as per selected user");
	}
	}