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
@Marker.GCPManage
@Marker.All
public class TC_M06 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M06.class);

    /*
     * Verify Stats page in Manage tab and verify Elastic Search tab
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateElasticSearchStatsTab(String clusterId) {
        test = extent.startTest("TC_M06.validateElasticSearchStatsTab", "Verify Elastic Search tab");
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

        //Click on Stats tab and validate header
        waitExecuter.sleep(2000);
        manage.clickStatsTab();
        test.log(LogStatus.INFO, "Clicked on Stats Tab.");
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.validateStatsHeader(),"Stats Header is not present.");
        test.log(LogStatus.INFO, "Verified Stats Tab.");

        //Click on Elastic Search tab and validate details on the page
        manage.clickElasticsearchTab();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Clicked on Elastic Serach Tab.");
        waitExecuter.sleep(2000);
        Assert.assertTrue(manage.verifyElasticSearchDetails()," Elastic Serach Header is not present.");
        test.log(LogStatus.PASS, "Verified Elastic Search Tab and its details.");

    }
}
