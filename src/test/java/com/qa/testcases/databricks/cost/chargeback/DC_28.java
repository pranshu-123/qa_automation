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
public class DC_28 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_28.class.getName());

	@Test
	public void TC_Cost_CB_28_VerifyDBUCountOnGeneratedGraph() {
		test = extent.startTest("TC_Cost_CB_28_VerifyDBUCountOnGeneratedGraph", "Add up the DBUs of the result and verify the total DBUs is populated correctly on generated graph ");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_28_VerifyDBUCountOnGeneratedGraph");
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
		String resultSetValue = chargeBackCluster.calculateDBUSumFromResultSet();
		//String actualValue = resultSetValue.substring(0,resultSetValue.indexOf(".")+3);
		String expectedVale = chargeBackCluster.fetchDBUValueFromGraph();
		Assert.assertTrue(resultSetValue.equals(expectedVale), "DBU value calculated from Result Set does not matches with the value populated in DBU Graph");
		test.log(LogStatus.PASS, "DBU values are matching.");
		LOGGER.info("DBU values are matching.");
	}

}
