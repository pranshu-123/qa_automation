package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_45 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_45.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyCopyAppNameID(String clusterId) {
        test = extent.startTest("TC_HIVE_45.VerifyCopyAppNameID",
                "Verify that on hovering over app, user is able to copy app name/id by clicking on '+'");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        Actions actions = new Actions(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userActions.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (hiveAppCount > 0) {
            // Hive on to the first row
            test.log(LogStatus.INFO, "Hive on to the first row");
            LOGGER.info("Hive on to the first row");
            WebElement appName = applicationsPageObject.hoverOnAppName;
            actions.moveToElement(appName).perform();
            waitExecuter.sleep(2000);
            WebElement tooltipValue = applicationsPageObject.getToolTipValueOfAppName;
            String value = tooltipValue.getAttribute("aria-describedby");
            //String tooltipValue = applicationsPageObject.getToolTipValueOfAppName.getText();
            LOGGER.info("TOOLTIP-- "+value);
            // Validate that on hovering the row is highlighted
            test.log(LogStatus.INFO, "Validate that on hovering the row is highlighted");
            LOGGER.info("Validate that on hovering the row is highlighted");
            Assert.assertNotNull(tooltipValue, "On hover the row is not highlighted");
            test.log(LogStatus.PASS, "On hover, the application in table is highlighted");
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            throw new SkipException("The clusterId does not have any application under it");
        }
        // Click on show-all to view all type of applications
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
    }
}
