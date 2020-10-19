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
public class TC_CO_12 extends BaseClass {

	private WaitExecuter waitExecuter;
	private DatePicker datePicker;
	private static final Logger LOGGER = Logger.getLogger(TC_CO_12.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CO_12_verifyKPIJobs(String clusterId) {
		test = extent.startTest("TC_CO_12_verifyKPIJobs : "+clusterId, "Verify Custom Date KPIs under Jobs heading ");
		test.assignCategory(" Cluster Overview");
		waitExecuter = new WaitExecuter(driver);
		datePicker = new DatePicker(driver);

		//Select the cluster
		test.log(LogStatus.INFO, "This testcase is running for cluster: "+clusterId);
		LOGGER.info("Selecting cluster from the list " +clusterId);
		HomePage homePage = new HomePage(driver);
	    homePage.selectMultiClusterId(clusterId);
		DatePicker datePicker = new DatePicker(driver);
		// Click on datepicker button
		waitExecuter.sleep(3000);
		LOGGER.info("Click on date picker");
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		// Click on custom range
		LOGGER.info("Select custom range");
		test.log(LogStatus.INFO, "Select custom range");
		datePicker.selectCustomRange();
		waitExecuter.sleep(1000);
		/* Set Start date by substracting days from current date and end date as currentdate */ 
		LOGGER.info("Set current date and past date");
		test.log(LogStatus.INFO, "Set current date and past date");
		datePicker.setCurrentAndPastDate(-30);
		waitExecuter.sleep(1000);
		// Click on apply button of Cluster
		LOGGER.info("Click on apply button");
		test.log(LogStatus.INFO, "Click on apply button");
		datePicker.clickOnCustomDateApplyBtn();
		waitExecuter.sleep(1000);

	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyRunningKPI(String clusterId) {		
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		try {

			LOGGER.info("Verify running graph");
			test.log(LogStatus.INFO, "Check if Running title is present");
			// Check if running title is present
			Assert.assertTrue(kpi.isRunningDisplayed(), "Job Running title is not displayed ");
			// Check the value of running if running title is present
			LOGGER.info("Verify running graph value");
			test.log(LogStatus.INFO, "Check if Running title value is not null");
			Assert.assertNotNull(kpi.getRunningValue(), "Job running value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if running title is not present
			throw new SkipException("Running title tab is not present under Job heading");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyPendingKPI(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		try {
			LOGGER.info("Verify pending graph");
			test.log(LogStatus.INFO, "Check if Pending title is present");
			// Check if pending title is present
			Assert.assertTrue(kpi.isPendingDisplayed(), "Job Pending title is not displayed ");
			LOGGER.info("Verify pending graph value is not null");
			test.log(LogStatus.INFO, "Check if Pending value is not null");
			// Check the value of pending graph
			Assert.assertNotNull(kpi.getPendingValue(), "Job pending value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if pending title is not present
			throw new SkipException("Pending title tab is not present under Job heading");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifySuccessKPI(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);		
		KPI kpi = new KPI(driver);
		try {
			LOGGER.info("Verify Success graph");
			test.log(LogStatus.INFO, "Check if Success title is present");
			// Check if success title is present
			Assert.assertTrue(kpi.isSuccessDisplayed(), "Job Success title is not displayed ");
			LOGGER.info("Verify success graph value");
			test.log(LogStatus.INFO, "Check if Success value is not null");
			// Check the value of success if Success title is present
			Assert.assertNotNull(kpi.getSuccessValue(), "Job success value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if Success title is not present
			throw new SkipException("Success title tab is not present under Job heading");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyFailedKPI(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		try {
			LOGGER.info("Verify Failed graph");
			test.log(LogStatus.INFO, "Check if Failed title is present");
			// Check if failed title is present
			Assert.assertTrue(kpi.isFailedDisplayed(), "Job Failed title is not displayed ");
			// Check the value of failed if failed title is present
			LOGGER.info("Verify Failed graph avlue is not null");
			test.log(LogStatus.INFO, "Check if Failed graph value is not null");
			Assert.assertNotNull(kpi.getFailedValue(), "Job failed value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if failed title is not present
			throw new SkipException("Failed title tab is not present under Job title");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyKilledKPI(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		try {
			LOGGER.info("Verify Killed graph");
			test.log(LogStatus.INFO, "Check if Killed title is present");
			// Check if Killed title is present
			Assert.assertTrue(kpi.isKilledDisplayed(), "Cluster Killed title is not displayed ");
			// Check the value of Killed if Killed title is present
			LOGGER.info("Verify Failed graph value is not null");
			test.log(LogStatus.INFO, "Check if Failed graph value is not null");
			Assert.assertNotNull(kpi.getKilledValue(), "Job Killed value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if Killed title is not present
			throw new SkipException("Killed title tab is not present under Job title");
		}
	}

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyInefficientEventsKPI(String clusterId) {
		test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
		// Select the cluster
		LOGGER.info("Selecting cluster from the list " + clusterId);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		KPI kpi = new KPI(driver);
		try {
			LOGGER.info("Verify InefficientEvents graph");
			test.log(LogStatus.INFO, "Check if InefficientEvents title is present");
			// Check if InefficientEvents title is present
			Assert.assertTrue(kpi.isInefficientEventsDisplayed(), "Cluster InefficientEvents title is not displayed ");
			// Check the value of InefficientEvents if InefficientEvents title is present
			LOGGER.info("Verify InefficientEvents graph is not null");
			test.log(LogStatus.INFO, "Check if InefficientEvents graph value is not null");
			Assert.assertNotNull(kpi.getInefficientEventsValue(), "Job InefficientEvents value is none ");
		} catch (NoSuchElementException error) {
			// Skip test case if InefficientEvents title is not present
			throw new SkipException("InefficientEvents title tab is not present under Job title");

		}

	}
}
