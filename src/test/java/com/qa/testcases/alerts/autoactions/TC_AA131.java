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
public class TC_AA131 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA131.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P1-Verify Post's behavior in Slack without adding any Slack tokens or webhook URLs.")
    public void validatePostToSlackActionWithUrlAndToken(String clusterId) {
        test = extent.startTest("TC_AA131.validatePostToSlackActionWithUrlAndToken",
                "Validate behaviour Post to slack by adding valid Slack token and webhook URL");
        test.assignCategory(" Alerts ");

        AutoActions aa = new AutoActions(driver);
        aa.setupForAutoActionsPage();
        test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
        logger.info("Verified Alerts Tab is clicked.");

        //Validate Auto Actions header default
        String aaHeader = aa.getAutoActionsHeader();
        Assert.assertEquals(aaHeader, "AutoActions", "Auto Actions Header not matched.");
        test.log(LogStatus.INFO, "Verified Auto Actions Header.");
        logger.info("Auto Actions Header found and matched");

        aa.clickOnNewAutoActionBtn();
        test.log(LogStatus.INFO, "clicked on new auto action button");
        Assert.assertTrue(aa.validateNewAutoActionPolicyPageDisplayed(), "New Auto Action Policy page" +
                " not displayed");

        //Close default Refine Scope cluster window on New auto action policy
        aa.closeDefaultRefineScope();

        String policyName = "testPolicyPostToSlackActionWithUrlAndToken";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO, "Fill new auto action policy details, without value");

        String inputAction = "Post To Slack";
        String postToSlackUrl = "http://somewebhookurl.com";
        String postToSlackToken = "0123456789";
        aa.enterPostToSlackUrl(inputAction, postToSlackUrl, postToSlackToken);
        test.log(LogStatus.INFO,"Clicked action Post To Slack");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
                policyName + " not found.");
        test.log(LogStatus.PASS, "Validated  Post to slack by adding valid Slack token and webhook URL" +
                " on New Auto Action Policy page");

    }
}
