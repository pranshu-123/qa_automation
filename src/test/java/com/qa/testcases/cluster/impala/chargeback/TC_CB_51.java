package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
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

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_51 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private DatePicker picker;
	private HomePage homePage;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_51.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Memory and CPU Cost in the Chargeback table")
	public void TC_CB_51_VerifyTotalCPUAndMemoryHoursOfTable(String clusterId) {
		test = extent.startTest("TC_CB_51_VerifyTotalCPUAndMemoryHoursOfTable: " + clusterId,
				"Verify User is able to see list of clusters available");
		test.assignCategory(" Cluster - Impala Chargeback");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		waitExecuter = new WaitExecuter(driver);
		chargebackImpala = new ChargeBackImpala(driver);
		picker = new DatePicker(driver);
		homePage = new HomePage(driver);
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);

		// Click on Chargeback tab
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		chargeBackImpala.selectImpalaChargeback("Impala");


		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.sleep(1000);
		
		// Select last 30 days from date picker
		test.log(LogStatus.INFO, "Select last 30 days from date picker");
		LOGGER.info("Select last 30 days from date picker");		
		picker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		picker.selectLast30Days();
		waitExecuter.sleep(1000);

		// Set vcore and memory per hour values
		test.log(LogStatus.INFO, "Set vcore and memory per hour values");
		LOGGER.info("Set vcore and memory per hour values");	
		chargebackImpalaPageObject.setChargebackVcorePerHour.sendKeys("5");
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.setChargebackMemoryPerHour.sendKeys("5");
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.applyButton.click();
		waitExecuter.sleep(1000);
		
		// Get CPU hours list from chargeback table
		test.log(LogStatus.INFO, "Get CPU hours consumption of every user from chargeback table");
		LOGGER.info("Get CPU hours consumption of every user from chargeback table");
		List<Double> cpuHoursList = chargebackImpala.getUsersCPUHoursFromTable();
		waitExecuter.sleep(1000);

		// Get CPU cost list from chargeback table
		test.log(LogStatus.INFO, "Get CPU consumption cost of every user from chargeback table");
		LOGGER.info("Get CPU consumption cost of every user from chargeback table");
		List<Double> cpuCostsList = chargebackImpala.getUsersCPUHoursCostFromTable();
		waitExecuter.sleep(1000);

		// Validate CPU calculated cost and cost from chargeback table
		Assert.assertTrue(chargebackImpala.compareTableCPUCostToCalculatedCost(cpuCostsList, cpuHoursList, 5),
				"The table cost do not match with calculated cost of CPU");
		test.log(LogStatus.PASS, "The table cost match with calculated cost of CPU.");

	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyMemoryCost(String clusterId) {
		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
		LOGGER.info("Select clusterId : "+clusterId);
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
