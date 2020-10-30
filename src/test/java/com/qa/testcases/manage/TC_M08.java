package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.Manage
@Marker.All
public class TC_M08 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M08.class);

    /*
     * Verify Run Diagnostics page in Manage tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateRunDiagnosticsTabAndLoadLatestDiagnostics(String clusterId) {
        test = extent.startTest("TC_M08.validateRunDiagnosticsTabAndLoadLatestDiagnostics", "Verify " +
                "Run Diagnostics page in Manage tab");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
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
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.validateRunDiagnosticsHeader(), "Run Diagnostics Header is not present.");
        test.log(LogStatus.INFO, "Verified Run Diagnostics Tab.");

        waitExecuter.sleep(2000);
        manage.clickOnLoadLatestDiagnostics();
        test.log(LogStatus.INFO, "Clicked on Load Latest Diagnostics Button.");

        waitExecuter.sleep(2000);
        manage.verifyLoadLatestDiagnosticsHeaderAndTimeStamp();
        test.log(LogStatus.INFO, "Verified Latest Diagnostics Header and Time Stamp.");

        waitExecuter.sleep(2000);
        manage.verifyLoadLatestDiagnosticsContent();
        test.log(LogStatus.INFO, "Verified Load Latest Diagnostics contents.");
        test.log(LogStatus.PASS, "Verified diagnostics log with time stamp");

    }
}
