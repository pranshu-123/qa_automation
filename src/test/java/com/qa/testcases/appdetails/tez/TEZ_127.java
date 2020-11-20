package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.spark.TC_spark_219;
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
public class TEZ_127 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_127.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_127_verifyAppStatus(String clusterId) {
        test = extent.startTest("TEZ_127_verifyAppStatus: " + clusterId,
                "Verify App status must be updated relevantly");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_127_verifyAppStatus");

        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);


        //Verify that the left pane has Tez check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Tez check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        tezDetailsPage.clickOnlyLink("Tez");
        applicationsPageObject.expandStatus.click();
        int appCount = tezDetailsPage.clickOnlyLink("Success");
        //Clicking on the Tez app must go to apps detail page
        if (appCount > 0) {
            String statusValue = tezDetailsPage.verifyStatus(tezApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Tez Status  is displayed in the Header: " + statusValue);
            //Close apps details page
            MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            waitExecuter.sleep(3000);


        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, tezApps.homeTab);
    }
}
