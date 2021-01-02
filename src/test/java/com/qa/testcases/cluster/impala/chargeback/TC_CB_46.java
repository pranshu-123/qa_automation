package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.enums.chargeback.ImpalaJobTableColumn;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
public class TC_CB_46 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_46.class);
    /**
     * Validate table sorting - By Start Time
     */
    @Test
    public void validateSortByStartTime() {
        test =
            extent.startTest("TC_CB_46.validateSortByStartTime","Validate table sorting - By Start Time");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Click on impala chargeback", test);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        LOGGER.info("Select last 90 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.START_TIME);
        LOGGER.info("Click on Start time.", test);
        Boolean isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.START_TIME, true);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.START_TIME);
        LOGGER.info("Click again on Start Time.", test);
        isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.START_TIME, false);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}