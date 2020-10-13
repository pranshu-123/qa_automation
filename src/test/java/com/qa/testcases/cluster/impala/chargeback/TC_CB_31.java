package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_31 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private DatePicker picker;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_31.class.getName());
	
	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_31_VerifyTotalMemoryHoursToUserMemoryHours(String clusterId) {
		test = extent.startTest("TC_CB_31_VerifyTotalMemoryHoursToUserMemoryHours : " + clusterId,
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

		// Get CPU hours from table
		double totalCPUHoursFromTable = chargebackImpala.getTotalCPUHoursFromTable();

		// Get total CPU hours from table
		double headerValue = chargebackImpala.getCPUHoursFromGraphHeader();
		if (!chargebackImpalaPageObject.CPUHoursFromGraphHeader.getText().equals("0.00")) {

			int avgSecondDiff = chargebackImpala.getCPUHourListFromTable().size() / 2;
			double differenceInSeconds = headerValue - totalCPUHoursFromTable;
			// Compare header CPU hours to total table hours
			Assert.assertTrue(differenceInSeconds < avgSecondDiff,
					"The seconds difference is greater than that of average seconds exemption ");
			test.log(LogStatus.PASS, "The seconds difference is less than the expected value.");

		} else
			Assert.assertEquals(headerValue, totalCPUHoursFromTable,
					"Total CPU hours from table does not match header CPU hours when converted to seconds");
			test.log(LogStatus.PASS, "Total CPU hours from table match the header value of CPU graph.");
	}
}
