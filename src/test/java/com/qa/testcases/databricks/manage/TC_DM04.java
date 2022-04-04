package com.qa.testcases.databricks.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.databricks.manage.DbxManage;
import com.qa.scripts.manage.Manage;
import com.qa.testcases.manage.TC_M04;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxManage
@Marker.All
public class TC_DM04 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M04.class);

    /*
     * Click on View link on Daemons page and verify logs with respect to the selected category.
     */
    @Test()
    public void validateViewAndLogLevel() {
        test = extent.startTest("TC_DM04.validateViewAndLogLevel", "Verify logs with respect to " +
                "the selected category");
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


        DbxManage manage = new DbxManage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");

        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.daemeons);
        //Validate daemons table is populated
        Assert.assertTrue(manage.validateDaemonsCountInTbl(), "Daemons not found in the table.");
        test.log(LogStatus.INFO, "Verified Daemons count on Manage Tab.");

        //Click on View tab
        manage.clickOnView();
        test.log(LogStatus.INFO, "Clicked on View link.");
        manage.verifyViewPage();
        test.log(LogStatus.PASS, "Verified Daemons sorting option on all applicable tab on Manage Tab.");

    }

}

