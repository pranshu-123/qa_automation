package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
public class TC_CB_07 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_07.class);

    /**
     * Verify the data displayed by Unravel UI when the user
     * selects a custom date range where Impala data is not available
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyImpalaWithNonExistingDateRange(String clusterId) {
        test = extent.startTest("TC_CB_07.VerifyImpalaWithNonExistingDateRange", "Verify the data displayed by " +
            "Unravel UI when the user selects a custom date range where Impala data is not available.");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Navigate to impala chargeback page", test);

        //Select Cluster
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        LOGGER.info("Selected cluster: " + clusterId, test);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectCustomRange();
        LOGGER.info("Select custom date range", test);
        datePicker.setStartDate("01/01/2000");
        LOGGER.info("Select start date: 01/01/2000", test);
        datePicker.setEndDate("01/01/2001");
        LOGGER.info("Select end date: 01/01/2001", test);
        datePicker.clickOnCustomDateApplyBtn();
        Assert.assertEquals(chargeBackImpala.getNoDataElementsImpalaChargeBack().size(), 5,
            "Some charts or table are displaying data.");
        LOGGER.pass("'No Data Available' displayed.", test);
    }
}
