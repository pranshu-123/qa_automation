package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.testcases.cluster.overview.TC_CO_08;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CB_09 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_09.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CB_09_VerifyClusterList(String clusterId) {
		test = extent.startTest("TC_CB_09_VerifyClusterList : "+clusterId , "Verify User is able to see list of clusters available");
		test.assignCategory("4620 Cluster - Impala Chargeback");

		waitExecuter = new WaitExecuter(driver);
		chargebackImpala = new ChargeBackImpala(driver);
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
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		// Click on chargeback group by combobox
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.groupBySearchBox.click();

		// Verify the number of options available in group by options
		waitExecuter.sleep(1000);
		Assert.assertEquals(chargebackImpalaPageObject.listOfClusters.size(), 9,
				"Available options should be 9 but present are " + chargebackImpalaPageObject.listOfClusters.size());

		// Verify the names of options available in group by options
		Assert.assertTrue(chargebackImpala.validateGroupByOptions(),
				"The displayed option does not smatch the group by actual options");

	}
}
