package com.qa.testcases.alerts.autoactions;

import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_AA124 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA123.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAlwaysTime(String clusterId) {
        test = extent.startTest("TC_AA123.validateAlwaysTime",
                "Verify user is able to select a specific TIME interval on a daily basis to trigger " +
                        "auto actions when violations occur between that time interval.");
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

        String policyName = "testPolicyDailyTime";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO,"Fill new auto action policy details, without value");

        aa.clickOnRefineScope();
        test.log(LogStatus.INFO,"Clicked on refine scope button");

        String scope = "Time";
        aa.selectRefineScope(scope);
        test.log(LogStatus.INFO,"Selected scope");

        String userScopeChkBoxName = "daily";
        aa.clickScopeChkBox(userScopeChkBoxName);
        test.log(LogStatus.INFO,"Clicked on scope '" + userScopeChkBoxName + "' checkbox");

        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
                policyName + " not found.");
        test.log(LogStatus.PASS, "Validated TIME scope as Daily, on New Auto Action Policy page");

    }

}