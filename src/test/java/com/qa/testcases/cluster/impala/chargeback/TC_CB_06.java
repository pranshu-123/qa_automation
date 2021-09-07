package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_06 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_CB_06.class.getName());

    /**
     * Verify the user can select custom date ranges
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user can select the custom date range.")
    public void TC_CB_06_VerifyCustomDateRange(String clusterId) {
        test = extent.startTest("TC_CB_06_VerifyCustomDateRange: " + clusterId, "Verify the user can select custom date ranges");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        //Cluster selected
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.waitUntilPageFullyLoaded();

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);

        CommonPageObject commonPageObject = new CommonPageObject(driver);
        commonPageObject.clusterDropdown.click();
        waitExecuter.sleep(2000);

        long requestTime = System.currentTimeMillis();
        waitExecuter.sleep(2000);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectCustomRange();
        datePicker.setCurrentAndPastDate(-3);
        waitExecuter.sleep(3000);
        // Click on apply button of Cluster
        LOGGER.info("Click on apply button");
        test.log(LogStatus.INFO, "Click on apply button");
        datePicker.clickOnCustomDateApplyBtn();
        waitExecuter.sleep(1000);
        // Get the set start and end date from calendar
        LOGGER.info("Get date from date picker tab");
        String actual_date = datePicker.getSetDateFromCalendar();
        // Convert the set date format to the output format of calendar
        LOGGER.info("Convert the date in expected format");
        String expected_date = datePicker.convertedSetCurrentAndPastDate(-3);
        LOGGER.info("Expected date"+expected_date);
        waitExecuter.sleep(1000);

        LOGGER.info("Verify calendar tab");
        test.log(LogStatus.INFO, "Check if date is displayed in calendar tab");
        // Check if the date is displayed in calendar
        Assert.assertTrue(datePicker.isDateDisplayedInCalendar(),
                "Calendar Date is not displayed as set " + actual_date);
        test.log(LogStatus.PASS, "Start Date and end date is displayed in calendar tab successfully");

        LOGGER.info("Validate the set date");
        test.log(LogStatus.INFO, "Check if the date set by user is properly set on calendar tab");
        // Check if set date is equal to the get date of UI
        Assert.assertTrue(actual_date.equals(expected_date), "Set Date is not displayed in calendar bar " + actual_date);
        test.log(LogStatus.PASS, "Set start and end date is successfully verified with calendar date displayed on UI");

    }
}
