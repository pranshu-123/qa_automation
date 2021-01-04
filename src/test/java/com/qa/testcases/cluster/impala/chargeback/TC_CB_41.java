package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
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
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_41 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_41.class);
    /**
     * Validate table sorting - By type
     */
    @Test
    public void validateSortByType() {
        test =
            extent.startTest("TC_CB_41.validateSortByType","Validate table sorting - By type");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();
        LOGGER.info("Click on impala chargeback", test);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        LOGGER.info("Select last 90 days in date picker.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.TYPE);
        LOGGER.info("Click on type.", test);
        Boolean isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.TYPE, true);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in ascending order.", test);
        chargeBackImpala.clickOnTableHeading(ImpalaJobTableColumn.TYPE);
        LOGGER.info("Click again on type.", test);
        isDataSorted = chargeBackImpala.isDataSorted(ImpalaJobTableColumn.TYPE, false);
        Assert.assertTrue(isDataSorted, "Data is not sorted.");
        LOGGER.pass("Data is displayed in descending order.", test);
    }
}
