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
import org.testng.annotations.Test;
@Marker.AppDetailsMr
@Marker.All
public class MR_049 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_049.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_049_verifyAllDataTabs(String clusterId) {
        test = extent.startTest("MR_049_verifyAllDataTabs: " + clusterId,
                "Verify Data Tabs must be available on UI");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_049_verifyAllDataTabs");

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
        mrDetailsPage.clickOnlyLink("Map Reduce");
        waitExecuter.sleep(1000);
        applicationsPageObject.expandStatus.click();
        int appCount = mrDetailsPage.clickOnlyLink("Success");

        //Clicking on the Map reduce app must go to apps detail page and verify Data Tabs must be available on UI
        if (appCount > 0) {
            String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Map Reduce Application Id is displayed in the Header: " + headerAppId);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Analysis", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Resources", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Errors", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Logs", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Tags", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Metrics", test);
            mrDetailsPage.verifyAllDataTabs(mrApps, "Configuration", test);

            //Close apps details page
            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
        } else {
            test.log(LogStatus.SKIP, "No Map Reduce Application present");
            logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");

        }
    }
}
