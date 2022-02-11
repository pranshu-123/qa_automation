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
public class CT_08 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(CT_08.class.getName());
	@Test
	public void TC_Cost_Trends_08_VerifyClusterGraph() {
		test = extent.startTest("TC_Cost_Trends_08_VerifyClusterGraph", "Validate Graphs for Clusters Group By filter");
		test.assignCategory("Cost/Trends");
		Log.startTestCase("TC_Cost_Trends_08_VerifyClusterGraph");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		CostTrends costTrends = new CostTrends(driver);
		chargeBackCluster.navigateToCostTab("Trends");
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		chargeBackCluster.filterCost("Clusters");
		costTrends.validateGraphFooter("Clusters");
		costTrends.validateGeneratedGraph();
		test.log(LogStatus.PASS, "Cluster graph generated succesfully.");
		LOGGER.info("Cluster graph generated succesfully.");
	}
}