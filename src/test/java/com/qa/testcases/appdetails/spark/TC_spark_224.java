package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_224 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * 1. Stage for the Job should be displayed
     * 2. Stage must contain these fields ,
     * Stage ID, Start Time, Duration, tasks, shuffle read, Shuffle write, Input, Output
     */
    Logger logger = LoggerFactory.getLogger(TC_spark_224.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_224_verifyAppDetailsPageStage(String clusterId) {
        test = extent.startTest("TC_spark_224_verifyAppDetailsPageStage: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
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
        appsDetailsPage.clickOnlyLink("Spark");
        applicationsPageObject.expandStatus.click();
        int appCount = appsDetailsPage.clickOnlyLink("Success");

        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject, applicationsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);

            /**clicking on the UI must go to apps detail page and verify the basic tabs present */
            String tagValue = appsDetailsPage.verifyAppSummaryTabs(sparkAppsDetailsPageObject, "Tags", test);
            if (!tagValue.equals("spark-streaming"))
              appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, false, false);
            test.log(LogStatus.PASS, "The job stage table has jobs and corresponding details displayed per job id");
            //Close apps details page
            MouseActions.clickOnElement(driver, sparkAppsDetailsPageObject.closeAppsPageTab);
        } else {
            test.log(LogStatus.SKIP, "No Spark Application present");
            logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
                "of 90 days");
        }
    }
}
