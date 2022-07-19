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
@Marker.EMRMapReduce
@Marker.All
public class MR_046 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_046.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_046_verifyMRAppsList(String clusterId) {
        test = extent.startTest("MR_046_verifyMRAppsList: " + clusterId,
                "Verify User must be able to open the MR app detail page");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_046_verifyMRAppsList");

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
        mrDetailsPage.clickOnlyLink("MapReduce");
        waitExecuter.sleep(2000);
        applicationsPageObject.expandStatus.click();
        int appCount = mrDetailsPage.clickOnlyLink("Success");
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);
        mrApps.sortByDurationApp.click();
        waitExecuter.waitUntilPageFullyLoaded();
        mrApps.sortUp.click();
        waitExecuter.sleep(2000);
        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = mrDetailsPage.verifyAppId(mrApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            mrDetailsPage.verifyRightPaneKpis(mrApps);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            waitExecuter.waitUntilElementPresent(mrApps.closeAppsPageTab);
            MouseActions.clickOnElement(driver, mrApps.closeAppsPageTab);
        } else {
            test.log(LogStatus.SKIP, "No Spark Application present");
            logger.error("No Map Reduce Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }
}