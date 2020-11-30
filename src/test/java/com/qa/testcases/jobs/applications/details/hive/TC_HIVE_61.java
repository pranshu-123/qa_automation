package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_61 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_61.class.getName());

    @Test
    public void VerifyHiveAppsWithTezTags() {
        test = extent.startTest("TC_HIVE_61.VerifyHiveAppsWithTezTags",
                "Verify that when clicked on Hive parent app as 'Tez' should navigate to hive details page having tabs as-"
                        + "ex : Analysis, Query, counters, Graphs, VertexTimelimit, All vertices, All Tasks, Diagonostic, "
                        + "Tables, Tags, All Tasks Attempts, Tasks Attempts Graph, Changed Configuration");
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
        // JavaScriptExecuter executor = (JavaScriptExecuter) driver;
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(4000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(4000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' Hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' Hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
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
                list.add(applicationsPageObject.checkTezInParentApp.size());
                waitExecuter.sleep(1000);

                for (int value : list) {
                    if (value > 0) {
                        applicationsPageObject.expandStatus.click();
                        waitExecuter.sleep(2000);
                        int successAppCount = sparkApp.clickOnlyLink("Success");
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
                            List<String> expectedTabs = Arrays.asList("analysis", "query", "diagnostics", "tables",
                                    "tags", "counters", "graph", "vertex timeline", "all vertices", "all tasks",
                                    "all task attempts", "tasks attempts graph", "changed configuration");
                            List<String> actualTabs = new ArrayList<>();
                            try {
                                String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                                        .getData(DataFlavor.stringFlavor);
                                String[] getCopiedText = data.split(":");
                                // Click on first app in table to navigate to app details page
                                test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                                LOGGER.info("Click on first app in table to navigate to app details page");
                                applicationsPageObject.getStatusFromTable.click();
                                waitExecuter.waitUntilElementPresent(applicationsPageObject.loader);
                                waitExecuter.sleep(3000);
                                Assert.assertEquals(applicationsPageObject.hiveTezAppDetailsTab.size(), 13,
                                        "Expected tabs in Hive apps having Tez as parent app is 13 but found- "
                                                + applicationsPageObject.hiveTezAppDetailsTab.size());
                                LOGGER.info("Number of tabs expected matched i.e 13");
                                test.log(LogStatus.PASS, "Number of tabs expected matched i.e 13");
                                for (int j = 0; j < applicationsPageObject.hiveTezAppDetailsTab.size(); j++) {
                                    actualTabs.add(applicationsPageObject.hiveTezAppDetailsTab.get(j)
                                            .getAttribute("content").trim().toLowerCase());
                                }
                                waitExecuter.sleep(3000);
                                LOGGER.info("Expected tabs- " + expectedTabs);
                                test.log(LogStatus.INFO, "Expected tabs- " + expectedTabs);
                                LOGGER.info("Actual tabs- " + actualTabs);
                                test.log(LogStatus.INFO, "Actual tabs- " + actualTabs);
                                Assert.assertTrue(actualTabs.containsAll(expectedTabs),
                                        "All expected tabs are not loaded");
                                // Navigate back to parent page and click on reset
                                test.log(LogStatus.INFO, "Navigate back to parent page and click on reset");
                                LOGGER.info("Navigate back to parent page and click on reset");
                                driver.navigate().back();
                                waitExecuter.sleep(5000);
                                allApps.reset();
                            } catch (HeadlessException | UnsupportedFlavorException | IOException | AssertionError e) {
                                driver.navigate().back();
                                waitExecuter.sleep(5000);
                                allApps.reset();
                                e.printStackTrace();
                            }
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
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it");
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
            LOGGER.info("Click on reset if there are no hive apps");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it.");
        }
    }
}