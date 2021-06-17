package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.ImpalaJobTableColumn;
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
public class TC_CB_42 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_41.class);
    /**
     * Validate table sorting - By user
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P1-Verify that the tables should sorted by User ascending and descending order.")
    public void validateSortByUser(String clusterId) {
        test =
            extent.startTest("TC_CB_42.validateSortByUser","Verify the sorting of tables By User.");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        ChargebackImpalaPageObject chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Click on impala chargeback", test);
        DatePicker datePicker = new DatePicker(driver);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilPageFullyLoaded();
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        LOGGER.info("Select last 30 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.USER);
        LOGGER.info("Click on user.", test);
        Assert.assertTrue(chargebackImpalaPageObject.userSortDown.isDisplayed(), "Data down is not working.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.USER);
        LOGGER.info("Click again on user.", test);
        Assert.assertTrue(chargebackImpalaPageObject.userSortUp.isDisplayed(), "Data up is not working.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}