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
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
@Marker.AppDetailsMr
@Marker.All
public class MR_051 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_051.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_051_verifyColourCode(String clusterId) {
        test = extent.startTest("MR_051_verifyColourCode: " + clusterId,
                "Verify color coded value Effeciency and REcommendation");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_051_verifyColourCode");

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

        int totalMapReduceAppCnt = mrDetailsPage.clickOnlyLink("Map Reduce");
        int appCount = 0;
        if (totalMapReduceAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            appCount = mrDetailsPage.clickOnlyLink("Success");
            waitExecuter.waitUntilPageFullyLoaded();
            //Clicking on the Map reduce app must go to apps detail page and verify Data Tabs must be available on UI
            if (appCount > 0) {
                String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
                test.log(LogStatus.PASS, "Map Reduce Application Id is displayed in the Header: " + headerAppId);
                waitExecuter.waitUntilPageFullyLoaded();
                MouseActions.clickOnElement(driver, mrApps.analysisTab);
                mrDetailsPage.analysisColorCode(mrApps);
                waitExecuter.waitUntilPageFullyLoaded();
                //Close apps details page
                MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
            } else {
                test.log(LogStatus.SKIP, "No Map Reduce Application present");
                logger.info("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");

            }
        }
    }
}
