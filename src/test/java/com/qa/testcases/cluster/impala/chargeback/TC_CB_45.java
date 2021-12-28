package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.enums.chargeback.ImpalaJobTableColumn;
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
public class TC_CB_45 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_45.class);

    /**
     * Validate table sorting - By App Name / ID
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P1-Verify that the tables should sorted by App Name / ID ascending and descending order")
    public void validateSortByAppNameAndID(String clusterId) {
        test =
            extent.startTest("TC_CB_45.validateSortByAppNameAndID", "Validate table sorting - By App Name / ID");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        LOGGER.info("Click on impala chargeback", test);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
      	chargeBackImpala.clickOnGroupBySearchBox();
   	chargeBackImpala.closeGroupByOptionsExcept(GroupByOptions.INPUT_TABLES);
        LOGGER.info("Select last 90 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.APP_NAME_ID);
        LOGGER.info("Click on App Name / ID.", test);
        Boolean isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.APP_NAME_ID, true);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        chargeBackImpala.clickOnGroupBySearchBox();
    chargeBackImpala.closeGroupByOptionsExcept(GroupByOptions.INPUT_TABLES);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.APP_NAME_ID);
        LOGGER.info("Click again on App Name / ID.", test);
        isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.APP_NAME_ID, false);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}