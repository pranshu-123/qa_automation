package com.qa.testcases.manage;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_M14 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M14.class);

    /*
     * Verify Monitoring page in Manage tab and verify zookeeper tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMonitoringTabAndZookeeperTab(String clusterId) {
        test = extent.startTest("TC_M14.validateMonitoringTabAndZookeeperTab", "Verify" +
                " Monitoring  page and zookeeper tab details");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");

        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        waitExecuter.sleep(2000);
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        //Click on Zookeper and validate its details
        waitExecuter.sleep(2000);
        manage.clickZookeperTab();
        test.log(LogStatus.INFO, "Clicked on Zookeper Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateZookeperTableHeaders(),"Zookeper Header columns are not present");

        Assert.assertTrue(manage.verifyZookeperTSAndDataAge(),"Zookeper TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified Zookeper table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with Zookeper details.");

    }
}
