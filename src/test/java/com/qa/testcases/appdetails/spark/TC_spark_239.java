package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.GCPAppDetailsSpark
@Marker.All
public class TC_spark_239 extends BaseClass {
    /**
     * Verify that on a cluster with different kinds of Spark Apps:
     * Execution should loaded
     * UI should contain flowchart about RDD
     */
    Logger logger = LoggerFactory.getLogger(TC_spark_239.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_239_verifyJobsExecutionTab(String clusterId) {
        test = extent.startTest("TC_spark_239_verifyJobsExecutionTab: " + clusterId,
                "Verify that  Execution should loaded\n" +
                    " UI should contain flowchart about RDD");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_239_verifyJobsExecutionTab");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkAppsDetailsPageObject = new SparkAppsDetailsPageObject(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has spark check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");
        appsDetailsPage.clickOnlyLink("Spark");
        applicationsPageObject.expandStatus.click();
        int appCount = appsDetailsPage.clickOnlyLink("Success");

        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            /**clicking on the UI must go to apps detail page and verify the basic tabs present */
            String tagValue = appsDetailsPage.verifyAppSummaryTabs(sparkAppsDetailsPageObject, "Tags", test);
            if (!tagValue.equals("spark-streaming"))
              appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, true, false);
            else
            {
                sparkAppsDetailsPageObject.closeAppsPageTab.click();
                waitExecuter.sleep(1000);
                applicationsPageObject.getAnotherAppFromTable.click();
                waitExecuter.sleep(2000);
                appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, true, false);
            }
            test.log(LogStatus.PASS, "Verified that the Execution tab is loaded and contains a flowchart about RDD");
            //Close apps details page
            MouseActions.clickOnElement(driver, sparkAppsDetailsPageObject.closeAppsPageTab);
        } else {
            test.log(LogStatus.SKIP , "No Spark Application present");
            logger.warn("No Spark Application present in the " + clusterId + " cluster for the time span " +
                "of 90 days");
        }
    }
}
