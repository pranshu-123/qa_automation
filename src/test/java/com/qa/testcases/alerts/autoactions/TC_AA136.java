package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.alerts.AutoActions;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.validations.AllAppsTableValidation;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA136 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_AA136.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="Verify the details of the auto alert by hovering over the app button (bell icon).")
    public void validateAutoAlertByHoverAppBadge(String clusterId) {
        test = extent.startTest("TC_AA136.validateAutoAlertByHoverAppBadge",
                "Validate auto alert detail by hovering over app badge(bell icon).");
        test.assignCategory(" Alerts ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        AutoActionsPageObject autoActionsPageObject = new AutoActionsPageObject(driver);
        HomePageObject homePageObject = new HomePageObject(driver);

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

        //After creating auto alert policy return to homepage
        MouseActions.clickOnElement(driver, homePageObject.unravelLogo);

        // Select 'Only' application that are with 'killed' status
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has status expand link and the 'killed' check box
        test.log(LogStatus.INFO, "Verify that the left pane has status expand link and the killed check box");
        logger.info("Select killed status and assert that table contain insight data");
        applicationsPageObject.expandStatus.click();
        int appKilledCount = appsDetailsPage.clickOnlyLink("Killed");

        String expectedTooltip = "<b>Policy violations :<b><br>Policy violations are not available";
        //Actions builder = new Actions (driver);

        if(appKilledCount > 0) {
            waitExecuter.sleep(3000);
            List<WebElement> tableRows = applicationsPageObject.allAppsTable.findElements(By.xpath("//tr"));
            //Validate whether data exist in all Apps table for killed status
            AllAppsTableValidation.validateValueExistInTableForKilledStatus(tableRows);

            waitExecuter.sleep(3000);
            //Instantiate Action Class
            Actions actions = new Actions(driver);
            //Retrieve WebElement
            WebElement icon_alert_element = autoActionsPageObject.icon_summary_header_apps;

            // Using the action class to mimic mouse hover
            actions.moveToElement(icon_alert_element).perform();
            WebElement toolTip = autoActionsPageObject.icon_summary_header_apps; //driver.findElement(By.xpath("//*[@id="age"]"));

            // To get the tool tip text and assert
            String toolTipText = toolTip.getText();
            System.out.println("ToolTipText-->: "+toolTipText);
            MouseActions.clickOnElement(driver, homePageObject.unravelLogo);
            test.log(LogStatus.PASS, "Verified auto alert detail by clicking app badge.");
            logger.info("Verified auto alert detail by clicking app badge.");

        }else {
            test.log(LogStatus.INFO, "No data exist for the killed status");
            logger.info("No data exist for the killed status");
            MouseActions.clickOnElement(driver, homePageObject.unravelLogo);
            System.out.println("ToolTipText--> Not Found");
            Assert.assertTrue(false);
            logger.info("Verified auto alert, no killed application found.");

        }
    }
}
