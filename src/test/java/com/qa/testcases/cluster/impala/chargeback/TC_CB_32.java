package com.qa.testcases.cluster.impala.chargeback;

import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author - Ojasvi Pandey
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_32 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private DatePicker picker;
	private HomePage homePage;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_32.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_32_VerifyTotalMemoryHoursToUserMemoryHours(String clusterId) {
		test = extent.startTest("TC_CB_32_VerifyTotalMemoryHoursToUserMemoryHours : " + clusterId,
				"Verify that the Total Memory hours displayed pie chart is equal to the sum of all the users memory hours from chargeback table");
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
		test.log(LogStatus.INFO, "Click on Chargeback tab");
		LOGGER.info("Click on Chargeback tab");
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
		waitExecuter.sleep(1000);

		// Click on chargeback dropdown and select Impala
		test.log(LogStatus.INFO, "Click on chargeback dropdown and select Impala");
		LOGGER.info("Click on chargeback dropdown and select Impala");
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
		waitExecuter.sleep(1000);
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownImpalaOption);
		chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
		waitExecuter.sleep(1000);

		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);	
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.sleep(1000);
		
		// Select last 30 days from date picker
		test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
		LOGGER.info("Select clusterId : " + clusterId);
		picker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		picker.selectLast30Days();
		waitExecuter.sleep(1000);

		// Get header value from memory graph and convert it into seconds
		test.log(LogStatus.INFO, "Get header value from memory graph and convert it into seconds");
		LOGGER.info("Get header value from memory graph and convert it into seconds");
		int memoryHourInSeconds = chargebackImpala.getMemoryHoursInSecFromGraphHeader();
		waitExecuter.sleep(1000);
		
		/*
		 * Get total user memory consumption from chargeback table and convert it to
		 * seconds
		 */
		test.log(LogStatus.INFO, "Get total user memory consumption from chargeback table and convert it to seconds");
		LOGGER.info("Get total user memory consumption from chargeback table and convert it to seconds");
		int totalMemoryConsumptionInSeconds = chargebackImpala.getTotalMemoryHoursToSecondFromTable();
		waitExecuter.sleep(1000);
		if (!chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText().trim().equals("<1s")) {
			List<WebElement> memoryDataFromTable = chargebackImpala.getMemoryDataFromTable();
			waitExecuter.sleep(1000);
			int avgSecondDiff = 60;
			int differenceInSeconds = memoryHourInSeconds - totalMemoryConsumptionInSeconds;
			waitExecuter.sleep(1000);
			Assert.assertTrue(differenceInSeconds < avgSecondDiff,
					"The seconds difference is greater than that of average seconds exemption ");
			test.log(LogStatus.PASS, "The seconds difference is less than the expected value.");
		} else
			Assert.assertEquals(totalMemoryConsumptionInSeconds, memoryHourInSeconds,
					"The total seconds calculation from header does not match with table calculation");
		test.log(LogStatus.PASS, "The seconds calculation from table match the header.");
	}
}
