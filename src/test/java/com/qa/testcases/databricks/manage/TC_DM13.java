package com.qa.testcases.databricks.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.manage.DbxManagePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M13;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxManage
@Marker.All
public class TC_DM13 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M13.class);

    /*
     * Verify Monitoring page in Manage tab and verify Kafka tab
     */
    @Test()
    public void validateMonitoringTabAndKafkaTab() {
        test = extent.startTest("TC_DM13.validateMonitoringTabAndKafkaTab", "Verify" +
                " Monitoring  page and Kafka details");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject subTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        DbxManage manage = new DbxManage(driver);
        DbxManagePageObject managePageObject = new DbxManagePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");

        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        /* waitExecuter.waitUntilElementPresent(managePageObject.monitoringTab);*/
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        /* waitExecuter.waitUntilElementPresent(managePageObject.monitoringHeader);*/
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        try{
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.monitoringKafkaTab, "Kafka");
            test.log(LogStatus.INFO, "Verified Kafka is loaded properly.");
            Assert.assertTrue(managePageObject.monitoringKafkaTab.isDisplayed(),"Kafka Tab " +
                    "not found.");
        }catch (TimeoutException e){
            e.printStackTrace();
            test.log(LogStatus.INFO, "Kafka Tab not found.");
            logger.info("Kafka Tab not found.");
            Assert.assertTrue(false, "Kafka Tab not found.");
        }
        //Click on Kafka and validate its details
        waitExecuter.sleep(2000);
        manage.clickKafkaTab();
        test.log(LogStatus.INFO, "Clicked on Kafka Tab.");
        waitExecuter.sleep(3000);
        Assert.assertTrue(manage.validateKafaDataDetails(),"Kafka data headers are missing");
        test.log(LogStatus.INFO, "Verified Kafka data details on Kafka Tab.");

        Assert.assertTrue(manage.verifyKafkaTSAndDataAge(),"Kafka TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified Kafka table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with Kafka details.");


    }

}