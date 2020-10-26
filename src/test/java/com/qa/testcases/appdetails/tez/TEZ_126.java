package com.qa.testcases.appdetails.tez;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
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

public class TEZ_126 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_spark_219.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_124_verifyKPIsarelistedandhavedata(String clusterId) {
        test = extent.startTest("TEZ_124_verifyKPIsarelistedandhavedata: " + clusterId,
                "Verify KPIs are listed and have data");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_124_verifyKPIsarelistedandhavedata");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
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
        Assert.assertEquals(appCount, totalCount, "The tez app count of SparkApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = tezDetailsPage.verifyAppId(tezApps, applicationsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);

            /**clicking on the UI must go to apps detail page and verify the basic tabs present */
            tezDetailsPage.verifyAppsComponent(tezApps, false, false);
            test.log(LogStatus.PASS, "The basic components for an application is present");
        } else {
            logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
        //Close apps details page
        MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
        //  sparkAppsDetailsPageObject.closeAppsPageTab.click();
    }
}
