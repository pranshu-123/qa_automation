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
public class TC_AA138 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA138.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="Verify that each function of AA by adding more than 2 RULE SET")
    public void validate2RuleSetWithOR(String clusterId) {
        test = extent.startTest("TC_AA138.validate2RuleSetWithOR",
                "Validate the behaviour of AA by adding more than 2 RULESETS");
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

        String policyName = "testPolicyTwoRuleSetWithOR";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO, "Fill new auto action policy details");
        aa.enterRuleSetInPolicyWithOption("Queue", "3", "OR");
        test.log(LogStatus.INFO, "Added 2nd RuleSet policy with OR option");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
                policyName + " not found.");
        test.log(LogStatus.PASS, "Validated 2 RuleSets with OR option on New Auto Action Policy page");

    }
}
