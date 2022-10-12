package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.compute.Compute;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxCostChargeback
public class DC_38 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_38.class.getName());

	@Test
	public void TC_Cost_CB_38_ValidateCostOnChargebackMatchesComputePageForSelectedUser() {
		test = extent.startTest("TC_Cost_CB_38_ValidateCostOnChargebackMatchesComputePageForSelectedUser", "Validate Cost on Chargeback page must match with those on Compute page for selected User");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_38_ValidateCostOnChargebackMatchesComputePageForSelectedUser");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		Compute compute = new Compute(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast7Days();
		LOGGER.info("Custom Date Selected");
		waitExecuter.sleep(3000);
		jobs.selectGroupByFilterValue("User");
		chargeBackCluster.filterBy("Clusters");
		chargeBackCluster.filterByCluster();
		String costOnChargeback = chargeBackCluster.calculateTotalCostFromResultSet();
		chargeBackCluster.selectOptimize();
		waitExecuter.sleep(3000);
		String costOnCompute = 	"$ " + compute.retrieveCostSum();
		Assert.assertEquals(costOnChargeback, costOnCompute);
		test.log(LogStatus.PASS, "Cost on Chargeback page must matches with those on Compute page for selected User");
		LOGGER.info("Cost on Chargeback page must matches with those on Compute page for selected User");

	}
}
