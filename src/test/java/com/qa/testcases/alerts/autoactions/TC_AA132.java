package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA132 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA132.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P1-verify the behavior Move to queue without entering queue name")
    public void validateMoveAppToQueueActionWithOutQueue(String clusterId) {
        test = extent.startTest("TC_AA132.validateMoveAppToQueueActionWithOutQueue",
                "Validate behaviour Move to queue without entering queue name");
        test.assignCategory(" Alerts ");

        AutoActions aa = new AutoActions(driver);
        aa.setupForAutoActionsPage();
        test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
        logger.info("Verified Alerts Tab is clicked.");

        //Validate Auto Actions header default
        String aaHeader = aa.getAutoActionsHeader();
        Assert.assertEquals(aaHeader, "Auto Actions", "Auto Actions Header not matched.");
        test.log(LogStatus.INFO, "Verified Auto Actions Header.");
        logger.info("Auto Actions Header found and matched");

        aa.clickOnNewAutoActionBtn();
        test.log(LogStatus.INFO, "clicked on new auto action button");
        Assert.assertTrue(aa.validateNewAutoActionPolicyPageDisplayed(), "New Auto Action Policy page" +
                " not displayed");

        //Close default Refine Scope cluster window on New auto action policy
        aa.closeDefaultRefineScope();

        String policyName = "testPolicyMoveAppToQueueActionWithOutQueue";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO, "Fill new auto action policy details, without value");

        String inputAction = "Move App To Queue";
        String queueName = "";
        aa.enterMoveAppToQueueName(inputAction, queueName);
        test.log(LogStatus.INFO,"Clicked action Move App To Queue");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        String expectedErrMsgText = "error: \"\"move_to_queue\" action is missing \"queue\" field\"";
        Assert.assertTrue(aa.verifyErrorMsg(expectedErrMsgText),"Error Msg not found.");
        test.log(LogStatus.PASS, "Validated Move to queue action without entering queue name on New Auto Action Policy page");

    }
}
