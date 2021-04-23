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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.Manage
@Marker.All
public class TC_M15 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M15.class);

    /*
     * Verify Monitoring page in Manage tab and verify zookeeper tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMonitoringTabAndElasticTab(String clusterId) {
        test = extent.startTest("TC_M15.validateMonitoringTabAndElasticTab", "Verify" +
                " Monitoring  page and Elastic tab details");
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

        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        waitExecuter.waitUntilElementPresent(managePageObject.monitoringTab);
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        waitExecuter.waitUntilElementPresent(managePageObject.monitoringHeader);
        waitExecuter.sleep(1000);
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        try{
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringElasticTab, "Elastic");
            test.log(LogStatus.INFO, "Verified Elastic is loaded properly.");
        }catch (TimeoutException e){
            e.printStackTrace();
            test.log(LogStatus.INFO, "Elastic Tab not found.");
            logger.info("Elastic Tab not found.");
            Assert.assertTrue(false, "Elastic Tab not found.");
        }

        //Click on Elastic tab and validate its details
        waitExecuter.sleep(2000);
        manage.clickElasticTab();
        test.log(LogStatus.INFO, "Clicked on Elastic Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateElasticDataDetails(), "Elastic Header columns are not present");

        Assert.assertTrue(manage.verifyElasticTSAndDataAge(), "Elastic TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified Elastic  TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with Elastic details.");

    }
}
