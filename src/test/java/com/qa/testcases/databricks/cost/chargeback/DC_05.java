package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.testcases.databricks.cost.trends.CT_03;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_05 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_05.class.getName());
	
	@Test
	public void TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace() {
		test = extent.startTest("TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace", "If \"Workspace\" is selected the table should show all the apps as per workspace");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_05_VerifyChargebackResultGroupByWorkspace");
		String[] expectedValues = {"AI_Workspace","ML_Workspace","4730_PG"};
		String[] expectedGraphValues = {"DBU","Cost","Cluster"};
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Workspace");
		chargeBackCluster.validatePieChartGraph(expectedGraphValues);
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Workspace");
		chargeBackCluster.validateResultSet(expectedValues);
		test.log(LogStatus.PASS, "Result populated as  per Group By filter");
		LOGGER.info("Result populated as  per Group By filter");
	}
}