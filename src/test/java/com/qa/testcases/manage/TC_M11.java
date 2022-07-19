package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Manage
@Marker.emrManage
@Marker.All
public class TC_M11 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_M11.class.getName());

    /*
     * Verify Monitoring page in Manage tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMonitoringTabAndDBStatusTab(String clusterId) {
        test = extent.startTest("TC_M11.validateMonitoringTabAndDBStatusTab", "Verify" +
                " Monitoring  page and DB Status details");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        waitExecuter.waitUntilElementPresent(managePageObject.monitoringTab);
        manage.clickMonitoringTab();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");
        logger.info("Verified Monitoring Tab.");

        try{
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringDBStatusTab, "DB Status");
            test.log(LogStatus.INFO, "Verified DB Status is loaded properly.");
            Assert.assertTrue(managePageObject.monitoringDBStatusTab.isDisplayed(),"DB Status Tab " +
                    "not found.");
        }catch (TimeoutException e){
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
        Assert.assertTrue(manage.validateDBStatusTableHeaders(),"DB Status Header columns are not present");

        Assert.assertTrue(manage.verifyDBStatusTSAndDataAge(),"DB Status TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified DB Status table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with DB Status details.");
        logger.info("Verified Monitoring page with DB Status details.");


    }
}
