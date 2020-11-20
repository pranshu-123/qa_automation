package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.spark.TC_spark_219;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.AppDetailsTez
@Marker.All
public class TEZ_008 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_008.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_008_verifyDataIO(String clusterId) {
        test = extent.startTest("TEZ_008_verifyDataIO: " + clusterId,
                "Verify Data IO must be present for apps that access the data.");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_008_verifyDataIO");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
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
        Assert.assertEquals(appCount, totalCount, "The tez app count of tezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        /*
         * Validate the start time types are --
         */
        if (appCount > 0) {
            String Read = tezDetailsPage.verifyRead(tezApps);
            test.log(LogStatus.PASS, "Read IO is displayed in the Tez Table: " + Read);

            String Write = tezDetailsPage.verifyWrite(tezApps);
            test.log(LogStatus.PASS, "Write IO is displayed in the Tez Table: " + Write);

        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");


        }
        //Close apps details page
        MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
    }
}
