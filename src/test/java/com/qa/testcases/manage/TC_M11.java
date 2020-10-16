package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
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
public class TC_M11 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M11.class);

    /*
     * Verify Monitoring page in Manage tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMonitoringTabAndDBStatusTab(String clusterId) {
        test = extent.startTest("TC_M11.validateMonitoringTabAndDBStatusTab", "Verify" +
                " Monitoring  page and DB Status details");
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

        //Click on Monitoring tab and validate header
        waitExecuter.sleep(2000);
        manage.clickMonitoringTab();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        //Click on DB Status and validate its details
        waitExecuter.sleep(2000);
        manage.clickDBStatusTab();
        test.log(LogStatus.INFO, "Clicked on DB Status Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateDBStatusTableHeaders(),"DB Status Header columns are not present");

        Assert.assertTrue(manage.verifyDBStatusTSAndDataAge(),"DB Status TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified DB Status table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with DB Status details.");


    }
}
