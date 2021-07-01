package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.parameters.Parameter;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_01 extends BaseClass {
	private WaitExecuter waitExecuter;
	private Impala impala;
	private ImpalaPageObject impalaPageObject;
	private DatePicker picker;
	private Parameter param;
	private static final Logger LOGGER = Logger.getLogger(IM_RES_01.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user can view the graph for Memory and Query on selecting the date picker.")
	public void IM_RES_01_verifyGraphsForDatePickerOptions(String clusterId) {
		test = extent.startTest("IM_RES_01_verifyGraphIsDisplayedForMemoryConsumptionAndQueryForAllDatePickerOption",
				"Verify if user is able to view graph for Memory and Query on selecting different options from date picker from 1hour to last month");
		test.assignCategory(" Cluster/Impala Resources");
		LOGGER.info("Verifying memory and query graph for last 1 hour");
		waitExecuter = new WaitExecuter(driver);
		picker = new DatePicker(driver);
		impala = new Impala(driver);
		// Intialize impala page objects
		impalaPageObject = new ImpalaPageObject(driver);
		// Click on Resources tab and select Impala
		impala.selectImpalaResource();
		waitExecuter.sleep(1000);
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(2000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);

		// Select last one hour
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 1hour from  date picker");
		picker.selectLast30Days();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 2 hour");
		// Select last two hour
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 2 hour from date picker");
		picker.selectLastTwoHour();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 6 hour");
		// Select last six hour
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 6 hour date picker");
		picker.selectLastSixHour();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 12 hour");
		// Select last twelve hour
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 12 hour from date picker");
		picker.selectLast12Hour();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Today");
		// Select today from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select today from date picker");
		picker.selectToday();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Yesterday");
		// Select yesterday from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select yesterday from date picker");
		picker.selectYesterday();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 7 days");
		// Select 7 Days from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 7 days from date picker");
		picker.selectLast7Days();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 30 days");
		// Select 30 days from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 30 days from date picker");
		picker.selectLast30Days();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 90 days");
		// Select 90 days from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last 90 days from date picker");
		picker.selectLast90Days();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for this month");
		// Select this month from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select this month from date picker");
		picker.selectThisMonth();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last month");
		// Select last month from date picker
		test.log(LogStatus.INFO, "Click on date picker");
		picker.clickOnDatePicker();
		test.log(LogStatus.INFO, "Select last month from date picker");
		picker.selectLastMonth();
		waitExecuter.sleep(3000);
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
	}
}
