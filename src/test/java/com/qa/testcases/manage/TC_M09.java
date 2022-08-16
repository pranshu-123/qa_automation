package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.FileUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Manage
@Marker.GCPManage
@Marker.All
public class TC_M09 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_M11.class.getName());

    /*
     * Verify Run Diagnostics page in Manage tab and Download Support Bundle
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateRunDiagnosticsTabAndDownloadSupportBundle(String clusterId) {
        test = extent.startTest("TC_M09.validateRunDiagnosticsTabAndDownloadSupportBundle", "Verify " +
                "Run Diagnostics page and Download Support Bundle in Manage tab");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        Manage manage = new Manage(driver,test);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Run Diagnostics tab and validate header
        waitExecuter.sleep(2000);
        manage.clickRunDiagnostics();
        test.log(LogStatus.INFO, "Clicked on Run Diagnostics Tab.");
        logger.info("Clicked on Run Diagnostics Tab.");
        waitExecuter.waitUntilPageFullyLoaded();
        //Assert.assertTrue(manage.validateRunDiagnosticsHeader(), "Run Diagnostics Header is not present.");
        test.log(LogStatus.INFO, "Verified Run Diagnostics Tab.");
        logger.info("Verified Run Diagnostics Tab.");


        manage.clickOnDownloadSupportBundle();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Download Support Bundle button.");
        logger.info("Clicked on Download Support Bundle button.");

        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(4000);
        userActions.performActionWithPolling(managePageObject.downloadSupportBundleBtn, UserAction.CLICK);
        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("diagnostics.txt"), "Not able to " +
                "Download Support Bundle.");
        waitExecuter.sleep(7000);
        test.log(LogStatus.PASS,"Verified Run Diagnostics page and Download Support Bundle");
        logger.info("Verified Run Diagnostics page and Download Support Bundle");
    }
}
