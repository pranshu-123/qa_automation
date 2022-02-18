package com.qa.testcases.databricks.cost.budget;

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
public class CB_05 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_05.class.getName());

	@Test
	public void TC_Cost_Budget_05_validateNewBudgetChargeback() {
		test = extent.startTest("TC_Cost_Budget_05_validateNewBudgetChargeback", "Validate New Budget Chargeback");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_05_validateNewBudgetChargeback");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		costBudget.createNewBudget("Test Budget");
		costBudget.saveBudget();
		LOGGER.info("New Budget created successfully.");
		test.log(LogStatus.PASS, "New Budget created successfully.");
		costBudget.validateCreatedBudget("Test Budget");
		LOGGER.info("Newly created budget is displayed.");
		costBudget.selectActionButton("Chargeback");
		waitExecuter.sleep(2000);
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("/cost/chargeback"));
		LOGGER.info("Navigated to cost chargeback page");
		test.log(LogStatus.PASS, "Navigated to cost trends page");	
		chargeBackCluster.navigateToCostTab("Budget");
	}
}
