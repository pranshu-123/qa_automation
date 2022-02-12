package com.qa.testcases.databricks.cost.budget;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostBudget;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostBudget
public class CB_01 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_01.class.getName());
	
	@Test
	public void TC_Cost_Budget_01_validateBudgetPageElements() {
		test = extent.startTest("TC_Cost_Budget_01_validateBudgetPageElements", "Validate Budget page objects");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_01_validateBudgetPageElements");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		waitExecuter.sleep(2000);
		costBudget.verifyBudgetPageObjects();
		test.log(LogStatus.PASS, "All the elements are listed correctly");
		LOGGER.info("All the elements are listed correctly");
	}
}
