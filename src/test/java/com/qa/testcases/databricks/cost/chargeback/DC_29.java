package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_29 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_29.class.getName());

	@Test
	public void TC_Cost_CB_29_VerifyTotalCostOnGeneratedGraph() {
		test = extent.startTest("TC_Cost_CB_29_VerifyTotalCostOnGeneratedGraph", "Add up the total Cost of the result and verify the total Cost is populated correctly on generated graph");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_29_VerifyTotalCostOnGeneratedGraph");
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
		waitExecuter.sleep(2000);
		String resultSetValue = chargeBackCluster.calculateTotalCostFromResultSet();
		String expectedVale = chargeBackCluster.fetchTotalCostFromGraph();
		Assert.assertTrue(resultSetValue.equals(expectedVale), "DBU value calculated from Result Set does not matches with the value populated in DBU Graph");
		test.log(LogStatus.PASS, "DBU values are matching.");
		LOGGER.info("DBU values are matching.");
	}

}
