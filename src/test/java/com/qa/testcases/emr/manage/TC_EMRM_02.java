package com.qa.testcases.emr.manage;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.emr.manage.EmrSub_Manage_TopPanelModulePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.emr.manage.EmrManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M02;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_02 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_EMRM_02.class);
    @Test()
    public void validateDaemonsTbl() {
        test = extent.startTest("TC_EMRM_02.validateDaemonsTbl", "Verify Daemons page in Manage tab");
        test.assignCategory("Emr_Manage");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSub_Manage_TopPanelModulePageObject subTopPanelModulePageObject = new EmrSub_Manage_TopPanelModulePageObject(driver);
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

        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.daemeons);

        //Validate daemons table is populated
        Assert.assertTrue(manage.validateDaemonsCountInTbl(), "Daemons table is not populated");
        test.log(LogStatus.PASS, "Verified all Daemons with details.");
        logger.info("Verified all Daemons with details.");

    }
}




