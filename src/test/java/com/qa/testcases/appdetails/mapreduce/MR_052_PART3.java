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
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.All
public class MR_052_PART3 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_052_PART3.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_052_PART3_verifyKilledStatus(String clusterId) {
        test = extent.startTest("MR_052_PART3_verifyKilledStatus: " + clusterId,
                "Verify there are 2 tabs , Task Attempt (Map), Task Attempt(Reduce)");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_052_PART3_verifyKilledStatus");

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
        int totalMapReduceAppCnt = mrDetailsPage.clickOnlyLink("MapReduce");
        if (totalMapReduceAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            int appCount = mrDetailsPage.clickOnlyLink("Killed");
            if (appCount > 0) {
                String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
                test.log(LogStatus.PASS, "Map Reduce Application Id is displayed in the Header: " + headerAppId);
                waitExecuter.waitUntilPageFullyLoaded();
                MouseActions.clickOnElement(driver, mrApps.resourcesTab);
                waitExecuter.waitUntilPageFullyLoaded();
                mrDetailsPage.validateMapandReduceTab(mrApps,"Task Attempts",test);

                //Close apps details page
                waitExecuter.waitUntilElementPresent(mrApps.closeAppsPageTab);
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);

            } else {
                test.log(LogStatus.SKIP, "No Map Reduce Application present");
                logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
                //Close apps details page
                MouseActions.clickOnElement(driver, mrApps.homeTab);
            }
        }
    }
}