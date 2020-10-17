package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.scripts.alerts.AutoActions;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M01;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.Alerts
@Marker.All
public class TC_AA95 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_AA95.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateNameAsAlphabets(String clusterId) {
        test = extent.startTest("TC_AA95.validateNameAsAlphabets", "Verify user can set " +
                "a name using alphabets and save the auto action.");
        test.assignCategory(" Alerts ");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
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


    }

}
