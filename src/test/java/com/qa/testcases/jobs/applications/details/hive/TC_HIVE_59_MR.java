package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.EMRHive
@Marker.GCPAppDetailsHive
@Marker.All
public class TC_HIVE_59_MR extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_59_MR.class.getName());

    @Test
    public void VerifyHiveAppsWithMRTagsKPIS() {
        test = extent.startTest("TC_HIVE_59_MR.VerifyHiveAppsWithMRTagsKPIS",
                "Verify that when clicked on MR parent app as 'Hive' should display Start Date, End Date, Duration and Data I/O");
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
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' Map Reduce from app types and get its jobs count");
        LOGGER.info("Select 'Only' Map Reduce from app types and get its jobs count");
        sparkApp.clickOnlyLink("MapReduce");
        int appCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        List<Integer> list = new ArrayList<>();

        if (appCount > 0) {
            sorting:
            for (int i = 0; i < 2; i++) {
                // Sort by parent app
                test.log(LogStatus.INFO, "Sort by parent app");
                LOGGER.info("Sort by parent app");
                applicationsPageObject.sortByParentApp.click();
                waitExecuter.sleep(1000);
                list.add(applicationsPageObject.checkHiveInParentApp.size());
                waitExecuter.sleep(1000);

                for (int value : list) {
                    if (value > 0) {
                        applicationsPageObject.expandStatus.click();
                        waitExecuter.sleep(2000);
                        int successAppCount = sparkApp.clickOnlyLink("Success");
                        waitExecuter.sleep(2000);
                        if (successAppCount > 0) {
                            // Get MR app id from the first row
                            test.log(LogStatus.INFO, "Get MR app id from the first row");
                            LOGGER.info("Get MR app id from the first row");
                            WebElement name = applicationsPageObject.getAppNameFromTable;
                            actions.moveToElement(name).perform();
                            waitExecuter.sleep(1000);
                            actions.moveToElement(applicationsPageObject.copyAppName).perform();
                            waitExecuter.sleep(1000);
                            applicationsPageObject.copyAppName.click();

                            // Click on first app in table to navigate to app details page
                            test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                            LOGGER.info("Click on first app in table to navigate to app details page");
                            waitExecuter.waitUntilElementPresent(applicationsPageObject.clickOnAppId);
                            applicationsPageObject.clickOnAppId.click();
                            waitExecuter.waitUntilElementPresent(applicationsPageObject.closeIcon);
                            waitExecuter.sleep(2000);
                            List<String> KPIS = new ArrayList<>(
                                    Arrays.asList("duration", "data i/o", "start time", "end time"));
                            List<String> KPIheading = new ArrayList<String>();
                            List<String> KPIheadingValues = new ArrayList<String>();
                            for (int j = 0; j < applicationsPageObject.getAllKPIHeading.size(); j++) {
                                KPIheading
                                        .add(applicationsPageObject.getAllKPIHeading.get(j).getText().trim().toLowerCase());
                                KPIheadingValues.add(applicationsPageObject.getAllKPIHeadingValues.get(j).getText().trim());
                            }

                            LOGGER.info("Present KPI headings- " + KPIheading);
                            LOGGER.info("Expected KPI headings- " + PageConstants.MRHiveKPIs.KPIS.toString());
                            Assert.assertNotNull(KPIheadingValues, "The KPIS value is null");
                            Assert.assertTrue(KPIheading.containsAll(KPIS),
                                    "The KPI didn't Load for app Id. ");
                            test.log(LogStatus.PASS, "KPI's load successfully for MR having Hive as parent app");

                            // Navigate back to parent page and click on reset
                            test.log(LogStatus.INFO, "Navigate back to parent page and click on reset");
                            LOGGER.info("Navigate back to parent page and click on reset");
                            driver.navigate().back();
                            waitExecuter.sleep(5000);
                            allApps.reset();

                        } else {
                            test.log(LogStatus.SKIP,
                                    "The clusterId does not have application of MR with HIVE as parent app");
                            waitExecuter.sleep(1000);
                            // Click on reset if there are no hive apps
                            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
                            LOGGER.info("Click on reset if there are no hive apps");
                            allApps.reset();
                            throw new SkipException(
                                    "The clusterId does not have application of MR with HIVE as parent app.");
                        }
                        break sorting;
                    }
                }
            }
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusters does not have any application under it and also does not display 'No Data Available' for it"
            );
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
            LOGGER.info("Click on reset if there are no hive apps");
            allApps.reset();
        }
    }
}