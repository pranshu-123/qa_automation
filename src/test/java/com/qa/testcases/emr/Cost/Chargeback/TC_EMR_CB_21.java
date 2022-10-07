package com.qa.testcases.emr.Cost.Chargeback;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.cost.ChargebackPageObject;
import com.qa.scripts.emr.Cost.Chargeback;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;
@Marker.EmrCostChargeback
public class TC_EMR_CB_21 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_EMR_CB_21.class.getName());

    @Test
    public void TC_EMR_CB_21_VerifyBudgetforName() throws InterruptedException {
        test = extent.startTest("TC_EMR_CB_21_VerifyBudgetforName", "Validation of Budget for Name");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_CB_21_VerifyBudgetforName");
        String[] expectedScopeValues = {"Global","Tags"};
        String[] expectedGraphValues = {"Cost","EC2","EMR","EBS","Cluster count"};
        String[] expectedMultiselectedTags = {"owner","Owner","CreatedBy","POC"};
        String[] expectedBudgetTitle = {"Details","Scope details","Budget details"};
        Chargeback chargeBack = new Chargeback(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargebackPageObject chargebackPageObject=new ChargebackPageObject(driver);
        chargeBack.NavigateToCostTab("Chargeback");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Chargeback page");
        LOGGER.info("Clicking on scope dropdown for values validation");
        chargeBack.DropdownValidation(expectedScopeValues);
        chargebackPageObject.ScopeDropdownButton.click();
        chargeBack.SelectGlobal();
        waitExecuter.sleep(2000);
        Assert.assertTrue(chargebackPageObject.ScopeSelected.isDisplayed(),"Global is not selected in scope dropdown");
        LOGGER.info("User is able to select Global in scope dropdown");
        LOGGER.info("Global is selected in scope dropdown");
        chargeBack.SelectCBbyTag(expectedMultiselectedTags);
        chargeBack.SelectNameinCb_byTag();
        LOGGER.info("User is able to select Name in CB by Tag key");
        chargeBack.ValidatePieChartGraph(expectedGraphValues);
        chargeBack.ValidateResultSetIsDisplayedWithValues();
        LOGGER.info("Validated the result corresponding to Name in Chargeback by Tag key");

        chargeBack.NavigateCreateBudget();
        chargeBack.ValidateCreateBudget(expectedBudgetTitle);
        test.log(LogStatus.PASS, "Validated Create Budget page corresponding to Name");
        LOGGER.info("Validated Create Budget page corresponding to Name");

    }


}







