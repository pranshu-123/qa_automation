package com.qa.testcases.emr.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;

import com.qa.pagefactory.emr.manage.EmrSub_Manage_TopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;

import com.qa.scripts.emr.manage.EmrManage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EmrManage
@Marker.All
public class TC_EMRM_23 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_EMRM_23.class);


    @Test()
    public void validateAuditSortingoption() {
        test = extent.startTest("TC_EMRM_23.validateAuditTab", "Verify" +
                "AuditSortingoption ");
        test.assignCategory("Emr_Manage");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrSub_Manage_TopPanelModulePageObject subTopPanelModulePageObject = new EmrSub_Manage_TopPanelModulePageObject(driver);
        EmrManage manage = new EmrManage(driver);
        ManagePageObject managePageObject = new ManagePageObject(driver);
        // Navigate to Manage tab from header
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
        test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
        logger.info("Verified Manage Tab is clicked.");

        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header is not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");
        logger.info("Verified Manage Page is loaded successfully.");
        //waitExecuter.waitUntilPageFullyLoaded();


        //Click on Audit tab and validate header
        manage.clickAuditTab();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Audit Tab.");
        logger.info("Clicked on Audit option.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateAuditHeader(), "Audit Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Audit Tab.");

        //Click on icon-sort
        manage.clickOnIconAuditSort();
        waitExecuter.sleep(3000);
        manage.validateAuditSorting("User");
        test.log(LogStatus.PASS, "Verified Audits sorting option");
        logger.info("Verified Audits sorting option");
    }
}



