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
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_55 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_55.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAppDetailsPageLaunchs(String clusterId) {
        test = extent.startTest("TC_HIVE_55.VerifyAppDetailsPageLaunchs",
                "Verify that when User click on Hive app should navigate to App detail page.");
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
        UserActions userAction = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userAction.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select only Hive apps and check if app counts is 0 or greater than that
        test.log(LogStatus.INFO, "Select only hive apps and check if app counts is 0 or greater than that");
        LOGGER.info("Select only hive apps and check if app counts is 0 or greater than that");
        sparkApp.clickOnlyLink("Hive");
        int appCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());

        if (appCount > 0) {
            // Get Hive app id from the first row
            test.log(LogStatus.INFO, "Get Hive app id from the first row");
            LOGGER.info("Get Hive app id from the first row");
            WebElement name = applicationsPageObject.getAppNameFromTable;
            actions.moveToElement(name).perform();
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            actions.moveToElement(applicationsPageObject.copyAppName).perform();
            waitExecuter.waitUntilElementClickable(applicationsPageObject.copyAppName);
            applicationsPageObject.copyAppName.click();

            // Click on first app in table to navigate to app details page
            test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
            LOGGER.info("Click on first app in table to navigate to app details page");
            waitExecuter.waitUntilElementClickable(applicationsPageObject.getStatusFromTable);
            applicationsPageObject.getStatusFromTable.click();
            waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
            // Assert that App details page opens
            test.log(LogStatus.INFO, "Assert that App details page opens");
            LOGGER.info("Assert that App details page opens");
            waitExecuter.waitUntilElementPresent(applicationsPageObject.appSummary);
            Assert.assertTrue(applicationsPageObject.appSummary.isDisplayed(),
                    "Application Summary page did not load ");
            test.log(LogStatus.PASS, "App details page is launched successfully");
            waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
            userAction.performActionWithPolling(applicationsPageObject.closeIcon, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            // Navigate back to parent page and click on reset
            test.log(LogStatus.INFO, "Navigate back to parent page and click on reset");
            LOGGER.info("Navigate back to parent page and click on reset");
            allApps.reset();

        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
            LOGGER.info("Click on reset if there are no hive apps");
            allApps.reset();
        }
    }
}
