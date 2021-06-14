package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.DateUtils;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_08 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_08.class);

    /**
     * Verify the user is not allowed to select a furture date range
     * @param clusterId - ClusterId to select for test execution
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user should not be allowed to pick a future date range")
    public void verifyUserCanSelectFutureDate(String clusterId) throws ParseException {
        test = extent.startTest("TC_CB_08.verifyUserCanSelectFutureDate", "Verify the user is not allowed to select a furture date range");
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
        datePicker.setStartDate(DateUtils.getCurrentDate());
        datePicker.setStartAndEndDateWithDaysDifference(-10,10);
        datePicker.clickOnCustomDateApplyBtn();
        String date = DateUtils.getDateWithDayDifference(10);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date inputDate = dateFormatter.parse(date);
        DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        String expectedDate = fmt.format(inputDate);
        LOGGER.info("Passed future date as end date: " + expectedDate, test);
        Assert.assertFalse(datePicker.getDefaultDate().contains(expectedDate), "User is able to apply future date in " +
            "the datepicker.");
        LOGGER.pass("User is not able to apply future date in date picker option", test);
    }
}
