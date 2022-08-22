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
import com.relevantcodes.extentreports.LogStatus;
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
    public void TC_CB_16_validateGroupByProjectImpalaChargeback(String clusterId) {
        test = extent.startTest("TC_CB_16.validateGroupByTeamImpalaChargeback", "Validate Charge Back report " +
            "when Grouped by \"Project\"");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargebackImpalaPageObject cbPageObject = new ChargebackImpalaPageObject(driver);
        WaitExecuter wait = new WaitExecuter(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Navigate to impala chargeback page", test);
        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);
        homePage.selectMultiClusterIdClusterPage(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        wait.waitUntilElementClickable(cbPageObject.impalaDropdownOption);
        datePicker.selectLast90Days();
        wait.waitUntilElementClickable(cbPageObject.impalaDropdownOption);
        LOGGER.info("Select last 90 days", test);
        chargeBackImpala.clickOnGroupBySearchBox();
        wait.waitUntilElementClickable(cbPageObject.impalaDropdownOption);
        chargeBackImpala.selectGroupBy(GroupByOptions.PROJECT);
        wait.waitUntilElementClickable(cbPageObject.impalaDropdownOption);
        LOGGER.info("Click on groupBy: " + GroupByOptions.PROJECT.value, test);
        chargeBackImpala.validateGroupByPieChartOption();
        LOGGER.pass("Validated whether pie charts displayed group by data", test);
        chargeBackImpala.validateGroupByOptions();
        LOGGER.pass("Validated the group by options in group by table", test);
        Assert.assertTrue(chargeBackImpala.getImpalaJobsTableRecord().size()>0, "Data is not displayed");
        LOGGER.pass("Validated whether data is displayed for impala", test);
    }
}