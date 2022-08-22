package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TC_CB_21 extends BaseClass {
	private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_20.class);

	/**
	 * Validate Charge Back report when Grouped by "inputTables"
	 * @param clusterId - ClusterId to select for test execution
	 */
	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the chargeback report should be present when clicking on grouped by Queue and team")
	public void TC_CB_21_validateGroupByQueueTeam(String clusterId) {
		test = extent.startTest("TC_CB_20.validateGroupByQueueTeam", "Validate Impala ChargeBack Queue and Team report " +
				"when Grouped by \"inputTables\"");
		test.assignCategory(" Cluster - Impala Chargeback");
		ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		chargeBackImpala.selectImpalaChargeback();
		LOGGER.info("Navigate to impala chargeback page", test);
		//Select Cluster
		HomePage homePage = new HomePage(driver);
		ChargebackImpalaPageObject cbPageObject = new ChargebackImpalaPageObject(driver);
		// Select the cluster
		test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
		homePage.selectMultiClusterIdClusterPage(clusterId);


		chargeBackImpala.selectImpalaType("Impala");
		waitExecuter.sleep(2000);
		DatePicker datePicker = new DatePicker(driver);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		LOGGER.info("Select last 90 days", test);
		chargeBackImpala.clickOnGroupBySearchBox();
		chargeBackImpala.selectGroupBy(GroupByOptions.USER);
		LOGGER.info("Click on groupBy: " + GroupByOptions.USER.value, test);
		List<String> definedGroupByOption = Arrays.asList("user");
		List<Boolean> isMessageContained = chargeBackImpala.validateMessageHaveGroupByValues(definedGroupByOption);
		LOGGER.info("Actual group by options- "+isMessageContained, test);
		LOGGER.pass("Validated the group by options in group by table", test);
		Assert.assertFalse(isMessageContained.contains(false), "Message is not displayed with set filter under pie-chart");
		chargeBackImpala.remove1stGroupByOption();
		chargeBackImpala.validateGroupByPieCharts();
		chargeBackImpala.selectGroupBy(GroupByOptions.TEAM);
		LOGGER.info("Click on groupBy: " + GroupByOptions.TEAM.value, test);
		LOGGER.pass("Validated whether pie charts displayed group by data", test);
		definedGroupByOption = Arrays.asList("team");
		LOGGER.info("Expected group by options- "+definedGroupByOption, test);
		isMessageContained = chargeBackImpala.validateMessageHaveGroupByValues(definedGroupByOption);
		LOGGER.info("Actual group by options- "+isMessageContained, test);
		LOGGER.pass("Validated the group by options in group by table", test);
		Assert.assertFalse(isMessageContained.contains(false), "Message is not displayed with set filter under pie-chart");
		LOGGER.pass("Validated whether data is displayed for impala", test);
		Assert.assertTrue(chargeBackImpala.validatePieChartPresence(cbPageObject.jobPie));
		LOGGER.pass("Validated whether data is displayed for impala chargeback jobs pie-chart", test);
	}
}
