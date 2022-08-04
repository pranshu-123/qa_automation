package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.EMRMapReduce
@Marker.GCPAppDetailsMr
@Marker.All
public class MR_050 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(MR_050.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void MR_050_verifyAnalysisTab(String clusterId) {
        test = extent.startTest("MR_050_verifyClusterFilter: " + clusterId,
                "Verify Collapsable tabs on Recommendations and effeciency must be listed");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_050_verifyClusterFilter");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        Actions actions = new Actions(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        mrDetailsPage.clickOnlyLink("MapReduce");
        int appCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        List<Integer> list = new ArrayList<>();

        if (appCount > 0) {
            sorting:
            for (int i = 0; i < 2; i++) {
                // Sort by parent app
                test.log(LogStatus.INFO, "Sort by parent app");
                LOGGER.info("Sort by parent app");
                mrApps.sortByParentApp.click();
                waitExecuter.sleep(1000);
                list.add(mrApps.checkInsightsApp.size());
                waitExecuter.sleep(1000);

                for (int value : list) {
                    if (value > 0) {
                        applicationsPageObject.expandStatus.click();
                        waitExecuter.sleep(2000);
                        int successAppCount = mrDetailsPage.clickOnlyLink("Failed");
                        waitExecuter.sleep(2000);
                        if (successAppCount > 0) {
                            // Get MR app id from the first row
                            test.log(LogStatus.INFO, "Get MR app id from the first row");
                            LOGGER.info("Get MR app id from the first row");

                            // Click on first app in table to navigate to app details page
                            test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                            LOGGER.info("Click on first app in table to navigate to app details page");
                            waitExecuter.waitUntilElementClickable(applicationsPageObject.clickOnAppId);
                            applicationsPageObject.clickOnAppId.click();
                            waitExecuter.waitUntilElementPresent(applicationsPageObject.closeIcon);

                            Assert.assertTrue(mrApps.mrSummaryApp.isDisplayed(),
                                    "Map Reduce is not present as parent app for app id. ");
                            //mrDetailsPage.validateAnalysisTab(mrApps);
                        } else {
                            test.log(LogStatus.SKIP,
                                    "The clusterId does not have application of MR app");
                            waitExecuter.sleep(1000);
                            // Click on reset if there are no MR apps
                            test.log(LogStatus.INFO, "Click on reset if there are no MR apps");
                            LOGGER.info("Click on reset if there are no MR apps");
                            allApps.reset();
                            throw new SkipException(
                                    "The clusterId does not have application of MR with MR app.");
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
            LOGGER.info("Click on reset if there are no MR apps");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it.");
        }
    }
}
