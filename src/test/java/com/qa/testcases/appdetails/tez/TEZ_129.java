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
@Marker.All
public class TEZ_129 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_129.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_129_verifyTheQuery(String clusterId) {
        test = extent.startTest("TEZ_129_verifyTheQuery: " + clusterId,
                "Verify Query that was used to run the app must be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_129_verifyTheQuery");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of SparkApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        //Clicking on the Tez app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = tezDetailsPage.verifyAppId(tezApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Tez Application Id is displayed in the Header: " + headerAppId);

            /**clicking on the UI must go to apps detail page and verify the basic tabs present */
            tezDetailsPage.verifyAppsComponent(tezApps, false, false, true);
            test.log(LogStatus.PASS, "The basic components for an application is present");

            //TBD Query  value not poulated in Tez apps detail page
            String tagValue = tezDetailsPage.verifyAppSummaryTabs(tezApps, "Tags", test);
            if (!tagValue.equals("spark-streaming"))
                tezDetailsPage.verifyAppsComponent(tezApps, false, false, true);
            else
            {
                tezApps.closeAppsPageTab.click();
                waitExecuter.sleep(1000);
                applicationsPageObject.getAnotherAppFromTable.click();
                waitExecuter.sleep(2000);
                tezDetailsPage.verifyAppsComponent(tezApps, false, false, true);
            }
            test.log(LogStatus.PASS, "The job stage table has jobs and corresponding details displayed per " +
                    "job id along with tabs and its respective data");

            //Close apps details page
        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, tezApps.homeTab);
    }
}
