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
public class CB_03  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_03.class.getName());
	@Test
	public void TC_Cost_Budget_03_createNewBudget() {
		test = extent.startTest("TC_Cost_Budget_03_createNewBudget", "Create new active Budget");
		test.assignCategory(" Cluster / Job");
		Log.startTestCase("TC_Cost_Budget_03_createNewBudget");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		waitExecuter.sleep(2000);
		costBudget.createNewBudget("Test Budget");
		costBudget.validateCreatedBudget("Test Budget");
	}

}
