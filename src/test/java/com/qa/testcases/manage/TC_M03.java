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
public class TC_M03 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M03.class);

    /*
     * Verify Daemons page in Manage tab for sorting option on all applicable tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateDaemonsTblSorting(String clusterId) {
        test = extent.startTest("TC_M03.validateDaemonsTblSorting", "Verify Daemons page in Manage tab" +
                "for  sorting option on all applicable tab");
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
        logger.info("Verified Manage Tab is clicked.");


        Manage manage = new Manage(driver);
        //Validate daemon header default
        String daemonHeader = manage.validateDaemonHeader();
        Assert.assertEquals(daemonHeader, "Daemons", "Daemons Header not matched.");
        test.log(LogStatus.INFO, "Verified Manage Page is loaded successfully.");
        logger.info("Verified Manage Page is loaded successfully.");

        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.daemeons);
        //Validate daemons table is populated
        Assert.assertTrue(manage.validateDaemonsCountInTbl(), "Daemons not found in the table.");
        test.log(LogStatus.INFO, "Verified Daemons count on Manage Tab.");
        logger.info("Verified Daemons count on Manage Tab.");

        //Click on icon-sort
        manage.clickOnIconSort();
        test.log(LogStatus.PASS, "Verified Daemons sorting option on all applicable tab on Manage Tab.");
        logger.info("Verified Daemons sorting option on all applicable tab on Manage Tab.");

    }
}
