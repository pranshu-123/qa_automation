package com.qa.testcases.emr.Cost.Chargeback;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.Cost.ChargebackPageObject;
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
public class TC_EMR_CB_07 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_EMR_CB_07.class.getName());

    @Test
    public void TC_EMR_CB_07_VerifyMultiTagsDdnvalues() {
        test = extent.startTest("TC_EMR_CB_07_VerifyMultiTagsDdnvalues", "Validation of Multiselect dropdown of tags");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_05_VerifyMultiTagsDdn");
        String[] expectedScopeValues = {"Global","Tags"};
        String[] expectedMultiselectedTags = {"owner","CreatedBy","Owner","Purpose","Retention","Tenure","PILLAR"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargebackPageObject chargebackPageObject=new ChargebackPageObject(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");
        LOGGER.info("Clicking on scope dropdown for values validation");
        chargeBack.DropdownValidation(expectedScopeValues);
        chargeBack.SelectTags();
        LOGGER.info("User is able to select Tags in scope dropdown");
        waitExecuter.sleep(2000);
        Assert.assertTrue(chargebackPageObject.MultiselectTags.isDisplayed(),"Multiselect Tags value is not visible.");
        LOGGER.info("Tags Multiselect dropdown is visible.");
        chargeBack.TagsMultiSelect(expectedMultiselectedTags);

        test.log(LogStatus.PASS, "Muliselect Tag Dropdown is displaying proper values as expected");
        LOGGER.info("Muliselect Tag Dropdown is displaying proper values as expected");
    }


}

