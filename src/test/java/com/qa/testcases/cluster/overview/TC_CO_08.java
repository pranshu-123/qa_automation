package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import org.testng.annotations.Test;
import java.util.logging.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.SkipException;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.KPI;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.ClusterOverview
@Marker.All
public class TC_CO_08 extends BaseClass {

	private WaitExecuter waitExecuter;
	private DatePicker datePicker;
	private static final Logger LOGGER = Logger.getLogger(TC_CO_08.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description="P0-Verify cluster details KPI's for a different date or time range")
	public void TC_CO_08_verifyClusterKPI(String clusterId) {
		test = extent.startTest("TC_CO_08_verifyClusterKPI : " + clusterId,
				"Verify KPIs under cluster heading for Custom Date");
		test.assignCategory(" Cluster Overview");
		KPI kpi = new KPI(driver);
		waitExecuter = new WaitExecuter(driver);
		datePicker = new DatePicker(driver);
		waitExecuter.sleep(3000);

		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		// Click on datepicker button
		LOGGER.info("Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		// Click on custom range
		LOGGER.info("Select custom range");
		datePicker.selectCustomRange();
		waitExecuter.sleep(1000);
		// Set Start date by substracting days from current date and end date as
		// currentdate

		LOGGER.info("Set Start date by substracting days from current date and end date as currentdate");
		datePicker.setCurrentAndPastDate(-30);
		waitExecuter.sleep(1000);
		// Click on apply button of Cluster
		LOGGER.info("Click on custom date apply");
		datePicker.clickOnCustomDateApplyBtn();
		waitExecuter.sleep(1000);
		
		LOGGER.info("Verify node graph");
		test.log(LogStatus.INFO, "Check if Node title is present");
		// Check if Node title is present
		Assert.assertTrue(kpi.isNodeDisplayed(), "Cluster Node title is not displayed ");
		// Check the value of node if node title is present
		test.log(LogStatus.INFO, "Check the value of node if node title is present");
		if (kpi.isNodeDisplayed()) {
			Assert.assertNotNull(kpi.getNodeValue(), "Cluster Node value is none ");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyVcoreGraph(String clusterId) {		
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		LOGGER.info("Verify vcore graph");
		test.log(LogStatus.INFO, "Check if vcores title is present");
		// Check of vcores title is present
		Assert.assertTrue(kpi.isVcoreDisplayed(), "Cluster Available Vcores title is not displayed ");
		// Check the value of vcores if vcore title is present
		test.log(LogStatus.INFO, "Check the value of vcores if vcore title is present");
		if (kpi.isVcoreDisplayed()) {
			Assert.assertNotNull(kpi.getVcoreValue(), "Cluster Vcore value is none ");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyMemoryGraph(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		
		KPI kpi = new KPI(driver);
		LOGGER.info("Verify memory graph");
		test.log(LogStatus.INFO, "Check if memory title is present");
		// Check if memory title is present
		Assert.assertTrue(kpi.isMemoryDisplayed(), "Cluster Available Memory title is not displayed ");
		// Check the value of memory if memory title is present
		test.log(LogStatus.INFO, "Check the value of memory if memory title is present");
		if (kpi.isMemoryDisplayed())

		{
			Assert.assertNotNull(kpi.getMemoryValue(), "Cluster memory value is none ");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyAlertGraph(String clusterId) {
		
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		LOGGER.info("Verify alert graph");
		try {
			test.log(LogStatus.INFO, "Check if alert title is present");
			// Check if alert title is present
			Assert.assertTrue(kpi.isAlertDisplayed(), "Cluster Alert title is not displayed ");
			test.log(LogStatus.INFO, "Check the value of alert if alert title is present");
			// Check the value of alert if alert title is present
			Assert.assertNotNull(kpi.getAlertValue(), "Cluster alert value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if alert title is not present
			throw new SkipException("Alert title tab is not present under Cluster title");
		}
	}
}
