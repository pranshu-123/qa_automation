package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA111 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_AA111.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P1-Verify that the user can select EVERYONE as the user, record the auto action.")
    public void validateEveryOneAsUser(String clusterId) {
        test = extent.startTest("TC_AA111.validateEveryOneAsUser",
                "Verify user is able to select EVERYONE as user, save the auto action.");
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

        String policyName = "testPolicy";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        test.log(LogStatus.INFO,"Fill new auto action policy details, without value");

        aa.clickOnRefineScope();
        test.log(LogStatus.INFO,"Clicked on refine scope button");

        String scope = "User";
        aa.selectRefineScope(scope);
        test.log(LogStatus.INFO,"Selected scope");
        Assert.assertFalse(aa.validateDefaultAllUserScopeChkBox(), "all check box in refine scope is not checked");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Click on save button");
        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
                policyName + " not found.");
        test.log(LogStatus.PASS, "Validated EVERYONE or all user selected as scope on New Auto Action Policy page");

    }
}
