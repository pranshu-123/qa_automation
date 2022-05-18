package com.qa.testcases.databricks.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M07;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxManage
@Marker.All
public class TC_DM07 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M07.class);

    /*
     * Verify Stats page in Manage tab and verify Sensor Heartbeat tab
     */
    @Test()
    public void validateSensorHeartbeatStatsTab() {
        test = extent.startTest("TC_DM07.validateSensorHeartbeatStatsTab", "Verify Sensor Heartbeat tab");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject subTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");

        DbxManage manage = new DbxManage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Stats tab and validate header
        waitExecuter.sleep(2000);
        manage.clickStatsTab();
        test.log(LogStatus.INFO, "Clicked on Stats Tab.");
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.validateStatsHeader(), "Stats Header is not present.");
        test.log(LogStatus.INFO, "Verified Stats Tab.");

        //Click on Sensor Heartbeat tab and validate details on the page
        manage.clickSensorHeartbeatTab();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Clicked on Sensor Heartbeat Tab.");
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.verifySensorHeartbeatDetails(),"Sensor Heartbeat table details is not present.");
        test.log(LogStatus.PASS, "Verified Sensor Heartbeat Tab and its details.");

    }
}
