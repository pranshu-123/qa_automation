package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Birender Kumar
 */

@Marker.Manage
@Marker.All
public class TC_M01 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateManageTabGenerated(String clusterId) {
        test = extent.startTest("TC_M01.validateManageTabGenerated", "Verify all page is working fine in Manage tab");
        test.assignCategory(" Manage ");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);

        test.log(LogStatus.PASS, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader,"Daemons","Daemons Header not matched.");

        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.daemeons);

        //Validate tabs under Manage tab.
        Assert.assertTrue(manage.validateAllTabsPresent(), "Missing tabs on Manage Page.");
        test.log(LogStatus.PASS, "Verified Manage Page is loaded successfully.");
        logger.info("Verified Manage Page is loaded successfully.");

    }
}
