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
public class CB_02 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CB_02.class.getName());
	
	@Test
	public void TC_Cost_Budget_02_validateActiveSection() {
		test = extent.startTest("TC_Cost_Budget_02_validateActiveSection", "Validate Active section details");
		test.assignCategory("Cost/Budget");
		Log.startTestCase("TC_Cost_Budget_02_validateActiveSection");
		String[] headers = {"Name","Description","Type","Period","Start","End","Scope","Budget Status","Actions"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		CostBudget costBudget = new CostBudget(driver);
		chargeBackCluster.navigateToCostTab("Budget");
		LOGGER.info("Navigated to Cost Budget Page");
		waitExecuter.sleep(2000);
		costBudget.verifyBudgetActiveSectionColumns(headers);
		test.log(LogStatus.PASS, "New Budget creaated successfully.");
		LOGGER.info("New Budget creaated successfully.");
	}
}