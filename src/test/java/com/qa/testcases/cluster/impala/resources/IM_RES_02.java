package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.parameters.Parameter;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

/* 
 * @author - Ojasvi Pandey 
*/

@Marker.ImpalaResources
@Marker.All
public class IM_RES_02 extends BaseClass {
	private WaitExecuter waitExecuter;
	private Impala impala;
	private ImpalaPageObject impalaPageObject;
	private DatePicker picker;
	private Parameter param;
	private HomePage homePage;
	private DatePickerPageObject datePickerPageObject;
	private static final Logger LOGGER = Logger.getLogger(IM_RES_02.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user should be able to select each date picker and display the Memory Consumption and Queries graphs ")
	public void IM_RES_02_verifyIfGraphDisplayedForMemoryConsumptionAndQuery(String clusterId) {
		test = extent.startTest("IM_RES_02_verifyIfGraphDisplayedForMemoryConsumptionAndQuery : " + clusterId,
				"Verify if user is able to view graph for ");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		waitExecuter = new WaitExecuter(driver);
		picker = new DatePicker(driver);
		impala = new Impala(driver);
		datePickerPageObject = new DatePickerPageObject(driver);
		homePage = new HomePage(driver);
		impalaPageObject = new ImpalaPageObject(driver);

		test.log(LogStatus.INFO, "Go to impala page");
		LOGGER.info("Click on impala tab");
		// Click on Resources tab and select Impala
		impala.selectImpalaResource("Impala");
		waitExecuter.sleep(2000);

		// Select the cluster
		test.log(LogStatus.INFO, "Selecting the cluster");
		LOGGER.info("Selecting the cluster");
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Select custom date
		test.log(LogStatus.INFO, "Selecting the custom date");
		LOGGER.info("Select the custom date from date picker");
		picker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		picker.selectCustomRange();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		// Set start date in calendar
		LOGGER.info("Set the start date");
		test.log(LogStatus.INFO, "Set the custom date in date picker");
		waitExecuter.waitUntilElementClickable(impalaPageObject.customRangeStartDate);
		impalaPageObject.customRangeStartDate.clear();
		impalaPageObject.customRangeStartDate.sendKeys(param.impalaResourceStartDate);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		// Set end date in calendar
		LOGGER.info("Set the end date");
		waitExecuter.waitUntilElementClickable(impalaPageObject.customRangeEndDate);
		impalaPageObject.customRangeEndDate.clear();
		impalaPageObject.customRangeEndDate.sendKeys(param.impalaResourceEndDate);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		
		// Click apply button
		LOGGER.info("Click on apply button");
		test.log(LogStatus.INFO, "Click on apply button");
		try {
			if (impalaPageObject.applyBtnImpalaDatePicker.isDisplayed()) {
				// waitExecuter.waitUntilElementClickable(impalaPageObject.applyBtnImpalaDatePicker);
				JavaScriptExecuter.clickOnElement(driver, impalaPageObject.applyBtnImpalaDatePicker);
				waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
			}
		} catch (NoSuchElementException exception) {
			JavaScriptExecuter.clickOnElement(driver, impalaPageObject.applyBtn);
			waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		}

		// Verify if memory graph is present
		LOGGER.info("Verify if memory graph is present");
		test.log(LogStatus.INFO, "Verify if memory graph is present");
		// Validate of Memory graph is present for selected date range
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
		LOGGER.info("Verify query graph is present");
		test.log(LogStatus.INFO, "Verify query graph is present");
		// Validate of Query graph is present for selected date range
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
	}
}
