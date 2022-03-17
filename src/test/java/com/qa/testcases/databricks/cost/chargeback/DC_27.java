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
public class DC_27 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_27.class.getName());

	//@Test
	public void TC_Cost_CB_27_VerifyClusterCountOnGeneratedGraph() {
		test = extent.startTest("TC_Cost_CB_27_VerifyClusterCountOnGeneratedGraph", "Add up the Cluster count of the result and verify the total Cluster Count is populated correctly on generated graph");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_27_VerifyClusterCountOnGeneratedGraph");
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
		String resultSetValue = chargeBackCluster.calculateClusterSumFromResultSet();
		String expectedVale = chargeBackCluster.fetchClusterValueFromGraph();
		Assert.assertTrue(resultSetValue.equals(expectedVale), "Cluster count calculated from Result Set does not matches with the value populated in Cluster Graph");
		test.log(LogStatus.PASS, "Cluster values are matching.");
		LOGGER.info("Cluster values are matching.");
	}

}
