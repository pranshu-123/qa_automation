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
public class CB_11  extends BaseClass{

	
	private static final Logger LOGGER = Logger.getLogger(CB_11.class.getName());
	@Test
	public void TC_Cost_Budget_11_validateInvalidDbuErrorMessage() {
		test = extent.startTest("TC_Cost_Budget_11_validateInvalidDbuErrorMessage", "Invalid Budget DBU range");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_11_validateInvalidDbuErrorMessage");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		List<String> errorMsg = costBudget.createBudgetWithInvalidDBUs("Finance Budget");
		Assert.assertTrue(errorMsg.contains("Value should be between 1 & 10000000000"), "Error Message not displayed");
		LOGGER.info("Invalid DBU error message was displayed.");
		test.log(LogStatus.PASS, "Invalid DBU error message was displayed.");
	}
}