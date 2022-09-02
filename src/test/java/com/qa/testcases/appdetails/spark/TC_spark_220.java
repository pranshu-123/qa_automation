package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;

import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.GCPAppDetailsSpark
@Marker.All
public class TC_spark_220 extends BaseClass {
    /**
     * Verify the attempts and the KPIs for a Spark App:
     * 1. Application details page should be opened
     * 2. Left pane must be opened and should have KPIs listed (start, end and duration listed and should not be empty)
     * 3. If there are failed attempts then, there should be attempts tab under which attempts attempts for "failed"
     * and "success" must be displayed in the form of bar graph
     */
    Logger logger = LoggerFactory.getLogger(TC_spark_220.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_220_verifyAttemptsAndKpis(String clusterId) {
        test = extent.startTest("TC_spark_220_verifyAttemptsAndKpis: " + clusterId,
                "Verify 1. Application details page should be opened\n" +
                    "  2. Left pane must be opened and should have KPIs listed (start, end and duration listed and should not be empty)\n" +
                    "  3. If there are failed attempts then, there should be attempts tab under which attempts attempts for \"failed\"\n" +
                    "    and \"success\" must be displayed in the form of bar graph");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_220_verifyAttemptsAndKpis");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkPageObj = new SparkAppsDetailsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
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
            appsDetailsPage.navigateToFailedAppsAppPage(applicationsPageObject, sparkPageObj, test, false);
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with attempt data ");
        } else {
            //Close apps details page
            MouseActions.clickOnElement(driver, sparkPageObj.homeTab);
            test.log(LogStatus.SKIP, "No Spark Application present");
            logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
                "of 90 days");
        }
    }
}



