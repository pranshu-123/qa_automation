package com.qa.testcases.emr.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.emr.manage.EmrSub_Manage_TopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.emr.manage.EmrManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M11;
import com.qa.utils.FileUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_09 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_EMRM_09.class.getName());

    /*
     * Verify Run Diagnostics page in Manage tab and Download Support Bundle
     */
    @Test()
    public void validateRunDiagnosticsTabAndDownloadSupportBundle() {
        test = extent.startTest("TC_EMRM_09.validateRunDiagnosticsTabAndDownloadSupportBundle", "Verify " +
                "Run Diagnostics page and Download Support Bundle in Manage tab");
        test.assignCategory("Emr_Manage");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSub_Manage_TopPanelModulePageObject subTopPanelModulePageObject = new EmrSub_Manage_TopPanelModulePageObject(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        UserActions userActions = new UserActions(driver);
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
        Assert.assertTrue(manage.validateRunDiagnosticsHeader1(), "Run Diagnostics Header is not  present.");
        test.log(LogStatus.INFO, "Verified Run Diagnostics Tab.");
        logger.info("Verified Run Diagnostics Tab.");


        manage.clickOnDownloadSupportBundle();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Download Support Bundle button.");
        logger.info("Clicked on Download Support Bundle button.");

        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(4000);
        userActions.performActionWithPolling(managePageObject.downloadSupportBundleBtn, UserAction.CLICK);
        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("diagnostics.txt"), "Downloaded " +
                "not working for Support Bundle File.");
        waitExecuter.sleep(7000);
        test.log(LogStatus.PASS, "Verified Run Diagnostics page and Download Support Bundle File");
        logger.info("Verified Run Diagnostics page and Download Support Bundle File");
    }
}

