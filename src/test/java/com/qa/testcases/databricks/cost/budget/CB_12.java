package com.qa.testcases.databricks.cost.budget;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostBudget;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostBudget
public class CB_12  extends BaseClass{

	
	private static final Logger LOGGER = Logger.getLogger(CB_12.class.getName());
	@Test
	public void TC_Cost_Budget_12_createBudgetWithEmptyFields() {
		test = extent.startTest("TC_Cost_Budget_12_createBudgetWithEmptyFields", "Create Empty Budget");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_12_createBudgetWithEmptyFields");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		List<String> errorMsg = costBudget.createBudgetWithEmptyDetails();
		Assert.assertTrue(errorMsg.contains("Required"), "Error Message not displayed");
		LOGGER.info("Empty Field - *Required error message was displayed.");
		test.log(LogStatus.PASS, "Empty Field - *Required error message was displayed.");
	}
}