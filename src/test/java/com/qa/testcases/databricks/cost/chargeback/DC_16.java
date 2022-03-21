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
public class DC_16 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_16.class.getName());
	
	@Test
	public void TC_Cost_CB_16_VerifyCopyUrlFunctionality() {
		test = extent.startTest("TC_Cost_CB_16_VerifyCopyUrlFunctionality", "Verify Copy URL functionality");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_16_VerifyCopyUrlFunctionality");
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		Jobs jobs = new Jobs(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to Chareback page");
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		jobs.selectGroupByFilterValue("Cluster");
		String url = chargeBackCluster.selectCopyUrl();
		waitExecuter.sleep(2000);
		LOGGER.info("URL copied");
		driver.navigate().to(url);
		waitExecuter.sleep(2000);
		LOGGER.info("Navigated to copied URL");
		chargeBackCluster.validateDate();
		Assert.assertTrue(url.contains("compute/dbclusters"));
		test.log(LogStatus.PASS, "Navigated to Cluster page");
		LOGGER.info("Navigated to Cluster page");
	}
}
