package com.qa.testcases.databricks.cost.chargeback;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.databricks.cost.ChargeBackCluster;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;

@Marker.DbCostChargeback
public class DC_01 extends BaseClass{

	@Test
	public void TC_Cost_CB_01_VerifyGroupByFilter() {
		test = extent.startTest("TC_Cost_CB_01_VerifyGroupByFilter", "The \"group by\" options should switch all the tabs accordingly (Users, workspaces, cluster, Tag Key)	");
		test.assignCategory(" Cluster / Job");
		Log.startTestCase("TC_Cost_CB_01_VerifyGroupByFilter");
		Jobs jobs = new Jobs(driver);
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		List<String> values  = chargeBackCluster.fetchAllGroupByFilterValues();
		Assert.assertTrue(values.contains("User"));
		Assert.assertTrue(values.contains("Workspace"));
		Assert.assertTrue(values.contains("Cluster"));
		Assert.assertTrue(values.contains("Tag Key"));
		jobs.selectGroupByFilterValue("User");
		chargeBackCluster.validateResultSetIsDisplayedWithValues("User");
		jobs.selectGroupByFilterValue("Workspace");
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Workspace");
		jobs.selectGroupByFilterValue("Cluster");
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Cluster");
		jobs.selectGroupByFilterValue("Tag Key");
		chargeBackCluster.validateResultSetIsDisplayedWithValues("Tag Key");
	}
}
