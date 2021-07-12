package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class TC_CB_44 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_44.class);

    /**
     * Validate table sorting - By Real User
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P1-Verify that the tables should sorted by Real User ascending and descending order")
    public void validateSortByRealUser(String clusterId) {
        test =
            extent.startTest("TC_CB_44.validateSortByRealUser", "Validate table sorting - By Real User");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        LOGGER.info("Click on impala chargeback", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        LOGGER.info("Select last 90 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.REAL_USER);
        LOGGER.info("Click on real user.", test);
        Boolean isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.REAL_USER, true);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.REAL_USER);
        LOGGER.info("Click again on real user.", test);
        isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.REAL_USER, false);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}