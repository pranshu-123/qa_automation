package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.AppDetailsTez
@Marker.EMRTez
@Marker.GCPAppDetailsTez
@Marker.All
public class TEZ_126 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_126.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_126_verifyClusterAndQueue(String clusterId) {
        test = extent.startTest("TEZ_126_verifyClusterAndQueue: " + clusterId,
                "Verify KPIs should be populated and the values must be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_126_verifyClusterAndQueue");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int appCount = tezDetailsPage.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of TezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        applicationsPageObject.expandStatus.click();
        appCount = tezDetailsPage.clickOnlyLink("Success");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            //Clicking on the Tez app must go to apps detail page
            if (appCount > 0) {
                String validateTopRightTab = tezDetailsPage.validateTopRightTab(tezApps, test);
                test.log(LogStatus.PASS, "The basic components for an application is present" + validateTopRightTab);
            } else {
                test.log(LogStatus.SKIP, "No Tez Application present");
                logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
            MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            waitExecuter.sleep(3000);

        } catch (VerifyError te) {
            throw new AssertionError("No Tez Application present in the \" + clusterId + \" cluster for the time span \" +\n" +
                    " \"of 90 days\"" + te);
        }
    }
}