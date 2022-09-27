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
public class TC_EMR_CB_04 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_EMR_CB_04.class.getName());

    @Test
    public void TC_EMR_CB_04_VerifyScopedropdown() {
        test = extent.startTest("TC_EMR_CB_04_VerifyScopedropdown", "scope dropdown validation");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_04_VerifyScopedropdown");
       String[] expectedScopeValues = {"Global","Tags"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");
        LOGGER.info("Clicking on scope dropdown for values validation");
      chargeBack.DropdownValidation(expectedScopeValues);
      test.log(LogStatus.PASS, "Scope dropdown has proper values");
        LOGGER.info("Scope dropdown has proper values");
    }


}

