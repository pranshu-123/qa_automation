package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.scripts.alerts.AutoActions;
import com.qa.testcases.jobs.applications.inefficientapps.TC_JIA02;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA95 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_AA95.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P1-Verify that user can define a name using alphabets and save the automatic action.")
    public void validateNameAsAlphabets(String clusterId) {
        test = extent.startTest("TC_AA95.validateNameAsAlphabets", "Verify user can set " +
                "a name using alphabets and save the auto action.");
        test.assignCategory(" Alerts ");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.alerts);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.alerts);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.alerts);
        test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
        logger.info("Verified Alerts Tab is clicked.");

        AutoActions aa = new AutoActions(driver);
        //Validate Auto Actions header default
        String aaHeader = aa.getAutoActionsHeader();
        Assert.assertEquals(aaHeader, "Auto Actions", "Auto Actions Header not matched.");
        test.log(LogStatus.INFO, "Verified Auto Actions Header.");
        logger.info("Auto Actions Header found and matched");

        aa.clickOnNewAutoActionBtn();
        test.log(LogStatus.INFO, "clicked on new auto action button");
        Assert.assertTrue(aa.validateNewAutoActionPolicyPageDisplayed(), "New Auto Action Policy page" +
                " not displayed");
        String policyName = "policy1";
        aa.enterNewAutoActionPolicyDetails(policyName,"User", "3");
        test.log(LogStatus.INFO,"Fill new auto action policy details");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Click on save button");
        logger.info("Filled New Auto Action Policy details and clicked on save button");

        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Newly added Policy: "+
                policyName + " not found.");
        test.log(LogStatus.PASS, "New policy added successfully on alerts auto action page.");

    }

}
