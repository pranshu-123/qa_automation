package com.qa.testcases.emr.Cost.Chargeback;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.cost.ChargebackPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.Cost.Chargeback;
import com.qa.testcases.databricks.cost.chargeback.DC_02;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.EmrCostChargeback
public class TC_EMR_CB_06 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_EMR_CB_06.class.getName());

    @Test
    public void TC_EMR_CB_05_VerifyTagsasScope() {
        test = extent.startTest("TC_EMR_CB_06_VerifyGlobalasScope", "Global in scope dropdown validation");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_06_VerifyTagsasScope");
        String[] expectedScopeValues = {"Global","Tags"};
        String[] expectedGraphValues = {"Cost","EC2","EMR","EBS","Cluster count"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargebackPageObject chargebackPageObject=new ChargebackPageObject(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");
        LOGGER.info("Clicking on scope dropdown for values validation");
        chargeBack.DropdownValidation(expectedScopeValues);
        chargeBack.SelectGlobal();
        LOGGER.info("User is able to select Global in scope dropdown");
        waitExecuter.sleep(2000);
        Assert.assertFalse(chargebackPageObject.MultiselectTags.isDisplayed(),"Tags Multiselect dropdown is visible");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();
        test.log(LogStatus.PASS, "Global is selected in scope dropdown and is displaying proper data");
        LOGGER.info("Global is selected in scope dropdown and is displaying proper data");
    }


}

