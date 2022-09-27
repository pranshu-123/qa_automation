package com.qa.testcases.emr.Cost.Budget;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.Cost.ChargebackPageObject;
import com.qa.scripts.emr.Cost.Budget;
import com.qa.scripts.emr.Cost.Chargeback;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;
@Marker.EmrCostChargeback
public class TC_EMR_Budget_01 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_EMR_Budget_01.class.getName());

    @Test
    public void TC_EMR_Budget_01_VerifyBudgetPage() throws InterruptedException {
        test = extent.startTest("TC_EMR_Budget_01_VerifyBudgetPage", "Validation of Budget Page elements");
        test.assignCategory("Cost/Chargeback");
        Log.startTestCase("TC_EMR_Budget_01_VerifyBudgetPage");
        String[] expectedBudgetTitle = {"Details","Scope details","Budget details"};
        Chargeback chargeback=new Chargeback(driver);
        Budget budget = new Budget(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargebackPageObject chargebackPageObject=new ChargebackPageObject(driver);
        chargeback.NavigateToCostTab("Budget");
        waitExecuter.sleep(2000);
        LOGGER.info("Navigated to Budget page");
        LOGGER.info("Clicking on scope dropdown for values validation");
        budget.validateBudgetPage();

        test.log(LogStatus.PASS, "Validated Create Budget page elements");
        LOGGER.info("Validated Create Budget page elements");

    }


}







