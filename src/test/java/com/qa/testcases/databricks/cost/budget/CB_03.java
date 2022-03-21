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
public class CB_03  extends BaseClass{

	
	private static final Logger LOGGER = Logger.getLogger(CB_03.class.getName());
	@Test
	public void TC_Cost_Budget_03_createNewBudget() {
		test = extent.startTest("TC_Cost_Budget_03_createNewBudget", "Create new active Budget");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_03_createNewBudget");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		costBudget.createNewBudget("Finance Budget","1");
		costBudget.saveBudget();
		LOGGER.info("New Budget created successfully.");
		costBudget.validateCreatedBudget("Finance Budget");
		test.log(LogStatus.PASS, "Newly created budget is displayed.");
		LOGGER.info("Newly created budget is displayed.");
	}

}
