package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.alerts.AutoActions;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TCAA136 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TCAA136.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAutoAlertByHoverAppBadge(String clusterId) {
        test = extent.startTest("TCAA136.validateAutoAlertByHoverAppBadge",
                "Validate auto alert detail by hovering over app badge(bell icon).");
        test.assignCategory(" Alerts ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);

        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.alerts);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.alerts);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.alerts);
        test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
        logger.info("Verified Alerts Tab is clicked.");

        AutoActions aa = new AutoActions(driver);
        //Validate Auto Actions header default
        String aaHeader = aa.getAutoActionsHeader();
        Assert.assertEquals(aaHeader, "Auto Actions", "Auto Actions Header not matched.");
        test.log(LogStatus.INFO, "Verified Auto Actions Header.");
        logger.info("Auto Actions Header found and matched");

        aa.clickOnNewAutoActionBtn();
        test.log(LogStatus.INFO, "clicked on new auto action button");
        Assert.assertTrue(aa.validateNewAutoActionPolicyPageDisplayed(), "New Auto Action Policy page" +
                " not displayed");

        //Data for creating new auto action policy
        Map<String,String> mapPolicyData = new HashMap<>();
        mapPolicyData.put("PolicyName", "MRAppPolicyForBellIconCheck");
        mapPolicyData.put("TriggerCondition", "App");
        mapPolicyData.put("ApplicationType", "mapreduce");
        mapPolicyData.put("Metric","elapsedTime");
        mapPolicyData.put("Value","100");
        mapPolicyData.put("Actions","Kill App");

        aa.createNewAutoActionPolicy(mapPolicyData);

        test.log(LogStatus.INFO,"Fill new auto action policy details");
        aa.clickOnSaveBtn();
        test.log(LogStatus.INFO,"Click on save button");
        logger.info("Filled New Auto Action Policy details and clicked on save button");

        Assert.assertTrue(aa.validateAutoActionAdded(mapPolicyData.get("PolicyName")), "Newly added Policy: "+
                mapPolicyData.get("PolicyName") + " not found.");
        test.log(LogStatus.PASS, "New policy with description name as alphabets added successfully " +
                "on alerts auto action page.");

//        // Select 'Only' Map Reduce type and get its jobs count
//        test.log(LogStatus.INFO, "Select 'Only' Map Reduce from app types and get its jobs count");
//        logger.info("Select 'Only' Map Reduce from app types and get its jobs count");
//        sparkApp.clickOnlyLink("Map Reduce");
//
//        applicationsPageObject.listOfInsights.size();  // no of icon-alerts


    }
}
