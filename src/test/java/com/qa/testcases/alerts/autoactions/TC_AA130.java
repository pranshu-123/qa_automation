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
public class TC_AA130 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA130.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validatePostToSlackActionWithOutUrl(String clusterId) {
        test = extent.startTest("TC_AA130.validatePostToSlackAction",
                "Validate behaviour of Post to Slack without adding Slack token and webhook URL");
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

        String policyName = "testPolicyPostToSlackWithOutUrlAction";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO, "Fill new auto action policy details, without value");

        String inputAction = "Post To Slack";
        String postToSlackUrl = "";
        String postToSlackToken = "";
        aa.enterPostToSlackUrl(inputAction, postToSlackUrl, postToSlackToken);
        test.log(LogStatus.INFO,"Clicked action Post To Slack");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        Assert.assertTrue(aa.verifyErrorMsgForPostToSlackAction(),"Error Msg not found.");
        test.log(LogStatus.PASS, "Validated Post to Slack without adding Slack token and webhook URL on New Auto Action Policy page");

    }
}
