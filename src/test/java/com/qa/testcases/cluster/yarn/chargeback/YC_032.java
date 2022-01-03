package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_032 extends BaseClass {

	/**
	 * Verify Yarn Jobs Table on Yarn chrageback page and table should contains all the fields By default :
	 */

	@Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the yarn jobs table should contain all the default fields on the yarn chargeback page")
	public void YC_032_verifyYarnJobsTableColumnsOnYarnChargeBackPage(String clusterId) {
		test = extent.startTest("YC_032_verifyYarnJobsTableColumnsOnYarnChargeBackPage: " + clusterId, "Verify yarn jobs table columns on Yarn chrageback page.");
		test.assignCategory(" Cluster - Yarn Chargeback");

		ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		chargeBackYarn.selectYarnChargeback();
		waitExecuter.sleep(2000);

		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
		HomePage homePage = new HomePage(driver);
		chargeBackYarn.selectChargebackType("Yarn");
		waitExecuter.sleep(2000);
		homePage.selectMultiClusterIdClusterPage(clusterId);
		waitExecuter.sleep(1000);

		// Click on datepicker button
		DatePicker datePicker = new DatePicker(driver);
		datePicker.clickOnDatePicker();
		datePicker.selectLast30Days();
		test.log(LogStatus.PASS, "Selected 30 Days date picker for Yarn chargeback page");

		// Select Yarn Charge back
		chargeBackYarn.clickOnGroupBySearchBox();
		String optionName = "User";
		chargeBackImpala.remove1stGroupByOption();
		chargeBackYarn.selectOptionsInGroupBy(optionName);
		test.log(LogStatus.PASS, "Selected " + optionName + " as option in Group By filter, yarn chargeback page");

		//Checking Yarn Jobs Table Records populated
		chargeBackYarn.getYarnJobsTableRecord();
		test.log(LogStatus.PASS, "Verified Yarn Jobs Table is available on Yarn chargeback page");

		//Validate Header Column names in Yarn Jobs Table
		Assert.assertTrue(chargeBackYarn.validateHeaderColumnNameInYarnJobsTable(),"Validation failed for header column names from Yarn Jobs Table");
		test.log(LogStatus.PASS, "Verified Column names in Yarn Jobs Table successfully on Yarn chargeback page");
	}
}
