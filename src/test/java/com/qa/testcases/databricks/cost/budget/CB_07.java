package com.qa.testcases.databricks.cost.budget;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostBudget;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;

@Marker.DbxCostBudget
public class CB_07  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_07.class.getName());

	@Test
	public void TC_Cost_Budget_07_editCreatedBudget() {
		test = extent.startTest("TC_Cost_Budget_07_editCreatedBudget", "Create new active Budget");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_07_editCreatedBudget");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		costBudget.createNewBudget("Finance Budget");
		costBudget.saveBudget();
		costBudget.validateCreatedBudget("Finance Budget");
		LOGGER.info("Newly created budget is displayed.");
		String expectedValue = costBudget.editExistingBudget("Finance Budget");
		LOGGER.info("Budget Edited successfully.");
		costBudget.verifyUpdatedScope(expectedValue);
		LOGGER.info("Budget Edited sucessfully.");
	}
}
