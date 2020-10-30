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
public class TC_M12 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M12.class);

    /*
     * Verify Monitoring page in Manage tab and verify DB performance tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMonitoringTabAndDBPerformanceTab(String clusterId) {
        test = extent.startTest("TC_M12.validateMonitoringTabAndDBPerformanceTab", "Verify" +
                " Monitoring  page and DB performance details");
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

        //Click on Monitoring tab and validate header
        waitExecuter.sleep(2000);
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        //Click on DB Performance and validate its details
        waitExecuter.sleep(2000);
        manage.clickDBPerformanceTab();
        test.log(LogStatus.INFO, "Clicked on DB Performance Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateDBPerformanceTableHeaders(),"DB Performance Header columns are not present");

        Assert.assertTrue(manage.verifyDBPerfromanceTSAndDataAge(),"DB Performance TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified Performance Status table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with DB Performance details.");
    }
}
