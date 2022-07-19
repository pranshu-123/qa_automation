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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.EMRHive
@Marker.All
public class TC_HIVE_60 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_60.class.getName());

    @Test
    public void VerifyHiveAppsWithMRTags() {
        test = extent.startTest("TC_HIVE_60.VerifyHiveAppsWithTezTags",
                "MR application having Hive as parent app should have tabs 'table' , 'gant chart' in left pane");
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
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' Map Reduce from app types and get its jobs count");
        LOGGER.info("Select 'Only' Map Reduce from app types and get its jobs count");
        sparkApp.clickOnlyLink("MapReduce");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        int appCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        List<Integer> list = new ArrayList<>();
        if (appCount > 0) {
            sorting:
            for (int i = 0; i < 2; i++) {
                // Sort by parent app
                test.log(LogStatus.INFO, "Sort by parent app");
                LOGGER.info("Sort by parent app");
                waitExecuter.waitUntilElementClickable(applicationsPageObject.sortByParentApp);
                userActions.performActionWithPolling(applicationsPageObject.sortByParentApp, UserAction.CLICK);
                waitExecuter.waitUntilPageFullyLoaded();
                userActions.performActionWithPolling(applicationsPageObject.sortUp, UserAction.CLICK);
                waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
                list.add(applicationsPageObject.checkHiveInParentApp.size());
                waitExecuter.sleep(1000);

                for (int value : list) {
                    if (value > 0) {
                        waitExecuter.waitUntilElementClickable(applicationsPageObject.expandStatus);
                        userActions.performActionWithPolling(applicationsPageObject.expandStatus, UserAction.CLICK);
                        waitExecuter.sleep(1000);
                        int successAppCount = sparkApp.clickOnlyLink("Success");
                        if (successAppCount > 0) {
                            // Get MR app id from the first row
                            test.log(LogStatus.INFO, "Get MR app id from the first row");
                            LOGGER.info("Get MR app id from the first row");
                            WebElement name = applicationsPageObject.getAppNameFromTable;
                            actions.moveToElement(name).perform();
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
                            actions.moveToElement(applicationsPageObject.copyAppName).perform();
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
                            applicationsPageObject.copyAppName.click();
                            List<String> expectedTabs = Arrays.asList("table", "gantt chart");
                            List<String> actualTabs = new ArrayList<>();

                            // Click on first app in table to navigate to app details page
                            test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                            LOGGER.info("Click on first app in table to navigate to app details page");
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.checkHiveInParentApp.get(0));
                            applicationsPageObject.checkHiveInParentApp.get(0).click();
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
                            Assert.assertEquals(applicationsPageObject.mrHiveLeftPaneHeaders.size(), 2,
                                    "Expected tabs in MR apps having Hive as parent app is 2 but found- "
                                            + applicationsPageObject.hiveTezAppDetailsTab.size());
                            LOGGER.info("Number of tabs expected matched i.e 2");
                            test.log(LogStatus.PASS, "Number of tabs expected matched i.e 2");
                            for (int j = 0; j < applicationsPageObject.mrHiveLeftPaneHeaders.size(); j++) {
                                actualTabs.add(applicationsPageObject.mrHiveLeftPaneHeaders.get(j)
                                        .getText().trim().toLowerCase());
                            }
                            waitExecuter.sleep(3000);
                            LOGGER.info("Expected tabs- " + expectedTabs);
                            test.log(LogStatus.INFO, "Expected tabs- " + expectedTabs);
                            LOGGER.info("Actual tabs- " + actualTabs);
                            test.log(LogStatus.INFO, "Actual tabs- " + actualTabs);
                            Assert.assertTrue(actualTabs.containsAll(expectedTabs),
                                    "All expected tabs are not loaded");
                            test.log(LogStatus.PASS, "Actual tabs matched expected tabs");
                            // Navigate back to parent page and click on reset
                            test.log(LogStatus.INFO, "Navigate back to parent page and click on reset");
                            LOGGER.info("Navigate back to parent page and click on reset");
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
                            userActions.performActionWithPolling(applicationsPageObject.closeIcon, UserAction.CLICK);
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
                            allApps.reset();

                        } else {
                            test.log(LogStatus.SKIP,
                                    "The clusterId does not have application of MR with HIVE as parent app");
                            waitExecuter.sleep(1000);
                            // Click on reset if there are no hive apps
                            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
                            LOGGER.info("Click on reset if there are no hive apps");
                            userActions.performActionWithPolling(applicationsPageObject.closeIcon, UserAction.CLICK);
                            allApps.reset();
                        }
                        break sorting;
                    }

                }

            }
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it");
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
            LOGGER.info("Click on reset if there are no hive apps");
            allApps.reset();
        }
    }
}