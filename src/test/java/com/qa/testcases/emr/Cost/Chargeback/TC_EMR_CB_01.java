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
public class TC_EMR_CB_01 extends BaseClass {

        private static final Logger LOGGER = Logger.getLogger(DC_02.class.getName());

        @Test
        public void TC_EMR_CB_01_VerifyCalender() {
        test = extent.startTest("TC_EMR_CB_01_VerifyCalender", "user is able to select date ranges to see the corresponding data.");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_01_VerifyCalender");
        String[] expectedGraphValues = {"Cost","EC2","EMR","EBS","Cluster count"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");
        datePicker.clickOnDatePickerForCost();
                waitExecuter.sleep(1000);
                datePicker.clickOnCustomDateApplyBtnCost();
               chargeBack.ValidatePieChartGraph(expectedGraphValues);
               chargeBack.ValidateResultSetIsDisplayedWithValues();

        test.log(LogStatus.PASS, "Result is correctly displayed for the selected dates");
        LOGGER.info("Result is correctly displayed for the selected dates");
    }


}
