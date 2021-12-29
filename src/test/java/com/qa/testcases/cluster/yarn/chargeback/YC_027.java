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

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_027 extends BaseClass {

	private static final Logger LOGGER = Logger.getLogger(ChargeBackYarn.class.getName());

	/**
	 * Verify Group By Table on Yarn chrageback page
	 */
	@Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the user must be select a graph based on the group by field")
	public void YC_027_verifyGroupByTableColumnCountOnYarnChargeBackPage(String clusterId) {
		test = extent.startTest("YC_027_verifyGroupByTableColumnCountOnYarnChargeBackPage: "+clusterId, "Verify Click on groupby edit field, Yarn chrageback page.");
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

		chargeBackYarn.clickOnGroupBySearchBox();
		String optionName = "User";
		chargeBackImpala.remove1stGroupByOption();
		chargeBackYarn.selectOptionsInGroupBy(optionName);
		test.log(LogStatus.PASS, "Selected "+ optionName + " as option in Group By filter, yarn chargeback page");

		chargeBackYarn.getResultsGroupedByTableRecords();
		test.log(LogStatus.PASS, "Verified Group By Table is available on Yarn chargeback page");
		LOGGER.info("Size of Headers: "+ chargeBackYarn.getResultsGroupedByTableHeaderNames().size());
		Assert.assertTrue(chargeBackYarn.getResultsGroupedByTableHeaderNames().size() == 5 || chargeBackYarn.getResultsGroupedByTableHeaderNames().size() == 4);
		test.log(LogStatus.PASS, "Verified total columns found in Groupby table are: "+chargeBackYarn.getResultsGroupedByTableHeaderNames().size());
		test.log(LogStatus.PASS, "Verified Group By Table Header consist of minimun 4 cloumns or max 5 columns on Yarn chargeback page");

	}

}
