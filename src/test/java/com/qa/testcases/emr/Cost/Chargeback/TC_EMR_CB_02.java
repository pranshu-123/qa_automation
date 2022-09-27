package com.qa.testcases.emr.Cost.Chargeback;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.Cost.Chargeback;
import com.qa.testcases.databricks.cost.chargeback.DC_02;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EmrCostChargeback
public class TC_EMR_CB_02 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(DC_02.class.getName());

    @Test
    public void TC_EMR_CB_02_VerifyDateOptions() {
        test = extent.startTest("TC_EMR_CB_02_VerifyCalender", "user is able to select given date options to see the corresponding data.");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_02_VerifydateOptions");
        String[] expectedGraphValues = {"Cost","EC2","EMR","EBS","Cluster count"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");

        //click on today and verify the data for the same
        chargeBack.ClickOnGivenDateRange("Today");
        LOGGER.info("clicked on Today option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 7D and verify the data for the same

        chargeBack.ClickOnGivenDateRange("7D");
        LOGGER.info("clicked on 7D option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 14D and verify the data for the same

        chargeBack.ClickOnGivenDateRange("14D");
        LOGGER.info("clicked on 14D option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 1M and verify the data for the same

        chargeBack.ClickOnGivenDateRange("1M");
        LOGGER.info("clicked on 1M option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 3M and verify the data for the same

        chargeBack.ClickOnGivenDateRange("3M");
        LOGGER.info("clicked on 3M option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 6M and verify the data for the same

        chargeBack.ClickOnGivenDateRange("6M");
        LOGGER.info("clicked on 6M option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        //click on 1Y and verify the data for the same

        chargeBack.ClickOnGivenDateRange("1Y");
        LOGGER.info("clicked on 1Y option below calender");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();

        test.log(LogStatus.PASS, "Result is correctly displayed for the given date options below calender.");
        LOGGER.info("Result is correctly displayed for the given date options below calender.");
    }


}
