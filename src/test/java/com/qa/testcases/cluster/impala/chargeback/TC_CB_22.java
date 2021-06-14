package com.qa.testcases.cluster.impala.chargeback;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_22 extends BaseClass {
	private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_22.class);

	/**
	 * Validate Charge Back report when Grouped by "inputTables"
	 * 
	 * @param clusterId - ClusterId to select for test execution
	 */
	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user is not able to group by more than two filters.")
	public void validateGroupByInputTablesImpalaChargeback(String clusterId) {
		test = extent.startTest("TC_CB_22.validateGroupByInputTablesImpalaChargeback",
				"Validate Charge Back report " + "when Grouped by \"inputTables\"");
		test.assignCategory(" Cluster - Impala Chargeback");
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		ChargebackImpalaPageObject chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		chargeBackImpala.selectImpalaChargeback();
		LOGGER.info("Navigate to impala chargeback page", test);
		// Select Cluster
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);
		WaitExecuter wait = new WaitExecuter(driver);
		LOGGER.info("Selected cluster: " + clusterId, test);
		DatePicker datePicker = new DatePicker(driver);
		datePicker.clickOnDatePicker();
		//datePicker.selectLast30Days();
		LOGGER.info("Select last 90 days", test);
		chargeBackImpala.clickOnGroupBySearchBox();
		chargeBackImpala.selectGroupBy(GroupByOptions.INPUT_TABLES);
		LOGGER.info("Click on groupBy: " + GroupByOptions.INPUT_TABLES.value, test);
		wait.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
		chargeBackImpala.clickOnGroupBySearchBox();
		LOGGER.pass("Validated the group by options can be chosen only 2", test);
		Assert.assertTrue(chargeBackImpala.validateGroupByMessage(),
				"Group by dropdown does not display select only 2 items.");
		LOGGER.pass("Validated that group by display select only 2 items as message.", test);
	}
}