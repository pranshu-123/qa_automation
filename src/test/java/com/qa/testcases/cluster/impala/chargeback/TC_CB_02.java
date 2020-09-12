package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
/**
 * Verify User is able to view number of clusters on clicking onto cluster
 * dropdown
 */

public class TC_CB_02 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private  DatePickerPageObject picker;

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_02_VerifyClusterList(String clusterId) {
		test = extent.startTest("TC_CB_02_VerifyClusterList: "+clusterId, "Verify User is able to see list of clusters available");
		test.assignCategory("4620 Cluster - Impala Chargeback");

		waitExecuter = new WaitExecuter(driver);
		chargebackImpala = new ChargeBackImpala(driver);
		picker = new DatePickerPageObject(driver);
		// Intialize impala page objects
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		// Click on Chargeback tab
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
		// Click on chargeback dropdown
		waitExecuter.sleep(1000);
		JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
		// Selecting Impala chargeback
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
		picker.calendarDate.click();
		picker.thisMonth.click();
		waitExecuter.sleep(1000);
		// Click on cluster dropdown
		waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.containerDropdownArrow);
		chargebackImpalaPageObject.containerDropdownArrow.click();
		// Get the list of clusters
		Assert.assertNotNull(chargebackImpala.getListOfClusters(chargebackImpalaPageObject.listOfClusters), "There are no cluster available");
		test.log(LogStatus.PASS, "The cluster list is not empty.");

	}

}
