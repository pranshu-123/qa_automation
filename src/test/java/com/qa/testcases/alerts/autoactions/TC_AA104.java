package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.Alerts
@Marker.All
public class TC_AA104 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_AA104.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateDescriptionNameAsSpecialChars(String clusterId) {
        test = extent.startTest("TC_AA104.validateDescriptionNameAsSpecialChars", "Verify user can set" +
                " a description using special characters and save the auto action.");
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
        String policyName = "!@#$%";
        String policyDescription = "!@#$%";
        aa.enterNewAutoActionPolicyDetails(policyName,"User", "3",policyDescription);
        test.log(LogStatus.INFO,"Fill new auto action policy details");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Click on save button");
        logger.info("Filled New Auto Action Policy details and clicked on save button");

        Assert.assertTrue(aa.validateAutoActionAdded(policyName), "Newly added Policy: "+
                policyName + " not found.");
        test.log(LogStatus.PASS, "New policy with description name as special chars added successfully " +
                "on alerts auto action page.");

    }
}
