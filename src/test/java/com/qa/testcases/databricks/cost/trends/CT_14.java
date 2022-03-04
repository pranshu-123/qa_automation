package com.qa.testcases.databricks.cost.trends;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.scripts.databricks.cost.CostTrends;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostTrends
public class CT_14 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_14.class.getName());
	
	@Test
	public void TC_Cost_Trends_14_ValidateGraphForSingleUser() {
		test = extent.startTest("TC_Cost_Trends_14_ValidateGraphForSingleUser", "Validate Graph for single User");
		test.assignCategory("Cost/Trends");
		Log.startTestCase("TC_Cost_Trends_14_ValidateGraphForSingleUser");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		CostTrends costTrends = new CostTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);

		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		costTrends.filterBy("Users");
		costTrends.filterSingleValue("root");
		costTrends.validateGraphFooter("root");
		costTrends.validateGeneratedGraph();
		test.log(LogStatus.PASS, "Job Run graph generated succesfully.");
		LOGGER.info("Job Run graph generated succesfully.");
	}

}
