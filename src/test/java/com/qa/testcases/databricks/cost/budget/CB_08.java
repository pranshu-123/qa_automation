package com.qa.testcases.databricks.cost.budget;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostBudget;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

public class CB_08 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_08.class.getName());
	
	@Test
	public void TC_Cost_Budget_08_deleteCreatedBudget() {
		test = extent.startTest("TC_Cost_Budget_08_deleteCreatedBudget", "Delete existing budget");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_08_deleteCreatedBudget");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		waitExecuter.sleep(2000);
		costBudget.deleteExistingBudget("Test Budget");
		test.log(LogStatus.PASS, "New Budget creaated successfully.");
		LOGGER.info("New Budget creaated successfully.");
	}
}