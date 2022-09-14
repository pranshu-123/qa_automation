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
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsMr
@Marker.EMRMapReduce
@Marker.GCPAppDetailsMr
@Marker.All
public class MR_040 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_006.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_040_verfyMrAppswithClusterIDs(String clusterId) throws InterruptedException {
        test = extent.startTest("MR_040_verfyMrAppswithClusterIDs: " + clusterId,
                "Verify All the MR apps run on different Clusters must have the cluster ID");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_040_verfyMrAppswithClusterIDs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        test.log(LogStatus.INFO, "Verify that the left pane has map reduce check box and the apps number");
        int appCount = mrDetailsPage.clickOnlyLink("MapReduce");

        /*
         * Validate the start time types are --
         */
        if (appCount > 0) {
            String clusterid = mrDetailsPage.verifyclusterId(mrApps);
            test.log(LogStatus.PASS, "Read IO is displayed in the Map Reduce Table: " + clusterid);


        } else {
            test.log(LogStatus.SKIP, "No Map Reduce Application present");
            logger.error("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
            //Close apps details page
            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);

        }
    }
}