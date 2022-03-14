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
public class CB_14  extends BaseClass{

	
	private static final Logger LOGGER = Logger.getLogger(CB_14.class.getName());
	@Test
	public void TC_Cost_Budget_14_createNewBudgetWithAtRisk() {
		test = extent.startTest("TC_Cost_Budget_14_createNewBudgetWithAtRisk", "Create Budget with \"At Risk\"");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_14_createNewBudgetWithAtRisk");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		costBudget.createNewBudget("Finance Budget","5000");
		costBudget.saveBudget();
		LOGGER.info("New Budget created successfully.");
		costBudget.validateCreatedBudget("Finance Budget");
		LOGGER.info("Newly created budget is displayed.");
		Assert.assertTrue(costBudget.fetchBudgetStatus().equals("At Risk"),"Budget Risk Status not displayed correctly");
		LOGGER.info("Budget Risk Status displayed correctly.");
		test.log(LogStatus.PASS, "Budget Risk Status displayed correctly.");
	}
}
