package com.qa.testcases.cluster.impala.chargeback;

import java.util.List;
import java.util.logging.Logger;

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

public class TC_CB_32 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private DatePicker picker;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_32.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_32_VerifyTotalMemoryHoursToUserMemoryHours(String clusterId) {
		test = extent.startTest("TC_CB_32_VerifyTotalMemoryHoursToUserMemoryHours : " + clusterId,
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
		chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		// Select last 30 days
		picker.clickOnDatePicker();
		waitExecuter.sleep(1000);
		picker.selectLast30Days();

		// Get header value from memory graph and convert it into seconds
		waitExecuter.sleep(1000);

		int memoryHourInSeconds = chargebackImpala.getMemoryHoursInSecFromGraphHeader();

		/*
		 * Get total user memory consumption from chargeback table and convert it to
		 * seconds
		 */
		int totalMemoryConsumptionInSeconds = chargebackImpala.getTotalMemoryHoursToSecondFromTable();
		if (!chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText().trim().equals("<1s")) {
			List<WebElement> memoryDataFromTable = chargebackImpala.getMemoryDataFromTable();
			int avgSecondDiff = memoryDataFromTable.size() / 2;

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
