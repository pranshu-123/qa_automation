package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/* 
 * @author - Ojasvi Pandey 
*/

@Marker.ImpalaResources
@Marker.All
public class IM_RES_10 extends BaseClass {

	private Impala impala;
	private WaitExecuter waitExecuter;
	private DatePicker datePicker;
	private ImpalaPageObject impalaPageObject;
	private static final Logger LOGGER = Logger.getLogger(IM_RES_10.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void IM_RES_10_verifyQueryGraphForQueueGroupForAllDatePickerOptions(String clusterId) {
		test = extent.startTest("IM_RES_10_verifyQueryGraphForQueueGroupForAllDatePickerOptions : " + clusterId,
				"Verify Query graph one by one for each of the options in date picker range");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		impala = new Impala(driver);
		waitExecuter = new WaitExecuter(driver);
		datePicker = new DatePicker(driver);
		impalaPageObject = new ImpalaPageObject(driver);
		HomePage homePage = new HomePage(driver);
		test.log(LogStatus.INFO, "Login to the application");

		// Click on Impala tab
		test.log(LogStatus.INFO, "Go to impala page");
		LOGGER.info("Clicking on Impala tab");
		waitExecuter.waitUntilElementClickable(impalaPageObject.clusterImpalaTab);
		JavaScriptExecuter.clickOnElement(driver, impalaPageObject.clusterImpalaTab);
		waitExecuter.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

		// Select the cluster
		test.log(LogStatus.INFO, "Select cluster : " + clusterId);
		LOGGER.info("Select cluster : " + clusterId);
		waitExecuter.sleep(1000);		
		homePage.selectMultiClusterId(clusterId);

		// Select last one hour
		test.log(LogStatus.INFO, "Click on date picker");
		LOGGER.info("Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select last 1 hour");
		LOGGER.info("Select last one hour");
		datePicker.selectLastOneHour();
		waitExecuter.sleep(3000);
		
		//Select queue in group by
		test.log(LogStatus.INFO, "Select Queue in Group by option.");
		LOGGER.info("Select Queue in Group by option.");
		impalaPageObject.groupByDropdownButton.click();
		waitExecuter.sleep(1000);
		impalaPageObject.groupByQueueList.click();
		waitExecuter.sleep(1000);
		

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 2 hour");
		// Select last 2 hour
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 2 hours");
		datePicker.selectLastTwoHour();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 6 hour");
		// Select last 6 hour
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 6 hours");
		datePicker.selectLastSixHour();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for last 12 hour");
		// Select last 12 hour
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 12 hours");
		datePicker.selectLast12Hour();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Today");
		// Select option from date picker as Today
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Today from date picker");
		datePicker.selectToday();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Last 7 days");
		// Select option from date picker as Last 7 days
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 7 days from date picker");
		datePicker.selectLast7Days();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Last 30 days");
		// Select option from date picker as Last 30 days
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 30 days from date picker");
		datePicker.selectLast30Days();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Last 90 days");
		// Select option from date picker as Last 90 days
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last 90 days from date picker");
		datePicker.selectLast90Days();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for This Month");
		// Select option from date picker as This Month
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select This Month from date picker");
		datePicker.selectThisMonth();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

		LOGGER.info("Verifying memory and query graph for Last Month");
		// Select option from date picker as Today
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		test.log(LogStatus.INFO, "Select Last Month from date picker");
		datePicker.selectLastMonth();
		waitExecuter.sleep(3000);

		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
	}

}
