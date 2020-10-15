package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.cluster.topx.TC_CTX_20;
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
        test.assignCategory("4620 Manage ");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.gear);
        test.log(LogStatus.PASS, "Verified Manage Tab is clicked.");

        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader,"Daemons","Daemons Header not matched.");

        //Validate tabs under Manage tab.
        Assert.assertTrue(manage.validateAllTabsPresent(), "Missing tabs on Manage Page.");
        test.log(LogStatus.PASS, "Verified Manage Page is loaded successfully.");

    }
}
