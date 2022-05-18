package com.qa.testcases.databricks.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.manage.DbxManagePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M10;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DbxManage
@Marker.All
public class TC_DM10 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_M10.class.getName());

    /*
     * Verify Monitoring page in Manage tab
     */
    @Test()
    public void validateMonitoringTabAndPartitionInfoTab() {
        test = extent.startTest("TC_DM10.validateMonitoringTabAndPartitionInfoTab", "Verify" +
                " Monitoring  page in Manage tab");
        test.assignCategory(" Manage ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject subTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        DbxManage manage = new DbxManage(driver);
        DbxManagePageObject managePageObject = new DbxManagePageObject(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        //Click on Monitoring tab and validate header
        manage.clickMonitoringTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Monitoring Tab.");
        logger.info("Clicked on Monitoring Tab.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateMonitoringHeader(), "Monitoring Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Monitoring Tab.");

        try{
            waitExecuter.sleep(2000);
            waitExecuter.waitUntilTextToBeInWebElement(managePageObject.partitionInfoTab, "Partition Info");
            test.log(LogStatus.PASS, "Verified Partition Tab is loaded properly.");
            Assert.assertTrue(managePageObject.partitionInfoTab.isDisplayed(),"Partition Info Tab " +
                    "not found.");
        }catch (TimeoutException e){
            e.printStackTrace();
            test.log(LogStatus.INFO, "Partition Info Tab not found.");
            logger.info("Partition Info Tab not found.");
            Assert.assertTrue(false, "Partition Info Tab not found.");
        }

        Assert.assertTrue(manage.verifyPartitionInfoTab(), "Partition Info tab is not present.");
        test.log(LogStatus.INFO, "Verified Partition Info tab on Monitoring Page.");
        logger.info("Verified Partition Info tab on Monitoring Page.");
        Assert.assertTrue(manage.validatePartitionInfoTableHeaders(),"Verified Partition Info table headers.");
        test.log(LogStatus.INFO, "Verified Partition Info table headers.");
        logger.info("Verified Partition Info table headers.");
        Assert.assertTrue(manage.verifyPartitionInfoTSAndDataAge(),"PartitionInfo TimeStamp and Data Age " +
                "not displayed");
        test.log(LogStatus.INFO, "Verified Partition Info table TimeStamp and Data Age.");
        test.log(LogStatus.PASS, "Verified Monitoring page with Partition Info details.");
        logger.info("Verified Monitoring page with Partition Info details.");


    }
}
