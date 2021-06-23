package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.All
public class MR_058 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_058.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_058_verifyMetricsGraphs(String clusterId) {
        test = extent.startTest("MR_058_verifyMetricsGraphs: " + clusterId,
                "Verify the Metrics Graphs are present \n" + "OS Memory");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_058_verifyMetricsGraphs");

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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        test.log(LogStatus.INFO, "Verify that the left pane has map reduce check box and the apps number");
        int totalMapReduceAppCnt=mrDetailsPage.clickOnlyLink("MapReduce");
        Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        if (totalMapReduceAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            int appCount = mrDetailsPage.clickOnlyLink("Success");
            //Clicking on the Map reduce app must go to apps detail page and verify Data Tabs must be available on UI
            waitExecuter.sleep(2000);
            mrApps.sortByDurationApp.click();
            waitExecuter.waitUntilPageFullyLoaded();
            mrApps.sortUp.click();
            waitExecuter.sleep(2000);
            if (appCount > 0) {
                mrApps.getTypeFromTable.click();
                waitExecuter.waitUntilPageFullyLoaded();
                MouseActions.clickOnElement(driver, mrApps.resourcesTab);

                waitExecuter.sleep(2000);
                mrDetailsPage.validateResourcesTab(mrApps,"Metrics",test);
                test.log(LogStatus.PASS, "Verify the Metrics Graphs are present in OS Memory");
                //Close apps details page
                waitExecuter.waitUntilElementPresent(mrApps.closeAppsPageTab);
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);

            } else {
                test.log(LogStatus.SKIP, "No Map Reduce Application present");
                logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
        }
    }
}

