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
public class TC_AA117 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_AA114.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="Verify that the user is capable of selecting all clusters, save the auto action.")
    public void validateAllCluster(String clusterId) {
        test = extent.startTest("TC_AA114.validateAllCluster",
                "Verify user is able to select all clusters, save the auto action.");
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

        String policyName = "testPolicyClusterAll";
        aa.enterNewAutoActionPolicyDetails(policyName, "Cluster", "3");
        test.log(LogStatus.INFO,"Fill new auto action policy details, without value");

        aa.clickOnRefineScope();
        test.log(LogStatus.INFO,"Clicked on refine scope button");

        String scope = "Cluster";
        aa.selectRefineScope(scope);
        test.log(LogStatus.INFO,"Selected scope");

        String userScopeChkBoxName = "all";
        aa.clickScopeChkBox(userScopeChkBoxName);
        test.log(LogStatus.INFO,"Clicked on scope '" + userScopeChkBoxName + "' checkbox");

        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Clicked on save button");
        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Policy: " +
                policyName + " not found.");
        test.log(LogStatus.PASS, "Validated 'all' cluster, selected as scope on New Auto Action Policy page");

    }

}
