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

	public class CB_09  extends BaseClass{

		
		private static final Logger LOGGER = Logger.getLogger(CB_09.class.getName());
		@Test
		public void TC_Cost_Budget_09_createNewUpcomingBudget() {
			test = extent.startTest("TC_Cost_Budget_09_createNewUpcomingBudget", "Create new upcoming Budget");
			test.assignCategory("Cost/Budget");
			Log.startTestCase("TC_Cost_Budget_09_createNewUpcomingBudget");
			ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
			WaitExecuter waitExecuter = new WaitExecuter(driver);  
			CostBudget costBudget = new CostBudget(driver);
			chargeBackCluster.navigateToCostTab("Budget");
			LOGGER.info("Navigated to Cost Budget Page");
			waitExecuter.sleep(2000);
			costBudget.createNewBudget("Finance Upcoming Budget","1");
			costBudget.setBudgetActivationDate();
			costBudget.saveBudget();
			LOGGER.info("New Upcoming Budget created successfully.");
			test.log(LogStatus.PASS, "New Upcoming Budget created successfully.");
			costBudget.validateUpcomingBudget("Finance Upcoming Budget");
			LOGGER.info("Newly created upcoming budget is displayed.");
			costBudget.deleteExistingBudget("Finance Upcoming Budget");
			LOGGER.info("New Budget deleted successfully.");
		}
	}
