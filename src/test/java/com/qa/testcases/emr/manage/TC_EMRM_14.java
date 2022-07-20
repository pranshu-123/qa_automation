package com.qa.testcases.emr.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.manage.DbxManagePageObject;
import com.qa.pagefactory.emr.manage.EmrSub_Manage_TopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.emr.manage.EmrManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M14;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_14 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_EMRM_14.class);


    @Test()
    public void validateMonitoringTabAndZookeeperTab() {
        test = extent.startTest("TC_EMRM_14.validateMonitoringZookeeperTab", "Verify" +
                " Monitoring  page and zookeeper tab details");
        test.assignCategory("Emr_Manage");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSub_Manage_TopPanelModulePageObject subTopPanelModulePageObject = new EmrSub_Manage_TopPanelModulePageObject(driver);
        EmrManage manage = new EmrManage(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header is not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        logger.info("Clicked on Monitoring Tab.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");


        try {
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringZookeeperTab, "Zookeeper");
            test.log(LogStatus.INFO, "Zookeeper tab is loaded properly.");
            Assert.assertTrue(managePageObject.monitoringZookeeperTab.isDisplayed(), "Zookeper Tab " +
                    "is not found.");
        } catch (TimeoutException e) {
            e.printStackTrace();
            test.log(LogStatus.INFO, "Zookeeper Tab not found.");
            logger.info("Zookeeper Tab not found.");
            Assert.assertTrue(false, "Zookeeper Tab not found.");
        }
        //Click on Zookeper and validate its details
        waitExecuter.sleep(2000);
        manage.clickZookeperTab();
        test.log(LogStatus.INFO, "Clicked on Zookeeper Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateZookeperTableHeaders(), "Zookepeer Header columns are not present");

        Assert.assertTrue(manage.verifyZookeperTSAndDataAge(), "Zookeeper TimeStamp and Data Age " +
                "is not displayed");
        test.log(LogStatus.INFO, "Verified Zookeeper table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with Zookeeper details.");

    }
}
