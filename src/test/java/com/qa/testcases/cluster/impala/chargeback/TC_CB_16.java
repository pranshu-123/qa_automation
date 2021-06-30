package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_16 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_16.class);

    /**
     * Validate Charge Back report when Grouped by "Project"
     * @param clusterId - ClusterId to select for test execution
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the chargeback report should be present when clicking on grouped by Project.")
    public void validateGroupByProjectImpalaChargeback(String clusterId) {
        test = extent.startTest("TC_CB_16.validateGroupByTeamImpalaChargeback", "Validate Charge Back report " +
            "when Grouped by \"Project\"");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        ChargebackImpalaPageObject cbPageObject = new ChargebackImpalaPageObject(driver);
        WaitExecuter wait = new WaitExecuter(driver);
        chargeBackImpala.selectImpalaChargeback("Impala");
        LOGGER.info("Navigate to impala chargeback page", test);
        //Select Cluster
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        LOGGER.info("Selected cluster: " + clusterId, test);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        wait.waitUntilElementClickable(cbPageObject.chargeBackDropdownOptionsButton);
        datePicker.selectLast90Days();
        wait.waitUntilElementClickable(cbPageObject.chargeBackDropdownOptionsButton);
        LOGGER.info("Select last 90 days", test);
        chargeBackImpala.clickOnGroupBySearchBox();
        wait.waitUntilElementClickable(cbPageObject.chargeBackDropdownOptionsButton);
        chargeBackImpala.selectGroupBy(GroupByOptions.PROJECT);
        wait.waitUntilElementClickable(cbPageObject.chargeBackDropdownOptionsButton);
        LOGGER.info("Click on groupBy: " + GroupByOptions.PROJECT.value, test);
        chargeBackImpala.validateGroupByPieChartOption();
        LOGGER.pass("Validated whether pie charts displayed group by data", test);
        chargeBackImpala.validateGroupByOptions();
        LOGGER.pass("Validated the group by options in group by table", test);
        Assert.assertTrue(chargeBackImpala.getImpalaJobsTableRecord().size()>0, "Data is not displayed");
        LOGGER.pass("Validated whether data is displayed for impala", test);
    }
}