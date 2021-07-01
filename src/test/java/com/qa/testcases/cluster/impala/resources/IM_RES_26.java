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
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

/* 
 * @author - Ojasvi Pandey 
*/
@Marker.ImpalaResources
@Marker.All
public class IM_RES_26 extends BaseClass {
	private WaitExecuter waitExecuter;
	private Impala impala;
	private ImpalaPageObject impalaPageObject;
	private DatePicker picker;
	private Parameter param;
	private DatePickerPageObject datePickerPageObject;
	private static final Logger LOGGER = Logger.getLogger(IM_RES_26.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Memory usage graph should display the message No Data To Display")
	public void IM_RES_26_verifyIsGraphDisplayedForMemoryConsumptionAndQuery(String clusterId) {
		test = extent.startTest("IM_RES_26_verifyIsGraphDisplayedForMemoryConsumptionAndQuery : " + clusterId,
				"Verify if user is able to view No data if graph is not visible");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		waitExecuter = new WaitExecuter(driver);
		picker = new DatePicker(driver);
		impala = new Impala(driver);
		impalaPageObject = new ImpalaPageObject(driver);
		datePickerPageObject = new DatePickerPageObject(driver);
		HomePage homePage = new HomePage(driver);

		//Select impala tab
		test.log(LogStatus.INFO, "Go to resource page");
		LOGGER.info("Select impala from dropdown");
		impala.selectImpalaResource();
		waitExecuter.sleep(2000);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Select the cluster
		test.log(LogStatus.INFO, "Select cluster : " + clusterId);
		LOGGER.info("Select cluster : " + clusterId);		
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Select custom date
		picker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		picker.selectCustomRange();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Set start date in calendar
		LOGGER.info("Set the start date");
		waitExecuter.waitUntilElementClickable(impalaPageObject.customRangeStartDate);
		impalaPageObject.customRangeStartDate.clear();
		impalaPageObject.customRangeStartDate.sendKeys(param.impalaResource2018StartDate);
		// Set end date in calendar
		LOGGER.info("Set the end date");
		impalaPageObject.customRangeEndDate.clear();
		impalaPageObject.customRangeEndDate.sendKeys(param.impalaResource2018EndDate);
		waitExecuter.waitUntilElementClickable(impalaPageObject.applyBtn);
		// Click apply button
		LOGGER.info("Click on apply button");
		test.log(LogStatus.INFO, "Click on apply button");
		impalaPageObject.applyBtn.click();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		LOGGER.info("Verify Memory graph is displayed with No data to display");
		test.log(LogStatus.INFO, "Verify Memory graph is displayed with No data to display");
		Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");

		LOGGER.info("Verify Query graph is displayed with No data to display");
		test.log(LogStatus.INFO, "Verify Query graph is displayed with No data to display");
		Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
	}

}
