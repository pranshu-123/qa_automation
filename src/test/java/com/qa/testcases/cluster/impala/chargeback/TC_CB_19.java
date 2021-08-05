package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_19 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_19.class);

    /**
     * Validate Charge Back report when Grouped by "inputTables"
     * @param clusterId - ClusterId to select for test execution
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the chargeback report should be present when clicking on grouped by inputTables.")
    public void validateGroupByInputTablesImpalaChargeback(String clusterId) {
        test = extent.startTest("TC_CB_19.validateGroupByInputTablesImpalaChargeback", "Validate Charge Back report " +
            "when Grouped by \"inputTables\"");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Navigate to impala chargeback page", test);
        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        LOGGER.info("Select last 30 days", test);
        chargeBackImpala.clickOnGroupBySearchBox();
        chargeBackImpala.selectGroupBy(GroupByOptions.INPUT_TABLES);
        LOGGER.info("Click on groupBy: " + GroupByOptions.INPUT_TABLES.value, test);
        //chargeBackImpala.remove1stGroupByOption();
        chargeBackImpala.validateJobsPieCharts();
        LOGGER.pass("Validated whether pie charts displayed group by data", test);
        chargeBackImpala.validateGroupByOptions();
        LOGGER.pass("Validated the group by options in group by table", test);
        Assert.assertTrue(chargeBackImpala.getImpalaJobsTableRecord().size()>0, "Data is not displayed");
        LOGGER.pass("Validated whether data is displayed for impala", test);
    }
}