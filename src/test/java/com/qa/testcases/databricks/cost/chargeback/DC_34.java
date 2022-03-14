package com.qa.testcases.databricks.cost.chargeback;

import java.util.logging.Logger;

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
public class DC_34 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_34.class.getName());

	@Test
	public void TC_Cost_CB_34_ValidateCustomDateFeature() {
		test = extent.startTest("TC_Cost_CB_34_ValidateCustomDateFeature", "Validate Custom Date feature");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_34_ValidateCustomDateFeature");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectCustomRange();
		datePicker.setCurrentAndPastDate(-13);
		LOGGER.info("Custom Date Selected");
		waitExecuter.sleep(3000);
		datePicker.clickOnCustomDateApplyBtn();
		waitExecuter.sleep(2000);
		jobs.selectGroupByFilterValue("User");
		chargeBackCluster.validateResultSetIsDisplayedWithValues("User");
		test.log(LogStatus.PASS, "Resultant Table displays data as per selected Custom Date");
		LOGGER.info("Resultant Table displays data as per selected Custom Date");

	}
}
