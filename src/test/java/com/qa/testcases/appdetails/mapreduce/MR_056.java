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
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Marker.AppDetailsMr
@Marker.All
public class MR_056 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_056.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_056_verifycontainersGraphs(String clusterId) {
        test = extent.startTest("MR_056_verifycontainersGraphs: " + clusterId,
                "Verify the number of containers plotted aganist the time");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_056_verifycontainersGraphs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        mrDetailsPage.clickOnlyLink("Map Reduce");
        int appCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        List<Integer> list = new ArrayList<>();

        if (appCount > 0) {
            sorting:
            for (int i = 0; i < 1; i++) {
                // Sort by parent app
                test.log(LogStatus.INFO, "Sort by Read app");
                logger.info("Sort by Read app");
                mrApps.sortByReadApp.click();
                waitExecuter.waitUntilPageFullyLoaded();
                list.add(mrApps.checkReadApp.size());
                waitExecuter.waitUntilPageFullyLoaded();

                for (int value : list) {
                    if (value > 0) {
                        applicationsPageObject.expandStatus.click();
                        waitExecuter.sleep(2000);
                        int successAppCount = mrDetailsPage.clickOnlyLink("Success");
                        waitExecuter.sleep(2000);

                        if (successAppCount > 0) {
                            mrApps.getTypeFromTable.click();
                            waitExecuter.waitUntilPageFullyLoaded();
                            waitExecuter.waitUntilElementPresent(mrApps.resourcesTab);
                            MouseActions.clickOnElement(driver, mrApps.resourcesTab);
                            waitExecuter.waitUntilPageFullyLoaded();
                            mrDetailsPage.validateContainsTab(mrApps, test);
                            waitExecuter.sleep(2000);
                            test.log(LogStatus.PASS, "Verify the number of containers plotted against the time");
                            //Close apps details page
                            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
                        } else {
                            test.log(LogStatus.SKIP,
                                    "The clusterId does not have application of Map Reduce as parent app");
                            waitExecuter.sleep(1000);
                            // Click on reset if there are no MR apps
                            test.log(LogStatus.INFO, "Click on reset if there are no hive apps");
                            logger.info("Click on reset if there are no Map Reduce apps");
                            allApps.reset();
                            throw new SkipException(
                                    "The clusterId does not have application of Map Reduce as parent app.");
                        }
                        break sorting;
                    }
                }
            }
        } else{
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusters does not have any application under it and also does not display 'No Data Available' for it"
            );
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            waitExecuter.sleep(1000);
            // Click on reset if there are no hive apps
            test.log(LogStatus.INFO, "Click on reset if there are no Map Reduce apps");
            logger.info("Click on reset if there are no hive apps");
            allApps.reset();
            throw new SkipException("The clusterId does not have any application under it.");
        }
    }
}


