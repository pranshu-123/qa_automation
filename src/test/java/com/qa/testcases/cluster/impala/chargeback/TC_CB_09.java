package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.testcases.cluster.overview.TC_CO_08;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author - Ojasvi Pandey
 */

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_09 extends BaseClass {
	private WaitExecuter waitExecuter;
	private ChargeBackImpala chargebackImpala;
	private ChargebackImpalaPageObject chargebackImpalaPageObject;
	private HomePage homePage;
	private static final Logger LOGGER = Logger.getLogger(TC_CB_09.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the Grouped by drop-down box displays all the filters.")
	public void TC_CB_09_VerifyGroupByOptions(String clusterId) {
		test = extent.startTest("TC_CB_09_VerifyGroupByOptions : " + clusterId,
				"Verify group by options available in list");
		test.assignCategory(" Cluster - Impala Chargeback");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		waitExecuter = new WaitExecuter(driver);
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		homePage = new HomePage(driver);
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);

		// Click on Chargeback tab
		chargeBackImpala.selectImpalaChargeback("Impala");
		test.log(LogStatus.INFO, "Click on Chargeback tab");


		
		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
		LOGGER.info("Select clusterId : "+clusterId);
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.sleep(1000);
		
		// Click on chargeback group by combobox
		test.log(LogStatus.INFO, "Click on chargeback group by combobox");
		LOGGER.info("Click on chargeback group by combobox");
		waitExecuter.sleep(1000);
		chargebackImpalaPageObject.groupBySearchBox.click();
		waitExecuter.sleep(1000);
		
		// Verify the names of options available in group by
		test.log(LogStatus.INFO, "Verify the names of options available in group by");
		LOGGER.info("Verify the names of options available in group by");
		Assert.assertTrue(chargebackImpala.validateGroupByOptions(),
				"The displayed option does not smatch the group by actual options");

	}
}
