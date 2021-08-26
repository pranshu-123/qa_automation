package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.scripts.alerts.AutoActions;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA100 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_AA100.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P1-Verify user can set a name which is 500 characters long and save the auto action.")
    public void validateNameAs500Chars(String clusterId) {
        test = extent.startTest("TC_AA100.validateNameAs500Chars", "Verify user can set " +
                "a name which is 500 characters long and save the auto action.");
        test.assignCategory(" Alerts ");

        AutoActions aa = new AutoActions(driver);
        AutoActionsPageObject autoActionsPageObject=new AutoActionsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
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
        String policyName = "abcpolicyname0123456789validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500Chars" +
                "validateNameAs500Charsabpolicyname0123456789validateNameAs500CharsvalidateNameAs500Chars" +
                "validateNameAs500CharsvalidateNameAs500Charsabpolicyname0123456789validateNameAs500Chars" +
                "validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500Charsabpolicyname0123456789" +
                "validateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500CharsvalidateNameAs500" +
                "Charsabpolicyname0123456789validateNameAs500CharsvalidateNameAs5";
        aa.enterNewAutoActionPolicyDetails(policyName, "User", "3");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Fill new auto action policy details");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO, "Click on save button");
        logger.info("Filled New Auto Action Policy details and clicked on save button");

        aa.validateBanner();
        Assert.assertFalse(aa.validateAutoActionAdded(policyName), "Newly added Policy name 500 char : " +
                policyName + " able to found.");
        test.log(LogStatus.PASS, "New Auto action policy not added with policy name " +
                "as 500 characters long.");

    }
}