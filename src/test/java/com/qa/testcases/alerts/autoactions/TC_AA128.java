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
public class TC_AA128 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA128.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="Validate the behavior - select the HTTP Post action and register AA without adding URL.")
    public void validateHttpPostAction(String clusterId) {
        test = extent.startTest("TC_AA128.validateHttpPostAction",
                "Validate the behaviour - select HTTP Post action and save AA without adding URL");
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

        String policyName = "testPolicyhttpPostWithOutUrlAction";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO, "Fill new auto action policy details, without value");

        String inputAction = "Http Post";
        String httpPostUrl = "";
        aa.enterHttpPostUrl(inputAction,httpPostUrl);
        test.log(LogStatus.INFO,"Clicked action HttpPostUrl");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        String expectedErrMsgText = "error: \"\"http_post\" action is missing \"url\" or \"urls\" field\"";
        Assert.assertTrue(aa.verifyErrorMsg(expectedErrMsgText),"Error Msg not found.");
        test.log(LogStatus.PASS, "Validated Http Post action without adding url, on New Auto Action Policy page");

    }
}
