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
public class TC_CB_11 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_11.class);

    /**
     * Validate Charge Back report when Grouped by "Real User"
     * @param clusterId - ClusterId to select for test execution
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateGroupByRealUserImpalaChargeback(String clusterId) {
        test = extent.startTest("TC_CB_11.validateGroupByRealUserImpalaChargeback", "Validate Charge Back report when" +
            " Grouped by \"Real User\"");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        ChargebackImpalaPageObject cbImpalaPageObject = new ChargebackImpalaPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Navigate to impala chargeback page", test);
        //Select Cluster
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        LOGGER.info("Selected cluster: " + clusterId, test);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        datePicker.selectLast90Days();
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        LOGGER.info("Select last 90 days", test);
        chargeBackImpala.clickOnGroupBySearchBox();
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        chargeBackImpala.selectGroupBy(GroupByOptions.REAL_USER);
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        LOGGER.info("Click on groupBy: " + GroupByOptions.REAL_USER.value, test);
        chargeBackImpala.validateGroupByPieCharts();
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        LOGGER.pass("Validated whether pie charts displayed group by data", test);
        chargeBackImpala.validateGroupByOptions();
        waitExecuter.waitUntilElementClickable(cbImpalaPageObject.chargeBackDropdownOptionsButton);
        LOGGER.pass("Validated the group by options in group by table", test);
        Assert.assertTrue(chargeBackImpala.getImpalaJobsTableRecord().size()>0, "Data is not displayed");
        LOGGER.pass("Validated whether data is displayed for impala", test);
    }
}
