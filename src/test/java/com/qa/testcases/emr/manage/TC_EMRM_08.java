package com.qa.testcases.emr.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.emr.manage.EmrSub_Manage_TopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.emr.manage.EmrManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M08;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_08 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_M08.class.getName());

    /*
     * Verify Run Diagnostics page in Manage tab
     */
    @Test()
    public void validateRunDiagnosticsTabAndLoadLatestDiagnostics() {
        test = extent.startTest("TC_EMRM_08.validateRunDiagnosticsTabAndLoadLatestDiagnostics", "Verify " +
                "Run Diagnostics page in Manage tab");
        test.assignCategory("Emr_Manage");


        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSub_Manage_TopPanelModulePageObject subTopPanelModulePageObject = new EmrSub_Manage_TopPanelModulePageObject(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        EmrManage manage = new EmrManage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header is not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");
        logger.info("Verified Manage Page is loaded successfully.");

        //Click on Run Diagnostics tab and validate header
        waitExecuter.sleep(2000);
        manage.clickRunDiagnostics();
        test.log(LogStatus.INFO, "Clicked on Run Diagnostics Tab.");
        logger.info("Clicked on Run Diagnostics Tab.");
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.validateRunDiagnosticsHeader1(), "Run Diagnostics Header is not present.");
        test.log(LogStatus.INFO, "Verified Run Diagnostics Tab.");
        logger.info("Verified Run Diagnostics Tab.");

        manage.clickOnLoadLatestDiagnostics();
        waitExecuter.sleep(8000);
        waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
        test.log(LogStatus.INFO, "Clicked on Load Latest Diagnostics Button.");
        logger.info("Clicked on Load Latest Diagnostics Button.");
        waitExecuter.waitUntilPageFullyLoaded();

        try {
            waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
            waitExecuter.sleep(8000);
            waitExecuter.waitUntilElementPresent(managePageObject.latestDiagnosticsContentHeader);
            waitExecuter.sleep(4000);
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.latestDiagnosticsContentHeader, "Diagnostics Log");
            waitExecuter.sleep(9000);
            waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
            test.log(LogStatus.INFO, "Verified Diagnostics Log is loaded properly.");
            waitExecuter.waitUntilElementPresent(managePageObject.latestDiagnosticsContentHeader);
            waitExecuter.sleep(9000);
            waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
            Assert.assertTrue(managePageObject.latestDiagnosticsContentHeader.isDisplayed(), "Diagnostics Logs " +
                    "are not found.");
            waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
            waitExecuter.sleep(9000);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(LogStatus.INFO, "Verified Latest Diagnostics Header and Time Stamp not found.");
            logger.info("Verified Latest Diagnostics Header and Time Stamp not found. ");
            Assert.assertTrue(false, "Latest Diagnostics Header and Time Stamp not found.");
        }
        waitExecuter.waitUntilElementClickable(managePageObject.resetButton);
        Assert.assertTrue(manage.verifyLoadLatestDiagnosticsHeaderAndTimeStamp(), "Latest " +
                "Diagnostics Log not found.");
        test.log(LogStatus.INFO, "Verified Latest Diagnostics Header and Time Stamp.");
        logger.info("Verified Latest Diagnostics Header and Time Stamp.");

        waitExecuter.sleep(5000);
        Assert.assertTrue(manage.verifyLoadLatestDiagnosticsContent(), "latest " +
                "Diagnostics contents are not loaded.");
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(1000);
        test.log(LogStatus.INFO, "Verified Load Latest Diagnostics contents.");
        test.log(LogStatus.PASS, "Verified diagnostics log with time stamp");
        logger.info("Verified diagnostics log with time stamp");

    }
}
