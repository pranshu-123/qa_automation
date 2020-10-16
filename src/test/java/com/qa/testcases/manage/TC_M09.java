package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.FileUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.Manage
@Marker.All
public class TC_M09 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M09.class);

    /*
     * Verify Run Diagnostics page in Manage tab and Download Support Bundle
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateRunDiagnosticsTabAndDownloadSupportBundle(String clusterId) {
        test = extent.startTest("TC_M09.validateRunDiagnosticsTabAndDownloadSupportBundle", "Verify " +
                "Run Diagnostics page and Download Support Bundle in Manage tab");
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

        //Click on Run Diagnostics tab and validate header
        waitExecuter.sleep(2000);
        manage.clickRunDiagnostics();
        test.log(LogStatus.INFO, "Clicked on Run Diagnostics Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateRunDiagnosticsHeader(), "Run Diagnostics Header is not present.");
        test.log(LogStatus.INFO, "Verified Run Diagnostics Tab.");

        waitExecuter.sleep(2000);
        manage.clickOnDownloadSupportBundle();
        test.log(LogStatus.INFO, "Clicked on Download Support Bundle button.");

        waitExecuter.sleep(2000);
        Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("diagnostics.txt"), "Not able to " +
                "Download Support Bundle.");
        test.log(LogStatus.PASS,"Verified Run Diagnostics page and Download Support Bundle");

    }
}
