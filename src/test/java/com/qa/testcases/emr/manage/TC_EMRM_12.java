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
import com.qa.testcases.manage.TC_M12;
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
public class TC_EMRM_12 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_EMRM_12.class);

    /*
     * Verify Monitoring page in Manage tab and verify DB performance tab
     */
    @Test()
    public void validateMonitoringTabAndDBPerformanceTab() {
        test = extent.startTest("TC_EMRM_12.validateMonitoringDBPerformanceTab", "Verify" +
                " Monitoring  page and DB performance details");
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
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringDBPerformanceTab, "DB Performance");
            test.log(LogStatus.INFO, "DB Performance is loaded Successfully.");
            Assert.assertTrue(managePageObject.monitoringDBPerformanceTab.isDisplayed(), "DB Performance Tab " +
                    "is not found.");
        } catch (TimeoutException e) {
            e.printStackTrace();
            test.log(LogStatus.INFO, "DB Performance Tab not found.");
            logger.info("DB Performance Tab not found.");
            Assert.assertTrue(false, "DB Performance Tab not found.");
        }
        //Click on DB Performance and validate its details
        waitExecuter.sleep(2000);
        manage.clickDBPerformanceTab();
        test.log(LogStatus.INFO, "Clicked on DB Performance Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateDBPerformanceTableHeaders(), "DB Performance Header columns are not present");

        Assert.assertTrue(manage.verifyDBPerfromanceTSAndDataAge(), "DB Performance TimeStamp and Data Age " +
                "is not displayed");
        test.log(LogStatus.INFO, "Verified Performance Status table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with DB Performance details.");
    }
}

