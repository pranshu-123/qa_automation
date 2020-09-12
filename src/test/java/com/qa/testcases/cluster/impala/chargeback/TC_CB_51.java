package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CB_51 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private DatePicker picker;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_51.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_51_VerifyTotalCPUAndMemoryHoursOfTable(String clusterId) {
		test = extent.startTest("TC_CB_51_VerifyTotalCPUAndMemoryHoursOfTable: "+clusterId,
				"Verify User is able to see list of clusters available");
		test.assignCategory("4620 Cluster - Impala Chargeback");
		waitExecuter = new WaitExecuter(driver);
		chargebackImpala = new ChargeBackImpala(driver);
		picker = new DatePicker(driver);
		// Intialize impala page objects
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		// Click on Chargeback tab
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
		// Click on chargeback dropdown
		waitExecuter.sleep(1000);
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
		// Selecting Impala chargeback
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownImpalaOption);
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		// Select last 30 days from date picker
		waitExecuter.sleep(1000);
		picker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		picker.selectLast30Days();

		// Set vcore and memory per hour values
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.setChargebackVcorePerHour.sendKeys("5");
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.setChargebackMemoryPerHour.sendKeys("5");
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.applyButton.click();

		// Get CPU hours list from chargeback table
		waitExecuter.sleep(1000);
		List<Double> cpuHoursList = chargebackImpala.getUsersCPUHoursFromTable();

		// Get CPU cost list from chargeback table
		waitExecuter.sleep(1000);
		List<Double> cpuCostsList = chargebackImpala.getUsersCPUHoursCostFromTable();

		// Validate CPU calculated cost and cost from chargeback table
		waitExecuter.sleep(1000);
		Assert.assertTrue(chargebackImpala.compareTableCPUCostToCalculatedCost(cpuCostsList, cpuHoursList, 5),
				"The table cost do not match with calculated cost of CPU");
		test.log(LogStatus.PASS, "The table cost match with calculated cost of CPU.");

	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyMemoryCost(String clusterId) {
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		// Get Memory usage in hours from chargeback table
		waitExecuter.sleep(1000);
		List<Double> memoryHoursList = chargebackImpala.getUsersMemoryHoursFromTable();

		// Get CPU cost list from chargeback table
		waitExecuter.sleep(1000);
		List<Double> memoryCostsList = chargebackImpala.getUsersMemoryHoursCostFromTable();

		// Validate Memory calculated cost and cost from chargeback table
		Assert.assertTrue(chargebackImpala.compareTableMemoryCostToCalculatedCost(memoryCostsList, memoryHoursList, 5),
				"The table cost do not match with calculated cost of Memory.");
		test.log(LogStatus.PASS, "The table cost do match with calculated cost of Memory.");
	}
}
