package com.qa.testcases.appdetails.spark;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_spark_224 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Stage for the Job should be displayed
     * 2. Stage must contain these fields ,
     *     Stage ID, Start Time, Duration, tasks, shuffle read, Shuffle write, Input, Output
     */
    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_224.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_224_verifyAppDetailsPageStage(String clusterId) {
        test = extent.startTest("TC_spark_224_verifyAppDetailsPageStage: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory("4620 Apps Details-Spark");
        Log.startTestCase("TC_spark_224_verifyAppDetailsPageStage");


        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkAppsDetailsPageObject = new SparkAppsDetailsPageObject(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has spark check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject, applicationsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);

            /**clicking on the UI must go to apps detail page and verify the basic tabs present */
            appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, false);
        }
        else
            {
                logger.info("No Spark Application present in the "+ clusterId+ " cluster for the time span " +
                            "of 90 days");
            }
        //Close apps details page
        sparkAppsDetailsPageObject.closeAppsPageTab.click();
    }
}
