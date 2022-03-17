package com.qa.testcases.databricks.cost.chargeback;

import java.util.List;
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
public class DC_01 extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(DC_01.class.getName());
	//@Test
	public void TC_Cost_CB_01_VerifyGroupByFilter() {
		test = extent.startTest("TC_Cost_CB_01_VerifyGroupByFilter", "The \"group by\" options should switch all the tabs accordingly (Users, workspaces, cluster, Tag Key)	");
		test.assignCategory("Cost/Chargeback");
		Log.startTestCase("TC_Cost_CB_01_VerifyGroupByFilter");
		Jobs jobs = new Jobs(driver);
		ChargeBackCluster chargeBackCluster = new ChargeBackCluster(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);  
		DatePicker datePicker = new DatePicker(driver);
		chargeBackCluster.navigateToCostTab("Chargeback");
		LOGGER.info("Navigated to Chareback page");
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
		test.log(LogStatus.PASS, "Group By filter display all 4 types: User, Workspace, Cluster, Tag key");
		LOGGER.info("Group By filter display all 4 types: User, Workspace, Cluster, Tag key");
	}
}
