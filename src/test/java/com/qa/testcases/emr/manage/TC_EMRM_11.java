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
import com.qa.testcases.manage.TC_M11;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_11 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_EMRM_11.class.getName());

    /*
     * Verify Monitoring page in Manage tab
     */
    @Test()
    public void validateMonitoringTabAndDBStatusTab() {
        test = extent.startTest("TC_EMRM_11.validateMonitoringTabAndDBStatusTab", "Verify" +
                " Monitoring  page and DB Status details");
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
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringDBStatusTab, "DB Status");
            test.log(LogStatus.INFO, "Verified DB Status is loaded properly.");
            Assert.assertTrue(managePageObject.monitoringDBStatusTab.isDisplayed(), "DB Status Tab " +
                    "is not found.");
        } catch (TimeoutException e) {
            e.printStackTrace();
            test.log(LogStatus.INFO, "DB Status Tab not found.");
            logger.info("DB Status Tab not found.");
            Assert.assertTrue(false, "DB Status Tab not found.");
        }
        //Click on DB Status and validate its details
        waitExecuter.sleep(2000);
        manage.clickDBStatusTab();
        test.log(LogStatus.INFO, "Clicked on DB Status Tab.");
        logger.info("Clicked on DB Status Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateDBStatusTableHeaders(), "DB Status Header columns are not present");

        Assert.assertTrue(manage.verifyDBStatusTSAndDataAge(), "DB Status TimeStamp and Data Age " +
                "is not displayed");
        test.log(LogStatus.INFO, "Verified DB Status table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with DB Status details.");
        logger.info("Verified Monitoring page with DB Status details.");


    }
}
