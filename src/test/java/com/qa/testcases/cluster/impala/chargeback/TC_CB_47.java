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
public class TC_CB_47 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_47.class);
    /**
     * Validate table sorting - By Finished Time
     */
    @Test
    public void validateSortByFinishedTime() {
        test =
            extent.startTest("TC_CB_47.validateSortByFinishedTime","Validate table sorting - By Finished Time");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Click on impala chargeback", test);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        LOGGER.info("Select last 90 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.FINISHED_TIME);
        LOGGER.info("Click on Finished time.", test);
        Boolean isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.FINISHED_TIME, true);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.FINISHED_TIME);
        LOGGER.info("Click again on Finished Time.", test);
        isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.FINISHED_TIME, false);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}